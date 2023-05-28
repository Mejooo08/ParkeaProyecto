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

    @Override
    public CrudRepository<Usuario, Integer> getDto(){
        return usuarioDtoAPI;
    }
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

    public void validarIntentos(Usuario u){
        if(u.getIntentos() >= 3){
            System.out.println("Usuario con mas de tres intentos bloquear");
            u.setEstado("D");
            save(u);
        }else{
            System.out.println("Intentos permitidos");
        }
    }

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

    @Override
    public byte[] exportPdf() throws JRException, FileNotFoundException {
        return usuarioReportGenerator.exportToPdf((List<Usuario>) usuarioDtoAPI.findAll());
    }

    @Override
    public byte[] exportXls() throws JRException, FileNotFoundException {
        return usuarioReportGenerator.exportToXls((List<Usuario>) usuarioDtoAPI.findAll());
    }
}

