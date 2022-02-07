package com.example.laboratorio3.Repository;

import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Mapper.RecordatorioDto;
import com.example.laboratorio3.Mapper.RecordatorioMapper;
import com.example.laboratorio3.Model.Recordatorio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecordatorioRepository {

    private final RecordatorioDataSource datasource;
   // private RecordatorioDataSource.GuardarRecordatorioCallback callback;

    public RecordatorioRepository(final RecordatorioDataSource datasource) {
        this.datasource = datasource;
    }


    public boolean saveRecordatorio(Recordatorio reco){
        final Boolean[] resultado = new Boolean[1];
        Runnable r = new Runnable() {
            @Override
            public void run() {
                RecordatorioMapper rm = new RecordatorioMapper();
                RecordatorioDto dto=rm.fromDomainModelTODto(reco);
                datasource.guardarRecordatorio(dto, new RecordatorioDataSource.GuardarRecordatorioCallback() {
                    @Override
                    public void resultado(boolean exito) {
                        resultado[0] = new Boolean(exito);
                    }
                });
            }

        };
        r.run();
        return resultado[0];
    }
    public List<Recordatorio> getRecordatorios(){
        final List<Recordatorio>[] recordatorios = new List[]{new ArrayList<Recordatorio>()};
        RecordatorioMapper rm = new RecordatorioMapper();
        Runnable r = new Runnable() {
            @Override
            public void run() {

                datasource.recuperarRecordatorios(new RecordatorioDataSource.RecuperarRecordatorioCallback() {
                    @Override
                    public void resultado(boolean exito, List<RecordatorioDto> recordatoriosDto) {
                        if(exito){
                            //  rm.fromListDtoToListDomainModel(recordatoriosDto);
                            //Collections.copy(recordatorios,rm.fromListDtoToListDomainModel(recordatoriosDto));
                            recordatorios[0] = rm.fromListDtoToListDomainModel(recordatoriosDto).stream().collect(Collectors.toList());
                        }
                    }
                });
            }
        };
        r.run();
        return recordatorios[0];
    }


}
