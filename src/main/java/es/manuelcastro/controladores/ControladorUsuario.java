package es.manuelcastro.controladores;

import java.util.List;

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

import es.manuelcastro.entidades.Usuario;
import es.manuelcastro.interfaces.IUsuarioDAO;
import es.manuelcastro.utils.Constantes;

import javax.validation.Valid;

/**
 * Clase Controlador. Gestiona las peticiones web relacionadas con los usuarios.
 * 
 * Controlador Spring MVC que enlaza las vistas con las operaciones del DAO
 * IUsuarioDAO.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-09
 */

@Controller
@RequestMapping("/usuarios")
public class ControladorUsuario {

	@Autowired
	private IUsuarioDAO usuarioDAO;

	/**
	 * Muestra el formulario para dar de alta un nuevo usuario. Maneja las
	 * solicitudes GET a /nuevo.
	 *
	 * @param modelo - El objeto Model para agregar atributos que se puedan pasar a
	 *               la vista
	 * @return - El nombre de la vista "nuevoUsuario" a enviar
	 */
	@GetMapping("/alta")
	public String altaUsuario(Model modelo) {

		modelo.addAttribute("usuario", new Usuario());

		return "usuarios/nuevoUsuario";
	}

	/**
	 * Procesa el envio del formulario para insertar un nuevo usuario.
	 *
	 * @param usuario - Vincula los campos del formulario al objeto Usuario
	 * @param result  - Contiene los errores de validacion generados por las
	 *                anotaciones @Valid en la entidad Usuario
	 * @param modelo  - El objeto Model para agregar atributos a la vista en caso de
	 *                no redireccion
	 * @return - El nombre de la vista a la que se debe redirigir
	 */
	@PostMapping("/insertar")
	public String insertarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,
			Model modelo) {

		try {

			if (result.hasErrors()) {

				return "usuarios/nuevoUsuario";

			} else {

				usuarioDAO.altaUsuario(usuario);

				return "redirect:/login.jsp";

			}

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_INSERTAR);

			return Constantes.ERROR;

		}
	}

	/**
	 * Metodo que recupera la informacion de un usuario basado en su id.
	 * 
	 * @param usuarioId - ID del usuario a visualizar
	 * @param modelo    - Modelo de la vista
	 * @return - Vista del "detalle" del usuario o "error" si algo falla
	 */
	@GetMapping("/detalle")
	public String detalleUsuario(@ModelAttribute("usuario") @RequestParam("usuarioId") int usuarioId, Model modelo) {

		try {

			Usuario usuario = usuarioDAO.detalleUsuario(usuarioId);

			modelo.addAttribute("usuario", usuario);

			return "usuarios/detalleUsuario";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;

		}
	}

	/**
	 * Procesa el envio al formulario para modificar el usuario.
	 *
	 * @param usuarioId - El ID del usuario a modificar
	 * @param modelo    - El modelo para pasar los datos a la vista
	 * @return - El nombre de la vista o "error" si algo falla
	 */
	@GetMapping("/modificar")
	public String modificarUsuario(@RequestParam("usuarioId") int usuarioId, Model modelo) {

		try {

			Usuario usuario = usuarioDAO.detalleUsuario(usuarioId);

			modelo.addAttribute("usuario", usuario);

			modelo.addAttribute("origen", "perfil");

			return Constantes.EDIT_USER_JSP;

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;

		}
	}

	/**
	 * Tratamos el formulario para actualizar un usuario existente en la BBDD.
	 *
	 * @param usuario            - El objeto Usuario a actualizar
	 * @param result             - El objeto BindingResult que contiene los
	 *                           resultados de la validacion
	 * @param modelo             - El modelo para pasar los datos a la vista
	 * @param redirectAttributes - Los atributos para redirigir y pasar mensajes
	 * @param session            - Gestiona la sesion del usuario
	 * @return - Redireccion al "detalleUsuario" si va bien, a "editarUsuario" con
	 *         los errores o "error" si algo falla
	 */
	@PostMapping("/actualizar")
	public String actualizaUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,
			Model modelo, RedirectAttributes redirectAttributes, HttpSession session) {

		Integer usuarioId = (Integer) session.getAttribute("usuarioId");

		try {

			if (result.hasErrors()) {

				modelo.addAttribute("origen", "perfil");

				return Constantes.EDIT_USER_JSP;

			} else {

				usuarioDAO.actualizaUsuario(usuario);

				return "redirect:/usuarios/detalle?usuarioId=" + usuarioId;

			}

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_ACTUALIZAR);

			return Constantes.ERROR;
		}
	}

	/**
	 * Eliminamos un usuario por su ID.
	 *
	 * @param usuarioId - El ID del usuario a eliminar
	 * @param modelo    - El modelo para pasar los datos a la vista
	 * @return - Redireccion al login o a "error"
	 */
	@GetMapping("/eliminar")
	public String eliminarUsuario(@RequestParam("usuarioId") int usuarioId, Model modelo) {

		try {

			usuarioDAO.eliminaUsuario(usuarioId);

			return "redirect:/login.jsp";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_ELIMINAR);

			return Constantes.ERROR;

		}
	}

	/**
	 * Lista de usuarios para el admin.
	 *
	 * @param modelo - El modelo para pasar los datos a la vista
	 * @return - Redireccion al login o a "error"
	 */
	@GetMapping("/lista")
	public String getUsuarios(Model modelo) {

		try {

			List<Usuario> usuarios = usuarioDAO.getUsuarios();

			modelo.addAttribute("usuarios", usuarios);

			return "usuarios/usuarios";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;

		}
	}

	/**
	 * Procesa el envio al formulario para modificar el usuario.
	 *
	 * @param usuarioId - El ID del usuario a modificar
	 * @param modelo    - El modelo para pasar los datos a la vista
	 * @return - El nombre de la vista a la que se envia
	 */
	@GetMapping("/modificarAdmin")
	public String modificarUsuarioAdmin(@RequestParam("usuarioId") int usuarioId, Model modelo) {

		Usuario usuario = usuarioDAO.detalleUsuario(usuarioId);

		modelo.addAttribute("usuario", usuario);

		modelo.addAttribute("origen", "admin");

		return Constantes.EDIT_USER_JSP;

	}

	/**
	 * Tratamos el formulario para actualizar un usuario existente en la BBDD.
	 *
	 * @param usuario            - El objeto Usuario a actualizar
	 * @param result             - El objeto BindingResult que contiene los
	 *                           resultados de la validacion
	 * @param modelo             - El modelo para pasar los datos a la vista
	 * @param redirectAttributes - Los atributos para redirigir y pasar mensajes
	 * @return - Redireccion a "listaUsuarios" si va bien o a "editarUsuario" si va
	 *         mal
	 */
	@PostMapping("/actualizarAdmin")
	public String actualizaUsuarioAdmin(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,
			Model modelo, RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {

			modelo.addAttribute("origen", "admin");

			return Constantes.EDIT_USER_JSP;

		}

		usuarioDAO.actualizaUsuario(usuario);

		return "redirect:/usuarios/lista";
	}

	/**
	 * Eliminamos un usuario por su ID.
	 *
	 * @param usuarioId - El ID del usuario a eliminar
	 * @param modelo    - El modelo para pasar los datos a la vista
	 * @return - Redireccion a la lista de usuarios
	 */
	@GetMapping("/eliminarAdmin")
	public String eliminarUsuarioAdmin(@RequestParam("usuarioId") int usuarioId, Model modelo) {

		usuarioDAO.eliminaUsuario(usuarioId);

		return "redirect:/usuarios/lista";
	}

	/**
	 * Procesa el login.
	 * 
	 * @param usuario  - Valor asignado por la request
	 * @param password - Valor asignado por la request
	 * @param modelo   - Establece el atributo al modelo
	 * @param session  - Gestiona la sesion del usuario
	 * @return - Envia al jsp correspondiente
	 */
	@PostMapping("/login")
	public String buscarUsuario(@RequestParam String usuario, @RequestParam String password, Model modelo,
			HttpSession session) {

		try {

			if (usuario.isBlank() || password.isBlank()) {
				modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CAMPOS_LOGIN);
				return "forward:/login.jsp";
			}

			Usuario usuarioRegistrado = usuarioDAO.buscarUsuario(usuario, password);

			if (usuarioRegistrado != null) {

				session.setAttribute("usuarioId", usuarioRegistrado.getUsuarioId());

				session.setAttribute("nombre", usuarioRegistrado.getNombre());

				session.setAttribute("admin", usuarioRegistrado.isAdmin());

				return "redirect:/index";
			}

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_WRONG_USER);

			return "forward:/login.jsp";

		} catch (Exception e) {
			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_LOGIN);

			return Constantes.ERROR;

		}
	}

	/**
	 * Procesa el logout.
	 * 
	 * @param session - Gestiona la sesion del usuario
	 * @return - Envia al jsp correspondiente
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login.jsp";
	}

}
