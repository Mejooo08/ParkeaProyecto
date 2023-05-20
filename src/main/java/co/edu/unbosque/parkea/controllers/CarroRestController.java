package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Auditoria;
import co.edu.unbosque.parkea.model.Carro;
import co.edu.unbosque.parkea.model.dto.AuditoriaDTO;
import co.edu.unbosque.parkea.model.dto.CarroDTO;
import co.edu.unbosque.parkea.service.AuditoriaServiceAPI;
import co.edu.unbosque.parkea.service.CarroServiceAPI;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

public class CarroRestController {

    private CarroServiceAPI carroServiceAPI;

    private AuditoriaServiceAPI audi;

    @GetMapping(value = "/getAll")
    public List<CarroDTO> getAll(){
        List<Carro> getall = carroServiceAPI.getAll();
        List<CarroDTO> listaN = new ArrayList<>();
        for (Carro a:getall){
            CarroDTO objeto = new CarroDTO(a.getIdCarro(), a.getUsuario().getLogin(), a.getPlaca(), a.getModelo(), a.getEstado());
            listaN.add(objeto);
        }
        return listaN;
    }
}
