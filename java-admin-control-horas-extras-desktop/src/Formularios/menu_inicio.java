
package Formularios;

import Clases_reporte.Calcular_horas_extra;
import Clases_reporte.Menu_reporte;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import pconexionsql.resgistro_datos_mysql;
/**
 * @author CelesteZaldivar
 */
public class menu_inicio extends javax.swing.JFrame {

 
    public Timer temporizador=new Timer();
    public TimerTask h_verificar_campos_vacios;
    public TextAutoCompleter autoCompleter;
    public resgistro_datos_mysql registar_datos_sql=new resgistro_datos_mysql();
    public ResultSet rs=null;
    public Menu_reporte menu_reporte;
    public categoria_problema cate_pro=new categoria_problema();
    public menu_inicio() {
        initComponents();
        this.setLocationRelativeTo(null);
        ImageIcon img1=new ImageIcon("C:\\Users\\Celeste Zaldivar\\Documents\\NetBeansProjects\\prueba de coexion\\Admin_horas_extras\\src\\iconos\\icono_login.png");
        ImageIcon img2=new ImageIcon("C:\\Users\\Celeste Zaldivar\\Documents\\NetBeansProjects\\prueba de coexion\\Admin_horas_extras\\src\\iconos\\icono_login.png");
        Image imgEscalada = img1.getImage().getScaledInstance(img_login_1.getWidth(),img_login_1.getHeight(), Image.SCALE_SMOOTH);
        Icon  iconoEscalado = new ImageIcon(imgEscalada);
        img_login_1.setIcon(iconoEscalado);
        Image imgEscalada2 = img2.getImage().getScaledInstance(img_login_2.getWidth(),img_login_2.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado2 = new ImageIcon(imgEscalada2);
        img_login_2.setIcon(iconoEscalado2);
        verficar_campos_vacios();
        temporizador.scheduleAtFixedRate(h_verificar_campos_vacios,0,1*1000);
      
     
      autoCompleter=new TextAutoCompleter(nom_emple_login);
      ArrayList<String> l_nombres=llenar_lista_nombre();
        for (String nombre : l_nombres) {
            autoCompleter.addItem(nombre);
        }
      
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        nom_emple_login = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btn_entrar = new javax.swing.JButton();
        img_login_1 = new javax.swing.JLabel();
        img_login_2 = new javax.swing.JLabel();
        eti_login = new javax.swing.JLabel();
        contra_login = new javax.swing.JPasswordField();
        btn_cerrar_sesion = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        icono_hora_extra = new javax.swing.JLabel();
        icono_n_emple = new javax.swing.JLabel();
        icono_n_puesto = new javax.swing.JLabel();
        icono_n_peticion = new javax.swing.JLabel();
        icono_n_agencia = new javax.swing.JLabel();
        icono_n_problema = new javax.swing.JLabel();
        icono_reportes = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        eti_mensaje = new javax.swing.JLabel();
        hora_menu_ini = new javax.swing.JLabel();
        fecha_menu_ini = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1330, 845));
        setResizable(false);
        setSize(new java.awt.Dimension(1330, 845));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153), 2));
        jPanel2.setForeground(java.awt.Color.white);
        jPanel2.setPreferredSize(new java.awt.Dimension(250, 840));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(java.awt.Color.white);
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Inciar sesion");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 250, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Nombre:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 87, 29));

        nom_emple_login.setBackground(java.awt.Color.white);
        nom_emple_login.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        nom_emple_login.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 102), 1, true));
        jPanel2.add(nom_emple_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 200, 30));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Contraseña:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 87, 29));

        btn_entrar.setBackground(java.awt.Color.white);
        btn_entrar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btn_entrar.setForeground(new java.awt.Color(255, 102, 102));
        btn_entrar.setText("Entrar");
        btn_entrar.setBorder(null);
        btn_entrar.setBorderPainted(false);
        btn_entrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_entrarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_entrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, 80, 32));
        jPanel2.add(img_login_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 480, 20, 20));
        jPanel2.add(img_login_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 20, 20));

        eti_login.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        eti_login.setForeground(new java.awt.Color(255, 255, 255));
        eti_login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(eti_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 640, 250, 20));

        contra_login.setBackground(new java.awt.Color(255, 255, 255));
        contra_login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
        contra_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contra_loginActionPerformed(evt);
            }
        });
        jPanel2.add(contra_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 200, 30));

        btn_cerrar_sesion.setBackground(java.awt.Color.white);
        btn_cerrar_sesion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_cerrar_sesion.setForeground(new java.awt.Color(255, 102, 102));
        btn_cerrar_sesion.setText("Cerrar sesion");
        btn_cerrar_sesion.setBorder(null);
        btn_cerrar_sesion.setBorderPainted(false);
        btn_cerrar_sesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cerrar_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrar_sesionActionPerformed(evt);
            }
        });
        jPanel2.add(btn_cerrar_sesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 720, 130, 32));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rrhh_a.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153)));
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 250, 760));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("Tegucigalpa  2280-1010");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 830));

        jPanel4.setBackground(java.awt.Color.white);
        jPanel4.setForeground(java.awt.Color.white);
        jPanel4.setAutoscrolls(true);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(java.awt.Color.white);
        jLabel4.setFont(new java.awt.Font("Alef", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Mas opciones");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 1080, 23));

        icono_hora_extra.setBackground(new java.awt.Color(255, 255, 255));
        icono_hora_extra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono_hora_extra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/hora_extra.png"))); // NOI18N
        icono_hora_extra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_hora_extra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                icono_hora_extraMouseMoved(evt);
            }
        });
        icono_hora_extra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_hora_extraMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                icono_hora_extraMouseExited(evt);
            }
        });
        jPanel4.add(icono_hora_extra, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 100, 80));

        icono_n_emple.setBackground(new java.awt.Color(255, 255, 255));
        icono_n_emple.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono_n_emple.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/new_emple.png"))); // NOI18N
        icono_n_emple.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_n_emple.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                icono_n_empleMouseMoved(evt);
            }
        });
        icono_n_emple.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_n_empleMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                icono_n_empleMouseExited(evt);
            }
        });
        jPanel4.add(icono_n_emple, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 100, 80));

        icono_n_puesto.setBackground(new java.awt.Color(255, 255, 255));
        icono_n_puesto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono_n_puesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/new_puesto_trabajo.png"))); // NOI18N
        icono_n_puesto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_n_puesto.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                icono_n_puestoMouseMoved(evt);
            }
        });
        icono_n_puesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_n_puestoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                icono_n_puestoMouseExited(evt);
            }
        });
        jPanel4.add(icono_n_puesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 130, 100, 80));

        icono_n_peticion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono_n_peticion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/new_peticion.png"))); // NOI18N
        icono_n_peticion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_n_peticion.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                icono_n_peticionMouseMoved(evt);
            }
        });
        icono_n_peticion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_n_peticionMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                icono_n_peticionMouseExited(evt);
            }
        });
        jPanel4.add(icono_n_peticion, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 100, 80));

        icono_n_agencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono_n_agencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/new_agencia.png"))); // NOI18N
        icono_n_agencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_n_agencia.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                icono_n_agenciaMouseMoved(evt);
            }
        });
        icono_n_agencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_n_agenciaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                icono_n_agenciaMouseExited(evt);
            }
        });
        jPanel4.add(icono_n_agencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 100, 80));

        icono_n_problema.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono_n_problema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/new_problema.png"))); // NOI18N
        icono_n_problema.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_n_problema.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                icono_n_problemaMouseMoved(evt);
            }
        });
        icono_n_problema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_n_problemaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                icono_n_problemaMouseExited(evt);
            }
        });
        jPanel4.add(icono_n_problema, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 590, 100, 80));

        icono_reportes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono_reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/reporte.png"))); // NOI18N
        icono_reportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icono_reportes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                icono_reportesMouseMoved(evt);
            }
        });
        icono_reportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono_reportesMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                icono_reportesMouseExited(evt);
            }
        });
        jPanel4.add(icono_reportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 590, 100, 80));

        jLabel9.setBackground(java.awt.Color.white);
        jLabel9.setFont(new java.awt.Font("Alef", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Crear una peticion");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 1080, -1));

        jSeparator1.setBackground(new java.awt.Color(255, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(255, 153, 153));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 768, 1090, 10));

        jSeparator2.setBackground(new java.awt.Color(255, 153, 153));
        jSeparator2.setForeground(new java.awt.Color(255, 153, 153));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 1080, 10));

        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reportes");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 670, 100, 20));

        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Revisar horas extra");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 130, -1));

        jLabel3.setForeground(new java.awt.Color(255, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nuevo problema");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 670, 100, -1));

        jLabel5.setForeground(new java.awt.Color(255, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Crear puesto de trabajo");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 210, 150, -1));

        jLabel6.setForeground(new java.awt.Color(255, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nueva agencia");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, 100, -1));

        jLabel7.setForeground(new java.awt.Color(255, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Nuevo empleado");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, 120, -1));

        eti_mensaje.setBackground(new java.awt.Color(51, 51, 51));
        eti_mensaje.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        eti_mensaje.setForeground(new java.awt.Color(255, 102, 102));
        jPanel4.add(eti_mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 620, 20));

        hora_menu_ini.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        hora_menu_ini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hora_menu_ini.setText("jLabel10");
        jPanel4.add(hora_menu_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, 150, 30));

        fecha_menu_ini.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        fecha_menu_ini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fecha_menu_ini.setText("jLabel10");
        jPanel4.add(fecha_menu_ini, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 150, 30));

        jSeparator3.setBackground(new java.awt.Color(255, 153, 153));
        jSeparator3.setForeground(new java.awt.Color(255, 153, 153));
        jPanel4.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 490, 1090, 10));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("La Ceiba  2480-1010");
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 780, 120, 30));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("San Pedro Sula  2580-1010");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 780, 170, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 1080, 830));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void icono_hora_extraMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_hora_extraMouseMoved
        this.icono_hora_extra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
    }//GEN-LAST:event_icono_hora_extraMouseMoved

    private void icono_hora_extraMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_hora_extraMouseExited
        this.icono_hora_extra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_icono_hora_extraMouseExited

    private void icono_n_empleMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_empleMouseMoved
        this.icono_n_emple.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
    }//GEN-LAST:event_icono_n_empleMouseMoved

    private void icono_n_empleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_empleMouseExited
        this.icono_n_emple.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_icono_n_empleMouseExited

    private void icono_n_puestoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_puestoMouseMoved
        this.icono_n_puesto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
    }//GEN-LAST:event_icono_n_puestoMouseMoved

    private void icono_n_puestoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_puestoMouseExited
        this.icono_n_puesto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_icono_n_puestoMouseExited

    private void icono_n_peticionMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_peticionMouseMoved
        this.icono_n_peticion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
    }//GEN-LAST:event_icono_n_peticionMouseMoved

    private void icono_n_peticionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_peticionMouseExited
        this.icono_n_peticion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_icono_n_peticionMouseExited

    private void icono_n_agenciaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_agenciaMouseMoved
       this.icono_n_agencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
    }//GEN-LAST:event_icono_n_agenciaMouseMoved

    private void icono_n_agenciaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_agenciaMouseExited
        this.icono_n_agencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_icono_n_agenciaMouseExited

    private void icono_n_problemaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_problemaMouseMoved
        this.icono_n_problema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
    }//GEN-LAST:event_icono_n_problemaMouseMoved

    private void icono_n_problemaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_problemaMouseExited
        this.icono_n_problema.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_icono_n_problemaMouseExited

    private void icono_reportesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_reportesMouseMoved
        this.icono_reportes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
    }//GEN-LAST:event_icono_reportesMouseMoved

    private void icono_reportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_reportesMouseExited
         this.icono_reportes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_icono_reportesMouseExited

    private void icono_hora_extraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_hora_extraMouseClicked
       if(eti_mensaje.getText().isEmpty())
       {
           JOptionPane.showMessageDialog(this, "Debe iniciar sesion");
           
       }else{
        Calcular_horas_extra c_h=new Calcular_horas_extra(new javax.swing.JDialog(),true);
        c_h.setVisible(true);
       }
       
       
    }//GEN-LAST:event_icono_hora_extraMouseClicked

    private void icono_n_empleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_empleMouseClicked
       if(eti_mensaje.getText().isEmpty())
       {
           JOptionPane.showMessageDialog(this, "Debe iniciar sesion");
           
       }else
       {
            Ingresar_empleados i_e=new Ingresar_empleados();
            i_e.setVisible(true);
       }
        
    }//GEN-LAST:event_icono_n_empleMouseClicked

    private void icono_n_puestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_puestoMouseClicked
        if(eti_mensaje.getText().isEmpty())
       {
           JOptionPane.showMessageDialog(this, "Debe iniciar sesion");
           
       }else{
         Ingresar_puestos i_p =new Ingresar_puestos();
        i_p.setVisible(true);
        }
       
    }//GEN-LAST:event_icono_n_puestoMouseClicked

    private void icono_n_peticionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_peticionMouseClicked
       
        if(eti_mensaje.getText().isEmpty())
       {
           JOptionPane.showMessageDialog(this, "Debe iniciar sesion");
           
       }else
        {
            Crear_peticion c_p=new Crear_peticion();
        c_p.setVisible(true);
        }
        
    }//GEN-LAST:event_icono_n_peticionMouseClicked

    private void icono_n_agenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_agenciaMouseClicked
       
       if(eti_mensaje.getText().isEmpty())
       {
           JOptionPane.showMessageDialog(this, "Debe iniciar sesion");
           
       }else
       {
          Crear_agencia c_a=new Crear_agencia();
          c_a.setVisible(true);
       }
      
    }//GEN-LAST:event_icono_n_agenciaMouseClicked

    private void icono_n_problemaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_n_problemaMouseClicked
       if(eti_mensaje.getText().isEmpty())
       {
           JOptionPane.showMessageDialog(this, "Debe iniciar sesion");
           
       }else
       {
           
           cate_pro.setVisible(true);
       }
       
    }//GEN-LAST:event_icono_n_problemaMouseClicked

    private void btn_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_entrarActionPerformed
       try {
           String id_emple="";
           String datos[]=obtener_datos_login();
           for (String d : datos) {
               if(d==null||d.isEmpty())
               {
                   eti_login.setText("Hay campos vacios");
                   return;
               }
           }
           rs=registar_datos_sql.sql("SELECT id_empleado FROM empleado WHERE nombre_completo_empleado='"+datos[0]+"' AND contra_empleado='"+datos[1]+"'");
           while(rs.next())
           {
              id_emple=rs.getString("id_empleado");
           }
           if(!id_emple.isEmpty())
           {
               eti_mensaje.setText("Bienvedino: "+datos[0]);
               eti_login.setText("");
               nom_emple_login.setText("");
               contra_login.setText("");
           }else
           {
                eti_login.setText("Usuario no encontrado");
           }
       } catch (SQLException ex) {
           Logger.getLogger(menu_inicio.class.getName()).log(Level.SEVERE, null, ex);
       }
      
    }//GEN-LAST:event_btn_entrarActionPerformed

    private void icono_reportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono_reportesMouseClicked
      
          if(eti_mensaje.getText().isEmpty())
       {
           JOptionPane.showMessageDialog(this, "Debe iniciar sesion");
           
       }else
       {
           menu_reporte=new Menu_reporte();
           menu_reporte.setVisible(true);
       }
       
    }//GEN-LAST:event_icono_reportesMouseClicked

    private void btn_cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrar_sesionActionPerformed
        eti_mensaje.setText("");
    }//GEN-LAST:event_btn_cerrar_sesionActionPerformed

    private void contra_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contra_loginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contra_loginActionPerformed
    public String[] obtener_datos_login()
    {
        String datos[]=new String[2];
       if(!nom_emple_login.getText().toString().isEmpty()&&!contra_login.getText().toString().isEmpty())
       {
           eti_login.setText("");
           datos[0]=nom_emple_login.getText();
           datos[1]=contra_login.getText().toString();
          
       }else
       {
          eti_login.setText("Hay campos vacios");
          
       }
       return datos;
    }
     public void verficar_campos_vacios(){
        h_verificar_campos_vacios=new TimerTask() {
           @Override
           public void run() {
               if(!nom_emple_login.getText().isEmpty())
               {
                   img_login_2.setVisible(false);
               }else
               {
                   img_login_2.setVisible(true);
               }
               if(!contra_login.getText().isEmpty())
               {
                   img_login_1.setVisible(false);
               }else
               {
                    img_login_1.setVisible(true);
               }
               
                Date date = new Date();
            DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            String hora=""+formatoHora.format(date);
            DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
            String fecha=""+formatoFecha.format(date);
            String f=formatoFecha.format(date);
            fecha_menu_ini.setText("Fecha: "+fecha);
            hora_menu_ini.setText("Hora: "+hora);
               
           }
       };
    }
     public ArrayList<String> llenar_lista_nombre()
     {
         ArrayList<String> lista_n=new ArrayList<>();
       try {
           rs=registar_datos_sql.traer_valores_todos_condicion("empleado", "RRHH", "depar_trabajo");
           while(rs.next())
           {
               lista_n.add(rs.getString("nombre_completo_empleado"));
           }
       } catch (SQLException ex) {
           Logger.getLogger(menu_inicio.class.getName()).log(Level.SEVERE, null, ex);
       }
         
         return lista_n;
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu_inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu_inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar_sesion;
    private javax.swing.JButton btn_entrar;
    private javax.swing.JPasswordField contra_login;
    private javax.swing.JLabel eti_login;
    private javax.swing.JLabel eti_mensaje;
    private javax.swing.JLabel fecha_menu_ini;
    private javax.swing.JLabel hora_menu_ini;
    private javax.swing.JLabel icono_hora_extra;
    private javax.swing.JLabel icono_n_agencia;
    private javax.swing.JLabel icono_n_emple;
    private javax.swing.JLabel icono_n_peticion;
    private javax.swing.JLabel icono_n_problema;
    private javax.swing.JLabel icono_n_puesto;
    private javax.swing.JLabel icono_reportes;
    private javax.swing.JLabel img_login_1;
    private javax.swing.JLabel img_login_2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField nom_emple_login;
    // End of variables declaration//GEN-END:variables
}
