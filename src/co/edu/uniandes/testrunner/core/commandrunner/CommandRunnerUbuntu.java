package co.edu.uniandes.testrunner.core.commandrunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;

import co.edu.uniandes.testrunner.core.util.ApplicationConstants;
import co.edu.uniandes.testrunner.core.util.ApplicationLogMessages;
import co.edu.uniandes.testrunner.web.persistance.dao.TestRunnerDAO;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;

/**
 * Implementación específica para Ubuntu 17.10 del {@link CommandRunner}
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class CommandRunnerUbuntu extends CommandRunner {

	@EJB
	private TestRunnerDAO testRunnerDAO;

	@Override
	public void runCommand(String command) {
		String s = null;
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

	@Override
	public void runCommand(TestRun testRun) {
		List<String> commandList = Arrays.asList(ApplicationConstants.BASH, ApplicationConstants.BASH_PARAM,
				testRun.getTestCommand());
		ProcessBuilder processBuilder = new ProcessBuilder(commandList);
		processBuilder.redirectErrorStream(true);
		logger.info(String.format(ApplicationLogMessages.LOG_RUNNING_COMMAND, testRun.getTestCommand()));
		if (this.getWorkingDirectory() != null) {
			processBuilder.directory(this.getWorkingDirectory());
			logger.info(String.format(ApplicationLogMessages.LOG_WORKING_DIRECTORY, this.getWorkingDirectory()));
		}
		try {
			process = processBuilder.start();
			String s = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((s = reader.readLine()) != null) {
				logger.info(s);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		logger.info(ApplicationLogMessages.LOG_COMMAND_COMPLETE);

	}

}
