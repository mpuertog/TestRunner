package co.edu.uniandes.testrunner.web.business.implementation;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.edu.uniandes.testrunner.web.business.ReportEJB;
import co.edu.uniandes.testrunner.web.persistance.dao.ReportsDAO;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

@Stateless
public class ReportEJBImplementation implements ReportEJB {

	@EJB
	private ReportsDAO reportsDAO;

	@Override
	public List<TestRun> findAllTestRun() {
		return reportsDAO.findAllTestRun();
	}

	@Override
	public List<TestRun> findByFramework(String framework) {
		return reportsDAO.findTestRunByFramework(framework);
	}

	@Override
	public TestRun findByTestRun(TestRun testRun) {
		return reportsDAO.findTestRun(testRun);
	}

}
