package co.edu.uniandes.testrunner.web.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;
import co.edu.uniandes.testrunner.core.util.ApplicationConstants;
import co.edu.uniandes.testrunner.core.util.FilesConstants;
import co.edu.uniandes.testrunner.core.util.WildardReplaceUtil;
import co.edu.uniandes.testrunner.web.business.LightHouseEJB;
import co.edu.uniandes.testrunner.web.configuration.os.PathConfiguratorPropertyKeys;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
@ViewScoped
public class WebMB extends BaseMB {

	private String lighthouseURL;

	private String cypressURL;

	private String pitestFolder;

	private Properties properties;

	private TestRun testRun;

	@EJB
	private LightHouseEJB lightHouseEJB;

	@PostConstruct
	public void init() {
		properties = (Properties) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(ApplicationConstants.PATH_SESSION_KEY);
	}

	public void lighthouseTest() {
		String userPath = properties.get(PathConfiguratorPropertyKeys.USER_PROFILE).toString().trim();
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(WebConstants.LIGHTHOUSE_BASE, userPath).trim());
		sb.append(FilesConstants.LIGHTHOUSE_PATH);
		sb.append(FilesConstants.LIGHTHOUSE_FILENAME);
		sb.append(lighthouseURL);
		CommandRunner.getRunner().runCommand(sb.toString());
		infoMessage(WebConstants.LIGHTHOUSE_FINISHED + lighthouseURL);
		testRun = new TestRun();
		testRun.setTestCommand(sb.toString());
		testRun.setTestDate(new Date());
		testRun.setTestType(WebConstants.LIGHTHOUSE_TYPE);
		testRun.setTestFramework(WebConstants.LIGHTHOUSE);
		lightHouseEJB.saveLighthouseTest(testRun,
				userPath + FilesConstants.LIGHTHOUSE_PATH + FilesConstants.LIGHTHOUSE_FILENAME);
	}

	public void cypressRandomTest() {
		String cypressMonkeyTestPath = properties.getProperty(PathConfiguratorPropertyKeys.CYPRESS_PATH);
		WildardReplaceUtil wildcardUtil = new WildardReplaceUtil();
		wildcardUtil.replaceCypressMonkey(cypressMonkeyTestPath + WebConstants.CYPRESS_MONEY_SCRIPT, cypressURL);
		CommandRunner.getRunner()
		.runCommand(cypressMonkeyTestPath + String.format(WebConstants.CYPRESS_RUN, cypressMonkeyTestPath));
		infoMessage(WebConstants.CYPRESS_FINISHED + cypressURL);
		testRun = new TestRun();
		testRun.setTestCommand(cypressMonkeyTestPath + String.format(WebConstants.CYPRESS_RUN, cypressMonkeyTestPath));
		testRun.setTestDate(new Date());
		testRun.setTestType(WebConstants.CYPRESS_TYPE);
		testRun.setTestFramework(WebConstants.CYPRESS);
		lightHouseEJB.saveCypressRandomTest(testRun, null);
	}

	public void pitest() throws Exception {
		File pom  = new File(pitestFolder, "pom.xml");
		if (!pom.exists()){
			warningMessage("Debe suminsitrar un proyecto maven");
			return;
		}
		
		boolean junit = FileUtils.readFileToString(pom).replaceAll("\\s+","").contains("<artifactId>junit</artifactId><version>4.6</version>");
		
		if (!junit){
			warningMessage("Debe usar JUnit 4.6");
			return;
		}
		
		boolean pitest = FileUtils.readFileToString(pom).replaceAll("\\s+","").contains("<plugin><groupId>org.pitest</groupId><artifactId>pitest-maven</artifactId>");
		
		if (!pitest){
			String plugin = "<plugin>\n<groupId>org.pitest</groupId>\n<artifactId>pitest-maven</artifactId>\n<version>1.3.2</version>\n</plugin>";
			warningMessage("Debe usar el plugin de Pitest: "+plugin);
			return;
		}
		
		CommandRunner.getRunner().runCommand(String.format(WebConstants.PITEST_RUN, pitestFolder));
		
		testRun = new TestRun();
		testRun.setTestCommand(String.format(WebConstants.PITEST_RUN, pitestFolder));
		testRun.setTestDate(new Date());
		testRun.setTestType(WebConstants.PITEST_TYPE);
		testRun.setTestFramework(WebConstants.PITEST);
		
		File fl = new File(pitestFolder+File.separator+"target"+File.separator+"pit-reports");
	    File[] files = fl.listFiles(new FileFilter() {          
	        public boolean accept(File file) {
	            return file.isDirectory();
	        }
	    });
	    long lastMod = Long.MIN_VALUE;
	    File choice = null;
	    for (File file : files) {
	        if (file.lastModified() > lastMod) {
	            choice = file;
	            lastMod = file.lastModified();
	        }
	    }
		
		lightHouseEJB.savePitestTest(testRun,choice.getAbsolutePath()+File.separator+"index.html");
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

}
