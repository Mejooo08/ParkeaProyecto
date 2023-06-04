package co.edu.unbosque.parkea.service.impl;


import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.Auditoria;
import co.edu.unbosque.parkea.repository.AuditoriaRepository;
import co.edu.unbosque.parkea.service.AuditoriaServiceAPI;
import co.edu.unbosque.parkea.util.AuditoriaReportGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class AuditoriaServiceImpl extends GenericServiceImpl<Auditoria, Integer> implements AuditoriaServiceAPI {
    @Autowired
    private AuditoriaRepository auditoriaDtoAPI;

    @Autowired
    private AuditoriaReportGenerator auditoriaReportGenerator;
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Este método se usa para generar el crud de auditoría
     * @return
     */
    @Override
    public CrudRepository<Auditoria, Integer> getDto(){
        return auditoriaDtoAPI;
    }

    /**
     * Este método se usa para exportar el reporte PDF generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return auditoriaReportGenerator.exportToPdf((List<Auditoria>) auditoriaDtoAPI.findAll());
    }

    /**
     * Este método se usa para exportar el reporte EXCEL generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        return auditoriaReportGenerator.exportToXls((List<Auditoria>) auditoriaDtoAPI.findAll());
    }

}

