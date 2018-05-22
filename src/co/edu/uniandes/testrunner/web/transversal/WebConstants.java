package co.edu.uniandes.testrunner.web.transversal;

public class WebConstants {

	public static final String TEST_CASES_HEADER = "Ejecución de Pruebas";
	public static final String REPORT_HEADER = "Reportes de Ejecución";
	public static final String ANALISIS_HEADER = "Analisis set de pruebas";
	public static final String ABOUT_HEADER = "Acerca de";

	public static final String GROWL_INFO = "Información: ";
	public static final String GROWL_WARNING = "Advertencia: ";
	public static final String GROWL_ERROR = "Error: ";

	public static final String FINISHED = "FINISHED";

	public static final String LIGHTHOUSE = "Lighthouse";
	public static final String LIGHTHOUSE_BASE = "lighthouse --output json --chrome-flags=\"--headless\" --output-path=%s ";
	public static final String LIGHTHOUSE_RUNNING = "Ejecutando análisis de PWA sobre: ";
	public static final String LIGHTHOUSE_TYPE = "Análisis PWA";
	public static final String LIGHTHOUSE_SCORES = "Calificaciones PWA";

	public static final String CYPRESS = "Cypress";
	public static final String CYPRESS_RUN = "./node_modules/.bin/cypress run -P %s";
	public static final String CYPRESS_MONEY_SCRIPT = "/cypress/integration/monkey.js";
	public static final String CYPRESS_MONEY_SCRIPT1 = "/cypress/integration/monkey1.js";
	public static final String CYPRESS_E2E = "End to End";
	public static final String CYPRESS_RANDOM = "Cypress Random";
	public static final String CYPRESS_RUNNING = "Ejecutando Test End to End sobre: ";
	public static final String CALABASH = "Calabash";
	public static final String PITEST = "Pitest";
	public static final String PITEST_RUN = "mvn org.pitest:pitest-maven:mutationCoverage -f '%s'";
	public static final String PITEST_TYPE = "Mutating";
        public static final String UPLOAD_FILE= "Se cargo con elxito el archivo";

	public static final String CONTENT_TYPE = "Content-Type";
	public static final String MIME_JSON = "application/json";

}
