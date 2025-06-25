package es.manuelcastro.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

/**
 * Excepcion personalizada para indicar que un recurso especifico no se encontro
 * en la base de datos.
 *
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-10
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que permite especificar un mensaje de error.
	 * 
	 * @param message - Mensaje que describe la excepcion
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}

	/**
	 * Constructor que permite especificar un mensaje y una causa del error.
	 *
	 * @param message - Mensaje descriptivo del error
	 * @param cause   - Causa original de la excepcion
	 */
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}