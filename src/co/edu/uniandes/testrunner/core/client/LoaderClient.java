package co.edu.uniandes.testrunner.core.client;

import co.edu.uniandes.testrunner.core.loader.Loader;
import co.edu.uniandes.testrunner.core.loader.json.CalabashJsonLoader;
import co.edu.uniandes.testrunner.core.loader.json.LightHouseJsonLoader;
import co.edu.uniandes.testrunner.core.loader.pojo.CalabashAndroidPOJO;
import co.edu.uniandes.testrunner.core.loader.pojo.CalabashAndroidStepPOJO;
import co.edu.uniandes.testrunner.core.loader.pojo.LighthousePOJO;

public class LoaderClient {

	public static void main(String[] args) {
		Loader loader = new LightHouseJsonLoader();
		LighthousePOJO pojo = (LighthousePOJO) loader.loadFromFile("lighthouse-results.json");
		System.out.println(pojo.getUserAgent());
		System.out.println(pojo.getInitialUrl());
		System.out.println(pojo.getCategories());
		System.out.println(pojo.getServiceWorker());
		System.out.println(pojo.getWorksOffline());

		Loader loader2 = new CalabashJsonLoader();
		CalabashAndroidPOJO pojo2 = (CalabashAndroidPOJO) loader2.loadFromFile("calendula.json");

		System.out.println(pojo2.getFeatureName());
		System.out.println(pojo2.getscenario());

		for (CalabashAndroidStepPOJO step : pojo2.getStepsList()) {
			System.out.println(step.getName());
			System.out.println(step.getKeyword());
			System.out.println(step.getResult());
		}
	}

}
