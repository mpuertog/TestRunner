package co.edu.uniandes.testrunner.web.business.implementation;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;
import co.edu.uniandes.testrunner.core.util.ApplicationConstants;
import co.edu.uniandes.testrunner.core.util.FilesConstants;
import co.edu.uniandes.testrunner.core.util.WildardReplaceUtil;
import co.edu.uniandes.testrunner.web.business.AndroidEJB;
import co.edu.uniandes.testrunner.web.configuration.os.PathConfiguratorPropertyKeys;
import co.edu.uniandes.testrunner.web.persistance.dao.AndroidDAO;
import co.edu.uniandes.testrunner.web.persistance.dao.TestRunnerDAO;
import co.edu.uniandes.testrunner.web.persistance.entities.AndroidEmulator;
import co.edu.uniandes.testrunner.web.persistance.entities.TestDetail;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;
import co.edu.uniandes.testrunner.web.transversal.FileProcessException;
import co.edu.uniandes.testrunner.web.transversal.FileUtil;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@Stateless
public class AndroidEJBImplementation implements AndroidEJB {

	private final Properties properties = (Properties) FacesContext.getCurrentInstance().getExternalContext()
			.getSessionMap().get(ApplicationConstants.PATH_SESSION_KEY);

	@EJB
	private AndroidDAO androidDAO;

	@EJB
	private TestRunnerDAO testRunnerDAO;

	@Override
	public List<AndroidEmulator> listAllEmulators() {
		return androidDAO.findAllAndroidEmulator();
	}

	@Override
	public AndroidEmulator findAndroidEmulatorByID(int androidEmulatorID) {
		return androidDAO.findAndroidEmulatorByID(androidEmulatorID);
	}

	@Override
	public void runAndroidE2ETest(String apkName, String gherkinCode, String encodedAPK, String emulator) {
		String userPath = properties.get(PathConfiguratorPropertyKeys.USER_PROFILE).toString().trim();
		final String emulatorString = emulator;

		TestRun testRun = new TestRun();
		testRun.setTestCommand("calabash-android run " + apkName);
		testRun.setTestDate(new Date());
		testRun.setTestType(emulator);
		testRun.setTestFramework(WebConstants.CALABASH);

		TestDetail testGherkinDetail = new TestDetail();
		testGherkinDetail.setFileName(FilesConstants.CALABASH_FILENAME);
		testGherkinDetail.setFileContent(gherkinCode);
		testGherkinDetail.setTestRun(testRun);

		TestDetail testAPKDetail = new TestDetail();
		testAPKDetail.setFileName(apkName);
		testAPKDetail.setFileContent(encodedAPK);
		testAPKDetail.setTestRun(testRun);

		testRun.setTestDetails(Arrays.asList(testGherkinDetail, testAPKDetail));
		testRun = testRunnerDAO.saveAndroidE2ETest(testRun);

		WildardReplaceUtil wildardReplaceUtil = new WildardReplaceUtil();
		String workFolder = userPath + FilesConstants.CALABASH_PATH;
		String screenshotsFolder = workFolder + "sreenshots/" + testRun.getId() + ApplicationConstants.SLASH;
		String configDestination = workFolder + "config/cucumber.yml";
		String featuresFolder = workFolder + "features/";
		String featureFile = featuresFolder + "test.feature";
		new File(screenshotsFolder).mkdirs();
		CommandRunner androidRunner = CommandRunner.getRunner();

		androidRunner.runCommand(String.format(FilesConstants.CALABASH_DELETE_FEATURE, featuresFolder));
		try {
			FileUtil.writeFileFromBase64(featureFile, gherkinCode);
		} catch (FileProcessException e) {
			e.printStackTrace();
		}

		androidRunner.setWorkingDirectory(new File(workFolder));
		wildardReplaceUtil.replaceCalabashConfig(screenshotsFolder, configDestination);
		Thread emulatorThread = new Thread(() -> {
			try {
				androidRunner.runCommand("pwd");
				androidRunner.runCommand("calabash-android resign " + apkName);
				androidRunner.runCommand(emulatorString);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		emulatorThread.start();
		Thread testThread = new Thread(() -> {
			try {
				Thread.sleep(25000);
				androidRunner
						.runCommand("calabash-android run " + apkName + " -p custom --format json --out test.json");
				androidRunner.runCommand(ApplicationConstants.KILL_EMULATOR);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		testThread.start();
	}

}
