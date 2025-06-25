package es.manuelcastro.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Clase Controlador. Gestiona las peticiones web relacionadas el index y el Binder.
 * 
 * @author Manuel Castro
 * @version 1.1
 * @since 2025-06-09
 */

@Controller
@RequestMapping("/")
public class Controlador {

    /**
     * Redirige a la pagina principal.
     * 
     * @return - Envia al jsp correspondiente
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}  
