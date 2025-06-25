package es.manuelcastro.controladores;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Clase de configuracion global que aplica personalizaciones a los datos
 * de formularios web en todos los controladores.
 * Elimina espacios en blanco de los campos tipo String.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-09
 */

@ControllerAdvice
public class BinderGlobal {

	/**
	 * Metodo para cortar los espacios en blancos del formulario.
	 * 
	 * @param binder - Valor a asignar
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor recortaEspacios = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, recortaEspacios);
    }
}
