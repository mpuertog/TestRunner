package co.edu.uniandes.testrunner.web.persistance.dao;

import java.util.List;

import javax.ejb.Local;

import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

@Local
public interface ReportsDAO {

	public List<TestRun> findAllTestRun();

	public List<TestRun> findTestRunByFramework(String framework);

	public TestRun findTestRun(TestRun testRun);
}
