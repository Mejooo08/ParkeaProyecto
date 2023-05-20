package co.edu.unbosque.parkea.repository;

import co.edu.unbosque.parkea.model.TipoParqueadero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoParqueaderoRepository extends CrudRepository<TipoParqueadero, Integer> {
}
