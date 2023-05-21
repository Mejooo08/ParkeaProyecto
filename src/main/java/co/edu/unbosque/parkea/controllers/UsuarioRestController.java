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
                    UsuarioDTO objeto = new UsuarioDTO(u.getIdUsuario(),u.getRol(),u.getLogin(), u.getDireccion(),u.getIdDocumento().getDescripcion(),u.getNumeroDoc(), u.getPuntosFidelizacion(),u.getTajetaCredito(), u.getIntentos(),u.getEstado());
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
        usuario.setIntentos(0);
        usuario.setEstado("A");
        usuarioServiceAPI.save(usuario);
        audi.saveAuditoria("Guardar", "Usuario",idUsuario);
        correoService.enviarCorreo(usuario.getLogin()+"", "Registro exitoso", "Bienvenido usuario "+usuario.getLogin()+":\nUsted ha sido registrado" +
                ", su clave de accesso es: " +contra);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/saveUsuario/{idIdentificacion}")
    public HttpStatus saveLogin(@RequestBody Usuario usuario,
                           @PathVariable(value = "idIdentificacion") int idIdentificacion){

        TipoDocumento identificacion = tipoDocumentoServiceAPI.get(idIdentificacion);
        Rol rol = rolServiceAPI.get(1);
        usuario.setIdDocumento(identificacion);
        usuario.setRol(rol);
        usuario.setClave(usuarioServiceAPI.hashearContra(usuario.getClave()));
        usuario.setEstado("A");
        usuario.setIntentos(0);
        usuarioServiceAPI.save(usuario);
        correoService.enviarCorreo(usuario.getLogin()+"", "Registro exitoso", "Bienvenido usuario "+usuario.getLogin()+":\nHa quedado" +
                "registrado en nuestra sistema como un nuevo usuario, ya puede iniciar sesión");
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
                                                 @PathVariable(value = "contra") String contra){
        usuario.setClave(usuarioServiceAPI.hashearContra(contra));
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
}