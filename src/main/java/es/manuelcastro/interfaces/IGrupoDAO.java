package es.manuelcastro.interfaces;

import java.util.List;

import es.manuelcastro.entidades.Grupo;

/**
 * Interfaz con los metodos de las distintas operaciones sobre la tabla "grupos"
 * en la BBDD
 * 
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-03
 */
public interface IGrupoDAO {

	/**
	 * Metodo que busca en la BBDD y recoge toda la lista de la tabla grupos
	 */
	public List<Grupo> getGrupos();

	/**
	 * Metodo que recupera la informacion de un grupo en base a su id
	 * 
	 * @param grupoId - Valor a asignar
	 */
	public Grupo getGrupo(int grupoId);

	/**
	 * Metodo que se encarga de insertar un grupo en la BBDD
	 * 
	 * @param grupo - Valor a asignar
	 */
	public void altaGrupo(Grupo grupo);

	/**
	 * Metodo que se encarga de eliminar un grupo en la BBDD
	 * 
	 * @param grupoId - Valor a asignar
	 */
	public void eliminaGrupo(int grupoId);

	/**
	 * Metodo que se encarga de actualizar un grupo en la BBDD
	 * 
	 * @param grupo - Valor a asignar
	 */
	public void actualizaGrupo(Grupo grupo);

}
