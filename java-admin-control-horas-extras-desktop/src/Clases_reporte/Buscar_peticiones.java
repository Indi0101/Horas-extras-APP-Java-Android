
package Clases_reporte;

import Formularios.Ingresar_empleados;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import pconexionsql.resgistro_datos_mysql;

/**
 *
 * @author CelesteZaldivar
 */
public class Buscar_peticiones extends javax.swing.JFrame {
    
   public resgistro_datos_mysql rdm=new resgistro_datos_mysql();
   public ResultSet rs=null;
   public Clase_reportes clase_reportes=new Clase_reportes();
   public ArrayList<String> lista_id_emple_peticion =new ArrayList<>();
   public DefaultTableModel tabla_peticion = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };
    public Buscar_peticiones() {
        initComponents();
        crear_columnas_tabla_peticion();
        llenar_combox_peticion();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nombres_combo_buscar_peticion = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_buscar_peticion = new javax.swing.JTable();
        btn_buscar_peticion = new javax.swing.JButton();
        btn_imp_buscar_peticion = new javax.swing.JButton();
        btn_salir_buscar_peticion = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        estado_combo_buscar_peticion = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        id_peti_combo_buscar_peticion = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Buscar Peticiones");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Buscar por:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Nombre de empleado");

        tbl_buscar_peticion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_buscar_peticion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_buscar_peticionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_buscar_peticion);

        btn_buscar_peticion.setText("Buscar");
        btn_buscar_peticion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_buscar_peticionMouseClicked(evt);
            }
        });

        btn_imp_buscar_peticion.setText("Imprimir");
        btn_imp_buscar_peticion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_imp_buscar_peticionMouseClicked(evt);
            }
        });
        btn_imp_buscar_peticion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imp_buscar_peticionActionPerformed(evt);
            }
        });

        btn_salir_buscar_peticion.setText("Salir");
        btn_salir_buscar_peticion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_salir_buscar_peticionMouseClicked(evt);
            }
        });

        jLabel3.setText("Estado");

        jLabel4.setText("Id de peticiion");

        id_peti_combo_buscar_peticion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_imp_buscar_peticion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir_buscar_peticion))
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_peti_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombres_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(252, 252, 252)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_buscar_peticion)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(btn_buscar_peticion)
                        .addComponent(estado_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nombres_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(id_peti_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_salir_buscar_peticion)
                    .addComponent(btn_imp_buscar_peticion))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_buscar_peticionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_buscar_peticionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_buscar_peticionMouseClicked

    private void btn_buscar_peticionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_buscar_peticionMouseClicked
     
           String info[]=traer_datos_peticion();     ///traer_datos();
           for(String i:info)
           {
               if(i==null)
               {
                   return;
               }
           }
           System.err.println("id_combo_nom:"+info[4]+"  id_id_emple_comb"+lista_id_emple_peticion.get(Integer.parseInt(info[4])));
        //////////////"id_peticion","hora_peticion","fecha_peticion","n_agencia_peticion","estado_peticion",
        /////              "ubicacion_peticiones_finalizadas","fecha_final__peticiones_finalizadas"
        /////          ,"hora_final_peticiones_finalizadas","id_empleado_encargado"
        
        if(info[0].equalsIgnoreCase("0"))
        {
            try {
                rs=rdm.sql("SELECT peticiones.id_peticiones,hora_peticion,fecha_peticion,n_agencia_peticion,estado_peticion,ubicacion_peticiones_finalizadas,fecha_final__peticiones_finalizadas,hora_final_peticiones_finalizadas,id_empleado,id_empleado_encargado,emple_a_1_peticion_finalizada,emple_a_2_peticion_finalizada,emple_a_3_peticion_finalizada "
                        + "FROM peticiones,peticiones_finalizadas "
                        + "WHERE peticiones.id_peticiones=peticiones_finalizadas.id_peticiones "
                        + "AND peticiones.estado_peticion='"+info[3]+"'"
                        + "AND peticiones_finalizadas.id_empleado='"+lista_id_emple_peticion.get(Integer.parseInt(info[4]))+"'");
                     ///   + "OR (peticiones_finalizadas.emple_a_1_peticion_finalizada='"+lista_id_emple.get(Integer.parseInt(info[4]))+"')");
                ///+ "OR peticiones_finalizadas.emple_a_1_peticion_finalizada='"+lista_id_emple.get(Integer.parseInt(info[4]))+"'"
               ///         + "OR peticiones_finalizadas.emple_a_2_peticion_finalizada='"+lista_id_emple.get(Integer.parseInt(info[4]))+"'"
               ///      + "OR peticiones_finalizadas.emple_a_3_peticion_finalizada='"+lista_id_emple.get(Integer.parseInt(info[4]))+"'"
                //////////////// + "WHERE peticiones.id_peticiones=peticiones_finalizadas.id_peticiones");
                cargar_tabla_peticion(rs);
            } catch (SQLException ex) {
                Logger.getLogger(Buscar_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
        {
            try {
                System.err.println("nombre selec largo"+info[2]);
                rs=rdm.sql("SELECT peticiones.id_peticiones,hora_peticion,fecha_peticion,n_agencia_peticion,estado_peticion,ubicacion_peticiones_finalizadas,fecha_final__peticiones_finalizadas,hora_final_peticiones_finalizadas,id_empleado,id_empleado_encargado,emple_a_1_peticion_finalizada,emple_a_2_peticion_finalizada,emple_a_3_peticion_finalizada "
                        + "FROM peticiones,peticiones_finalizadas "
                        + "WHERE peticiones.id_peticiones=peticiones_finalizadas.id_peticiones "
                        + "AND peticiones.estado_peticion='"+info[3]+"'"
                        + "AND peticiones_finalizadas.id_empleado='"+lista_id_emple_peticion.get(Integer.parseInt(info[4]))+"'"
                        + "AND peticiones_finalizadas.id_peticiones='"+info[1]+"'");

                cargar_tabla_peticion(rs);
            } catch (SQLException ex) {
                Logger.getLogger(Buscar_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      

    }//GEN-LAST:event_btn_buscar_peticionMouseClicked

    private void btn_imp_buscar_peticionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_imp_buscar_peticionMouseClicked
        String datos[]=new String[3];
       // datos[0]=this.estado_h_e.getSelectedItem().toString();
       // datos[1]=this.fecha_h_e.getSelectedItem().toString();
        //datos[2]=this.nombres_combo_buscar_peticion.getSelectedItem().toString();
       // c_reporte.horas_extra_emple(datos);
    }//GEN-LAST:event_btn_imp_buscar_peticionMouseClicked

    private void btn_imp_buscar_peticionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imp_buscar_peticionActionPerformed
        Clase_reportes cr=new Clase_reportes();
        String datos[]=new String[3];
        datos[0]=this.id_peti_combo_buscar_peticion.getSelectedItem().toString();
        int i=this.nombres_combo_buscar_peticion.getSelectedIndex();
        datos[1]=this.lista_id_emple_peticion.get(i);
        datos[2]=this.estado_combo_buscar_peticion.getSelectedItem().toString();
      //  cr.peticiones_emple(datos);
    }//GEN-LAST:event_btn_imp_buscar_peticionActionPerformed

    private void btn_salir_buscar_peticionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salir_buscar_peticionMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_salir_buscar_peticionMouseClicked

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
            java.util.logging.Logger.getLogger(Buscar_peticiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buscar_peticiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buscar_peticiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buscar_peticiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Buscar_peticiones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar_peticion;
    private javax.swing.JButton btn_imp_buscar_peticion;
    private javax.swing.JButton btn_salir_buscar_peticion;
    private javax.swing.JComboBox<String> estado_combo_buscar_peticion;
    private javax.swing.JComboBox<String> id_peti_combo_buscar_peticion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox<String> nombres_combo_buscar_peticion;
    private javax.swing.JTable tbl_buscar_peticion;
    // End of variables declaration//GEN-END:variables

    /*private String[] traer_datos() {
        
    }*/
    private void crear_columnas_tabla_peticion() {
        
        tabla_peticion.addColumn("Id peticion");
        tabla_peticion.addColumn("Hora de ingreso");
        tabla_peticion.addColumn("Fecha de ingreso");
        tabla_peticion.addColumn("Agencia");
        tabla_peticion.addColumn("Estado");
        tabla_peticion.addColumn("Ubicacion");
        tabla_peticion.addColumn("Fecha finalizacion");
        tabla_peticion.addColumn("Hora de finalizacion");
        tabla_peticion.addColumn("Empleado");
        tabla_peticion.addColumn("Emcargado de agencia");
        tabla_peticion.addColumn("Compañero/ayudante 1");
        tabla_peticion.addColumn("Compañero/ayudante 2");
        tabla_peticion.addColumn("Compañero/ayudante 3");
        this.tbl_buscar_peticion.setModel(tabla_peticion);
//        this.tbl_buscar.setAutoscrolls(true);
        this.tbl_buscar_peticion.getColumn(tbl_buscar_peticion.getModel().getColumnName(0)).setMaxWidth(300);
    }
    public void cargar_tabla_peticion(ResultSet rs)
     {
       String id_datos[]={"id_peticiones","hora_peticion","fecha_peticion","n_agencia_peticion","estado_peticion",
                            "ubicacion_peticiones_finalizadas","fecha_final__peticiones_finalizadas"
                             ,"hora_final_peticiones_finalizadas","id_empleado","id_empleado_encargado","emple_a_1_peticion_finalizada","emple_a_2_peticion_finalizada","emple_a_3_peticion_finalizada"};
       String datos[]=new String [13];
       if(this.tbl_buscar_peticion.getRowCount()!=0)
        {
            tabla_peticion.setRowCount(0);
        try {
            while(rs.next())
            {
                for(int x=0; x<id_datos.length;x++)
                {
                    datos[x]=rs.getString(id_datos[x]);
                }
                tabla_peticion.addRow(datos);    
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else
        {
            try {
            while(rs.next())
            {
                for(int x=0; x<id_datos.length;x++)
                {
                    datos[x]=rs.getString(id_datos[x]);
                }
                tabla_peticion.addRow(datos);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
           }
    private void llenar_combox_peticion() {
        ArrayList<String> nombres_lista =new ArrayList<>();
        ArrayList<String> estado_lista=new ArrayList<>();
       try {
           rs=rdm.sql("SELECT id_peticiones,estado_peticion FROM peticiones");
           while(rs.next())
           {
               id_peti_combo_buscar_peticion.addItem(rs.getString("id_peticiones"));
               if(!estado_lista.contains(rs.getString("estado_peticion")))
               {
                   estado_lista.add(rs.getString("estado_peticion"));
               }
                   }
           rs=rdm.sql("SELECT id_empleado,nombre_completo_empleado FROM empleado");
           while(rs.next())
           {
               lista_id_emple_peticion.add(rs.getString("id_empleado"));
               if(!nombres_lista.contains(rs.getString("nombre_completo_empleado")))
               {
                   nombres_lista.add(rs.getString("nombre_completo_empleado"));
               }
           }
           for(String n:nombres_lista)
           {
               this.nombres_combo_buscar_peticion.addItem(n);
           }
           for(String e:estado_lista)
           {
               this.estado_combo_buscar_peticion.addItem(e);
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(Buscar_peticiones.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public String[] traer_datos_peticion()
    {
        String d[]=new String [5];
        int id_peti_itenm_combo=this.id_peti_combo_buscar_peticion.getSelectedIndex();
        int id_nomb_item_combo=nombres_combo_buscar_peticion.getSelectedIndex();
        String id_item=this.id_peti_combo_buscar_peticion.getSelectedItem().toString();
        String nom_emple_item_combo=this.nombres_combo_buscar_peticion.getSelectedItem().toString();
        String estado_item_combo=this.estado_combo_buscar_peticion.getSelectedItem().toString();
        if(id_item.isEmpty()&&nom_emple_item_combo.isEmpty()&&estado_item_combo.isEmpty())
        {
                for(int x=0;x<d.length;x++)
                {
                      d[x]=null;
                }
                return d;
        }else
        {
        d[0]=id_peti_itenm_combo+"";
        d[1]=id_item;
        d[2]=nom_emple_item_combo;
        d[3]=estado_item_combo;
        d[4]=id_nomb_item_combo+"";
        return d;
        }
        
    }
}
