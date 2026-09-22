/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Firma_digital_clases.Contenedor_firma;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import pconexionsql.resgistro_datos_mysql;

/**
 *
 * @author CelesteZaldivar
 */

public final class Ingresar_empleados extends javax.swing.JDialog {
    public resgistro_datos_mysql rdm=new resgistro_datos_mysql();
    public ResultSet rs=null;
    public ArrayList<String> contenido_combo=new ArrayList<>();
    public String id_seleccionado;
    public int size_lista=0;
    public String n_firma;
    public Timer temporizador;
    public JFormattedTextField salario = new JFormattedTextField ();
    public JPasswordField contra_ingresar_empleados=new JPasswordField();
    public TimerTask verificar_hora_correcta;
    public DefaultTableModel tabla = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return true;
        }
    };
   
    public Ingresar_empleados() {
        initComponents();
        this.setModal(true); 
        this.setLocationRelativeTo(null); 
        hilo_hora_fecha();
      
        llenar_combo_departamento();
        cargarTitulosColumas();
        cargarTabla();
        this.combo_depar.addActionListener((ActionEvent arg0) -> {
            llenar_combo_puesto(combo_depar.getSelectedItem().toString());
            });
        llenar_combo_puesto(combo_depar.getSelectedItem().toString());
       
       temporizador = new Timer();
       temporizador.scheduleAtFixedRate(verificar_hora_correcta,0,1*1000);
       text_solo_num();
     
     
    }
    public void text_solo_num()
    {
      
         salario.setValue(0);
        jPanel1.add(salario,new AbsoluteConstraints(132, 290, 140, 20));
        jPanel1.add(contra_ingresar_empleados,new AbsoluteConstraints(132, 470, 190, 20));

    }
     public void hilo_hora_fecha()
    {
        this.verificar_hora_correcta = new TimerTask(){
            @Override
            public void run() {
             Date date = new Date();
            DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            String hora=""+formatoHora.format(date);
            DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
            String fecha=""+formatoFecha.format(date);
            fecha_ingreso_empleado.setText(fecha);
            hora_ingreso_empleado.setText(hora);
            
            
            if(casada.isSelected()==true)
            {
                soltero.getModel().setSelected(false);
                viudo.getModel().setSelected(false);
                uni_libre.getModel().setSelected(false);
            }
            if(soltero.isSelected()==true)
            {
                casada.getModel().setSelected(false);
                viudo.getModel().setSelected(false);
                uni_libre.getModel().setSelected(false);
            }
            if(viudo.isSelected()==true)
            {
                soltero.getModel().setSelected(false);
                casada.getModel().setSelected(false);
                uni_libre.getModel().setSelected(false);
            }
            if(uni_libre.isSelected()==true)
            {
                soltero.getModel().setSelected(false);
                viudo.getModel().setSelected(false);
                casada.getModel().setSelected(false);
            }
            if(femenino.isSelected()==true)
            {
                masculino.getModel().setSelected(false);
                
            }
            if(masculino.isSelected()==true)
            {
                femenino.getModel().setSelected(false);
                
            }
            
            }
        };
    }
    public void cargarTitulosColumas(){
     
        tabla.addColumn("Id");
        tabla.addColumn("Nombre completo");
        tabla.addColumn("Numero dentidad");
        tabla.addColumn("Direccion");
        tabla.addColumn("Celular");
        tabla.addColumn("Correo");
        tabla.addColumn("RTN");
        tabla.addColumn("Estado civil");
        tabla.addColumn("Genero");
        tabla.addColumn("Fecha nacimiento");
        tabla.addColumn("Puesto");
        tabla.addColumn("Salario");
        tabla.addColumn("Contraseña");
        tabla.addColumn("Fecha ingreso");
        tabla.addColumn("Hora de ingreso");
        tabla.addColumn("Firma");
        
        this.tbl_empleados.setModel(tabla);
        this.tbl_empleados.setAutoscrolls (true);
        this.tbl_empleados.getColumn(this.tbl_empleados.getModel().getColumnName(0)).setMaxWidth(400);
    } 
    private void cargarTabla() {
        if(this.tbl_empleados.getRowCount()!=0)
        {
            tabla.setRowCount(0);
            llenar_tabla();
        }else
        {
             llenar_tabla();
        }    
    }
    public void llenar_tabla()
    {
         String datos[]=new String[16];
         try {
            rs=rdm.traer_datos_todos("empleado");
            while(rs.next())
            {
                datos[0]=rs.getString("id_empleado");
                datos[1]=rs.getString("nombre_completo_empleado");
                datos[2]=rs.getString("identidad_empleado");
                datos[3]=rs.getString("direccion_empleado");
                datos[4]=rs.getString("cel_empleado");
                datos[5]=rs.getString("correo__empleado");
                datos[6]=rs.getString("rtn__empleado");
                datos[7]=rs.getString("estado_civil_empleado");
                datos[8]=rs.getString("genero_empleado");
                datos[9]=rs.getString("fecha_nacimiento_empleado");
                datos[10]=rs.getString("puesto_empleado");
                datos[11]=rs.getString("salario_empleado");
                datos[12]=rs.getString("contra_empleado");
                datos[13]=rs.getString("fecha_de_ingreso_empleado");
                datos[14]=rs.getString("hora_de_ingreso_empleado");
                datos[15]=rs.getString("firma_emple");
                
                tabla.addRow(datos);    
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tel = new javax.swing.JTextField();
        correo = new javax.swing.JTextField();
        nombre_empleado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fecha_nacimiento = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        puestos = new javax.swing.JComboBox<>();
        casada = new javax.swing.JCheckBox();
        soltero = new javax.swing.JCheckBox();
        viudo = new javax.swing.JCheckBox();
        uni_libre = new javax.swing.JCheckBox();
        femenino = new javax.swing.JCheckBox();
        masculino = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_empleados = new javax.swing.JTable();
        btn_guardar_ingresar_empleados = new javax.swing.JButton();
        btn_limpiar_ingresar_empleados = new javax.swing.JButton();
        btn_modificar_ingresar_empleados = new javax.swing.JButton();
        btn_eliminar_ingresar_empleados = new javax.swing.JButton();
        btn_salir_ingresar_empleados = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        hora_ingreso_empleado = new javax.swing.JTextField();
        fecha_ingreso_empleado = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btn_firma = new javax.swing.JButton();
        img_firma = new javax.swing.JLabel();
        btn_traer_firma = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        combo_depar = new javax.swing.JComboBox<>();
        rtn = new javax.swing.JTextField();
        num_identidad = new javax.swing.JTextField();
        direccion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 800));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 800));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 250, 800, -1));
        jPanel1.add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 164, 800, -1));
        jPanel1.add(nombre_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 38, 510, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Nombre completo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 42, 114, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Num. Identidad");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 84, 114, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("RTN");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 126, 120, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Correo");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 168, 120, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Direccion");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 210, 120, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Salario");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 294, 120, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Telefono");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 252, 120, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText("Fecha de nacimiento");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 332, 120, 20));
        jPanel1.add(fecha_nacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 332, 140, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Puesto de trabajo");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 130, 20));

        jPanel1.add(puestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 170, -1));

        casada.setText("Casado");
        jPanel1.add(casada, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 379, -1, -1));

        soltero.setText("Soltero");
        jPanel1.add(soltero, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 379, -1, -1));

        viudo.setText("Viudo");
        jPanel1.add(viudo, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 379, -1, -1));

        uni_libre.setText("Union libre");
        jPanel1.add(uni_libre, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 379, -1, -1));

        femenino.setText("Femenino");
        femenino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femeninoActionPerformed(evt);
            }
        });
        jPanel1.add(femenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, -1, -1));

        masculino.setText("Masculino");
        jPanel1.add(masculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Estado civil");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 383, 128, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Genero");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 110, -1));

        panel1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 423, -1, -1));

        jLabel13.setFont(new java.awt.Font("Microsoft YaHei", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Ingresar nuevos usuarios");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 944, -1));

        tbl_empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_empleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_empleados);

        btn_guardar_ingresar_empleados.setText("Guardar");
        btn_guardar_ingresar_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accion_click_guardar(evt);
            }
        });

        btn_limpiar_ingresar_empleados.setText("Limpiar");
        btn_limpiar_ingresar_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accion_click_limpiar(evt);
            }
        });

        btn_modificar_ingresar_empleados.setText("Modificar");
        btn_modificar_ingresar_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accion_click_modificar(evt);
            }
        });

        btn_eliminar_ingresar_empleados.setText("Eliminar");
        btn_eliminar_ingresar_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accion_click_eliminar(evt);
            }
        });

        btn_salir_ingresar_empleados.setText("Salir");
        btn_salir_ingresar_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accion_click_salir(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_guardar_ingresar_empleados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_limpiar_ingresar_empleados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_modificar_ingresar_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_eliminar_ingresar_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir_ingresar_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 499, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar_ingresar_empleados)
                    .addComponent(btn_limpiar_ingresar_empleados)
                    .addComponent(btn_modificar_ingresar_empleados)
                    .addComponent(btn_eliminar_ingresar_empleados)
                    .addComponent(btn_salir_ingresar_empleados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 930, 300));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Fecha de ingreso");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, 100, 20));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Hora de ingreso");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, 100, 20));

        hora_ingreso_empleado.setEditable(false);
        hora_ingreso_empleado.setText("jTextField1");
        jPanel1.add(hora_ingreso_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 40, 170, -1));

        fecha_ingreso_empleado.setEditable(false);
        fecha_ingreso_empleado.setText("jTextField1");
        jPanel1.add(fecha_ingreso_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, 171, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Contraseña");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 110, -1));

        btn_firma.setText("Crear firma ");
        btn_firma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firmaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_firma, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 290, 170, 24));

        img_firma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_firma.setText("Espacio para imagen");
        img_firma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel1.add(img_firma, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 360, 170, 100));

        btn_traer_firma.setText("Trear firma");
        btn_traer_firma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_traer_firmaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_traer_firma, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 330, 170, 24));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Departamento de trabajo");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 150, 20));

        jPanel1.add(combo_depar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 290, 170, -1));
        jPanel1.add(rtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 120, 800, -1));
        jPanel1.add(num_identidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 80, 510, -1));
        jPanel1.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 210, 800, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(467, 467, 467)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void accion_click_salir(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accion_click_salir
         /// System.err.println(""+num_identidad.getValue());
         dispose();
    }//GEN-LAST:event_accion_click_salir

    private void accion_click_eliminar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accion_click_eliminar
        if(id_seleccionado!=null&& !id_seleccionado.isEmpty())
        {
            try {
                int res=JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar esta fila?"+"\n"+"\t\tId : "+id_seleccionado);
                if(res==0)
                {
                    String msj=rdm.eliminar_fila("empleado", id_seleccionado,"id_empleado");
                    
                }
                btn_guardar_ingresar_empleados.setEnabled(true);
                cargarTabla();
                limpiar_campos();
                rdm.cerrar_conexion();
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
        {JOptionPane.showMessageDialog(null, "Seleccione un elemento de la tabla para eliminar");}
    }//GEN-LAST:event_accion_click_eliminar

    private void accion_click_modificar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accion_click_modificar
        String d[]=extraer_datos_formulario();
        for (String sd : d) {
            if(sd==null||sd.isEmpty())
            { JOptionPane.showMessageDialog(null, "Hay campos vacíos");
                return;}
        }
        System.err.println("URL Modificar: "+d[15]);
        try {
            rdm.modificar_emple_firma(d,id_seleccionado);
            rdm.cerrar_conexion();
             cargarTabla();
            limpiar_campos();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_accion_click_modificar

    private void accion_click_limpiar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accion_click_limpiar
     limpiar_campos();
        desmarcar();
    }//GEN-LAST:event_accion_click_limpiar

    private void accion_click_guardar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accion_click_guardar
        String d[]=extraer_datos_formulario();
        for (String sd : d) {
            if(sd==null||sd.isEmpty())
            {JOptionPane.showMessageDialog(null, "Hay campos vacio");
                return;}
        }
        try {

            String identi="";
            rs=rdm.consultar_existencia_id("empleado", d[1], "identidad_empleado");
            while(rs.next())
            {
                identi=rs.getString("identidad_empleado");
            }
            if(null==identi || identi.isEmpty()){
                  System.err.println("URL Guardar:"+d[14]);
                rdm.guardar_emple_con_firma(d);
                cargarTabla();
                limpiar_campos();
                rdm.cerrar_conexion();
            }else
            {JOptionPane.showMessageDialog(null, "Este dato ya existe");}

        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_accion_click_guardar

    private void tbl_empleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_empleadosMouseClicked
        desmarcar();
        int fila_seleccionada=this.tbl_empleados.rowAtPoint(evt.getPoint());
     
        String datos_tbl[]=new String[16];
        for(int x=0;x<16;x++)
        {
            datos_tbl[x]=this.tbl_empleados.getValueAt(fila_seleccionada, x).toString();
            // System.out.println(datos_tbl[x]);
        }
        this.id_seleccionado=datos_tbl[0];
        this.nombre_empleado.setText(datos_tbl[1]);
        this.num_identidad.setText(datos_tbl[2]);
        this.rtn.setText(datos_tbl[6]);
        this.correo.setText(datos_tbl[5]);
        this.direccion.setText(datos_tbl[3]);
        this.salario.setText(datos_tbl[11]);
        this.tel.setText(datos_tbl[4]);
        // Date fecha_nacimiento ;
        if(datos_tbl[9].equalsIgnoreCase("null")){
            return;
        }else
        {
            try {
                Date fech= new SimpleDateFormat("dd-MM-yyyy").parse(datos_tbl[9]);
                this.fecha_nacimiento.setDate(fech);
            } catch (ParseException ex) {
                Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(datos_tbl[8].equalsIgnoreCase("femenino"))
        {
            this.femenino.setSelected(true);
        }else
        {
            this.masculino.setSelected(true);
        }
        if(datos_tbl[7].equalsIgnoreCase("soltero"))
        {
            this.soltero.setSelected(true);
        }else
        {
            if(datos_tbl[7].equalsIgnoreCase("casado"))
            {
                this.casada.setSelected(true);
            }
            if(datos_tbl[7].equalsIgnoreCase("union libre"))
            {
                this.uni_libre.setSelected(true);
            }
            if(datos_tbl[7].equalsIgnoreCase("viudo"))
            {
                this.viudo.setSelected(true);
            }
        }
        this.puestos.getModel().setSelectedItem(datos_tbl[10]);
        this.contra_ingresar_empleados.setText(datos_tbl[12]);
        
        String ruta="C:/xampp/htdocs/conexion_hora_extra/firmas_img/"+datos_tbl[15];
        ImageIcon icon =new ImageIcon(ruta);
        Image imgEscalada = icon.getImage().getScaledInstance(img_firma.getWidth(),img_firma.getHeight(), Image.SCALE_SMOOTH);
        Icon  iconoEscalado = new ImageIcon(imgEscalada);
        this.img_firma.setIcon(iconoEscalado);
        n_firma=datos_tbl[15];
       /// System.err.println(""+datos_tbl[15]);
    }//GEN-LAST:event_tbl_empleadosMouseClicked

    private void femeninoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femeninoActionPerformed
    
    }//GEN-LAST:event_femeninoActionPerformed

    private void btn_firmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firmaActionPerformed
        Contenedor_firma cf=new Contenedor_firma();
        cf.mostrar();
      
        System.out.println("\n"+"btn_firma"+n_firma);
    }//GEN-LAST:event_btn_firmaActionPerformed

    private void btn_traer_firmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_traer_firmaActionPerformed
       JFileChooser file =new JFileChooser();
       file.showOpenDialog(this);
       File archivo=file.getSelectedFile();
       if(archivo!=null)
       {
        String origen=archivo.getPath();
        n_firma=archivo.getName();
        ImageIcon icon =new ImageIcon(origen);
        Image imgEscalada = icon.getImage().getScaledInstance(img_firma.getWidth(),img_firma.getHeight(), Image.SCALE_SMOOTH);
        Icon  iconoEscalado = new ImageIcon(imgEscalada);
        this.img_firma.setIcon(iconoEscalado);
       }else
       {
           //JOptionPane.showMessageDialog(null, "Seleccione una imagen");
       }
           
    }//GEN-LAST:event_btn_traer_firmaActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Ingresar_empleados().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar_ingresar_empleados;
    private javax.swing.JButton btn_firma;
    private javax.swing.JButton btn_guardar_ingresar_empleados;
    private javax.swing.JButton btn_limpiar_ingresar_empleados;
    private javax.swing.JButton btn_modificar_ingresar_empleados;
    private javax.swing.JButton btn_salir_ingresar_empleados;
    private javax.swing.JButton btn_traer_firma;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JCheckBox casada;
    private javax.swing.JComboBox<String> combo_depar;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField fecha_ingreso_empleado;
    private com.toedter.calendar.JDateChooser fecha_nacimiento;
    private javax.swing.JCheckBox femenino;
    private javax.swing.JTextField hora_ingreso_empleado;
    private javax.swing.JLabel img_firma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox masculino;
    private javax.swing.JTextField nombre_empleado;
    private javax.swing.JTextField num_identidad;
    private java.awt.Panel panel1;
    private javax.swing.JComboBox<String> puestos;
    private javax.swing.JTextField rtn;
    private javax.swing.JCheckBox soltero;
    private javax.swing.JTable tbl_empleados;
    private javax.swing.JTextField tel;
    private javax.swing.JCheckBox uni_libre;
    private javax.swing.JCheckBox viudo;
    // End of variables declaration//GEN-END:variables

    private void desmarcar() {
        this.masculino.setSelected(false);
        this.femenino.setSelected(false);
        this.uni_libre.setSelected(false);  
        this.viudo.setSelected(false);
        this.soltero.setSelected(false);
        this.casada.setSelected(false);
    }
 
    public String[] extraer_datos_formulario()
    {
         String datos[]=new String[16];
         String estado_ci = null,puesto_combo;
         String genero_che = null,fecha_n=null;
        //obtener fecha
        String formato= this.fecha_nacimiento.getDateFormatString();
        System.out.print(formato);
        Date date=this.fecha_nacimiento.getDate();
        if(date!=null)
        {
            SimpleDateFormat sdf=new SimpleDateFormat(formato);
            fecha_n=String.valueOf(sdf.format(date));
        }
        
        ////////////obtener valor de chechBox
        if(this.casada.isSelected()==true)
        {
            estado_ci="Casado";
        }
        if(this.soltero.isSelected()==true)
        {
            estado_ci="Soltero";
        }
        if(this.viudo.isSelected()==true)
        {
            estado_ci="Viudo";
        }
        if(this.uni_libre.isSelected()==true)
        {
            estado_ci="Union libre";
        }
        if(this.masculino.isSelected()==true)
        {
            genero_che="Masculino";
        }
        if(this.femenino.isSelected()==true)
        {
            genero_che="Femenino";
        }
        puesto_combo=this.puestos.getSelectedItem().toString();
        
        /////llenar array con datos con textflied
           datos[0]=this.nombre_empleado.getText();
           datos[1]=rtn.getText();
           datos[2]=this.tel.getText();
           datos[8]=""+salario.getValue();
           datos[4]=this.correo.getText();
           datos[5]=rtn.getText();
           datos[6]=estado_ci;
           datos[7]=genero_che;
           datos[3]=tel.getText();
           datos[9]=fecha_n;
           datos[10]=puesto_combo;
           datos[11]=combo_depar.getSelectedItem().toString();
           datos[12]=this.fecha_ingreso_empleado.getText();
           datos[13]=this.hora_ingreso_empleado.getText();
           datos[14]=this.contra_ingresar_empleados.getText();
           datos[15]=n_firma;
        
           
           return datos;
        
        
    }
    private void limpiar_campos() {
        this.nombre_empleado.setText("");
        this.rtn.setText("");
        this.rtn.setText("");
        this.tel.setText("");
        this.tel.setText("");
        this.salario.setText("");
        this.correo.setText("");
        this.contra_ingresar_empleados.setText("");
    }
    private void llenar_combo_departamento() {
      try {
            rs=rdm.traer_datos_todos("departamento_trabajo");
            
            while(rs.next())
            {
              
            this.combo_depar.addItem(rs.getString("nombre_depa_trabajo"));
          
            }
            rdm.cerrar_conexion();
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void llenar_combo_puesto(String item)
    {
        if(combo_depar.getSelectedIndex()>=0)
        {
            // String item=combo_cate_proble.getSelectedItem().toString();
             if(puestos.getModel().getSize()<0)
             {
                 System.err.println("esta vacio combo problema");
                 metodo_combo_problema(item);
             }else
             {
                 puestos.removeAllItems();
                 metodo_combo_problema(item);
                 System.err.println("esta lleno combo problema");
               
             }
        }
    }
    public void metodo_combo_problema(String item)
    {
         System.out.println("Item"+ item);
         try {
              rs=rdm.sql("SELECT * FROM puesto_departamento WHERE nombre_depa_trabajo='"+item+"'");
                while(rs.next())
                 {
                   System.out.println("dentro del while: "+rs.getString("nombre_puesto_departamento"));
                   this.puestos.addItem(rs.getString("nombre_puesto_departamento"));
                 }
                  rdm.cerrar_conexion();
              } catch (SQLException ex) {
                Logger.getLogger(Crear_peticion.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("algo salio mal");
              }
    }

}
