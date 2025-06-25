package es.manuelcastro.controladores;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.manuelcastro.entidades.Evento;
import es.manuelcastro.entidades.Grupo;
import es.manuelcastro.interfaces.IEventoDAO;
import es.manuelcastro.interfaces.IGrupoDAO;
import es.manuelcastro.utils.Constantes;

/**
 * Clase Controlador. Gestiona las peticiones web relacionadas con los eventos.
 * 
 * Controlador Spring MVC que enlaza las vistas con las operaciones del DAO
 * IEventoDAO.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-09
 */

@Controller
@RequestMapping("/eventos")
public class ControladorEvento {

	@Autowired
	private IEventoDAO eventoDAO;
	@Autowired
	private IGrupoDAO grupoDAO;

	/**
	 * Muestra el formulario de busqueda de eventos.
	 * 
	 * @param modelo - El modelo de la vista
	 * @return - La vista "buscarEventos" o "index" si falla
	 */
	@RequestMapping("/buscar")
	public String buscarEventos(Model modelo) {

		try {

			return "eventos/buscarEventos";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return "index";
		}
	}

	/**
	 * Muestra la lista de eventos, opcionalmente filtrados.
	 * 
	 * @param filtro - Filtro para buscar por nombre o descripcion
	 * @param modelo - El modelo de la vista
	 * @return - La vista "eventos" o "error" si ocurre un problema
	 */
	@RequestMapping("/lista")
	public String listaEventos(@RequestParam(name = "filtro", defaultValue = "") String filtro, Model modelo) {

		try {

			List<Evento> eventos;

			if (filtro == null || filtro.trim().isEmpty()) {

				eventos = eventoDAO.getEventos();

			} else {

				eventos = eventoDAO.getEventosFiltrados(filtro);

			}

			modelo.addAttribute("eventos", eventos);

			return "eventos/eventos";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;
		}
	}

	/**
	 * Muestra los detalles de un evento seleccionado.
	 * 
	 * @param eventoId - ID del evento a visualizar
	 * @param modelo   - El modelo de la vista
	 * @return - la vista "detalleEvento" o "error" si ocurre un problema
	 */
	@RequestMapping("/detalle")
	public String detalleEvento(@RequestParam("eventoId") int eventoId, Model modelo) {

		try {

			Evento evento = eventoDAO.getEvento(eventoId);

			modelo.addAttribute("evento", evento);

			return "eventos/detalleEvento";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_NOT_FOUND);

			return Constantes.ERROR;
		}
	}

	/**
	 * Muestra el formulario para insertar un nuevo evento.
	 * 
	 * @param modelo - El modelo de la vista
	 * @return - La vista "nuevoEvento" o "error" si ocurre un problema
	 */
	@RequestMapping("/alta")
	public String altaEvento(Model modelo) {

		try {

			modelo.addAttribute("evento", new Evento());

			return "eventos/nuevoEvento";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;

		}
	}

	/**
	 * Procesa la insercion de un nuevo evento.
	 * 
	 * @param evento        - El evento a insertar
	 * @param bindingResult - Resultado de la validacion
	 * @param modelo        - El modelo de la vista
	 * @return - Redireccion a la lista de eventos, a "nuevoEvento" con los errores
	 *         si ocurre un problema o a "error"
	 */
	@PostMapping("/insertar")
	public String insertaEvento(@Valid @ModelAttribute("evento") Evento evento, BindingResult bindingResult,
			Model modelo) {

		try {
			
			try {
				
			    Grupo grupo = grupoDAO.getGrupo(evento.getGrupoId());
			    
			} catch (Exception e) {
				
			    bindingResult.rejectValue("grupoId", "error.evento", "El grupo especificado no existe");
			}
			if (bindingResult.hasErrors()) {
				
				return "eventos/nuevoEvento";
			}

			eventoDAO.altaEvento(evento);

			return "redirect:/eventos/lista";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_INSERTAR);

			return Constantes.ERROR;
		}
	}

	/**
	 * Muestra el formulario de actualizacion de un evento.
	 * 
	 * @param eventoId - ID del evento a modificar
	 * @param modelo   - El modelo de la vista
	 * @return - La vista "actualizarEvento" o a la lista con los errores si ocurre
	 *         un problema
	 */
	@GetMapping("/modificar")
	public String modificarEvento(@RequestParam("eventoId") int eventoId, Model modelo) {

		try {

			Evento eventoSeleccionado = eventoDAO.getEvento(eventoId);

			modelo.addAttribute("evento", eventoSeleccionado);

			return "eventos/actualizarEvento";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return "eventos/lista";
		}
	}

	/**
	 * Procesa la actualizacion de un evento.
	 * 
	 * @param evento        - El evento con los datos actualizados
	 * @param bindingResult - Resultado de la validacion
	 * @param modelo        - El modelo de la vista
	 * @return redireccion a la lista de eventos o a "actualizarEvento" con los
	 *         errores si ocurre un problema
	 */
	@PostMapping("/actualizar")
	public String actualizarEvento(@Valid @ModelAttribute("evento") Evento evento, BindingResult bindingResult,
			Model modelo) {

		if (bindingResult.hasErrors()) {
			
			return "eventos/actualizarEvento";
		}

		eventoDAO.actualizarEvento(evento);

		return "redirect:/eventos/lista";
	}

	/**
	 * Elimina un evento por su ID.
	 * 
	 * @param eventoId - ID del evento a eliminar
	 * @param modelo   - El modelo de la vista
	 * @return - Redireccion a la lista de eventos
	 */
	@GetMapping("/eliminar")
	public String eliminarEvento(@RequestParam("eventoId") int eventoId, Model modelo) {

		eventoDAO.eliminaEvento(eventoId);

		return "redirect:/eventos/lista";

	}
}