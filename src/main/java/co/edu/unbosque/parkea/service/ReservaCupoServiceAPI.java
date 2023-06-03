package co.edu.unbosque.parkea.service;

import co.edu.unbosque.parkea.commons.GenericServiceAPI;
import co.edu.unbosque.parkea.model.ReservaCupo;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ReservaCupoServiceAPI extends GenericServiceAPI<ReservaCupo, Integer> {

    ArrayList<Integer> facturacion(int tarifa, String horaIngreso, String horaSalida, String fidelizacion);

    byte[] exportPdf() throws JRException, FileNotFoundException;

    byte[] exportXls() throws JRException, FileNotFoundException;
}
