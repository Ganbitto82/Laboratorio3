package com.example.laboratorio3.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Mapper.RecordatorioDto;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecordatorioPreferencesDataSource implements RecordatorioDataSource  {
    private final SharedPreferences sharedPreferences;

    public RecordatorioPreferencesDataSource(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void guardarRecordatorio(RecordatorioDto recordatorioDto, GuardarRecordatorioCallback callback) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String key = String.valueOf(recordatorioDto.hashCode());
        try {
            editor.putString(key,recordatorioDto.toJSON().toString());
            editor.commit();
        }catch (Exception e){

        }
        Boolean result= sharedPreferences.contains(key);
        callback.resultado(result);
    }

    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback) {
        List<RecordatorioDto> listaRecordatorioDto= new ArrayList<RecordatorioDto>();
        Set<String> listaPreference= sharedPreferences.getAll().keySet();
        boolean exito= false;
        if(!listaPreference.isEmpty()){
            exito= true;
            RecordatorioDto nuevo;
            JSONObject nuvJson;
            for (String i: listaPreference ) {
                try {
                    nuvJson= new JSONObject(sharedPreferences.getString(i,null));
                    nuevo= new RecordatorioDto(nuvJson);
                    listaRecordatorioDto.add(nuevo);
                }catch (Exception e){}
            }
        }
        callback.resultado(exito,listaRecordatorioDto);



    }
}