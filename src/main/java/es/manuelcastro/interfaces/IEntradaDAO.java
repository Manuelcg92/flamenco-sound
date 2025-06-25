package es.manuelcastro.interfaces;

import es.manuelcastro.entidades.Entrada;

/**
 * Interfaz con los metodos de las distintas operaciones sobre la tabla
 * "ventaentrada" en la BBDD
 * 
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-16
 *
 */

public interface IEntradaDAO {

	/**
	 * Metodo que se encarga de insertar una entrada en la BBDD
	 * 
	 * @param entrada - Valor a asignar
	 */
	public void altaEntrada(Entrada entrada);

	/**
	 * Metodo que devuelve el numero de entradas compradas por un usuario para un
	 * evento especifico
	 * 
	 * @param usuarioId - ID del usuario
	 * @param eventoId  - ID del evento
	 */
	public int getNumEntradas(int usuarioId, int eventoId);

}
