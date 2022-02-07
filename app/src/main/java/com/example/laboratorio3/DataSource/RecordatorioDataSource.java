package com.example.laboratorio3.DataSource;

import com.example.laboratorio3.Mapper.RecordatorioDto;

import java.util.List;

public interface RecordatorioDataSource {
    interface GuardarRecordatorioCallback {
        void resultado(final boolean exito);
    }

    interface RecuperarRecordatorioCallback {
        void resultado(final boolean exito, final List<RecordatorioDto> recordatorios);
    }

    void guardarRecordatorio(final RecordatorioDto recordatorio, final GuardarRecordatorioCallback callback);
    void recuperarRecordatorios(final RecuperarRecordatorioCallback callback);


}
