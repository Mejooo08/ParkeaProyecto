package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.TipoParqueadero;
import co.edu.unbosque.parkea.model.dto.TipoParqueaderoDTO;
import co.edu.unbosque.parkea.service.TipoParqueaderoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/TipoParqueadero")
public class TipoParqueaderoRestController {

    @Autowired
     private TipoParqueaderoServiceAPI tipoParqueaderoServiceAPI;

    /**
     * Este método se usa para listar todos los elementos de tipo de parqueadero
     * @return
     */
    @GetMapping(value = "/getAll")
    public List<TipoParqueaderoDTO> getAll(){
        List<TipoParqueadero> getall = tipoParqueaderoServiceAPI.getAll();
        List<TipoParqueaderoDTO> listaF = new ArrayList<>();

        for (TipoParqueadero c:getall){
            if(c.getEstado().equals("A")){
                TipoParqueaderoDTO objeto = new TipoParqueaderoDTO(c.getIdTipoParq(), c.getDescripcion(),c.getEstado() );
                listaF.add(objeto);
            }
        }
        return listaF;
    }
}
