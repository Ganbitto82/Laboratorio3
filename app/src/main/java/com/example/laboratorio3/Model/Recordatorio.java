package com.example.laboratorio3.Model;

import java.util.Date;

public class Recordatorio {
    private String texto;
    private Date fecha;

    public Recordatorio(){}

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
