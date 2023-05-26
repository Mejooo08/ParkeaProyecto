package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Auditoria;
import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.model.dto.AuditoriaDTO;
import co.edu.unbosque.parkea.service.AuditoriaServiceAPI;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/api/Auditoria")
public class AuditoriaRestController {

    public AuditoriaRestController(){
    }

    @Autowired
    private AuditoriaServiceAPI auditoriaServiceAPI;

    @Autowired
    private UsuarioServiceAPI usuarioServiceAPI;

    @GetMapping(value = "/getAll")
    public List<AuditoriaDTO> getAll() {
        List<Auditoria> getAll = auditoriaServiceAPI.getAll();
        List<AuditoriaDTO> listaN = new ArrayList<>();
        try {
            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            for (Auditoria a : getAll) {
                AuditoriaDTO objeto = new AuditoriaDTO(a.getIdInforme(), a.getUsuario().getLogin(), a.getFechaHora().toString(), a.getEvento(), a.getTabla(), ipAddress);
                listaN.add(objeto);
            }
        } catch (UnknownHostException e) {
            // Manejar la excepción en caso de que no se pueda obtener la dirección IP
            e.printStackTrace();
        }
        return listaN;
    }


    public void saveAuditoria(String evento, String tabla, int idUsuario){
        Auditoria auditoria = new Auditoria();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Usuario usuario = usuarioServiceAPI.get(idUsuario);
        auditoria.setUsuario(usuario);
        auditoria.setFechaHora(timestamp+"");
        auditoria.setEvento(evento);
        auditoria.setTabla(tabla);
        auditoria.setIpUsuario("IP");
        auditoriaServiceAPI.save(auditoria);
    }

    public static String obtenerDireccionIP2() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            try {
                InetAddress inetAddress = InetAddress.getByName(servletRequestAttributes.getRequest().getRemoteAddr());
                return inetAddress.getHostAddress();
            } catch (UnknownHostException e) {
                System.out.println("No se pudo objeter IP");
                return "Host Desconocido";
            }
        }
        return null;
    }
}
