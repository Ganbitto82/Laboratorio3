package com.example.laboratorio3.Util;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.laboratorio3.Dao.DaoRecordatorio;
import com.example.laboratorio3.Mapper.RecordatorioDto;

@Database(entities = {RecordatorioDto.class}, version = 1)
public abstract class MyRoomDb extends RoomDatabase {
    public abstract DaoRecordatorio recordarorioDao();
    public static MyRoomDb INSTANCE;

    public static  MyRoomDb getInstance(Context ctx){
        if(INSTANCE==null) {
            INSTANCE = Room.databaseBuilder(ctx,MyRoomDb.class,"Db-Recordatorio")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }
}
