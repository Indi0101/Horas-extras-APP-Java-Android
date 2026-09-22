
package pconexionsql;

import java.sql.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author CelesteZaldivar
 */
public class resgistro_datos_mysql {
    public  Statement st;
    public  ResultSet rs;
    public  Connection con;
    private final PconexionSQL conexion=new PconexionSQL();
    public resgistro_datos_mysql()
    {}
    /**
     *
     * @param datos
     * @param tabla
     * @return
     * @throws SQLException
     */
    public String guardarDatos(String  datos[],String tabla) throws SQLException
    {
       con = conexion.conexion();
       st=con.createStatement();
         if(tabla.equalsIgnoreCase("puesto_departamento"))
         {
              String d;
              d = datos[0];
            st.execute("INSERT INTO puesto_departamento VALUES(null,'"+datos[1]+"','"+d+"')");
            
             return  "se guardo exitozamente";
         }
          if(tabla.equalsIgnoreCase("departamento_trabajo"))
         {
              String d;
              d = datos[0];
            st.execute("INSERT INTO departamento_trabajo VALUES(null,'"+d+"')");
          
             return  "se guardo exitozamente";
         }
         if(tabla.equalsIgnoreCase("empleado"))
         {
            st.execute("INSERT INTO "+tabla+" (nombre_completo_empleado, identidad_empleado,direccion_empleado, cel_empleado, correo__empleado, rtn__empleado,estado_civil_empleado,genero_empleado,salario_empleado,fecha_nacimiento_empleado,puesto_empleado,fecha_de_ingreso_empleado,hora_de_ingreso_empleado,contra_empleado) VALUES('"+datos[0]+"','"+datos[1]+"','"+datos[2]+"','"+datos[3]+"','"+datos[4]+"','"+datos[5]+"','"+datos[6]+"','"+datos[7]+"','"+datos[8]+"','"+datos[9]+"','"+datos[10]+"','"+datos[11]+"','"+datos[12]+"','"+datos[13]+"')");
          
            return  "se guardo exitosamente";
         }
        if(tabla.equalsIgnoreCase("agencias"))
        {
            st.execute("INSERT INTO "+tabla+" VALUES(null,'"+datos[0]+"','"+datos[1]+"','"+datos[2]+"','"+datos[3]+"','"+datos[4]+"')");
           
            return  "se guardo exitosamente";

        }
        if(tabla.equalsIgnoreCase("peticiones"))
        {
            int v = Integer.parseInt(datos[5]);
            st.execute("INSERT INTO "+tabla+" VALUES(null,'"+datos[0]+"','"+datos[1]+"','"+datos[2]+"','"+datos[3]+"','"+datos[4]+"',"+v+",'"+datos[6]+"','"+datos[7]+"','null','null')");
          
            return  "se guardo exitosamente";

        }
        if(tabla.equalsIgnoreCase("categorias_problema"))
        {
            String d=datos[0];
            st.execute("INSERT INTO "+tabla+" VALUES(null,'"+d+"')");
         
            return  "se guardo exitosamente";
        }
        if(tabla.equalsIgnoreCase("problema"))
        {
            st.execute("INSERT INTO "+tabla+" VALUES(null,'"+datos[0]+"','"+datos[1]+"')");
            
            return  "se guardo exitosamente";
        }
      
           return  "ERROR AL GUARDAR";  
    }
    public ResultSet traer_datos_todos(String tabla) throws SQLException
    {
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        rs=st.executeQuery("SELECT * FROM "+tabla+"");
      
        return rs;
    }
     public ResultSet traer_datos_distintos(String tabla) throws SQLException
    {
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        rs=st.executeQuery("SELECT DISTINCT nombre_empeleado_ingreso_hora_extra,estado_ingreso_hora_extra,fecha__ingreso_hora_extra FROM "+tabla+"");
       
        return rs;
    }
    public ResultSet traer_datos_todos_condicion(String tabla,String columna,String condi[]) throws SQLException
    {
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        rs=st.executeQuery("SELECT * FROM "+tabla+" WHERE "+columna+"='"+condi[0]+"' OR "+columna+"='"+condi[1]+"'");
        
        return rs;
    }
    public ResultSet sql(String sql) throws SQLException
    {
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        rs=st.executeQuery(sql);
        
        return rs;
    }
    public ResultSet traer_una_fila_hora_extra(String tabla,String tbl2, String id)
    {
        try {
            con=conexion.conexion();
            st=(Statement)con.createStatement();
            rs=st.executeQuery("SELECT hora_inicio_ingreso_hora_extra,fecha_inicio_ingreso_hora_extra,hora_final_ingreso_hora_extra,fecha_final_ingreso_hora_extra,nombre_empeleado_ingreso_hora_extra,total_horas_extras_ingreso_horas_extras,fecha__ingreso_hora_extra,hora__ingreso_hora_extra,id_sysaid,puesto_empleado,id_empleado,salario_empleado FROM "+tabla+","+tbl2+" WHERE id_ingreso_hora_extra ='"+id+"' AND "+tabla+".nombre_empeleado_ingreso_hora_extra="+tbl2+".nombre_completo_empleado");
           
        } catch (SQLException ex) {
            Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rs;
    }
    public ResultSet traer_valores_todos_condicion(String tabla,String filtro,String colum) throws SQLException
    {
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        
        rs=st.executeQuery("SELECT * FROM "+tabla+" WHERE "+colum+" = '"+filtro+"'");
        //JOptionPane.showMessageDialog(null, ""+rs);
        // cerrar_conexion();
        return rs;
    }
    public ResultSet traer_valores_f2(String tabla,String filtro[],String colum[]) throws SQLException
    {
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        rs=st.executeQuery("SELECT * FROM "+tabla+" WHERE "+colum[0]+" = '"+filtro[0]+"' OR "+colum[1]+"='"+filtro[1]+"'");
        //JOptionPane.showMessageDialog(null, ""+rs);
         //cerrar_conexion();
        return rs;
    }
    public String modificar_fila(String tabla,String indicador, String valor_nuevo[])
    {
        con=conexion.conexion();
        try {
            st=(Statement)con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(tabla.equalsIgnoreCase("puesto_departamento"))
        {
            try {
                st.execute("UPDATE "+tabla+" SET nombre_puesto_departamento = '"+valor_nuevo[0]+"',nombre_depa_trabajo = '"+valor_nuevo[1]+"' WHERE id_puesto_departamento = '"+indicador+"'");
                // cerrar_conexion();
                return "Se modifico con exito";
            } catch (SQLException ex) {
                Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(tabla.equalsIgnoreCase("departamento_trabajo"))
        {
            try {
                st.execute("UPDATE "+tabla+" SET nombre_depa_trabajo = '"+valor_nuevo[0]+"' WHERE id_depa_trabajo = '"+indicador+"'");
              ////   cerrar_conexion();
                return "Se modifico con exito";
            } catch (SQLException ex) {
                Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(tabla.equalsIgnoreCase("empleado"))
        {
            try {
                
                st.execute("UPDATE "+tabla+" SET nombre_completo_empleado = '"+valor_nuevo[0]+"',identidad_empleado='"+valor_nuevo[1]+"',direccion_empleado='"+valor_nuevo[2]+"',cel_empleado='"+valor_nuevo[3]+"',correo__empleado='"+valor_nuevo[4]+"',rtn__empleado='"+valor_nuevo[5]+"',estado_civil_empleado='"+valor_nuevo[6]+"',genero_empleado='"+valor_nuevo[7]+"',salario_empleado='"+valor_nuevo[8]+"',fecha_nacimiento_empleado='"+valor_nuevo[9]+"',puesto_empleado='"+valor_nuevo[10]+"',contra_empleado='"+valor_nuevo[13]+"',firma_emple='"+valor_nuevo[14]+"' WHERE id_empleado  = '"+indicador+"'");
               // cerrar_conexion();
                return "Se modifico con exito";
            } catch (SQLException ex) {
                Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(tabla.equalsIgnoreCase("agencias"))
        {
            try {
                st.execute("UPDATE "+tabla+" SET nombre_agencias ='"+valor_nuevo[0]+"',departamento_agencias = '"+valor_nuevo[1]+"', municipio_agencias='"+valor_nuevo[2]+"', direccion_agencias= '"+valor_nuevo[3]+"',cordenada_agencias= '"+valor_nuevo[4]+"' WHERE Id_agencias= '"+indicador+"'");
              //   cerrar_conexion();
                return "Se modifico con exito";
            } catch (SQLException ex) {
                Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(tabla.equalsIgnoreCase("peticiones"))
        {
              int v = Integer.parseInt(valor_nuevo[5]);
            try {
                st.execute("UPDATE "+tabla+"  SET emple_envio_peticiones='"+valor_nuevo[0]+"',emple_recibio_peticiones='"+valor_nuevo[1]+"',descripcion_peticion='"+valor_nuevo[2]+"',n_agencia_peticion='"+v+"',nombre_problema='"+valor_nuevo[6]+"' WHERE id_peticiones= '"+indicador+"'");
             //    cerrar_conexion();
                return "Se modifico con exito";
            } catch (SQLException e) {
                  Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if(tabla.equalsIgnoreCase("ingreso_hora_extra"))
        {
            try {
                st.execute("UPDATE "+tabla+"  SET hora_inicio_ingreso_hora_extra='"+valor_nuevo[1]+"',hora_final_ingreso_hora_extra='"+valor_nuevo[2]+"',fecha_inicio_ingreso_hora_extra='"+valor_nuevo[3]+"',fecha_final_ingreso_hora_extra='"+valor_nuevo[4]+"',nombre_empeleado_ingreso_hora_extra='"+valor_nuevo[5]+"',peticion_empleado_ingreso_hora_extra='"+valor_nuevo[6]+"' ,comentario_ingreso_hora_extra='"+valor_nuevo[7]+"',ubicacion_ingreso_hora_extra='"+valor_nuevo[8]+"',fecha__ingreso_hora_extra='"+valor_nuevo[9]+"',hora__ingreso_hora_extra='"+valor_nuevo[10]+"',total_horas_extras_ingreso_horas_extras='"+valor_nuevo[11]+"',estado_ingreso_hora_extra='"+valor_nuevo[12]+"',pago_total_ingreso_hora_extra='"+valor_nuevo[13]+"' WHERE id_ingreso_hora_extra = '"+indicador+"'");
             //    cerrar_conexion();
                return "Se modifico con exito";
            } catch (SQLException e) {
                  Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, e);
            }
        }
         
        return "Error al Modificar";
    }
    public String eliminar_fila(String tabla,String indicador ,String id_indicador)
    {
        con=conexion.conexion();
        try {
            st=(Statement)con.createStatement();
            st.execute("DELETE FROM "+tabla+" WHERE "+id_indicador+"="+indicador+" ");
        } catch (SQLException ex) {
            Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return "Eliminado con exito";    
    }
    public ResultSet fechas_horas_peticiones(String id_p) throws SQLException
    {
      //  System.err.println("Dentro de fechas_horas_peticiones:"+n_emple);
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        rs=st.executeQuery("SELECT fecha_peticion FROM peticiones WHERE peticiones.id_peticiones='"+id_p+"'");
        
        return rs;
    }
     public ResultSet fechas_sysaid(String id) throws SQLException
    {
      //  System.err.println("Dentro de fechas_horas_peticiones:"+n_emple);
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        rs=st.executeQuery("SELECT fecha_hoja_de_servicio FROM hoja_de_servicio WHERE id_hoja_de_servicio ='"+id+"'");
        
         
        return rs;
    }
   
    public void modificar_pro_cate(String tabla,String d[])
    {
        switch(tabla)
        {
            case "problema":   
            {
                try {
                    st.execute("UPDATE "+tabla+" SET nombre_categoria = '"+d[1]+"', nombre_problema ='"+d[2]+"' WHERE id_problema = '"+d[0]+"'");
                } catch (SQLException ex) {
                    Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case "categorias_problema":
            {
                try {
                    st.execute("UPDATE "+tabla+" SET  nombre_categoria ='"+d[1]+"' WHERE id_categoria='"+d[0]+"'");
                } catch (SQLException ex) {
                    Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
         
    }
    public String guarda_un_dato(String tabla,String datos[],String columna[]) {
        con = conexion.conexion();
        try {
            st=(Statement)con.createStatement();
            st.execute("UPDATE "+tabla+" SET "+columna[0]+" = '"+datos[0]+"',"+columna[1]+" = '"+datos[1]+"' WHERE "+columna[2]+" = '"+datos[2]+"'");
            return "Se guardo con exito";
        } catch (SQLException ex) {
            Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return "Error al guardar datos";
    }
    public ResultSet consultar_existencia_id(String tabla, String id,String columna) throws SQLException
    {
         con = conexion.conexion();
         st=(Statement)con.createStatement();
         rs=st.executeQuery("SELECT "+columna+" FROM "+tabla+" WHERE "+columna+" = '"+id+"'" );
         
         return rs;
    }
     public void llenar_tabla_jasper_entrada_salida(ArrayList<String> id,ArrayList<String> estado,ArrayList<String> hora,ArrayList<String> fecha,ArrayList<String> nombre,ArrayList<String> corde)
    {
        String sql;
        for(int x=0; x<id.size();x++)
        {
            try {
                 sql="INSERT INTO temporal_marca VALUES('"+id.get(x)+"','"+estado.get(x)+"','"+hora.get(x)+"','"+fecha.get(x)+"','"+nombre.get(x)+"','"+corde.get(x)+"')";
                st.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
    }
    public void llenar_tabla_jasper_hora_extra(ArrayList<String> id,ArrayList<String> h_ini,ArrayList<String> h_fin,ArrayList<String> f_ini,ArrayList<String> f_fin,ArrayList<String> nom,ArrayList<String> sysaid,ArrayList<String> comen,ArrayList<String> f_ingre,ArrayList<String> h_ingre,ArrayList<String> t_horas,ArrayList<String> estado,ArrayList<String> t_pago)
    {
        String sql;
        for(int x=0;x<id.size();x++)
        {
            try {
                sql="INSERT INTO temporal_horas_extra VALUES('"+id.get(x)+"','"+h_ini.get(x)+"','"+h_fin.get(x)+"','"+f_ini.get(x)+"','"+f_fin.get(x)+"','"+nom.get(x)+"','"+sysaid.get(x)+"','"+comen.get(x)+"','"+f_ingre.get(x)+"','"+h_ingre.get(x)+"','"+t_horas.get(x)+"','"+estado.get(x)+"','"+t_pago.get(x)+"')";
                st.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
    }
    public void llenar_tabla_jasper_peticion(ArrayList<String> id,ArrayList<String> h_ingre,ArrayList<String> f_ingre,ArrayList<String> ingre_por,ArrayList<String> agen,ArrayList<String> emple,ArrayList<String> descri,ArrayList<String> pro,ArrayList<String> estado,ArrayList<String> h_final,ArrayList<String> f_final)
    {
        String sql;
        for(int x=0;x<id.size();x++)
        {
            try {
                sql="INSERT INTO temporal_peti VALUES('"+id.get(x)+"','"+h_ingre.get(x)+"','"+f_ingre.get(x)+"','"+ingre_por.get(x)+"','"+agen.get(x)+"','"+emple.get(x)+"','"+descri.get(x)+"','"+pro.get(x)+"','"+estado.get(x)+"','"+h_final.get(x)+"','"+f_final.get(x)+"')";
                st.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void llenar_tabla_jasper_sysaid(ArrayList<String> id,ArrayList<String> agen,ArrayList<String> id_peti,ArrayList<String> fecha,ArrayList<String> h_ini,ArrayList<String> h_fin,ArrayList<String> titulo,
            ArrayList<String> cate,ArrayList<String> descri,ArrayList<String> trabajo,ArrayList<String> mate,ArrayList<String> emple_res,ArrayList<String> emple_con,ArrayList<String> emple_apoyo1,
            ArrayList<String> emple_apoyo2,ArrayList<String> emple_apoyo3,ArrayList<String> h_sali,ArrayList<String> h_retor,ArrayList<String> sector,ArrayList<String> trans,ArrayList<String> alim,
            ArrayList<String> hospe,ArrayList<String> total_g,ArrayList<String> solu,ArrayList<String> nom_geren)
    {
         con = conexion.conexion();
       
        String sql;
        for(int x=0;x<id.size();x++)
        {
            try {
                sql="INSERT INTO temporal_sysaid VALUES('"+id.get(x)+"','"+agen.get(x)+"','"+id_peti.get(x)+"','"+fecha.get(x)+"','"+h_ini.get(x)+"','"+h_fin.get(x)+"','"+titulo.get(x)+"','"+cate.get(x)+"','"+descri.get(x)+"','"+trabajo.get(x)+"','"+mate.get(x)+"','"+emple_res.get(x)+"'"
                        + ",'"+emple_con.get(x)+"','"+emple_apoyo1.get(x)+"','"+emple_apoyo2.get(x)+"','"+emple_apoyo3.get(x)+"','"+h_sali.get(x)+"','"+h_retor.get(x)+"','"+sector.get(x)+"','"+trans.get(x)+"','"+alim.get(x)+"','"+hospe.get(x)+"','"+total_g.get(x)+"','"+solu.get(x)+"','"+nom_geren.get(x)+"')";
                st.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }
     public void vaciar_tabla(String tabla) throws SQLException
     {
         st.execute(" TRUNCATE TABLE "+tabla+"");
        
     }
     public void guardar_emple_con_firma(String datos[]) throws FileNotFoundException
     {
      // File archivofoto = new File(datos[14]);
       String SSQL = "INSERT INTO empleado (nombre_completo_empleado, identidad_empleado,direccion_empleado, cel_empleado, correo__empleado, rtn__empleado,estado_civil_empleado,genero_empleado,salario_empleado,fecha_nacimiento_empleado,puesto_empleado,depar_trabajo,fecha_de_ingreso_empleado,hora_de_ingreso_empleado,contra_empleado,firma_emple) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

           try {

              /// FileInputStream convertir_imagen = new FileInputStream (archivofoto);
              con = conexion.conexion();

               PreparedStatement psql = con.prepareStatement(SSQL);
           
               for(int x=0; x<datos.length;x++)
               {
                psql.setString(x+1, datos[x]);
                   
               }
             psql.executeUpdate();//// JOptionPane.showMessageDialog(null, psql.executeUpdate()+""); 

               psql.close();

           } catch (SQLException e) {

               JOptionPane.showMessageDialog(null, "Error al intentar almacenar la información:\n"
                                            + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
           }
     }
     public void modificar_emple_firma(String datos[],String indicador) throws FileNotFoundException
     {
          //File archivofoto = new File(datos[14]);
          String SSQL = "UPDATE empleado SET nombre_completo_empleado = ? ,identidad_empleado=?,direccion_empleado= ?"
                  + ",cel_empleado= ? ,correo__empleado= ? ,rtn__empleado= ? ,estado_civil_empleado= ? ,genero_empleado= ? "
                  + ",salario_empleado= ? ,fecha_nacimiento_empleado= ?,puesto_empleado= ? , depar_trabajo= ? ,contra_empleado= ? ,firma_emple= ? WHERE id_empleado  = '"+indicador+"'";
           try {

             //  FileInputStream convertir_imagen = new FileInputStream (archivofoto); 
               con = conexion.conexion();

               PreparedStatement psql = con.prepareStatement(SSQL);
               for(int x=0; x<12;x++)
               {
                psql.setString(x+1, datos[x]);
                
               }
               psql.setString(13, datos[14]);
               psql.setString(14, datos[15]);
              
               psql.executeUpdate(); 

               psql.close();

           } catch (SQLException e) {

               JOptionPane.showMessageDialog(null, "Error al intentar almacenar la información:\n"
                                            + e, "Error en la operación", JOptionPane.ERROR_MESSAGE);
           }
     }
     
    public void cerrar_conexion() throws SQLException
    {
        st.close();
        con.close();
        rs.close();
    } 
    
}
