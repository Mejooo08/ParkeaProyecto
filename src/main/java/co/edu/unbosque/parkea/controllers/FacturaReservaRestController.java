package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Parqueadero;
import co.edu.unbosque.parkea.model.ReservaCupo;
import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.service.ParqueaderoServiceAPI;
import co.edu.unbosque.parkea.service.ReservaCupoServiceAPI;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/ReservaCupo/Factura")
public class FacturaReservaRestController {

    @Autowired
    private ReservaCupoServiceAPI reservaCupoServiceAPI;

    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;

    @Autowired
    private AuditoriaRestController audi;

    @Autowired
    private ParqueaderoServiceAPI parqueaderoServiceAPI;

    /**
     * Este método se usa para generar la factura de la reserva
     * @param idParqueadero
     * @param idReservaCupo
     * @param idUsuario
     * @return
     */
    @GetMapping(value = "/factura/{idUsuario}/{idParqueadero}/{idReservaCupo}")
    public HttpStatus factura(@PathVariable(value = "idParqueadero") int idParqueadero,
                              @PathVariable(value = "idReservaCupo") int idReservaCupo,
                              @PathVariable(value = "idUsuario") int idUsuario){
        Usuario usuario = usuarioServiceAPI.get(idUsuario);
        ReservaCupo cupo = reservaCupoServiceAPI.get(idReservaCupo);
        Parqueadero parq = parqueaderoServiceAPI.get(idParqueadero);
        ArrayList<Integer> valores = reservaCupoServiceAPI.facturacion(parq.getTarifa(), cupo.getHoraIngreso(), cupo.getHoraSalida(), parq.getFidelizacion());
        int puntos = valores.get(0);
        int precio = valores.get(1);
        usuario.setPuntosFidelizacion(usuario.getPuntosFidelizacion()+puntos);
        audi.saveAuditoria("Pago Reserva", "Reserva Cupo", idUsuario);
        return HttpStatus.OK;
    }
}
