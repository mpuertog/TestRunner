package co.edu.uniandes.testrunner.web.controller;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.FileUtils;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;
import co.edu.uniandes.testrunner.web.business.LightHouseEJB;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;
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
    	infoMessage(WebConstants.RUNNING);
       lightHouseEJB.saveCypressRandomTest(cypressURL);
       infoMessage(WebConstants.CYPRESS_RUNNING + cypressURL);
    }
    
    public void cypressDinamicTest() {
    	infoMessage(WebConstants.RUNNING);
	    infoMessage(WebConstants.CYPRESS_RUNNING + cypressURL);
	    //lightHouseEJB.saveCypressRandomTest(testRun, null);
    }
    
    public void pitest() throws Exception {
        File pom = new File(pitestFolder, "pom.xml");
        if (!pom.exists()) {
            warningMessage("Debe suminsitrar un proyecto maven");
            return;
        }

        boolean junit = FileUtils.readFileToString(pom).replaceAll("\\s+", "").contains("<artifactId>junit</artifactId><version>4.6</version>");

        if (!junit) {
            warningMessage("Debe usar JUnit 4.6");
            return;
        }

        boolean pitest = FileUtils.readFileToString(pom).replaceAll("\\s+", "").contains("<plugin><groupId>org.pitest</groupId><artifactId>pitest-maven</artifactId>");

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

        lightHouseEJB.savePitestTest(choice.getAbsolutePath() + File.separator + "index.html");
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
