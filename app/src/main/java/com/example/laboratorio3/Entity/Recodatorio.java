package com.example.laboratorio3.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;
import java.util.function.Consumer;

@Entity (tableName = "Recordatorio")
public class Recodatorio {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="recordatorioID")
    @NonNull
   private int recordatorioID;
    @ColumnInfo(name="fecha")
   private Date fecha;
    @ColumnInfo(name="hora")
    private String hora;
    @ColumnInfo(name="texto")
    String texto;

    public Recodatorio(){}

    public  Recodatorio(Consumer<Recodatorio> r){
        r.accept(this);
    }

    public int getRecordatorioID() {
        return recordatorioID;
    }

    public void setRecordatorioID(int recordatorioID) {
        this.recordatorioID = recordatorioID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recodatorio that = (Recodatorio) o;
        return recordatorioID == that.recordatorioID && Objects.equals(fecha, that.fecha) && Objects.equals(hora, that.hora) && Objects.equals(texto, that.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordatorioID, fecha, hora, texto);
    }
}
