package co.edu.uniandes.testrunner.web.configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class ConfigurationContextListener implements ServletContextListener {

	Logger logger = Logger.getRootLogger();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("Context destoyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Starting context...");
	}

}
