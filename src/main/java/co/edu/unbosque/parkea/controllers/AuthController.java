package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.model.dto.UsuarioDTO;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import co.edu.unbosque.parkea.service.impl.EnvioCorreoImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping(value = "/api/Autenticacion")
public class AuthController {

    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;

    @Autowired
    private EnvioCorreoImpl correoService;
    @EventListener(ApplicationReadyEvent.class)
    public void envioCorreo() {
        System.out.println("----- Iniciado");
    }

    @PostMapping(value = "/validarLogin/{correo}/{clave}")
    public UsuarioDTO login(@PathVariable(value = "correo") String correo,
                            @PathVariable(value = "clave") String clave){
       Usuario u =  usuarioServiceAPI.login(correo, clave);
       UsuarioDTO objeto = new UsuarioDTO();
       if(u != null){
           objeto = new UsuarioDTO(u.getIdUsuario(),u.getRol().getTipoRol(),u.getLogin(), u.getDireccion(),u.getIdDocumento().getDescripcion(),u.getNumeroDoc(), u.getPuntosFidelizacion(),u.getTarjetaCredito(), u.getIntentos(),u.getEstado());
       }else{
           System.out.println("Usuario Correo o contraseña mal");
           return  null;
       }
       int val = comprobacion(u);
        switch(val) {
            case 0:
                System.out.println("Usuario bloqueado");
                correoService.enviarCorreo(u.getLogin(),"Usuario Bloqueado", "Su usuario esta bloqueado," +
                        "contacte a un administrador para poder acceder a ParkeaColombia");
                correoService.enviarCorreo("dfmejiar@unbosque.edu.edu.co","Usuario Bloqueado", "El usuario: "+u.getIdUsuario()+" ha sido bloqueado por " +
                        "intentar más de 3 veces acceder a la cuenta de manera incorrecta");
                return null;
            case 1:
                return objeto;
            case 2:
                correoService.enviarCorreo(u.getLogin(),"Inicio de Sesion", "Para iniciar sesión es necesario" +
                        " que cambies la contraseña proporcionada por default");
                System.out.println("Usuario cambio contraseña necesario");
                return null;
            default:
                System.out.println("Se peto");
                return null;
        }
    }

    @PostMapping(value = "/validarLogin")
    public RedirectView loginPRUEBA(@RequestParam("email") String correo,
                                    @RequestParam("password") String clave, Model model, HttpSession session, RedirectAttributes redirectAttributes){
        Usuario u =  usuarioServiceAPI.login(correo, clave);
        UsuarioDTO objeto = new UsuarioDTO();
        if(u != null){
            objeto = new UsuarioDTO(u.getIdUsuario(),u.getRol().getTipoRol(),u.getLogin(), u.getDireccion(),u.getIdDocumento().getDescripcion(),u.getNumeroDoc(), u.getPuntosFidelizacion(),u.getTarjetaCredito(), u.getIntentos(),u.getEstado());
        }else{
            System.out.println("Usuario Correo o contraseña mal");
            model.addAttribute("error", "Credenciales inválidas");
            return new RedirectView("/pagina_principal/inicio_principal");
        }
        int val = comprobacion(u);
        String r = "";
        switch(val) {
            case 0:
                System.out.println("Usuario bloqueado");
                correoService.enviarCorreo(u.getLogin(),"Usuario Bloqueado", "Su usuario esta bloqueado," +
                        "contacte a un administrador para poder acceder a ParkeaColombia");
                correoService.enviarCorreo("dfmejiar@unbosque.edu.edu.co","Usuario Bloqueado", "El usuario: "+u.getIdUsuario()+" ha sido bloqueado por " +
                        "intentar más de 3 veces acceder a la cuenta de manera incorrecta");
                model.addAttribute("error", "Credenciales inválidas");
                return new RedirectView("/pagina_principal/inicio_principal");
            case 1:
                String tipo_u = objeto.getRol();
                if(tipo_u.equals("Cliente")){
                    session.setAttribute("username", objeto.getLogin());
                    session.setAttribute("id", objeto.getIdUsuario());
                    return new RedirectView("/usuario/inicio_usuario");
                }else{
                    session.setAttribute("username", objeto.getLogin());
                    session.setAttribute("id", objeto.getIdUsuario());
                    redirectAttributes.addAttribute("dato1", objeto.getLogin());
                    return new RedirectView("/Administrador/inicio_administrador");
                }
            case 2:
                correoService.enviarCorreo(u.getLogin(),"Inicio de Sesion", "Para iniciar sesión es necesario" +
                        " que cambies la contraseña proporcionada por default");
                model.addAttribute("error", "Credenciales inválidas");
                return new RedirectView("/pagina_principal/inicio_principal");
            default:
                System.out.println("Se peto");
                return new RedirectView(r);
        }
    }
    public int comprobacion(Usuario u){
        int valor = -1;
            if(usuarioServiceAPI.validarEstado(u) == 1){
                valor = 1;
                // "Login correcto"
            }else if(usuarioServiceAPI.validarEstado(u) == 2){
                valor = 2;
                //"Necesario cambio contraseña"
            }else{
                valor = 0;
                //"Sesion fallida"
            }
            return valor;
    }
}
