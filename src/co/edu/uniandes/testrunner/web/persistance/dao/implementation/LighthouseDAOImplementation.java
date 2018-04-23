package co.edu.uniandes.testrunner.web.persistance.dao.implementation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.uniandes.testrunner.web.persistance.dao.LighthouseDAO;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

@Stateless
public class LighthouseDAOImplementation implements LighthouseDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public void saveLighthouseTest(TestRun testRun) {
		em.persist(testRun);
	}

	@Override
	public void saveCypressRandomTest(TestRun testRun) {
		em.persist(testRun);
	}

}
