package co.edu.uniandes.testrunner.web.persistance.dao;

import javax.ejb.Local;

import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

@Local
public interface LighthouseDAO {

	public void saveLighthouseTest(TestRun testRun);
	
	public void saveCypressRandomTest(TestRun testRun);
	
	public void savePitestTest(TestRun testRun);

}
