package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.TipoDocumento;
import co.edu.unbosque.parkea.model.dto.TipoDocumentoDTO;
import co.edu.unbosque.parkea.service.TipoDocumentoServiceAPI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/Document")
public class TipoDocumentoRestController {

    private TipoDocumentoServiceAPI tipoDocumentoServiceAPI;

    private AuditoriaRestController audi;

    @GetMapping(value = "/getAll")
    public List<TipoDocumentoDTO> getAll(){
        List<TipoDocumento> getall = tipoDocumentoServiceAPI.getAll();
        List<TipoDocumentoDTO> listaF = new ArrayList<>();

        for (TipoDocumento t:getall){
            if(t.getEstado().equals("A")){
                TipoDocumentoDTO objeto = new TipoDocumentoDTO(t.getIdDocumento(),t.getDescripcion(),t.getEstado());
                listaF.add(objeto);
            }
        }
        return listaF;
    }
}