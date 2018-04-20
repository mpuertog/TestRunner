package co.edu.uniandes.testrunner.web.configuration.os;

import java.util.Properties;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;
import co.edu.uniandes.testrunner.core.util.ApplicationConstants;

/**
 * Implementación específica para Windows del {@link CommandRunner}
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class PathConfiguratorWindows extends PathConfigurator {

	@Override
	public Properties buildPathProperties() {
		Properties properties = new Properties();
		properties.setProperty(PathConfiguratorPropertyKeys.USER_PROFILE,
				System.getenv(PathConfiguratorPropertyKeys.USER_PROFILE));
		properties.setProperty(PathConfiguratorPropertyKeys.CYPRESS_PATH,
				System.getenv(PathConfiguratorPropertyKeys.USER_PROFILE) + ApplicationConstants.CYPRESS_PROJECT_DIR);
		return properties;
	}

}
