package co.edu.uniandes.testrunner.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
@ViewScoped
public class WebMB extends BaseMB {

	private String targetURL;

	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}

	public void lighthouseTest() {
		CommandRunner.getRunner().runCommand(WebConstants.LIGHTHOUSE_BASE + targetURL);
		infoMessage(WebConstants.LIGHTHOUSE_FINISHED + targetURL);
	}

	public void cypressTest() {
		CommandRunner.getRunner().runCommand(WebConstants.CYPRESS_COMMAND);
		infoMessage(WebConstants.CYPRESS_FINISHED + targetURL);
	}

}
