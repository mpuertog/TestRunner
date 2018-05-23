package co.edu.uniandes.testrunner.web.transversal;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

import co.edu.uniandes.testrunner.core.loader.Loader;

/**
 * Clase de utilidades para el manejo de archivos
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class FileUtil {

	public FileUtil() {
		throw new IllegalArgumentException();
	}

	/**
	 * Dado un archivo en la ubicación dada, retorna un string codificado en
	 * {@link Base64} con el contenido del archivo
	 * 
	 * @param filePath
	 *            Ruta del archivo a convertir
	 * @return {@link String} codificado en {@link Base64} con el contenido del
	 *         archivo
	 * @throws FileProcessException
	 */
	public static String getBase64FromFile(String filePath) throws FileProcessException {
		String originalInput = null;
		try {
			Thread.sleep(1000);
			originalInput = Loader.readFile(filePath);
			return Base64.getEncoder().encodeToString(originalInput.getBytes());
		} catch (Exception e) {
			throw new FileProcessException("Error procesando el archivo ", e);
		}
	}

	/**
	 * Retorna un {@link String} codificado en {@link Base64}
	 * 
	 * @param fileContent
	 *            {@link String} con el contenido del archivo o lo que se desee
	 *            codificar
	 * @return {@link String} codificado en {@link Base64}
	 */
	public static String getBase64FromString(String fileContent) {
		return Base64.getEncoder().encodeToString(fileContent.getBytes());
	}

	public static void writeFileFromBase64(String filePath, String encodedContent) throws FileProcessException {
		String decodedContent = new String(Base64.getDecoder().decode(encodedContent));
		try {
			FileUtils.writeStringToFile(new File(filePath), decodedContent, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new FileProcessException(e);
		}
	}

}
