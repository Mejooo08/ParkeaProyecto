package co.edu.unbosque.parkea.repository;

import co.edu.unbosque.parkea.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {
}
