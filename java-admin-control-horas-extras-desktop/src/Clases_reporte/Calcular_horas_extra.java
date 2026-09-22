
package Clases_reporte;


import Clases_reporte.Clase_reportes;
import Clases_reporte.ReporteVisor;
import Formularios.Alerta_dialog;
import Formularios.Alerta_msj_mal_horas;
import Formularios.Calcular_salario;
import Formularios.Ingresar_puestos;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pconexionsql.resgistro_datos_mysql;

/**
 *
 * @author CelesteZaldivar
 */
public class Calcular_horas_extra extends javax.swing.JDialog{
    public ArrayList<String> lista_nuevo=new ArrayList<>();
    public DefaultListModel modelo_nuevo = new DefaultListModel();
    public resgistro_datos_mysql rdm=new resgistro_datos_mysql();
    public ResultSet rs=null;
    public String item_selec_lista_false,item_selec_lista_true,id_emple;
    public int id_selec_lista_false,id_selec_lista_true;
    public String fechas_festivas[]={"1-05","14-04","1-01","15-09","3-10","12-10","21-10","25-12","1-04","2-04","3-04"};
    public DefaultTableModel tabla = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };
    public Calcular_horas_extra(javax.swing.JDialog p,Boolean b) {
        super(p,b);
        initComponents();
        this.setModal(true); 
        this.setLocationRelativeTo(null); 
        this.lista_false.setModel(modelo_nuevo);
        llenar_lista_false();
        this.msj_calcular_error.setText("");
       
        crear_columnas_tabla();
        llenar_tabla();
    }
    public void crear_columnas_tabla()
    {
        tabla.addColumn("Id");
        tabla.addColumn("Empleado");
        tabla.addColumn("Total horas");
        tabla.addColumn("Hora inicio");
        tabla.addColumn("Hora final");
        tabla.addColumn("Estado");
        tabla.addColumn("Total pago");
        this.tbl_horas_extra.setModel(tabla);
        this.tbl_horas_extra.setAutoscrolls (true);
    }
   
    public final void llenar_lista_false()
    {
        try {
            String condi[]={"Pendiente",""};
            rs=rdm.traer_datos_todos_condicion("ingreso_hora_extra","estado_ingreso_hora_extra",condi);
            while(rs.next())
            {
                lista_nuevo.add("N.Id Reg"+":"+rs.getString("id_ingreso_hora_extra"));
               
            }
            for (int i = 0; i < lista_nuevo.size(); i++) {
             modelo_nuevo.addElement(lista_nuevo.get(i));

            }
          
        } catch (SQLException ex) {
            Logger.getLogger(Calcular_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void llenar_tabla()
    {
        String datos[]=new String[7];
        String consulta="SELECT * FROM ingreso_hora_extra WHERE fecha__ingreso_hora_extra  = (SELECT MAX(fecha__ingreso_hora_extra)FROM ingreso_hora_extra) AND estado_ingreso_hora_extra='Aprobada' OR estado_ingreso_hora_extra='Rechazada'";
         if(this.tbl_horas_extra.getRowCount()!=0 )
        {
            tabla.setRowCount(0);
            try {
                rs=rdm.sql(consulta);
                while(rs.next())
                { 
                    datos[0]=rs.getString("id_ingreso_hora_extra");
                    datos[1]=rs.getString("nombre_empeleado_ingreso_hora_extra");
                    datos[2]=rs.getString("total_horas_extras_ingreso_horas_extras");
                    datos[3]=rs.getString("hora_inicio_ingreso_hora_extra");
                    datos[4]=rs.getString("hora_final_ingreso_hora_extra");
                    datos[5]=rs.getString("estado_ingreso_hora_extra");
                    datos[6]=rs.getString("pago_total_ingreso_hora_extra");
                    tabla.addRow(datos);
                }
                 
            } catch (SQLException ex) {
                Logger.getLogger(Ingresar_puestos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          }else{
             try {
                rs=rdm.sql(consulta);
                while(rs.next())
                { 
                    datos[0]=rs.getString("id_ingreso_hora_extra");
                    datos[1]=rs.getString("nombre_empeleado_ingreso_hora_extra");
                    datos[2]=rs.getString("total_horas_extras_ingreso_horas_extras");
                    datos[3]=rs.getString("hora_inicio_ingreso_hora_extra");
                    datos[4]=rs.getString("hora_final_ingreso_hora_extra");
                    datos[5]=rs.getString("estado_ingreso_hora_extra");
                    datos[6]=rs.getString("pago_total_ingreso_hora_extra");
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

        panel_lista_nueva = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_false = new javax.swing.JList<>();
        lb_actualizar = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        msj_confir_calcular_horas_extra = new javax.swing.JLabel();
        panel_info = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        salario_calcular_hora = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        peticion_calcu_hora = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        puesto_calcular_hora = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        total_horas_calcular_hora = new javax.swing.JTextField();
        nombre_empleado_calcular_hora = new javax.swing.JTextField();
        hora_inicio_calcular_hora = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fecha_inicio_calcular_hora = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        hora_final_calcular_hora = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fecha_final_calcular_hora = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        fecha_calcular_hora = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        hora_calcular_hora = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        hoja_servicio = new javax.swing.JTextField();
        ver_peti = new javax.swing.JLabel();
        ver_hoja_s = new javax.swing.JLabel();
        txt_msj_horas_mal = new javax.swing.JLabel();
        panel_tabla = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_horas_extra = new javax.swing.JTable();
        label1 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panel_aprobar = new javax.swing.JPanel();
        btn_calcular_calcular_hora = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        total_calculo_calcular_hora = new javax.swing.JTextField();
        btn_confirmar_calcular_hora = new javax.swing.JButton();
        btn_salir_calcular_hora = new javax.swing.JButton();
        msj_calcular_error = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(java.awt.Color.white);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_lista_nueva.setBackground(new java.awt.Color(255, 255, 255));

        lista_false.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        lista_false.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lista_false.setToolTipText("");
        lista_false.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lista_false.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_falseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lista_false);

        lb_actualizar.setBackground(new java.awt.Color(255, 102, 102));
        lb_actualizar.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lb_actualizar.setForeground(new java.awt.Color(255, 102, 102));
        lb_actualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_actualizar.setText("Actualizar");
        lb_actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lb_actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_actualizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lb_actualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lb_actualizarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panel_lista_nuevaLayout = new javax.swing.GroupLayout(panel_lista_nueva);
        panel_lista_nueva.setLayout(panel_lista_nuevaLayout);
        panel_lista_nuevaLayout.setHorizontalGroup(
            panel_lista_nuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_lista_nuevaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_lista_nuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        panel_lista_nuevaLayout.setVerticalGroup(
            panel_lista_nuevaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_lista_nuevaLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_actualizar)
                .addContainerGap())
        );

        getContentPane().add(panel_lista_nueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 150, 330));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(672, 232, 125, -1));

        jLabel16.setToolTipText("");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 349, 124, -1));
        getContentPane().add(msj_confir_calcular_horas_extra, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 576, 187, -1));

        panel_info.setBackground(new java.awt.Color(255, 255, 255));
        panel_info.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(255, 102, 102));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Nombre de empleado");
        panel_info.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, 20));

        salario_calcular_hora.setEditable(false);
        salario_calcular_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panel_info.add(salario_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 130, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel14.setText("Salario");
        panel_info.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 130, 20));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel17.setText("Id de peticion");
        panel_info.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, 20));

        peticion_calcu_hora.setEditable(false);
        peticion_calcu_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panel_info.add(peticion_calcu_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 130, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Puesto de trabajo");
        panel_info.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 130, 20));

        puesto_calcular_hora.setEditable(false);
        puesto_calcular_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panel_info.add(puesto_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 130, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Total  horas");
        panel_info.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 120, 20));

        total_horas_calcular_hora.setEditable(false);
        total_horas_calcular_hora.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        total_horas_calcular_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_horas_calcular_hora.setText("00:00");
        panel_info.add(total_horas_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 130, -1));

        nombre_empleado_calcular_hora.setEditable(false);
        nombre_empleado_calcular_hora.setBackground(new java.awt.Color(255, 255, 255));
        panel_info.add(nombre_empleado_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 318, -1));

        hora_inicio_calcular_hora.setEditable(false);
        hora_inicio_calcular_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panel_info.add(hora_inicio_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 106, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Hora de inicio ");
        panel_info.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 100, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Fecha de inicio");
        panel_info.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 96, 20));

        fecha_inicio_calcular_hora.setEditable(false);
        fecha_inicio_calcular_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panel_info.add(fecha_inicio_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 106, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText("Hora final");
        panel_info.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 100, 20));

        hora_final_calcular_hora.setEditable(false);
        hora_final_calcular_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panel_info.add(hora_final_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 106, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Fecha final");
        panel_info.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 110, 20));

        fecha_final_calcular_hora.setEditable(false);
        fecha_final_calcular_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panel_info.add(fecha_final_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 106, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Fecha de ingreso");
        panel_info.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 20));

        fecha_calcular_hora.setEditable(false);
        fecha_calcular_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panel_info.add(fecha_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 130, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel13.setText("Hora de ingreso");
        panel_info.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 110, 20));

        hora_calcular_hora.setEditable(false);
        hora_calcular_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panel_info.add(hora_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 130, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel18.setText("Hoja de servicio");
        panel_info.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 20));

        hoja_servicio.setEditable(false);
        hoja_servicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        panel_info.add(hoja_servicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 130, -1));

        ver_peti.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        ver_peti.setForeground(new java.awt.Color(255, 102, 102));
        ver_peti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ver_peti.setText("Ver");
        ver_peti.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ver_peti.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ver_petiMouseMoved(evt);
            }
        });
        ver_peti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ver_petiMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ver_petiMouseExited(evt);
            }
        });
        panel_info.add(ver_peti, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, -1, 20));

        ver_hoja_s.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        ver_hoja_s.setForeground(new java.awt.Color(255, 102, 102));
        ver_hoja_s.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ver_hoja_s.setText("Ver");
        ver_hoja_s.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ver_hoja_s.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ver_hoja_sMouseMoved(evt);
            }
        });
        ver_hoja_s.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ver_hoja_sMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ver_hoja_sMouseExited(evt);
            }
        });
        panel_info.add(ver_hoja_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, 20));
        panel_info.add(txt_msj_horas_mal, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 260, 70));

        getContentPane().add(panel_info, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 620, 330));

        panel_tabla.setBackground(new java.awt.Color(255, 255, 255));

        tbl_horas_extra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbl_horas_extra);

        javax.swing.GroupLayout panel_tablaLayout = new javax.swing.GroupLayout(panel_tabla);
        panel_tabla.setLayout(panel_tablaLayout);
        panel_tablaLayout.setHorizontalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 988, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panel_tablaLayout.setVerticalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panel_tabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 1010, 240));

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        label1.setText("Control de horas extras");
        getContentPane().add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 980, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Informacion del usuario");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 620, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Solicitudes nuevas");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, -1));

        panel_aprobar.setBackground(new java.awt.Color(255, 255, 255));
        panel_aprobar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        panel_aprobar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_calcular_calcular_hora.setText("Calcular");
        btn_calcular_calcular_hora.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)));
        btn_calcular_calcular_hora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_calcular_calcular_hora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_calcular_calcular_horaMouseClicked(evt);
            }
        });
        panel_aprobar.add(btn_calcular_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 65, 30));

        jButton1.setText("Denegar");
        jButton1.setToolTipText("");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_denegar_ActionPerformed(evt);
            }
        });
        panel_aprobar.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 64, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("Total a pagar");
        panel_aprobar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        total_calculo_calcular_hora.setEditable(false);
        total_calculo_calcular_hora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        total_calculo_calcular_hora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_calculo_calcular_hora.setText("00.00");
        panel_aprobar.add(total_calculo_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, 32));

        btn_confirmar_calcular_hora.setText("Confirmar resultado");
        btn_confirmar_calcular_hora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_confirmar_calcular_hora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_confirmar_calcular_horaMouseClicked(evt);
            }
        });
        panel_aprobar.add(btn_confirmar_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        btn_salir_calcular_hora.setText("Salir");
        btn_salir_calcular_hora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_salir_calcular_hora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salir_calcular_horaActionPerformed(evt);
            }
        });
        panel_aprobar.add(btn_salir_calcular_hora, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

        msj_calcular_error.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panel_aprobar.add(msj_calcular_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 170, 200, -1));

        getContentPane().add(panel_aprobar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, 200, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_confirmar_calcular_horaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_confirmar_calcular_horaMouseClicked
        System.out.println(item_selec_lista_false);
        String p=this.total_calculo_calcular_hora.getText();
        
        if(p.equalsIgnoreCase("00.00")){
            Font font = new Font("Tahoma", Font.PLAIN, 13);
            this.total_calculo_calcular_hora.setBackground(Color.orange);
            this.msj_calcular_error.setForeground(Color.red);
            this.msj_calcular_error.setText("Debe calcular el porcentaje");
            this.msj_calcular_error.setFont(font);
        }else
        {
            this.msj_calcular_error.setText("");
            this.total_calculo_calcular_hora.setBackground(Color.white);
            String columna[]={"pago_total_ingreso_hora_extra","estado_ingreso_hora_extra","id_ingreso_hora_extra"};
            String pago_total=this.total_calculo_calcular_hora.getText();
            if(!item_selec_lista_false.isEmpty()&& !pago_total.isEmpty())
            {
              String id_[]=item_selec_lista_false.split(":");
               String datos[]={pago_total,"Aprobada",id_[1]};
               String msj=rdm.guarda_un_dato("ingreso_hora_extra",datos,columna);
              // JOptionPane.showMessageDialog(null, msj);

               lista_nuevo.clear();
               modelo_nuevo.clear();
               llenar_lista_false();
               llenar_tabla();
            }
        }
        
    }//GEN-LAST:event_btn_confirmar_calcular_horaMouseClicked

    private void btn_denegar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_denegar_ActionPerformed
      
        if(item_selec_lista_false!=null)
        {
            String item_selec[]=item_selec_lista_false.split(":");
            String datos[]={"","Rechazada",item_selec[1]};
            String columnas[]={"pago_total_ingreso_hora_extra","estado_ingreso_hora_extra","id_ingreso_hora_extra"};
            String msj=rdm.guarda_un_dato("ingreso_hora_extra", datos, columnas);
            lista_nuevo.clear();
            modelo_nuevo.clear();
            llenar_lista_false();
            llenar_tabla();
          
        }else
        {JOptionPane.showMessageDialog(null, "Selecciones un elemento de la lista (Solicitudes nuevas)");}
       
    }//GEN-LAST:event_btn_denegar_ActionPerformed

    private void lista_falseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_falseMouseClicked
       item_selec_lista_false=lista_false.getSelectedValue();
       id_selec_lista_false=lista_false.getSelectedIndex();
       this.msj_confir_calcular_horas_extra.setText("");
       
       llenar_campos();
    }//GEN-LAST:event_lista_falseMouseClicked

    private void btn_salir_calcular_horaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salir_calcular_horaActionPerformed
      dispose();
    }//GEN-LAST:event_btn_salir_calcular_horaActionPerformed

    private void btn_calcular_calcular_horaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_calcular_calcular_horaMouseClicked
      if(item_selec_lista_false!=null)
      {
        if(comparar_fechas().equalsIgnoreCase("bien")&&comparar_horas_extra_horas_sysaid().equalsIgnoreCase("bien"))
        {
            this.msj_calcular_error.setText("");
            double d=calcular_horas_extras();
            this.total_calculo_calcular_hora.setText(""+d);
            this.total_calculo_calcular_hora.setBackground(Color.white);
        }else
        {
            this.msj_calcular_error.setText("");
            this.total_calculo_calcular_hora.setBackground(Color.white);
        }
        
      }else
      {JOptionPane.showMessageDialog(null, "Selecciones un elemento de la lista (Solicitudes nuevas)");}
     
    }//GEN-LAST:event_btn_calcular_calcular_horaMouseClicked

    private void ver_hoja_sMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ver_hoja_sMouseMoved
         this.ver_hoja_s.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
    }//GEN-LAST:event_ver_hoja_sMouseMoved

    private void ver_petiMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ver_petiMouseMoved
       this.ver_peti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
    }//GEN-LAST:event_ver_petiMouseMoved

    private void ver_petiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ver_petiMouseExited
         this.ver_peti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_ver_petiMouseExited

    private void ver_hoja_sMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ver_hoja_sMouseExited
       this.ver_hoja_s.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_ver_hoja_sMouseExited

    private void lb_actualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_actualizarMouseEntered
     this.lb_actualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102)));
    }//GEN-LAST:event_lb_actualizarMouseEntered

    private void lb_actualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_actualizarMouseExited
       this.lb_actualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_lb_actualizarMouseExited

    private void lb_actualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_actualizarMouseClicked
        lista_nuevo.clear();
        modelo_nuevo.clear();
        llenar_lista_false();
    }//GEN-LAST:event_lb_actualizarMouseClicked

    private void ver_petiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ver_petiMouseClicked
      Clase_reportes cr=new Clase_reportes();
      if(!peticion_calcu_hora.getText().isEmpty()&&!nombre_empleado_calcular_hora.getText().isEmpty())
      {
          System.err.println(""+peticion_calcu_hora.getText());
        //cr.una_fila_horas_extra_emple(peticion_calcu_hora.getText(), nombre_empleado_calcular_hora.getText());
        Map parametros=new HashMap();
        int id_n=Integer.parseInt(peticion_calcu_hora.getText());
        parametros.put("id_peticion",id_n);
        parametros.put("nombre_emple",nombre_empleado_calcular_hora.getText());
          ReporteVisor reporteVisor = new ReporteVisor(parametros,"reportes/Traer_una_fila.jasper");
      }
      
    }//GEN-LAST:event_ver_petiMouseClicked

    private void ver_hoja_sMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ver_hoja_sMouseClicked
        Clase_reportes cr=new Clase_reportes();
      if(!hoja_servicio.getText().isEmpty())
      {
          System.err.println(""+hoja_servicio.getText());
         ///cr.mostrar_hoja_sysaid(hoja_servicio.getText());
        
         Map parametros=new HashMap();
         int id_n=Integer.parseInt(hoja_servicio.getText());
          parametros.put("id_sysaid",id_n);
            ReporteVisor reporteVisor = new ReporteVisor(parametros,"reportes/SYSAID.jasper");
      }
    }//GEN-LAST:event_ver_hoja_sMouseClicked
     public void llenar_campos()
    {
        if(!item_selec_lista_false.isEmpty())
        { 
                String item_selec[]=item_selec_lista_false.split(":");

            try {
                rs=rdm.traer_una_fila_hora_extra("ingreso_hora_extra","empleado",item_selec[1]);
                while(rs.next())
                {
                this.hora_inicio_calcular_hora.setText(rs.getString("hora_inicio_ingreso_hora_extra")); 
                this.fecha_inicio_calcular_hora.setText(rs.getString("fecha_inicio_ingreso_hora_extra"));
                this.hora_final_calcular_hora.setText(rs.getString("hora_final_ingreso_hora_extra"));
                this.fecha_final_calcular_hora.setText(rs.getString("fecha_final_ingreso_hora_extra"));
                this.nombre_empleado_calcular_hora.setText(rs.getString("nombre_empeleado_ingreso_hora_extra"));
                this.total_horas_calcular_hora.setText(rs.getString("total_horas_extras_ingreso_horas_extras"));
                this.hora_calcular_hora.setText(rs.getString("hora__ingreso_hora_extra"));
                this.fecha_calcular_hora.setText(rs.getString("fecha__ingreso_hora_extra"));
                this.hoja_servicio.setText(rs.getString("id_sysaid"));
                this.puesto_calcular_hora.setText(rs.getString("puesto_empleado"));
                this.id_emple=rs.getString("id_empleado");
                this.salario_calcular_hora.setText(rs.getString("salario_empleado"));
                }
           } catch (SQLException ex) {
                Logger.getLogger(Calcular_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            try {
                rs=rdm.sql("SELECT id_peti_hoja_de_servicio FROM hoja_de_servicio WHERE id_hoja_de_servicio ='"+hoja_servicio.getText().toString()+"'");
                while(rs.next())
                {
                    this.peticion_calcu_hora.setText(rs.getString("id_peti_hoja_de_servicio"));
                }
                total_calculo_calcular_hora.setText("00.00");
                rdm.cerrar_conexion();
            } catch (SQLException ex) {
                Logger.getLogger(Calcular_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public double calcular_horas_extras()
     {
         double paga_total_horas_redon=0;
         double t_horas=0;
         double t_min= 0;
         String diurna="diurna";
         String nocturna="nocturna";
         String h_i=hora_inicio_calcular_hora.getText().toString();
         String h_f=hora_final_calcular_hora.getText().toString();
         String salario=this.salario_calcular_hora.getText();
        
        String tr[]=clasificar_horas();
        String festivo[]=identificar_fechas_festivas();
         if(!salario.isEmpty())
         {
            
             if(tr[0].equalsIgnoreCase(diurna)&&tr[1].equalsIgnoreCase(diurna)&&festivo[0].equalsIgnoreCase(festivo[1]))
             {
                
                 if(festivo[0].equalsIgnoreCase("festivo"))
                 {
                        Calcular_salario c=new Calcular_salario();
                        paga_total_horas_redon = c.cal_pago_diurno_festivo(salario, total_horas_calcular_hora.getText(),h_i,h_f);
                 }else
                 {
                        Calcular_salario c=new Calcular_salario();
                        paga_total_horas_redon = c.cal_pago_diurno(salario, total_horas_calcular_hora.getText(),h_i,h_f);
                 }
             }
               if(tr[0].equalsIgnoreCase(diurna)&&tr[1].equalsIgnoreCase(diurna)&&festivo[0].equalsIgnoreCase("festivo")&&!festivo[1].equalsIgnoreCase("festivo"))
             {
                 Calcular_salario c=new Calcular_salario();
                paga_total_horas_redon = c.cal_pago_diurno_1_diurno_festivo(salario, total_horas_calcular_hora.getText(),h_i,h_f);
             }
                if(tr[0].equalsIgnoreCase(diurna)&&tr[1].equalsIgnoreCase(diurna)&&!festivo[0].equalsIgnoreCase("festivo")&&festivo[1].equalsIgnoreCase("festivo"))
             {
                 Calcular_salario c=new Calcular_salario();
                paga_total_horas_redon = c.cal_pago_diurno_diurno_2_festivo(salario, total_horas_calcular_hora.getText(),h_i,h_f);
             }
             if(tr[0].equalsIgnoreCase(nocturna)&&tr[1].equalsIgnoreCase(nocturna)&&festivo[0].equalsIgnoreCase(festivo[1]))
             {
                  if(festivo[0].equalsIgnoreCase("festivo"))
                 {
                        Calcular_salario c=new Calcular_salario();
                        paga_total_horas_redon = c.cal_pago_noctuno_festivo(salario, total_horas_calcular_hora.getText(),h_i,h_f);
                 }else
                 {
                        Calcular_salario c=new Calcular_salario();
                        paga_total_horas_redon = c.cal_pago_noctuno(salario, total_horas_calcular_hora.getText(),h_i,h_f);
                 }
             }
               if(tr[0].equalsIgnoreCase(nocturna)&&tr[1].equalsIgnoreCase(diurna)&&festivo[0].equalsIgnoreCase(festivo[1]))
             {
                 if(festivo[0].equalsIgnoreCase("festivo"))
                 {
                        Calcular_salario c=new Calcular_salario();
                       paga_total_horas_redon = c.cal_pago_nocturno_diurno_festivo(salario, total_horas_calcular_hora.getText(),h_i,h_f);
                 }else
                 {
                        Calcular_salario c=new Calcular_salario();
                        paga_total_horas_redon = c.cal_pago_nocturno_diurno(salario, total_horas_calcular_hora.getText(),h_i,h_f);
                 }
                 
             }
               if(tr[0].equalsIgnoreCase(nocturna)&&tr[1].equalsIgnoreCase(diurna)&&festivo[0].equalsIgnoreCase("festivo")&&!festivo[1].equalsIgnoreCase("festivo"))
             {
                 Calcular_salario c=new Calcular_salario();
                paga_total_horas_redon = c.cal_pago_nocturno_diurno_festivo_n(salario, total_horas_calcular_hora.getText(),h_i,h_f);
             }
              if(tr[0].equalsIgnoreCase(nocturna)&&tr[1].equalsIgnoreCase(diurna)&&!festivo[0].equalsIgnoreCase("festivo")&&festivo[1].equalsIgnoreCase("festivo"))
             {
                 Calcular_salario c=new Calcular_salario();
                paga_total_horas_redon = c.cal_pago_nocturno_diurno_festivo_d(salario, total_horas_calcular_hora.getText(),h_i,h_f);
             }
             
             if(tr[0].equalsIgnoreCase(diurna)&&tr[1].equalsIgnoreCase(nocturna)&&festivo[0].equalsIgnoreCase("festivo")&&!festivo[1].equalsIgnoreCase("festivo"))
             {
                 Calcular_salario c=new Calcular_salario();
                paga_total_horas_redon = c.cal_pago_diurno_nocturno_festivo_d(salario, total_horas_calcular_hora.getText(),h_i,h_f);
             }
              if(tr[0].equalsIgnoreCase(diurna)&&tr[1].equalsIgnoreCase(nocturna)&&!festivo[0].equalsIgnoreCase("festivo")&&festivo[1].equalsIgnoreCase("festivo"))
             {
                 Calcular_salario c=new Calcular_salario();
                paga_total_horas_redon = c.cal_pago_diurno_nocturno_festivo_n(salario, total_horas_calcular_hora.getText(),h_i,h_f);
             }
              if(tr[0].equalsIgnoreCase(diurna)&&tr[1].equalsIgnoreCase(nocturna)&&festivo[0].equalsIgnoreCase(festivo[1]))
             {
                 if(festivo[0].equalsIgnoreCase("festivo"))
                 {
                     Calcular_salario c=new Calcular_salario();
                     paga_total_horas_redon = c.cal_pago_diurno_nocturno_festivo(salario, total_horas_calcular_hora.getText(),h_i,h_f);
                 }else
                 {
                     Calcular_salario c=new Calcular_salario();
                     paga_total_horas_redon = c.cal_pago_diurno_nocturno(salario, total_horas_calcular_hora.getText(),h_i,h_f);
                 }
                 
             }
               if(tr[0].equalsIgnoreCase(nocturna)&&tr[1].equalsIgnoreCase(nocturna)&&festivo[0].equalsIgnoreCase("festivo")&&!festivo[1].equalsIgnoreCase("festivo"))
             {
                 Calcular_salario c=new Calcular_salario();
                paga_total_horas_redon = c.cal_pago_nocturno_1_nocturno_festivo(salario, total_horas_calcular_hora.getText(),h_i,h_f);
             }
                if(tr[0].equalsIgnoreCase(nocturna)&&tr[1].equalsIgnoreCase(nocturna)&&!festivo[0].equalsIgnoreCase("festivo")&&festivo[1].equalsIgnoreCase("festivo"))
             {
                 Calcular_salario c=new Calcular_salario();
                paga_total_horas_redon = c.cal_pago_nocturno_nocturno_2_festivo(salario, total_horas_calcular_hora.getText(),h_i,h_f);
             }
           
         } 
          return paga_total_horas_redon;
        
     }
    public String comparar_horas_extra_horas_sysaid()
    {   
        String result="";
        String h_inicio_s="";
        String h_final_s="";
        String h_ini_h_e=hora_inicio_calcular_hora.getText().toString();
        String h_fin_h_e=hora_final_calcular_hora.getText().toString();
        String h_ini_h_e_con="";
        String h_fin_h_e_con="";
        try {
            rs=rdm.sql("SELECT hora_inici_hoja_de_servicio,hora_finalizacion_hoja_de_servicio FROM hoja_de_servicio WHERE id_hoja_de_servicio ='"+hoja_servicio.getText().toString()+"'");
             while(rs.next())
             {
                 h_inicio_s=rs.getString("hora_inici_hoja_de_servicio");
                 h_final_s=rs.getString("hora_finalizacion_hoja_de_servicio");
             }
        rdm.cerrar_conexion();
        } catch (SQLException ex) {
            Logger.getLogger(Calcular_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
       SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
       Date date_h_ini;
       Date date_h_fin;
        try {
            date_h_ini = parseFormat.parse(h_ini_h_e);
            date_h_fin = parseFormat.parse(h_fin_h_e);
            ///System.out.println("Fechas convertidas : "+displayFormat.format(date_h_ini) + " = " + displayFormat.format(date_h_fin));
            h_ini_h_e_con=displayFormat.format(date_h_ini);
            h_fin_h_e_con=displayFormat.format(date_h_fin);
        } catch (ParseException ex) {
            Logger.getLogger(Calcular_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(h_ini_h_e_con.equalsIgnoreCase(h_inicio_s)&&h_fin_h_e_con.equalsIgnoreCase(h_final_s))
        {
            result="bien";
        }else
        {
          Alerta_msj_mal_horas aler= new Alerta_msj_mal_horas(new javax.swing.JDialog(),true);
          aler.mostrar_txt(h_ini_h_e_con, h_fin_h_e_con, h_inicio_s, h_final_s);
          aler.setVisible(true);
            result="null";
        }
    
        return result;
    }
     public String[] identificar_fechas_festivas()
     {
         String info[]=new String[2];
         info[0]="null";
         info[1]="null";
         String f_i=fecha_inicio_calcular_hora.getText();
         String f_f=fecha_final_calcular_hora.getText();
         String tro_ini[]=f_i.split("-");
         String tro_fin[]=f_f.split("-");
         String dia_mes_ini=tro_ini[0]+"-"+tro_ini[1];
         String dia_mes_fin=tro_fin[0]+"-"+tro_fin[1];
         for(int x=0; x<fechas_festivas.length;x++)
         {
             if(fechas_festivas[x].equalsIgnoreCase(dia_mes_ini))
             {
                 info[0]="festivo";
                 break;
             }             
             if(fechas_festivas[x].equalsIgnoreCase(dia_mes_fin))
             {
                 info[1]="festivo";
                 break;
             }
         }
         
         System.err.println("FECHAS FESTIVAS : "+info[0]+"  "+info[1]);
         
         return info;
     }
     public String[] clasificar_horas()
     {
         String tipo_de_horas[]=new String [2];
        int lim_h_pm=7;
        int lim_h_am=5;
        String d_horas_ini[]=hora_inicio_calcular_hora.getText().toString().split(":");
        String d_horas_fin[]=hora_final_calcular_hora.getText().toString().split(":");
        String d_ini[]=d_horas_ini[1].split(" ");
        String d_fin[]=d_horas_fin[1].split(" ");
        String h_i=d_horas_ini[0];
        String h_f=d_horas_fin[0];
        String mm_ini=d_ini[0];
        String mm_fin=d_fin[0];
        String ampm_ini=d_ini[1];
        String ampm_fin=d_fin[1];
        int h_ini=Integer.parseInt(h_i);
        int h_fin=Integer.parseInt(h_f);
        
        if(h_ini==12&&ampm_ini.equalsIgnoreCase("am"))
        {
            tipo_de_horas[0]="nocturna";
        }
        if(h_ini==12&&ampm_ini.equalsIgnoreCase("pm"))
        {
            tipo_de_horas[0]="diurna";
        }
        if(h_ini<lim_h_pm && ampm_ini.equalsIgnoreCase("pm"))
        {
             System.err.println("en esta ini<7");
            tipo_de_horas[0]="diurna";
        }
        if(h_ini>=lim_h_pm && ampm_ini.equalsIgnoreCase("pm") || h_ini<lim_h_am&& ampm_ini.equalsIgnoreCase("am"))
        {
            System.err.println("en esta ini>7");
             tipo_de_horas[0]="nocturna";
        }
         if(h_fin==12&&ampm_fin.equalsIgnoreCase("am"))
        {
            tipo_de_horas[1]="nocturna";
        }
         if(h_fin==12&&ampm_fin.equalsIgnoreCase("pm"))
        {
            tipo_de_horas[0]="diurna";
        }
        if(h_fin<lim_h_pm&&ampm_fin.equalsIgnoreCase("pm")|| h_fin==lim_h_pm&&mm_fin.equalsIgnoreCase("00")&&ampm_fin.equalsIgnoreCase("pm"))
        {
             tipo_de_horas[1]="diurna";
        }
        if(h_fin>=lim_h_pm&&ampm_fin.equalsIgnoreCase("pm")||h_fin<lim_h_am&& ampm_fin.equalsIgnoreCase("am"))
        {
            tipo_de_horas[1]="nocturna";
        }
        if(h_fin>=lim_h_am&& ampm_fin.equalsIgnoreCase("am"))
        {
            tipo_de_horas[1]="diurna";
        }
         if(h_ini>=lim_h_am&& ampm_ini.equalsIgnoreCase("am"))
        {
             System.err.println("en esta ini>=5");
            tipo_de_horas[0]="diurna";
        }
        System.err.println(h_i+":"+mm_ini+" "+ampm_ini+"  "+tipo_de_horas[0]+"\n"+h_f+":"+mm_fin+" "+ampm_fin+"  "+tipo_de_horas[1]); 
        return tipo_de_horas;
     }
     public String get_fecha_peticione()
     {
         String x="";
     
         String f_ingreso="";
         String id_peti=peticion_calcu_hora.getText();
         if(item_selec_lista_false!=null&&id_peti!=null&&id_emple!=null&&!item_selec_lista_false.isEmpty()&&!id_peti.isEmpty()&&!id_emple.isEmpty())
         {
             try {
                 rs=rdm.fechas_horas_peticiones(id_peti);
                 while(rs.next())
                 {                     
                     f_ingreso=rs.getString("fecha_peticion");
                 }
                 
             } catch (SQLException ex) {
                 Logger.getLogger(Calcular_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
                 System.err.println("Error en la consulta :"+ex);
             }
         }else
         {
             System.err.println("item_selec_lista_false esta vacio ");
         }
       
         return f_ingreso;
     }
     public String get_fecha_sysaid()
     {
         String f_sysaid="";
         String id_sy=hoja_servicio.getText();
         String n_emple=nombre_empleado_calcular_hora.getText();
         if(item_selec_lista_false!=null&&id_sy!=null&&n_emple!=null&&!item_selec_lista_false.isEmpty()&&!id_sy.isEmpty()&&!n_emple.isEmpty())
         {
             try {
                 rs=rdm.fechas_sysaid(id_sy);
                 while(rs.next())
                 {
                     f_sysaid=rs.getString("fecha_hoja_de_servicio");
                           
                 }
                 rdm.cerrar_conexion();
             } catch (SQLException ex) {
                 Logger.getLogger(Calcular_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
             }
         }else
         {
             System.err.println("item_selec_lista_false esta vacio ");
         }
         
         return f_sysaid;
     }
     public String  comparar_fechas()
     { 
         String x="";
            if(item_selec_lista_false!=null)
            {
                Alerta_dialog al=new Alerta_dialog(new javax.swing.JDialog(), true);
                Date fpeti_ini=new Date();
                Date fsy_fin=new Date();
                Date fhe_ini=new Date();
                Date fhe_fin=new Date();
                
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
               try {
                   fpeti_ini=sdf.parse(get_fecha_peticione());
                   fsy_fin=sdf.parse(get_fecha_sysaid());
                   fhe_ini=sdf.parse(fecha_inicio_calcular_hora.getText().toString());
                   fhe_fin=sdf.parse(fecha_final_calcular_hora.getText().toString());
               } catch (ParseException ex) {
                   Logger.getLogger(Calcular_horas_extra.class.getName()).log(Level.SEVERE, null, ex);
               }
               
               if(fhe_fin.equals(fsy_fin)&&fhe_ini.after(fpeti_ini))
               {
                 x="bien";
               }else
               {
                   al.mostrar_txt(fecha_inicio_calcular_hora.getText().toString(),get_fecha_peticione(),fecha_final_calcular_hora.getText().toString(),get_fecha_sysaid());
                al.setVisible(true);
               }
               
           }else{
            JOptionPane.showMessageDialog(null, "Seleccione un elemento de la lista (Solicitudes nuevas)");
            }
          
       return x;
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
      /*  try {
               for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calcular_horas_extra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form 
        java.awt.EventQueue.invokeLater(() -> {
            new Calcular_horas_extra().setVisible(true);
        });*/
        java.awt.EventQueue.invokeLater(() -> {
            Calcular_horas_extra dialog = new Calcular_horas_extra(new javax.swing.JDialog(), true);
            
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
          //  dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_calcular_calcular_hora;
    private javax.swing.JButton btn_confirmar_calcular_hora;
    private javax.swing.JButton btn_salir_calcular_hora;
    private javax.swing.JTextField fecha_calcular_hora;
    private javax.swing.JTextField fecha_final_calcular_hora;
    private javax.swing.JTextField fecha_inicio_calcular_hora;
    private javax.swing.JTextField hoja_servicio;
    private javax.swing.JTextField hora_calcular_hora;
    private javax.swing.JTextField hora_final_calcular_hora;
    private javax.swing.JTextField hora_inicio_calcular_hora;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Label label1;
    private javax.swing.JLabel lb_actualizar;
    private javax.swing.JList<String> lista_false;
    private javax.swing.JLabel msj_calcular_error;
    private javax.swing.JLabel msj_confir_calcular_horas_extra;
    private javax.swing.JTextField nombre_empleado_calcular_hora;
    private javax.swing.JPanel panel_aprobar;
    private javax.swing.JPanel panel_info;
    private javax.swing.JPanel panel_lista_nueva;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JTextField peticion_calcu_hora;
    private javax.swing.JTextField puesto_calcular_hora;
    private javax.swing.JTextField salario_calcular_hora;
    private javax.swing.JTable tbl_horas_extra;
    private javax.swing.JTextField total_calculo_calcular_hora;
    private javax.swing.JTextField total_horas_calcular_hora;
    private javax.swing.JLabel txt_msj_horas_mal;
    private javax.swing.JLabel ver_hoja_s;
    private javax.swing.JLabel ver_peti;
    // End of variables declaration//GEN-END:variables

   
}
