package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.Carro;
import co.edu.unbosque.parkea.repository.CarroRepository;
import co.edu.unbosque.parkea.service.CarroServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CarroServiceImpl extends GenericServiceImpl<Carro, Integer> implements CarroServiceAPI {
    @Autowired
    private CarroRepository carroDtoApi;

    /**
     * Este método se usa para generar el crud de carro
     * @return
     */
    @Override
    public CrudRepository<Carro, Integer> getDto() {
        return carroDtoApi;
    }
}
