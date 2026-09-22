/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

/**
 *
 * @author CelesteZaldivar
 */
public class Calcular_salario {
    public String no_festiva="no_festiva";
     public String festiva="festiva";
     public double lim_h_=7;
    public Calcular_salario()
    {
    
    }
     public double cal_pago_noctuno(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
            /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
          
          total=calcular_h_nocturnas(datos[2],no_festiva, salario);
          
          return total;
     }
     public double cal_pago_diurno(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
           /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
          if(datos[2]>3)
          {
              double total_diurnas=datos[2]-9;
              double t_diurnas = calcular_h_diurnas(total_diurnas, no_festiva, salario);
              double t_nocturnas = calcular_h_nocturnas(9.0, no_festiva, salario);
              total=t_diurnas+t_nocturnas;
          }else
          {
              total = calcular_h_diurnas(datos[2], no_festiva, salario);
          }
             
          return total;
     }
     public double cal_pago_diurno_nocturno(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
          /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
          String am_h_fin[]=h_f.split(" ");
          if(am_h_fin[1].equalsIgnoreCase("AM"))
          {
              double t_h_noc=datos[1]+5;
              double t_h_diur=datos[2]-t_h_noc;
              double v_noc=calcular_h_nocturnas(t_h_noc,no_festiva,salario);
              double v_diur=calcular_h_diurnas(t_h_diur,no_festiva,salario);
              total=v_noc+v_diur;  
          }else
          {
             double total_h_noc=datos[1]-lim_h_;
             System.err.println(" conerciom horas "+datos[1]+"- 7 ="+total_h_noc);
             double t_h_diurnas=datos[2]-total_h_noc;
             double v_noc=calcular_h_nocturnas(total_h_noc,no_festiva,salario);
             double v_diur=calcular_h_diurnas(t_h_diurnas,no_festiva,salario);
           total=v_noc+v_diur;  
          }
         
          return total;
     }
      public double cal_pago_nocturno_diurno(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
          /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
           String am_h_ini[]=h_i.split(" ");
          if(am_h_ini[1].equalsIgnoreCase("AM"))
          {
              double t_h_noc=5-datos[0];
              double t_h_diu=datos[2]-t_h_noc;
              double v_noc=calcular_h_nocturnas(t_h_noc,no_festiva,salario);
              double v_diur=calcular_h_diurnas(t_h_diu,no_festiva,salario);
              total=v_noc+v_diur; 
          }else
          {
            double total_h_no=12-datos[0];
            double total_h_noc=total_h_no+4;
            System.err.println("12 - "+datos[0]+" = "+total_h_no+" +  4 = "+total_h_noc);
            double t_h_diurnas=datos[2]-total_h_noc;
            double v_noc=calcular_h_nocturnas(total_h_noc,no_festiva,salario);
            double v_diur=calcular_h_diurnas(t_h_diurnas,no_festiva,salario);
            total=v_noc+v_diur; 
          }
           
          return total;
     }
       public double cal_pago_noctuno_festivo(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
          
          total=calcular_h_nocturnas(datos[2],festiva, salario);
         
          return total;
     }
     public double cal_pago_diurno_festivo(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
           if(datos[2]>3)
          {
              double total_diurnas=datos[2]-9;
              double t_diurnas = calcular_h_diurnas(total_diurnas, festiva, salario);
              double t_nocturnas = calcular_h_nocturnas(9.0, festiva, salario);
              total=t_diurnas+t_nocturnas;
          }else
          {
              total = calcular_h_diurnas(datos[2], festiva, salario);
          }
          return total;
     }
     public double cal_pago_diurno_nocturno_festivo_d(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
          /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
          String am_h_fin[]=h_f.split(" ");
          if(am_h_fin[1].equalsIgnoreCase("AM"))
          {
              double t_h_noc=datos[1]+5;
              double t_h_diur=datos[2]-t_h_noc;
              double v_noc=calcular_h_nocturnas(t_h_noc,no_festiva,salario);
              double v_diur=calcular_h_diurnas(t_h_diur,festiva,salario);
              total=v_noc+v_diur;  
          }else
          {
             double total_h_noc=datos[1]-lim_h_;
             System.err.println(" conerciom horas "+datos[1]+"- 7 ="+total_h_noc);
             double t_h_diurnas=datos[2]-total_h_noc;
             double v_noc=calcular_h_nocturnas(total_h_noc,no_festiva,salario);
             double v_diur=calcular_h_diurnas(t_h_diurnas,festiva,salario);
           total=v_noc+v_diur;  
          }
          return total;
     }
     public double cal_pago_diurno_nocturno_festivo_n(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
          /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
          String am_h_fin[]=h_f.split(" ");
          if(am_h_fin[1].equalsIgnoreCase("AM"))
          {
              double t_h_noc=datos[1]+5;
              double t_h_diur=datos[2]-t_h_noc;
              double v_noc=calcular_h_nocturnas(t_h_noc,festiva,salario);
              double v_diur=calcular_h_diurnas(t_h_diur,no_festiva,salario);
              total=v_noc+v_diur;  
          }else
          {
             double total_h_noc=datos[1]-lim_h_;
             System.err.println(" conerciom horas "+datos[1]+"- 7 ="+total_h_noc);
             double t_h_diurnas=datos[2]-total_h_noc;
             double v_noc=calcular_h_nocturnas(total_h_noc,festiva,salario);
             double v_diur=calcular_h_diurnas(t_h_diurnas,no_festiva,salario);
           total=v_noc+v_diur;  
          }
          return total;
     }
      public double cal_pago_nocturno_diurno_festivo(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
           /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
           String am_h_ini[]=h_i.split(" ");
          if(am_h_ini[1].equalsIgnoreCase("AM"))
          {
              double t_h_noc=5-datos[0];
              double t_h_diu=datos[2]-t_h_noc;
              double v_noc=calcular_h_nocturnas(t_h_noc,festiva,salario);
              double v_diur=calcular_h_diurnas(t_h_diu,festiva,salario);
              total=v_noc+v_diur; 
          }else
          {
            double total_h_no=12-datos[0];
            double total_h_noc=total_h_no+4;
            System.err.println("12 - "+datos[0]+" = "+total_h_no+" +  4 = "+total_h_noc);
            double t_h_diurnas=datos[2]-total_h_noc;
            double v_noc=calcular_h_nocturnas(total_h_noc,festiva,salario);
            double v_diur=calcular_h_diurnas(t_h_diurnas,festiva,salario);
            total=v_noc+v_diur; 
          }

          return total;
     }
       public double cal_pago_diurno_nocturno_festivo(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
           /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
          String am_h_fin[]=h_f.split(" ");
          if(am_h_fin[1].equalsIgnoreCase("AM"))
          {
              double t_h_noc=datos[1]+5;
              double t_h_diur=datos[2]-t_h_noc;
              double v_noc=calcular_h_nocturnas(t_h_noc,festiva,salario);
              double v_diur=calcular_h_diurnas(t_h_diur,festiva,salario);
              total=v_noc+v_diur;  
          }else
          {
             double total_h_noc=datos[1]-lim_h_;
             System.err.println(" conerciom horas "+datos[1]+"- 7 ="+total_h_noc);
             double t_h_diurnas=datos[2]-total_h_noc;
             double v_noc=calcular_h_nocturnas(total_h_noc,festiva,salario);
             double v_diur=calcular_h_diurnas(t_h_diurnas,festiva,salario);
           total=v_noc+v_diur;  
          }
          return total;
     }
        public double cal_pago_nocturno_diurno_festivo_d(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
           /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
           String am_h_ini[]=h_i.split(" ");
          if(am_h_ini[1].equalsIgnoreCase("AM"))
          {
              double t_h_noc=5-datos[0];
              double t_h_diu=datos[2]-t_h_noc;
              double v_noc=calcular_h_nocturnas(t_h_noc,no_festiva,salario);
              double v_diur=calcular_h_diurnas(t_h_diu,festiva,salario);
              total=v_noc+v_diur; 
          }else
          {
            double total_h_no=12-datos[0];
            double total_h_noc=total_h_no+4;
            System.err.println("12 - "+datos[0]+" = "+total_h_no+" +  4 = "+total_h_noc);
            double t_h_diurnas=datos[2]-total_h_noc;
            double v_noc=calcular_h_nocturnas(total_h_noc,no_festiva,salario);
            double v_diur=calcular_h_diurnas(t_h_diurnas,festiva,salario);
            total=v_noc+v_diur; 
          }
          return total;
     }
     public double cal_pago_nocturno_diurno_festivo_n(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
           /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
           String am_h_ini[]=h_i.split(" ");
          if(am_h_ini[1].equalsIgnoreCase("AM"))
          {
              double t_h_noc=5-datos[0];
              double t_h_diu=datos[2]-t_h_noc;
              double v_noc=calcular_h_nocturnas(t_h_noc,festiva,salario);
              double v_diur=calcular_h_diurnas(t_h_diu,no_festiva,salario);
              total=v_noc+v_diur; 
          }else
          {
            double total_h_no=12-datos[0];
            double total_h_noc=total_h_no+4;
            System.err.println("12 - "+datos[0]+" = "+total_h_no+" +  4 = "+total_h_noc);
            double t_h_diurnas=datos[2]-total_h_noc;
            double v_noc=calcular_h_nocturnas(total_h_noc,festiva,salario);
            double v_diur=calcular_h_diurnas(t_h_diurnas,no_festiva,salario);
            total=v_noc+v_diur; 
          }
          return total;
     }
      public double cal_pago_diurno_1_diurno_festivo(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
            /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
          if(datos[2]>3)
          {
             
              double ho_noc_ini=7-datos[0];
              double total_diurnas=datos[2]-9;
              double h_diur_fin=total_diurnas-ho_noc_ini;
              double t_horas_noc_ini=calcular_h_diurnas(ho_noc_ini, festiva, salario);
              double t_diurnas_fin = calcular_h_diurnas(h_diur_fin, no_festiva, salario);
              double t_nocturnas = calcular_h_nocturnas(9.0, no_festiva, salario);
              total=t_horas_noc_ini+t_diurnas_fin+t_nocturnas;
          }
         
             
          return total;
     }
      public double cal_pago_diurno_diurno_2_festivo(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
             /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
          if(datos[2]>3)
          {
              double ho_noc_ini=7-datos[0];
              double total_diurnas=datos[2]-9;
              double h_diur_fin=total_diurnas-ho_noc_ini;
              double t_horas_noc_ini=calcular_h_diurnas(ho_noc_ini, no_festiva, salario);
              double t_diurnas_fin = calcular_h_diurnas(h_diur_fin, festiva, salario);
              double t_nocturnas = calcular_h_nocturnas(9.0, no_festiva, salario);
              total=t_horas_noc_ini+t_diurnas_fin+t_nocturnas;
          }
          return total;
     }
       public double cal_pago_nocturno_1_nocturno_festivo(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
             /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
          double ho_noc_ini=datos[0]-7;
          double ho_noc_fin=datos[2]-ho_noc_ini;
          double total_ho_noc_ini=calcular_h_nocturnas(ho_noc_ini, festiva, salario);
          double total_ho_noc_fin=calcular_h_nocturnas(ho_noc_fin, no_festiva, salario);
          total=total_ho_noc_ini+total_ho_noc_fin;
          return total;
     }
      public double cal_pago_nocturno_nocturno_2_festivo(String salario,String total_horas,String h_i,String h_f)
     {
          double total=0;
             /////0=h_i  1=h_f  2=h_T
              /////0=h_i  1=h_f  2=h_T
          double datos[]=new double[3];
          datos=convertir_datos(total_horas,h_i,h_f);
          double ho_noc_ini=datos[0]-7;
          double ho_noc_fin=datos[2]-ho_noc_ini;
          double total_ho_noc_ini=calcular_h_nocturnas(ho_noc_ini, no_festiva, salario);
          double total_ho_noc_fin=calcular_h_nocturnas(ho_noc_fin, festiva, salario);
          total=total_ho_noc_ini+total_ho_noc_fin;
          return total;
     }

    private double calcular_h_nocturnas(double hora_mas_min_fin_nocturna, String f,String salario) {
        double v_noc=0;
        double sala=Double.parseDouble(salario);
        double sal_dias=sala/30;
        double sal_horas=sal_dias/8;
        double sal_por_hora_extra=0;
        double sal_duplo=0; 
        if(f.equalsIgnoreCase("no_festiva"))
        {
          sal_por_hora_extra=sal_horas*1.50;
          double paga_total_horas=hora_mas_min_fin_nocturna*sal_por_hora_extra;
           v_noc=Math.round(paga_total_horas*100.0)/100.0;
        }
        else
        {
          sal_duplo=sal_horas*2;
          sal_por_hora_extra=sal_duplo*1.50;
          double paga_total_horas=hora_mas_min_fin_nocturna*sal_por_hora_extra;
          v_noc=Math.round(paga_total_horas*100.0)/100.0;
        }
        System.err.println("Nocturna total pago:"+v_noc+"  "+"Total horas: "+hora_mas_min_fin_nocturna+"  Salario por hora nocturno: "+sal_por_hora_extra);
        return v_noc;
    }

    private double calcular_h_diurnas(double t_h_diurnas, String f,String salario) {
       double v_diur=0;
       double sala=Double.parseDouble(salario);
       double sal_dias=sala/30;
       double sal_horas=sal_dias/8;
       double sal_por_hora_extra=0;
       double sal_duplo=0;
       if(f.equalsIgnoreCase("no_festiva"))
        {
            sal_por_hora_extra=sal_horas*1.25;
            double paga_total_horas=t_h_diurnas*sal_por_hora_extra;
            v_diur=Math.round(paga_total_horas*100.0)/100.0;
        }
        else
        {
             sal_duplo=sal_horas*2;
             sal_por_hora_extra=sal_duplo*1.25;
            double paga_total_horas=t_h_diurnas*sal_por_hora_extra;
            v_diur=Math.round(paga_total_horas*100.0)/100.0;
        }
        System.err.println("Diurna total pago:"+v_diur+"  "+"Total horas: "+t_h_diurnas+"  Salario por hora diurna: "+sal_por_hora_extra);
        return v_diur;
    }
    public double[] convertir_datos(String total_horas,String h_i,String h_f)
    {
        double valores[]=new double[3];
        String separa_h_ini[]=h_i.split(":");
          String h_ini_=separa_h_ini[0];
          String separa_min_ini[]=separa_h_ini[1].split(" ");
          String min_ini_=separa_min_ini[0];
          
          String separa_h_fin[]=h_f.split(":");
          String h_fin_=separa_h_fin[0];
          String separa_min_fin[]=separa_h_fin[1].split(" ");
          String min_fin_=separa_min_fin[0];
          
           String total_horas_traba=total_horas;
           String [] hora_min=total_horas_traba.split(":");
           double t_horas=Double.parseDouble(hora_min[0]);
           double t_min=Double.parseDouble(hora_min[1]);
           double conver_min=t_min/60;
           double t_hora_min=t_horas+conver_min;
          System.err.println("conversion hora total T "+t_horas+"+"+conver_min+"   "+t_min);
           double conver_h_ini=Double.parseDouble(h_ini_);
           double conver_h_fin=Double.parseDouble(h_fin_);
           double conver_min_ini=Double.parseDouble(min_ini_);
           double conver_min_fin=Double.parseDouble(min_fin_);
           
           double _con_min_ini=conver_min_ini/60;
           double hora_mas_min_ini_=conver_h_ini+_con_min_ini;
            double _con_min_fin=conver_min_fin/60;
           double hora_mas_min_fin_=conver_h_fin+_con_min_fin;
           
           valores[0]=hora_mas_min_ini_;
           valores[1]=hora_mas_min_fin_;
           valores[2]=t_hora_min;
           
           return valores;
        
    }
    
}

