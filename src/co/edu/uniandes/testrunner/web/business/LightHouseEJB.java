package co.edu.uniandes.testrunner.web.business;

import javax.ejb.Local;

import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

@Local
public interface LightHouseEJB {

	public void saveLighthouseTest(String command);

	public void saveCypressRandomTest(String command);

	public void saveCypressE2ETest(String command);

	public void savePitestTest(TestRun testRun, String command);

}
