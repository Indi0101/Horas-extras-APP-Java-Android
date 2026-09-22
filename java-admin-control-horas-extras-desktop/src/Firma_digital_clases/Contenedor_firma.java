
package Firma_digital_clases;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author CelesteZaldivar
 */
public class Contenedor_firma {
    public JButton limpiar,guardar,salir;
    public JLabel lb;
    public Lienzo_firma lienzo;
    //JFrame frame;
    JDialog frame;
    public String n_firma;
    public void mostrar()
{
    
    ///frame =new JFrame("Crear firma digital");
    frame= new JDialog();
    frame.setResizable(false); 
    Container cont=frame.getContentPane();
    cont.setLayout(new BorderLayout());
    lienzo=new Lienzo_firma();
    cont.add(lienzo,BorderLayout.CENTER);
    JPanel jp=new JPanel();
    limpiar=new JButton("Limpiar");
    guardar=new JButton("Guardar");
    salir=new JButton("Salir");
    lb=new JLabel();
    limpiar.addActionListener(actionListener);
    guardar.addActionListener(actionListener);
    salir.addActionListener(actionListener);
   
    jp.add(limpiar);
    jp.add(guardar);
    jp.add(salir);
    jp.add(lb);
  
   cont.add(jp,BorderLayout.SOUTH);
 
    frame.setSize(600, 400);
    frame.setModal(true);
    frame.setLocationRelativeTo(null); 
    //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setVisible(true);
  
    
  
}
ActionListener actionListener =new ActionListener() {
        @Override
    public void actionPerformed(ActionEvent ae) {
     if(ae.getSource()==limpiar)
     {
     lienzo.clear();
     }
     if(ae.getSource()==guardar)
     {  
        n_firma=lienzo.guardarImg();
        
        System.err.println("Contenedor_firma:"+n_firma);
        lb.setText("Se guardo correctamente");
     
     }
     if(ae.getSource()==salir)
     {
        frame.dispose();
     }
    
        }
    };
public static void main(String [] args)
{
     
   new Contenedor_firma().mostrar();
}
public Contenedor_firma()
{  }

}
