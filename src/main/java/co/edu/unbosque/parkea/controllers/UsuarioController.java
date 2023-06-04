package co.edu.unbosque.parkea.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    /**
     * Este método se usa para darle inicio a un usuario
     * @return
     */
    @GetMapping("/inicio_usuario")
    public String inicio(){

        return "inicio_usuario";
    }
}
