package co.edu.unbosque.parkea.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class NuevaContra {

    @GetMapping("/cambiar_contrasena")
        public String html() {
            return "cambiar_contrasena";
        }
}