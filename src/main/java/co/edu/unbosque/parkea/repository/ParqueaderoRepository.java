package co.edu.unbosque.parkea.repository;

import co.edu.unbosque.parkea.model.Parqueadero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParqueaderoRepository extends CrudRepository<Parqueadero, Integer> {
}
