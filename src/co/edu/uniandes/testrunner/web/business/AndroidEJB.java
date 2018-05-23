package co.edu.uniandes.testrunner.web.business;

import java.util.List;

import javax.ejb.Local;

import co.edu.uniandes.testrunner.web.persistance.entities.AndroidEmulator;

@Local
public interface AndroidEJB {

	public List<AndroidEmulator> listAllEmulators();

	public AndroidEmulator findAndroidEmulatorByID(int androidEmulatorid);

	public void runAndroidE2ETest(String apkName, String gherkinCode, String encodedTest, String emulator);
}
