/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;
import clases.Color_fila_tabla;
import conexionMYSQL.resgistro_datos_mysql;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author CelesteZaldivar
 */
    public final class Ingresar_horas_extras extends javax.swing.JFrame {
       public resgistro_datos_mysql rgd=new resgistro_datos_mysql();
       public ResultSet rs=null;
       public Timer temporizador;
       public TimerTask verificar_hora_correcta;
       public int hIni=0,hFi=0,mIni=0,mFi=0,hd=0;
       public String amPmIni,amPmFi,dia,dia2,fecha_dia_ini,id_sele;
       public ArrayList<String> c_combo_nombre,lista_combo_syiad,c_hora,c_min;
       public String f[]=new String[2];
       public ArrayList<String>hora_inicio_sysaid=new ArrayList<>();
       public ArrayList<String>hora_final_sysaid=new ArrayList<>();
       public ArrayList<String>fecha_final_sysaid=new ArrayList<>();
       public ArrayList<String>comen__sysaid=new ArrayList<>();
       public String mostrar_infor[]=new String[9],datos_user[];
       public String i;
       public int todas=0,pendientes=0,rechazadas=0,aprobadas=0;
      public DefaultTableModel tabla = new DefaultTableModel(){
         @Override
        public boolean isCellEditable(int Fila, int Colum) {
            return false;
        }
    };  
    public Ingresar_horas_extras()
    {
        
    }
    public Ingresar_horas_extras(String d[]) {
    initComponents();
    this.setLocationRelativeTo(null); 
    datos_user=d;
    
    c_combo_nombre=new ArrayList<>();
    lista_combo_syiad=new ArrayList<>();
    c_hora=new ArrayList<>();
    c_min=new ArrayList<>();
    temporizador = new Timer();
    llenar_combo_hora_min();
    validar_hora_h();
    
    nombres.setText(datos_user[0]);
        
        try {
            rs=rgd.traer_sysaid(datos_user[0]);
            while(rs.next())
            {
               this.combo_sysaid.addItem(rs.getString("id_hoja_de_servicio"));
               this.lista_combo_syiad.add(rs.getString("id_hoja_de_servicio"));
               this.hora_inicio_sysaid.add(rs.getString("hora_inici_hoja_de_servicio"));
               this.hora_final_sysaid.add(rs.getString("hora_finalizacion_hoja_de_servicio"));
               this.fecha_final_sysaid.add(rs.getString("fecha_hoja_de_servicio"));
               this.comen__sysaid.add(rs.getString("trabajo_r_hoja_de_servicio"));
            }
             rgd.cerrar_conexion();
        } catch (SQLException e) {        }
       
      crear_columnas_tabla();

        Object seleccionadoInicial = combo_sysaid.getSelectedItem();

        if (seleccionadoInicial != null) {

            asignar_hora_inicio(seleccionadoInicial.toString());

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No existen hojas de servicio asignadas a este empleado.",
                    "Sin hojas de servicio",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        
      temporizador.scheduleAtFixedRate(verificar_hora_correcta,0,1*1000);
      this.nombres.addActionListener((ActionEvent ae) -> {
 
    });
     cargarTabla();
     txt_aprobadas.setText("Aprobadas ("+aprobadas+")");
     txt_pendiente.setText("Pendiente ("+pendientes+")");
     txt_rechazadas.setText("Rechazadas ("+rechazadas+")");
     txt_todas.setText("Todas ("+todas+")");
    }
    public void asignar_hora_inicio(String item)
    {
       /// String id_s=combo_sysaid.getSelectedItem().toString();
        String h_i="";
        String h_f="";
        String f_f="";
        String tra="";
      if(!item.isEmpty())
      {
        for(int x=0; x<lista_combo_syiad.size();x++)
        {
            if(lista_combo_syiad.get(x).equalsIgnoreCase(item))
            {
                h_i=hora_inicio_sysaid.get(x);
                h_f=hora_final_sysaid.get(x);
                f_f=fecha_final_sysaid.get(x);
                tra=comen__sysaid.get(x);
            }
        }
        ////////hora inicion sysaid
         SimpleDateFormat displayFormat_h_ini = new SimpleDateFormat("HH:mm");
         SimpleDateFormat parseFormat_h_ini = new SimpleDateFormat("hh:mm a");
         Date date222;
                try {
                    date222 = displayFormat_h_ini.parse(h_i); 
                   // System.out.println("\n"+parseFormat.format(date222) + " = " + displayFormat.format(date222));
                    String ho_mi_ini[]=parseFormat_h_ini.format(date222).split(":");
                    String v_[]=ho_mi_ini[1].split(" ");
                    String ma_pm_i=v_[1];
                    String h_ini_=ho_mi_ini[0];
                    String mi_ini_=v_[0];
                    //System.out.println("\n"+h_ini_+":"+mi_ini_+" "+ma_pm_i);
                    hora_inicio.getModel().setSelectedItem(h_ini_);
                    min_inicio.getModel().setSelectedItem(mi_ini_);
                    ampmi.getModel().setSelectedItem(ma_pm_i);
                } catch (ParseException ex) {
                    Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 SimpleDateFormat displayFormat_h_fin = new SimpleDateFormat("HH:mm");
                 SimpleDateFormat parseFormat_h_fin = new SimpleDateFormat("hh:mm a");
                 Date date333;
                try {
                    date333 = displayFormat_h_fin.parse(h_f); 
                   // System.out.println("\n"+parseFormat.format(date222) + " = " + displayFormat.format(date222));
                    String ho_mi_fin[]=parseFormat_h_fin.format(date333).split(":");
                    String v_[]=ho_mi_fin[1].split(" ");
                    String ma_pm_f=v_[1];
                    String h_fin_=ho_mi_fin[0];
                    String mi_fin_=v_[0];
                    //System.out.println("\n"+h_fin_+":"+mi_fin_+" "+ma_pm_f);
                    hora_final.getModel().setSelectedItem(h_fin_);
                    min_final.getModel().setSelectedItem(mi_fin_);
                    ampmf.getModel().setSelectedItem(ma_pm_f);
                } catch (ParseException ex) {
                    Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 try {
                Date fech= new SimpleDateFormat("dd-MM-yyyy").parse(f_f);
                this.fecha_final.setDate(fech);
            } catch (ParseException ex) {
                Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
            }
                 this.comen_ingreso_hora_extra.setText(tra);
      }
    }
    public void validar_hora_h()
    {
        this.verificar_hora_correcta = new TimerTask() {
            @Override
            public void run() {
                Date date = new Date(); 
                DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                String hora=""+formatoHora.format(date);
                hora_ingrsar_horas.setText(hora);
                
                DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
                String fecha=""+formatoFecha.format(date);
                fecha_dia_ini=formatoFecha.format(date);
                fecha_ingrsar_horas.setText(fecha);
               
            if("sabado".equalsIgnoreCase(dia) || "domingo".equalsIgnoreCase(dia))
              {
               /// System.err.println("Estoy en "+dia);
                if("sabado".equalsIgnoreCase(dia))
                {
                   /// System.err.println("Estoy en Sabado"+dia);
                    validar_valores_correctos_s();
                    try {
                        calcu_cantidad_horas();
                    } catch (ParseException ex) {
                        Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    verificar_valores_d();
                    try {
                        calcu_cantidad_horas();
                    } catch (ParseException ex) {
                        Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
              }
            else
            {
               // System.err.println("Estoy en Dia semana:"+dia);
                verificar_valores_correctos_l_a_v();
                try {
                    calcu_cantidad_horas();
                } catch (ParseException ex) {
                    Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }
        };
    }
    public void calcu_cantidad_horas() throws ParseException
    {   
        Date date[]=new Date[2];
        int dias[]=new int[2];
        Date d2[]=new Date[2];
        DateFormat dateFormat[]=new SimpleDateFormat[2];
        String forma[]=new String[2];
        
        if(this.fecha_inicio.getDate()!=null && this.fecha_final.getDate()!=null)
        { 
         ////System.err.println("valores de fechas:"+verificar_fecha());
         forma[0]=this.fecha_inicio.getDateFormatString();
         forma[1]=this.fecha_final.getDateFormatString();
         d2[0]=this.fecha_inicio.getDate();
         d2[1]=this.fecha_final.getDate();
         for(int x=0;x<2;x++)
            {
               dateFormat[x] = new SimpleDateFormat(forma[x]);
               f[x]=String.valueOf(dateFormat[x].format(d2[x]));
               date[x]=(Date)dateFormat[x].parse(f[x]);
               GregorianCalendar fecha_calen =new GregorianCalendar();
               fecha_calen.setTime(date[x]);
               dias[x]=fecha_calen.get(Calendar.DAY_OF_WEEK);
               dia=dia_semana(dias[0]);
               dia2=dia_semana(dias[1]);
               System.out.print("Dias:"+dias[x]+" = "+dia);
            }
        if(dia.equalsIgnoreCase("sabado")&&dias[1]==1)
        {
           hd=1;
          // this.fecha_final.setBackground(Color.LIGHT_GRAY);
          // this.txt_msg_ingresar_horas_extras.setText("");
          
        }else
        {
           hd=dias[1]-dias[0];
          // this.txt_msg_ingresar_horas_extras.setText("");
          // this.fecha_final.setBackground(Color.LIGHT_GRAY);
        }
        if(hd==-1)
        {
            this.fecha_final.setBackground(Color.red);
            this.txt_msg_ingresar_horas_extras.setForeground(Color.red);
            this.txt_msg_ingresar_horas_extras.setText("Error en las fechas: La fecha final es incorrecta"+". "+"La fecha final no debe ser menor a la inicial.");
        }
        int dias_diferentes=(int) ((d2[1].getTime()-d2[0].getTime())/86400000);
        if(dias_diferentes>1)
        {
            this.fecha_final.setBackground(Color.red);
            this.txt_msg_ingresar_horas_extras.setForeground(Color.red);
            this.txt_msg_ingresar_horas_extras.setText("Error en las fechas: La fecha final es incorrecta.");
        }
        else{
             this.fecha_final.setBackground(Color.LIGHT_GRAY);
             this.txt_msg_ingresar_horas_extras.setText("");
        }
        int vg=verificar_fecha();
       /// System.err.println(vg+" verificar fecha");
        if(vg==1)
        {
             this.fecha_final.setBackground(Color.red);
             this.txt_msg_ingresar_horas_extras.setForeground(Color.red);////La fecha final es incorrecta
             this.txt_msg_ingresar_horas_extras.setText("Error en las fechas: .");
        }
        int tm,th;
        String hora_total;
        if(verificar_color())
        {
         //   System.err.println("hay Rojos ");
            this.total_horas_ingresar_horas.setText("00:00");
        }
        else
        {  
            if(amPmIni.equalsIgnoreCase(amPmFi))
            {
                if(mIni==0)
                {
                    if(mFi<10)
                    {
                        hora_total=hora_12_calcu();
                        this.total_horas_ingresar_horas.setText(hora_total+":0"+mFi);
                        this.txt_msg_ingresar_horas_extras.setText("");
                    }else
                    {
                        tm=mFi;
                        hora_total=hora_12_calcu();
                        this.total_horas_ingresar_horas.setText(hora_total+":"+tm);
                        this.txt_msg_ingresar_horas_extras.setText("");
                    }
                }
                else
                {
                    if(mIni<mFi)
                    {
                        tm=mFi-mIni;
                        hora_total=hora_12_calcu();
                        if(tm<10)
                        {
                         this.total_horas_ingresar_horas.setText(hora_total+":0"+tm);
                         this.txt_msg_ingresar_horas_extras.setText("");
                        }else{
                         this.total_horas_ingresar_horas.setText(hora_total+":"+tm);
                         this.txt_msg_ingresar_horas_extras.setText("");
                        }
                    }
                    else{
                        int vmi;
                        tm=mFi-mIni;
                        hora_total=hora_12_calcu();
                        int t_min_h;
                        t_min_h=60*Integer.parseInt(hora_total);
                        int v=t_min_h+tm;
                        th=Math.round((v/60));
                        vmi=Math.round((v%60));
                        if(vmi<10)
                        {
                             this.total_horas_ingresar_horas.setText(th+":0"+vmi);
                             this.txt_msg_ingresar_horas_extras.setText("");
                        }
                        else
                        {
                            this.total_horas_ingresar_horas.setText(th+":"+vmi);
                            this.txt_msg_ingresar_horas_extras.setText("");
                        }
                    }
                }
            }
            if(!amPmIni.equalsIgnoreCase(amPmFi))
            {
                if(mIni==0)
                    {
                        tm=mFi;
                        hora_total= hora_12_calcu();
                     //   System.err.println("llamado de hora:"+hora_total);
                        if(tm<10){
                          this.total_horas_ingresar_horas.setText(hora_total+":0"+tm);
                          this.txt_msg_ingresar_horas_extras.setText("");
                        }else
                        {
                          this.total_horas_ingresar_horas.setText(hora_total+":"+tm);
                          this.txt_msg_ingresar_horas_extras.setText("");
                        }
                         
                    }
                    if(mIni<mFi)
                    {
                        tm=mFi-mIni;
                        hora_total= hora_12_calcu();
                        if(tm<10)
                        {
                         this.total_horas_ingresar_horas.setText(hora_total+":0"+tm);
                         this.txt_msg_ingresar_horas_extras.setText("");
                        }else{
                         this.total_horas_ingresar_horas.setText(hora_total+":"+tm);
                         this.txt_msg_ingresar_horas_extras.setText("");
                        }
                       
                    }
                    else{
                        int vmi;
                        tm=mFi-mIni;
                        hora_total= hora_12_calcu();
                      //  System.out.println("  veri.. hora_total "+hora_total);
                        int t_min_h;
                        t_min_h=60*Integer.parseInt(hora_total);
                        int v=t_min_h+tm;
                        th=Math.round((v/60));
                        vmi=Math.round((v%60));
                        if(vmi<10)
                        {
                            if(th<10)
                            {
                                 this.total_horas_ingresar_horas.setText("0"+th+":0"+vmi);
                                // System.out.println("  veri hora_final.. "+th);
                                 this.txt_msg_ingresar_horas_extras.setText("");
                            }else
                            {
                             this.total_horas_ingresar_horas.setText(th+":0"+vmi);
                             //System.out.println("  veri hora_final.. "+th);
                             this.txt_msg_ingresar_horas_extras.setText("");
                            }
                            
                        }
                        else
                        {
                            if(th<10)
                            {
                                this.total_horas_ingresar_horas.setText("0"+th+":"+vmi);
                               // System.out.println("  veri.. "+th);
                                this.txt_msg_ingresar_horas_extras.setText("");
                            } else
                            {
                                this.total_horas_ingresar_horas.setText(th+":"+vmi);
                               // System.out.println("  veri.. "+th);
                                this.txt_msg_ingresar_horas_extras.setText("");
                            }
                            
                        }
                        
                        
                    }
            }
        }
      }
    }
    public String hora_12_calcu()
    {
        int h;
        String th;
        if(hd==0)
        {
            if(amPmFi.equalsIgnoreCase(amPmIni))
            {
                if(hIni==12)
                {
                    hIni=0;
                }
                h=hFi-hIni;
            }else
            {
                 if(hIni==12)
                {
                    hIni=0;
                    if(hFi==12)
                      {hFi=0;}
                }
                int v=12-hIni;
                
                h=v+hFi;
            }
        }else
        {
            if(amPmFi.equalsIgnoreCase(amPmIni))
            {
                if(hIni==12)
                {
                    hIni=0;
                    if(hFi==12)
                    { hFi=0;}
                }
                int v=12-hIni;
                h=v+12+hFi;
              ///  System.err.println("ampmi = ampmf 12 "+h);
               ///if(hIni==0) hIni=12;
            }
            else
            {
                if(hIni==12)
                {
                    hIni=0;
                   //  System.err.println("ampmi != ampm fhIni==12 "+hIni);
                }
                int v=12-hIni;
                h=v+hFi;
               // System.err.println("ampmi != ampm  "+h);
            }
            if(hFi==12&&hIni!=12)
            {
                
                int t=12-hIni;
                h=t+0;
            //    System.err.println("hFi==12 :"+t+" :"+hIni);
            }
           
        }
         if(h<10)
         {
             th="0"+h;
           //  System.err.println("hora<10: "+th);
         }else
         {
             th=""+h;
            //  System.err.println("hora>10 : "+th);
         }
         return th;
    }
    public void crear_columnas_tabla()
    {
        tabla.addColumn("Id");
        tabla.addColumn("Nombre empleado");
        tabla.addColumn("SYSAID");
        tabla.addColumn("Fecha inicio");
        tabla.addColumn("Hora de inicio");
        tabla.addColumn("Fecha final");
        tabla.addColumn("Hora de final");
        tabla.addColumn("Comentario");
        tabla.addColumn("Total de horas");
        tabla.addColumn("Estado");
        this.tbl_mostrar_HE.setModel(tabla);
        this.tbl_mostrar_HE.setAutoscrolls (true);
      
    }
     void cargarTabla() {
       
        if(this.tbl_mostrar_HE.getRowCount()!=0)
        {
            tabla.setRowCount(0);
             llenar_tabla();
        }else
        {
             llenar_tabla();
        }    
    }
      void cargarTabla_po_estado(String estado) {
       
        if(this.tbl_mostrar_HE.getRowCount()!=0)
        {
            tabla.setRowCount(0);
             llenar_tabla_por_estado(estado);
        }else
        {
            llenar_tabla_por_estado(estado);
        }    
    }
    public void llenar_tabla()
    {
         String datos[]=new String[10];
         Color_fila_tabla c_fila=new Color_fila_tabla();
         todas=0;
         rechazadas=0;
         pendientes=0;
         aprobadas=0;
         try {
            rs=rgd.traer_hora_extra_user("ingreso_hora_extra",datos_user[0]);
            while(rs.next())
            {
                    datos[0]=rs.getString("id_ingreso_hora_extra");
                    datos[1]=rs.getString("nombre_empeleado_ingreso_hora_extra");
                    datos[2]=rs.getString("id_sysaid");
                    datos[3]=rs.getString("fecha_inicio_ingreso_hora_extra");
                    datos[4]=rs.getString("hora_inicio_ingreso_hora_extra");
                    datos[5]=rs.getString("fecha_final_ingreso_hora_extra");
                    datos[6]=rs.getString("hora_final_ingreso_hora_extra");
                    datos[7]=rs.getString("comentario_ingreso_hora_extra");
                    datos[8]=rs.getString("total_horas_extras_ingreso_horas_extras");
                    datos[9]=rs.getString("estado_ingreso_hora_extra");            
                    contar_estado(rs.getString("estado_ingreso_hora_extra"));
                    tbl_mostrar_HE.getColumnModel().getColumn(9).setCellRenderer(c_fila);
                    todas++;
                    txt_aprobadas.setText("Aprobadas ("+aprobadas+")");
                    txt_pendiente.setText("Pendiente ("+pendientes+")");
                    txt_rechazadas.setText("Rechazadas ("+rechazadas+")");
                    txt_todas.setText("Todas ("+todas+")");
                   
                    tabla.addRow(datos);    
            }
            
             rgd.cerrar_conexion();
        } catch (SQLException e) {
            System.err.println("ERROR al cargar tabla >"+e);
        }
    }
    public void llenar_tabla_por_estado(String estado)
    {
         String datos[]=new String[10];
         Color_fila_tabla c_fila=new Color_fila_tabla();
         try {
            rs=rgd.sql("SELECT * FROM ingreso_hora_extra WHERE nombre_empeleado_ingreso_hora_extra = '"+datos_user[0]+"' AND estado_ingreso_hora_extra='"+estado+"'");
            while(rs.next())
            {
                    datos[0]=rs.getString("id_ingreso_hora_extra");
                    datos[1]=rs.getString("nombre_empeleado_ingreso_hora_extra");
                    datos[2]=rs.getString("id_sysaid");
                    datos[3]=rs.getString("fecha_inicio_ingreso_hora_extra");
                    datos[4]=rs.getString("hora_inicio_ingreso_hora_extra");
                    datos[5]=rs.getString("fecha_final_ingreso_hora_extra");
                    datos[6]=rs.getString("hora_final_ingreso_hora_extra");
                    datos[7]=rs.getString("comentario_ingreso_hora_extra");
                    datos[8]=rs.getString("total_horas_extras_ingreso_horas_extras");
                    datos[9]=rs.getString("estado_ingreso_hora_extra");            
                 
                    tbl_mostrar_HE.getColumnModel().getColumn(9).setCellRenderer(c_fila);
                   
                    tabla.addRow(datos);    
            }
            
             rgd.cerrar_conexion();
        } catch (SQLException e) {
            System.err.println("ERROR al cargar tabla >"+e);
        }
    }
    public String [] cargar_datos()
    {
        String h_i,h_f,m_i,m_f;
        String datos[]=new String[12];
        if(hIni<10)
        {h_i="0"+hIni;}   
        else
        {h_i=""+hIni;}
         if(hFi<10)
        {h_f="0"+hFi;}
         else
        {h_f=""+hFi;}
         if(mIni<10)
        {m_i="0"+mIni;}
         else
        {m_i=""+mIni;}
         if(mFi<10)
        {m_f="0"+mFi;}
         else
        {m_f=""+mFi;}
        datos[0]=h_i+":"+m_i+" "+amPmIni;
        datos[1]=h_f+":"+m_f+" "+amPmFi;
        datos[2]=this.f[0];
        datos[3]=this.f[1];
        datos[4]=(String) this.nombres.getText();
        datos[5]=(String)this.combo_sysaid.getSelectedItem();
        datos[6]=(String) this.comen_ingreso_hora_extra.getText();
        datos[7]=(String) this.fecha_ingrsar_horas.getText();
        datos[8]=(String) this.hora_ingrsar_horas.getText();
        datos[9]=this.total_horas_ingresar_horas.getText();
        System.err.println("Total horas"+datos[9]);
        datos[10]="Pendiente";
        datos[11]="00.00";
     
        return datos;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_mostrar_HE = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        total_horas_ingresar_horas = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fecha_ingrsar_horas = new javax.swing.JLabel();
        hora_ingrsar_horas = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fecha_inicio = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        fecha_final = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_salir_registro = new javax.swing.JButton();
        btn_modifocar_registro = new javax.swing.JButton();
        btn_eliminar_registro = new javax.swing.JButton();
        javax.swing.JButton btn_guradar_registro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        comen_ingreso_hora_extra = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        hora_final = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        min_final = new javax.swing.JComboBox<>();
        ampmf = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        hora_inicio = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        min_inicio = new javax.swing.JComboBox<>();
        ampmi = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_msg_ingresar_horas_extras = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt2_msg_ingresar_horas_extras = new javax.swing.JLabel();
        nombres = new javax.swing.JTextField();
        combo_sysaid = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txt_aprobadas = new javax.swing.JLabel();
        txt_rechazadas = new javax.swing.JLabel();
        txt_pendiente = new javax.swing.JLabel();
        txt_todas = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ingresar horas extras");
        setFocusable(false);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setAutoscrolls(true);
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel1.setFocusCycleRoot(true);
        jPanel1.setFocusable(false);

        tbl_mostrar_HE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_mostrar_HE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_mostrar_HEMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_mostrar_HE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Seleccionar nombre");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        total_horas_ingresar_horas.setFont(new java.awt.Font("Myanmar Text", 0, 17)); // NOI18N
        total_horas_ingresar_horas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_horas_ingresar_horas.setText("00:00");

        jLabel11.setFont(new java.awt.Font("Vrinda", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Total de horas extras:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(total_horas_ingresar_horas, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(total_horas_ingresar_horas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        fecha_ingrsar_horas.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        fecha_ingrsar_horas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fecha_ingrsar_horas.setText("jLabel10");
        fecha_ingrsar_horas.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        hora_ingrsar_horas.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        hora_ingrsar_horas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hora_ingrsar_horas.setText("jLabel4");
        hora_ingrsar_horas.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Hora de finalizacion");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Fecha de inicio");

        fecha_inicio.setDateFormatString("dd-MM-yyyy");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Fecha de finalizacion");

        fecha_final.setDateFormatString("dd-MM-yyyy");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Comentario");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Hora de inicio");

        btn_salir_registro.setText("Salir");
        btn_salir_registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                click_salir_registro(evt);
            }
        });

        btn_modifocar_registro.setText("Modificar");
        btn_modifocar_registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                click_modifocar_registro(evt);
            }
        });

        btn_eliminar_registro.setText("Eliminar");
        btn_eliminar_registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminar_registroMouseClicked(evt);
            }
        });

        btn_guradar_registro.setForeground(new java.awt.Color(0, 51, 51));
        btn_guradar_registro.setText("Guardar");
        btn_guradar_registro.setToolTipText("");
        btn_guradar_registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                click_guardar_registro(evt);
            }
        });

        comen_ingreso_hora_extra.setColumns(20);
        comen_ingreso_hora_extra.setRows(5);
        jScrollPane2.setViewportView(comen_ingreso_hora_extra);

        jPanel4.setBackground(new java.awt.Color(51, 88, 155));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 57, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText(":");

        min_final.setFocusable(false);

        ampmf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PM", "AM" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hora_final, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(min_final, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ampmf, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(min_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ampmf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hora_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText(":");

        ampmi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PM", "AM" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hora_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(min_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ampmi, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(min_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ampmi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hora_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(51, 88, 155));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Horas extra - INFATLAN");
        jLabel10.setToolTipText("");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel10)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        txt_msg_ingresar_horas_extras.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_msg_ingresar_horas_extras.setForeground(new java.awt.Color(0, 51, 255));
        txt_msg_ingresar_horas_extras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_msg_ingresar_horas_extras.setText(" No debe haber campos en Rojo - Ingresar informacion correcta para calcular horas extras ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Fecha:");
        jLabel12.setPreferredSize(new java.awt.Dimension(52, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Hora:");
        jLabel13.setPreferredSize(new java.awt.Dimension(52, 20));

        txt2_msg_ingresar_horas_extras.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt2_msg_ingresar_horas_extras.setForeground(java.awt.Color.red);
        txt2_msg_ingresar_horas_extras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        nombres.setEditable(false);
        nombres.setText("jTextField3");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel14.setText("SYSAID");

        txt_aprobadas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_aprobadas.setForeground(new java.awt.Color(0, 102, 0));
        txt_aprobadas.setText("Aprobadas");
        txt_aprobadas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_aprobadas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txt_aprobadasMouseMoved(evt);
            }
        });
        txt_aprobadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_aprobadasMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_aprobadasMouseExited(evt);
            }
        });

        txt_rechazadas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_rechazadas.setForeground(new java.awt.Color(255, 153, 0));
        txt_rechazadas.setText("Rechazadas");
        txt_rechazadas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_rechazadas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txt_rechazadasMouseMoved(evt);
            }
        });
        txt_rechazadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_rechazadasMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_rechazadasMouseExited(evt);
            }
        });

        txt_pendiente.setBackground(new java.awt.Color(153, 153, 153));
        txt_pendiente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_pendiente.setForeground(new java.awt.Color(102, 102, 102));
        txt_pendiente.setText("Pendiente");
        txt_pendiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_pendiente.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txt_pendienteMouseMoved(evt);
            }
        });
        txt_pendiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_pendienteMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_pendienteMouseExited(evt);
            }
        });

        txt_todas.setBackground(new java.awt.Color(153, 153, 153));
        txt_todas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_todas.setForeground(new java.awt.Color(51, 88, 155));
        txt_todas.setText("Todas");
        txt_todas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_todas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                txt_todasMouseMoved(evt);
            }
        });
        txt_todas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_todasMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_todasMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_msg_ingresar_horas_extras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_sysaid, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hora_ingrsar_horas, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_ingrsar_horas, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(107, 107, 107)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(148, 148, 148))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(fecha_final, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_guradar_registro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_modifocar_registro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_eliminar_registro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_salir_registro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_aprobadas)
                        .addGap(18, 18, 18)
                        .addComponent(txt_pendiente)
                        .addGap(18, 18, 18)
                        .addComponent(txt_rechazadas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_todas))
                    .addComponent(txt2_msg_ingresar_horas_extras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(hora_ingrsar_horas)
                    .addComponent(fecha_ingrsar_horas)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_sysaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha_final, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fecha_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 22, Short.MAX_VALUE)
                .addComponent(txt_msg_ingresar_horas_extras, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt2_msg_ingresar_horas_extras, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guradar_registro)
                    .addComponent(btn_eliminar_registro)
                    .addComponent(btn_modifocar_registro)
                    .addComponent(btn_salir_registro)
                    .addComponent(txt_rechazadas)
                    .addComponent(txt_pendiente)
                    .addComponent(txt_aprobadas)
                    .addComponent(txt_todas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void click_guardar_registro(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_click_guardar_registro
        String d[]=cargar_datos();
        String id_pe="";
        if(!verificar_color()){
         for(String d1:d)
         {
            if(d1==null||d1.isEmpty())
            {
              JOptionPane.showMessageDialog(null, "Hay campos vacíos");
              return;
            }
         }
         try {
            rs=rgd.verificar_existencia(d[4],d[5]);
            while(rs.next())
            {
                if(!rs.getBoolean(1)) {
                    System.err.println("NO existe ID peticion");
                    System.out.println("se gurado");
                    try {
                       String msj=this.rgd.guardarDatos(d, "ingreso_hora_extra");
                       System.out.println("se gurado"+msj);

                   } catch (SQLException ex) {
                       Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
                   }
                  
                   this.comen_ingreso_hora_extra.setText("");
                   this.total_horas_ingresar_horas.setText("00:00");
                 
                }
                else
                {
                    Mensage m=new Mensage(this,false);
                    String info[]={d[4],d[5]};
                     m.setVisible(true);
                    m.msg(info);
                   
                    System.err.println("Ya existe");
                }
            }
                
            
        } catch (SQLException ex) {
            Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else
        {
            JOptionPane.showMessageDialog(null, "No debe haber campos en rojo");
        }
          cargarTabla();
           try {
               rgd.cerrar_conexion();
           } catch (SQLException ex) {
               Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
           }
    }//GEN-LAST:event_click_guardar_registro

    private void click_modifocar_registro(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_click_modifocar_registro
           String datos[]=cargar_datos();
           String info[]={datos[4],datos[5],datos[6],datos[2],datos[3],datos[0],datos[1],datos[9]};
           for(String c:datos)
           {
               if(c.isEmpty())
               {
                   JOptionPane.showMessageDialog(null, "Hay campos vacios");
                   return;
               }
           }
                  try {
                     String d[]={datos[4],datos[5],datos[6],datos[2],datos[3],datos[0],datos[1],datos[9]};
                     String msj=rgd.modificar_datos("ingreso_hora_extra", d, id_sele);
                     System.err.println(""+msj);
                     cargarTabla();
                     
                    } catch (SQLException ex) {
                      Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                    System.err.println("NO existe ID peticion Modificar");
                    return;
                
    }//GEN-LAST:event_click_modifocar_registro

    private void click_salir_registro(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_click_salir_registro

        Login l=new Login();
        l.setVisible(true);
        dispose();
     //  System.exit(0);
       
    }//GEN-LAST:event_click_salir_registro

    private void tbl_mostrar_HEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_mostrar_HEMouseClicked
        // TODO add your handling code here:
         int fila_seleccionada=this.tbl_mostrar_HE.rowAtPoint(evt.getPoint());
         id_sele=this.tbl_mostrar_HE.getValueAt(fila_seleccionada, 0).toString();
         for(int x=0; x<mostrar_infor.length; x++)
         {
             mostrar_infor[x]=this.tbl_mostrar_HE.getValueAt(fila_seleccionada, x).toString();
           
         }
       
         combo_sysaid.getModel().setSelectedItem(this.tbl_mostrar_HE.getValueAt(fila_seleccionada, 2).toString());
         String h1=this.tbl_mostrar_HE.getValueAt(fila_seleccionada, 4).toString();
         String ho_ini[]=info_de_combo(h1);
         this.hora_inicio.getModel().setSelectedItem(ho_ini[0]);
         this.min_inicio.getModel().setSelectedItem(ho_ini[1]);
         this.ampmi.getModel().setSelectedItem(ho_ini[2]);
         
         String h2=this.tbl_mostrar_HE.getValueAt(fila_seleccionada, 6).toString();
         String ho_fin[]=info_de_combo(h2);
         this.hora_final.getModel().setSelectedItem(ho_fin[0]);
         this.min_final.getModel().setSelectedItem(ho_fin[1]);
         this.ampmf.getModel().setSelectedItem(ho_fin[2]);

         try {
            Date fech1= new SimpleDateFormat("dd-MM-yyyy").parse(this.tbl_mostrar_HE.getValueAt(fila_seleccionada, 3).toString());
            this.fecha_inicio.setDate(fech1);
         } catch (ParseException ex) {
            Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
         }
          try {
            Date fech2= new SimpleDateFormat("dd-MM-yyyy").parse(this.tbl_mostrar_HE.getValueAt(fila_seleccionada, 5).toString());
            this.fecha_final.setDate(fech2);
              System.err.println("fecha una:"+fech2.toString());
        } catch (ParseException ex) {
            Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
        }
          this.total_horas_ingresar_horas.setText(this.tbl_mostrar_HE.getValueAt(fila_seleccionada, 8).toString());
         
          this.comen_ingreso_hora_extra.setText(this.tbl_mostrar_HE.getValueAt(fila_seleccionada, 7).toString());
          for(int b=0; b<10;b++)
          {
              System.err.println("Tabla Seleccion :"+this.tbl_mostrar_HE.getValueAt(fila_seleccionada, b).toString());
          }
    }//GEN-LAST:event_tbl_mostrar_HEMouseClicked

    private void btn_eliminar_registroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminar_registroMouseClicked
     
     if(id_sele.isEmpty())
     {
        JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento en la tabla para eliminar.");
     }else
     {
         int valor_res= JOptionPane.showConfirmDialog(null, "                           ¡Aviso!                  \nUna vez eliminado no se puede recuperar."+"\n¿Desea eliminar esta información?");
        if(valor_res==0)
        {
            String msj=rgd.eliminar_fila("ingreso_hora_extra",id_sele, "id_ingreso_hora_extra");
            //JOptionPane.showMessageDialog(null, msj);
            cargarTabla();
        } 
     }
     
    }//GEN-LAST:event_btn_eliminar_registroMouseClicked

    private void txt_aprobadasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_aprobadasMouseMoved
      this.txt_aprobadas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0)));
    }//GEN-LAST:event_txt_aprobadasMouseMoved

    private void txt_pendienteMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_pendienteMouseMoved
      this.txt_pendiente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
    }//GEN-LAST:event_txt_pendienteMouseMoved

    private void txt_rechazadasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_rechazadasMouseMoved
      this.txt_rechazadas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
    }//GEN-LAST:event_txt_rechazadasMouseMoved

    private void txt_rechazadasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_rechazadasMouseExited
        this.txt_rechazadas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_txt_rechazadasMouseExited

    private void txt_pendienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_pendienteMouseExited
       this.txt_pendiente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_txt_pendienteMouseExited

    private void txt_aprobadasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_aprobadasMouseExited
        this.txt_aprobadas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_txt_aprobadasMouseExited

    private void txt_aprobadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_aprobadasMouseClicked
       
        cargarTabla_po_estado("Aprobada");
    }//GEN-LAST:event_txt_aprobadasMouseClicked

    private void txt_pendienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_pendienteMouseClicked
        cargarTabla_po_estado("Pendiente");
    }//GEN-LAST:event_txt_pendienteMouseClicked

    private void txt_rechazadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_rechazadasMouseClicked
      cargarTabla_po_estado("Rechazada");
    }//GEN-LAST:event_txt_rechazadasMouseClicked

    private void txt_todasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_todasMouseMoved
        this.txt_todas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,88,155)));
    }//GEN-LAST:event_txt_todasMouseMoved

    private void txt_todasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_todasMouseClicked
        cargarTabla();
    }//GEN-LAST:event_txt_todasMouseClicked

    private void txt_todasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_todasMouseExited
        this.txt_todas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }//GEN-LAST:event_txt_todasMouseExited
    public void verificar_valores_correctos_l_a_v()
    { 
       hIni=Integer.parseInt(hora_inicio.getSelectedItem().toString());
       hFi=Integer.parseInt(hora_final.getSelectedItem().toString());
       mIni=Integer.parseInt(min_inicio.getSelectedItem().toString());
       mFi=Integer.parseInt(min_final.getSelectedItem().toString());
       amPmIni=ampmi.getSelectedItem().toString();
       amPmFi=ampmf.getSelectedItem().toString();
      
        if(hIni>=4 && amPmIni.equalsIgnoreCase("Pm") || hIni<=7 && amPmIni.equalsIgnoreCase("Am")){     
            if(hIni==12&& amPmIni.equalsIgnoreCase("Pm"))
            {
                color_red("r_i");
                this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: No se puede iniciar a las 12 p.m. los días lunes a viernes.");
                return;
            }
            color_white("w_i");
            this.txt2_msg_ingresar_horas_extras.setText("");
        }else
        {
          color_red("r_i");
          this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de inicio es incorrecta.");
        }
        if(hFi>=4 && amPmFi.equalsIgnoreCase("pm")||hFi<=8 && amPmFi.equalsIgnoreCase("am"))
        {
            if(hFi==12&& amPmIni.equalsIgnoreCase("Pm"))
            {
                color_red("r_f");
                this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: No se puede finalizar a las 12 p.m. los días lunes a viernes.");
                return;
            }
           this.txt2_msg_ingresar_horas_extras.setText("");
           color_white("w_f");
        }
        else
        {
          color_red("r_f");
          this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de finalización es incorrecta.");
         
        }
        //hd==0
        if(hd==0&&amPmIni.equalsIgnoreCase(amPmFi)&&hIni==hFi&&mIni==mFi)
        {
          this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: Las horas no deben ser iguales.");
          color_red("t");
        }
        if(hd==0&&amPmIni.equalsIgnoreCase(amPmFi)&&hIni==hFi&&mIni>mFi)
        {
          this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: El minuto de inicio no es valido.");
          color_red("r_i");
        }
        if(hd==0&& amPmIni.equalsIgnoreCase(amPmFi)&&hIni>hFi)
        {
            if(hIni==12&&amPmIni.equalsIgnoreCase("am"))
            {
                color_white("w_i");
                this.txt2_msg_ingresar_horas_extras.setText("");
                return;
            }
            color_red("t");
            this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de finalización no es valida.");
        }
        if(hd==0&& !amPmIni.equalsIgnoreCase(amPmFi))
        {
            this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de inicio y la hora de finalización no son validas.");
            color_red("t");
        }
        ////hd==1
        if(hd==1&&amPmIni.equalsIgnoreCase(amPmFi))
        {
             this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de inicio y la hora de finalización no son validas.");
            color_red("t");
        }
        if(!amPmIni.equalsIgnoreCase(amPmFi)&&hd==1)
        {
            if(amPmIni.equalsIgnoreCase("pm")&& hFi<=8 && amPmFi.equalsIgnoreCase("am"))
            {
                color_white("w_f");
                this.txt2_msg_ingresar_horas_extras.setText("");
                return;
            }
            else
            {
                if(hFi==12&& amPmFi.equalsIgnoreCase("am"))
                {
                 color_white("w_f");
                 this.txt2_msg_ingresar_horas_extras.setText("");
                 return;
                }
            }
            this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de inicio no es valida.");
            color_red("r_i");
        }
        
    }
    public void validar_valores_correctos_s()
    {
        hIni=Integer.parseInt(hora_inicio.getSelectedItem().toString());
        hFi=Integer.parseInt(hora_final.getSelectedItem().toString());
        mIni=Integer.parseInt(min_inicio.getSelectedItem().toString());
        mFi=Integer.parseInt(min_final.getSelectedItem().toString());
        amPmIni=ampmi.getSelectedItem().toString();
        amPmFi=ampmf.getSelectedItem().toString();
       if(hIni<=7 && amPmIni.equalsIgnoreCase("am")||hIni<=12 && amPmIni.equalsIgnoreCase("pm"))
       {
           color_white("w_i"); 
           this.txt2_msg_ingresar_horas_extras.setText("");
       }
       else
       {
           color_red("r_i");
           this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de inicio no es valida");
       }
       if(hFi<=8 && amPmFi.equalsIgnoreCase("am")||hFi<=12 && amPmFi.equalsIgnoreCase("pm"))
       {
           color_white("w_f");
           this.txt2_msg_ingresar_horas_extras.setText("");
       }
       else
       {
            color_red("r_f");
            this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de finalizació no es valida");
       }
        //hd==0
       if(hd==0&&amPmIni.equalsIgnoreCase("am")&& hIni==12)
        {
          this.txt2_msg_ingresar_horas_extras.setText("");
          color_white("w_i");
        }
       if(hd==0&&amPmFi.equalsIgnoreCase("am")&& hFi==12)
        {
            this.txt2_msg_ingresar_horas_extras.setText("");
            color_white("w_f");
        }
       if(hd==0&&hIni==hFi && amPmIni.equalsIgnoreCase(amPmFi)&& mIni==mFi)
       {
            this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de inicio y la hora de finalizacón no deben ser iguales.");
            color_red("t");
       } 
       if(hd==0&& amPmIni.equalsIgnoreCase(amPmFi)&& hIni==hFi&&mIni>mFi)
       {  
            this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: El minuto de inicio no es valido.");
            color_red("r_i");
       }
       if(amPmIni.equalsIgnoreCase(amPmFi)&& hd==0 && hIni>hFi)
       {
          if(amPmIni.equalsIgnoreCase("am")&& hIni==12)
           {
            this.txt2_msg_ingresar_horas_extras.setText("");
            color_white("w_i");
            return;
           }
          if(amPmIni.equalsIgnoreCase("pm")&& hIni==12)
           {
            this.txt2_msg_ingresar_horas_extras.setText("");
            color_white("w_i");
            return;
           }
            this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de inicio no es valida.");
           color_red("r_i");
       }
       if(!amPmIni.equalsIgnoreCase(amPmFi)&& hd==0)
       {
           this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de inicio y la hora de finalizació no son validas.");
           color_red("t");
       }
       ////hd==1
       if(hd==1 && amPmIni.equalsIgnoreCase("am"))
       {
          this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora de inicio y la hora de finalizació no son validas.");
           color_red("t");
       }
     
       if(hd==1 &&dia2.equalsIgnoreCase("domingo") && amPmIni.equalsIgnoreCase("pm"))
       {
               this.txt2_msg_ingresar_horas_extras.setText("");
               color_white("t");
       }
       else
       {
           this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: Los valores no son validas.");
       }
       if(!verificar_color())
       {
           this.txt2_msg_ingresar_horas_extras.setText("");
       }
       
   }
    public void verificar_valores_d()
    {
       hIni=Integer.parseInt(hora_inicio.getSelectedItem().toString());
       hFi=Integer.parseInt(hora_final.getSelectedItem().toString());
       mIni=Integer.parseInt(min_inicio.getSelectedItem().toString());
       mFi=Integer.parseInt(min_final.getSelectedItem().toString());
       amPmIni=ampmi.getSelectedItem().toString();
       amPmFi=ampmf.getSelectedItem().toString();
      
       //System.err.println(hIni+":"+hFi+":"+mIni+":"+mFi+" hd:"+hd);
       if(hIni<=12&& amPmIni.equalsIgnoreCase("am") || hIni<=12 && amPmIni.equalsIgnoreCase("pm"))
       {
           color_white("w_i");
          this.txt2_msg_ingresar_horas_extras.setText("");
       }
       else
       {
           color_red("r_i");
           this.txt2_msg_ingresar_horas_extras.setForeground(Color.red);
           this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora inicial no es valida");
       }
       if(hFi<=12&& amPmFi.equalsIgnoreCase("pm")||hFi<=12&& amPmFi.equalsIgnoreCase("am"))
       {
          color_white("w_f");
          this.txt2_msg_ingresar_horas_extras.setText("");

       } else
       {
          color_red("r_f");
          
          this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora final no es valida");
       }
       //hd=0
       if(hIni==hFi && amPmIni.equalsIgnoreCase(amPmFi)&& mIni==mFi&&hd==0)
        {
            this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: Los valores son incorrectos");
            color_red("t");
        }
       if(hd==0&& amPmIni.equalsIgnoreCase(amPmFi)&& hIni==hFi&&mIni>mFi)
       { 
           this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: Los valores son incorrectos");
           color_red("r_i");
       }
       if(hd==0&& amPmIni.equalsIgnoreCase("am")&&amPmFi.equalsIgnoreCase("pm"))
       { 
           
           this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: Los valores son incorrectos");
           color_white("t");
       }
       if(amPmIni.equalsIgnoreCase(amPmFi)&& hd==0&&hIni>hFi)
       {
           if(hIni==12 && amPmIni.equalsIgnoreCase("am"))
           {
                color_white("w_i");
                this.txt2_msg_ingresar_horas_extras.setText("");
                return;
           }
            this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora inicial es incorrecta");
            color_red("r_i");
       }
       if(hIni==12&&amPmIni.equalsIgnoreCase("pm"))
       {
            this.txt2_msg_ingresar_horas_extras.setText("");
            color_white("w_i");
       }
       
       if(hd==0&& amPmIni.equalsIgnoreCase("pm")&&amPmFi.equalsIgnoreCase("am"))
       {
            this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: Los valores son incorrectos");
            color_red("t");
       }
       //hd==1
       if(amPmIni.equalsIgnoreCase(amPmFi)&& hd==1)
       {
           if(amPmIni.equalsIgnoreCase("am"))
           {
               if(hFi>8){
                    if(hFi==12)
                    {
                      this.txt2_msg_ingresar_horas_extras.setText("");
                      color_white("w_f");
                      return;
                    }
                     this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: La hora final es incorrecta");
                     color_red("r_f");
               }else{
                     this.txt2_msg_ingresar_horas_extras.setText("");
                    color_white("t");
               }
               
           }
           
           if(amPmIni.equalsIgnoreCase("pm"))
           {
                this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: Los valores son incorrectos");
                color_red("t");
           }
       }
       if(!amPmIni.equalsIgnoreCase(amPmFi)&& hd==1)
       {
           if(amPmIni.equalsIgnoreCase("pm"))
           {
               if(hFi>8)
               {    
                   if(hFi==12)
                   {
                        this.txt2_msg_ingresar_horas_extras.setText("");
                        color_white("w_f");
                        return;
                   }
                   this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: la hora final es incorecta");
                   color_red("r_f");
               }else{
                    this.txt2_msg_ingresar_horas_extras.setText("");
                    color_white("t");
               }
             
           }
          
           if(amPmIni.equalsIgnoreCase("am"))
           {
                this.txt2_msg_ingresar_horas_extras.setText("Error en las horas: la hora incial es incorecta");
                color_red("t");
           }
           
       }
       if(!verificar_color())
       {
           this.txt2_msg_ingresar_horas_extras.setText("");
       }
   }
    public int verificar_fecha() throws ParseException
    {  
        int i = 0;
        String f_ini=this.fecha_inicio.getDateFormatString();
        String f_fin=this.fecha_final.getDateFormatString();
        
        SimpleDateFormat form_ini=new SimpleDateFormat(f_ini);
        SimpleDateFormat form_fin=new SimpleDateFormat(f_fin);
        
        Date d1_ini=this.fecha_inicio.getDate();
        Date d1_fin=this.fecha_final.getDate();
        
        String fe_ini=String.valueOf(form_ini.format(d1_ini));
        String fe_fin=String.valueOf(form_fin.format(d1_fin));
        
        Date d_ini=(Date)form_ini.parse(fe_ini);
        Date d_fin=(Date)form_fin.parse(fe_fin);
       
        if(d1_ini.compareTo(d1_fin)>0)
        {
         i=1;
         ///fecha final es menor a fecha inicial
        }else
        {
            if(d1_ini.compareTo(d1_fin)==0)
            {
                 i=0;
                // System.err.println(" fecha iguales = "+i+" fecha inicio"+d1_ini+" fecha final"+d1_fin);
                 
            }else
            {
                 if(d1_ini.compareTo(d1_fin)==-1)
                 {
                //   System.err.println("ggg = "+d1_ini.compareTo(d1_fin));
                   i=-1;
                 }
               // i=-1;
               // System.err.println("Verificar fecha - "+i);
            }
        } 
        System.err.println("Verificar fecha ="+i);
        return i;
    }
    public void color_red(String i)
    {
       if("r_i".equalsIgnoreCase(i))
       {
        this.hora_inicio.setBackground(Color.red);
        this.ampmi.setBackground(Color.red);
        this.min_inicio.setBackground(Color.red);
       }
       if("r_f".equalsIgnoreCase(i))
       {
        this.hora_final.setBackground(Color.red);
        this.ampmf.setBackground(Color.red);
        this.min_final.setBackground(Color.red);
       }
       if("t".equalsIgnoreCase(i))
       {
        this.hora_final.setBackground(Color.red);
        this.hora_inicio.setBackground(Color.red);
        this.ampmi.setBackground(Color.red);        
        this.ampmf.setBackground(Color.red);
        this.min_inicio.setBackground(Color.red);
        this.min_final.setBackground(Color.red);
       }
   }
    public void color_white(String i)
    {
       if("w_i".equalsIgnoreCase(i))
       {
        this.hora_inicio.setBackground(Color.white);
        this.ampmi.setBackground(Color.white);
        this.min_inicio.setBackground(Color.white);
       }
       if("w_f".equalsIgnoreCase(i))
       {
        this.hora_final.setBackground(Color.white);
        this.ampmf.setBackground(Color.white);
        this.min_final.setBackground(Color.white);
       }
       if("t".equalsIgnoreCase(i))
       {
        this.hora_final.setBackground(Color.white);
        this.hora_inicio.setBackground(Color.white);
        this.ampmi.setBackground(Color.white);        
        this.ampmf.setBackground(Color.white);
        this.min_inicio.setBackground(Color.white);
        this.min_final.setBackground(Color.white);
       }
   }
    private void llenar_combo_hora_min()
    {
       for(int x=1; x<13;x++)
       {
           if(x<10)
           {
            hora_inicio.addItem("0"+x);
            hora_final.addItem("0"+x);
            c_hora.add("0"+x);
           }
           else
           {
            hora_inicio.addItem(""+x);
            hora_final.addItem(""+x);
            c_hora.add(""+x);
           }
       }
       for(int y=0; y<60 ;y++)
       {
           if(y<10)
           {
               min_inicio.addItem("0"+y);
               min_final.addItem("0"+y);
               c_min.add("0"+y);
           }else
           {
                min_inicio.addItem(""+y);
                min_final.addItem(""+y);
                c_min.add(""+y);
           }
       }
    }
    public String dia_semana(int i)
    {
       String dia_sem="";
       switch(i)
       {
           case 1:
               dia_sem="Domingo";
           break;
           
           case 2:
               dia_sem="Lunes";
           break;
           
           case 3:
               dia_sem="Martes";
           break;
           
           case 4:
               dia_sem="Miercoles";
           break;
           
           case 5:
               dia_sem="Jueves";
           break;
           
           case 6:
               dia_sem="Viernes";
           break;
           
           case 7:
               dia_sem="Sabado";
               break;
           default:
               break;
           
       }
       return dia_sem; 
   }
    public boolean verificar_color()
    {
        int red=0xffff0000;
        Color cIni=this.hora_inicio.getBackground();
        Color cFin=this.hora_final.getBackground();
        Color apFin=this.ampmf.getBackground();
        Color apIni=this.ampmi.getBackground();
        Color f_fin=this.fecha_final.getBackground();
        return cIni.getRGB()==red|| cFin.getRGB()==red || apIni.getRGB()==red ||apFin.getRGB()==red ||f_fin.getRGB()==red;
    }
    public String [] info_de_combo(String h)
    {
        String h_i1[]=h.split(":");
         for(int x=0;x<h_i1.length; x++)
         {
             System.err.println("hora inicio "+h_i1[x]);
         }
         
         String[] m_I2=h_i1[1].split(" ");  
          for(int x=0;x<m_I2.length; x++)
         {
             System.err.println("PARTE "+m_I2[x]);
         }
          
        String[] d={h_i1[0],m_I2[0],m_I2[1]};
        System.err.println("PARTE final "+d[0]+":"+d[1]+":"+d[2]);
        return d;
    }
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
            java.util.logging.Logger.getLogger(Ingresar_horas_extras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
           new Ingresar_horas_extras().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> ampmf;
    public javax.swing.JComboBox<String> ampmi;
    private javax.swing.JButton btn_eliminar_registro;
    private javax.swing.JButton btn_modifocar_registro;
    private javax.swing.JButton btn_salir_registro;
    private javax.swing.JComboBox<String> combo_sysaid;
    private javax.swing.JTextArea comen_ingreso_hora_extra;
    private com.toedter.calendar.JDateChooser fecha_final;
    private javax.swing.JLabel fecha_ingrsar_horas;
    private com.toedter.calendar.JDateChooser fecha_inicio;
    public javax.swing.JComboBox<String> hora_final;
    private javax.swing.JLabel hora_ingrsar_horas;
    public javax.swing.JComboBox<String> hora_inicio;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JComboBox<String> min_final;
    public javax.swing.JComboBox<String> min_inicio;
    private javax.swing.JTextField nombres;
    public javax.swing.JTable tbl_mostrar_HE;
    private javax.swing.JLabel total_horas_ingresar_horas;
    private javax.swing.JLabel txt2_msg_ingresar_horas_extras;
    private javax.swing.JLabel txt_aprobadas;
    private javax.swing.JLabel txt_msg_ingresar_horas_extras;
    private javax.swing.JLabel txt_pendiente;
    private javax.swing.JLabel txt_rechazadas;
    private javax.swing.JLabel txt_todas;
    // End of variables declaration//GEN-END:variables

    private String verificar_peticion(String d) {
        
        String id_pe="";     
        try {
                 rs=rgd.traerDato("ingreso_hora_extra",d,"id_ingreso_hora_extra ","peticion_empleado_ingreso_hora_extra");
                   while(rs.next())
                   {
                      id_pe=rs.getString("id_ingreso_hora_extra");
                   }
             } catch (SQLException ex) {
                 Logger.getLogger(Ingresar_horas_extras.class.getName()).log(Level.SEVERE, null, ex);
             }
        return id_pe;
    }

    private void contar_estado(String string) {
      
       switch(string)
       {
           case "Rechazada":
               rechazadas++;
               break;
           case "Pendiente":
               pendientes++;
               break;
           case "Aprobada":
               aprobadas++;
               break;
               
       }
    }
}