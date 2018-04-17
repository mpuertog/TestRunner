package co.edu.uniandes.testrunner.core.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import co.edu.uniandes.testrunner.core.loader.pojo.POJO;
import co.edu.uniandes.testrunner.core.util.ApplicationConstants;

/**
 * Clase abstracta que define los métodos a implementar para cada {@link Loader}
 * específico
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public abstract class Loader {

	protected String jsonString;

	public abstract POJO loadFromFile(String fileName);

	protected String readFile(String file) throws IOException {
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String lineSeparator = System.getProperty(ApplicationConstants.LINE_SEPARATOR);
		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(lineSeparator);
			}
			return stringBuilder.toString();
		} finally {
			reader.close();
		}
	}

}
