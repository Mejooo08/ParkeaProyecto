package co.edu.unbosque.parkea.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Administrador")
public class AdministradorController {

    @GetMapping("/inicio_administrador")
    public String administrador(Model model, @RequestParam("correo") String correo, @RequestParam("id") String id){
        model.addAttribute("correo", correo);
        model.addAttribute("id", id);
        return "inicio_administrador";
    }
}
