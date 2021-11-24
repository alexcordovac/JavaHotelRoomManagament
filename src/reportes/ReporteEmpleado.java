package reportes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import conexion.Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.jfree.io.IOUtils;

public class ReporteEmpleado {

    public void generarReporte(String idEmpleado) throws JRException, SQLException, URISyntaxException {
        //Ruta del reporte

        InputStream jasperStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("reportes/empleado_reporte.jasper");
        JasperReport report = (JasperReport) JRLoader.loadObject(jasperStream);

        //HashMap para guardar la generarReporte
        Map<String, Object> map = new HashMap();
        map.put("idEmpleado", idEmpleado);

        //Conexion
        Connection con = Conexion.conectar();

        //"Llenamos el reporte"
        JasperPrint prin = JasperFillManager.fillReport(report, map, con);

        //Lo guardamos en pdf
        JasperExportManager.exportReportToPdfFile(prin, "empleado_" + idEmpleado + ".pdf");
        System.out.println("Reporte exportado.");

        //Cerrar conexiones
        con.close();
        Conexion.desconectar();

        //Mostrar pdf
        JasperViewer ver = new JasperViewer(prin, false);
        ver.setVisible(true);
    }
}
