package co.edu.unbosque.parkea.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    /**
     * Este método se usa para darle inicio a un usuario
     * @return
     */
    @GetMapping("/inicio_usuario")
    public String inicio(Model model, @RequestParam("correo") String correo, @RequestParam("id") String id){
        model.addAttribute("correo", correo);
        model.addAttribute("id", id);
        return "inicio_usuario";
    }
}
