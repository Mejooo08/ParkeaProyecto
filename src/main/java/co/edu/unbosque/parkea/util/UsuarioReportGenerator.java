package co.edu.unbosque.parkea.util;

import co.edu.unbosque.parkea.model.Usuario;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UsuarioReportGenerator {
    /**
     * Este método se usa para exportar el reporte PDF generado
     * @param list
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    public byte[] exportToPdf(List<Usuario> list) throws JRException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport(list));
    }

    /**
     * Este método se usa para exportar el reporte EXCEL generado
     * @param list
     * @return
     * @throws JRException
     * @throws FileNotFoundException
     */
    public byte[] exportToXls(List<Usuario> list) throws JRException, FileNotFoundException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(list)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }

    /**
     * Este método se usa para traer el reporte
     * @param list
     * @return
     * @throws FileNotFoundException
     * @throws JRException
     */
    private JasperPrint getReport(List<Usuario> list) throws FileNotFoundException, JRException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("usuarioData", new JRBeanCollectionDataSource(list));

        JasperPrint report = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("classpath:reportes/usuarioReport.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());

        return report;
    }
}
