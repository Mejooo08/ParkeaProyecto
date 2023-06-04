package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.TipoParqueadero;
import co.edu.unbosque.parkea.repository.TipoParqueaderoRepository;
import co.edu.unbosque.parkea.service.TipoParqueaderoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoParqueaderoServiceImpl extends GenericServiceImpl<TipoParqueadero, Integer> implements TipoParqueaderoServiceAPI {
    @Autowired
    private TipoParqueaderoRepository tipoParqueaderoDtoApi;

    /**
     * Este método se usa para generar el crud de tipo de parqueadero
     *
     * @return
     */
    @Override
    public CrudRepository<TipoParqueadero, Integer> getDto() {
        return tipoParqueaderoDtoApi;
    }
}
