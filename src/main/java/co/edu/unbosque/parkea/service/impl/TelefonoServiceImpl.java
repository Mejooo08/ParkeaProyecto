package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.ReservaCupo;
import co.edu.unbosque.parkea.model.Telefono;
import co.edu.unbosque.parkea.repository.TelefonoRepository;
import co.edu.unbosque.parkea.service.ReservaCupoServiceAPI;
import co.edu.unbosque.parkea.service.TelefonoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class TelefonoServiceImpl extends GenericServiceImpl<Telefono, Integer> implements TelefonoServiceAPI {
    @Autowired
    private TelefonoRepository telefonoDtoApi;
    @Override
    public CrudRepository<Telefono, Integer> getDto() {
        return telefonoDtoApi;
    }
}
