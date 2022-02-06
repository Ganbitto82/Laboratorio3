package com.example.laboratorio3.Model;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Objects;
import java.util.function.Consumer;


public class Recordatorio {

    private String texto;
    private Date fecha;


    public Recordatorio(){}

    public Recordatorio(Consumer<Recordatorio> r){
        r.accept(this);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recordatorio that = (Recordatorio) o;
        return Objects.equals(texto, that.texto) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(texto, fecha);
    }

    public  Recordatorio(JSONObject json) {
        try {
            this.texto = json.getString("mensaje");
            this.fecha = new Date(json.getLong("fecha"));
        }catch (Exception e){

        }
    }
    public JSONObject toJSON() throws JSONException {
        JSONObject resultado= new JSONObject();
        resultado.put("mensaje",this.texto);
        resultado.put("fecha",this.fecha.getTime());
        return resultado;
    }
}
