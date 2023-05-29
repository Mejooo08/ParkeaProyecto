package co.edu.unbosque.parkea.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pagina_principal")
public class inicioController {

    @GetMapping("/inicio_usuario")
    public String inicio(){
        return "inicio_usuario";
    }
}
