package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.model.dto.UsuarioDTO;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/Autenticacion")
public class AuthController {

    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;

    @EventListener(ApplicationReadyEvent.class)
    public void envioCorreo() {
        System.out.println("----- Iniciado");
    }

    @GetMapping(value = "/validarLogin/{correo}/{clave}")
    public UsuarioDTO login(@PathVariable(value = "correo") String correo,
                            @PathVariable(value = "clave") String clave){
       Usuario u =  usuarioServiceAPI.login(correo, clave);
       UsuarioDTO objeto = new UsuarioDTO();
       if(u != null){
           objeto = new UsuarioDTO(u.getIdUsuario(),u.getRol(),u.getLogin(), u.getDireccion(),u.getIdDocumento().getDescripcion(),u.getNumeroDoc(), u.getPuntosFidelizacion(),u.getTajetaCredito(), u.getIntentos(),u.getEstado());
       }
       int val = comprobacion(u);
        switch(val) {
            case 0:
                System.out.println("Usuario bloqueado");
                return null;
            case 1:
                System.out.println("Sesion iniciada con exito");
                return objeto;
            case 2:
                System.out.println("La contrasenia debe ser cambiada");
                return objeto;
            case 3:
                System.out.println("Login fallido");
                return null;
            default:
                System.out.println("Se peto");
                return null;
        }
    }
    public int comprobacion(Usuario u){
        if(u != null){
            if(!usuarioServiceAPI.validarEstado(u)){
                return 0;
                // "Estado inactivo"
            }else{
                return 1;
                //"Sesión iniciada con exito"
            }
        }else{
            return 3;
            //"Login fallido"
        }
    }
}
