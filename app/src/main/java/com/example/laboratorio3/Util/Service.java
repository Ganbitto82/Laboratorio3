package com.example.laboratorio3.Util;

import com.example.laboratorio3.Mapper.RecordatorioDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Service {
    @GET("recordatorio/")
    Call<List<RecordatorioDto>> obtenerRecordatorios();
    @POST("recordatorio/")
    Call<RecordatorioDto> guardarRecordatorio(@Body RecordatorioDto p);
}
