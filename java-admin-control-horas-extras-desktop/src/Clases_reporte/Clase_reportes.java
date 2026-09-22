/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_reporte;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import pconexionsql.PconexionSQL;

/**
 *
 * @author CelesteZaldivar
 */
public class Clase_reportes {
    private final PconexionSQL conexion=new PconexionSQL();
    public  Connection con;
    public Clase_reportes()
    {
    }
    public void _peticiones_emple()
    {
        try {
            con = conexion.conexion();
           JasperReport report=null;
           String path="src\\Reportes\\Peticiones.jasper";
           report =(JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jp=JasperFillManager.fillReport(report, null, con);
            JasperViewer viewer=new JasperViewer(jp,false);
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
           viewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void _horas_extra_emple()
    {
        try {
            con = conexion.conexion();
           JasperReport report=null;
           String path="src\\Reportes\\Horas_extra_emple.jasper";
           report =(JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jp=JasperFillManager.fillReport(report, null, con);
            JasperViewer viewer=new JasperViewer(jp,false);
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
           viewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void una_fila_horas_extra_emple(String id,String n)
    {
         Map parametros=new HashMap();
         int id_n=Integer.parseInt(id);
          parametros.put("id_peticion",id_n);
          parametros.put("nombre_emple",n);
         
        try {
            con = conexion.conexion();
           JasperReport report=null;
           String path="src\\Reportes\\Traer_una_fila.jasper";
           report =(JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jp=JasperFillManager.fillReport(report, parametros, con);
            JasperViewer viewer=new JasperViewer(jp,false);
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
           viewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void _hora_entrada_salida()
    {
       
        try {
            con = conexion.conexion();
           JasperReport report=null;
           String path="src\\Reportes\\Marca_entrada_salida_emple.jasper";
           report =(JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jp=JasperFillManager.fillReport(report, null, con);
            JasperViewer viewer=new JasperViewer(jp,false);
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
           viewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void mostrar_hoja_sysaid(String id)
    {
        Map parametros=new HashMap();
         int id_n=Integer.parseInt(id);
          parametros.put("id_sysaid",id_n);
        
         
        try {
            con = conexion.conexion();
           JasperReport report=null;
           String path="src\\Reportes\\SYSAID.jasper";
           report =(JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jp=JasperFillManager.fillReport(report, parametros, con);
            JasperViewer viewer=new JasperViewer(jp,false);
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
           viewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void reporte_hoja_sysaid()
    {
        
        try {
            con = conexion.conexion();
           JasperReport report=null;
           String path="src\\Reportes\\Repor_sysaid.jasper";
           report =(JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jp=JasperFillManager.fillReport(report, null, con);
            JasperViewer viewer=new JasperViewer(jp,false);
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
           viewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Clase_reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
