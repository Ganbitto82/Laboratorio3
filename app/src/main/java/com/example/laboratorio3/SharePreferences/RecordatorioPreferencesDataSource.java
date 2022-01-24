package com.example.laboratorio3.SharePreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Entity.Recordatorio;

public class RecordatorioPreferencesDataSource implements RecordatorioDataSource {
    private final SharedPreferences sharedPreferences;

    public RecordatorioPreferencesDataSource(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void guardarRecordatorio(Recordatorio recordatorio, GuardarRecordatorioCallback callback) {

        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FECHA", String.valueOf(recordatorio.getFecha()));
            editor.putString("TEXTO", String.valueOf(recordatorio.getTexto()));
            editor.commit();
            callback.resultado(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback) {

    }
}