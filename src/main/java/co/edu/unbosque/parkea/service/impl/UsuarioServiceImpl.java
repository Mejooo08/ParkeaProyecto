package co.edu.unbosque.parkea.service.impl;

import co.edu.unbosque.parkea.commons.GenericServiceImpl;
import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.repository.UsuarioRepository;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Integer> implements UsuarioServiceAPI {

    @Autowired
    private UsuarioRepository usuarioDtoAPI;


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
        Usuario encontrado = lista.get(0);

        if(encontrado != null){
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
    public boolean validarEstado(Usuario u){
        if(u.getEstado().equals("A")){
            System.out.println("Estado activo");
            return true;
        }else{
            System.out.println("Estado desactivado");
            return false;
        }
    }

    public void validarIntentos(Usuario u){
        if(u.getIntentos() > 3){
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
            md = MessageDigest.getInstance("SHA-256");
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
}

