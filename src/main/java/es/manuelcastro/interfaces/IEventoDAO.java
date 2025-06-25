package es.manuelcastro.interfaces;

import java.util.List;

import es.manuelcastro.entidades.Evento;

/**
 * Interfaz con los metodos de las distintas operaciones sobre la tabla
 * "eventos" en la BBDD
 * 
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-03
 *
 */
public interface IEventoDAO {

	/**
	 * Metodo que busca en la BBDD y recoge toda la lista de la tabla eventos
	 */
	public List<Evento> getEventos();

	/**
	 * Metodo que recupera la informacion de un evento en base a su id
	 * 
	 * @param eventoId - Valor a asignar
	 */
	public Evento getEvento(int eventoId);

	/**
	 * Metodo que se encarga de insertar un evento en la BBDD
	 * 
	 * @param evento - Valor a asignar
	 */
	public void altaEvento(Evento evento);

	/**
	 * Metodo que se encarga de eliminar un evento en la BBDD
	 * 
	 * @param eventoId - Valor a asignar
	 */
	public void eliminaEvento(int eventoId);

	/**
	 * Metodo que se encarga de actualizar un evento en la BBDD
	 * 
	 * @param evento - Valor a asignar
	 */
	public void actualizarEvento(Evento evento);

	/**
	 * Metodo que devuelve una lista de eventos en base a un filtro especificado
	 * 
	 * @param filtro - Criterio de búsqueda a aplicar
	 */
	public List<Evento> getEventosFiltrados(String filtro);

}
