package co.edu.uniandes.testrunner.web.transversal;

public class WebConstants {

	public static final String TEST_CASES_HEADER = "Ejecución de Pruebas";
	public static final String REPORT_HEADER = "Reportes de Ejecución";
	public static final String ABOUT_HEADER = "Acerca de";

	public static final String GROWL_INFO = "Información: ";
	public static final String GROWL_WARNING = "Advertencia: ";
	public static final String GROWL_ERROR = "Error: ";

	public static final String LIGHTHOUSE = "Lighthouse";
	public static final String LIGHTHOUSE_BASE = "lighthouse --output json --chrome-flags=\"--headless\" --output-path=%s ";
	public static final String LIGHTHOUSE_FINISHED = "Análisis de PWA terminado sobre: ";
	public static final String LIGHTHOUSE_TYPE = "Progresivas";

	public static final String CYPRESS = "Cypress";
	public static final String CYPRESS_RUN = "./node_modules/.bin/cypress run -P %s";
	public static final String CYPRESS_MONEY_SCRIPT = "/cypress/integration/monkey.js";
	public static final String CYPRESS_TYPE = "End to End";
	public static final String CYPRESS_FINISHED = "Test End to End finalizado sobre: ";

}
