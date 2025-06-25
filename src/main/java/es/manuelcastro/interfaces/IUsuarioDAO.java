package es.manuelcastro.interfaces;

import es.manuelcastro.entidades.Usuario;

import java.util.List;

/**
 * Interfaz con los metodos de las distintas operaciones sobre la tabla
 * "usuario" en la BBDD
 * 
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-03
 *
 */

public interface IUsuarioDAO {

	/**
	 * Metodo que se encarga de insertar un usuario en la BBDD
	 * 
	 * @param usuario - Valor a asignar
	 */
	void altaUsuario(Usuario usuario);

	/**
	 * Metodo que recupera la informacion de un usuario en base a su id
	 * 
	 * @param usuarioId - Valor a asignar
	 */
	Usuario detalleUsuario(int usuarioId);

	/**
	 * Metodo que se encarga de actualizar un usuario en la BBDD
	 * 
	 * @param usuario - Valor a asignar
	 */
	void actualizaUsuario(Usuario usuario);

	/**
	 * Metodo que se encarga de eliminar un usuario en la BBDD
	 * 
	 * @param usuarioId - Valor a asignar
	 */
	void eliminaUsuario(int usuarioId);

	/**
	 * Metodo que busca en la BBDD y recoge toda la lista de la tabla usuarios
	 */
	List<Usuario> getUsuarios();

	/**
	 * Metodo que busca un usuario en la BBDD en base al nombre de usuario y su
	 * password
	 * 
	 * @param usuario  - Nombre de usuario del Usuario
	 * @param password - Password del Usuario
	 */
	Usuario buscarUsuario(String usuario, String password);

	/**
	 * Metodo que busca un usuario en la BBDD en base a su id
	 * 
	 * @param usuarioId - Valor a asignar
	 */
	Usuario buscarUsuario(int usuarioId);

}
