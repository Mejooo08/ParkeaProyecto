package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Rol;
import co.edu.unbosque.parkea.model.dto.RolDTO;
import co.edu.unbosque.parkea.service.RolServiceAPI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/Rol")
public class RolRestController {

    private RolServiceAPI rolServiceAPI;


    private AuditoriaRestController audi;

    @GetMapping(value = "/getAll")
    public List<RolDTO> getAll(){
        List<Rol> getall = rolServiceAPI.getAll();
        List<RolDTO> listaF = new ArrayList<>();

        for (Rol r:getall){
            if(r.getEstado().equals("A")){
                RolDTO objeto = new RolDTO(r.getIdRol(),r.getTipoRol(),r.getEstado());
                listaF.add(objeto);
            }
        }
        return listaF;
    }

    @PostMapping(value = "/saveRol/{idUsuario}")
    public HttpStatus save(@RequestBody Rol rol, @PathVariable(value = "idUsuario") int idUsuario){
        rol.setEstado("A");
        rolServiceAPI.save(rol);
        audi.saveAuditoria("Guardar", "Rol",idUsuario);
        return HttpStatus.OK;
    }

    @PutMapping(value = "/updateRol/{id}/{idUsuario}")
    public HttpStatus update(@RequestBody Rol rol, @PathVariable(value = "id") int id, @PathVariable(value = "idUsuario") int idUsuario){

        Rol objeto = rolServiceAPI.get(id);
        if (objeto != null){
            objeto.setTipoRol(rol.getTipoRol());
            objeto.setEstado(rol.getEstado());
            rolServiceAPI.save(objeto);
            audi.saveAuditoria("Actualizar", "Rol",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @GetMapping(value = "/deleteRol/{id}/{idUsuario}")
    public HttpStatus delete(@PathVariable int id, @PathVariable(value = "idUsuario") int idUsuario){
        Rol rol = rolServiceAPI.get(id);
        if (rol != null){
            rol.setEstado("D");
            rolServiceAPI.save(rol);
            audi.saveAuditoria("Eliminar", "Rol",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }
}