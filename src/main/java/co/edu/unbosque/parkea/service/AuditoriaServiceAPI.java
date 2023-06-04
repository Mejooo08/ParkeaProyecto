package co.edu.unbosque.parkea.service;

import co.edu.unbosque.parkea.commons.GenericServiceAPI;
import co.edu.unbosque.parkea.model.Auditoria;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface AuditoriaServiceAPI extends GenericServiceAPI<Auditoria,Integer> {
    /**
     * Este método se usa para exportar el reporte PDF generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    byte[] exportPdf() throws JRException, FileNotFoundException;

    /**
     * Este método se usa para exportar el reporte EXCEL generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */

    byte[] exportXls() throws JRException, FileNotFoundException;
}
