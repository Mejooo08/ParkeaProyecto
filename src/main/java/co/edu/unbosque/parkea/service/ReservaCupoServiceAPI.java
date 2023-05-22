package co.edu.unbosque.parkea.service;

import co.edu.unbosque.parkea.commons.GenericServiceAPI;
import co.edu.unbosque.parkea.model.ReservaCupo;

public interface ReservaCupoServiceAPI extends GenericServiceAPI<ReservaCupo, Integer> {

    int facturacion(int tarifa, String horaIngreso, String horaSalida,  String fidelizacion);
}
