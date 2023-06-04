package co.edu.unbosque.parkea.commons;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericServiceAPI<T, ID>{
    /**
     * Este método se usa para guardar una entidad
     * @param entity
     * @return
     */
    @Override
    public T save(T entity){
        return getDto().save(entity);
    }

    /**
     * Este método se usa para eliminar una entidad
     * @param id
     */
    @Override
    public void delete(ID id){
        getDto().deleteById(id);
    }

    /**
     * Este método se usa para traer una entidad
     * @param id
     * @return
     */
    @Override
    public T get(ID id){
        Optional<T> obj = getDto().findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }

    /**
     * Este método se usa para listar todos los objetos dentro de una entidad
     * @return
     */
    @Override
    public List<T> getAll(){
        List<T> resultList = new ArrayList<>();
        getDto().findAll().forEach(obj -> resultList.add(obj));
        return resultList;
    }

    /**
     * Este método se usa para realizar el crud de una entidad
     * @return
     */
    public abstract CrudRepository<T, ID> getDto();

}
