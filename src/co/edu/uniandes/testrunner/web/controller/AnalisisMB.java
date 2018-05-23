package co.edu.uniandes.testrunner.web.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.FileUtils;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;
import co.edu.uniandes.testrunner.web.business.TestEJB;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
@ViewScoped
public class AnalisisMB extends BaseMB {

	@EJB
	private TestEJB lightHouseEJB;

	private String pitestFolder;

	public void pitest() throws Exception {
		File pom = new File(pitestFolder, "pom.xml");
		if (!pom.exists()) {
			warningMessage("Debe suminsitrar un proyecto maven");
			return;
		}

		boolean junit = FileUtils.readFileToString(pom).replaceAll("\\s+", "")
				.contains("<artifactId>junit</artifactId><version>4.6</version>");

		if (!junit) {
			warningMessage("Debe usar JUnit 4.6");
			return;
		}

		boolean pitest = FileUtils.readFileToString(pom).replaceAll("\\s+", "")
				.contains("<plugin><groupId>org.pitest</groupId><artifactId>pitest-maven</artifactId>");

		if (!pitest) {
			String plugin = "<plugin>\n<groupId>org.pitest</groupId>\n<artifactId>pitest-maven</artifactId>\n<version>1.3.2</version>\n</plugin>";
			warningMessage("Debe usar el plugin de Pitest: " + plugin);
			return;
		}

		CommandRunner.getRunner().runCommand(String.format(WebConstants.PITEST_RUN, pitestFolder));

		TestRun testRun = new TestRun();
		testRun.setTestCommand(String.format(WebConstants.PITEST_RUN, pitestFolder));
		testRun.setTestDate(new Date());
		testRun.setTestType(WebConstants.PITEST_TYPE);
		testRun.setTestFramework(WebConstants.PITEST);

		File fl = new File(pitestFolder + File.separator + "target" + File.separator + "pit-reports");
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

		lightHouseEJB.savePitestTest(testRun, choice.getAbsolutePath() + File.separator + "index.html");
	}

	public String getPitestFolder() {
		return pitestFolder;
	}

	public void setPitestFolder(String pitestFolder) {
		this.pitestFolder = pitestFolder;
	}

}
