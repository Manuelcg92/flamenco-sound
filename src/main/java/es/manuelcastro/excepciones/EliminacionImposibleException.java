package es.manuelcastro.excepciones;

import lombok.NoArgsConstructor;

/**
 * Excepcion personalizada para cuando quieran eliminar un usuario con entradas
 * o un evento con entradas vendidas
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-18
 */

@NoArgsConstructor
public class EliminacionImposibleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor que permite especificar un mensaje de error.
	 * 
	 * @param mensaje - Mensaje que describe la excepcion
	 */
	public EliminacionImposibleException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor que permite especificar un mensaje y una causa del error.
	 *
	 * @param mensaje - Mensaje descriptivo del error
	 * @param cause   - Causa original de la excepcion
	 */
	public EliminacionImposibleException(String mensaje, Throwable cause) {

		super(mensaje, cause);

	}
}