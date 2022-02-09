package com.example.laboratorio3.Retrofit;

import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Mapper.RecordatorioDto;
import com.example.laboratorio3.Util.MyRetrofit;
import com.example.laboratorio3.Util.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordatorioRetrofitDataSource implements RecordatorioDataSource {
    private Service service;

    public RecordatorioRetrofitDataSource(String user ,String pass){
        service= MyRetrofit.createService(Service.class,user,pass);

    }
    @Override
    public void guardarRecordatorio(RecordatorioDto recordatorioDto, GuardarRecordatorioCallback callback) {
        Call<RecordatorioDto> call = service.guardarRecordatorio(recordatorioDto);
        call.enqueue(new Callback<RecordatorioDto>() {
            @Override
            public void onResponse(Call<RecordatorioDto> c, Response<RecordatorioDto> response) {
                if(response.isSuccessful())
                    callback.resultado(true);
                else
                    callback.resultado(false);
            }

            @Override
            public void onFailure(Call<RecordatorioDto> c, Throwable t) {
                callback.resultado(false);
            }
        });
    }

    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback) {
     Call <List<RecordatorioDto>> call= service.obtenerRecordatorios();
     call.enqueue(new Callback<List<RecordatorioDto>>() {
         @Override
         public void onResponse(Call<List<RecordatorioDto>> c, Response<List<RecordatorioDto>> response) {
             if(response.isSuccessful())
                 callback.resultado(true, response.body());
             else
                 callback.resultado(false,new ArrayList<RecordatorioDto>());
         }

         @Override
         public void onFailure(Call<List<RecordatorioDto>> c, Throwable t) {
             callback.resultado(false,new ArrayList<RecordatorioDto>());
         }
     });
    }
}
