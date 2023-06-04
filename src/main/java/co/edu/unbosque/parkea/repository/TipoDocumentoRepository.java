package co.edu.unbosque.parkea.repository;

import co.edu.unbosque.parkea.model.TipoDocumento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta clase se usa como repositorio de tipo de documento
 */
@Repository
public interface TipoDocumentoRepository extends CrudRepository <TipoDocumento, Integer> {
}
