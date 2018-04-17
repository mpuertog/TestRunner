package co.edu.uniandes.testrunner.core.loader.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import co.edu.uniandes.testrunner.core.loader.Loader;
import co.edu.uniandes.testrunner.core.loader.pojo.CalabashAndroidPOJO;
import co.edu.uniandes.testrunner.core.loader.pojo.CalabashAndroidStepPOJO;
import co.edu.uniandes.testrunner.core.loader.pojo.POJO;
import co.edu.uniandes.testrunner.core.util.CalabashJsonKeys;

/**
 * {@link Loader} específico para el archivo de salida de las pruebas E2E
 * realizadas con Calabash-Android
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
@SuppressWarnings("rawtypes")
public class CalabashJsonLoader extends Loader {

	CalabashAndroidPOJO pojo;

	@Override
	public POJO loadFromFile(String fileName) {
		pojo = new CalabashAndroidPOJO();
		try {

			jsonString = readFile(fileName);
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(CalabashJsonKeys.ELEMENTS);

			List<Object> jsonArrayList = jsonArray.toList();
			List<CalabashAndroidStepPOJO> pojoStepList = new ArrayList<CalabashAndroidStepPOJO>();

			HashMap testElements = (HashMap) jsonArrayList.get(CalabashJsonKeys.ELEMENTS_INDEX);

			ArrayList stepsList = (ArrayList) testElements.get(CalabashJsonKeys.STEPS);

			for (Object step : stepsList) {
				HashMap stepMap = (HashMap) step;
				HashMap stepResults = (HashMap) stepMap.get(CalabashJsonKeys.RESULT);
				CalabashAndroidStepPOJO stepPojo = new CalabashAndroidStepPOJO();
				stepPojo.setKeyword(stepMap.get(CalabashJsonKeys.KEYWORD).toString());
				stepPojo.setName(stepMap.get(CalabashJsonKeys.NAME).toString());
				stepPojo.setResult(stepResults.get(CalabashJsonKeys.STATUS).toString());
				pojoStepList.add(stepPojo);
			}

			pojo.setFeatureName(jsonObject.get(CalabashJsonKeys.NAME).toString());
			pojo.setScenario(testElements.get(CalabashJsonKeys.NAME).toString());
			pojo.setStepsList(pojoStepList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pojo;
	}

}
