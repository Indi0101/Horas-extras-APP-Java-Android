
package Formularios;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pconexionsql.resgistro_datos_mysql;

/**
 *
 * @author CelesteZaldivar
 */
public class categoria_problema extends javax.swing.JDialog {
    public  ResultSet rs=null;
    public resgistro_datos_mysql rdm=new resgistro_datos_mysql();
    public  String id_selecionado_problema="";
    public  String id_selecionado_categoria="";
    DefaultTableModel tabla_categoria = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };
     DefaultTableModel tabla_problema = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };
    public categoria_problema() {
        initComponents();
        this.setModal(true); 
        this.setLocationRelativeTo(null);
        crear_columnas_tabla_categoria();
        crear_columnas_tabla_problema();
        llenar_tabla_categorias();
        llenar_tabla_problema();
        llenar_combo();
    }
    private void llenar_combo() {
        try {
            rs=rdm.sql("SELECT nombre_categoria FROM categorias_problema");
            if(combo_id_categoria.getSelectedIndex()<0)
            {
                 while(rs.next())
                {
                    this.combo_id_categoria.addItem(rs.getString("nombre_categoria"));
                }
            }else
            {
                this.combo_id_categoria.removeAllItems();
                 while(rs.next())
                {
                    this.combo_id_categoria.addItem(rs.getString("nombre_categoria"));
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(categoria_problema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void crear_columnas_tabla_categoria()
    {
        tabla_categoria.addColumn("Id");
        tabla_categoria.addColumn("Nombre");
        this.tbl_ingresar_categoria.setModel(tabla_categoria);
        this.tbl_ingresar_categoria.setAutoscrolls (true);
    }
    public void crear_columnas_tabla_problema()
    {
        tabla_problema.addColumn("Id");
        tabla_problema.addColumn("Nombre categoria");
        tabla_problema.addColumn("Nombre");
        this.tbl_ingresar_problema.setModel(tabla_problema);
        this.tbl_ingresar_problema.setAutoscrolls (true);
    }
    public void llenar_tabla_categorias()
    {
     
       String datos[]=new String[2];
        if(this.tbl_ingresar_categoria.getRowCount()!=0 )
        {
            tabla_categoria.setRowCount(0);
            try {
                rs=rdm.traer_datos_todos("categorias_problema");
                while(rs.next())
                { 
                    datos[0]=rs.getString("id_categoria");
                    datos[1]=rs.getString("nombre_categoria");
                    tabla_categoria.addRow(datos);
                }
                 
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          }else{
            try {
                rs=rdm.traer_datos_todos("categorias_problema");
                while(rs.next())
                {
                    datos[0]=rs.getString("id_categoria");
                    datos[1]=rs.getString("nombre_categoria");
                   tabla_categoria.addRow(datos);
                }
             
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      public void llenar_tabla_problema()
    {
       String datos[]=new String[3];
        if(this.tbl_ingresar_problema.getRowCount()!=0 )
        {
            tabla_problema.setRowCount(0);
            try {
                rs=rdm.traer_datos_todos("problema");
                while(rs.next())
                { 
                    datos[0]=rs.getString("id_problema");
                    datos[1]=rs.getString("nombre_categoria");
                    datos[2]=rs.getString("nombre_problema");
                    tabla_problema.addRow(datos);
                }
                 
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          }else{
            try {
                rs=rdm.traer_datos_todos("problema");
                while(rs.next())
                {

                    datos[0]=rs.getString("id_problema");
                    datos[1]=rs.getString("nombre_categoria");
                    datos[2]=rs.getString("nombre_problema");
                    tabla_problema.addRow(datos);
                }
             
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public String cargar_datos_categoria()
    {
        String d="";
        if(this.nombre_categoria.getText().isEmpty())
        {
            this.nombre_categoria.setBackground(Color.red);
           d="";
        }
        else
        {
            this.nombre_categoria.setBackground(Color.white);
            d=this.nombre_categoria.getText();
           
        }
        if(!d.isEmpty())
        {
            return d;
        }else
        {
         ///JOptionPane.showMessageDialog(this, "Hay datos vacios");
         return "";
        }
        
    }
    public String[] cargar_datos_problema()
    {
        String d[]=new String[2];
          if(this.nombre_problema.getText().isEmpty())
        {
            this.nombre_problema.setBackground(Color.red);
          d[0]="";
        }
        else
        {
           this.nombre_problema.setBackground(Color.white);
           d[0]=this.nombre_problema.getText();
        }
        d[1]=this.combo_id_categoria.getSelectedItem().toString();
       
      return d;
      
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nombre_problema = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btn_guardar__problema = new javax.swing.JButton();
        btn_limpiar_problema = new javax.swing.JButton();
        btn_modificar_problema = new javax.swing.JButton();
        javax.swing.JButton btn_eliminar_problema = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_ingresar_problema = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        combo_id_categoria = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nombre_categoria = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_guardar_categoria = new javax.swing.JButton();
        btn_limpiar_categoria = new javax.swing.JButton();
        btn_modificar_categoria = new javax.swing.JButton();
        btn_eliminar_categoria = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_ingresar_categoria = new javax.swing.JTable();
        btn_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));

        jPanel1.setBackground(java.awt.Color.white);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Ingresar nombre de problema");
        jLabel5.setToolTipText("");

        btn_guardar__problema.setBackground(java.awt.Color.lightGray);
        btn_guardar__problema.setText("Guardar");
        btn_guardar__problema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar__problemaActionPerformed(evt);
            }
        });

        btn_limpiar_problema.setBackground(java.awt.Color.lightGray);
        btn_limpiar_problema.setText("Limpiar");
        btn_limpiar_problema.setToolTipText("");
        btn_limpiar_problema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_limpiar_problemaevento_clip_limpiar(evt);
            }
        });

        btn_modificar_problema.setBackground(java.awt.Color.lightGray);
        btn_modificar_problema.setText("Midificar");
        btn_modificar_problema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modificar_problemaevento_clip_modificar(evt);
            }
        });

        btn_eliminar_problema.setBackground(java.awt.Color.lightGray);
        btn_eliminar_problema.setText("Eliminar");
        btn_eliminar_problema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminar_problemaevento_clip_eliminar(evt);
            }
        });

        tbl_ingresar_problema.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_ingresar_problema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ingresar_problemaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_ingresar_problema);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ingresar un nuevo problema");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Seleccionar categoria");
        jLabel8.setToolTipText("");

        combo_id_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_id_categoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(combo_id_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(nombre_problema, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btn_guardar__problema)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_limpiar_problema)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_modificar_problema)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_eliminar_problema)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre_problema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_id_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_guardar__problema)
                            .addComponent(btn_limpiar_problema)
                            .addComponent(btn_modificar_problema)
                            .addComponent(btn_eliminar_problema)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(java.awt.Color.white);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Ingresar una nueva categoria ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Ingresar nombre de categoria");
        jLabel7.setToolTipText("");

        btn_guardar_categoria.setBackground(java.awt.Color.lightGray);
        btn_guardar_categoria.setText("Guardar");
        btn_guardar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_categoriaActionPerformed(evt);
            }
        });

        btn_limpiar_categoria.setBackground(java.awt.Color.lightGray);
        btn_limpiar_categoria.setText("Limpiar");
        btn_limpiar_categoria.setToolTipText("");
        btn_limpiar_categoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_limpiar_categoriaevento_clip_limpiar(evt);
            }
        });

        btn_modificar_categoria.setBackground(java.awt.Color.lightGray);
        btn_modificar_categoria.setText("Midificar");
        btn_modificar_categoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modificar_categoriaevento_clip_modificar(evt);
            }
        });

        btn_eliminar_categoria.setBackground(java.awt.Color.lightGray);
        btn_eliminar_categoria.setText("Eliminar");
        btn_eliminar_categoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminar_categoriaevento_clip_eliminar(evt);
            }
        });

        tbl_ingresar_categoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_ingresar_categoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ingresar_categoriaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_ingresar_categoria);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombre_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(btn_guardar_categoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_limpiar_categoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_modificar_categoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_eliminar_categoria)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_guardar_categoria)
                            .addComponent(btn_limpiar_categoria)
                            .addComponent(btn_modificar_categoria)
                            .addComponent(btn_eliminar_categoria)))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_salir.setBackground(java.awt.Color.lightGray);
        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_salir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardar__problemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar__problemaActionPerformed
       String datos[]= cargar_datos_problema();
      for(int x=0;x<datos.length;x++)
       {
           if(datos[x].isEmpty())
           {
               JOptionPane.showMessageDialog(this, "Hay campos vacios");
               return;
           }
       }
      try {
            String msj=rdm.guardarDatos(datos, "problema");
            System.err.println(""+msj);
          } catch (SQLException ex) {
                   Logger.getLogger(categoria_problema.class.getName()).log(Level.SEVERE, null, ex);
          }
      llenar_tabla_problema();
    }//GEN-LAST:event_btn_guardar__problemaActionPerformed

    private void btn_limpiar_problemaevento_clip_limpiar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_limpiar_problemaevento_clip_limpiar
         this.nombre_problema.setText("");
    }//GEN-LAST:event_btn_limpiar_problemaevento_clip_limpiar

    private void btn_modificar_problemaevento_clip_modificar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificar_problemaevento_clip_modificar
       String datos[]=cargar_datos_problema();
       
       if(!id_selecionado_problema.isEmpty())
       { 
           String d[]={id_selecionado_problema,datos[0],datos[1]};
            for(int x=0; x<datos.length;x++)
            {
                if(datos[x].isEmpty())
                {
                    JOptionPane.showMessageDialog(this, "Hay campos vacios");
                    return;
                }
            }
        rdm.modificar_pro_cate("problema", d);
        System.err.println("datos tbl_problema modificados");
        llenar_tabla_problema();
       }
        
    }//GEN-LAST:event_btn_modificar_problemaevento_clip_modificar

    private void btn_eliminar_problemaevento_clip_eliminar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminar_problemaevento_clip_eliminar
     if(!id_selecionado_problema.isEmpty())
     {
         String msj=rdm.eliminar_fila("problema", id_selecionado_problema, "id_problema");
         System.err.println(msj);
         llenar_tabla_problema();
     }
    }//GEN-LAST:event_btn_eliminar_problemaevento_clip_eliminar

    private void tbl_ingresar_problemaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ingresar_problemaMouseClicked
       int fila_Seleccionada=this.tbl_ingresar_problema.rowAtPoint(evt.getPoint());
       id_selecionado_problema=this.tbl_ingresar_problema.getValueAt(fila_Seleccionada, 0).toString(); 
       this.combo_id_categoria.getModel().setSelectedItem(this.tbl_ingresar_problema.getValueAt(fila_Seleccionada, 1).toString());
       this.nombre_problema.setText(this.tbl_ingresar_problema.getValueAt(fila_Seleccionada, 2).toString());
    }//GEN-LAST:event_tbl_ingresar_problemaMouseClicked

    private void btn_guardar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_categoriaActionPerformed
       String d[]=new String[2];
        d[0]=cargar_datos_categoria();
       if(d[0].isEmpty())
       {
           JOptionPane.showMessageDialog(this, "Hay campos vacios");
           return;
       }
       try {
               String msg=rdm.guardarDatos(d,"categorias_problema");
               System.err.println(""+msg);
           } catch (SQLException ex) {
               Logger.getLogger(categoria_problema.class.getName()).log(Level.SEVERE, null, ex);
           }
       llenar_tabla_categorias();
       llenar_combo();
    }//GEN-LAST:event_btn_guardar_categoriaActionPerformed

    private void btn_limpiar_categoriaevento_clip_limpiar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_limpiar_categoriaevento_clip_limpiar
        this.nombre_categoria.setText("");
    }//GEN-LAST:event_btn_limpiar_categoriaevento_clip_limpiar

    private void btn_modificar_categoriaevento_clip_modificar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificar_categoriaevento_clip_modificar
        String n_cate=cargar_datos_categoria();
       if(!n_cate.isEmpty()&&!id_selecionado_categoria.isEmpty())
       {
           String datos[]={id_selecionado_categoria,n_cate};
           rdm.modificar_pro_cate("categorias_problema", datos);
           System.err.println("Se modifico tbl_categoria");
       }else
       {
           JOptionPane.showMessageDialog(this, "Hay campos vacios");
           return;
       }
       llenar_tabla_categorias();
       llenar_combo();
    }//GEN-LAST:event_btn_modificar_categoriaevento_clip_modificar
      
       
       
    private void btn_eliminar_categoriaevento_clip_eliminar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminar_categoriaevento_clip_eliminar
       if(!id_selecionado_categoria.isEmpty())
     {
         String msj=rdm.eliminar_fila("categorias_problema", id_selecionado_categoria, "id_categoria");
         System.err.println(msj);
         llenar_tabla_categorias();
         llenar_combo();
     }
       
    }//GEN-LAST:event_btn_eliminar_categoriaevento_clip_eliminar

    private void tbl_ingresar_categoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ingresar_categoriaMouseClicked
         int fila_Seleccionada=this.tbl_ingresar_categoria.rowAtPoint(evt.getPoint());
       id_selecionado_categoria=this.tbl_ingresar_categoria.getValueAt(fila_Seleccionada, 0).toString(); 
       this.nombre_categoria.setText(this.tbl_ingresar_categoria.getValueAt(fila_Seleccionada, 1).toString());
    }//GEN-LAST:event_tbl_ingresar_categoriaMouseClicked

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
       dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void combo_id_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_id_categoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_id_categoriaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new categoria_problema().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar_categoria;
    private javax.swing.JButton btn_guardar__problema;
    private javax.swing.JButton btn_guardar_categoria;
    private javax.swing.JButton btn_limpiar_categoria;
    private javax.swing.JButton btn_limpiar_problema;
    private javax.swing.JButton btn_modificar_categoria;
    private javax.swing.JButton btn_modificar_problema;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox<String> combo_id_categoria;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField nombre_categoria;
    private javax.swing.JTextField nombre_problema;
    private javax.swing.JTable tbl_ingresar_categoria;
    private javax.swing.JTable tbl_ingresar_problema;
    // End of variables declaration//GEN-END:variables

    
}
