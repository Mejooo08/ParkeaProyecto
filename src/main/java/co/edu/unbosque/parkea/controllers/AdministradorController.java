package co.edu.unbosque.parkea.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Administrador")
public class AdministradorController {

    @GetMapping("/inicio_administrador")
    public String administrador(){
        return "inicio_administrador";
    }
}
