package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Carro;
import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.model.dto.CarroDTO;
import co.edu.unbosque.parkea.service.CarroServiceAPI;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/Carro")
public class CarroRestController {

    @Autowired
    private CarroServiceAPI carroServiceAPI;

    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;

    @Autowired
    private AuditoriaRestController audi;

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


    @PostMapping(value = "/saveCarro/{idCarro}/{idUsuario}")
    public HttpStatus save(@RequestBody Carro carro, @PathVariable(value = "idUsuario") int idUsuario){
        Usuario user = usuarioServiceAPI.get(idUsuario);
        carro.setUsuario(user);
        carro.setEstado("A");
        carroServiceAPI.save(carro);
        audi.saveAuditoria("Guardar", "Carro",idUsuario);
        return HttpStatus.OK;
    }

    @PutMapping(value = "/updateCarro/{id}/{idUsuario}")
    public HttpStatus update(@RequestBody Carro carro, @PathVariable(value = "id") int id, @PathVariable(value = "idUsuario") int idUsuario){

        Carro objeto = carroServiceAPI.get(id);
        if (objeto != null){
            objeto.setPlaca(carro.getPlaca());
            objeto.setModelo(carro.getModelo());
            objeto.setEstado(carro.getEstado());
            carroServiceAPI.save(objeto);
            audi.saveAuditoria("Actualizar", "Carro",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    @GetMapping(value = "/deleteCarro/{id}/{idUsuario}")
    public HttpStatus delete(@PathVariable int id, @PathVariable(value = "idUsuario") int idUsuario){
        Carro carro = carroServiceAPI.get(id);
        if (carro != null){
            carro.setEstado("D");
            carroServiceAPI.save(carro);
            audi.saveAuditoria("Eliminar", "Carro",idUsuario);
        }else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }
}
