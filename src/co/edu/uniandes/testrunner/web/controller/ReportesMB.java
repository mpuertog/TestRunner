package co.edu.uniandes.testrunner.web.controller;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import co.edu.uniandes.testrunner.core.util.ApplicationConstants;
import co.edu.uniandes.testrunner.web.business.ReportesEJB;
import co.edu.uniandes.testrunner.web.persistance.entities.TestDetail;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

@ManagedBean
@ViewScoped
public class ReportesMB extends BaseMB {

    private String reporteLink;
    private List<TestDetail> reportListCypress;
    private List<TestDetail> reportListCalabash;
    private List<TestDetail> reportListGherkin;
    private List<TestDetail> reportListLightHouse;
    private List<TestDetail> reportListLightPitest;
    private List<TestRun> testListCypress;
    private List<TestRun> testListCalabash;
    private List<TestRun> testListGherkin;
    private List<TestRun> testListLightHouse;
    private List<TestRun> testListLightPitest;
    
    
    private Properties properties;
    
    @EJB
    private ReportesEJB reportes;
    

    @PostConstruct
    public void init() {
        properties = (Properties) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .get(ApplicationConstants.PATH_SESSION_KEY);
        reporteLink = " ";
        testRunList();
        
    }
    
    public void testRunList(){ 
        testListCypress = new ArrayList<>();
        testListCalabash = new ArrayList<>();
        testListGherkin = new ArrayList<>();
        testListLightHouse = new ArrayList<>();
        testListLightPitest = new ArrayList<>();
        testListCypress = reportes.findByFramework(WebConstants.FRAMEWORK_CYPRESS);  
        testListCalabash = reportes.findByFramework(WebConstants.FRAMEWORK_CALABASH);
        testListGherkin = reportes.findByFramework(WebConstants.FRAMEWORK_GHERKIN);
        testListLightHouse = reportes.findByFramework(WebConstants.FRAMEWORK_LIGHTHOUSE);
        testListLightPitest = reportes.findByFramework(WebConstants.FRAMEWORK_PITEST); 
        reportTestDetail();
    }

    public void reportTestDetail () {
        reportListCypress = new ArrayList<>();
        reportListCalabash = new ArrayList<>();
        reportListGherkin = new ArrayList<>();
        reportListLightHouse = new ArrayList<>();
        reportListLightPitest = new ArrayList<>();
        reportListCypress = reportes.findByTestRun(testListCypress);
        reportListCalabash = reportes.findByTestRun(testListCalabash);
        reportListGherkin = reportes.findByTestRun(testListGherkin);
        reportListLightHouse = reportes.findByTestRun(testListLightHouse);
        reportListLightPitest = reportes.findByTestRun(testListLightPitest);    
    }
    
    
    public String getReporteLink() {
        return reporteLink;
    }

    public void setReporteLink(String reporteLink) {
        this.reporteLink = reporteLink;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<TestDetail> getReportListCypress() {
        return reportListCypress;
    }

    public void setReportListCypress(List<TestDetail> reportListCypress) {
        this.reportListCypress = reportListCypress;
    }

    public List<TestDetail> getReportListCalabash() {
        return reportListCalabash;
    }

    public void setReportListCalabash(List<TestDetail> reportListCalabash) {
        this.reportListCalabash = reportListCalabash;
    }

    public List<TestDetail> getReportListGherkin() {
        return reportListGherkin;
    }

    public void setReportListGherkin(List<TestDetail> reportListGherkin) {
        this.reportListGherkin = reportListGherkin;
    }

    public List<TestDetail> getReportListLightHouse() {
        return reportListLightHouse;
    }

    public void setReportListLightHouse(List<TestDetail> reportListLightHouse) {
        this.reportListLightHouse = reportListLightHouse;
    }

    public List<TestDetail> getReportListLightPitest() {
        return reportListLightPitest;
    }

    public void setReportListLightPitest(List<TestDetail> reportListLightPitest) {
        this.reportListLightPitest = reportListLightPitest;
    }

    public List<TestRun> getTestListCypress() {
        return testListCypress;
    }

    public void setTestListCypress(List<TestRun> testListCypress) {
        this.testListCypress = testListCypress;
    }

    public List<TestRun> getTestListCalabash() {
        return testListCalabash;
    }

    public void setTestListCalabash(List<TestRun> testListCalabash) {
        this.testListCalabash = testListCalabash;
    }

    public List<TestRun> getTestListGherkin() {
        return testListGherkin;
    }

    public void setTestListGherkin(List<TestRun> testListGherkin) {
        this.testListGherkin = testListGherkin;
    }

    public List<TestRun> getTestListLightHouse() {
        return testListLightHouse;
    }

    public void setTestListLightHouse(List<TestRun> testListLightHouse) {
        this.testListLightHouse = testListLightHouse;
    }

    public List<TestRun> getTestListLightPitest() {
        return testListLightPitest;
    }

    public void setTestListLightPitest(List<TestRun> testListLightPitest) {
        this.testListLightPitest = testListLightPitest;
    }

    public ReportesEJB getReportes() {
        return reportes;
    }

    public void setReportes(ReportesEJB reportes) {
        this.reportes = reportes;
    }

}
