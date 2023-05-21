package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.Rol;
import co.edu.unbosque.parkea.repository.RolRepository;
import co.edu.unbosque.parkea.service.RolServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends GenericServiceImpl<Rol, Integer> implements RolServiceAPI {
    @Autowired
    private RolRepository rolDtoApi;
    @Override
    public CrudRepository<Rol, Integer> getDto() {
        return rolDtoApi;
    }
}
