package co.edu.uniandes.testrunner.web.business;

import javax.ejb.Local;

import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

@Local
public interface LightHouseEJB {

	public void saveLighthouseTest(TestRun testRun, String outputFile);

	public void saveCypressRandomTest(TestRun testRun, String outputFileS);

}
