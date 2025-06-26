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

import es.manuelcastro.entidades.Grupo;
import es.manuelcastro.entidades.Producto;
import es.manuelcastro.interfaces.IGrupoDAO;
import es.manuelcastro.interfaces.IProductoDAO;
import es.manuelcastro.utils.Constantes;

@Controller
@RequestMapping("/productos")
public class ControladorProducto {
	
	@Autowired
	private IProductoDAO productoDAO;
	
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
	public String detalleGrupo(@RequestParam("grupoId") int grupoId, Model modelo) {

		try {

			Grupo grupo = grupoDAO.getGrupo(grupoId);

			modelo.addAttribute("detallesGrupo", grupo);

			return "productos/productos";

		} catch (Exception e) {

			modelo.addAttribute(Constantes.ERROR, Constantes.ERROR_CARGAR);

			return Constantes.ERROR;
		}

	}
	
	/**
	 * Muestra el formulario para insertar un nuevo componente.
	 * 
	 * @param grupoId - Recoge el id del grupo
	 * @param modelo - El modelo de la vista
	 * @return - La vista "nuevoComponente" o "error" si ocurre un problema
	 */
	@RequestMapping("/alta")
	public String altaProducto(@RequestParam("grupoId") int grupoId, Model modelo) {

		try {

			modelo.addAttribute("producto", new Producto(grupoId));

			return "producto/nuevoProducto";

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
	public String insertaProducto(@Valid @ModelAttribute("producto") Producto producto,
			BindingResult bindingResult, Model modelo) {

		try {

			if (bindingResult.hasErrors()) {
				return "producto/nuevoProducto";
			}

			productoDAO.altaProducto(producto);

			return "redirect:/grupos/detalle?grupoId=" + producto.getGrupo().getGrupoId();

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
	public String modificarProducto(@RequestParam("productoId") int productoId, Model modelo) {


		try {

			Producto productoSeleccionado = productoDAO.getProducto(productoId);

			modelo.addAttribute("producto", productoSeleccionado);

			return "producto/editarProducto";

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
	public String actualizarProducto(@Valid @ModelAttribute("producto") Producto producto,
			BindingResult bindingResult, Model modelo) {

		if (bindingResult.hasErrors()) {
			return "producto/editarProducto";
		}

		productoDAO.actualizarProducto(producto);

		return "redirect:/grupos/detalle?grupoId=" + producto.getGrupo().getGrupoId();
	}

	/**
	 * Elimina un ccomponente por su ID.
	 * 
	 * @param componenteId - ID del componente a eliminar
	 * @param modelo   - El modelo de la vista
	 * @return - Redireccion al jsp correspondiente
	 */
	@GetMapping("/eliminar")
	public String eliminarProducto(@RequestParam("productoId") int productoId, Model modelo) {

		int grupoId = productoDAO.getGrupoId(productoId);

		productoDAO.eliminaProducto(productoId);

		return "redirect:/grupos/detalle?grupoId=" + grupoId;

	}

}

