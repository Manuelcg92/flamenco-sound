package es.manuelcastro.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.manuelcastro.entidades.Grupo;
import es.manuelcastro.interfaces.IGrupoDAO;
import es.manuelcastro.utils.Constantes;

import javax.validation.Valid;

/**
 * Clase Controlador. Gestiona las peticiones web relacionadas con los grupos.
 * 
 * Controlador Spring MVC que enlaza las vistas con las operaciones del DAO
 * IGrupoDAO.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-09
 */

@Controller
@RequestMapping("/grupos")
public class ControladorGrupo {

	@Autowired
	private IGrupoDAO grupoDAO;

	/**
	 * Metodo que guarda y muestra una lista con todos los grupos musicales de la
	 * BBDD.
	 * 
	 * @param modelo - Establece el atributo al modelo
	 * @return - Envia al jsp correspondiente
	 */
	@GetMapping("/lista")
	public String obtenerGrupos(Model modelo) {

		try {

			List<Grupo> grupos = grupoDAO.getGrupos();

			modelo.addAttribute("grupos", grupos);

			return "grupos/gruposMusicales";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;
		}
	}

	/**
	 * Metodo que crea un grupo vacio y envia a la vista para agregar los datos del
	 * nuevo grupo
	 * 
	 * @param modelo - Establece el atributo al modelo
	 * @return - Envia al jsp correspondiente
	 */
	@RequestMapping("/alta")
	public String altaGrupo(Model modelo) {

		try {

			Grupo grupo = new Grupo();

			modelo.addAttribute("grupo", grupo);

			return "grupos/nuevoGrupo";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;

		}
	}

	/**
	 * Metodo que se encarga de insertar un grupo en la BBDD.
	 * 
	 * @param grupo     - Valor a asignar
	 * @param resultado - Recoge la validacion segun las anotaciones de los
	 *                  atributos
	 * @param modelo    - Establece el atributo al modelo
	 * @return - Envia al jsp correspondiente
	 */
	@PostMapping("/insertar")
	public String insertarGrupo(@Valid @ModelAttribute("grupo") Grupo grupo, BindingResult resultado, Model modelo) {

		try {

			if (resultado.hasErrors()) {

				return "grupos/nuevoGrupo";

			} else {

				grupoDAO.altaGrupo(grupo);

				return "redirect:/grupos/lista";

			}

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_INSERTAR);

			return Constantes.ERROR;
		}

	}

	/**
	 * Metodo que recoge los datos del grupo en la BBDD segun el id y envia a la
	 * vista para modificar el grupo.
	 * 
	 * @param grupoId - Recoge el id del grupo
	 * @param modelo  - Establece el atributo al modelo
	 * @return - Envia al jsp correspondiente
	 */
	@GetMapping("/modificar")
	public String modificarGrupo(@RequestParam("grupoId") int grupoId, Model modelo) {

		try {

			Grupo grupo = grupoDAO.getGrupo(grupoId);

			modelo.addAttribute("grupo", grupo);

			return "grupos/editarGrupo";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;
		}
	}

	/**
	 * Metodo que se encarga de actualizar un grupo en la BBDD.
	 * 
	 * @param grupo     - Valor a asignar
	 * @param resultado - Recoge la validacion segun las anotaciones de los
	 *                  atributos
	 * @param modelo    - Establece el atributo al modelo
	 * @return - Envia al jsp correspondiente
	 */
	@PostMapping("/actualizar")
	public String actualizarGrupo(@Valid @ModelAttribute Grupo grupo, BindingResult resultado, Model modelo) {

		if (resultado.hasErrors()) {

			return "grupos/editarGrupo";
		}

		grupoDAO.actualizaGrupo(grupo);

		return "redirect:/grupos/lista";
	}

	/**
	 * Metodo que recupera la informacion de un grupo basado en su id.
	 * 
	 * @param grupoId - Recoge el id del grupo
	 * @param modelo  - Establece el atributo al modelo
	 * @return - Envia al jsp correspondiente
	 */
	@RequestMapping("/detalle")
	public String detalleGrupo(@RequestParam("grupoId") int grupoId, Model modelo) {

		try {

			Grupo grupo = grupoDAO.getGrupo(grupoId);

			modelo.addAttribute("detalleGrupo", grupo);

			return "grupos/detalleGrupo";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;
		}

	}

	/**
	 * Metodo que se encarga de eliminar un grupo en la BBDD.
	 * 
	 * @param grupoId - Recoge el id del grupo
	 * @param modelo  - Establece el atributo al modelo
	 * @return - Envia al jsp correspondiente
	 */
	@GetMapping("/eliminar")
	public String eliminarGrupo(@RequestParam("grupoId") int grupoId, Model modelo) {

		grupoDAO.eliminaGrupo(grupoId);

		return "redirect:/grupos/lista";

	}

}
