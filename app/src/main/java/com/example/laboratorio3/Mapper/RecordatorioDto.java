package com.example.laboratorio3.Mapper;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.laboratorio3.Conversor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
@Entity (tableName = "recordatorio")
public class RecordatorioDto {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private Integer id;
    private String texto;
    @TypeConverters({Conversor.class})
    private Date fecha;

    public RecordatorioDto(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public  RecordatorioDto(JSONObject json) {
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
