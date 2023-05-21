package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Carro;
import co.edu.unbosque.parkea.model.Parqueadero;
import co.edu.unbosque.parkea.model.TipoParqueadero;
import co.edu.unbosque.parkea.model.dto.ParqueaderoDTO;
import co.edu.unbosque.parkea.service.ParqueaderoServiceAPI;
import co.edu.unbosque.parkea.service.TipoParqueaderoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/Parqueadero")
public class ParqueaderoRestController {

    @Autowired
    private ParqueaderoServiceAPI parqueaderoServiceAPI;

    @Autowired
    private TipoParqueaderoServiceAPI tipoParqueaderoServiceAPI;

    @Autowired
    private AuditoriaRestController audi;

    @GetMapping(value = "/getAll")
    public List<ParqueaderoDTO> getAll(){

        List<Parqueadero> getall = parqueaderoServiceAPI.getAll();
        List<ParqueaderoDTO> listaF = new ArrayList<>();

        for (Parqueadero c:getall){
            if(c.getEstado().equals("A")){
                ParqueaderoDTO objeto = new ParqueaderoDTO(c.getIdParqueadero(), c.getIdTipoParq().getDescripcion(), c.getUbicacion(), c.getHorarioAtencion(), c.getTarifa(), c.getFidelizacion(), c.getCiudad(), c.getCuposTotales(), c.getCuposOcupados(), c.getEstado());
                listaF.add(objeto);
            }
        }
        return listaF;
    }

    @PostMapping(value = "/saveParq/{idParqueadero}")
    public HttpStatus save(@RequestBody Parqueadero parq, @PathVariable(value = "idUsuario") int idUsuario){
        parq.setEstado("A");
        parqueaderoServiceAPI.save(parq);
        audi.saveAuditoria("Guardar", "Parqueadero",idUsuario);
        return HttpStatus.OK;
    }

    @PutMapping(value = "/updateParq/{id}/{idUsuario}")
    public HttpStatus update(@RequestBody Parqueadero parq, @PathVariable(value = "id") int id, @PathVariable(value = "idUsuario") int idUsuario){

        Parqueadero objeto = parqueaderoServiceAPI.get(id);
        TipoParqueadero tipoParqueadero =tipoParqueaderoServiceAPI.get(id);
        if (objeto != null){
            objeto.setIdTipoParq(tipoParqueadero);
            objeto.setUbicacion(parq.getUbicacion());
            objeto.setHorarioAtencion(parq.getHorarioAtencion());
            objeto.setTarifa(parq.getTarifa());
            objeto.setFidelizacion(parq.getFidelizacion());
            objeto.setCiudad(parq.getCiudad());
            objeto.setCuposTotales(parq.getCuposTotales());
            objeto.setCuposOcupados(parq.getCuposOcupados());
            objeto.setEstado(parq.getEstado());
            parqueaderoServiceAPI.save(objeto);
            audi.saveAuditoria("Actualizar", "Parqueadero",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @GetMapping(value = "/deleteParq/{id}/{idUsuario}")
    public HttpStatus delete(@PathVariable int id, @PathVariable(value = "idUsuario") int idUsuario){
        Parqueadero parqueadero = parqueaderoServiceAPI.get(id);
        if (parqueadero != null){
            parqueadero.setEstado("D");
            parqueaderoServiceAPI.save(parqueadero);
            audi.saveAuditoria("Eliminar", "Parqueadero",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

}
