package es.manuelcastro.daos;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.manuelcastro.entidades.Grupo;
import es.manuelcastro.entidades.Producto;
import es.manuelcastro.excepciones.DDBBException;
import es.manuelcastro.excepciones.ResourceNotFoundException;
import es.manuelcastro.interfaces.IProductoDAO;
import es.manuelcastro.utils.Constantes;


@Transactional
@Repository
public class ProductoDAO implements IProductoDAO{


		// Fabrica de sesiones de Hibernate inyectada automaticamente por Spring
		@Autowired
		private SessionFactory sessionFactory;

		/**
		 * Metodo que recupera un componente a partir de su ID.
		 * 
		 * @param componenteId - ID del componente a recuperar
		 * @return - El componente encontrado
		 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
		 *                                   especificado
		 * @throws DDBBException             si ocurre un error relacionado con la BBDD
		 *                                   durante la operacion
		 * @throws RuntimeException          si ocurre un error inesperado no controlado
		 *                                   especificamente
		 */
		@Override
		public Producto getProducto(int productoId) {

			try {

				Session miSesion = sessionFactory.getCurrentSession();

				Producto elProducto = miSesion.get(Producto.class, productoId);

				if (elProducto == null) {

					throw new ResourceNotFoundException(Constantes.ERROR_CARGAR);

				}

				return elProducto;

			} catch (HibernateException e) {

				throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

			} catch (ResourceNotFoundException e) {

				throw e;

			} catch (Exception e) {

				throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

			}

		}

		/**
		 * Metodo que da de alta un nuevo componente en la base de datos.
		 * 
		 * @param componente - El componente a persistir
		 * @throws DDBBException si ocurre un error relacionado con la BBDD durante la
		 *                       operacion
		 */
		@Override
		public void altaProducto(Producto producto) {

			try {

				Session miSesion = sessionFactory.getCurrentSession();

				miSesion.save(producto);

			} catch (Exception e) {

				throw new DDBBException();

			}
		}

		/**
		 * Metodo que actualiza los datos de un componente existente.
		 * 
		 * @param componente - Componente con los datos actualizados
		 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
		 *                                   especificado
		 * @throws DDBBException             si ocurre un error relacionado con la BBDD
		 *                                   durante la operacion
		 * @throws RuntimeException          si ocurre un error inesperado no controlado
		 *                                   especificamente
		 */
		public void actualizarProducto(Producto producto) {

			try {

				Session miSesion = sessionFactory.getCurrentSession();

				Producto productoExiste = miSesion.get(Producto.class, producto.getProductoId());

				if (productoExiste == null) {

					throw new ResourceNotFoundException(Constantes.ERROR_ACTUALIZAR);

				}

				miSesion.merge(producto);

			} catch (HibernateException e) {

				throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

			} catch (ResourceNotFoundException e) {

				throw e;

			} catch (Exception e) {

				throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

			}
		}

		/**
		 * Metodo que elimina un componente de la base de datos a partir de su ID.
		 * Tambien lo elimina de su grupo asociado si lo tiene.
		 * 
		 * @param componenteId - ID del componente a eliminar
		 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
		 *                                   especificado
		 * @throws DDBBException             si ocurre un error relacionado con la BBDD
		 *                                   durante la operacion
		 * @throws RuntimeException          si ocurre un error inesperado no controlado
		 *                                   especificamente
		 */
		public void eliminaProducto(int productoId) {

			try {
				Session miSesion = sessionFactory.getCurrentSession();

				Producto productoExiste = miSesion.get(Producto.class, productoId);

				if (productoExiste == null) {

					throw new ResourceNotFoundException(Constantes.ERROR_ELIMINAR);
				}

				Grupo grupo = productoExiste.getGrupo();

				if (grupo != null && grupo.getComponentes() != null) {

					grupo.getProductos().remove(productoExiste);
				}

				miSesion.delete(productoExiste);

			} catch (HibernateException e) {

				throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

			} catch (ResourceNotFoundException e) {

				throw e;

			} catch (Exception e) {

				throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

			}
		}

		/**
		 * Metodo que obtiene el ID del grupo al que pertenece un componente.
		 * 
		 * @param componenteId - ID del componente
		 * @return - ID del grupo asociado
		 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
		 *                                   especificado
		 * @throws DDBBException             si ocurre un error relacionado con la BBDD
		 *                                   durante la operacion
		 * @throws RuntimeException          si ocurre un error inesperado no controlado
		 *                                   especificamente
		 */
		public int getProductoId(int productoId) {

			try {
				Session miSesion = sessionFactory.getCurrentSession();

				Producto producto = miSesion.get(Producto.class, productoId);

				if (producto == null) {
					throw new ResourceNotFoundException("No se encontró el componente con ID: " + productoId);
				}

				if (producto.getGrupo() == null) {
					throw new ResourceNotFoundException(
							"El producto con ID " + productoId + " no tiene grupo asociado");
				}

				return producto.getGrupo().getGrupoId();

			} catch (HibernateException e) {

				throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

			} catch (ResourceNotFoundException e) {

				throw e;

			} catch (Exception e) {

				throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

			}
		}

}
