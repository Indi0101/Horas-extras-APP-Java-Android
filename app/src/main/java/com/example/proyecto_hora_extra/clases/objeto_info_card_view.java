package com.example.proyecto_hora_extra.clases;

public class objeto_info_card_view {
    public String estado="";
    public String agencia="";
    public String peticion="";
    public String descripcion="";
    public String direccion="";
    public String fecha;
    public String hora;
    public String cordenadas[];
    public String n_emple_r="";
    public String problema="";
    public String fecha_final="";
    public String hora_final="";
    public objeto_info_card_view(String agencia,String estado,String peticion,String descripcion,String direccion,String fecha,String hora,String cordenadas[],String n_emple_r,String problema,String fecha_final,String hora_final)
    {
        this.estado=estado;
        this.agencia=agencia;
        this.peticion=peticion;
        this.descripcion=descripcion;
        this.direccion=direccion;
        this.fecha=fecha;
        this.hora=hora;
        this.cordenadas=cordenadas;
        this.n_emple_r=n_emple_r;
        this.problema=problema;
        this.fecha_final=fecha_final;
        this.hora_final=hora_final;
    }
    public objeto_info_card_view()
    {}

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String[] getCordenadas() {
        return cordenadas;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getEstado() {
        return estado;
    }

    public String getPeticion() {
        return peticion;
    }

    public String getN_emple_r() {
        return n_emple_r;
    }

    public String getProblema() {
        return problema;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPeticion(String peticion) {
        this.peticion = peticion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setCordenadas(String[] cordenadas) {
        this.cordenadas = cordenadas;
    }

    public void setN_emple_r(String n_emple_r) {
        this.n_emple_r = n_emple_r;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }
}
