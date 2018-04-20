package co.edu.uniandes.testrunner.web.controller;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;
import co.edu.uniandes.testrunner.core.util.ApplicationConstants;
import co.edu.uniandes.testrunner.core.util.WildardReplaceUtil;
import co.edu.uniandes.testrunner.web.configuration.os.PathConfiguratorPropertyKeys;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
@ViewScoped
public class WebMB extends BaseMB {

	private String lighthouseURL;

	private String cypressURL;

	private Properties properties;

	@PostConstruct
	public void init() {
		properties = (Properties) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(ApplicationConstants.PATH_SESSION_KEY);
	}

	public void lighthouseTest() {
		CommandRunner.getRunner().runCommand(WebConstants.LIGHTHOUSE_BASE + lighthouseURL);
		infoMessage(WebConstants.LIGHTHOUSE_FINISHED + lighthouseURL);
	}

	public void cypressRandomTest() {
		String cypressMonkeyTestPath = properties.getProperty(PathConfiguratorPropertyKeys.CYPRESS_PATH);
		WildardReplaceUtil wildcardUtil = new WildardReplaceUtil();
		wildcardUtil.replaceCypressMonkey(cypressMonkeyTestPath + WebConstants.CYPRESS_MONEY_SCRIPT, cypressURL);
		CommandRunner.getRunner()
				.runCommand(cypressMonkeyTestPath + String.format(WebConstants.CYPRESS_RUN, cypressMonkeyTestPath));
		infoMessage(WebConstants.CYPRESS_FINISHED + cypressURL);
	}

	public String getLighthouseURL() {
		return lighthouseURL;
	}

	public void setLighthouseURL(String lighthouseURL) {
		this.lighthouseURL = lighthouseURL;
	}

	public String getCypressURL() {
		return cypressURL;
	}

	public void setCypressURL(String cypressURL) {
		this.cypressURL = cypressURL;
	}

}
