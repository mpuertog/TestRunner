package co.edu.uniandes.testrunner.core.commandrunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import co.edu.uniandes.testrunner.core.util.ApplicationConstants;
import co.edu.uniandes.testrunner.core.util.ApplicationLogMessages;

/**
 * Implementación específica del {@link CommandRunner} para Windows
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class CommandRunnerWindows extends CommandRunner {

	@Override
	public void runCommand(String command) {
		String s = null;
		try {
			List<String> commandList = Arrays.asList(ApplicationConstants.POWER_SHELL, command);
			ProcessBuilder processBuilder = new ProcessBuilder(commandList);
			processBuilder.redirectErrorStream(true);
			logger.info(String.format(ApplicationLogMessages.LOG_RUNNING_COMMAND, command));
			process = processBuilder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((s = reader.readLine()) != null) {
				logger.info(s);
			}
			process.waitFor();
			logger.info(ApplicationLogMessages.LOG_COMMAND_COMPLETE);
		} catch (IOException | InterruptedException e) {
			logger.error(ApplicationLogMessages.LOG_COMMAND_ERROR, e);
		}
	}

}
