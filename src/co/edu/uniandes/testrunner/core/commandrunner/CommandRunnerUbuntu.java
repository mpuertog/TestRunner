package co.edu.uniandes.testrunner.core.commandrunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import co.edu.uniandes.testrunner.core.util.ApplicationConstants;
import co.edu.uniandes.testrunner.core.util.ApplicationLogMessages;

/**
 * Implementación específica para Ubuntu 17.10 del {@link CommandRunner}
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class CommandRunnerUbuntu extends CommandRunner {

	@Override
	public void runCommand(String command) {

		try {
			List<String> commandList = Arrays.asList(ApplicationConstants.BASH, ApplicationConstants.BASH_PARAM,
					command);
			ProcessBuilder processBuilder = new ProcessBuilder(commandList);
			processBuilder.redirectErrorStream(true);
			logger.info(String.format(ApplicationLogMessages.LOG_RUNNING_COMMAND, command));
			if (this.getWorkingDirectory() != null) {
				processBuilder.directory(this.getWorkingDirectory());
				logger.info(String.format(ApplicationLogMessages.LOG_WORKING_DIRECTORY, this.getWorkingDirectory()));
			}
			process = processBuilder.start();
			Thread commandLineThread = new Thread(() -> {
				try {
					String s = null;
					BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					while ((s = reader.readLine()) != null) {
						logger.info(s);
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			});
			commandLineThread.setDaemon(true);
			commandLineThread.start();
			logger.info(ApplicationLogMessages.LOG_COMMAND_COMPLETE);
		} catch (IOException e) {
			logger.error(ApplicationLogMessages.LOG_COMMAND_ERROR, e);
		}
	}

}
