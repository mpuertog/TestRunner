package co.edu.uniandes.testrunner.core.client;

import java.io.File;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;

/**
 * Clase cliente que ejecuta el {@link CommandRunner}
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class TestRunnerClient {

	public static void main(String[] args) throws InterruptedException {
		CommandRunner runner = CommandRunner.getRunner();
		runner.setWorkingDirectory(new File("/home/santiago/TestRunner/androidTest/calendula"));
		//runner.runCommand("lighthouse --output json --chrome-flags=\"--headless\" --output-path=./lighthouse-results.json http://www.google.com");
		//runner.runCommand("calabash-android resign apk/es.usc.citius.servando.calendula_34.apk");
		//runner.runCommand("calabash-android resign Calendula_v2.5.4_apkpure.com.apk");
		//runner.runCommand("calabash-android run Calendula_v2.5.4_apkpure.com.apk --format json --out calendula.json");
	
		
		Thread t = new Thread(new Runnable() {
	         @Override
	         public void run() {
	        	 runner.runCommand("emulator @Nexus -no-snapshot-load");
		         }
		});
		
		t.start();
		Thread.sleep(25000);
		
		runner.runCommand("calabash-android resign Calendula_v2.5.4_apkpure.com.apk");
		runner.runCommand("calabash-android run Calendula_v2.5.4_apkpure.com.apk -p custom --format json --out calendula.json");
		runner.runCommand("pkill qemu-system-i38");
	}

}
