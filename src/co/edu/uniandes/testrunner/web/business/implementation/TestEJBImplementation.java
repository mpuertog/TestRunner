package co.edu.uniandes.testrunner.web.business.implementation;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;
import co.edu.uniandes.testrunner.core.util.ApplicationConstants;
import co.edu.uniandes.testrunner.core.util.FilesConstants;
import co.edu.uniandes.testrunner.core.util.WildardReplaceUtil;
import co.edu.uniandes.testrunner.web.business.TestEJB;
import co.edu.uniandes.testrunner.web.configuration.os.PathConfiguratorPropertyKeys;
import co.edu.uniandes.testrunner.web.persistance.dao.TestRunnerDAO;
import co.edu.uniandes.testrunner.web.persistance.entities.TestDetail;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;
import co.edu.uniandes.testrunner.web.transversal.FileProcessException;
import co.edu.uniandes.testrunner.web.transversal.FileUtil;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@Stateless
public class TestEJBImplementation implements TestEJB {

	protected final static Logger logger = Logger.getRootLogger();
	protected final Properties properties = (Properties) FacesContext.getCurrentInstance().getExternalContext()
			.getSessionMap().get(ApplicationConstants.PATH_SESSION_KEY);

	@EJB
	private TestRunnerDAO testRunnerDAO;

	@Override
	public void saveLighthouseTest(String command) {
		StringBuilder sb = new StringBuilder();
		sb.append(properties.get(PathConfiguratorPropertyKeys.USER_PROFILE).toString().trim());
		sb.append(FilesConstants.LIGHTHOUSE_PATH);
		sb.append(FilesConstants.LIGHTHOUSE_FILENAME);
		String lightHousecommand = String.format(WebConstants.LIGHTHOUSE_BASE, sb.toString()).trim();
		TestRun testRun = new TestRun();
		testRun.setTestCommand(lightHousecommand + ApplicationConstants.WHITE_SPACE + command);
		testRun.setTestDate(new Date());
		testRun.setTestType(WebConstants.LIGHTHOUSE_TYPE);
		testRun.setTestFramework(WebConstants.LIGHTHOUSE);
		TestDetail testDetail = new TestDetail();
		testDetail.setFileName(FilesConstants.LIGHTHOUSE_FILENAME);
		try {
			testDetail.setFileContent(FileUtil.getBase64FromFile(sb.toString()));
		} catch (FileProcessException e) {
			logger.error(e);
		}
		testDetail.setTestRun(testRun);
		testRun.setTestDetails(Arrays.asList(testDetail));
		testRunnerDAO.saveLighthouseTest(testRun);
		Thread commandLineThread = new Thread(() -> {
			try {
				CommandRunner.getRunner().runCommand(testRun);
				testRun.setTestStatus(WebConstants.FINISHED);
				testRunnerDAO.updateTest(testRun);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		commandLineThread.start();
	}

	@Override
	public void saveCypressRandomTest(String command) {
		String cypressMonkeyTestPath = properties.getProperty(PathConfiguratorPropertyKeys.CYPRESS_PATH);
		WildardReplaceUtil wildcardUtil = new WildardReplaceUtil();
		wildcardUtil.replaceCypressMonkey(cypressMonkeyTestPath + WebConstants.CYPRESS_MONEY_SCRIPT, command);
		TestRun testRun = new TestRun();
		testRun.setTestCommand(cypressMonkeyTestPath + String.format(WebConstants.CYPRESS_RUN, cypressMonkeyTestPath));
		testRun.setTestDate(new Date());
		testRun.setTestType(WebConstants.CYPRESS_RANDOM);
		testRun.setTestFramework(WebConstants.CYPRESS);
		TestDetail testDetail = new TestDetail();
		testDetail.setFileName(FilesConstants.CYPRESS_FILENAME);
		testDetail.setTestRun(testRun);
		testRun.setTestDetails(Arrays.asList(testDetail));
		testRunnerDAO.saveCypressRandomTest(testRun);
		Thread commandLineThread = new Thread(() -> {
			try {
				CommandRunner.getRunner().runCommand(
						cypressMonkeyTestPath + String.format(WebConstants.CYPRESS_RUN, cypressMonkeyTestPath));
				testRun.setTestStatus(WebConstants.FINISHED);
				testRunnerDAO.updateTest(testRun);
			} catch (Exception ex) {
				logger.error(ex);
			}
		});
		commandLineThread.start();

	}

	@Override
	public void saveCypressE2ETest(String command) {
		String cypressMonkeyTestPath = properties.getProperty(PathConfiguratorPropertyKeys.CYPRESS_PATH);
		WildardReplaceUtil wildcardUtil = new WildardReplaceUtil();
		wildcardUtil.replaceCypressMonkey(cypressMonkeyTestPath + WebConstants.CYPRESS_MONEY_SCRIPT1, command);
		CommandRunner.getRunner()
				.runCommand(cypressMonkeyTestPath + String.format(WebConstants.CYPRESS_RUN, cypressMonkeyTestPath));
		TestRun testRun = new TestRun();
		testRun.setTestCommand(cypressMonkeyTestPath + String.format(WebConstants.CYPRESS_RUN, cypressMonkeyTestPath));
		testRun.setTestDate(new Date());
		testRun.setTestType(WebConstants.CYPRESS_E2E);
		testRun.setTestFramework(WebConstants.CYPRESS);
		// lightHouseEJB.saveCypressRandomTest();
	}

	@Override
	public void savePitestTest(TestRun testRun, String ouutputFile) {
		TestDetail testDetail = new TestDetail();
		testDetail.setFileName(ouutputFile);
		testDetail.setTestRun(testRun);
		testRun.setTestDetails(Arrays.asList(testDetail));
		testRunnerDAO.savePitestTest(testRun);
	}

	@Override
	public void uploadFile(UploadedFile file) {
	}

}
