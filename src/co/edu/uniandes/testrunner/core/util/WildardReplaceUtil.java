package co.edu.uniandes.testrunner.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.faces.context.FacesContext;

/**
 * Utilidades para reemplazar por la URL a analizar un comodín que se encuentra
 * en los archivos que interpreta el framework de pruebas
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class WildardReplaceUtil {

	/**
	 * Reemplaza el Wildcard con la URL recibida como parámetro y escribe en la
	 * carpeta de destino el archivo JS correspondiente a Cypress
	 * 
	 * @param filePath
	 *            La URL del archivo Cypress de destino
	 * @param url
	 *            La URL que debe ir dentro del archivo
	 */
	public void replaceCypressMonkey(String filePath, String url) {
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		String path = "/WEB-INF/codetemplates/cypressTest/monkey.js";
		try {
			InputStream inStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(path);
			bufferedReader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			String text = stringBuilder.toString();
			text = text.replaceAll(ApplicationConstants.URL_WILDCARD, url);

			File targetFile = new File(filePath);
			targetFile.delete();
			targetFile = new File(filePath);

			try (PrintWriter out = new PrintWriter(filePath)) {
				out.println(text);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
        
        public void runTestCypressByFile(String filePath) {
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		String path = "/WEB-INF/codetemplates/cypressTest/" + filePath;
		try {
			InputStream inStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(path);
			bufferedReader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			String text = stringBuilder.toString();
			File targetFile = new File(filePath);
			targetFile.delete();
			targetFile = new File(filePath);

			try (PrintWriter out = new PrintWriter(filePath)) {
				out.println(text);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Reemplaza el Wildcard con la URI recibida como parámetro y escribe en la
	 * carpeta de destino el archivo correspondiente
	 * 
	 * @param configFilePath
	 *            La URI del archivo de destino
	 * @param screenshotPath
	 *            La URI que debe ir dentro del archivo
	 */
	public void replaceCalabashConfig(String screenshotPath, String configFilePath) {
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			InputStream inStream = FacesContext.getCurrentInstance().getExternalContext()
					.getResourceAsStream(FilesConstants.CALABASH_CONFIG_SRC);
			bufferedReader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			String text = stringBuilder.toString();
			text = text.replaceAll(ApplicationConstants.SCREENSHOT_WILDCARD, screenshotPath);

			File targetFile = new File(configFilePath);
			targetFile.delete();
			targetFile = new File(configFilePath);

			try (PrintWriter out = new PrintWriter(configFilePath)) {
				out.println(text);
			}
		} catch (IOException e) {
			e.printStackTrace();
			new Exception(e);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
