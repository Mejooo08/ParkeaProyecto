package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.ReservaCupo;
import co.edu.unbosque.parkea.repository.ReservaCupoRepository;
import co.edu.unbosque.parkea.service.ReservaCupoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservaCupoServiceImpl extends GenericServiceImpl<ReservaCupo, Integer> implements ReservaCupoServiceAPI {
    @Autowired
    private ReservaCupoRepository reservaDtoApi;
    @Override
    public CrudRepository<ReservaCupo, Integer> getDto() {
        return reservaDtoApi;
    }
}
