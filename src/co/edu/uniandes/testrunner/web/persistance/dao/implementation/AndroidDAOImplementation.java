package co.edu.uniandes.testrunner.web.persistance.dao.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.edu.uniandes.testrunner.web.persistance.dao.AndroidDAO;
import co.edu.uniandes.testrunner.web.persistance.entities.AndroidEmulator;

@Stateless
public class AndroidDAOImplementation implements AndroidDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<AndroidEmulator> findAllAndroidEmulator() {
		TypedQuery<AndroidEmulator> query = em.createNamedQuery("AndroidEmulator.findAll", AndroidEmulator.class);
		return query.getResultList();
	}

	@Override
	public AndroidEmulator findAndroidEmulatorByID(int androidEmulatorID) {
		return em.find(AndroidEmulator.class, androidEmulatorID);
	}

}
