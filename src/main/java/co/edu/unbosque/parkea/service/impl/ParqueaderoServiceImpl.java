package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.Parqueadero;
import co.edu.unbosque.parkea.repository.ParqueaderoRepository;
import co.edu.unbosque.parkea.service.ParqueaderoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ParqueaderoServiceImpl extends GenericServiceImpl<Parqueadero, Integer> implements ParqueaderoServiceAPI {

    @Autowired
    private ParqueaderoRepository parqueaderoDtoApi;
    @Override
    public CrudRepository<Parqueadero, Integer> getDto() {
        return parqueaderoDtoApi;
    }
}
