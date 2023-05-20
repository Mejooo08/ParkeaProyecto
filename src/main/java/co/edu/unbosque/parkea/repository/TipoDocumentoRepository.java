package co.edu.unbosque.parkea.repository;

import co.edu.unbosque.parkea.model.TipoDocumento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends CrudRepository <TipoDocumento, Integer> {
}
