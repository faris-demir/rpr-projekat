package ba.unsa.etf.rpr.projekat;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class SalesReport extends JFrame {
    public void showReport(Connection conn) throws JRException {
        InputStream file = getClass().getResourceAsStream("/reports/sales_history.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(file);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        viewer.setZoomRatio((float) 0.6);
        this.add(viewer);
        this.setSize(950, 650);
        this.setVisible(true);
    }
}
