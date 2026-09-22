/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_reporte;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import pconexionsql.PconexionSQL;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author CelesteZaldivar
 */
class Visor_De_reportes extends javax.swing.JDialog
{

    public Visor_De_reportes(JRViewer jrv, String str)
    {
        
        con = new Container();
        java.awt.Image icon = Toolkit.getDefaultToolkit().getImage("Images/food.gif");
        setIconImage(icon);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.white);
        setModal(true);
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        jrv.setPreferredSize(new Dimension(pantalla.width - 120, pantalla.height - 150));
        JScrollPane reportScroll = new JScrollPane(jrv);
        JPanel viewer = new JPanel();
        viewer.add(jrv);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent windowevent)
            {
            }

            @Override
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }

            final Visor_De_reportes this$0;

            
            {
                this$0 = Visor_De_reportes.this;
            }
        }
);
        viewer.setBounds(10, 10, pantalla.width - 100, pantalla.height);
        getContentPane().add(viewer);
        setSize(new Dimension(pantalla.width - 50, pantalla.height - 50));
        setJMenuBar(menu());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JMenuBar menu()
    {
        JMenu menuArchivo = new JMenu("ARCHIVO");
        menuArchivo.setMnemonic('A');
        menuArchivo.setBackground(Color.WHITE);
        JMenuItem exit = new JMenuItem("Salir", new ImageIcon("Imagenes/exit.jpg"));
        exit.setMnemonic('S');
        exit.setAccelerator(KeyStroke.getKeyStroke(115, 8));
        exit.setAccelerator(KeyStroke.getKeyStroke(27, 0));
        exit.setBackground(Color.WHITE);
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }

            final Visor_De_reportes this$0;

            
            {
                this$0 = Visor_De_reportes.this;
            }

            
        }
);
        menuArchivo.add(exit);
        JMenuBar barra = new JMenuBar();
        barra.setBackground(Color.WHITE);
        barra.add(menuArchivo);
        return barra;
    }

    JLabel muestra;
    JButton Ok;
    Dimension cuadro;
    Container con;
    
    
}
class ReporteVisor
{
    ReporteVisor(Map parameters, String n)
	{
		
		System.out.println(n);
		try
		{
			URL urlMaestro = getClass().getResource(n);
			JasperReport masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
           // masterReport.setWhenNoDataType(masterReport.WHEN_NO_DATA_TYPE_ALL_SECTIONS_NO_DETAIL);
                        PconexionSQL conexion=new PconexionSQL();
                        Connection con;
                        con = conexion.conexion();
			JasperPrint jasperPrint = null;
			jasperPrint= JasperFillManager.fillReport(masterReport,parameters,con);
			
			//removeBlankPage(jasperPrint.getPages());	
 
			JRViewer jrv = new JRViewer(jasperPrint);
			jrv.setZoomRatio(new Float(0.99));
			
			if(jasperPrint.getPages().isEmpty()==false)
			{
                            Visor_De_reportes visor_De_reportes = new Visor_De_reportes(jrv,"Colinsoft ... Vista de Reporte");
                            //estado=true;
      		}
      		else{
      			// estado=false;
      		}

      		try{
				con.close();
			}catch(SQLException exp){}   		
    	} 
    	catch (JRException j) { 
    		//bd.SavingFails("Error Ejecutando Reporte "+n+ " en Reporte.java "+j);
      		javax.swing.JOptionPane.showMessageDialog(null,"Mensaje de Error:"+j.getMessage(),"Error en Reporte",0);
    	}
	}
}
