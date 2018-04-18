package co.edu.uniandes.testrunner.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;
import co.edu.uniandes.testrunner.core.util.WildardReplaceUtil;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
@ViewScoped
public class WebMB extends BaseMB {

	private String lighthouseURL;

	private String cypressURL;

	public void lighthouseTest() {
		CommandRunner.getRunner().runCommand("cd " + WebConstants.CYPRESS_PARAMETERS);
		CommandRunner.getRunner().runCommand(WebConstants.LIGHTHOUSE_BASE + lighthouseURL);
		infoMessage(WebConstants.LIGHTHOUSE_FINISHED + lighthouseURL);
	}

	public void cypressRandomTest() {
		String cypressMonkeyTestPath = WebConstants.WEB_PROJECTS_URI + "/cypress/integration/monkey.js";
		WildardReplaceUtil wildcardUtil = new WildardReplaceUtil();
		wildcardUtil.replaceCypressMonkey(cypressMonkeyTestPath, cypressURL);
		CommandRunner.getRunner().runCommand("cd " + WebConstants.WEB_PROJECTS_URI);
		CommandRunner.getRunner().runCommand(String.format(WebConstants.CYPRESS_RUN, WebConstants.CYPRESS_PARAMETERS));
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
