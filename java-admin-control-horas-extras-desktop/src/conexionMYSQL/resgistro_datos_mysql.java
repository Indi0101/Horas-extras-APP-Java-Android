/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionMYSQL;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Statement;


/**
 *
 * @author CelesteZaldivar
 */
public class resgistro_datos_mysql {
    public  Statement st;
    public  ResultSet rs;
    private final conexionSQL conexion=new conexionSQL();
    public  Connection con;
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
         if(tabla.equalsIgnoreCase("puesto_departamento")==true)
         {
            String d;
            d = datos[0];
            st.execute("INSERT INTO puesto_departamento VALUES(null,'"+d+"')");
            
            return  "se guardo exitozamente";
         }
         if(tabla.equalsIgnoreCase("ingreso_hora_extra")==true)
         {
            st.execute("INSERT INTO "+tabla+" VALUES(null,'"+datos[0]+"','"+datos[1]+"','"+datos[2]+"','"+datos[3]+"','"+datos[4]+"','"+datos[5]+"','"+datos[6]+"','"+datos[7]+"','"+datos[8]+"','"+datos[9]+"','"+datos[10]+"','"+datos[11]+"')");
         
            return  "se guardo exitozamente";
         }
        
           return  "ERROR AL GUARDAR";  
    }
    public ResultSet traer_hora_extra_user(String tabla,String user)
    {
       con=conexion.conexion();
        try {
            st=(Statement)con.createStatement();
             rs=st.executeQuery("SELECT * FROM "+tabla+" WHERE nombre_empeleado_ingreso_hora_extra = '"+user+"'");
             
        } catch (SQLException ex) {
            Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        //JOptionPane.showMessageDialog(null, ""+rs);
        return rs;
    }
    public ResultSet traer_datos_todos(String tabla) throws SQLException
    {
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        rs=st.executeQuery("SELECT * FROM "+tabla+"");
       
        //JOptionPane.showMessageDialog(null, ""+rs);
        return rs;
    }
    public ResultSet traer_sysaid(String user)
    {
        con=conexion.conexion();
        try {
            st=(Statement)con.createStatement();
            rs=st.executeQuery("SELECT * FROM hoja_de_servicio WHERE id_emple_responsable_hoja_de_servicio='"+user+"' OR id_emple_apoyo1_hoja_de_servicio='"+user+"' OR id_emple_apoyo2_hoja_de_servicio='"+user+"' OR id_emple_apoyo3_hoja_de_servicio='"+user+"'");
        
        } catch (SQLException ex) {
            Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
              return rs;
    }
    public ResultSet traerDato(String tabla, String indicador, String valor_buscar, String valor_de_condicion) throws SQLException
    {
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        rs=st.executeQuery("SELECT "+valor_buscar+" FROM "+tabla+" WHERE "+valor_de_condicion+" = '"+indicador+"'");
        System.err.println("exito en consulta");
      
        return rs;
    }
    public ResultSet traer_nombre_puesto()
    {
        con=conexion.conexion();
        try {
            st=(Statement)con.createStatement(); 
            rs=st.executeQuery("SELECT nombre_completo_empleado,puesto_empleado FROM empleado");
            
        } catch (SQLException ex) {
            Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public String modificar_datos(String tabla, String valor_nuevo[],String indicador) throws SQLException
    {
       con = conexion.conexion();
       st=(Statement)con.createStatement();
      if(tabla.equalsIgnoreCase("ingreso_hora_extra"))
        {
            try {
               st.execute("UPDATE "+tabla+"  SET nombre_empeleado_ingreso_hora_extra='"+valor_nuevo[0]+"',id_sysaid='"+valor_nuevo[1]+"', comentario_ingreso_hora_extra='"+valor_nuevo[2]+"',fecha_inicio_ingreso_hora_extra='"+valor_nuevo[3]+"',fecha_final_ingreso_hora_extra='"+valor_nuevo[4]+"',hora_inicio_ingreso_hora_extra='"+valor_nuevo[5]+"',hora_final_ingreso_hora_extra='"+valor_nuevo[6]+"',total_horas_extras_ingreso_horas_extras='"+valor_nuevo[7]+"'  WHERE id_ingreso_hora_extra = '"+indicador+"'");
               for(String d: valor_nuevo)
            {
                System.out.println(d);
            }
              
                return "Se modifico con exito";
            } catch (SQLException e) {
                  Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, e);
            }
        }
   
      return "Error al modificar";
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
    public void cerrar_conexion() throws SQLException
    {
        st.close();
        con.close();
        rs.close();
    }         
    public ResultSet verificar_login(String tabla, String n, String c )
    {
        con=conexion.conexion();
        try {
            st=(Statement)con.createStatement();
            rs=st.executeQuery("SELECT id_empleado FROM "+tabla+" WHERE nombre_completo_empleado='"+n+"' AND contra_empleado='"+c+"'");
       
        } catch (SQLException ex) {
            Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public ResultSet verificar_existencia(String n, String c )
    {
        con=conexion.conexion();
        try {
            st=(Statement)con.createStatement();
            rs=st.executeQuery("SELECT EXISTS(SELECT nombre_empeleado_ingreso_hora_extra,id_sysaid FROM ingreso_hora_extra WHERE nombre_empeleado_ingreso_hora_extra='"+n+"' AND id_sysaid='"+c+"')");
        } catch (SQLException ex) {
            Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public ResultSet verificar_existencia_modificar(String d[] )
    {
        con=conexion.conexion();
        try {
            st=(Statement)con.createStatement();
            rs=st.executeQuery("SELECT EXISTS(SELECT * FROM ingreso_hora_extra"
                    + " WHERE (nombre_empeleado_ingreso_hora_extra='"+d[0]+"'"
                    + "AND peticion_empleado_ingreso_hora_extra='"+d[1]+"'"
                    + "AND comentario_ingreso_hora_extra='"+d[2]+"'"
                    + "AND fecha_inicio_ingreso_hora_extra='"+d[3]+"'"
                    + "AND fecha_final_ingreso_hora_extra='"+d[4]+"'"
                    + "AND hora_inicio_ingreso_hora_extra='"+d[5]+"'"
                    + "AND hora_final_ingreso_hora_extra='"+d[6]+"')"
                    + "OR (nombre_empeleado_ingreso_hora_extra='"+d[0]+"'"
                    + "AND peticion_empleado_ingreso_hora_extra='"+d[1]+"'))");
            System.err.println("Todo bien");
            
        } catch (SQLException ex) {
            Logger.getLogger(resgistro_datos_mysql.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Todo Mal error consulta existencia modificar:"+ex);
        }
        return rs;
    }
     public ResultSet traer_valores_todos_condicion(String tabla,String filtro,String colum) throws SQLException
    {
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        
        rs=st.executeQuery("SELECT * FROM "+tabla+" WHERE "+colum+" = '"+filtro+"'");
       
        //JOptionPane.showMessageDialog(null, ""+rs);
        return rs;
    }
     public ResultSet sql(String consulta) throws SQLException
    {
        con=conexion.conexion();
        st=(Statement)con.createStatement();
        
        rs=st.executeQuery(consulta);
       
        //JOptionPane.showMessageDialog(null, ""+rs);
        return rs;
    }
   
    
}
