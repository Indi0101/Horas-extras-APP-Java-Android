
package Clases_reporte;

import Formularios.Ingresar_empleados;
import java.awt.event.ActionEvent;
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
public class Menu_reporte extends javax.swing.JDialog{
   public resgistro_datos_mysql rdm=new resgistro_datos_mysql();
   public ResultSet rs=null;
   public Clase_reportes clase_reportes=new Clase_reportes();
   
   public ArrayList<String> lista_id_emple_peticion =new ArrayList<>();
   public ArrayList<String> lista_id_agencia_peticion=new ArrayList<>();
   public ArrayList<String> lista_nombre_agencia_peticion=new ArrayList<>();
   public DefaultTableModel tabla_peticion = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };
   
   public ArrayList<String> lista_id_emple_entra_salida=new ArrayList<>();
   public ArrayList<String> lista_estado_entra_salida=new ArrayList<>();
   public ArrayList<String> lista_fecha_entra_salida=new ArrayList<>();
   public DefaultTableModel tabla_entra_salida = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {return false;}};
   
   public ArrayList<String> lista_id_emple_hora_extra=new ArrayList<>();
   public ArrayList<String> lista_nombre_hora_extra=new ArrayList<>();
   public DefaultTableModel tabla_hora_extra = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };
   
    public ArrayList<String> lista_id_sysaid=new ArrayList<>();
   public ArrayList<String> lista_nombre_sysaid=new ArrayList<>();
   public DefaultTableModel tabla_sysaid = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };
   
   public Menu_reporte() {
        initComponents();
        this.setModal(true); 
        this.setLocationRelativeTo(null); 
        crear_columnas_tabla_peticion();
        crea_columnas_tabla_entrada_salida();
        crear_columnas_tabla_hora_extra();
        crear_columnas_tabla_sysaid();
        this.estado_combo_buscar_peticion.addItem("Todos");
        this.id_peti_combo_buscar_peticion.addItem("Todos");
        this.nombres_combo_buscar_peticion.addItem("Todos");
        this.combo_nombre_empleado_sysaid.addItem("Todos");
        this.fecha__entrar_salida.addItem("Todo");
        this.nombre_emple_entrar_salida.addItem("Todo");
        this.estado_entrar_salida.addItem("Todo");
        this.fecha_h_e.addItem("Todos");
        this.nombres_h_e.addItem("Todos");
        this.estado_h_e.addItem("Todos");
        llenar_combox_peticion();
        llenar_combo_nombre_entrada_salida();
        llenar_combox_hora_extra();
        llenar_combo_fecha_estado_entrada_salida();
        llenar_combo_nombre_id_sysaid();
        // filtrar_datos_combox_id_peticiones();
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        panel_horas_extra = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        fecha_h_e = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        nombres_h_e = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        estado_h_e = new javax.swing.JComboBox<>();
        btn_buscar_h_e = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_h_e = new javax.swing.JTable();
        btn_imp_h_e = new javax.swing.JButton();
        btn_salir_h_e = new javax.swing.JButton();
        panel_entrada_salida = new javax.swing.JPanel();
        panel_interno_e_s = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        nombre_emple_entrar_salida = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        estado_entrar_salida = new javax.swing.JComboBox<>();
        buscar_entrar_salida = new javax.swing.JButton();
        fecha__entrar_salida = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_entrada_salida = new javax.swing.JTable();
        mostrar_entrar_salida = new javax.swing.JButton();
        salir_entrar_salida = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panel_peticiones = new javax.swing.JPanel();
        panel_interno_peticiones = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nombres_combo_buscar_peticion = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_buscar_peticion = new javax.swing.JTable();
        btn_buscar_peticion = new javax.swing.JButton();
        btn_imp_buscar_peticion = new javax.swing.JButton();
        btn_salir_buscar_peticion = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        estado_combo_buscar_peticion = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        id_peti_combo_buscar_peticion = new javax.swing.JComboBox<>();
        panel_hoja_de_servicio = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        combo_nombre_empleado_sysaid = new javax.swing.JComboBox<>();
        btn_buscar_sysaid = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_sysaid = new javax.swing.JTable();
        btn_imprimir_sysaid = new javax.swing.JButton();
        btn_salir_sysaid = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setForeground(new java.awt.Color(51, 51, 51));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        panel_horas_extra.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText("Buscar por:");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Generar reportes de archivos de horas extras");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel13.setText("Fecha ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel14.setText("Nombre de empleado");

        jLabel15.setText("Estado");

        btn_buscar_h_e.setText("Buscar");
        btn_buscar_h_e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_buscar_h_eMouseClicked(evt);
            }
        });

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
        jScrollPane3.setViewportView(tbl_h_e);

        btn_imp_h_e.setText("Mostrar");
        btn_imp_h_e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_imp_h_eMouseClicked(evt);
            }
        });

        btn_salir_h_e.setText("Salir");
        btn_salir_h_e.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_salir_h_eMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_horas_extraLayout = new javax.swing.GroupLayout(panel_horas_extra);
        panel_horas_extra.setLayout(panel_horas_extraLayout);
        panel_horas_extraLayout.setHorizontalGroup(
            panel_horas_extraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator6)
            .addGroup(panel_horas_extraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_horas_extraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jSeparator5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_horas_extraLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombres_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(btn_buscar_h_e))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_horas_extraLayout.createSequentialGroup()
                        .addGap(0, 907, Short.MAX_VALUE)
                        .addComponent(btn_imp_h_e)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir_h_e))
                    .addGroup(panel_horas_extraLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 885, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_horas_extraLayout.setVerticalGroup(
            panel_horas_extraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_horas_extraLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_horas_extraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(nombres_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(fecha_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar_h_e)
                    .addComponent(jLabel15)
                    .addComponent(estado_h_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panel_horas_extraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_imp_h_e)
                    .addComponent(btn_salir_h_e))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Reporte de horas extra", panel_horas_extra);

        panel_interno_e_s.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Filtrar por:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Nombre empleado");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Estado");

        buscar_entrar_salida.setText("Buscar");
        buscar_entrar_salida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_entrar_salidaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("fecha");

        tbl_entrada_salida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbl_entrada_salida);

        mostrar_entrar_salida.setText("Mostrar");
        mostrar_entrar_salida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrar_entrar_salidaMouseClicked(evt);
            }
        });

        salir_entrar_salida.setText("Salir");
        salir_entrar_salida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salir_entrar_salidaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Generar reporte de Hora de entrada y Hora de salida");

        javax.swing.GroupLayout panel_interno_e_sLayout = new javax.swing.GroupLayout(panel_interno_e_s);
        panel_interno_e_s.setLayout(panel_interno_e_sLayout);
        panel_interno_e_sLayout.setHorizontalGroup(
            panel_interno_e_sLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_interno_e_sLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_interno_e_sLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_interno_e_sLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre_emple_entrar_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado_entrar_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha__entrar_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(buscar_entrar_salida)
                        .addGap(9, 9, 9))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_interno_e_sLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(mostrar_entrar_salida)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salir_entrar_salida))
                    .addGroup(panel_interno_e_sLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jSeparator3)
        );
        panel_interno_e_sLayout.setVerticalGroup(
            panel_interno_e_sLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_interno_e_sLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_interno_e_sLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombre_emple_entrar_salida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(estado_entrar_salida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(fecha__entrar_salida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar_entrar_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_interno_e_sLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mostrar_entrar_salida)
                    .addComponent(salir_entrar_salida))
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_entrada_salidaLayout = new javax.swing.GroupLayout(panel_entrada_salida);
        panel_entrada_salida.setLayout(panel_entrada_salidaLayout);
        panel_entrada_salidaLayout.setHorizontalGroup(
            panel_entrada_salidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_interno_e_s, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_entrada_salidaLayout.setVerticalGroup(
            panel_entrada_salidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_interno_e_s, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Registro de entrar y salida", panel_entrada_salida);

        panel_interno_peticiones.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Generar reportes de Peticiones");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Buscar por:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Nombre de empleado");

        nombres_combo_buscar_peticion.setToolTipText("");

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
        jScrollPane2.setViewportView(tbl_buscar_peticion);

        btn_buscar_peticion.setText("Buscar");
        btn_buscar_peticion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_buscar_peticionMouseClicked(evt);
            }
        });

        btn_imp_buscar_peticion.setText("Imprimir");
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

        jLabel9.setText("Estado");

        estado_combo_buscar_peticion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_combo_buscar_peticionActionPerformed(evt);
            }
        });

        jLabel10.setText("Id de peticiion");

        javax.swing.GroupLayout panel_interno_peticionesLayout = new javax.swing.GroupLayout(panel_interno_peticiones);
        panel_interno_peticiones.setLayout(panel_interno_peticionesLayout);
        panel_interno_peticionesLayout.setHorizontalGroup(
            panel_interno_peticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_interno_peticionesLayout.createSequentialGroup()
                .addGroup(panel_interno_peticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_interno_peticionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(panel_interno_peticionesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_imp_buscar_peticion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_salir_buscar_peticion))
                    .addGroup(panel_interno_peticionesLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_peti_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombres_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(btn_buscar_peticion)))
                .addContainerGap())
            .addGroup(panel_interno_peticionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator2)
            .addComponent(jSeparator4)
        );
        panel_interno_peticionesLayout.setVerticalGroup(
            panel_interno_peticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_interno_peticionesLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_interno_peticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(btn_buscar_peticion)
                    .addComponent(estado_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombres_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(id_peti_combo_buscar_peticion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panel_interno_peticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_salir_buscar_peticion)
                    .addComponent(btn_imp_buscar_peticion))
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_peticionesLayout = new javax.swing.GroupLayout(panel_peticiones);
        panel_peticiones.setLayout(panel_peticionesLayout);
        panel_peticionesLayout.setHorizontalGroup(
            panel_peticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_interno_peticiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_peticionesLayout.setVerticalGroup(
            panel_peticionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_interno_peticiones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Registro de peticiones", panel_peticiones);

        panel_hoja_de_servicio.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel16.setText("Buscar por:");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Generar reporte de Hoja de servicio(SYSAID)");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel19.setText("Nombre de empleado");

        btn_buscar_sysaid.setText("Buscar");
        btn_buscar_sysaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_sysaidActionPerformed(evt);
            }
        });

        tbl_sysaid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tbl_sysaid);

        btn_imprimir_sysaid.setText("Salir");
        btn_imprimir_sysaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimir_sysaidActionPerformed(evt);
            }
        });

        btn_salir_sysaid.setText("Mostrar");
        btn_salir_sysaid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_salir_sysaidMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_hoja_de_servicioLayout = new javax.swing.GroupLayout(panel_hoja_de_servicio);
        panel_hoja_de_servicio.setLayout(panel_hoja_de_servicioLayout);
        panel_hoja_de_servicioLayout.setHorizontalGroup(
            panel_hoja_de_servicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator8)
            .addGroup(panel_hoja_de_servicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_hoja_de_servicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 1050, Short.MAX_VALUE)
                    .addGroup(panel_hoja_de_servicioLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panel_hoja_de_servicioLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_nombre_empleado_sysaid, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_buscar_sysaid)
                .addGap(38, 38, 38))
            .addGroup(panel_hoja_de_servicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_hoja_de_servicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_salir_sysaid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_imprimir_sysaid, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_hoja_de_servicioLayout.setVerticalGroup(
            panel_hoja_de_servicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hoja_de_servicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_hoja_de_servicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(combo_nombre_empleado_sysaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar_sysaid))
                .addGap(21, 21, 21)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_hoja_de_servicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_imprimir_sysaid)
                    .addComponent(btn_salir_sysaid))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Registro de hojas de servicio", panel_hoja_de_servicio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane2.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscar_entrar_salidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_entrar_salidaActionPerformed

       try {
           String sql=verificar_items_entrada_salida();
          /// System.err.println(sql);
           rs=rdm.sql(sql);
           cargar_tabla_entrada_salida(rs);
       } catch (SQLException ex) {
           Logger.getLogger(Menu_reporte.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_buscar_entrar_salidaActionPerformed

    private void tbl_buscar_peticionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_buscar_peticionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_buscar_peticionMouseClicked

    private void btn_buscar_peticionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_buscar_peticionMouseClicked
       try {
           String sql=verificar_datos_peticiones();
           System.err.println(sql);
           rs=rdm.sql(sql);
           cargar_tabla_peticion(rs);
       } catch (SQLException ex) {
           Logger.getLogger(Menu_reporte.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_btn_buscar_peticionMouseClicked

    private void btn_imp_buscar_peticionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imp_buscar_peticionActionPerformed
       ArrayList<String> id=new ArrayList<>();
       ArrayList<String> h_ingre=new ArrayList<>();
       ArrayList<String> f_ingre=new ArrayList<>();
       ArrayList<String> ingre_por=new ArrayList<>();
       ArrayList<String> agen=new ArrayList<>();
       ArrayList<String> n_emple=new ArrayList<>();
       ArrayList<String> comen=new ArrayList<>();
       ArrayList<String> proble=new ArrayList<>();
       ArrayList<String> estado=new ArrayList<>();
       ArrayList<String> h_fin=new ArrayList<>();
       ArrayList<String> f_fin=new ArrayList<>();
      
         if(tabla_peticion.getRowCount()>0)
       {
           for(int x=0;x<tabla_peticion.getRowCount();x++)
           {
               id.add(tabla_peticion.getValueAt(x,0).toString());
               h_ingre.add(tabla_peticion.getValueAt(x,1).toString());
               f_ingre.add(tabla_peticion.getValueAt(x,2).toString());
               ingre_por.add(tabla_peticion.getValueAt(x,3).toString());
               agen.add(tabla_peticion.getValueAt(x,4).toString());
               n_emple.add(tabla_peticion.getValueAt(x,5).toString());
               comen.add(tabla_peticion.getValueAt(x,6).toString());
               proble.add(tabla_peticion.getValueAt(x,7).toString());
               estado.add(tabla_peticion.getValueAt(x,8).toString());
               h_fin.add(tabla_peticion.getValueAt(x,9).toString());
               f_fin.add(tabla_peticion.getValueAt(x,10).toString());
              
           }
           try {
               rdm.vaciar_tabla("temporal_peti");
               } catch (SQLException ex) {
                 Logger.getLogger(Menu_reporte.class.getName()).log(Level.SEVERE, null, ex);
                }
           rdm.llenar_tabla_jasper_peticion(id,h_ingre,f_ingre,ingre_por,agen,n_emple,comen,proble,estado,h_fin,f_fin);
            //clase_reportes._peticiones_emple();
            new ReporteVisor(null,"reportes/Peticiones.jasper");
       }
       
    }//GEN-LAST:event_btn_imp_buscar_peticionActionPerformed

    private void btn_salir_buscar_peticionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salir_buscar_peticionMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_salir_buscar_peticionMouseClicked

    private void salir_entrar_salidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir_entrar_salidaActionPerformed
        dispose();
    }//GEN-LAST:event_salir_entrar_salidaActionPerformed

    private void estado_combo_buscar_peticionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado_combo_buscar_peticionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estado_combo_buscar_peticionActionPerformed

    private void btn_buscar_h_eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_buscar_h_eMouseClicked
       try {
           String sql=verificar_items_hora_extra();
           rs=rdm.sql(sql);
           cargar_tabla_hora_extra(rs);
       } catch (SQLException ex) {
           Logger.getLogger(Menu_reporte.class.getName()).log(Level.SEVERE, null, ex);
       }

    }//GEN-LAST:event_btn_buscar_h_eMouseClicked

    private void tbl_h_eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_h_eMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_h_eMouseClicked

    private void btn_imp_h_eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_imp_h_eMouseClicked
       ArrayList<String> id=new ArrayList<>();
       ArrayList<String> h_ini=new ArrayList<>();
       ArrayList<String> h_fin=new ArrayList<>();
       ArrayList<String> f_ini=new ArrayList<>();
       ArrayList<String> f_fin=new ArrayList<>();
       ArrayList<String> n_emple=new ArrayList<>();
       ArrayList<String> sysaid=new ArrayList<>();
       ArrayList<String> comen=new ArrayList<>();
       ArrayList<String> f_ingre=new ArrayList<>();
       ArrayList<String> h_ingre=new ArrayList<>();
       ArrayList<String> t_hora=new ArrayList<>();
       ArrayList<String> estado=new ArrayList<>();
       ArrayList<String> pago_t=new ArrayList<>();
       if(tabla_hora_extra.getRowCount()>0)
       {
           for(int x=0;x<tabla_hora_extra.getRowCount();x++)
           {
               id.add(tabla_hora_extra.getValueAt(x,0).toString());
               h_ini.add(tabla_hora_extra.getValueAt(x,1).toString());
               h_fin.add(tabla_hora_extra.getValueAt(x,2).toString());
               f_ini.add(tabla_hora_extra.getValueAt(x,3).toString());
               f_fin.add(tabla_hora_extra.getValueAt(x,4).toString());
               n_emple.add(tabla_hora_extra.getValueAt(x,5).toString());
               sysaid.add(tabla_hora_extra.getValueAt(x,6).toString());
               comen.add(tabla_hora_extra.getValueAt(x,7).toString());
               f_ingre.add(tabla_hora_extra.getValueAt(x,8).toString());
               h_ingre.add(tabla_hora_extra.getValueAt(x,9).toString());
               t_hora.add(tabla_hora_extra.getValueAt(x,10).toString());
               estado.add(tabla_hora_extra.getValueAt(x,11).toString());
               pago_t.add(tabla_hora_extra.getValueAt(x,12).toString());
           }
           try {
               rdm.vaciar_tabla("temporal_horas_extra");
               } catch (SQLException ex) {
                 Logger.getLogger(Menu_reporte.class.getName()).log(Level.SEVERE, null, ex);
                }
            rdm.llenar_tabla_jasper_hora_extra(id,h_ini,h_fin,f_ini,f_fin,n_emple,sysaid,comen,f_ingre,h_ingre,t_hora,estado,pago_t);
            //clase_reportes._horas_extra_emple();
            new ReporteVisor(null,"reportes/Horas_extra_emple.jasper");
       }
    }//GEN-LAST:event_btn_imp_h_eMouseClicked

    private void btn_salir_h_eMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salir_h_eMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btn_salir_h_eMouseClicked

    private void mostrar_entrar_salidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostrar_entrar_salidaMouseClicked
      
         ArrayList<String> id=new ArrayList<>();
         ArrayList<String> estado=new ArrayList<>();
         ArrayList<String> hora=new ArrayList<>();
         ArrayList<String> fecha=new ArrayList<>();
         ArrayList<String> emple=new ArrayList<>();
         ArrayList<String> corde=new ArrayList<>();
         if(tabla_entra_salida.getRowCount()>0)
         {
                for (int i = 0; i < tabla_entra_salida.getRowCount(); i++) {

                   id.add(tabla_entra_salida.getValueAt(i,0).toString());
                   estado.add(tabla_entra_salida.getValueAt(i, 1).toString());
                   hora.add(tabla_entra_salida.getValueAt(i, 2).toString());
                   fecha.add(tabla_entra_salida.getValueAt(i, 3).toString());
                   emple.add(tabla_entra_salida.getValueAt(i, 4).toString());
                   corde.add(tabla_entra_salida.getValueAt(i, 5).toString());
                   }
                    try {
                        rdm.vaciar_tabla("temporal_marca");
                    } catch (SQLException ex) {
                  Logger.getLogger(Menu_reporte.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    rdm.llenar_tabla_jasper_entrada_salida(id,estado,hora,fecha,emple,corde);
                  //  clase_reportes._hora_entrada_salida();
                  new ReporteVisor(null,"reportes/Marca_entrada_salida_emple.jasper");
         }
         
    }//GEN-LAST:event_mostrar_entrar_salidaMouseClicked

    private void btn_imprimir_sysaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimir_sysaidActionPerformed
        dispose();
    }//GEN-LAST:event_btn_imprimir_sysaidActionPerformed

    private void btn_buscar_sysaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_sysaidActionPerformed
         try {
           String sql=verificar_items_sysaid();
           System.err.println(sql);
           rs=rdm.sql(sql);
           cargar_tabla_sysaid(rs);
       } catch (SQLException ex) {
           Logger.getLogger(Menu_reporte.class.getName()).log(Level.SEVERE, null, ex);
       }

    }//GEN-LAST:event_btn_buscar_sysaidActionPerformed

    private void btn_salir_sysaidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salir_sysaidMouseClicked
       ArrayList<String> id=new ArrayList<>();
       ArrayList<String> agen=new ArrayList<>();
       ArrayList<String> id_peti=new ArrayList<>();
       ArrayList<String> fecha=new ArrayList<>();
       ArrayList<String> h_ini=new ArrayList<>();
       ArrayList<String> h_fin=new ArrayList<>();
       ArrayList<String> titulo=new ArrayList<>();
       ArrayList<String> cate=new ArrayList<>();
       ArrayList<String> descri=new ArrayList<>();
       ArrayList<String> trabajo=new ArrayList<>();
       ArrayList<String> mate=new ArrayList<>();
       ArrayList<String> emple_res=new ArrayList<>();
       ArrayList<String> emple_con=new ArrayList<>();
       ArrayList<String> emple_apoyo1=new ArrayList<>();
       ArrayList<String> emple_apoyo2=new ArrayList<>();
       ArrayList<String> emple_apoyo3=new ArrayList<>();
       ArrayList<String> h_sali=new ArrayList<>();
       ArrayList<String> h_retor=new ArrayList<>();
       ArrayList<String> sector=new ArrayList<>();
       ArrayList<String> trans=new ArrayList<>();
       ArrayList<String> alim=new ArrayList<>();
       ArrayList<String> hospe=new ArrayList<>();
       ArrayList<String> total_g=new ArrayList<>();
       ArrayList<String> solu=new ArrayList<>();
       ArrayList<String> nom_geren=new ArrayList<>();
       
      
         if(tabla_sysaid.getRowCount()>0)
       {
           for(int x=0;x<tabla_sysaid.getRowCount();x++)
           {
               id.add(tabla_sysaid.getValueAt(x,0).toString());
               agen.add(tabla_sysaid.getValueAt(x,1).toString());
               id_peti.add(tabla_sysaid.getValueAt(x,2).toString());
               fecha.add(tabla_sysaid.getValueAt(x,3).toString());
               h_ini.add(tabla_sysaid.getValueAt(x,4).toString());
               h_fin.add(tabla_sysaid.getValueAt(x,5).toString());
               titulo.add(tabla_sysaid.getValueAt(x,6).toString());
               cate.add(tabla_sysaid.getValueAt(x,7).toString());
               descri.add(tabla_sysaid.getValueAt(x,8).toString());
               trabajo.add(tabla_sysaid.getValueAt(x,9).toString());
               mate.add(tabla_sysaid.getValueAt(x,10).toString());
               emple_res.add(tabla_sysaid.getValueAt(x,11).toString());
               emple_con.add(tabla_sysaid.getValueAt(x,12).toString());
               emple_apoyo1.add(tabla_sysaid.getValueAt(x,13).toString());
               emple_apoyo2.add(tabla_sysaid.getValueAt(x,14).toString());
               emple_apoyo3.add(tabla_sysaid.getValueAt(x,15).toString());
               h_sali.add(tabla_sysaid.getValueAt(x,16).toString());
               h_retor.add(tabla_sysaid.getValueAt(x,17).toString());
               sector.add(tabla_sysaid.getValueAt(x,18).toString());
               trans.add(tabla_sysaid.getValueAt(x,19).toString());
               alim.add(tabla_sysaid.getValueAt(x,20).toString());
               hospe.add(tabla_sysaid.getValueAt(x,21).toString());
               total_g.add(tabla_sysaid.getValueAt(x,22).toString());
               solu.add(tabla_sysaid.getValueAt(x,23).toString());
               nom_geren.add(tabla_sysaid.getValueAt(x,24).toString());
              
           }
           try {
                rdm.vaciar_tabla("temporal_sysaid");
                rdm.llenar_tabla_jasper_sysaid(id,agen,id_peti,fecha,h_ini,h_fin,titulo,cate,descri,trabajo,mate,emple_res,emple_con,emple_apoyo1,emple_apoyo2,emple_apoyo3,
                                            h_sali,h_retor,sector,trans,alim,hospe,total_g,solu,nom_geren);
              new ReporteVisor(null,"reportes/Repor_sysaid.jasper");
                // clase_reportes.reporte_hoja_sysaid();
                rdm.cerrar_conexion();
               } catch (SQLException ex) {
                 Logger.getLogger(Menu_reporte.class.getName()).log(Level.SEVERE, null, ex);
                }
                     
       }
    }//GEN-LAST:event_btn_salir_sysaidMouseClicked

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
            java.util.logging.Logger.getLogger(Menu_reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_reporte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_reporte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar_h_e;
    private javax.swing.JButton btn_buscar_peticion;
    private javax.swing.JButton btn_buscar_sysaid;
    private javax.swing.JButton btn_imp_buscar_peticion;
    private javax.swing.JButton btn_imp_h_e;
    private javax.swing.JButton btn_imprimir_sysaid;
    private javax.swing.JButton btn_salir_buscar_peticion;
    private javax.swing.JButton btn_salir_h_e;
    private javax.swing.JButton btn_salir_sysaid;
    private javax.swing.JButton buscar_entrar_salida;
    private javax.swing.JComboBox<String> combo_nombre_empleado_sysaid;
    private javax.swing.JComboBox<String> estado_combo_buscar_peticion;
    private javax.swing.JComboBox<String> estado_entrar_salida;
    private javax.swing.JComboBox<String> estado_h_e;
    private javax.swing.JComboBox<String> fecha__entrar_salida;
    private javax.swing.JComboBox<String> fecha_h_e;
    private javax.swing.JComboBox<String> id_peti_combo_buscar_peticion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton mostrar_entrar_salida;
    private javax.swing.JComboBox<String> nombre_emple_entrar_salida;
    private javax.swing.JComboBox<String> nombres_combo_buscar_peticion;
    private javax.swing.JComboBox<String> nombres_h_e;
    private javax.swing.JPanel panel_entrada_salida;
    private javax.swing.JPanel panel_hoja_de_servicio;
    private javax.swing.JPanel panel_horas_extra;
    private javax.swing.JPanel panel_interno_e_s;
    private javax.swing.JPanel panel_interno_peticiones;
    private javax.swing.JPanel panel_peticiones;
    private javax.swing.JButton salir_entrar_salida;
    private javax.swing.JTable tbl_buscar_peticion;
    private javax.swing.JTable tbl_entrada_salida;
    private javax.swing.JTable tbl_h_e;
    private javax.swing.JTable tbl_sysaid;
    // End of variables declaration//GEN-END:variables
     private void crear_columnas_tabla_sysaid()
    {
            tabla_sysaid.addColumn("Id");
            tabla_sysaid.addColumn("Agencia");
            tabla_sysaid.addColumn("Id peti");
            tabla_sysaid.addColumn("Fecha:");
            tabla_sysaid.addColumn("Hora inicio");
            tabla_sysaid.addColumn("Hora final");
            tabla_sysaid.addColumn("Titulo");
            tabla_sysaid.addColumn("Categoria");
            tabla_sysaid.addColumn("Descrip");
            tabla_sysaid.addColumn("Trabajo");
            tabla_sysaid.addColumn("Materiales");
            tabla_sysaid.addColumn("Empleado Responsable");
            tabla_sysaid.addColumn("Conductor");
            tabla_sysaid.addColumn("Personal apoyo 1");
            tabla_sysaid.addColumn("Personal apoyo 2");
            tabla_sysaid.addColumn("Personal apoyo 3");
            tabla_sysaid.addColumn("Hora salida");
            tabla_sysaid.addColumn("Hora retorno");
            tabla_sysaid.addColumn("Sector");
            tabla_sysaid.addColumn("Transporte");
            tabla_sysaid.addColumn("Alimentacion");
            tabla_sysaid.addColumn("Hotel");
            tabla_sysaid.addColumn("Total");
            tabla_sysaid.addColumn("Solucion");
            tabla_sysaid.addColumn("Gerente/Supervisor");
            
            this.tbl_sysaid.setModel(tabla_sysaid);
            this.tbl_sysaid.setAutoscrolls(true);
            this.tbl_sysaid.getColumn(tbl_sysaid.getModel().getColumnName(0)).setMaxWidth(300);
        }
    private void crear_columnas_tabla_peticion()
    {
            tabla_peticion.addColumn("Id peticion");
            tabla_peticion.addColumn("Hora de ingreso");
            tabla_peticion.addColumn("Fecha de ingreso");
            tabla_peticion.addColumn("Ingresada por:");
            tabla_peticion.addColumn("Agencia");
            tabla_peticion.addColumn("Empleado responsable");
            tabla_peticion.addColumn("Descripcion");
            tabla_peticion.addColumn("Problema");
            tabla_peticion.addColumn("Estado");
            tabla_peticion.addColumn("Hora de finalizacion");
            tabla_peticion.addColumn("Fecha de finalizacion");
            this.tbl_buscar_peticion.setModel(tabla_peticion);
            this.tbl_buscar_peticion.setAutoscrolls(true);
            this.tbl_buscar_peticion.getColumn(tbl_buscar_peticion.getModel().getColumnName(0)).setMaxWidth(300);
        }
    public void crea_columnas_tabla_entrada_salida()
    {
            tabla_entra_salida.addColumn("id");
            tabla_entra_salida.addColumn("Estado");
            tabla_entra_salida.addColumn("Hora");
            tabla_entra_salida.addColumn("Fecha");
            tabla_entra_salida.addColumn("Empleado");
            tabla_entra_salida.addColumn("Cordenadas"); 
            this.tbl_entrada_salida.setModel(tabla_entra_salida);
            this.tbl_entrada_salida.setAutoscrolls(true);
            this.tbl_entrada_salida.getColumn(tbl_entrada_salida.getModel().getColumnName(0)).setMaxWidth(300);
    }
    private void crear_columnas_tabla_hora_extra() {
        tabla_hora_extra.addColumn("Id");
        tabla_hora_extra.addColumn("Hora de inicio");
        tabla_hora_extra.addColumn("Hora final");
        tabla_hora_extra.addColumn("Fecha inicio");
        tabla_hora_extra.addColumn("Fecha final");
        tabla_hora_extra.addColumn("Nombre de empleado");
        tabla_hora_extra.addColumn("Sysaid");
        tabla_hora_extra.addColumn("Comentario");
        tabla_hora_extra.addColumn("Fecha de ingreso");
        tabla_hora_extra.addColumn("Hora de ingreso");
        tabla_hora_extra.addColumn("Total horas extras");
        tabla_hora_extra.addColumn("Estado");
        tabla_hora_extra.addColumn("Total pago");
        this.tbl_h_e.setModel(tabla_hora_extra);
        this.tbl_h_e.setAutoscrolls(true);
        this.tbl_h_e.getColumn(tbl_h_e.getModel().getColumnName(0)).setMaxWidth(300);
    }
    public void cargar_tabla_entrada_salida(ResultSet rs)
    {
        String id_datos[]={"id_marca_de_entrada_salida","estado_marca_de_entrada_salida","hora_marca_de_entrada_salida",
                           "fecha_marca_de_entrada_salida","id_empleado_marca_de_entrada_salida","codenadas_marca_de_entrada_salida"};
    
        if(this.tbl_entrada_salida.getRowCount()!=0)
        {
            tabla_entra_salida.setRowCount(0);
            llenar_datos_tablas_entrada_salida(rs,id_datos);
        }else
        {
            llenar_datos_tablas_entrada_salida(rs,id_datos);
        }
    }
    public void cargar_tabla_peticion(ResultSet rs)
    {
       String id_datos[]={"id_peticiones","hora_peticion","fecha_peticion","emple_envio_peticiones","n_agencia_peticion",
                            "emple_recibio_peticiones","descripcion_peticion","nombre_problema","estado_peticion","hora_final_peticion",
                            "fecha_final_peticion"};
      
       if(this.tbl_buscar_peticion.getRowCount()!=0)
        {
            tabla_peticion.setRowCount(0);
            llenar_datos_tablas_peticion(rs,id_datos);
        }else
        {
            llenar_datos_tablas_peticion(rs,id_datos);
        }
           }
    private void cargar_tabla_hora_extra(ResultSet r) {
         String id_datos[]={"id_ingreso_hora_extra","hora_inicio_ingreso_hora_extra","hora_final_ingreso_hora_extra","fecha_inicio_ingreso_hora_extra",
                         "fecha_final_ingreso_hora_extra","nombre_empeleado_ingreso_hora_extra","id_sysaid","comentario_ingreso_hora_extra",
                         "fecha__ingreso_hora_extra","hora__ingreso_hora_extra","total_horas_extras_ingreso_horas_extras",
                         "estado_ingreso_hora_extra","pago_total_ingreso_hora_extra"};
         if(this.tbl_h_e.getRowCount()!=0)
        {
            tabla_hora_extra.setRowCount(0);
            llenar_datos_tabla_hora_extra(r, id_datos);
        }else
        {
           llenar_datos_tabla_hora_extra(r, id_datos);
        }    
    }
    private void cargar_tabla_sysaid(ResultSet r) {
         String id_datos[]={"id_hoja_de_servicio","agencia_hoja_de_servicio","id_peti_hoja_de_servicio","fecha_hoja_de_servicio","hora_inici_hoja_de_servicio","hora_finalizacion_hoja_de_servicio",
                            "titulo_hoja_de_servicio","categoria_hoja_de_servicio","descrip_hoja_de_servicio","trabajo_r_hoja_de_servicio","materiales_hoja_de_servicio","id_emple_responsable_hoja_de_servicio",
                            "id_emple_conductor_hoja_de_servicio","id_emple_apoyo1_hoja_de_servicio","id_emple_apoyo2_hoja_de_servicio","id_emple_apoyo3_hoja_de_servicio","hora_salida_hoja_de_servicio",
                            "hora_retorno_hoja_de_servicio","sector_hoja_de_servicio","transporte_hoja_de_servicio","alimentacion_hoja_de_servicio","hospe_hoja_de_servicio","total_gastos_hoja_de_servicio","solucion_trabajo_hoja_de_servicio","nom_gerente_supervisor_hoja_de_servicio"};
         if(this.tbl_sysaid.getRowCount()!=0)
        {
            tabla_sysaid.setRowCount(0);
            llenar_datos_tabla_sysaid(r, id_datos);
        }else
        {
           llenar_datos_tabla_sysaid(r, id_datos);
        }    
    }
    private void llenar_datos_tabla_sysaid(ResultSet r, String[] id_datos) {
        String da[]=new String[26];
       try {
           while(r.next())
           {
               for(int x=0; x<id_datos.length;x++)
               {
                   da[x]=r.getString(id_datos[x]);
               }
               tabla_sysaid.addRow(da);
           }
       } catch (SQLException ex) {
           Logger.getLogger(Menu_reporte.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    public void llenar_datos_tablas_peticion(ResultSet r,String id_datos[])
    {
        String datos[]=new String [11];
         try {
            while(r.next())
            {
                for(int x=0; x<id_datos.length;x++)
                {
                    datos[x]=rs.getString(id_datos[x]);
                }
                String d=datos[5];
                String a=datos[4];
                //String agencia="";
                ///System.err.println("id agencia"+a);
                int u=0;
                for(int y=0; y<lista_id_agencia_peticion.size();y++)
                {   
                  
                    if(lista_id_agencia_peticion.get(y).equalsIgnoreCase(a))
                    {
                        u=y;
                        String agencia=lista_nombre_agencia_peticion.get(u);
                        datos[4]=agencia;
                        System.err.println("dentro de if_agencia "+a+" : "+lista_id_agencia_peticion.get(y));
                    }
                }
                int i=0;
                for(int x=0;x<lista_id_emple_peticion.size();x++)
                {
                    if(lista_id_emple_peticion.get(x).equalsIgnoreCase(d))
                    {
                        i=x;
                        String n=nombres_combo_buscar_peticion.getItemAt(i+1);
                        datos[5]=n;
                    }
                }
                
                System.out.println("n_agencia:"+datos[4]+"  :   "+datos[5]);

               tabla_peticion.addRow(datos);    
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void llenar_datos_tablas_entrada_salida(ResultSet r,String id_datos[])
    {
        String datos[]=new String [6];
        try {
            while(r.next())
            {
                for(int x=0; x<id_datos.length;x++)
                {
                    datos[x]=rs.getString(id_datos[x]);
                }
              String d=datos[4];
              int i=0;
               for(int x=0;x<lista_id_emple_entra_salida.size();x++)
               {
                   if(lista_id_emple_entra_salida.get(x).equalsIgnoreCase(d))
                   {
                       i=x;
                   }
               }
               String n=lista_nombre_hora_extra.get(i);
               datos[4]=n;
               tabla_entra_salida.addRow(datos);    
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_empleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void llenar_datos_tabla_hora_extra(ResultSet r,String id_datos[])
    {
        String da[]=new String[14];
       try {
           while(r.next())
           {
               for(int x=0; x<id_datos.length;x++)
               {
                   da[x]=r.getString(id_datos[x]);
               }
               tabla_hora_extra.addRow(da);
           }
       } catch (SQLException ex) {
           Logger.getLogger(Menu_reporte.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    private void llenar_combox_peticion() 
    {
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
             nombres_lista.add(rs.getString("nombre_completo_empleado"));
           }
           for(String n:nombres_lista)
           {
               this.nombres_combo_buscar_peticion.addItem(n);
           }
           for(String e:estado_lista)
           {
               this.estado_combo_buscar_peticion.addItem(e);
           }
           rs=rdm.sql("SELECT Id_agencias,nombre_agencias FROM agencias");
           while(rs.next())
           {
               lista_id_agencia_peticion.add(rs.getString("Id_agencias"));
               lista_nombre_agencia_peticion.add(rs.getString("nombre_agencias"));
           }
           rdm.cerrar_conexion();
       } catch (SQLException ex) {
           Logger.getLogger(Buscar_peticiones.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
     private void llenar_combo_nombre_id_sysaid()
    {
      try {
          rs=rdm.sql("SELECT DISTINCT id_emple_responsable_hoja_de_servicio FROM hoja_de_servicio");
          while(rs.next())
          {
              combo_nombre_empleado_sysaid.addItem(rs.getString("id_emple_responsable_hoja_de_servicio"));
          }
       rdm.cerrar_conexion();
        
      } catch (SQLException ex) {
          Logger.getLogger(Buscar_entrada_salida.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    private void llenar_combo_nombre_entrada_salida()
    {
      try {
          rs=rdm.sql("SELECT * FROM empleado");
          while(rs.next())
          {
              lista_id_emple_entra_salida.add(rs.getString("id_empleado"));
              nombre_emple_entrar_salida.addItem(rs.getString("nombre_completo_empleado"));
          }
          rdm.cerrar_conexion();
      } catch (SQLException ex) {
          Logger.getLogger(Buscar_entrada_salida.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    private void llenar_combox_hora_extra()
    {
        ArrayList<String> da_estado=new ArrayList<>();
        ArrayList<String> da_fecha=new ArrayList<>();
         ArrayList<String> da_nombre=new ArrayList<>();
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
            if(!da_nombre.contains(rs.getString("nombre_empeleado_ingreso_hora_extra")))
            {
                da_nombre.add(rs.getString("nombre_empeleado_ingreso_hora_extra").toString());
                System.err.println("no esta el dato nombre"+false);
            }
        }
            rs=rdm.traer_datos_todos("empleado");
            while(rs.next())
            {
                lista_nombre_hora_extra.add(rs.getString("nombre_completo_empleado"));
                lista_id_emple_hora_extra.add(rs.getString("id_empleado"));
            }
            
            for(String e:da_estado)
            {
                this.estado_h_e.addItem(e);
            }
            
            for(String f:da_fecha)
            {
                this.fecha_h_e.addItem(f);
            }
            for(String n:da_nombre)
            {
                this.nombres_h_e.addItem(n);
            }
            rdm.cerrar_conexion();
    } catch (SQLException ex) {
        Logger.getLogger(Buscar_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    private void llenar_combo_fecha_estado_entrada_salida()
    {
      try {
          rs=rdm.sql("SELECT * FROM marcar_de_entrada_salida");
          while(rs.next())
          {
              if(!lista_fecha_entra_salida.contains(rs.getString("fecha_marca_de_entrada_salida")))
              {lista_fecha_entra_salida.add(rs.getString("fecha_marca_de_entrada_salida"));}
              if(!lista_estado_entra_salida.contains(rs.getString("estado_marca_de_entrada_salida")))
              {lista_estado_entra_salida.add(rs.getString("estado_marca_de_entrada_salida"));}
          }
          
          for(int x=0;x<lista_fecha_entra_salida.size();x++)
          {
              this.fecha__entrar_salida.addItem(lista_fecha_entra_salida.get(x));
             
          }
          for(int x=0; x<lista_estado_entra_salida.size();x++)                  
          {
              this.estado_entrar_salida.addItem(lista_estado_entra_salida.get(x));
          }
          rdm.cerrar_conexion();
      } catch (SQLException ex) {
          Logger.getLogger(Buscar_entrada_salida.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    public String verificar_datos_peticiones()
    {
       Verificar_items_peticiones v=new Verificar_items_peticiones();
       String sql="";
       String id_peti=id_peti_combo_buscar_peticion.getSelectedItem().toString();
       String nom=nombres_combo_buscar_peticion.getSelectedItem().toString();
       String estado=estado_combo_buscar_peticion.getSelectedItem().toString();
       int id_emple=0;
       if(!nom.equalsIgnoreCase("Todos"))
       {
           int i=nombres_combo_buscar_peticion.getSelectedIndex();
           String id_e=lista_id_emple_peticion.get(i-1);
           id_emple=Integer.parseInt(id_e);
       }
       String items[]={id_peti,nom,estado};
       sql=v.verificar(items, id_emple);
       
        return sql;
    }
    private String verificar_items_entrada_salida()
    {
       Verificar_items_entrada_salida v=new Verificar_items_entrada_salida();
       String sql="";
       int id_emp=0;
       String id="";
       String n=nombre_emple_entrar_salida.getSelectedItem().toString();
       String f=fecha__entrar_salida.getSelectedItem().toString().trim();
       String e=estado_entrar_salida.getSelectedItem().toString();
       
       if(!n.equalsIgnoreCase("Todo"))
       {
        
           for(int x=0;x<lista_nombre_hora_extra.size();x++)
           {
               if(lista_nombre_hora_extra.get(x).equalsIgnoreCase(n))
               {
                   id=lista_id_emple_entra_salida.get(x);
               }
           }
          id_emp=Integer.parseInt(id);
       } 
        System.err.println(""+id_emp);
       String items[]={n,f,e};
       sql=v.verificar(items,id_emp);
       return sql;
    }
    public String verificar_items_hora_extra()
    {
        String sql="";
        Verificar_items_hora_extra v=new Verificar_items_hora_extra();
        String n=nombres_h_e.getSelectedItem().toString();
        String f=fecha_h_e.getSelectedItem().toString();
        String e=estado_h_e.getSelectedItem().toString();
        String items[]={n,f,e};
        sql=v.verificar(items);
        return sql;
    }
     public String verificar_items_sysaid()
    {
        String sql="";
        Verificar_items_sysaid v=new Verificar_items_sysaid();
        String n=combo_nombre_empleado_sysaid.getSelectedItem().toString();
        sql=v.verificar(n);
        return sql;
    }

   
}