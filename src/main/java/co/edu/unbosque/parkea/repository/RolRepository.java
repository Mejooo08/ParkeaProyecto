package co.edu.unbosque.parkea.repository;

import co.edu.unbosque.parkea.model.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta clase se usa como repositorio de Rol
 */
@Repository
public interface RolRepository extends CrudRepository<Rol, Integer> {
}
