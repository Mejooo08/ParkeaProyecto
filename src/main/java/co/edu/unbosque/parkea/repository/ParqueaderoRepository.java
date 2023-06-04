package co.edu.unbosque.parkea.repository;

import co.edu.unbosque.parkea.model.Parqueadero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta clase se usa como repositorio de parqueadero
 */
@Repository
public interface ParqueaderoRepository extends CrudRepository<Parqueadero, Integer> {
}
