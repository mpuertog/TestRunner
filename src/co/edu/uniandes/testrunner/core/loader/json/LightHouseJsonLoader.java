package co.edu.uniandes.testrunner.core.loader.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import co.edu.uniandes.testrunner.core.loader.Loader;
import co.edu.uniandes.testrunner.core.loader.pojo.LighthousePOJO;
import co.edu.uniandes.testrunner.core.loader.pojo.LighthouseReportCategoryPOJO;
import co.edu.uniandes.testrunner.core.loader.pojo.POJO;
import co.edu.uniandes.testrunner.core.util.LighthouseJsonKeys;

/**
 * Clase que dado un archivo tipo JSON, especcíficamente los generados por
 * pruebas en calabash-android, Google LightHouse y Cypress, son cargados a
 * objetos {@link POJO} para su posterior renderizado en otros formatos
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class LightHouseJsonLoader extends Loader {

	private LighthousePOJO pojo;

	@Override
	public POJO loadFromFile(String fileName) {
		try {
			pojo = new LighthousePOJO();
			jsonString = readFile(fileName);
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject auditsObject = jsonObject.getJSONObject(LighthouseJsonKeys.AUDITS);
			JSONArray jsonArray = jsonObject.getJSONArray(LighthouseJsonKeys.REPORT_CATEGORIES);

			List<LighthouseReportCategoryPOJO> pojoCategoriesList = new ArrayList<LighthouseReportCategoryPOJO>();
			List<Object> jsonArrayList = jsonArray.toList();

			for (Object jsonArrayForObject : jsonArrayList) {
				@SuppressWarnings("rawtypes")
				HashMap tempHashMap = (HashMap) jsonArrayForObject;
				LighthouseReportCategoryPOJO categoryPojo = new LighthouseReportCategoryPOJO();
				categoryPojo.setCategoryName((String) tempHashMap.get(LighthouseJsonKeys.NAME));
				categoryPojo.setCategoryScore((Number) tempHashMap.get(LighthouseJsonKeys.SCORE));
				pojoCategoriesList.add(categoryPojo);
			}

			pojo.setCategories(pojoCategoriesList);
			pojo.setLighthouseVersion(jsonObject.getString(LighthouseJsonKeys.LIGHTHOUSE_VERSION));
			pojo.setInitialUrl(jsonObject.getString(LighthouseJsonKeys.INITIAL_URL));
			pojo.setUserAgent(jsonObject.getString(LighthouseJsonKeys.USER_AGENT));
			pojo.setServiceWorker(auditsObject.getJSONObject(LighthouseJsonKeys.SERVICE_WORKER)
					.get(LighthouseJsonKeys.DESCRIPTION).toString());
			pojo.setWorksOffline(auditsObject.getJSONObject(LighthouseJsonKeys.WORKS_OFFLINE)
					.get(LighthouseJsonKeys.DESCRIPTION).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pojo;
	}

}
