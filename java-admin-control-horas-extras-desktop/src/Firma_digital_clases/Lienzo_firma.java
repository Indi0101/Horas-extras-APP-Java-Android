/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Firma_digital_clases;

import Formularios.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author CelesteZaldivar
 */
public class Lienzo_firma extends JComponent{
   private Image image;
   private Graphics gd;
   private int cx,cy,oldx,oldy;
    String n_firma;
    public Lienzo_firma()
    {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter(){
        public void mousePressed(MouseEvent e)
        {
        oldx=e.getX();
        oldy=e.getY();
        }
         });
        
        addMouseMotionListener(new MouseMotionAdapter() {
           public void mouseDragged(MouseEvent e)
           {
               cx=e.getX();
               cy=e.getY();
               if(gd!=null)
               {
                  
                   gd.setColor(Color.BLACK);
                   gd.fillOval(oldx, oldy, 7, 7);
                   repaint();
                   oldx=cx;
                   oldy=cy;
               }
           }
        });
    }
    public void paintComponent(Graphics g)
    {
        if(image==null)
        {
            image=createImage(getSize().width,getSize().height);
            gd=(Graphics)image.getGraphics();
            clear();
        }
        g.drawImage(image, 0,0, null);
       
    }
    public void clear()
    {
        gd.setColor(Color.white);
        gd.fillRect(0, 0, getSize().width, getSize().height);
        gd.setColor(Color.BLACK);
        repaint();
    }
   
    public String guardarImg()
    {
        JPanel frame =new JPanel();
        frame.add(this);
        frame.setSize(580, 300);
        frame.setVisible(true);
        n_firma=JOptionPane.showInputDialog("Ingrese nombre de la firma");
        try{
            BufferedImage img=new BufferedImage(580,300,BufferedImage.TYPE_INT_RGB);
            gd=img.createGraphics();
            frame.paint(gd);
            ImageIO.write(img, "jpg",new File("C:/xampp/htdocs/conexion_hora_extra/firmas_img/firma_"+n_firma+".jpg"));
            System.out.println("Se guardo");
        }
        catch(Exception e)
        {
            System.err.println("algo salio mal"+e);
        }
         return "firma_"+n_firma+".jpg";
    }
  
    
}
