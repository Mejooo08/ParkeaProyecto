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

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.*;
import java.io.FileNotFoundException;

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
            if(u.getEstado().equals("A") || u.getEstado().equals("N")){
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
        String contra = usuarioServiceAPI.generarContrasena(8);
        System.out.println("Contrasena:" + contra);
        usuario.setClave(usuarioServiceAPI.hashearContra(contra));
        usuario.setNumeroDoc(usuario.getNumeroDoc());
        usuario.setIntentos(0);
        usuario.setEstado("N");
        try{
            correoService.enviarCorreo(usuario.getLogin(), "Registro exitoso", "Bienvenido usuario "+usuario.getLogin()+":\nUsted ha sido registrado" +
                    ", su clave de accesso es: " +contra);
            usuarioServiceAPI.save(usuario);
            audi.saveAuditoria("Guardar", "Usuario",idUsuario);
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @PostMapping(value = "/saveUsuario")
    public HttpStatus save2(@RequestParam("correo") String correo,
                            @RequestParam("direccion") String direccion,
                            @RequestParam("tarjeta") String tarjeta,
                            @RequestParam("cedula") int tipoDoc,
                            @RequestParam("documento") String numDoc){
        Usuario usuario = new Usuario();
        TipoDocumento tipoDocumento = tipoDocumentoServiceAPI.get(tipoDoc);
        usuario.setIdDocumento(tipoDocumento);
        usuario.setNumeroDoc(numDoc);
        usuario.setLogin(correo);
        usuario.setDireccion(direccion);
        usuario.setTarjetaCredito(tarjeta);
        Rol rol = rolServiceAPI.get(2);
        usuario.setRol(rol);
        String contra = usuarioServiceAPI.generarContrasena(8);
        System.out.println("Contrasena:" + contra);
        usuario.setClave(usuarioServiceAPI.hashearContra(contra));
        usuario.setNumeroDoc(usuario.getNumeroDoc());
        usuario.setIntentos(0);
        usuario.setEstado("N");
        try{
            correoService.enviarCorreo(usuario.getLogin(), "Registro exitoso", "Bienvenido usuario "+usuario.getLogin()+":\nUsted ha sido registrado" +
                    ", su clave de accesso es: " +contra);
            usuarioServiceAPI.save(usuario);
            audi.saveAuditoria("Guardar", "Usuario",usuario.getIdUsuario());
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

    @PutMapping(value = "/cambiarContrasenia/{correo}/{contraVieja}/{contraNueva}")
    public HttpStatus cambiarContra(
                                    @PathVariable(value = "correo") String correo,
                                    @PathVariable(value = "contraVieja") String contraVieja,
                                    @PathVariable(value = "contraNueva") String contraNueva){
        Usuario u = usuarioServiceAPI.login(correo, contraVieja);
        if(u != null){
            int id = u.getIdUsuario();
            Usuario usuario = usuarioServiceAPI.get(id);
            usuario.setClave(usuarioServiceAPI.hashearContra(contraNueva));
            usuarioServiceAPI.save(usuario);
            audi.saveAuditoria("Cambio Contrasenia", "Usuario",usuario.getIdUsuario());
            correoService.enviarCorreo(usuario.getLogin(), "Cambio de contrasenia","Has cambiado la contraseña exitosamente");
            if(usuario.getEstado().equals("N")){
                usuario.setEstado("A");
                usuarioServiceAPI.save(usuario);
            }
            return HttpStatus.OK;
        }else{
            return  HttpStatus.INTERNAL_SERVER_ERROR;
        }
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




    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("usuarioReport", "usuarioReport.pdf");
        return ResponseEntity.ok().headers(headers).body(usuarioServiceAPI.exportPdf());
    }

    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("usuarioReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(usuarioServiceAPI.exportXls());
    }
}