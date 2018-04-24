package co.edu.uniandes.testrunner.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import co.edu.uniandes.testrunner.web.business.ReportEJB;
import co.edu.uniandes.testrunner.web.persistance.entities.TestDetail;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
@ViewScoped
public class ReportesMB extends BaseMB {

	private String reporteLink;
	private List<TestRun> cypressList = new ArrayList<TestRun>();
	private List<TestRun> lighthouseList = new ArrayList<TestRun>();
	private List<TestRun> calabashList = new ArrayList<TestRun>();
	private List<TestRun> pitestList = new ArrayList<TestRun>();

	private TestRun selectedTestRun;

	@EJB
	private ReportEJB reportEJB;

	@PostConstruct
	public void init() {
		getAllTestReports();
	}

	public void getAllTestReports() {
		cypressList = reportEJB.findByFramework(WebConstants.CYPRESS);
		lighthouseList = reportEJB.findByFramework(WebConstants.LIGHTHOUSE);
		calabashList = reportEJB.findByFramework(WebConstants.CALABASH);
		pitestList = reportEJB.findByFramework(WebConstants.PITEST);
	}

	public void downloadLighthouseReport() throws IOException {

		if (selectedTestRun == null)
			return;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		response.reset();
		response.setHeader("Content-Type", "application/json");

		OutputStream responseOutputStream = response.getOutputStream();
		TestDetail testDetail = reportEJB.findByTestRun(selectedTestRun).getTestDetails().get(0);
		byte[] valueEncoded = (testDetail.getFileContent().getBytes());
		InputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(valueEncoded));

		byte[] bytesBuffer = new byte[2048];
		int bytesRead;
		while ((bytesRead = inputStream.read(bytesBuffer)) > 0) {
			responseOutputStream.write(bytesBuffer, 0, bytesRead);
		}

		responseOutputStream.flush();
		inputStream.close();
		responseOutputStream.close();

		facesContext.responseComplete();

	}

	public String getReporteLink() {
		return reporteLink;
	}

	public void setReporteLink(String reporteLink) {
		this.reporteLink = reporteLink;
	}

	public List<TestRun> getCypressList() {
		return cypressList;
	}

	public void setCypressList(List<TestRun> cypressList) {
		this.cypressList = cypressList;
	}

	public List<TestRun> getLighthouseList() {
		return lighthouseList;
	}

	public void setLighthouseList(List<TestRun> lighthouseList) {
		this.lighthouseList = lighthouseList;
	}

	public List<TestRun> getCalabashList() {
		return calabashList;
	}

	public void setCalabashList(List<TestRun> calabashList) {
		this.calabashList = calabashList;
	}

	public ReportEJB getReportEJB() {
		return reportEJB;
	}

	public void setReportEJB(ReportEJB reportEJB) {
		this.reportEJB = reportEJB;
	}

	public List<TestRun> getPitestList() {
		return pitestList;
	}

	public void setPitestList(List<TestRun> pitestList) {
		this.pitestList = pitestList;
	}

	public TestRun getSelectedTestRun() {
		return selectedTestRun;
	}

	public void setSelectedTestRun(TestRun selectedTestRun) {
		this.selectedTestRun = selectedTestRun;
	}

}
