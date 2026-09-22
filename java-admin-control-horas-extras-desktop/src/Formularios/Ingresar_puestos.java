/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pconexionsql.resgistro_datos_mysql;

/**
 *
 * @author CelesteZaldivar
 */
public final class Ingresar_puestos extends javax.swing.JDialog {

    private final resgistro_datos_mysql registroDatos;
    public ResultSet rs=null;
    public  String id_selecionado; 
    public  String id_selecionado_depa; 

    public Ingresar_puestos(resgistro_datos_mysql registroDatos) throws HeadlessException {
        this.registroDatos = registroDatos;
    }
    DefaultTableModel tabla = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };
     DefaultTableModel tabla_depa_trabajo = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };
    
    public Ingresar_puestos() {
      
        initComponents();
        this.setModal(true); 
        this.setLocationRelativeTo(null); 
        this.registroDatos = new resgistro_datos_mysql();
        crear_columnas_tabla();
        crear_columnas_tabla_depa_trabajo();
         llenar_tabla();
         llenar_tabla_departamento();
         llenar_combo_puesto();
      // this.tbl_ingresar_puesto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      // this.tbl_ingresar_puesto.doLayout();
       
    }
    
    public void crear_columnas_tabla()
    {
        tabla.addColumn("Id");
        tabla.addColumn("Departamento");
        tabla.addColumn("Puesto trabajo");
        this.tbl_ingresar_puesto.setModel(tabla);
        this.tbl_ingresar_puesto.setAutoscrolls (true);
    }
     public void crear_columnas_tabla_depa_trabajo()
    {
        tabla_depa_trabajo.addColumn("Id");
        tabla_depa_trabajo.addColumn("Nombre departamento");
        this.tbl_depa_trabajo.setModel(tabla_depa_trabajo);
        this.tbl_depa_trabajo.setAutoscrolls (true);
    }
    public void llenar_tabla()
    {
     
        if(this.tbl_ingresar_puesto.getRowCount()!=0 )
        {
            tabla.setRowCount(0);
            cargar_tabla_puesto();
          }else{
            cargar_tabla_puesto();
        }
    }
     public void llenar_tabla_departamento()
    {
     
        if(this.tbl_depa_trabajo.getRowCount()!=0 )
        {
            tabla_depa_trabajo.setRowCount(0);
            cargar_tabla_depar();
          }else{
            cargar_tabla_depar();
        }
    }
    public void cargar_tabla_puesto()
    {
               String datos[]=new String[3];

        try {
                rs=registroDatos.traer_datos_todos("puesto_departamento");
                while(rs.next())
                { 
                    datos[0]=rs.getString("id_puesto_departamento");
                    datos[1]=rs.getString("nombre_depa_trabajo");
                    datos[2]=rs.getString("nombre_puesto_departamento");
                    tabla.addRow(datos);
                }
                 registroDatos.cerrar_conexion();
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private void cargar_tabla_depar() {
        String datos[]=new String[2];

        try {
                rs=registroDatos.traer_datos_todos("departamento_trabajo");
                while(rs.next())
                { 
                    datos[0]=rs.getString("id_depa_trabajo");
                    datos[1]=rs.getString("nombre_depa_trabajo");
                   
                    tabla_depa_trabajo.addRow(datos);
                }
                 registroDatos.cerrar_conexion();
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_ingresar_puesto = new javax.swing.JTable();
        btn_guardar_ingresar_puesto = new javax.swing.JButton();
        btn_limpiar_puesto = new javax.swing.JButton();
        btn_modificar_puesto = new javax.swing.JButton();
        btn_eliminar_puesto = new javax.swing.JButton();
        btn_salir_puesto = new javax.swing.JButton();
        nombre_puesto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        combo_departamento = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        nombre_depa_trabajo = new javax.swing.JTextField();
        btn_guardar_depa_trabajo = new javax.swing.JButton();
        btn_limpiar_depa_trabajo = new javax.swing.JButton();
        btn_modificar_depa_trabajo = new javax.swing.JButton();
        btn_eliminar_depa_trabajo = new javax.swing.JButton();
        btn_salir_depa_trabajo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_depa_trabajo = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        panel1.setBackground(new java.awt.Color(255, 255, 255));

        tbl_ingresar_puesto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_ingresar_puesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ingresar_puestoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_ingresar_puesto);

        btn_guardar_ingresar_puesto.setBackground(java.awt.Color.lightGray);
        btn_guardar_ingresar_puesto.setText("Guardar");
        btn_guardar_ingresar_puesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_ingresar_puestoActionPerformed(evt);
            }
        });

        btn_limpiar_puesto.setBackground(java.awt.Color.lightGray);
        btn_limpiar_puesto.setText("Limpiar");
        btn_limpiar_puesto.setToolTipText("");
        btn_limpiar_puesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                evento_clip_limpiar(evt);
            }
        });

        btn_modificar_puesto.setBackground(java.awt.Color.lightGray);
        btn_modificar_puesto.setText("Midificar");
        btn_modificar_puesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                evento_clip_modificar(evt);
            }
        });

        btn_eliminar_puesto.setBackground(java.awt.Color.lightGray);
        btn_eliminar_puesto.setText("Eliminar");
        btn_eliminar_puesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                evento_clip_eliminar(evt);
            }
        });

        btn_salir_puesto.setBackground(java.awt.Color.lightGray);
        btn_salir_puesto.setText("Salir");
        btn_salir_puesto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                evento_clip_salir(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Ingresar nombre de puesto");
        jLabel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ingresar un nuevo puesto de trabajo");

        combo_departamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combo_departamentoMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Seleccionar departamento");
        jLabel4.setToolTipText("");

        btn_guardar_depa_trabajo.setBackground(java.awt.Color.lightGray);
        btn_guardar_depa_trabajo.setText("Guardar");
        btn_guardar_depa_trabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_depa_trabajoActionPerformed(evt);
            }
        });

        btn_limpiar_depa_trabajo.setBackground(java.awt.Color.lightGray);
        btn_limpiar_depa_trabajo.setText("Limpiar");
        btn_limpiar_depa_trabajo.setToolTipText("");
        btn_limpiar_depa_trabajo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_limpiar_depa_trabajoevento_clip_limpiar(evt);
            }
        });

        btn_modificar_depa_trabajo.setBackground(java.awt.Color.lightGray);
        btn_modificar_depa_trabajo.setText("Midificar");
        btn_modificar_depa_trabajo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modificar_depa_trabajoevento_clip_modificar(evt);
            }
        });

        btn_eliminar_depa_trabajo.setBackground(java.awt.Color.lightGray);
        btn_eliminar_depa_trabajo.setText("Eliminar");
        btn_eliminar_depa_trabajo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminar_depa_trabajoevento_clip_eliminar(evt);
            }
        });

        btn_salir_depa_trabajo.setBackground(java.awt.Color.lightGray);
        btn_salir_depa_trabajo.setText("Salir");
        btn_salir_depa_trabajo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_salir_depa_trabajoevento_clip_salir(evt);
            }
        });

        tbl_depa_trabajo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_depa_trabajo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_depa_trabajoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_depa_trabajo);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ingresar un departamento de trabajo");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Ingresar nombre departamento");
        jLabel6.setToolTipText("");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(btn_guardar_ingresar_puesto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_limpiar_puesto, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_modificar_puesto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_eliminar_puesto)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_salir_puesto, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(combo_departamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(nombre_puesto, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(btn_guardar_depa_trabajo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_limpiar_depa_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_modificar_depa_trabajo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_eliminar_depa_trabajo)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_salir_depa_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(nombre_depa_trabajo, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                        .addGap(19, 19, 19)))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                    .addContainerGap(483, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre_depa_trabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_guardar_depa_trabajo)
                            .addComponent(btn_limpiar_depa_trabajo)
                            .addComponent(btn_modificar_depa_trabajo)
                            .addComponent(btn_eliminar_depa_trabajo)
                            .addComponent(btn_salir_depa_trabajo)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre_puesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_departamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_guardar_ingresar_puesto)
                            .addComponent(btn_limpiar_puesto)
                            .addComponent(btn_modificar_puesto)
                            .addComponent(btn_eliminar_puesto)
                            .addComponent(btn_salir_puesto)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                    .addContainerGap(264, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addContainerGap(265, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardar_ingresar_puestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_ingresar_puestoActionPerformed
        // TODO add your handling code here:
        String ta="puesto_departamento",msj; 
        String d[]=new String[2];
        d[0]=nombre_puesto.getText();
        d[1]=combo_departamento.getSelectedItem().toString();
        String pues="";
        
        try {
            rs=registroDatos.consultar_existencia_id(ta, d[0], "nombre_puesto_departamento");
            while(rs.next())
            {
                pues=rs.getString("nombre_puesto_departamento");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!nombre_puesto.getText().isEmpty())
        {
                 if(pues==null||pues.isEmpty())
            {
                try {
                   msj = registroDatos.guardarDatos(d,ta);
                   llenar_combo_puesto();
                   llenar_tabla();
                
                   this.nombre_puesto.setText("");
                   registroDatos.cerrar_conexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Este puesto ya existe");
            }
        }else
        {
             JOptionPane.showMessageDialog(null, "Hay campos vacios");
        }
        
          
    }//GEN-LAST:event_btn_guardar_ingresar_puestoActionPerformed

    private void evento_clip_limpiar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_evento_clip_limpiar
      this.nombre_puesto.setText("");
    }//GEN-LAST:event_evento_clip_limpiar

    private void evento_clip_modificar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_evento_clip_modificar
            String d[]=new String[2];
            
            d[0]=this.nombre_puesto.getText();
            d[1]=combo_departamento.getSelectedItem().toString();
        if(!this.nombre_puesto.getText().isEmpty()&& !id_selecionado.isEmpty())
        {
            String msj=registroDatos.modificar_fila("puesto_departamento", id_selecionado,d);
            llenar_tabla();
             llenar_combo_puesto();
            this.nombre_puesto.setText("");
            try {
            registroDatos.cerrar_conexion();
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else
        {
            JOptionPane.showMessageDialog(null,"Debe llenar el campo vacio");
        }
      
    }//GEN-LAST:event_evento_clip_modificar

    private void evento_clip_salir(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_evento_clip_salir
      dispose(); 
    }//GEN-LAST:event_evento_clip_salir

    private void evento_clip_eliminar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_evento_clip_eliminar
       
         if(id_selecionado!=null &&!this.id_selecionado.isEmpty())
         {
              int res= JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este dato?"+"\n"+"\tId : "+id_selecionado);
                System.out.print(res);
                if(res==0)
                {
                  String msj=registroDatos.eliminar_fila("puesto_departamento", id_selecionado,"id_puesto_departamento");
                  llenar_tabla();
                  llenar_combo_puesto();
                  this.nombre_puesto.setText("");
                  try {
                    registroDatos.cerrar_conexion();
                    } catch (SQLException ex) {
                    Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
         }else
         {
             JOptionPane.showMessageDialog(null,"Seleccione un elemento de la tabla para eliminar");
         }          
    }//GEN-LAST:event_evento_clip_eliminar

    private void tbl_ingresar_puestoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ingresar_puestoMouseClicked
    int fila_Seleccionada=this.tbl_ingresar_puesto.rowAtPoint(evt.getPoint());
      this.id_selecionado=this.tbl_ingresar_puesto.getValueAt(fila_Seleccionada, 0).toString();
      this.combo_departamento.getModel().setSelectedItem(this.tbl_ingresar_puesto.getValueAt(fila_Seleccionada, 1).toString());
    this.nombre_puesto.setText(this.tbl_ingresar_puesto.getValueAt(fila_Seleccionada, 2).toString());
     llenar_combo_puesto();
  
    }//GEN-LAST:event_tbl_ingresar_puestoMouseClicked

    private void btn_guardar_depa_trabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_depa_trabajoActionPerformed
        String ta="departamento_trabajo",msj; 
        String d[]=new String[1];
        d[0]=nombre_depa_trabajo.getText();
        String pues="";
        
        try {
            rs=registroDatos.consultar_existencia_id(ta, d[0], "nombre_depa_trabajo");
            while(rs.next())
            {
                pues=rs.getString("nombre_depa_trabajo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!nombre_depa_trabajo.getText().isEmpty())
        {
                 if(pues==null||pues.isEmpty())
            {
                try {
                   msj = registroDatos.guardarDatos(d,ta);
                   llenar_tabla_departamento();
                   llenar_combo_puesto();
                   this.nombre_depa_trabajo.setText("");
                   registroDatos.cerrar_conexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Este departamento ya existe");
            }
        }else
        {
             JOptionPane.showMessageDialog(null, "Hay campos vacios");
        }
        
    }//GEN-LAST:event_btn_guardar_depa_trabajoActionPerformed

    private void btn_limpiar_depa_trabajoevento_clip_limpiar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_limpiar_depa_trabajoevento_clip_limpiar
        this.nombre_depa_trabajo.setText("");
    }//GEN-LAST:event_btn_limpiar_depa_trabajoevento_clip_limpiar

    private void btn_modificar_depa_trabajoevento_clip_modificar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificar_depa_trabajoevento_clip_modificar
         String d[]=new String[1];
         d[0]=this.nombre_depa_trabajo.getText();
        if(!this.nombre_depa_trabajo.getText().isEmpty()&& !id_selecionado_depa.isEmpty())
        {
            String msj=registroDatos.modificar_fila("departamento_trabajo", id_selecionado_depa,d);
            llenar_tabla_departamento();
             llenar_combo_puesto();
            this.nombre_depa_trabajo.setText("");
            try {
                registroDatos.cerrar_conexion();
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
        {
            JOptionPane.showMessageDialog(null,"Debe llenar el campo vacio");
        }
      
    }//GEN-LAST:event_btn_modificar_depa_trabajoevento_clip_modificar

    private void btn_eliminar_depa_trabajoevento_clip_eliminar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminar_depa_trabajoevento_clip_eliminar
       if(id_selecionado_depa!=null &&!this.id_selecionado_depa.isEmpty())
         {
              int res= JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este dato?"+"\n"+"\tId : "+id_selecionado_depa);
                System.out.print(res);
                if(res==0)
                {
                  String msj=registroDatos.eliminar_fila("departamento_trabajo", id_selecionado_depa,"id_depa_trabajo");
                  llenar_tabla_departamento();
                   llenar_combo_puesto();
                  this.nombre_depa_trabajo.setText("");
                  try {
            registroDatos.cerrar_conexion();
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
         }else
         {
             JOptionPane.showMessageDialog(null,"Seleccione un elemento de la tabla para eliminar");
         }      
    }//GEN-LAST:event_btn_eliminar_depa_trabajoevento_clip_eliminar

    private void btn_salir_depa_trabajoevento_clip_salir(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salir_depa_trabajoevento_clip_salir
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_salir_depa_trabajoevento_clip_salir

    private void tbl_depa_trabajoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_depa_trabajoMouseClicked
    int fila_Seleccionada=this.tbl_depa_trabajo.rowAtPoint(evt.getPoint());
    this.id_selecionado_depa=this.tbl_depa_trabajo.getValueAt(fila_Seleccionada, 0).toString();
    this.nombre_depa_trabajo.setText(this.tbl_depa_trabajo.getValueAt(fila_Seleccionada, 1).toString());
    llenar_combo_puesto();
    }//GEN-LAST:event_tbl_depa_trabajoMouseClicked

    private void combo_departamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_departamentoMouseClicked
       llenar_combo_puesto();
    }//GEN-LAST:event_combo_departamentoMouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ingresar_puestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ingresar_puestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ingresar_puestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ingresar_puestos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Ingresar_puestos().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar_depa_trabajo;
    private javax.swing.JButton btn_eliminar_puesto;
    private javax.swing.JButton btn_guardar_depa_trabajo;
    private javax.swing.JButton btn_guardar_ingresar_puesto;
    private javax.swing.JButton btn_limpiar_depa_trabajo;
    private javax.swing.JButton btn_limpiar_puesto;
    private javax.swing.JButton btn_modificar_depa_trabajo;
    private javax.swing.JButton btn_modificar_puesto;
    private javax.swing.JButton btn_salir_depa_trabajo;
    private javax.swing.JButton btn_salir_puesto;
    private javax.swing.JComboBox<String> combo_departamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombre_depa_trabajo;
    private javax.swing.JTextField nombre_puesto;
    private java.awt.Panel panel1;
    private javax.swing.JTable tbl_depa_trabajo;
    private javax.swing.JTable tbl_ingresar_puesto;
    // End of variables declaration//GEN-END:variables
    public String n;

 
    private void llenar_combo_puesto() {
        try {
            rs=registroDatos.sql("SELECT nombre_depa_trabajo FROM departamento_trabajo");
            if(this.combo_departamento.getModel().getSize()>0)
            {
                  this.combo_departamento.removeAllItems();
                 while(rs.next())
               {
                    this.combo_departamento.addItem(rs.getString("nombre_depa_trabajo"));
                }
            }else
            {
              
                 while(rs.next())
                {
                    this.combo_departamento.addItem(rs.getString("nombre_depa_trabajo"));
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(categoria_problema.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            registroDatos.cerrar_conexion();
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
