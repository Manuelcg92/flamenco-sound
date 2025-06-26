package es.manuelcastro.interfaces;


import java.util.List;

import es.manuelcastro.entidades.Producto;

public interface IProductoDAO {

	/**
	 * Metodo que recupera la informacion de un producto en base a su id
	 * 
	 * @param productoId - Valor a asignar
	 */
	Producto getProducto(int productoId);

	/**
	 * Metodo que se encarga de insertar un producto en la BBDD
	 * 
	 * @param producto - Valor a asignar
	 */
	void altaProducto(Producto producto);

	/**
	 * Metodo que se encarga de actualizar un producto en la BBDD
	 * 
	 * @param producto - Valor a asignar
	 */
	void actualizarProducto(Producto producto);

	/**
	 * Metodo que se encarga de eliminar un producto en la BBDD
	 * 
	 * @param productoId - Valor a asignar
	 */
	void eliminaProducto(int productoId);

	/**
	 * Metodo que devuelve el @Override
	ID del grupo asociado a un producto
	 * 
	 * @param productoId - Valor a asignar
	 */
	int getGrupoId(int productoId);

	List<Producto> getProductos();

}