package es.manuelcastro.daos;

import es.manuelcastro.entidades.Usuario;
import es.manuelcastro.excepciones.DDBBException;
import es.manuelcastro.excepciones.EliminacionImposibleException;
import es.manuelcastro.excepciones.ResourceNotFoundException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

import es.manuelcastro.interfaces.IUsuarioDAO;
import es.manuelcastro.utils.Constantes;

/**
 * Clase que implementa la interfaz IUsuarioDAO y actua como un Data Access
 * Object (DAO) para la entidad Usuario. Se encarga de las operaciones de
 * persistencia (CRUD) relacionadas con los usuarios en la base de datos.
 * 
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-03
 */

@Repository
@Transactional
public class UsuarioDAO implements IUsuarioDAO {

	// Inyeccion de dependencia de SessionFactory de Hibernate
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Guarda un nuevo objeto Usuario en la base de datos.
	 *
	 * @param usuario - El objeto Usuario a ser guardado
	 * @throws DDBBException si ocurre un error relacionado con la BBDD durante la
	 *                       operacion
	 */
	@Override
	public void altaUsuario(Usuario usuario) {

		try {

			Session currentSession = sessionFactory.getCurrentSession();

			currentSession.save(usuario);

		} catch (Exception e) {

			throw new DDBBException();

		}
	}

	/**
	 * Obtenemos los datos del usuario por su ID.
	 *
	 * @param usuarioId - El ID del usuario a buscar
	 * @return - El objeto Usuario correspondiente al ID proporcionado
	 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
	 *                                   especificado
	 * @throws DDBBException             si ocurre un error relacionado con la BBDD
	 *                                   durante la operacion
	 * @throws RuntimeException          si ocurre un error inesperado no controlado
	 *                                   especificamente
	 */
	@Override
	public Usuario detalleUsuario(int usuarioId) {

		try {

			Session session = sessionFactory.getCurrentSession();

			Usuario usuario = session.get(Usuario.class, usuarioId);

			if (usuario == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_CARGAR);

			}

			return session.get(Usuario.class, usuarioId);

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}
	}

	/**
	 * Eliminamos un usuario de la BBDD segun su ID.
	 *
	 * @param usuario - El objeto Usuario correspondiente al ID proporcionado
	 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
	 *                                   especificado
	 * @throws DDBBException             si ocurre un error relacionado con la BBDD
	 *                                   durante la operacion
	 * @throws RuntimeException          si ocurre un error inesperado no controlado
	 *                                   especificamente
	 */
	@Override
	public void actualizaUsuario(Usuario usuario) {

		try {

			Session session = sessionFactory.getCurrentSession();

			Usuario usuarioExiste = session.get(Usuario.class, usuario.getUsuarioId());

			if (usuarioExiste == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_ACTUALIZAR);
			}
			session.merge(usuario);

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}
	}

	/**
	 * Elimina un usuario en la BBDD.
	 *
	 * @param usuarioId - El id del usuario
	 * @throws ResourceNotFoundException     si no se encuentra el usuario con el ID
	 *                                       especificado
	 * @throws EliminacionImposibleException si encuentra un usuario con entradas de
	 *                                       algun evento
	 * @throws DDBBException                 si ocurre un error relacionado con la
	 *                                       BBDD durante la operacion
	 * @throws RuntimeException              si ocurre un error inesperado no
	 *                                       controlado especificamente
	 */
	@Override
	public void eliminaUsuario(int usuarioId) {

		try {

			Session session = sessionFactory.getCurrentSession();

			Usuario usuario = session.get(Usuario.class, usuarioId);

			if (usuario == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_ELIMINAR);
			}

			Query<Long> query = session.createQuery(
					"SELECT COUNT(e) FROM Entrada e " + "WHERE e.usuario.usuarioId = :usuarioId", Long.class);

			query.setParameter("usuarioId", usuarioId);

			Long numEntradas = query.uniqueResult();

			if (numEntradas > 0) {

				throw new EliminacionImposibleException(Constantes.ERROR_USER_EXIST);
			}

			session.delete(usuario);

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (EliminacionImposibleException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}
	}

	/**
	 * Elimina un usuario en la BBDD.
	 * 
	 * @return - Devuelve la lista de usuarios
	 * @throws DDBBException si ocurre un error durante la consulta a la base de
	 *                       datos
	 */
	@Override
	public List<Usuario> getUsuarios() {

		try {

			Session miSession = sessionFactory.getCurrentSession();

			Query<Usuario> miQuery = miSession.createQuery("from Usuario", Usuario.class);

			return miQuery.getResultList();

		} catch (Exception e) {

			throw new DDBBException();

		}
	}

	/**
	 * Busca un usuario en la base de datos a partir del nombre de usuario y la
	 * password.
	 *
	 * @param usuario  - Nombre de usuario utilizado como criterio de busqueda
	 * @param password - Password del usuario utilizada para la autenticacion
	 * @return - el objeto Usuario si se encuentra una coincidencia exacta
	 * @throws DDBBException si ocurre un error durante la consulta a la base de
	 *                       datos
	 */
	@Override
	public Usuario buscarUsuario(String usuario, String password) {

		try {

			Session session = sessionFactory.getCurrentSession();

			String consulta = "FROM Usuario WHERE usuario = :usuario AND password = :password";

			Query<Usuario> query = session.createQuery(consulta, Usuario.class);

			query.setParameter("usuario", usuario);

			query.setParameter("password", password);

			return query.uniqueResult();

		} catch (Exception e) {

			throw new DDBBException();

		}
	}

	/**
	 * Recupera un usuario por su ID.
	 * 
	 * @param usuarioId - El identificador unico del usuario
	 * @return - El usuario correspondiente al ID, o {@code null} si no se encuentra
	 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
	 *                                   especificado
	 * @throws DDBBException             si ocurre un error relacionado con la BBDD
	 *                                   durante la operacion
	 * @throws RuntimeException          si ocurre un error inesperado no controlado
	 *                                   especificamente
	 */
	@Override
	public Usuario buscarUsuario(int usuarioId) {

		try {

			Session miSesion = sessionFactory.getCurrentSession();

			Usuario usuario = miSesion.get(Usuario.class, usuarioId);

			if (usuario == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_CARGAR);

			}

			return usuario;

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}

	}
}
