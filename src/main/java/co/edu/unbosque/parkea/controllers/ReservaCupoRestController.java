package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.*;
import co.edu.unbosque.parkea.model.ReservaCupo;
import co.edu.unbosque.parkea.model.TipoParqueadero;
import co.edu.unbosque.parkea.model.dto.ParqueaderoDTO;
import co.edu.unbosque.parkea.model.dto.ReservaCupoDTO;
import co.edu.unbosque.parkea.service.*;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
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

    /**
     * Este método se usa para listar todos los elementos de reseva
     * @return
     */

    @GetMapping(value = "/getAll")
    public List<ReservaCupoDTO> getAll(){

        List<ReservaCupo> getall = reservaCupoServiceAPI.getAll();
        List<ReservaCupoDTO> listaF = new ArrayList<>();

        for (ReservaCupo c:getall){
            ReservaCupoDTO objeto = new ReservaCupoDTO(c.getIdFactura(), c.getMomentoReserva().toString(), c.getHoraIngreso().toString(),c.getHoraSalida().toString(), c.getUsuario().getLogin(), c.getIdParqueadero().getUbicacion(), c.getPlacaCarro(), c.getEstado());
                listaF.add(objeto);

        }
        return listaF;
    }

    /**
     * Este método se usa para guardar los datos de una reserva
     * @param cupo
     * @param idParqueadero
     * @param idUsuario
     * @return
     */
    @PostMapping(value = "/saveCupo/{idReservaCupo}/{idParqueadero}/{idUsuario}")
    public HttpStatus save(@RequestBody ReservaCupo cupo,
                           @PathVariable(value = "idParqueadero") int idParqueadero,
                           @PathVariable(value = "idUsuario") int idUsuario){
        Usuario user = usuarioServiceAPI.get(idUsuario);
        Parqueadero parq = parqueaderoServiceAPI.get(idParqueadero);
        Carro carro = user.getCarros().get(0);
        cupo.setIdParqueadero(parq);
        cupo.setPlacaCarro(carro.getPlaca());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        cupo.setMomentoReserva(timestamp+"");
        cupo.setUsuario(user);
        reservaCupoServiceAPI.save(cupo);
        audi.saveAuditoria("Guardar", "Reserva Cupo",idUsuario);
        return HttpStatus.OK;
    }

    /**
     * Este método se usa para actualizar los datos de una reserva
     * @param cupo
     * @param idParqueadero
     * @param id
     * @param idUsuario
     * @return
     */
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
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            objeto.setMomentoReserva(timestamp+"");
            objeto.setHoraIngreso(cupo.getHoraIngreso());
            objeto.setHoraSalida(cupo.getHoraSalida());
            objeto.setPlacaCarro(cupo.getPlacaCarro());
            objeto.setIdParqueadero(parq);
            reservaCupoServiceAPI.save(objeto);
            audi.saveAuditoria("Actualizar", "Reserva Cupo",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    /**
     * Este método se usa para eliminar una reserva
     * @param id
     * @param idUsuario
     * @return
     */
    @GetMapping(value = "/deleteCupo/{id}/{idUsuario}")
    public HttpStatus delete(@PathVariable int id, @PathVariable(value = "idUsuario") int idUsuario){
        ReservaCupo cupo = reservaCupoServiceAPI.get(id);
        if (cupo != null){
            cupo.setEstado("D");
            reservaCupoServiceAPI.save(cupo);
            audi.saveAuditoria("Eliminar", "Reserva Cupo",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    /**
     * Este método se usa para exportar el reporte PDF generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("reservaReport", "reservaReport.pdf");
        return ResponseEntity.ok().headers(headers).body(reservaCupoServiceAPI.exportPdf());
    }

    /**
     * Este método se usa para exportar el reporte EXCEL generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("reservaReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(reservaCupoServiceAPI.exportXls());
    }
    
}
