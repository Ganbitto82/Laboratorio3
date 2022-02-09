package com.example.laboratorio3.Util;

import androidx.room.TypeConverter;

import java.util.Date;

public class Conversor {
    @TypeConverter
    public static Date toFecha(Long fecha){
        return fecha == null ? null: new Date(fecha);
    }

    @TypeConverter
    public static Long fromFecha(Date fecha){
        return fecha == null ? null :fecha.getTime();
    }
}
