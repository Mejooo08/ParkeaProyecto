package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.Telefono;
import co.edu.unbosque.parkea.repository.TelefonoRepository;
import co.edu.unbosque.parkea.service.TelefonoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TelefonoServiceImpl extends GenericServiceImpl<Telefono, Integer> implements TelefonoServiceAPI {
    @Autowired
    private TelefonoRepository telefonoDtoApi;

    /**
     * Este método se usa para generar el crud de teléfono
     * @return
     */
    @Override
    public CrudRepository<Telefono, Integer> getDto() {
        return telefonoDtoApi;
    }
}
