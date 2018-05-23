package co.edu.uniandes.testrunner.core.util;

/**
 * Clase que tiene todas las contantes relacionadas con el manejo de los
 * archivos relacionados con los frameworks de pruebas
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class FilesConstants {

	public static final String CALABASH_FILENAME = "calabash.json";
	public static final String CALABASH_ROOT_FOLDER = "androidTest";
	public static final String CALABASH_CONFIG_SRC = "/WEB-INF/codetemplates/cucumber.yml";
	public static final String CALABASH_SCREENSHOTS = "%s/screenshots/";
	public static final String CALABASH_PATH = "/TestRunner/webTest/androidTest/current/";
	public static final String CALABASH_DELETE_APK = "find %s -type f -name '*.apk' -delete";

	public static final String CYPRESS_FILENAME = "cypress.json";
	public static final String CYPRESS_PATH = "/TestRunner/webTest/cypress/integration/";
	public static final String CYPRESS_DELETE_JS = "find %s -type f -name '*.js' -delete";

	public static final String LIGHTHOUSE_FILENAME = "lighthouse.json ";
	public static final String LIGHTHOUSE_PATH = "/TestRunner/webTest/lighthouse/";

}
