package co.edu.unbosque.parkea.controllers;

import co.edu.unbosque.parkea.model.Auditoria;
import co.edu.unbosque.parkea.model.Usuario;
import co.edu.unbosque.parkea.model.dto.AuditoriaDTO;
import co.edu.unbosque.parkea.service.AuditoriaServiceAPI;
import co.edu.unbosque.parkea.service.UsuarioServiceAPI;
import jakarta.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
    public List<AuditoriaDTO> getAll(){
        List<Auditoria> getall = auditoriaServiceAPI.getAll();
        List<AuditoriaDTO> listaN = new ArrayList<>();
        for (Auditoria a:getall){
            AuditoriaDTO objeto = new AuditoriaDTO(a.getIdInforme(), a.getUsuario().getLogin(), a.getFechaHora().toString(), a.getEvento(), a.getTabla(), a.getIpUsuario());
            listaN.add(objeto);
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
        auditoria.setIpUsuario(obtenerDireccionIPPublica());
        auditoriaServiceAPI.save(auditoria);
    }

    public static String obtenerDireccionIPPublica() {
        String ip = null;
        try {
            URL url = new URL("https://ifconfig.me/ip");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // Tiempo de conexión máximo en milisegundos

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                ip = reader.readLine();
                reader.close();
            }
            connection.disconnect();
        } catch (IOException e) {
            System.out.println("Error al obtener la IP pública: " + e.getMessage());
        }
        return ip;
    }
    @GetMapping("/export-pdf")
    public ResponseEntity<byte[]> exportPdf() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("auditoriaReport", "auditoriaReport.pdf");
        return ResponseEntity.ok().headers(headers).body(usuarioServiceAPI.exportPdf());
    }

    @GetMapping("/export-xls")
    public ResponseEntity<byte[]> exportXls() throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("auditoriaReport" + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(usuarioServiceAPI.exportXls());
    }

}
