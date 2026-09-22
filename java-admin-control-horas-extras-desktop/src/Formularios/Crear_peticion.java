/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pconexionsql.resgistro_datos_mysql;
/**
 *
 * @author CelesteZaldivar
 */
public final class Crear_peticion extends javax.swing.JDialog {
    public resgistro_datos_mysql registroDatos=new resgistro_datos_mysql();
    public ResultSet rs=null;
    public String id_selecionado; 
    public ArrayList<String> emple=new ArrayList<>();
    public ArrayList<String> emple_infa=new ArrayList<>();
    public ArrayList<String> agencias=new ArrayList<>();
    public DefaultTableModel tabla = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return true;
        }
    }; 
    public Timer temporizador;
    public TimerTask verificar_hora_correcta;
    public Crear_peticion() {
            
            initComponents();
            this.setModal(true); 
            this.setLocationRelativeTo(null);
           hilo_hora_fecha();
          try {  
            
             emple=datos_combobox("nombre_completo_empleado","empleado",0);
             emple_infa=datos_combobox("nombre_completo_empleado","empleado",1);
             agencias=datos_combobox("nombre_agencias","agencias",2);
             
              agencias.forEach((egencia) -> {
                  this.nombre_agencia_peticiones.addItem(egencia);
                });
              emple.forEach((emple2) ->{
                  this.enviPor_peticiones.addItem(emple2);
              }
              );
                      } catch (SQLException ex) {
            Logger.getLogger(Crear_peticion.class.getName()).log(Level.SEVERE, null, ex);
        }
        crear_columnas_tabla();
        llenar_tabla();
        llenar_combo_categoria();
        this.combo_cate_proble.addActionListener((ActionEvent arg0) -> {
            llenar_combo_problema(combo_cate_proble.getSelectedItem().toString());
            });
        llenar_combo_problema(combo_cate_proble.getSelectedItem().toString());
        temporizador = new Timer();
        temporizador.scheduleAtFixedRate(verificar_hora_correcta,0,1*1000);
    }
    public final ArrayList datos_combobox(String d,String tabla,int v) throws SQLException
    {
        ArrayList<String> datos=new ArrayList<>();
        switch(v){
            case 0:
               rs=registroDatos.sql("SELECT "+d+" FROM "+tabla+" WHERE puesto_empleado <> 'Infatlan'");
                while(rs.next())
                {
                    datos.add(rs.getString(d));
                }
                registroDatos.cerrar_conexion();
            break;
            case 1:
                rs=registroDatos.sql("SELECT "+d+" FROM "+tabla+" WHERE puesto_empleado = 'Infatlan'");
                while(rs.next())
                {
                    datos.add(rs.getString(d));
                }
                  registroDatos.cerrar_conexion();
            break;
             case 2:
                rs=registroDatos.sql("SELECT "+d+" FROM "+tabla+"");
                while(rs.next())
                {
                    datos.add(rs.getString(d));
                }
                  registroDatos.cerrar_conexion();
            break;
                
        }
        
        return datos;
    }
    public void llenar_combo_categoria()
    {
        try {
            rs=registroDatos.traer_datos_todos("categorias_problema");
            while(rs.next())
            {
                this.combo_cate_proble.addItem(rs.getString("nombre_categoria"));
            }
            registroDatos.cerrar_conexion();
        } catch (SQLException ex) {
            Logger.getLogger(Crear_peticion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void llenar_combo_problema(String item)
    {
        if(combo_cate_proble.getSelectedIndex()>=0)
        {
            // String item=combo_cate_proble.getSelectedItem().toString();
             if(combo_problema.getModel().getSize()<0)
             {
                 System.err.println("esta vacio combo problema");
                 metodo_combo_problema(item);
             }else
             {
                 combo_problema.removeAllItems();
                 metodo_combo_problema(item);
                 System.err.println("esta lleno combo problema");
               
             }
        }
    }
    public void metodo_combo_problema(String item)
    {
         try {
              rs=registroDatos.sql("SELECT * FROM problema WHERE nombre_categoria='"+item+"'");
                while(rs.next())
                 {
                   System.out.println("dentro del while");
                   this.combo_problema.addItem(rs.getString("nombre_problema"));
                 }
                  registroDatos.cerrar_conexion();
              } catch (SQLException ex) {
                Logger.getLogger(Crear_peticion.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("algo salio mal");
              }
    }
     public void crear_columnas_tabla()
    {
        tabla.addColumn("Codigo");
        tabla.addColumn("Solicitado por:");
        tabla.addColumn("Descripcion");
        tabla.addColumn("Hora");
        tabla.addColumn("Fecha");
        tabla.addColumn("Agencia");
        tabla.addColumn("Problema");
        tabla.addColumn("Estado");
        this.tbl_peticiones.setModel(tabla);
        this.tbl_peticiones.setAutoscrolls (true);
    }
    public String[] cargar_datos()
    {
         String datos[]=new String[8];
        try {
           
            String a1[]=this.enviPor_peticiones.getSelectedItem().toString().split("-");
            String a3[]=this.nombre_agencia_peticiones.getSelectedItem().toString().split("-");
            String i="";
            rs=registroDatos.sql("SELECT id_agencias FROM agencias WHERE nombre_agencias='"+a3[0]+"'");
            while(rs.next())
            {
                i=rs.getString("id_agencias");
            }
            datos[6]=this.combo_problema.getSelectedItem().toString();
            datos[7]="Disponible";
            datos[5]=i;
            datos[4]=this.fecha_peticiones.getText();
            datos[3]=this.hora_peticiones.getText();
            datos[0]=a1[0];
            datos[1]="null";
            
            datos[2]=this.descrip_peticiones.getText();
            
            for(String d: datos)
            {
                if(d.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "hay campos vacíos");
                    return null;
                }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Crear_peticion.class.getName()).log(Level.SEVERE, null, ex);
        }
         return datos;
    }
    public void llenar_tabla()
    {
        if(this.tbl_peticiones.getRowCount()!=0 )
        {
            tabla.setRowCount(0);
          traer_datos_tabla();
            
          }else{
           traer_datos_tabla();
        }
    } 
    public void traer_datos_tabla()
    {
        String datos[]=new String[8];
        try {
                rs=registroDatos.sql("SELECT id_peticiones,emple_envio_peticiones,estado_peticion,descripcion_peticion,hora_peticion,fecha_peticion,nombre_agencias,nombre_problema FROM peticiones,agencias WHERE peticiones.n_agencia_peticion=agencias.Id_agencias");
                while(rs.next())
                { 
                    datos[0]=rs.getString("id_peticiones");
                    datos[1]=rs.getString("emple_envio_peticiones");
                    datos[2]=rs.getString("descripcion_peticion");
                    datos[3]=rs.getString("hora_peticion");
                    datos[4]=rs.getString("fecha_peticion"); 
                    datos[5]=rs.getString("nombre_agencias");
                    datos[6]=rs.getString("nombre_problema");
                    datos[7]=rs.getString("estado_peticion");
                    
                    tabla.addRow(datos);
                }
                   registroDatos.cerrar_conexion();
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            String f=formatoFecha.format(date);
            fecha_peticiones.setText(fecha);
            hora_peticiones.setText(hora);
            }
        };
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        id_peticiones = new javax.swing.JTextField();
        enviPor_peticiones = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        eti2 = new javax.swing.JLabel();
        eti1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nombre_agencia_peticiones = new javax.swing.JComboBox<>();
        panel1 = new java.awt.Panel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_peticiones = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        descrip_peticiones = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        btn_guardar_peticiones = new javax.swing.JButton();
        btn_limpiar_peticiones = new javax.swing.JButton();
        btn_modificar_peticiones = new javax.swing.JButton();
        btn_eliminar_peticiones = new javax.swing.JButton();
        btn_salir_peticiones = new javax.swing.JButton();
        hora_peticiones = new javax.swing.JTextField();
        fecha_peticiones = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        combo_cate_proble = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        combo_problema = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setResizable(false);

        jLabel1.setText("Num. Peticion");

        id_peticiones.setEditable(false);
        id_peticiones.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        enviPor_peticiones.setName("combo_envi"); // NOI18N

        jLabel3.setText("Solicitado por:");

        eti2.setText("Fecha");

        eti1.setText("Hora");

        jLabel6.setText("Agencia");

        panel1.setBackground(java.awt.SystemColor.activeCaptionBorder);

        tbl_peticiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_peticiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_peticionesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_peticiones);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        descrip_peticiones.setColumns(20);
        descrip_peticiones.setRows(5);
        jScrollPane1.setViewportView(descrip_peticiones);

        jLabel7.setText("Descripcion:");

        btn_guardar_peticiones.setText("Guardar");
        btn_guardar_peticiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_guardar_peticionesMouseClicked(evt);
            }
        });

        btn_limpiar_peticiones.setText("Limpiar");
        btn_limpiar_peticiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiar_peticionesActionPerformed(evt);
            }
        });

        btn_modificar_peticiones.setText("Modificar");
        btn_modificar_peticiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_modificar_peticionesMouseClicked(evt);
            }
        });

        btn_eliminar_peticiones.setText("Eliminar");
        btn_eliminar_peticiones.setToolTipText("");
        btn_eliminar_peticiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminar_peticionesMouseClicked(evt);
            }
        });

        btn_salir_peticiones.setText("Salir");
        btn_salir_peticiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_salir_peticionesMouseClicked(evt);
            }
        });

        hora_peticiones.setEditable(false);

        fecha_peticiones.setEditable(false);

        jLabel2.setText("Categoria de problema");

        jLabel4.setText("Tipo de problema");

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Crear una nueva preticion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combo_cate_proble, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(combo_problema, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(51, 51, 51)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombre_agencia_peticiones, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 682, Short.MAX_VALUE)
                                .addComponent(btn_guardar_peticiones)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_limpiar_peticiones)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_modificar_peticiones)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_eliminar_peticiones)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_salir_peticiones))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(id_peticiones, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(eti1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hora_peticiones, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eti2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fecha_peticiones, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enviPor_peticiones, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fecha_peticiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(id_peticiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(eti1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(enviPor_peticiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hora_peticiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(eti2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combo_cate_proble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre_agencia_peticiones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(combo_problema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar_peticiones)
                    .addComponent(btn_limpiar_peticiones)
                    .addComponent(btn_modificar_peticiones)
                    .addComponent(btn_eliminar_peticiones)
                    .addComponent(btn_salir_peticiones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_limpiar_peticionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiar_peticionesActionPerformed
     this.descrip_peticiones.setText("");
     this.id_peticiones.setText("");
    }//GEN-LAST:event_btn_limpiar_peticionesActionPerformed

    private void btn_guardar_peticionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardar_peticionesMouseClicked
        String datos[]=cargar_datos();
        String id="";
        if(id_selecionado!=null&&!id_selecionado.isEmpty())
        {
            try {
                int id_agencia=Integer.parseInt(datos[5]);
            rs=registroDatos.sql("SELECT id_peticiones FROM peticiones WHERE emple_envio_peticiones='"+datos[0]+"' AND descripcion_peticion='"+datos[2]+"' AND fecha_peticion='"+datos[4]+"' AND n_agencia_peticion="+id_agencia+" AND nombre_problema='"+datos[7]+"' ");
            while(rs.next())
            {
                id=rs.getString("id_peticiones");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Crear_peticion.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        if(datos!=null)
        {
             if(id==null||id.isEmpty())
            {
                try {
                    String msj=registroDatos.guardarDatos(datos, "peticiones");
                     this.descrip_peticiones.setText("");
                     llenar_tabla();
                       registroDatos.cerrar_conexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Crear_peticion.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else
            {
                JOptionPane.showMessageDialog(null, "Estos datos ya existen: "+id_selecionado);
                id_selecionado="";
            }
        
        }
        
    }//GEN-LAST:event_btn_guardar_peticionesMouseClicked

    private void tbl_peticionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_peticionesMouseClicked
       
        int fila_Seleccionada=this.tbl_peticiones.rowAtPoint(evt.getPoint());
        String datos[]=new String[7];
        for (int i = 0; i < datos.length; i++) {
            datos[i]=tbl_peticiones.getValueAt(fila_Seleccionada, i).toString();
        }
        this.id_selecionado=datos[0];
        this.id_peticiones.setText(id_selecionado);
        this.descrip_peticiones.setText(datos[2]);
        this.enviPor_peticiones.getModel().setSelectedItem(datos[1]);
        this.nombre_agencia_peticiones.getModel().setSelectedItem(datos[5]);
        this.combo_problema.getModel().setSelectedItem(datos[6]);
    }//GEN-LAST:event_tbl_peticionesMouseClicked

    private void btn_modificar_peticionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_modificar_peticionesMouseClicked
        // TODO add your handling code here:
        String datos[]=cargar_datos();
        if(datos!=null)
        {
            String msj=registroDatos.modificar_fila("peticiones", id_selecionado, datos);
            this.descrip_peticiones.setText("");
            llenar_tabla();
        }
    }//GEN-LAST:event_btn_modificar_peticionesMouseClicked

    private void btn_eliminar_peticionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminar_peticionesMouseClicked
       if(id_selecionado!=null&&!id_selecionado.isEmpty())
       {
           int i=JOptionPane.showConfirmDialog(null,"¿Seguro que quiere eliminar esta fila?\n"+"Id : "+id_selecionado);
            if(i==0)
            {
                String msj=registroDatos.eliminar_fila("peticiones", id_selecionado, "id_peticiones ");
                this.descrip_peticiones.setText("");
                llenar_tabla();
            }
       }else
       {
           JOptionPane.showMessageDialog(null, "Seleccione un elemento de la tabla para eliminar");
       }
        
    }//GEN-LAST:event_btn_eliminar_peticionesMouseClicked

    private void btn_salir_peticionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salir_peticionesMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_salir_peticionesMouseClicked
    public int recorrer_combo(String item, int v)
    {
      
       switch(v)
       {
           case 0:
                System.err.println("Item:"+item+"  "+emple_infa.size());
              for(int x=0; x<emple_infa.size(); x++)
                {
                    if(emple_infa.get(x).equalsIgnoreCase(item))
                    {
                        System.err.println("Listo: id "+x);
                       return x;
                    }
                }
            break;
            
            case 1:
                System.err.println("Item:"+item+"  "+emple.size());
              for(int x=0; x<emple.size(); x++)
                {
                    if(emple_infa.get(x).equalsIgnoreCase(item))
                    {
                        System.err.println("Listo: id "+x);
                       return x;
                    }
                }
            break;
             case 2:
                System.err.println("Item:"+item+"  "+agencias.size());
              for(int x=0; x<agencias.size(); x++)
                {
                    if(agencias.get(x).equalsIgnoreCase(item))
                    {
                        System.err.println("Listo: id "+x);
                       return x;
                    }
                }
            break;
       }
      
        return 0;
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Crear_peticion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Crear_peticion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Crear_peticion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Crear_peticion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Crear_peticion().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar_peticiones;
    private javax.swing.JButton btn_guardar_peticiones;
    private javax.swing.JButton btn_limpiar_peticiones;
    private javax.swing.JButton btn_modificar_peticiones;
    private javax.swing.JButton btn_salir_peticiones;
    private javax.swing.JComboBox<String> combo_cate_proble;
    private javax.swing.JComboBox<String> combo_problema;
    private javax.swing.JTextArea descrip_peticiones;
    private javax.swing.JComboBox<String> enviPor_peticiones;
    private javax.swing.JLabel eti1;
    private javax.swing.JLabel eti2;
    private javax.swing.JTextField fecha_peticiones;
    private javax.swing.JTextField hora_peticiones;
    private javax.swing.JTextField id_peticiones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> nombre_agencia_peticiones;
    private java.awt.Panel panel1;
    private javax.swing.JTable tbl_peticiones;
    // End of variables declaration//GEN-END:variables
}