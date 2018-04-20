package co.edu.uniandes.testrunner.web.configuration.os;

import java.util.Properties;

import co.edu.uniandes.testrunner.core.util.ApplicationConstants;

/**
 * Determina la ruta de los archivos base del proyecto, estas dependen del
 * sistema operativo huesped.
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public abstract class PathConfigurator {

	/**
	 * Método estático que resuelve en tiempo de ejecución el cofigurador de rutas
	 * dependiendo del sistema operativo huesped
	 * 
	 * @return
	 */
	public static PathConfigurator getConfigurator() {
		return System.getProperty(ApplicationConstants.OS_NAME_PROPERTY).contains(ApplicationConstants.OS_LINUX)
				? new PathConfiguratorUbuntu()
				: new PathConfiguratorWindows();
	}

	/**
	 * Método abstracto que retorna un objeto {@link Properties} con la
	 * configuración de las rutas a utilizar por la aplicación
	 * 
	 * @return {@link Properties} con las rutas específicas para el sistema
	 *         operativo huesped
	 */
	public abstract Properties buildPathProperties();
}
