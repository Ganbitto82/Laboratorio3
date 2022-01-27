package com.example.laboratorio3.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Entity.Recordatorio;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecordatorioPreferencesDataSource implements RecordatorioDataSource {
    private final SharedPreferences sharedPreferences;

    public RecordatorioPreferencesDataSource(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void guardarRecordatorio(Recordatorio recordatorio, GuardarRecordatorioCallback callback) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String key = String.valueOf(recordatorio.hashCode());
        try {
            editor.putString(key,recordatorio.toJSON().toString());
            editor.commit();
        }catch (Exception e){

        }
        Boolean result= sharedPreferences.contains(key);
        callback.resultado(result);
    }

    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback) {
        List<Recordatorio> listaRecordatorio= new ArrayList<>();
        Set<String> listaPreference= sharedPreferences.getAll().keySet();
        boolean exito= false;
        if(!listaPreference.isEmpty()){
            exito= true;

            Recordatorio nuevo;
            JSONObject nuvJson;
            for (String i: listaPreference ) {
                try {
                    nuvJson= new JSONObject(sharedPreferences.getString(i,null));
                    nuevo= new Recordatorio(nuvJson);
                    listaRecordatorio.add(nuevo);
                }catch (Exception e){}
            }
        }
        callback.resultado(exito,listaRecordatorio);



    }
}