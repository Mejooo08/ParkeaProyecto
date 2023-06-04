package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.repository.UsuarioRepository;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import co.edu.unbosque.parkea.util.UsuarioReportGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.Random;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import java.io.FileNotFoundException;


@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Integer> implements UsuarioServiceAPI {

    @Autowired
    private UsuarioRepository usuarioDtoAPI;

    @Autowired
    private UsuarioReportGenerator usuarioReportGenerator;


    @PersistenceContext
    EntityManager entityManager;

    /**
     * Este método se usa para generar el crud de usuario
     * @return
     */
    @Override
    public CrudRepository<Usuario, Integer> getDto(){
        return usuarioDtoAPI;
    }

    /**
     * Este método se usa para hacer el login de un usuario
     * @param correo
     * @param clave
     * @return
     */
    @Override
    public Usuario login(String correo, String clave){
        String query = "FROM Usuario WHERE login = :login";
        List<Usuario> lista = entityManager.createQuery(query).setParameter("login", correo).getResultList();

        if(!lista.isEmpty()){
            Usuario encontrado = lista.get(0);
            System.out.println("entro a no null");
            if(validarContra(clave, encontrado)){
                System.out.println("Clave correcta");
                return lista.get(0);
            }else {
                System.out.println("Clave incorrecta");
                int intentos = encontrado.getIntentos() +1;
                encontrado.setIntentos(intentos);
                return null;
            }
        }else{
            System.out.println("Dio null");
            return null;
        }
    }

    /**
     * Este método se usa para validar la contraseña de un usuario
     * @param contraValidar
     * @param u
     * @return
     */
    public boolean validarContra(String contraValidar, Usuario u){
        String contraHash = hashearContra(contraValidar);
        if(contraHash.equals(u.getClave())){
            System.out.println("Contraseña igual");
            System.out.println(u.getIntentos());
            u.setIntentos(0);
            System.out.println(u.getIntentos());
            save(u);
            return true;
        }
        System.out.println("Contraseña diferente");
        u.setIntentos(u.getIntentos()+1);
        save(u);
        validarIntentos(u);
        return false;
    }

    /**
     * Este método se usa para validar el estado en el que se encuentra un usuario
     * @param u
     * @return
     */
    @Override
    public int validarEstado(Usuario u){
        if(u.getEstado().equals("A")){
            System.out.println("Estado activo");
            return 1;

        }else if(u.getEstado().equals("N")) {
            System.out.println("Necesario cambio contraseña");
            return 2;
        }else{
            System.out.println("Estado desactivado");
            return 0;
        }
    }

    /**
     * Este método se usa para validar los intentos de inicio de sesión de un usuario
     * @param u
     */

    public void validarIntentos(Usuario u){
        if(u.getIntentos() >= 3){
            System.out.println("Usuario con mas de tres intentos bloquear");
            u.setEstado("D");
            save(u);
        }else{
            System.out.println("Intentos permitidos");
        }
    }

    /**
     * Este método se usa para hashear la contraseña del usuario
     * @param contra
     * @return
     */
    @Override
    public String hashearContra(String contra){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(contra.getBytes());
        StringBuffer sb = new StringBuffer();

        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    /**
     * Este método se usa para generar la contraseña del usuario la primera vez
     * @param longitud
     * @return
     */

    public String generarContrasena(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*-#";
        Random random = new Random();
        StringBuilder contraseña = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());
            contraseña.append(caracteres.charAt(indice));
        }

        return contraseña.toString();
    }

    /**
     * Este método se usa para exportar el reporte PDF generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return usuarioReportGenerator.exportToPdf((List<Usuario>) usuarioDtoAPI.findAll());
    }

    /**
     * Este método se usa para exportar el reporte EXCEL generado
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */

    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        return usuarioReportGenerator.exportToXls((List<Usuario>) usuarioDtoAPI.findAll());
    }
}

