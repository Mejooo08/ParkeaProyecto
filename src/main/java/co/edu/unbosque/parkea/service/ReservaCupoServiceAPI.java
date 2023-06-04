package co.edu.unbosque.parkea.service;

import co.edu.unbosque.parkea.commons.GenericServiceAPI;
import co.edu.unbosque.parkea.model.ReservaCupo;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ReservaCupoServiceAPI extends GenericServiceAPI<ReservaCupo, Integer> {

    ArrayList<Integer> facturacion(int tarifa, String horaIngreso, String horaSalida, String fidelizacion);

    /**
     * Este método se usa para exportar el reporte PDF generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    byte[] exportPdf() throws JRException, FileNotFoundException;

    /**
     * Este método se usa para exportar el reporte EXCEL
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */

    byte[] exportXls() throws JRException, FileNotFoundException;
}
