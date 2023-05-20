package co.edu.unbosque.parkea.service.impl;


import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.Auditoria;
import co.edu.unbosque.parkea.repository.AuditoriaRepository;
import co.edu.unbosque.parkea.service.AuditoriaServiceAPI;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditoriaServiceImpl extends GenericServiceImpl<Auditoria, Integer> implements AuditoriaServiceAPI {
    @Autowired
    private AuditoriaRepository auditoriaDtoAPI;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public CrudRepository<Auditoria, Integer> getDto(){
        return auditoriaDtoAPI;
    }

}

