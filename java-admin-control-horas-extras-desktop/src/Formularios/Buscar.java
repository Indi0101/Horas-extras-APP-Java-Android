/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import Clases_reporte.Clase_reportes;
import pconexionsql.resgistro_datos_mysql;

/**
 *
 * @author CelesteZaldivar
 */
public class Buscar extends javax.swing.JFrame {
    public Clase_reportes clase_reportes=new Clase_reportes();
  public DefaultTableModel tabla = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };
  
  public resgistro_datos_mysql rdatos=new resgistro_datos_mysql();
  public ResultSet rs=null;
  public String datos[]=new String[14];
    public Buscar() {
        initComponents();
        this.setLocationRelativeTo(null); 
        crear_columnas_tabla();
        
      try {
          rs=rdatos.traer_datos_todos("empleado");
          while(rs.next())
          {
              this.nombre_buscar.addItem(rs.getString("nombre_completo_empleado"));
          }
        
      } catch (SQLException ex) {
          Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
      }
      String es[]={"Aprobada","Rechazada","Pendiente"};
      for(String s:es)
      {
           this.estado_buscar.addItem(s);
      }
      try {
          rs=rdatos.traer_datos_todos("ingreso_hora_extra");
      } catch (SQLException ex) {
          Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
      }
       cargarTabla(rs);
      
      
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_buscar = new javax.swing.JTable();
        btn_eliminar_buscar = new javax.swing.JButton();
        btn_salir_buscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        nombre_buscar = new javax.swing.JComboBox<>();
        btn_buscar_buscar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        estado_buscar = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel1.setBackground(java.awt.Color.white);

        tbl_buscar.setAutoCreateRowSorter(true);
        tbl_buscar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_buscarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_buscar);

        btn_eliminar_buscar.setText("Imprimir");
        btn_eliminar_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_buscarActionPerformed(evt);
            }
        });

        btn_salir_buscar.setText("Salir");
        btn_salir_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salir_buscarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Filtrar por:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Nombre empleado");

        btn_buscar_buscar.setText("Buscar");
        btn_buscar_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_buscarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Estado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_eliminar_buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_salir_buscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 414, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_buscar_buscar)
                .addGap(479, 479, 479))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombre_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estado_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btn_buscar_buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_eliminar_buscar)
                    .addComponent(btn_salir_buscar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscar_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_buscarActionPerformed
        
       
        if(!this.nombre_buscar.getSelectedItem().toString().isEmpty() && !this.estado_buscar.getSelectedItem().toString().isEmpty())
        {
            System.err.println("adentro condicion buscar");
             String n_e=this.nombre_buscar.getSelectedItem().toString();
             String estado=this.estado_buscar.getSelectedItem().toString();
             String filtro[]={n_e,estado};
             String columna[]={"nombre_empeleado_ingreso_hora_extra","estado_ingreso_hora_extra"};
            try {
                rs=rdatos.traer_valores_f2("ingreso_hora_extra",filtro,columna);
                cargarTabla(rs);
            } catch (SQLException ex) {
                Logger.getLogger(Buscar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
        {}
    }//GEN-LAST:event_btn_buscar_buscarActionPerformed

    private void btn_salir_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salir_buscarActionPerformed
        dispose();
    }//GEN-LAST:event_btn_salir_buscarActionPerformed

    private void tbl_buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_buscarMouseClicked
        int fila_seleccionada=this.tbl_buscar.rowAtPoint(evt.getPoint());
       
        for(int x=0; x<datos.length;x++)
        {
         datos[x]=this.tbl_buscar.getValueAt(fila_seleccionada, x).toString();
        }
       Mostrar_info mi=new Mostrar_info(datos);
       mi.setVisible(true);
        
    }//GEN-LAST:event_tbl_buscarMouseClicked

    private void btn_eliminar_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_buscarActionPerformed
         String n_emple=this.nombre_buscar.getSelectedItem().toString();
         String estado=this.estado_buscar.getSelectedItem().toString();
         String fecha="13-04-2021";
         System.err.println(""+n_emple+":"+estado+":"+fecha);
         String d[]={estado,fecha,n_emple};
        // clase_reportes.horas_extra_emple(d);
    }//GEN-LAST:event_btn_eliminar_buscarActionPerformed
    
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
            java.util.logging.Logger.getLogger(Buscar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buscar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buscar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buscar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Buscar().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar_buscar;
    private javax.swing.JButton btn_eliminar_buscar;
    private javax.swing.JButton btn_salir_buscar;
    private javax.swing.JComboBox<String> estado_buscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JComboBox<String> nombre_buscar;
    private javax.swing.JTable tbl_buscar;
    // End of variables declaration//GEN-END:variables

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
        this.tbl_buscar.setModel(tabla);
//        this.tbl_buscar.setAutoscrolls(true);
        this.tbl_buscar.getColumn(this.tbl_buscar.getModel().getColumnName(0)).setMaxWidth(300);
    }
    private void cargarTabla(ResultSet r) {
         String da[]=new String[14];
      
        if(this.tbl_buscar.getRowCount()!=0)
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
