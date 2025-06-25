package es.manuelcastro.excepciones;

import lombok.NoArgsConstructor;

/**
 * Excepcion personalizada para errores relacionados con la base de datos.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-10
 */

@NoArgsConstructor
public class DDBBException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que permite especificar un mensaje de error.
	 * 
	 * @param mensaje - Mensaje que describe la excepcion
	 */
	public DDBBException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor que permite especificar un mensaje y una causa del error.
	 *
	 * @param mensaje - Mensaje descriptivo del error
	 * @param cause   - Causa original de la excepcion
	 */
	public DDBBException(String mensaje, Throwable cause) {

		super(mensaje, cause);

	}
}
