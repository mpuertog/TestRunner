package co.edu.uniandes.testrunner.web.persistance.dao;

import java.util.List;

import javax.ejb.Local;

import co.edu.uniandes.testrunner.web.persistance.entities.AndroidEmulator;

@Local
public interface AndroidDAO {

	public List<AndroidEmulator> findAllAndroidEmulator();

	public AndroidEmulator findAndroidEmulatorByID(int androidEmulatorID);

}
