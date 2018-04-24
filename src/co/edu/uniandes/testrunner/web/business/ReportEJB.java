package co.edu.uniandes.testrunner.web.business;

import java.util.List;

import javax.ejb.Local;

import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

@Local
public interface ReportEJB {

	public List<TestRun> findAllTestRun();

	public List<TestRun> findByFramework(String framework);

	public TestRun findByTestRun(TestRun testRun);

}
