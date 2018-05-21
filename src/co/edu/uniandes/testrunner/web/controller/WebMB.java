package co.edu.uniandes.testrunner.web.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.edu.uniandes.testrunner.web.business.LightHouseEJB;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
@ViewScoped
public class WebMB extends BaseMB {

	private String lighthouseURL;

	private String cypressURL;

	private String cypressTest;

	private String pitestFolder;

	@EJB
	private LightHouseEJB lightHouseEJB;

	public void lighthouseTest() {
		lightHouseEJB.saveLighthouseTest(lighthouseURL);
		infoMessage(WebConstants.LIGHTHOUSE_RUNNING + lighthouseURL);
	}

	public void cypressRandomTest() {
		lightHouseEJB.saveCypressRandomTest(cypressURL);
		infoMessage(WebConstants.CYPRESS_RUNNING + cypressURL);
	}

	public void cypressDinamicTest() {
		infoMessage(WebConstants.CYPRESS_RUNNING + cypressURL);
		// lightHouseEJB.saveCypressRandomTest(testRun, null);
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

	public String getPitestFolder() {
		return pitestFolder;
	}

	public void setPitestFolder(String pitestFolder) {
		this.pitestFolder = pitestFolder;
	}

	public String getCypressTest() {
		return cypressTest;
	}

	public void setCypressTest(String cypressTest) {
		this.cypressTest = cypressTest;
	}

}
