package co.edu.uniandes.testrunner.core.loader;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import co.edu.uniandes.testrunner.core.loader.pojo.POJO;

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

	public abstract POJO loadFromString(String fileContent);

	public static String readFile(String file) throws IOException {
		return FileUtils.readFileToString(new File(file.trim()), "UTF-8");
	}

}
