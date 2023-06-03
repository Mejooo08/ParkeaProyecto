package co.edu.unbosque.parkea.service;

import co.edu.unbosque.parkea.commons.GenericServiceAPI;
import co.edu.unbosque.parkea.model.Auditoria;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface AuditoriaServiceAPI extends GenericServiceAPI<Auditoria,Integer> {
    byte[] exportPdf() throws JRException, FileNotFoundException;

    byte[] exportXls() throws JRException, FileNotFoundException;
}
