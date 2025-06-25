package es.manuelcastro.interfaces;

import es.manuelcastro.entidades.Componente;

/**
 * Interfaz con los metodos de las distintas operaciones sobre la tabla
 * "componentes" en la BBDD.
 * 
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-16
 *
 */

public interface IComponenteDAO {

	/**
	 * Metodo que recupera la informacion de un componente en base a su id
	 * 
	 * @param componenteId - Valor a asignar
	 */
	Componente getComponente(int componenteId);

	/**
	 * Metodo que se encarga de insertar un componente en la BBDD
	 * 
	 * @param componente - Valor a asignar
	 */
	void altaComponente(Componente componente);

	/**
	 * Metodo que se encarga de actualizar un componente en la BBDD
	 * 
	 * @param componente - Valor a asignar
	 */
	void actualizarComponente(Componente componente);

	/**
	 * Metodo que se encarga de eliminar un componente en la BBDD
	 * 
	 * @param componenteId - Valor a asignar
	 */
	void eliminaComponente(int componenteId);

	/**
	 * Metodo que devuelve el ID del grupo asociado a un componente
	 * 
	 * @param componenteId - Valor a asignar
	 */
	int getGrupoId(int componenteId);

}
