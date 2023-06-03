package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.ReservaCupo;
import co.edu.unbosque.parkea.repository.ReservaCupoRepository;
import co.edu.unbosque.parkea.service.ReservaCupoServiceAPI;
import co.edu.unbosque.parkea.util.ReservaReportGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservaCupoServiceImpl extends GenericServiceImpl<ReservaCupo, Integer> implements ReservaCupoServiceAPI {
    @Autowired
    private ReservaCupoRepository reservaDtoApi;
    @Autowired
    private ReservaReportGenerator reservaReportGenerator;
    @Override
    public CrudRepository<ReservaCupo, Integer> getDto() {
        return reservaDtoApi;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ArrayList<Integer> facturacion(int tarifa, String horaIngreso, String horaSalida, String fidelizacion){
        ArrayList<Integer> numeros = new ArrayList<>();
        int puntos = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            Date fecha1 = dateFormat.parse(horaIngreso);
            Date fecha2 = dateFormat.parse(horaSalida);
            Duration diferencia = Duration.between(fecha1.toInstant(), fecha2.toInstant());
            long diferencia_m = diferencia.toMinutes();
            int precio = (int) diferencia_m * tarifa;
            if(fidelizacion.equals("S")){
                puntos = precio /1000;
            }

            numeros.add(precio);
            numeros.add(puntos);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return numeros;
    }
    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return reservaReportGenerator.exportToPdf((List<ReservaCupo>) reservaDtoApi.findAll());
    }

    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        return reservaReportGenerator.exportToXls((List<ReservaCupo>) reservaDtoApi.findAll());
    }
}
