package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Auditoria;
import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.model.dto.AuditoriaDTO;
import co.edu.unbosque.parkea.service.AuditoriaServiceAPI;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditoriaRestController {

    public AuditoriaRestController(){
    }

    private AuditoriaServiceAPI auditoriaServiceAPI;

    private UsuarioServiceAPI usuarioServiceAPI;

    @GetMapping(value = "/getAll")
    public List<AuditoriaDTO> getAll(){
        List<Auditoria> getall = auditoriaServiceAPI.getAll();
        List<AuditoriaDTO> listaN = new ArrayList<>();
        for (Auditoria a:getall){
            AuditoriaDTO objeto = new AuditoriaDTO(a.getIdInforme(), a.getUsuario().toString(), a.getFechaHora().toString(), a.getEvento(), a.getTabla(), a.getIpUsuario());
            listaN.add(objeto);
        }
        return listaN;
    }

    public void saveAuditoria(String evento, String tabla, int idUsuario){
        Auditoria auditoria = new Auditoria();

        Usuario usuario = usuarioServiceAPI.get(idUsuario);
        auditoria.setUsuario(usuario);
        auditoria.setFechaHora((java.sql.Date) new Date());
        auditoria.setEvento(evento);
        auditoria.setTabla(tabla);
        auditoria.setIpUsuario("ipprueba");
    }
}
