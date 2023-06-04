package co.edu.unbosque.parkea.service;

import co.edu.unbosque.parkea.commons.GenericServiceAPI;
import co.edu.unbosque.parkea.model.Parqueadero;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface ParqueaderoServiceAPI extends GenericServiceAPI<Parqueadero,Integer> {
    byte[] exportPdf() throws JRException, FileNotFoundException;

    byte[] exportXls() throws JRException, FileNotFoundException;
}
