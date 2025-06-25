package es.manuelcastro.controladores;

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

import es.manuelcastro.entidades.Componente;
import es.manuelcastro.interfaces.IComponenteDAO;
import es.manuelcastro.utils.Constantes;

/**
 * Clase ControladorComponente.
 * 
 * Controlador encargado de gestionar las operaciones relacionadas con los
 * componentes, incluyendo creacion, modificacion, eliminacion y visualizacion.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-16
 */

@Controller
@RequestMapping("/componentes")
public class ControladorComponente {

	@Autowired
	private IComponenteDAO ComponenteDAO;

	/**
	 * Muestra el formulario para insertar un nuevo componente.
	 * 
	 * @param grupoId - Recoge el id del grupo
	 * @param modelo - El modelo de la vista
	 * @return - La vista "nuevoComponente" o "error" si ocurre un problema
	 */
	@RequestMapping("/alta")
	public String altaComponente(@RequestParam("grupoId") int grupoId, Model modelo) {

		try {

			modelo.addAttribute("componente", new Componente(grupoId));

			return "componentes/nuevoComponente";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;

		}
	}

	/**
	 * Procesa la insercion de un nuevo componente.
	 * 
	 * @param componente    - El componente a insertar
	 * @param bindingResult - Resultado de la validacion
	 * @param modelo        - El modelo de la vista
	 * @return - Redireccion al detalle del grupo o "error" si ocurre un problema
	 */
	@PostMapping("/insertar")
	public String insertaComponente(@Valid @ModelAttribute("componente") Componente componente,
			BindingResult bindingResult, Model modelo) {

		try {

			if (bindingResult.hasErrors()) {
				return "componentes/nuevoComponente";
			}

			ComponenteDAO.altaComponente(componente);

			return "redirect:/grupos/detalle?grupoId=" + componente.getGrupo().getGrupoId();

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_INSERTAR);

			return Constantes.ERROR;
		}
	}

	/**
	 * Muestra el formulario de actualizacion de un componente.
	 * 
	 * @param componenteId - ID del componente a modificar
	 * @param modelo   - El modelo de la vista
	 * @return - La vista "editarComponente" o "error" si ocurre un problema
	 */
	@GetMapping("/modificar")
	public String modificarComponente(@RequestParam("componenteId") int componenteId, Model modelo) {


		try {

			Componente componenteSeleccionado = ComponenteDAO.getComponente(componenteId);

			modelo.addAttribute("componente", componenteSeleccionado);

			return "componentes/editarComponente";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return "grupos/detalle";
		}
	}

	/**
	 * Procesa la actualizacion de un componente.
	 * 
	 * @param componente - El componente con los datos actualizados
	 * @param bindingResult          - Resultado de la validacion
	 * @param modelo                 - El modelo de la vista
	 * @return - Redireccion al jsp correspondiente
	 */
	@PostMapping("/actualizar")
	public String actualizarComponente(@Valid @ModelAttribute("componente") Componente componente,
			BindingResult bindingResult, Model modelo) {

		if (bindingResult.hasErrors()) {
			return "componentes/editarComponente";
		}

		ComponenteDAO.actualizarComponente(componente);

		return "redirect:/grupos/detalle?grupoId=" + componente.getGrupo().getGrupoId();
	}

	/**
	 * Elimina un ccomponente por su ID.
	 * 
	 * @param componenteId - ID del componente a eliminar
	 * @param modelo   - El modelo de la vista
	 * @return - Redireccion al jsp correspondiente
	 */
	@GetMapping("/eliminar")
	public String eliminarComponente(@RequestParam("componenteId") int componenteId, Model modelo) {

		int grupoId = ComponenteDAO.getGrupoId(componenteId);

		ComponenteDAO.eliminaComponente(componenteId);

		return "redirect:/grupos/detalle?grupoId=" + grupoId;

	}

}
