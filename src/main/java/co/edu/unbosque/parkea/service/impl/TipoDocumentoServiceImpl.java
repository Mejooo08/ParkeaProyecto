package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.TipoDocumento;
import co.edu.unbosque.parkea.repository.TipoDocumentoRepository;
import co.edu.unbosque.parkea.service.TipoDocumentoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoDocumentoServiceImpl extends GenericServiceImpl<TipoDocumento, Integer> implements TipoDocumentoServiceAPI {
    @Autowired
    private TipoDocumentoRepository tipoDocumentoDtoApi;
    @Override
    public CrudRepository<TipoDocumento, Integer> getDto() {
        return tipoDocumentoDtoApi;
    }
}
