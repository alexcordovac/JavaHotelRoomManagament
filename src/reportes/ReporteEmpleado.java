
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

public class ReporteEmpleado {
    
    public void generarReporte(String idEmpleado) throws JRException, SQLException  {
        //Ruta del reporte
        String reporte = "src/reportes/empleado_reporte.jasper";
            
        //HashMap para guardar la generarReporte
        Map<String, Object> map = new HashMap();
        map.put("idEmpleado", idEmpleado);
        
        //Conexion
        Connection con = Conexion.conectar();
        
        //"Llenamos el reporte"
        JasperPrint prin = JasperFillManager.fillReport(reporte, map, con);

        //Lo guardamos en pdf
        JasperExportManager.exportReportToPdfFile(prin, "empleado_"+idEmpleado+".pdf");
        System.out.println("Reporte exportado.");
        
        //Cerrar conexiones
        con.close();
        Conexion.desconectar();
        
        //Mostrar pdf
        JasperViewer ver = new JasperViewer(prin, false);
        ver.setVisible(true);
    }
}
