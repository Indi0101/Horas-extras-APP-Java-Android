/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_reporte;

/**
 *
 * @author CelesteZaldivar
 */
public class Verificar_items_sysaid {
     public Verificar_items_sysaid()
    {
     
    }
    public String verificar(String items)
    {
        String sql="";
        if(!items.equalsIgnoreCase("Todos"))
        {
           sql="SELECT * FROM hoja_de_servicio WHERE id_emple_responsable_hoja_de_servicio='"+items+"' OR id_emple_apoyo1_hoja_de_servicio ='"+items+"' OR id_emple_apoyo2_hoja_de_servicio ='"+items+"' OR id_emple_apoyo3_hoja_de_servicio ='"+items+"'";
        }else
        {
            sql="SELECT * FROM hoja_de_servicio ";
       }
       return sql;
        }
    
}
