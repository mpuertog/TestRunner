package co.edu.uniandes.testrunner.web.configuration.os;

import java.util.Properties;

import co.edu.uniandes.testrunner.core.util.ApplicationConstants;

/**
 * {@link PathConfigurator} específico para trabajar desde Ubuntu 17.10
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class PathConfiguratorUbuntu extends PathConfigurator {

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
