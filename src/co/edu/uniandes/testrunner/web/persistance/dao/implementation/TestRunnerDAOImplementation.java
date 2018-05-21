package co.edu.uniandes.testrunner.web.persistance.dao.implementation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.uniandes.testrunner.web.persistance.dao.TestRunnerDAO;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

@Stateless
public class TestRunnerDAOImplementation implements TestRunnerDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public TestRun updateTest(TestRun testRun) {
		em.merge(testRun);
		em.flush();
		return testRun;
	}

	@Override
	public TestRun saveLighthouseTest(TestRun testRun) {
		em.persist(testRun);
		em.flush();
		return em.find(TestRun.class, testRun.getId());
	}

	@Override
	public TestRun saveCypressRandomTest(TestRun testRun) {
		em.persist(testRun);
		em.flush();
		return em.find(TestRun.class, testRun.getId());
	}

	@Override
	public TestRun savePitestTest(TestRun testRun) {
		em.persist(testRun);
		em.flush();
		return em.find(TestRun.class, testRun.getId());
	}

}
