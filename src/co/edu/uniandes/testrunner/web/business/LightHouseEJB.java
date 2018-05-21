package co.edu.uniandes.testrunner.web.business;

import javax.ejb.Local;

@Local
public interface LightHouseEJB {

	public void saveLighthouseTest(String command);

	public void saveCypressRandomTest(String command);

	public void saveCypressE2ETest(String command);

}
