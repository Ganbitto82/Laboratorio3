package com.example.laboratorio3.Repository;

import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Entity.Recordatorio;
import com.example.laboratorio3.SharePreferences.RecordatorioPreferencesDataSource;

public class RecordatorioRepository {

    private final RecordatorioDataSource datasource;
    private RecordatorioDataSource.GuardarRecordatorioCallback callback;

    public RecordatorioRepository(final RecordatorioDataSource datasource) {
        this.datasource = datasource;
    }

    public void save (Recordatorio r , RecordatorioPreferencesDataSource preferences){

        try {
            preferences.guardarRecordatorio( r,callback = new RecordatorioDataSource.GuardarRecordatorioCallback() {
                @Override
                public void resultado(boolean exito) {
                    if(exito)
                        datasource.guardarRecordatorio(r,callback);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
