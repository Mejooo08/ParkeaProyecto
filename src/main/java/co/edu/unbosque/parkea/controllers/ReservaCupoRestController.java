package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.*;
import co.edu.unbosque.parkea.model.ReservaCupo;
import co.edu.unbosque.parkea.model.TipoParqueadero;
import co.edu.unbosque.parkea.model.dto.ParqueaderoDTO;
import co.edu.unbosque.parkea.model.dto.ReservaCupoDTO;
import co.edu.unbosque.parkea.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ReservaCupo")
public class ReservaCupoRestController {

    @Autowired
    private ReservaCupoServiceAPI reservaCupoServiceAPI;

    @Autowired
    private ParqueaderoServiceAPI parqueaderoServiceAPI;

    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;

    @Autowired
    private CarroServiceAPI carroServiceAPI;

    @Autowired
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

    @PostMapping(value = "/saveCupo/{idReservaCupo}/{idParqueadero}/{idUsuario}")
    public HttpStatus save(@RequestBody ReservaCupo cupo,
                           @PathVariable(value = "idParqueadero") int idParqueadero,
                           @PathVariable(value = "idUsuario") int idUsuario){
        Usuario user = usuarioServiceAPI.get(idUsuario);
        Parqueadero parq = parqueaderoServiceAPI.get(idParqueadero);
        Carro carro = user.getCarros().get(0);
        cupo.setIdParqueadero(parq);
        cupo.setPlacaCarro(carro.getPlaca());
        cupo.setUsuario(user);
        reservaCupoServiceAPI.save(cupo);
        audi.saveAuditoria("Guardar", "Reserva Cupo",idUsuario);
        return HttpStatus.OK;
    }

    @PutMapping(value = "/updateCupo/{id}/{idParqueadero}/{idUsuario}")
    public HttpStatus update(@RequestBody ReservaCupo cupo,
                             @PathVariable(value = "idParqueadero") int idParqueadero,
                             @PathVariable(value = "id") int id,
                             @PathVariable(value = "idUsuario") int idUsuario){
        Usuario usuario = usuarioServiceAPI.get(idUsuario);
        Parqueadero parq = parqueaderoServiceAPI.get(idParqueadero);
        ReservaCupo objeto =reservaCupoServiceAPI.get(id);
        if (objeto != null){
            objeto.setUsuario(usuario);
            objeto.setHoraInicio(cupo.getHoraInicio());
            objeto.setHoraFinal(cupo.getHoraFinal());
            objeto.setPlacaCarro(cupo.getPlacaCarro());
            objeto.setIdParqueadero(parq);
            reservaCupoServiceAPI.save(objeto);
            audi.saveAuditoria("Actualizar", "Reserva Cupo",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }
    
}
