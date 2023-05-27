package co.edu.unbosque.parkea.service;

import co.edu.unbosque.parkea.commons.GenericServiceAPI;
import co.edu.unbosque.parkea.model.Usuario;

public interface UsuarioServiceAPI extends GenericServiceAPI<Usuario,Integer> {
    Usuario login(String correo, String clave);

    int validarEstado(Usuario u);

    String hashearContra(String contra);

    String generarContrasena(int longitud);
}
