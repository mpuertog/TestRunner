package co.edu.uniandes.testrunner.web.persistance.dao;

import javax.ejb.Local;

import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

@Local
public interface TestRunnerDAO {
	
	public TestRun updateTest(TestRun testRun);

	public TestRun saveLighthouseTest(TestRun testRun);
	
	public TestRun saveCypressRandomTest(TestRun testRun);
	
	public TestRun savePitestTest(TestRun testRun);

}
