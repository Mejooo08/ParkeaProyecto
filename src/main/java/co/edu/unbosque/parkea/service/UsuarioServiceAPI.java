package co.edu.unbosque.parkea.service;

import co.edu.unbosque.parkea.commons.GenericServiceAPI;
import co.edu.unbosque.parkea.model.Usuario;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface UsuarioServiceAPI extends GenericServiceAPI<Usuario,Integer> {
    /**
     * Este método se usa para realizar el login de un usuario
     * @param correo
     * @param clave
     * @return
     */
    Usuario login(String correo, String clave);

    /**
     * Este método se usa para validar el estado de un usuairo
     * @param u
     * @return
     */
    int validarEstado(Usuario u);

    /**
     * Este método se usa para hashear la contraseña de un usuario
     * @param contra
     * @return
     */
    String hashearContra(String contra);

    /**
     * Este método se usa para generar la contraseña de un usuario
     * @param longitud
     * @return
     */
    String generarContrasena(int longitud);

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
