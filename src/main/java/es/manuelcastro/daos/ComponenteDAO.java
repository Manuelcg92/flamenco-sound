package es.manuelcastro.daos;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.manuelcastro.entidades.Componente;
import es.manuelcastro.entidades.Grupo;
import es.manuelcastro.excepciones.DDBBException;
import es.manuelcastro.excepciones.ResourceNotFoundException;
import es.manuelcastro.interfaces.IComponenteDAO;
import es.manuelcastro.utils.Constantes;

/**
 * Clase ComponenteDAO. Implementacion de la interfaz IComponenteDAO para
 * realizar operaciones CRUD sobre entidades de tipo Componente en la base de
 * datos.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-16
 */

@Transactional
@Repository
public class ComponenteDAO implements IComponenteDAO {

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
	public Componente getComponente(int componenteId) {

		try {

			Session miSesion = sessionFactory.getCurrentSession();

			Componente elComponente = miSesion.get(Componente.class, componenteId);

			if (elComponente == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_CARGAR);

			}

			return elComponente;

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
	public void altaComponente(Componente componente) {

		try {

			Session miSesion = sessionFactory.getCurrentSession();

			miSesion.save(componente);

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
	public void actualizarComponente(Componente componente) {

		try {

			Session miSesion = sessionFactory.getCurrentSession();

			Componente componenteExiste = miSesion.get(Componente.class, componente.getComponenteId());

			if (componenteExiste == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_ACTUALIZAR);

			}

			miSesion.merge(componente);

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
	public void eliminaComponente(int componenteId) {

		try {
			Session miSesion = sessionFactory.getCurrentSession();

			Componente componenteExiste = miSesion.get(Componente.class, componenteId);

			if (componenteExiste == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_ELIMINAR);
			}

			Grupo grupo = componenteExiste.getGrupo();

			if (grupo != null && grupo.getComponentes() != null) {

				grupo.getComponentes().remove(componenteExiste);
			}

			miSesion.delete(componenteExiste);

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
	public int getGrupoId(int componenteId) {

		try {
			Session miSesion = sessionFactory.getCurrentSession();

			Componente componente = miSesion.get(Componente.class, componenteId);

			if (componente == null) {
				throw new ResourceNotFoundException("No se encontró el componente con ID: " + componenteId);
			}

			if (componente.getGrupo() == null) {
				throw new ResourceNotFoundException(
						"El componente con ID " + componenteId + " no tiene grupo asociado");
			}

			return componente.getGrupo().getGrupoId();

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}
	}

}
