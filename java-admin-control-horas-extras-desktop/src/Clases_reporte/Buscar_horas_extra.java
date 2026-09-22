/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_reporte;

import Formularios.Ingresar_empleados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import pconexionsql.resgistro_datos_mysql;

/**
 *
 * @author CelesteZaldivar
 */
public class Buscar_horas_extra extends javax.swing.JFrame {
public resgistro_datos_mysql rdm=new resgistro_datos_mysql();
public ResultSet rs=null;
public Clase_reportes c_reporte=new Clase_reportes();
public DefaultTableModel tabla = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };
    public Buscar_horas_extra() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.fecha_h_e.addItem("Todo");
      //  this.estado_h_e.addItem("Rechazada");
      //  this.estado_h_e.addItem("Aprobada");
        llenar_combox();
         crear_columnas_tabla();
         
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_h_e = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        nombres_h_e = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        fecha_h_e = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        estado_h_e = new javax.swing.JComboBox<>();
        btn_buscar_h_e = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_imp_h_e = new javax.swing.JButton();
        btn_salir_h_e = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tbl_h_e.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_h_e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_h_eMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_h_e);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Nombre de empleado");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Fecha ");

        jLabel4.setText("Estado");

        btn_buscar_h_e.setText("Buscar");
        btn_buscar_h_e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_buscar_h_eMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Buscar por:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Archivo de horas extras ");

        btn_imp_h_e.setText("Imprimir");
        btn_imp_h_e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_imp_h_eMouseClicked(evt);
            }
        });
        btn_imp_h_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imp_h_eActionPerformed(evt);
            }
        });

        btn_salir_h_e.setText("Salir");
        btn_salir_h_e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_salir_h_eMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombres_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(btn_buscar_h_e))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 831, Short.MAX_VALUE)
                        .addComponent(btn_imp_h_e)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir_h_e))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 812, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombres_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(fecha_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar_h_e)
                    .addComponent(jLabel4)
                    .addComponent(estado_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_imp_h_e)
                    .addComponent(btn_salir_h_e))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_h_eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_h_eMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_h_eMouseClicked

    private void btn_buscar_h_eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_buscar_h_eMouseClicked
     String info[]=traer_datos();
     for(String i:info) 
     {
         if(i==null)
         {
             return;
         }
     }
      
      if(info[0].equalsIgnoreCase("0"))
      {
         try {
             rs=rdm.sql("SELECT * FROM ingreso_hora_extra WHERE nombre_empeleado_ingreso_hora_extra='"+info[1]+"' AND estado_ingreso_hora_extra='"+info[2]+"'");
             cargarTabla(rs);   
         } catch (SQLException ex) {
             Logger.getLogger(Buscar_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
         }
      }else
      {
           try {
             rs=rdm.sql("SELECT * FROM ingreso_hora_extra WHERE nombre_empeleado_ingreso_hora_extra='"+info[1]+"' AND estado_ingreso_hora_extra='"+info[2]+"' AND fecha__ingreso_hora_extra='"+info[3]+"'");
             cargarTabla(rs);   
         } catch (SQLException ex) {
             Logger.getLogger(Buscar_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      
        
    }//GEN-LAST:event_btn_buscar_h_eMouseClicked

    private void btn_imp_h_eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_imp_h_eMouseClicked
      String datos[]=new String[3];
      datos[0]=this.estado_h_e.getSelectedItem().toString();
      datos[1]=this.fecha_h_e.getSelectedItem().toString();
      datos[2]=this.nombres_h_e.getSelectedItem().toString();
        System.err.println(datos[0]+":"+datos[1]+":"+datos[2]);
     // c_reporte.horas_extra_emple(datos);
    }//GEN-LAST:event_btn_imp_h_eMouseClicked

    private void btn_salir_h_eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salir_h_eMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_salir_h_eMouseClicked

    private void btn_imp_h_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imp_h_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_imp_h_eActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new Buscar_horas_extra().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar_h_e;
    private javax.swing.JButton btn_imp_h_e;
    private javax.swing.JButton btn_salir_h_e;
    private javax.swing.JComboBox<String> estado_h_e;
    private javax.swing.JComboBox<String> fecha_h_e;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox<String> nombres_h_e;
    private javax.swing.JTable tbl_h_e;
    // End of variables declaration//GEN-END:variables

    private void llenar_combox(){
        ArrayList<String> da_estado=new ArrayList<>();
        ArrayList<String> da_fecha=new ArrayList<>();
    try {
        rs=rdm.traer_datos_distintos("ingreso_hora_extra");
            while(rs.next())
        {
            
            if(!da_estado.contains(rs.getString("estado_ingreso_hora_extra")))
            {
                da_estado.add(rs.getString("estado_ingreso_hora_extra").toString());
                System.err.println("No esta el dato estado"+false);
            
            }
            if(!da_fecha.contains(rs.getString("fecha__ingreso_hora_extra")))
            {
                da_fecha.add(rs.getString("fecha__ingreso_hora_extra").toString());
                System.err.println("no esta el dato fecha"+false);
           
            }
        }
            rs=rdm.traer_datos_todos("empleado");
            while(rs.next())
            {
                this.nombres_h_e.addItem(rs.getString("nombre_completo_empleado"));
            }
            
            for(String e:da_estado)
            {
                this.estado_h_e.addItem(e);
            }
            
            for(String f:da_fecha)
            {
                this.fecha_h_e.addItem(f);
            }
            
    } catch (SQLException ex) {
        Logger.getLogger(Buscar_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }

    private String[] traer_datos() {
      String datos[]=new String[4];
      int posi_fecha_comb=this.fecha_h_e.getSelectedIndex();      
      String fecha=this.fecha_h_e.getSelectedItem().toString(); 
      String nomb=this.nombres_h_e.getSelectedItem().toString();
      String estado=this.estado_h_e.getSelectedItem().toString();
      
      if(fecha.isEmpty()&&nomb.isEmpty()&&estado.isEmpty())
      {
          for (int i = 0; i < datos.length; i++) {
               datos[i] =null;
              
          }
         return datos; 
      }else   
      {
          datos[0]=""+posi_fecha_comb;
          datos[1]=nomb;
          datos[2]=estado;
          datos[3]=fecha;
          
          return datos;
                  }
    }
    
     private void crear_columnas_tabla() {
        
        tabla.addColumn("Id");
        tabla.addColumn("Hora de inicio");
        tabla.addColumn("Hora final");
        tabla.addColumn("Fecha inicio");
        tabla.addColumn("Fecha final");
        tabla.addColumn("Nombre de empleado");
        tabla.addColumn("Numero Peticion");
        tabla.addColumn("Comentario");
        tabla.addColumn("Ubicacion");
        tabla.addColumn("Fecha de ingreso");
        tabla.addColumn("Hora de ingreso");
        tabla.addColumn("Total horas extras");
        tabla.addColumn("Estado");
        tabla.addColumn("Total pago");
        this.tbl_h_e.setModel(tabla);
//        this.tbl_buscar.setAutoscrolls(true);
        this.tbl_h_e.getColumn(tbl_h_e.getModel().getColumnName(0)).setMaxWidth(300);
    }
     
     private void cargarTabla(ResultSet r) {
         String da[]=new String[14];
      
        if(this.tbl_h_e.getRowCount()!=0)
        {
            tabla.setRowCount(0);
        try {
            while(r.next())
            {
                da[0]=r.getString("id_ingreso_hora_extra");
                da[1]=r.getString("hora_inicio_ingreso_hora_extra");
                da[2]=r.getString("hora_final_ingreso_hora_extra");
                da[3]=r.getString("fecha_inicio_ingreso_hora_extra");
                da[4]=r.getString("fecha_final_ingreso_hora_extra");
                da[5]=r.getString("nombre_empeleado_ingreso_hora_extra");
                da[6]=r.getString("peticion_empleado_ingreso_hora_extra");
                da[7]=r.getString("comentario_ingreso_hora_extra");
                da[8]=r.getString("ubicacion_ingreso_hora_extra");
                da[9]=r.getString("fecha__ingreso_hora_extra");
                da[10]=r.getString("hora__ingreso_hora_extra");
                da[11]=r.getString("total_horas_extras_ingreso_horas_extras");
                da[12]=r.getString("estado_ingreso_hora_extra");
                da[13]=r.getString("pago_total_ingreso_hora_extra");
                tabla.addRow(da);    
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else
        {
            try {
            while(r.next())
            {
                da[0]=r.getString("id_ingreso_hora_extra");
                da[1]=r.getString("hora_inicio_ingreso_hora_extra");
                da[2]=r.getString("hora_final_ingreso_hora_extra");
                da[3]=r.getString("fecha_inicio_ingreso_hora_extra");
                da[4]=r.getString("fecha_final_ingreso_hora_extra");
                da[5]=r.getString("nombre_empeleado_ingreso_hora_extra");
                da[6]=r.getString("peticion_empleado_ingreso_hora_extra");
                da[7]=r.getString("comentario_ingreso_hora_extra");
                da[8]=r.getString("ubicacion_ingreso_hora_extra");
                da[9]=r.getString("fecha__ingreso_hora_extra");
                da[10]=r.getString("hora__ingreso_hora_extra");
                da[11]=r.getString("total_horas_extras_ingreso_horas_extras");
                da[12]=r.getString("estado_ingreso_hora_extra");
                da[13]=r.getString("pago_total_ingreso_hora_extra");
                 tabla.addRow(da);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        }    
    }
}
