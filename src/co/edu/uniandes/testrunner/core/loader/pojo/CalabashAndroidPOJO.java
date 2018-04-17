package co.edu.uniandes.testrunner.core.loader.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link POJO} que contiene los resultados de la ejecución de las pruebas E2E
 * con Calabash-Android
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class CalabashAndroidPOJO extends POJO {

	private String featureName;
	private String scenario;
	private List<CalabashAndroidStepPOJO> stepsList;

	public CalabashAndroidPOJO() {
		stepsList = new ArrayList<CalabashAndroidStepPOJO>();
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getscenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public List<CalabashAndroidStepPOJO> getStepsList() {
		return stepsList;
	}

	public void setStepsList(List<CalabashAndroidStepPOJO> stepsList) {
		this.stepsList = stepsList;
	}

}
