package co.edu.unbosque.parkea.repository;

import co.edu.unbosque.parkea.model.Auditoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta clase se usa como repositorio de auditoría
 */
@Repository
public interface AuditoriaRepository extends CrudRepository<Auditoria, Integer> {
}
