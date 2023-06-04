package co.edu.unbosque.parkea.repository;

import co.edu.unbosque.parkea.model.Carro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta clase se usa como repositorio de carro
 */
@Repository
public interface CarroRepository extends CrudRepository<Carro, Integer> {
}
