package co.edu.uniandes.testrunner.web.persistance.dao.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.uniandes.testrunner.web.persistance.dao.ReportsDAO;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

@Stateless
public class ReportsDAOImplementation implements ReportsDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<TestRun> findAllTestRun() {
		return em.createNamedQuery("TestRun.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestRun> findTestRunByFramework(String framework) {
		return em.createNamedQuery("TestRun.findByFramework").setParameter("framework", framework).getResultList();
	}

	@Override
	public TestRun findTestRun(TestRun testRun) {
		return em.find(TestRun.class, testRun.getId());
	}

}
