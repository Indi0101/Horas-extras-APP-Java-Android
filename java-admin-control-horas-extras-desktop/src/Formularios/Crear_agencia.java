
package Formularios;

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
public final class Crear_agencia extends javax.swing.JDialog {

    
        
    public resgistro_datos_mysql registroDatos=new resgistro_datos_mysql();
    public ResultSet rs=null;
    public  String id_selecionado; 
    DefaultTableModel tabla = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    }; 
    public String depar[]={"Atlantida","Colon","La Paz","Santa Barbara","Olancho","Cortes","Gracias a Dios","El Paraiso","Choluteca","Valle","Franciasco Morazan","Intibuca","Yoro","Ocotepeque","Lempira","Islas de la Bahia"};
    public Crear_agencia() {
        initComponents();
        this.setModal(true); 
         this.setModal(true); 
        for(String d:depar)
        {
            this.departamento_agencia.addItem(d);
        }
     llenar_combo();
    crear_columnas_tabla();
    llenar_tabla();
    }
    public void llenar_combo()
    {
         for(String d:depar)
        {
            this.departamento_agencia.addItem(d);
        }
    }
     public void crear_columnas_tabla()
    {
        tabla.addColumn("Id");
        tabla.addColumn("Nombre");
        tabla.addColumn("Departamento");
        tabla.addColumn("Municipio");
        tabla.addColumn("Direccion");
        tabla.addColumn("Cordenadas");
        this.tbl_agencias.setModel(tabla);
        this.tbl_agencias.setAutoscrolls (true);
    }
     public void llenar_tabla()
    {
       // int f=this.tbl_ingresar_puesto.getSelectedRowCount();
      //  JOptionPane.showMessageDialog(null, f);
          this.setLocationRelativeTo(null); 
       String datos[]=new String[6];
        if(this.tbl_agencias.getRowCount()!=0 )
        {
            tabla.setRowCount(0);
            try {
                rs=registroDatos.traer_datos_todos("agencias");
                while(rs.next())
                { 
                    datos[0]=rs.getString("Id_agencias");
                    datos[1]=rs.getString("nombre_agencias");
                    datos[2]=rs.getString("departamento_agencias");
                    datos[3]=rs.getString("municipio_agencias");
                    datos[4]=rs.getString("direccion_agencias");
                    datos[5]=rs.getString("cordenada_agencias");
                    tabla.addRow(datos);
                }
                 
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          }else{
            try {
                rs=registroDatos.traer_datos_todos("agencias");
                while(rs.next())
                { 
                    datos[0]=rs.getString("Id_agencias");
                    datos[1]=rs.getString("nombre_agencias");
                    datos[2]=rs.getString("departamento_agencias");
                    datos[3]=rs.getString("municipio_agencias");
                    datos[4]=rs.getString("direccion_agencias");
                    datos[5]=rs.getString("cordenada_agencias");
                    tabla.addRow(datos);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_agencias = new javax.swing.JTable();
        btn_guardar_agencia = new javax.swing.JButton();
        btn_limpiar_agencia = new javax.swing.JButton();
        btn_modificar_agencia = new javax.swing.JButton();
        btn_eliminar_agencia = new javax.swing.JButton();
        btn_salir_agencia = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        direccion_agencia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        municipio_agencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        departamento_agencia = new javax.swing.JComboBox<>();
        nombre_agencia = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cordenada_agencia = new javax.swing.JTextField();
        label1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panel1.setName("Cordenadas"); // NOI18N

        tbl_agencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_agencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_agenciasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_agencias);

        btn_guardar_agencia.setText("Guardar");
        btn_guardar_agencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_guardar_agenciaMouseClicked(evt);
            }
        });

        btn_limpiar_agencia.setText("Limpiar");
        btn_limpiar_agencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_limpiar_agenciaMouseClicked(evt);
            }
        });

        btn_modificar_agencia.setText("Modificar");
        btn_modificar_agencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modificar_agenciaMouseClicked(evt);
            }
        });

        btn_eliminar_agencia.setText("Eliminar");
        btn_eliminar_agencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminar_agenciaMouseClicked(evt);
            }
        });

        btn_salir_agencia.setText("Salir");
        btn_salir_agencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_salir_agenciaMouseClicked(evt);
            }
        });

        label.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        label.setText("Direccion");

        direccion_agencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccion_agenciaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Municipio");

        municipio_agencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                municipio_agenciaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Departamento");

        departamento_agencia.setToolTipText("");
        departamento_agencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departamento_agenciaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Nombre");

        cordenada_agencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cordenada_agenciaActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        label1.setText("Cordenadas");
        label1.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ingresar una nueva agencia");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btn_guardar_agencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_limpiar_agencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_modificar_agencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_eliminar_agencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir_agencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cordenada_agencia))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(departamento_agencia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(municipio_agencia)
                                    .addComponent(nombre_agencia)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(direccion_agencia)))
                        .addGap(12, 12, 12)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nombre_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(departamento_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(municipio_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(39, 39, 39)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label)
                            .addComponent(direccion_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(cordenada_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_eliminar_agencia)
                            .addComponent(btn_salir_agencia)
                            .addComponent(btn_modificar_agencia)
                            .addComponent(btn_limpiar_agencia)
                            .addComponent(btn_guardar_agencia))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_limpiar_agenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_limpiar_agenciaMouseClicked

       limpiar_campos();
    }//GEN-LAST:event_btn_limpiar_agenciaMouseClicked

    private void btn_guardar_agenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardar_agenciaMouseClicked
        // TODO add your handling code here:
        String[] datos=recoger_datos();
        String id="";
        if(id_selecionado!=null&&!id_selecionado.isEmpty())
        {
            try {
            rs=registroDatos.consultar_existencia_id("agencias", id_selecionado, "Id_agencias");
            while(rs.next())
            {
                id=rs.getString("Id_agencias");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Crear_peticion.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        for (String dato : datos) {
            if (null == dato || dato.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Hay campos vacíos");
                return;
            }
        }
         if(id==null||id.isEmpty())
            {
                try {
                   String msj=registroDatos.guardarDatos(datos,"agencias");
                     limpiar_campos();
                } catch (SQLException ex) {
                    Logger.getLogger(Crear_peticion.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else
            {
                JOptionPane.showMessageDialog(null, "Estos datos ya existen: "+id_selecionado);
                id_selecionado="";
            }
           llenar_tabla();
         
    }//GEN-LAST:event_btn_guardar_agenciaMouseClicked

    private void btn_salir_agenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salir_agenciaMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_salir_agenciaMouseClicked

    private void tbl_agenciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_agenciasMouseClicked
        // TODO add your handling code here:
         int fila_Seleccionada=this.tbl_agencias.rowAtPoint(evt.getPoint());
         id_selecionado=this.tbl_agencias.getValueAt(fila_Seleccionada, 0).toString();
         this.nombre_agencia.setText(this.tbl_agencias.getValueAt(fila_Seleccionada, 1).toString());
         this.municipio_agencia.setText(this.tbl_agencias.getValueAt(fila_Seleccionada, 3).toString());
         this.direccion_agencia.setText(this.tbl_agencias.getValueAt(fila_Seleccionada, 4).toString());
         this.cordenada_agencia.setText(this.tbl_agencias.getValueAt(fila_Seleccionada, 5).toString());
       
        this.departamento_agencia.getModel().setSelectedItem(this.tbl_agencias.getValueAt(fila_Seleccionada, 2).toString());
    }//GEN-LAST:event_tbl_agenciasMouseClicked

    private void btn_eliminar_agenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminar_agenciaMouseClicked
        // TODO add your handling code here:
        if(id_selecionado!=null)
        {
           int r=JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar esta fila?\n"+" Id : "+id_selecionado);
            if(r==0)
            {
              String msj=registroDatos.eliminar_fila("agencias", id_selecionado, "Id_agencias");
              llenar_tabla();
              limpiar_campos();
           
            }    
        }else
        {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para Eliminra");
        }
    }//GEN-LAST:event_btn_eliminar_agenciaMouseClicked

    private void btn_modificar_agenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificar_agenciaMouseClicked
        // TODO add your handling code here:
       String datos[]=recoger_datos();
       String msj;
        for (String dato : datos) {
            if (null == dato || dato.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Hay campos vacíos");
                return;
            }
        }
       if(id_selecionado!=null)
       {
            msj = registroDatos.modificar_fila("agencias", id_selecionado, datos);
           limpiar_campos();
            llenar_tabla();
           
       }else
       {
         JOptionPane.showMessageDialog(null, "Hay campos vacíos");
       }
        
    }//GEN-LAST:event_btn_modificar_agenciaMouseClicked

    private void municipio_agenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_municipio_agenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_municipio_agenciaActionPerformed

    private void direccion_agenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccion_agenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccion_agenciaActionPerformed

    private void cordenada_agenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cordenada_agenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cordenada_agenciaActionPerformed

    private void departamento_agenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departamento_agenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departamento_agenciaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Crear_agencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Crear_agencia().setVisible(true);
        });
    }
    public String[] recoger_datos()
    {
        String datos[]=new String[5];
        datos[0]=nombre_agencia.getText();
        datos[1]=departamento_agencia.getSelectedItem().toString();
        datos[2]=municipio_agencia.getText();
        datos[3]=direccion_agencia.getText();
        datos[4]=cordenada_agencia.getText();

        return datos;
    }
    public void limpiar_campos()
    {
        this.nombre_agencia.setText("");
        this.direccion_agencia.setText("");
        this.municipio_agencia.setText("");
        this.cordenada_agencia.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar_agencia;
    private javax.swing.JButton btn_guardar_agencia;
    private javax.swing.JButton btn_limpiar_agencia;
    private javax.swing.JButton btn_modificar_agencia;
    private javax.swing.JButton btn_salir_agencia;
    private javax.swing.JTextField cordenada_agencia;
    private javax.swing.JComboBox<String> departamento_agencia;
    private javax.swing.JTextField direccion_agencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label1;
    private javax.swing.JTextField municipio_agencia;
    private javax.swing.JTextField nombre_agencia;
    private java.awt.Panel panel1;
    private javax.swing.JTable tbl_agencias;
    // End of variables declaration//GEN-END:variables
}
