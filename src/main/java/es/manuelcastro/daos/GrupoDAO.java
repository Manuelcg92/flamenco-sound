package es.manuelcastro.daos;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.manuelcastro.entidades.Grupo;
import es.manuelcastro.excepciones.DDBBException;
import es.manuelcastro.excepciones.ResourceNotFoundException;
import es.manuelcastro.interfaces.IGrupoDAO;
import es.manuelcastro.utils.Constantes;

/**
 * Clase DAO(Modelo) que recibe las peticiones del controlador, conecta con la
 * BBDD y le devuelve los datos.
 * 
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-03
 */

@Repository
@Transactional
public class GrupoDAO implements IGrupoDAO {

	// Fabrica de sesiones de Hibernate inyectada automaticamente por Spring
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Metodo que busca en la BBDD y recoge toda la lista de la tabla grupos.
	 * 
	 * @return - Devuelve la lista de grupos
	 * @throws DDBBException si ocurre un error relacionado con la BBDD durante la
	 *                       operacion
	 */
	@Override
	public List<Grupo> getGrupos() {

		try {

			Session miSession = sessionFactory.getCurrentSession();

			Query<Grupo> miQuery = miSession.createQuery("from Grupo", Grupo.class);

			return miQuery.getResultList();

		} catch (Exception e) {

			throw new DDBBException();

		}
	}

	/**
	 * Metodo que recupera la informacion de un grupo en base a su id.
	 * 
	 * @return - Devuelve el grupo especifico
	 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
	 *                                   especificado
	 * @throws DDBBException             si ocurre un error relacionado con la BBDD
	 *                                   durante la operacion
	 * @throws RuntimeException          si ocurre un error inesperado no controlado
	 *                                   especificamente
	 */
	@Override
	public Grupo getGrupo(int grupoId) {

		try {

			Session miSession = sessionFactory.getCurrentSession();

			Grupo grupo = miSession.get(Grupo.class, grupoId);

			if (grupo == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_CARGAR);

			}

			return grupo;

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}
	}

	/**
	 * Metodo que se encarga de insertar un grupo en la BBDD.
	 * 
	 * @throws DDBBException si ocurre un error relacionado con la BBDD durante la
	 *                       operacion
	 */
	@Override
	public void altaGrupo(Grupo grupo) {

		try {

			Session miSession = sessionFactory.getCurrentSession();

			miSession.save(grupo);

		} catch (Exception e) {

			throw new DDBBException();

		}
	}

	/**
	 * Metodo que se encarga de eliminar un grupo en la BBDD.
	 * 
	 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
	 *                                   especificado
	 * @throws DDBBException             si ocurre un error relacionado con la BBDD
	 *                                   durante la operacion
	 * @throws RuntimeException          si ocurre un error inesperado no controlado
	 *                                   especificamente
	 */
	@Override
	public void eliminaGrupo(int grupoId) {

		try {

			Session miSession = sessionFactory.getCurrentSession();

			Grupo grupo = miSession.get(Grupo.class, grupoId);

			if (grupo == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_ELIMINAR);

			}

			miSession.delete(grupo);

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}
	}

	/**
	 * Metodo que se encarga de actualizar un grupo en la BBDD.
	 * 
	 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
	 *                                   especificado
	 * @throws DDBBException             si ocurre un error relacionado con la BBDD
	 *                                   durante la operacion
	 * @throws RuntimeException          si ocurre un error inesperado no controlado
	 *                                   especificamente
	 */
	@Override
	public void actualizaGrupo(Grupo grupo) {
		try {

			Session miSession = sessionFactory.getCurrentSession();

			Grupo grupoExiste = miSession.get(Grupo.class, grupo.getGrupoId());

			if (grupoExiste == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_ACTUALIZAR);

			}

			miSession.merge(grupo);

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}
	}
}
