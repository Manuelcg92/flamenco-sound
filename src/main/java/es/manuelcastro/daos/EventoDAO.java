package es.manuelcastro.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.manuelcastro.entidades.Evento;
import es.manuelcastro.excepciones.DDBBException;
import es.manuelcastro.excepciones.EliminacionImposibleException;
import es.manuelcastro.excepciones.ResourceNotFoundException;
import es.manuelcastro.interfaces.IEventoDAO;
import es.manuelcastro.utils.Constantes;

/**
 * Clase EventoDAO. Implementa la interfaz IEventoDAO y proporciona operaciones
 * CRUD para la entidad Evento utilizando Hibernate.
 * 
 * Esta clase actua como un componente DAO (Data Access Object) y esta anotada
 * con {@code @Repository} para que Spring la gestione como un bean de
 * persistencia.
 * 
 * @author Manuel Castro
 * @version 1.0
 * @since 2025-06-02
 */

@Repository
@Transactional
public class EventoDAO implements IEventoDAO {

	// Fabrica de sesiones de Hibernate inyectada automaticamente por Spring
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Obtiene la lista completa de eventos almacenados en la base de datos.
	 * 
	 * @return - Una lista de objetos Evento
	 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
	 *                                   especificado
	 * @throws DDBBException             si ocurre un error relacionado con la BBDD
	 *                                   durante la operacion
	 * @throws RuntimeException          si ocurre un error inesperado no controlado
	 *                                   especificamente
	 */
	@Override
	public List<Evento> getEventos() {

		try {

			Session miSesion = sessionFactory.getCurrentSession();

			Query<Evento> miQuery = miSesion.createQuery("from Evento", Evento.class);

			return miQuery.getResultList();

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}
	}

	/**
	 * Recupera una lista de eventos filtrados por nombre o descripcion.
	 * 
	 * @param filtro - Cadena a buscar en el nombre o descripcion del evento
	 * @return - Una lista de eventos que coincidan con el filtro
	 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
	 *                                   especificado
	 * @throws DDBBException             si ocurre un error relacionado con la BBDD
	 *                                   durante la operacion
	 * @throws RuntimeException          si ocurre un error inesperado no controlado
	 *                                   especificamente
	 */
	@Override
	public List<Evento> getEventosFiltrados(String filtro) {

		try {

			Session miSesion = sessionFactory.getCurrentSession();

			Query<Evento> miQuery = miSesion.createQuery(
					"FROM Evento e WHERE e.nombre LIKE :filtro OR e.descripcion LIKE :filtro", Evento.class);

			miQuery.setParameter("filtro", "%" + filtro + "%");

			return miQuery.getResultList();

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}
	}

	/**
	 * Recupera un evento por su ID.
	 * 
	 * @param eventoId - el identificador unico del evento
	 * @return - El evento correspondiente al ID, o {@code null} si no se encuentra
	 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
	 *                                   especificado
	 * @throws DDBBException             si ocurre un error relacionado con la BBDD
	 *                                   durante la operacion
	 * @throws RuntimeException          si ocurre un error inesperado no controlado
	 *                                   especificamente
	 */
	@Override
	public Evento getEvento(int eventoId) {

		try {

			Session miSesion = sessionFactory.getCurrentSession();

			Evento evento = miSesion.get(Evento.class, eventoId);

			if (evento == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_CARGAR);

			}

			return evento;

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}

	}

	/**
	 * Inserta un nuevo evento en la base de datos.
	 * 
	 * @param evento - El objeto Evento que se desea almacenar
	 * @throws DDBBException si ocurre un error relacionado con la BBDD durante la
	 *                       operacion
	 */
	@Override
	public void altaEvento(Evento evento) {

		try {

			Session miSesion = sessionFactory.getCurrentSession();

			miSesion.save(evento);

		} catch (Exception e) {

			throw new DDBBException();

		}
	}

	/**
	 * Actualiza los datos de un evento existente en la base de datos.
	 * 
	 * @param evento - El objeto Evento con los datos actualizados
	 * @throws ResourceNotFoundException si no se encuentra el usuario con el ID
	 *                                   especificado
	 * @throws DDBBException             si ocurre un error relacionado con la BBDD
	 *                                   durante la operacion
	 * @throws RuntimeException          si ocurre un error inesperado no controlado
	 *                                   especificamente
	 */
	@Override
	public void actualizarEvento(Evento evento) {

		try {

			Session miSesion = sessionFactory.getCurrentSession();

			Evento eventoExiste = miSesion.get(Evento.class, evento.getEventoId());

			if (eventoExiste == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_ACTUALIZAR);

			}

			miSesion.merge(evento);

		} catch (HibernateException e) {

			throw new DDBBException(Constantes.ERROR_CONEXION + ": " + e.getMessage());

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			throw new RuntimeException(Constantes.ERROR_INESPERADO + e.getMessage(), e);

		}
	}

	/**
	 * Elimina un evento de la base de datos por su ID.
	 * 
	 * @param eventoId - El identificador del evento que se desea eliminar
	 * @throws ResourceNotFoundException     si no se encuentra el usuario con el ID
	 *                                       especificado
	 * @throws EliminacionImposibleException si el evento ya tiene entradas vendidas
	 * @throws DDBBException                 si ocurre un error relacionado con la
	 *                                       BBDD durante la operacion
	 * @throws RuntimeException              si ocurre un error inesperado no
	 *                                       controlado especificamente
	 */
	@Override
	public void eliminaEvento(int eventoId) {

		try {

			Session miSesion = sessionFactory.getCurrentSession();

			Evento eventoExiste = miSesion.get(Evento.class, eventoId);

			if (eventoExiste == null) {

				throw new ResourceNotFoundException(Constantes.ERROR_ELIMINAR);

			}

			Query<Long> query = miSesion
					.createQuery("SELECT COUNT(e) FROM Entrada e " + "WHERE e.evento.eventoId = :eventoId", Long.class);

			query.setParameter("eventoId", eventoId);

			Long numEntradas = query.uniqueResult();

			if (numEntradas > 0) {

				throw new EliminacionImposibleException(Constantes.ERROR_EVENT_EXIST);
			}

			miSesion.delete(eventoExiste);

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
}
