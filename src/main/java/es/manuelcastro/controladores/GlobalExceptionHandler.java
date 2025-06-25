package es.manuelcastro.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import es.manuelcastro.excepciones.DDBBException;
import es.manuelcastro.excepciones.EliminacionImposibleException;
import es.manuelcastro.excepciones.ResourceNotFoundException;
import es.manuelcastro.utils.Constantes;
/**
 * Manejador global de excepciones para la aplicacion.
 * Esta clase captura y gestiona distintas excepciones lanzadas por los controladores,
 * asignando mensajes adecuados al modelo y dirigiendo a la vista de error.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-11
 * 
 */

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	 /**
     * Maneja excepciones cuando se intenta eliminar un evento que tiene entradas asociadas o
     * un usuario con entradas asociadas
     *
     * @param e - Excepcion del tipo EliminacionImposibleException que se ha lanzado
     * @param model - Objeto Model usado para pasar datos a la vista
     * @return - Nombre de la vista de error
     */
    @ExceptionHandler(EliminacionImposibleException.class)
    public String handleEventoConEntradasException(EliminacionImposibleException e, Model model) {
    	
        logger.error("Error al eliminar evento por tener entradas asociadas: ", e);
        
        model.addAttribute(Constantes.ERROR, e.getMessage());
        
        return Constantes.ERROR;
    }

	/**
     * Maneja las excepciones relacionadas con la base de datos.
     *
     * @param e - Excepcion del tipo DDBBException que se ha lanzado
     * @param model - Objeto Model usado para pasar datos a la vista
     * @return - Nombre de la vista de error
     */
    @ExceptionHandler(DDBBException.class)
    public String handleDDBBException(DDBBException e, Model model) {
    	
    	logger.error(Constantes.ERROR_CONEXION + ": ", e);
    	
        model.addAttribute(Constantes.ERROR, Constantes.ERROR_CONEXION);
        
        return Constantes.ERROR; 
    }

    /**
     * Maneja excepciones cuando no se encuentra un recurso.
     *
     * @param e - Excepcion del tipo ResourceNotFoundException que se ha lanzado
     * @param model - Objeto Model usado para pasar datos a la vista
     * @return - Nombre de la vista de error
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(ResourceNotFoundException e, Model model) {
    	
    	logger.error(Constantes.NO_RESOURCE, e);
    	
        model.addAttribute(Constantes.ERROR, e.getMessage());
        
        return Constantes.ERROR;
    }
    
    /**
     * Maneja cualquier otra excepción no controlada especificamente.
     *
     * @param e - Excepcion generica que se ha lanzado
     * @param model - Objeto Model usado para pasar datos a la vista
     * @return - Nombre de la vista de error
     */
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception e, Model model) {
    	
    	logger.error(Constantes.ERROR_INESPERADO, e);
    	
        model.addAttribute(Constantes.ERROR, Constantes.ERROR_INESPERADO + e.getMessage());
        
        return Constantes.ERROR;
    }
}
