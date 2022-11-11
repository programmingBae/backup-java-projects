package sample;

import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sample.utility.JDBCConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    public void showReport(ActionEvent actionEvent) throws JRException {
        JasperPrint jp = new JasperPrint();
        Map param = new HashMap();
        Connection tes = JDBCConnection.getConnection();
        if (tes==null){
            System.out.println("failed");
        } else {
            System.out.println("success");
        }
        jp = JasperFillManager.fillReport("reportFile/Laporan1.jasper",param ,JDBCConnection.getConnection());
        JasperViewer viewer = new JasperViewer(jp,false);
        viewer.setTitle("laporan");
        viewer.setVisible(true);

    }
}
