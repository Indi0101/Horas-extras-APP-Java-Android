
package Clases_reporte;

/**
 *
 * @author CelesteZaldivar
 */
public class Verificar_items_entrada_salida {
    
    public Verificar_items_entrada_salida()
    {}
    public String verificar(String items[],int id_emp)
    {
       String sql="";
       int contador_diferentes=0;
       int contador_iguales=0;
       for(int x=0;x<items.length;x++)
       {
           if(items[x].equalsIgnoreCase("Todo"))
           {
               contador_iguales+=1;
               switch(x)
               { 
                   case 0:
                       if(!items[1].equalsIgnoreCase("Todo")&&!items[2].equalsIgnoreCase("Todo"))
                       {
                           sql="SELECT * FROM  marcar_de_entrada_salida WHERE fecha_marca_de_entrada_salida='"+items[1]+"' AND estado_marca_de_entrada_salida='"+items[2]+"'";
                       }else
                       {
                           sql="SELECT * FROM  marcar_de_entrada_salida WHERE fecha_marca_de_entrada_salida='"+items[1]+"' OR estado_marca_de_entrada_salida='"+items[2]+"'";
                       }
                       break;
                   case 1:
                       if(!items[0].equalsIgnoreCase("Todo")&&!items[2].equalsIgnoreCase("Todo"))
                       {
                           sql="SELECT * FROM  marcar_de_entrada_salida WHERE id_empleado_marca_de_entrada_salida='"+id_emp+"' AND estado_marca_de_entrada_salida='"+items[2]+"'";
                       }else
                       {
                           sql="SELECT * FROM  marcar_de_entrada_salida WHERE id_empleado_marca_de_entrada_salida='"+id_emp+"' OR estado_marca_de_entrada_salida='"+items[2]+"'";
                       }
                       break;
                   case 2:
                       if(!items[0].equalsIgnoreCase("Todo")&&!items[1].equalsIgnoreCase("Todo"))
                       {
                           sql="SELECT * FROM  marcar_de_entrada_salida WHERE id_empleado_marca_de_entrada_salida='"+id_emp+"' AND fecha_marca_de_entrada_salida='"+items[1]+"'";
                       }else
                       {
                           sql="SELECT * FROM  marcar_de_entrada_salida WHERE id_empleado_marca_de_entrada_salida='"+id_emp+"' OR fecha_marca_de_entrada_salida='"+items[1]+"'";
                       }
                       break;
               }
           }else
            {
             contador_diferentes+=1;
            }
       }
       if(contador_iguales==3)
       {
           sql="SELECT * FROM  marcar_de_entrada_salida";
       }
       if(contador_diferentes==3)
       {
           sql="SELECT * FROM  marcar_de_entrada_salida WHERE id_empleado_marca_de_entrada_salida='"+id_emp+"' AND fecha_marca_de_entrada_salida='"+items[1]+"' AND estado_marca_de_entrada_salida='"+items[2]+"'";
       }
       return sql;
    }
   
}
