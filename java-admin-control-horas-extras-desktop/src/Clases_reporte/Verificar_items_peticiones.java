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
public class Verificar_items_peticiones {
    public Verificar_items_peticiones()
    {}
    public String verificar(String items[],int id_emple)
    {
        String sql="";
        int contador_diferentes=0;
        int contador_iguales=0;
        for(int x=0;x<items.length;x++)
       {
           if(items[x].equalsIgnoreCase("Todos"))
           {
               contador_iguales+=1;
               switch(x)
               { 
                   case 0:
                       if(!items[1].equalsIgnoreCase("Todos")&&!items[2].equalsIgnoreCase("Todos"))
                       {
                         sql=consulta_0(0,items,id_emple); 
                       }else
                       {
                         sql=consulta_0(1,items,id_emple); 
                       }
                       break;
                   case 1:
                       if(!items[0].equalsIgnoreCase("Todos")&&!items[2].equalsIgnoreCase("Todos"))
                       {
                           int id=Integer.parseInt(items[0]);
                          sql=consulta_1(0, items, id);
                       }else
                       {
                          sql=consulta_1(1, items, 0);
                       }
                       break;
                   case 2:
                        
                       if(!items[0].equalsIgnoreCase("Todos")&&!items[1].equalsIgnoreCase("Todos"))
                       {
                           int id2=Integer.parseInt(items[0]);
                          sql=consulta_2(0,items,id2, id_emple);
                       }else
                       {
                          sql=consulta_2(1,items,0, id_emple);
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
           sql="SELECT * FROM peticiones";
       }
       if(contador_diferentes==3)
       {
           int id=Integer.parseInt(items[0]);
           System.out.println("contador_diferentes==3");
           sql="SELECT * FROM peticiones WHERE emple_recibio_peticiones='"+id_emple+"' AND id_peticiones ="+id+" AND estado_peticion='"+items[2]+"'";
       }

        return sql;
    }
    public String consulta_0(int i,String items[],int id_emple)
    {
        String sql="";
        switch(i)
        {case 0:
            sql="SELECT * FROM peticiones WHERE emple_recibio_peticiones='"+id_emple+"' AND estado_peticion='"+items[2]+"'";
            break;
        case 1:
            System.out.println("consulta 0");
            sql="SELECT * FROM peticiones WHERE emple_recibio_peticiones='"+id_emple+"' OR estado_peticion='"+items[2]+"'";
            break;
        }
        return sql;
    }
    public String  consulta_1(int i,String items[],int id_peti)
    {
        String sql="";
        switch(i)
        {case 0:
            
            sql="SELECT * FROM peticiones WHERE id_peticiones ="+id_peti+" AND estado_peticion='"+items[2]+"'";
            break;
        case 1:
            System.out.println("consulta 2");
            sql="SELECT * FROM peticiones WHERE id_peticiones ='"+items[0]+"' OR estado_peticion='"+items[2]+"'";
            break;
        }
        return sql;
    }
    public String consulta_2(int i,String items[],int id_peti,int id_emple)
    {
        String sql="";
        switch(i)
        {case 0:
            sql="SELECT * FROM peticiones WHERE id_peticiones ="+id_peti+" AND emple_recibio_peticiones='"+id_emple+"'";
            break;
        case 1:
            System.out.println("consulta 2");
            sql="SELECT * FROM peticiones WHERE id_peticiones ='"+items[0]+"' OR emple_recibio_peticiones='"+id_emple+"'";
            break;
        }
        return sql;
    }
}
