package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.*;
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

    @PostMapping(value = "/saveCupo/{idReservaCupo}")
    public HttpStatus save(@RequestBody ReservaCupo cupo, @PathVariable(value = "idUsuario") int idUsuario){
        reservaCupoServiceAPI.save(cupo);
        audi.saveAuditoria("Guardar", "Reserva Cupo",idUsuario);
        return HttpStatus.OK;
    }

    @PutMapping(value = "/updateCupo/{id}/{idUsuario}")
    public HttpStatus update(@RequestBody ReservaCupo cupo, @PathVariable(value = "id") int id, @PathVariable(value = "idUsuario") int idUsuario){

        ReservaCupo objeto =reservaCupoServiceAPI.get(id);
        Parqueadero parq = parqueaderoServiceAPI.get(id);
        Usuario usuario = usuarioServiceAPI.get(id);
        if (objeto != null){
            objeto.setUsuario(usuario);
            objeto.setHoraInicio(cupo.getHoraInicio());
            objeto.setHoraFinal(cupo.getHoraFinal());
            objeto.setPlacaCarro(cupo.getPlacaCarro());
            objeto.setIdFactura(cupo.getIdFactura());
            objeto.setIdParqueadero(parq);
            reservaCupoServiceAPI.save(objeto);
            audi.saveAuditoria("Actualizar", "Reserva Cupo",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @GetMapping(value = "/deleteCupo/{id}/{idUsuario}")
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
