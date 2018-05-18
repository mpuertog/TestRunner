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

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import co.edu.uniandes.testrunner.core.loader.Loader;
import co.edu.uniandes.testrunner.core.loader.json.LightHouseJsonLoader;
import co.edu.uniandes.testrunner.core.loader.pojo.LighthousePOJO;
import co.edu.uniandes.testrunner.core.loader.pojo.LighthouseReportCategoryPOJO;
import co.edu.uniandes.testrunner.core.util.FilesConstants;
import co.edu.uniandes.testrunner.web.business.ReportEJB;
import co.edu.uniandes.testrunner.web.persistance.entities.TestDetail;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;
import java.awt.Color;
import java.io.File;
import javafx.geometry.Pos;
import javax.imageio.ImageIO;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;

@ManagedBean
@ViewScoped
public class ReportesMB extends BaseMB {

	private String reporteLink;
	private List<TestRun> cypressList = new ArrayList<TestRun>();
	private List<TestRun> lighthouseList = new ArrayList<TestRun>();
	private List<TestRun> calabashList = new ArrayList<TestRun>();
	private List<TestRun> pitestList = new ArrayList<TestRun>();

	private StreamedContent lighthouseDownload;

	private BarChartModel barModel = new BarChartModel();

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

	public void downloadLighthouseReport(TestRun testRun) throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		response.reset();
		response.setHeader(WebConstants.CONTENT_TYPE, WebConstants.MIME_JSON);

		OutputStream responseOutputStream = response.getOutputStream();
		TestDetail testDetail = reportEJB.findByTestRun(testRun).getTestDetails().get(0);
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

		this.lighthouseDownload = new DefaultStreamedContent(inputStream, WebConstants.MIME_JSON,
				FilesConstants.LIGHTHOUSE_FILENAME);
	}

	public void initPWAScoreChart(TestRun testRun) {
		TestDetail testDetail = reportEJB.findByTestRun(testRun).getTestDetails().get(0);
		byte[] valueEncoded = (testDetail.getFileContent().getBytes());
		String fileContent = new String(Base64.getDecoder().decode(valueEncoded));
		BarChartModel model = new BarChartModel();
		ChartSeries scores = new ChartSeries();
		Loader loader = new LightHouseJsonLoader();
		LighthousePOJO pojo = (LighthousePOJO) loader.loadFromString(fileContent);
		for (LighthouseReportCategoryPOJO category : pojo.getCategories()) {
			scores.set(category.getCategoryName(), category.getCategoryScore());
		}
		scores.setLabel(WebConstants.LIGHTHOUSE_SCORES);
		model.setTitle(pojo.getInitialUrl());
		model.addSeries(scores);
		this.barModel = model;
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

	public StreamedContent getLighthouseDownload() {
		return lighthouseDownload;
	}

	public void setLighthouseDownload(StreamedContent lighthouseDownload) {
		this.lighthouseDownload = lighthouseDownload;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

}
