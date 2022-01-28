package com.example.laboratorio3.Repository;

import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Entity.Recordatorio;
import com.example.laboratorio3.SharePreferences.RecordatorioPreferencesDataSource;

public class RecordatorioRepository {

    private final RecordatorioDataSource datasource;
   // private RecordatorioDataSource.GuardarRecordatorioCallback callback;

    public RecordatorioRepository(final RecordatorioDataSource datasource) {
        this.datasource = datasource;
    }


    public void saveRecordatorio(Recordatorio reco,RecordatorioDataSource.GuardarRecordatorioCallback callback){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                datasource.guardarRecordatorio(reco,callback);
            }
        };
        r.run();
    }
    public void getRecordatorios(RecordatorioDataSource.RecuperarRecordatorioCallback callback){

        Runnable r = new Runnable() {
            @Override
            public void run() {
                datasource.recuperarRecordatorios(callback);
            }
        };
        r.run();
    }


}
