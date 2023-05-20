package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Parqueadero;
import co.edu.unbosque.parkea.model.ReservaCupo;
import co.edu.unbosque.parkea.model.TipoParqueadero;
import co.edu.unbosque.parkea.model.dto.ParqueaderoDTO;
import co.edu.unbosque.parkea.model.dto.ReservaCupoDTO;
import co.edu.unbosque.parkea.service.ParqueaderoServiceAPI;
import co.edu.unbosque.parkea.service.ReservaCupoServiceAPI;
import co.edu.unbosque.parkea.service.TipoParqueaderoServiceAPI;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class ReservaCupoRestController {
    private ReservaCupoServiceAPI reservaCupoServiceAPI;

    private ParqueaderoServiceAPI parqueaderoServiceAPI;

    private UsuarioServiceAPI usuarioServiceAPI;
    private AuditoriaRestController audi;

    @GetMapping(value = "/getAll")
    public List<ReservaCupoDTO> getAll(){

        List<ReservaCupo> getall = reservaCupoServiceAPI.getAll();
        List<ReservaCupoDTO> listaF = new ArrayList<>();

        for (ReservaCupo c:getall){
            ReservaCupoDTO objeto = new ReservaCupoDTO(c.getIdFactura(), c.getHoraInicio().toString(), c.getHoraFinal().toString(), c.getUsuario().getLogin(), c.getIdParqueadero().getUbicacion(), c.getPlacaCarro());
                listaF.add(objeto);

        }
        return listaF;
    }

    @PostMapping(value = "/saveParqueadero/{idParqueadero}")
    public HttpStatus save(@RequestBody Parqueadero parq, @PathVariable(value = "idUsuario") int idUsuario){
        parq.setEstado("A");
        parqueaderoServiceAPI.save(parq);
        audi.saveAuditoria("Guardar", "Parqueadero",idUsuario);
        return HttpStatus.OK;
    }

    @PutMapping(value = "/updateParq/{id}/{idUsuario}")
    public HttpStatus update(@RequestBody Parqueadero parq, @PathVariable(value = "id") int id, @PathVariable(value = "idUsuario") int idUsuario){

        Parqueadero objeto = parqueaderoServiceAPI.get(id);
        ReservaCupo reservaCupo =reservaCupoServiceAPI.get(id);
        if (reservaCupo != null){
            reservaCupo.setIdParqueadero(objeto);
            reservaCupo.setUsuario(reservaCupo.getUsuario());
            reservaCupo.setHoraFinal(reservaCupo.getHoraFinal());
            reservaCupo.setPlacaCarro(reservaCupo.getPlacaCarro());
            reservaCupo.setIdFactura(reservaCupo.getIdFactura());
            reservaCupo.setHoraInicio(reservaCupo.getHoraInicio());
            reservaCupoServiceAPI.save(reservaCupo);
            audi.saveAuditoria("Actualizar", "Carro",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }



}
