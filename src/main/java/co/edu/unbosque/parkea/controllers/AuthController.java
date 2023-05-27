package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.model.dto.UsuarioDTO;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import co.edu.unbosque.parkea.service.impl.EnvioCorreoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


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
    public String login(@PathVariable(value = "correo") String correo,
                            @PathVariable(value = "clave") String clave){
       Usuario u =  usuarioServiceAPI.login(correo, clave);
       UsuarioDTO objeto = new UsuarioDTO();
       if(u != null){
           objeto = new UsuarioDTO(u.getIdUsuario(),u.getRol().getTipoRol(),u.getLogin(), u.getDireccion(),u.getIdDocumento().getDescripcion(),u.getNumeroDoc(), u.getPuntosFidelizacion(),u.getTarjetaCredito(), u.getIntentos(),u.getEstado());
       }else{
           return  "Usuario No valido o contrasena invalida";
       }
       int val = comprobacion(u);
        switch(val) {
            case 0:
                System.out.println("Usuario bloqueado");
                correoService.enviarCorreo(u.getLogin(),"Usuario Bloqueado", "Su usuario esta bloqueado," +
                        "contacte a un administrador para poder acceder a ParkeaColombia");
                correoService.enviarCorreo("dfmejiar@unbosque.edu.edu.co","Usuario Bloqueado", "El usuario: "+u.getIdUsuario()+" ha sido bloqueado por " +
                        "intentar más de 3 veces acceder a la cuenta de manera incorrecta");
                return "Usuario Bloqueado";
            case 1:
                return objeto.toString();
            case 2:
                correoService.enviarCorreo(u.getLogin(),"Inicio de Sesion", "Para iniciar sesión es necesario" +
                        " que cambies la contraseña proporcionada por default");
                return "Necesario Cambio Contrasena";
            default:
                System.out.println("Se peto");
                return null;
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
