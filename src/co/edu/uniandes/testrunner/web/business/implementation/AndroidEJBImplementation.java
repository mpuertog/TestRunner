package co.edu.uniandes.testrunner.web.business.implementation;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.edu.uniandes.testrunner.web.business.AndroidEJB;
import co.edu.uniandes.testrunner.web.persistance.dao.AndroidDAO;
import co.edu.uniandes.testrunner.web.persistance.entities.AndroidEmulator;

@Stateless
public class AndroidEJBImplementation implements AndroidEJB {

	@EJB
	private AndroidDAO androidDAO;

	@Override
	public List<AndroidEmulator> listAllEmulators() {
		return androidDAO.findAllAndroidEmulator();
	}

	@Override
	public AndroidEmulator findAndroidEmulatorByID(int androidEmulatorID) {
		return androidDAO.findAndroidEmulatorByID(androidEmulatorID);
	}

}
