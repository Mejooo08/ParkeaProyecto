package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.Parqueadero;
import co.edu.unbosque.parkea.service.ParqueaderoServiceAPI;
import org.springframework.data.repository.CrudRepository;

public class ParqueaderoServiceImpl extends GenericServiceImpl<Parqueadero, Integer> implements ParqueaderoServiceAPI {
    @Override
    public CrudRepository<Parqueadero, Integer> getDto() {
        return null;
    }
}
