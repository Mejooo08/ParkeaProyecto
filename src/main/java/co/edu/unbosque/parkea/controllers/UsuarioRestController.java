package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Rol;
import co.edu.unbosque.parkea.model.TipoDocumento;
import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.model.dto.UsuarioDTO;
import co.edu.unbosque.parkea.service.RolServiceAPI;
import co.edu.unbosque.parkea.service.TipoDocumentoServiceAPI;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import co.edu.unbosque.parkea.service.impl.EnvioCorreoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/Usuario")
public class UsuarioRestController {

    public UsuarioRestController(){

    }
    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;

    @Autowired
    private TipoDocumentoServiceAPI tipoDocumentoServiceAPI;

    @Autowired
    private RolServiceAPI rolServiceAPI;

    @Autowired
    private EnvioCorreoImpl correoService;

    @Autowired
    private AuditoriaRestController audi;

    @GetMapping(value = "/getAll")
    public List<UsuarioDTO> getAll(){

        List<Usuario> getall = usuarioServiceAPI.getAll();
        List<UsuarioDTO> listaF = new ArrayList<>();

        for (Usuario u:getall){
            if(u.getEstado().equals("A")){
                    UsuarioDTO objeto = new UsuarioDTO(u.getIdUsuario(),u.getRol().getTipoRol() ,u.getLogin(), u.getDireccion(),u.getIdDocumento().getDescripcion(),u.getNumeroDoc(), u.getPuntosFidelizacion(),u.getTarjetaCredito(), u.getIntentos(),u.getEstado());
                    listaF.add(objeto);
                }
            }
        return listaF;
    }

    @PostMapping(value = "/saveUsuario/{idIdentificacion}/{idRol}/{idUsuario}")
    public HttpStatus save(@RequestBody Usuario usuario,
                           @PathVariable(value = "idIdentificacion") int idIdentificacion,
                           @PathVariable(value = "idRol") int idRol,
                           @PathVariable(value = "idUsuario") int idUsuario){

        TipoDocumento identificacion = tipoDocumentoServiceAPI.get(idIdentificacion);
        Rol rol = rolServiceAPI.get(idRol);
        usuario.setIdDocumento(identificacion);
        usuario.setRol(rol);
        String contra = usuario.getClave();
        usuario.setClave(usuarioServiceAPI.hashearContra(contra));
        usuario.setNumeroDoc(usuario.getNumeroDoc());
        usuario.setIntentos(0);
        usuario.setEstado("A");
        try{
            correoService.enviarCorreo(usuario.getLogin(), "Registro exitoso", "Bienvenido usuario "+usuario.getLogin()+":\nUsted ha sido registrado" +
                    ", su clave de accesso es: " +contra);
            audi.saveAuditoria("Guardar", "Usuario",idUsuario);
            usuarioServiceAPI.save(usuario);
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @PutMapping(value = "/updateUsuario/{id}/{idIdentificacion}/{idRol}/{idUsuario}")
    public HttpStatus update(@RequestBody Usuario usuario,
                                          @PathVariable(value = "id") int id,
                                          @PathVariable(value = "idIdentificacion") int idIdentificacion,
                                          @PathVariable(value = "idRol") int idRol,
                                          @PathVariable(value = "idUsuario") int idUsuario){

        Usuario objeto = usuarioServiceAPI.get(id);
        TipoDocumento identificacion = tipoDocumentoServiceAPI.get(idIdentificacion);
        Rol rol = rolServiceAPI.get(idRol);
        if (objeto != null){
            objeto.setRol(rol);
            objeto.setLogin(usuario.getLogin());
            objeto.setClave(usuarioServiceAPI.hashearContra(usuario.getClave()));
            objeto.setDireccion(usuario.getDireccion());
            objeto.setNumeroDoc(usuario.getNumeroDoc());
            objeto.setIdDocumento(identificacion);
            objeto.setIntentos(usuario.getIntentos());
            objeto.setTarjetaCredito(usuario.getTarjetaCredito());
            objeto.setPuntosFidelizacion(usuario.getPuntosFidelizacion());
            objeto.setEstado(usuario.getEstado());
            usuarioServiceAPI.save(objeto);
            audi.saveAuditoria("Actualizar", "Usuario",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @PutMapping(value = "/cambiarContrasenia/{id}/{contra}")
    public HttpStatus cambiarContra(@RequestBody Usuario usuario,
                                                 @PathVariable(value = "id") int id,
                                                 @PathVariable(value = "contra") String contra){
        usuario = usuarioServiceAPI.get(id);
        usuario.setClave(usuarioServiceAPI.hashearContra(contra));
        usuarioServiceAPI.save(usuario);
        audi.saveAuditoria("Cambio Contrasenia", "Usuario",usuario.getIdUsuario());
        correoService.enviarCorreo(usuario.getLogin(), "Cambio de contrasenia","Has cambiado la contraseña exitosamente");
        return HttpStatus.OK;
    }

    @GetMapping(value = "/deleteUsuario/{id}/{idUsuario}")
    public HttpStatus delete(@PathVariable int id, @PathVariable(value = "idUsuario") int idUsuario){
        Usuario usuario = usuarioServiceAPI.get(id);
        if (usuario != null){
            usuario.setEstado("D");
            audi.saveAuditoria("Eliminar", "Usuario",idUsuario);
            usuarioServiceAPI.save(usuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @PostMapping(value = "https://production.wompi.co/v1/tokens/cards")
    public HttpStatus pasarela(@PathVariable(value = "correo") String correo,
                               @PathVariable(value = "clave") String clave){
        Usuario u =  usuarioServiceAPI.login(correo,clave);
        correoService.enviarCorreo(u.getLogin(),"Compra Realizada", "Su compra se ha realizado" +
                "exitosamente");
        correoService.enviarCorreo("dfmejiar@unbosque.edu.edu.co","Compra Realizada", "El usuario: "+u.getIdUsuario()+" ha realizado " +
                "una compra de manera exitosa");
        return HttpStatus.OK;
    }
}