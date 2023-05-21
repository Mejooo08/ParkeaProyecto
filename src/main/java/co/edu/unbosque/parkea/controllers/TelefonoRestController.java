package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.*;
import co.edu.unbosque.parkea.model.dto.TelefonoDTO;
import co.edu.unbosque.parkea.service.TelefonoServiceAPI;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/Telefono")
public class TelefonoRestController {
//buenas
    @Autowired
    private TelefonoServiceAPI telefonoServiceAPI;

    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;

    @Autowired
    private AuditoriaRestController audi;

    @GetMapping(value = "/getAll")
    public List<TelefonoDTO> getAll(){

        List<Telefono> getall = telefonoServiceAPI.getAll();
        List<TelefonoDTO> listaF = new ArrayList<>();

        for (Telefono c:getall){
            if(c.getEstado().equals("A")){
                TelefonoDTO objeto = new TelefonoDTO(c.getIdTelefono(), c.getNumTelefono(), c.getEstado(),c.getUsuario().getLogin() );
                listaF.add(objeto);
            }
        }
        return listaF;
    }

    @PostMapping(value = "/saveTelefono/{idTelefono}/{idUsuario}")
    public HttpStatus save(@RequestBody Telefono telefono,
                           @PathVariable(value = "idUsuario") int idUsuario){
        Usuario user = usuarioServiceAPI.get(idUsuario);
        telefono.setUsuario(user);
        telefono.setEstado("A");
        telefonoServiceAPI.save(telefono);
        audi.saveAuditoria("Guardar", "Telefono",idUsuario);
        return HttpStatus.OK;
    }

    @PutMapping(value = "/updateTelefono/{id}/{idUsuario}")
    public HttpStatus update(@RequestBody Telefono telefono, @PathVariable(value = "id") int id, @PathVariable(value = "idUsuario") int idUsuario){

        Telefono objeto = telefonoServiceAPI.get(id);
        if (objeto != null){
            objeto.setNumTelefono(telefono.getNumTelefono());
            objeto.setEstado(telefono.getEstado());
            telefonoServiceAPI.save(objeto);
            audi.saveAuditoria("Actualizar", "Telefono",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }
    @GetMapping(value = "/deleteTelefono/{id}/{idUsuario}")
    public HttpStatus delete(@PathVariable int id, @PathVariable(value = "idUsuario") int idUsuario){
        Telefono telefono = telefonoServiceAPI.get(id);
        if (telefono != null){
            telefono.setEstado("D");
            telefonoServiceAPI.save(telefono);
            audi.saveAuditoria("Eliminar", "Telefono",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }
}
