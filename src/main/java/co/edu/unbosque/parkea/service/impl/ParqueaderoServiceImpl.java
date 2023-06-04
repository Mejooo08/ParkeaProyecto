package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.Parqueadero;
import co.edu.unbosque.parkea.repository.ParqueaderoRepository;
import co.edu.unbosque.parkea.service.ParqueaderoServiceAPI;
import co.edu.unbosque.parkea.util.ParqueaderoReportGenerator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.io.FileNotFoundException;


@Service
public class ParqueaderoServiceImpl extends GenericServiceImpl<Parqueadero, Integer> implements ParqueaderoServiceAPI {

    @Autowired
    private ParqueaderoRepository parqueaderoDtoApi;
    @Autowired
    private ParqueaderoReportGenerator parquaderoReportGenerator;

    /**
     * Este método se usa para generar el crud de parqueadero
     * @return
     */
    @Override
    public CrudRepository<Parqueadero, Integer> getDto() {
        return parqueaderoDtoApi;
    }

    /**
     * Este método se usa para exportar el reporte PDF generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return parquaderoReportGenerator.exportToPdf((List<Parqueadero>) parqueaderoDtoApi.findAll());
    }

    /**
     * Este método se usa para exportar el reporte EXCEL generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        return parquaderoReportGenerator.exportToXls((List<Parqueadero>) parqueaderoDtoApi.findAll());
    }
}
