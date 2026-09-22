
package Clases_reporte;

/**
 *
 * @author CelesteZaldivar
 */
public class Verificar_items_hora_extra {
    public Verificar_items_hora_extra()
    {
     
    }
    public String verificar(String items[])
    {
        int contador_iguales=0;
        int contador_diferentes=0;
        String sql="";
        for (int i = 0; i < items.length; i++) {
            if(items[i].equalsIgnoreCase("Todos"))
             { 
                 contador_iguales+=1;
                 switch(i)
                 { 
                   case 0:
                       if(!items[1].equalsIgnoreCase("Todos")&&!items[2].equalsIgnoreCase("Todos"))
                       {
                           sql="SELECT * FROM ingreso_hora_extra WHERE fecha__ingreso_hora_extra='"+items[1]+"' AND estado_ingreso_hora_extra='"+items[2]+"'";
                       }else
                       {
                           sql="SELECT * FROM ingreso_hora_extra WHERE fecha__ingreso_hora_extra='"+items[1]+"' OR estado_ingreso_hora_extra='"+items[2]+"'";
                       }
                       break;
                   case 1:
                       if(!items[0].equalsIgnoreCase("Todos")&&!items[2].equalsIgnoreCase("Todos"))
                       {
                           sql="SELECT * FROM ingreso_hora_extra WHERE nombre_empeleado_ingreso_hora_extra='"+items[0]+"' AND estado_ingreso_hora_extra='"+items[2]+"'";
                       }else
                       {
                           sql="SELECT * FROM ingreso_hora_extra WHERE nombre_empeleado_ingreso_hora_extra='"+items[0]+"' OR estado_ingreso_hora_extra='"+items[2]+"'";
                       }
                       break;
                   case 2:
                       if(!items[0].equalsIgnoreCase("Todos")&&!items[1].equalsIgnoreCase("Todos"))
                       {
                           sql="SELECT * FROM ingreso_hora_extra WHERE nombre_empeleado_ingreso_hora_extra='"+items[0]+"' AND fecha__ingreso_hora_extra='"+items[1]+"'";
                       }else
                       {
                           sql="SELECT * FROM ingreso_hora_extra WHERE nombre_empeleado_ingreso_hora_extra='"+items[0]+"' OR fecha__ingreso_hora_extra='"+items[1]+"'";
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
           sql="SELECT * FROM  ingreso_hora_extra";
       }
       if(contador_diferentes==3)
       {
           sql="SELECT * FROM  ingreso_hora_extra WHERE nombre_empeleado_ingreso_hora_extra='"+items[0]+"' AND fecha__ingreso_hora_extra='"+items[1]+"' AND estado_ingreso_hora_extra='"+items[2]+"'";
       }    
       return sql;
        }
}
