package com.example.laboratorio3.DataSource;

import com.example.laboratorio3.Entity.Recordatorio;

import java.util.List;

public interface RecordatorioDataSource {
    interface GuardarRecordatorioCallback {
        void resultado(final boolean exito);
    }

    interface RecuperarRecordatorioCallback {
        void resultado(final boolean exito, final List<Recordatorio> recordatorios);
    }

    void guardarRecordatorio(final Recordatorio recordatorio, final GuardarRecordatorioCallback callback);
    void recuperarRecordatorios(final RecuperarRecordatorioCallback callback);


}
