package com.example.laboratorio3.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.laboratorio3.Dao.DaoRecordatorio;
import com.example.laboratorio3.DataSource.RecordatorioDataSource;
import com.example.laboratorio3.Mapper.RecordatorioDto;

import java.util.List;

@Database(entities = {RecordatorioDto.class}, version = 1)

public abstract  class RecordatorioRoomDataSource extends RoomDatabase implements RecordatorioDataSource {
    public abstract DaoRecordatorio recordarorioDao();

    private static  RecordatorioRoomDataSource INSTANCE;

    public RecordatorioRoomDataSource(){};

    public static RecordatorioRoomDataSource getInstance(Context ctx){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(ctx, RecordatorioRoomDataSource.class, "DB-Recordatorio")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  INSTANCE;
    }

    @Override
    public void guardarRecordatorio(RecordatorioDto recordatorioDto, GuardarRecordatorioCallback callback){

        Runnable r = new Runnable() {
            @Override
            public void run() {
                long rowId = INSTANCE.recordarorioDao().insert(recordatorioDto);
                boolean result=false;
                if(rowId != OnConflictStrategy.IGNORE || rowId != OnConflictStrategy.FAIL|| rowId != OnConflictStrategy.ABORT){
                    result=true;
                }
                callback.resultado(result);
            }
        };
        r.run();
    }

    @Override
    public void recuperarRecordatorios(RecuperarRecordatorioCallback callback){
        List<RecordatorioDto> resultado = INSTANCE.recordarorioDao().getAll();
        boolean result= false;
        if(!resultado.isEmpty() || resultado!= null){
            result=true;
        }
        callback.resultado(result,resultado);
    }

}
