package com.example.proyecto_hora_extra.clases;

public class objeto_lista_perfil {
   public String id;
   public String estado;
   public String fecha;
   public objeto_lista_perfil(String id,String estado,String fecha)
   {
       this.estado=estado;
       this.id=id;
       this.fecha=fecha;
   }
    public String getestado() {
        return estado;
    }
    public String getFecha() {
        return fecha;
    }
    public String getId() {
        return id;
    }
    public void setestado(String estado) {
        this.estado = estado;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setId(String id) {
        this.id = id;
    }
}
