package co.edu.uniandes.testrunner.web.transversal;

/**
 * Excepción personalizada para lanzar cuando se está serializando un archivo
 * para enviar a base de datos
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class FileProcessException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileProcessException() {
		super();
	}

	public FileProcessException(Exception e) {
		super(e);
	}

	public FileProcessException(String message) {
		super(message);
	}

	public FileProcessException(String message, Exception e) {
		super(message, e);
	}

}
