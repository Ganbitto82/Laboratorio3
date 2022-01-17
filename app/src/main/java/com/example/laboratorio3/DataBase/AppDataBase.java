package com.example.laboratorio3.DataBase;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class AppDataBase extends RoomDatabase {
    public static AppDataBase instance;
    //public abstract ItemDao itemDao();

    public static AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDataBase.class, "checkList")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();


        }
        return instance;
    }


}
