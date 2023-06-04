package co.edu.unbosque.parkea.commons;

import java.io.Serializable;
import java.util.List;

public interface GenericServiceAPI<T, ID extends Serializable>{
    /**
     * Este método se usa para guardar una entidad
     * @param entity
     * @return
     */
    T save(T entity);

    /**
     * Este método se usa para eliminar una entidad por ID
     * @param id
     */
    void delete (ID id);

    /**
     * Este método se usa para traer una entidad por ID
     * @param id
     * @return
     */
    T get(ID id);

    /**
     * Este método se usa para listar las entidades
     * @return
     */
    List<T> getAll();
}