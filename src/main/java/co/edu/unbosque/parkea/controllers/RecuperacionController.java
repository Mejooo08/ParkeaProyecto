package co.edu.unbosque.parkea.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recuperar")
public class RecuperacionController {
    @GetMapping("/password")
    public String recuperar(){

        return "cambiar_contrasena";
    }
}
