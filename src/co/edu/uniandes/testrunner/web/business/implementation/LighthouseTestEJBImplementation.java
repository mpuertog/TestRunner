package co.edu.uniandes.testrunner.web.business.implementation;

import java.util.Arrays;
import java.util.Base64;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import co.edu.uniandes.testrunner.core.loader.Loader;
import co.edu.uniandes.testrunner.core.util.FilesConstants;
import co.edu.uniandes.testrunner.web.business.LightHouseEJB;
import co.edu.uniandes.testrunner.web.persistance.dao.LighthouseDAO;
import co.edu.uniandes.testrunner.web.persistance.entities.TestDetail;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

@Stateless
public class LighthouseTestEJBImplementation implements LightHouseEJB {

	protected final static Logger logger = Logger.getRootLogger();

	@EJB
	private LighthouseDAO lightHouseDAO;

	@Override
	public void saveLighthouseTest(TestRun testRun, String outputFile) {
		TestDetail testDetail = new TestDetail();
		testDetail.setFileName(FilesConstants.LIGHTHOUSE_FILENAME);
		testDetail.setFileContent(getBase64CypressJSON(outputFile));
		testRun.setTestDetails(Arrays.asList(testDetail));
		lightHouseDAO.saveLighthouseTest(testRun);

	}

	@Override
	public void saveCypressRandomTest(TestRun testRun, String ouutputFile) {
		TestDetail testDetail = new TestDetail();
		testDetail.setFileName(FilesConstants.CYPRESS_FILENAME);
		testRun.setTestDetails(Arrays.asList(testDetail));
		lightHouseDAO.saveCypressRandomTest(testRun);

	}

	private String getBase64CypressJSON(String filePath) {
		String originalInput = null;
		try {
			Thread.sleep(2000);
			originalInput = Loader.readFile(filePath);
			return Base64.getEncoder().encodeToString(originalInput.getBytes());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}

}
