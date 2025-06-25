package es.manuelcastro.controladores;

import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.manuelcastro.entidades.Entrada;
import es.manuelcastro.entidades.Evento;
import es.manuelcastro.entidades.Usuario;
import es.manuelcastro.excepciones.DDBBException;
import es.manuelcastro.interfaces.IEntradaDAO;
import es.manuelcastro.interfaces.IEventoDAO;
import es.manuelcastro.interfaces.IUsuarioDAO;
import es.manuelcastro.utils.Constantes;

/**
 * Clase Controlador. Gestiona las peticiones web relacionadas con las entradas.
 * 
 * Controlador Spring MVC que enlaza las vistas con las operaciones del DAO
 * IEntradaDAO.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-16
 */

@Controller
@RequestMapping("/entradas")
public class ControladorEntrada {

	@Autowired
	private IEntradaDAO entradaDAO;

	@Autowired
	private IEventoDAO eventoDAO;

	@Autowired
	private IUsuarioDAO usuarioDAO;

	private final ResourceBundle properties = ResourceBundle.getBundle("entrada");

	/**
	 * Muestra el formulario para visualizar cuantas entradas quedan para un evento
	 * y decidir si comprarlas.
	 * 
	 * @param eventoId  - Recoge el id del evento
	 * @param usuarioId - recoge el id del usuario
	 * @param modelo    - El modelo de la vista
	 * @return - La vista "entradas" o "error" si ocurre un problema
	 */
	@GetMapping("/alta")
	public String altaEntrada(@RequestParam("eventoId") Integer eventoId, @RequestParam("usuarioId") Integer usuarioId,
			Model modelo) {

		try {

			Evento evento = eventoDAO.getEvento(eventoId);

			Usuario usuario = usuarioDAO.buscarUsuario(usuarioId);

			if (evento == null) {

				modelo.addAttribute(Constantes.ERROR, Constantes.NO_EVENTO);

				return Constantes.ERROR;
			}
			if (usuario == null) {

				modelo.addAttribute(Constantes.ERROR, Constantes.NO_USUARIO);

				return Constantes.ERROR;
			}

			if (evento.getAsientosDisponibles() <= 0) {

				modelo.addAttribute(Constantes.ERROR, Constantes.NO_ASIENTOS);

				return Constantes.ERROR;
			}

			Entrada nuevaEntrada = new Entrada();

			nuevaEntrada.setEvento(evento);

			nuevaEntrada.setUsuario(usuario);

			modelo.addAttribute("maxEntradas", properties.getString("max.entradas"));

			nuevaEntrada.setPrecio(Double.parseDouble(properties.getString("precio.entrada")));

			modelo.addAttribute("entrada", nuevaEntrada);

			modelo.addAttribute("evento", evento);

			modelo.addAttribute("usuario", usuario);

			return Constantes.ENTRADAS_JSP;

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;

		}
	}

	/**
	 * Procesa la solicitud de compra de una entrada para un evento. Valida que el
	 * usuario este autenticado y autorizado para realizar la compra, que el evento
	 * exista y tenga asientos disponibles, y que el usuario no haya superado el
	 * limite de entradas permitidas.
	 * 
	 * @param entrada       - Objeto Entrada recibido del formulario de compra
	 * @param result        - Resultado de la validacion de la entrada
	 * @param modelo        - Modelo para pasar datos a la vista
	 * @param session       - Sesion HTTP para obtener el ID del usuario autenticado
	 * @param redirectAttrs - Atributos para redirigir mensajes entre vistas
	 * @return - Redireccion a la busqueda de eventos en caso de exito, a la vista
	 *         "login", "entradas" o "error" si ocurre algun problema
	 */
	@PostMapping("/comprarEntrada")
	public String comprarEntrada(@ModelAttribute("entrada") Entrada entrada, BindingResult result, Model modelo,
			HttpSession session, RedirectAttributes redirectAttrs) {

		Integer usuarioIdSesion = (Integer) session.getAttribute("usuarioId");

		if (usuarioIdSesion == null) {

			redirectAttrs.addFlashAttribute(Constantes.ERROR, Constantes.SESION_EXPIRADA);

			return "redirect:/usuarios/login";
		}

		Usuario usuarioAutenticado = usuarioDAO.buscarUsuario(usuarioIdSesion);

		if (usuarioAutenticado == null) {

			redirectAttrs.addFlashAttribute(Constantes.ERROR, Constantes.USUARIO_NO_BBDD);

			return "redirect:/error";
		}

		if (entrada.getUsuario() == null || usuarioAutenticado.getUsuarioId() != entrada.getUsuario().getUsuarioId()) {

			redirectAttrs.addFlashAttribute(Constantes.ERROR, Constantes.USUARIO_NO_AUTORIZADO);

			return "redirect:/error";
		}

		Evento evento = eventoDAO.getEvento(entrada.getEvento().getEventoId());

		if (evento == null) {

			redirectAttrs.addFlashAttribute(Constantes.ERROR, Constantes.NO_EVENTO);

			return "redirect:/eventos/lista";
		}

		modelo.addAttribute("evento", evento);

		modelo.addAttribute("usuario", usuarioAutenticado);

		if (result.hasErrors()) {
			return Constantes.ENTRADAS_JSP;
		}

		try {
			if (evento.getAsientosDisponibles() <= 0) {

				modelo.addAttribute(Constantes.ERROR, Constantes.NO_ASIENTOS);

				return Constantes.ENTRADAS_JSP;
			}

			entrada.setEvento(evento);

			entrada.setUsuario(usuarioAutenticado);

			entrada.setFecha(LocalDateTime.now());

			entrada.setCodigo(UUID.randomUUID().toString());

			int compradas = entradaDAO.getNumEntradas(usuarioIdSesion, entrada.getEvento().getEventoId());

			int maxEntradas = Integer.parseInt(properties.getString("max.entradas"));

			if (compradas >= maxEntradas) {

				modelo.addAttribute(Constantes.ERROR, Constantes.COMPRA_MAX_USUARIO);

				return Constantes.ENTRADAS_JSP;
			}

			entradaDAO.altaEntrada(entrada);

			evento.setAsientosDisponibles(evento.getAsientosDisponibles() - 1);

			eventoDAO.actualizarEvento(evento);

			redirectAttrs.addFlashAttribute("mensajeExito", Constantes.COMPRA_REALIZADA + entrada.getCodigo());

			return "redirect:/eventos/lista";

		} catch (DDBBException e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR + ": " + e.getMessage());

			return Constantes.ENTRADAS_JSP;

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_INESPERADO + e.getMessage());

			return Constantes.ENTRADAS_JSP;
		}

	}

}