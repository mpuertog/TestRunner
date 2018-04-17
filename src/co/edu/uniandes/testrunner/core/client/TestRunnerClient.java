package co.edu.uniandes.testrunner.core.client;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;

/**
 * Clase cliente que ejecuta el {@link CommandRunner}
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class TestRunnerClient {

	public static void main(String[] args) {
		CommandRunner runner = CommandRunner.getRunner();
		runner.runCommand("lighthouse --output json --chrome-flags=\"--headless\" --output-path=./lighthouse-results.json http://www.google.com");
		runner.runCommand("calabash-android resign apk/es.usc.citius.servando.calendula_34.apk");
		runner.runCommand("calabash-android run apk/es.usc.citius.servando.calendula_34.apk --format json --out calendula.json");
	}

}
