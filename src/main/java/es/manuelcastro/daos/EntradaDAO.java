package es.manuelcastro.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.manuelcastro.entidades.Entrada;
import es.manuelcastro.excepciones.DDBBException;
import es.manuelcastro.interfaces.IEntradaDAO;

/**
 * DAO para gestionar operaciones relacionadas con entradas en la base de datos.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-16
 */

@Repository
@Transactional
public class EntradaDAO implements IEntradaDAO {

	// Fabrica de sesiones de Hibernate inyectada automaticamente por Spring
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Inserta una nueva entrada en la base de datos.
	 * 
	 * @param entrada - El objeto Entrada que se desea almacenar
	 * @throws DDBBException si ocurre un error relacionado con la BBDD durante la
	 *                       operacion
	 */
	@Override
	public void altaEntrada(Entrada entrada) {

		try {

			Session miSesion = sessionFactory.getCurrentSession();

			miSesion.save(entrada);

		} catch (Exception e) {

			throw new DDBBException();

		}
	}

	/**
	 * Obtiene el numero total de entradas compradas por un usuario para un evento
	 * especifico. Realiza una consulta a la base de datos para contar cuantas
	 * entradas existen que correspondan al ID del usuario y al ID del evento
	 * proporcionados.
	 * 
	 * @param usuarioId - ID del usuario del que se desea conocer cuantas entradas
	 *                  ha comprado
	 * @param eventoId  - ID del evento para el cual se cuentan las entradas
	 * @return - Numero total de entradas que el usuario ha comprado para ese evento
	 */
	@Override
	public int getNumEntradas(int usuarioId, int eventoId) {

		Session miSesion = sessionFactory.getCurrentSession();

		Query<Long> query = miSesion.createQuery("SELECT COUNT(e) FROM Entrada e "
				+ "WHERE e.usuario.usuarioId = :usuarioId AND e.evento.eventoId = :eventoId", Long.class);

		query.setParameter("usuarioId", usuarioId);

		query.setParameter("eventoId", eventoId);

		return query.uniqueResult().intValue();
	}

}
