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
}