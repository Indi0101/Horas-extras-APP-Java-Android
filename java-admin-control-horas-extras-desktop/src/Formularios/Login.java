
package formularios;

import com.mxrck.autocompleter.TextAutoCompleter;
import conexionMYSQL.resgistro_datos_mysql;
import java.awt.Color;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import org.netbeans.lib.awtextra.AbsoluteConstraints;

/**
 *
 * @author CelesteZaldivar
 */
public class Login extends javax.swing.JFrame {
       public resgistro_datos_mysql rgd=new resgistro_datos_mysql();
       public ResultSet rs=null;
     
       public Timer temporizador=new Timer();
       public TimerTask h_verificar_campos_vacios;
       public TextAutoCompleter autoCompleter;
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null); 
        ImageIcon img1=new ImageIcon(getClass().getResource("icono_login.png"));
        ImageIcon img2=new ImageIcon(getClass().getResource("icono_login.png"));
        ImageIcon icono=new ImageIcon("C:\\Users\\Celeste Zaldivar\\Documents\\NetBeansProjects\\proyectoIntento3\\horas_extra_ingreso\\src\\Iconos\\infatlan_icono.png");
        Image imgEscalada = img1.getImage().getScaledInstance(img_login_1.getWidth(),img_login_1.getHeight(), Image.SCALE_SMOOTH);
        Icon  iconoEscalado = new ImageIcon(imgEscalada);
        img_login_1.setIcon(iconoEscalado);
        Image imgEscalada2 = img2.getImage().getScaledInstance(img_login_2.getWidth(),img_login_2.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado2 = new ImageIcon(imgEscalada2);
        img_login_2.setIcon(iconoEscalado2);
        Image imgEscalada3 = icono.getImage().getScaledInstance(lb_icono.getWidth(),lb_icono.getHeight(), Image.SCALE_SMOOTH);
        Icon  iconoEscalado3 = new ImageIcon(imgEscalada3);
        lb_icono.setIcon(iconoEscalado3);
        verficar_campos_vacios();
        temporizador.scheduleAtFixedRate(h_verificar_campos_vacios,0,1*1000);
        
         autoCompleter=new TextAutoCompleter(nombre_login);
         ArrayList<String> l_nombres=llenar_lista_nombre();
        for (String nombre : l_nombres) {
            autoCompleter.addItem(nombre);
        } 
        
      
        //contra_login.setBackground(Color.yellow);
      
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btn_login = new javax.swing.JButton();
        img_login_2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        img_login_1 = new javax.swing.JLabel();
        nombre_login = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_login = new javax.swing.JLabel();
        lb_icono = new javax.swing.JLabel();
        contra_login = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setFocusable(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_login.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btn_login.setText("Entrar");
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_loginMouseClicked(evt);
            }
        });
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        jPanel1.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 510, -1, -1));

        img_login_2.setText("jLabel5");
        jPanel1.add(img_login_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 450, 23, 22));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Contraseña:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 460, 114, -1));

        img_login_1.setText("jLabel5");
        jPanel1.add(img_login_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 390, 23, 22));

        nombre_login.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        nombre_login.setSelectionColor(new java.awt.Color(255, 102, 102));
        jPanel1.add(nombre_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 390, 299, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Nombre de usuario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, -1, -1));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Inicie sesion con su nombre de usuario y contraseña");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, 510, -1));

        txt_login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(txt_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 560, 478, 10));

        lb_icono.setText("jLabel5");
        jPanel1.add(lb_icono, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, 70, 60));

        contra_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contra_loginActionPerformed(evt);
            }
        });
        jPanel1.add(contra_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 450, 300, 25));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/blue-and-white-abstract-background-vector.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseClicked
      verificar_login();
    }//GEN-LAST:event_btn_loginMouseClicked

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
      verificar_login();
    }//GEN-LAST:event_btn_loginActionPerformed

    private void contra_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contra_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contra_loginActionPerformed
    public void verificar_login()
    {
         String datos[]=new String[3];
       datos=obtener_datos_formulario();
       System.out.print("Boton metodo 1");
       if(datos[0]!=null&&datos[1]!=null&&!datos[0].isEmpty()&&!datos[1].isEmpty())
       {
           msj_txt("");
           String id = null;
           
           rs=rgd.verificar_login("empleado",datos[0],datos[1]);
           try {
               while(rs.next())
               {
                    id=rs.getString("id_empleado");   
               }
               if(id!=null&&!id.isEmpty())
               {
                  abrir_clase(datos,id);
               }else
               {
                    msj_txt("El nombre o la contraseña son incorrectos"); 
               }
               System.out.print(""+id);
              
               
           } catch (SQLException ex) {
               Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else{
           msj_txt("Hay campos vacios");
           System.out.print("hay datos vacios");
       }
    }
    public void verficar_campos_vacios(){
        h_verificar_campos_vacios=new TimerTask() {
           @Override
           public void run() {
               if(!nombre_login.getText().isEmpty())
               {
                   img_login_1.setVisible(false);
               }else
               {
                   img_login_1.setVisible(true);
               }
               if(!contra_login.getText().isEmpty())
               {
                   img_login_2.setVisible(false);
               }else
               {
                img_login_2.setVisible(true);
               }
               
           }
       };
    }
    public void msj_txt(String msj)
    {
         txt_login.setText(msj);
         txt_login.setForeground(Color.red);
    }
    public String[] obtener_datos_formulario()
    {
        String datos[]=new String[2];
        if(!this.nombre_login.getText().isEmpty())
        {
           img_login_1.setVisible(false);
        }
        if(!this.nombre_login.getText().isEmpty()&&!this.contra_login.getText().isEmpty())
        {
           datos[0]=this.nombre_login.getText();
           datos[1]=this.contra_login.getText();
        }
        return  datos;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JPasswordField contra_login;
    private javax.swing.JLabel img_login_1;
    private javax.swing.JLabel img_login_2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lb_icono;
    private javax.swing.JTextField nombre_login;
    private javax.swing.JLabel txt_login;
    // End of variables declaration//GEN-END:variables

    private void abrir_clase(String[] datos,String id) {
        String d[]={datos[0],datos[1],id};
        dispose();
        new Ingresar_horas_extras(d).setVisible(true);
        
    }
    public ArrayList<String> llenar_lista_nombre()
     {
         ArrayList<String> lista_n=new ArrayList<>();
       try {
           rs=rgd.traer_valores_todos_condicion("empleado", "Infatlan", "depar_trabajo");
           while(rs.next())
           {
               lista_n.add(rs.getString("nombre_completo_empleado"));
           }
           rgd.cerrar_conexion();
       } catch (SQLException ex) {
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       }
         
         return lista_n;
     }
}
