package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.ReservaCupo;
import co.edu.unbosque.parkea.service.ReservaCupoServiceAPI;
import org.springframework.data.repository.CrudRepository;

public class ReservaCupoServiceImpl extends GenericServiceImpl<ReservaCupo, Integer> implements ReservaCupoServiceAPI {
    @Override
    public CrudRepository<ReservaCupo, Integer> getDto() {
        return null;
    }
}
