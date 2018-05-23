package co.edu.uniandes.testrunner.web.business;

import javax.ejb.Local;

import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;
import java.io.IOException;
import org.primefaces.event.FileUploadEvent;

@Local
public interface TestEJB {

	public void saveLighthouseTest(String command);

	public void saveCypressRandomTest(String command);

	public void saveCypressE2ETest(String command);

	public void savePitestTest(TestRun testRun, String command);

	public void uploadFile(FileUploadEvent event) throws IOException;

}
