package co.edu.unbosque.parkea.repository;

import co.edu.unbosque.parkea.model.Telefono;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta clase se usa como repositorio de teléfono
 */
@Repository
public interface TelefonoRepository extends CrudRepository<Telefono, Integer> {
}
