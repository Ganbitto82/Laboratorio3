package com.example.laboratorio3.Room;

import android.content.Context;

import androidx.room.OnConflictStrategy;

import com.example.laboratorio3.Dao.DaoRecordatorio;
import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Mapper.RecordatorioDto;
import com.example.laboratorio3.Util.MyRoomDb;

import java.util.List;

public class RecordatorioRoomDataSource  implements RecordatorioDataSource {
    private DaoRecordatorio recordatorioDao;
    private MyRoomDb db;


    public RecordatorioRoomDataSource(Context ctx){
        db= MyRoomDb.getInstance(ctx);
    };

    @Override
    public void guardarRecordatorio(RecordatorioDto recordatorioDto, GuardarRecordatorioCallback callback){

                long filaInsert = db.recordarorioDao().insert(recordatorioDto);
                boolean result=false;
                if(filaInsert != OnConflictStrategy.IGNORE || filaInsert!= OnConflictStrategy.FAIL|| filaInsert != OnConflictStrategy.ABORT){
                    result=true;
                }
                callback.resultado(result);
            }





    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback){
        List<RecordatorioDto> resultado = db.recordarorioDao().getAll();
        boolean result= false;
        if(!resultado.isEmpty() || resultado!= null){
            result=true;
        }
        callback.resultado(result,resultado);
    }

}
