package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Carro;
import co.edu.unbosque.parkea.model.Parqueadero;
import co.edu.unbosque.parkea.model.TipoParqueadero;
import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.model.dto.ParqueaderoDTO;
import co.edu.unbosque.parkea.service.ParqueaderoServiceAPI;
import co.edu.unbosque.parkea.service.TipoParqueaderoServiceAPI;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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
    private UsuarioServiceAPI usuarioServiceAPI;

    @Autowired
    private AuditoriaRestController audi;

    /**
     * Este método se usa para listar los elementos de parqueadero
     * @return
     */
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

    /**
     * Este método se usa para guardar los datos de un parqueadero
     * @param parq
     * @param idTipoParq
     * @param idUsuario
     * @return
     */
    @PostMapping(value = "/saveParq/{idParqueadero}/{idTipoParq}/{idUsuario}")
    public HttpStatus save(@RequestBody Parqueadero parq,
                           @PathVariable(value = "idTipoParq") int idTipoParq,
                           @PathVariable(value = "idUsuario") int idUsuario){
        TipoParqueadero tipo = tipoParqueaderoServiceAPI.get(idTipoParq);
        parq.setEstado("A");
        parq.setIdTipoParq(tipo);
        parqueaderoServiceAPI.save(parq);
        audi.saveAuditoria("Guardar", "Parqueadero",idUsuario);
        return HttpStatus.OK;
    }

    /**
     * Este método se usa para actualizar los datos de un parqueadero
     * @param parq
     * @param idTipoParq
     * @param id
     * @param idUsuario
     * @return
     */
    @PutMapping(value = "/updateParq/{id}/{idTipoParq}/{idUsuario}")
    public HttpStatus update(@RequestBody Parqueadero parq,
                             @PathVariable(value = "idTipoParq") int idTipoParq,
                             @PathVariable(value = "id") int id, @PathVariable(value = "idUsuario") int idUsuario){

        Parqueadero objeto = parqueaderoServiceAPI.get(id);
        TipoParqueadero tipoParqueadero =tipoParqueaderoServiceAPI.get(idTipoParq);
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

    /**
     * Este método se usa para eliminar un parqueadero
     * @param id
     * @param idUsuario
     * @return
     */
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

    /**
     * Este método se usa para exportar el repote PDF generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("parqueaderoReport", "parqueaderoReport.pdf");
        return ResponseEntity.ok().headers(headers).body(parqueaderoServiceAPI.exportPdf());
    }

    /**
     * Este método se usa para epxortar el reporte EXCEL generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("parqueaderoReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(parqueaderoServiceAPI.exportXls());
    }

}
