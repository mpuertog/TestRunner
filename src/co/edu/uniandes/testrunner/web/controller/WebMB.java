package co.edu.uniandes.testrunner.web.controller;

import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import java.io.IOException;
import javax.faces.bean.ManagedBean;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import co.edu.uniandes.testrunner.web.business.TestEJB;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
@ViewScoped
public class WebMB extends BaseMB {

    private String lighthouseURL;

    private String cypressURL;

    private String cypressTest;

    private String pitestFolder;

    private UploadedFile file;

    @EJB
    private TestEJB testEJB;

    public void cypressDinamicTest() {
        infoMessage(WebConstants.CYPRESS_RUNNING + cypressURL);
        // lightHouseEJB.saveCypressRandomTest(testRun, null);
    }

    public void upload(FileUploadEvent event) throws IOException {
        testEJB.uploadFile(event);
        infoMessage(WebConstants.UPLOAD_FILE);

    }

    public void cypressRandomTest() {
        testEJB.saveCypressRandomTest(cypressURL);
        infoMessage(WebConstants.CYPRESS_RUNNING + cypressURL);
    }


    public String getLighthouseURL() {
        return lighthouseURL;
    }

    public void setCypressTest(String cypressTest) {
        this.cypressTest = cypressTest;
    }

    public String getCypressURL() {
        return cypressURL;
    }

    public void setCypressURL(String cypressURL) {
        this.cypressURL = cypressURL;
    }
    

}
