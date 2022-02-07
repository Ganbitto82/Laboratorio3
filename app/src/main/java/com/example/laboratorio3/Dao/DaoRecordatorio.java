package com.example.laboratorio3.Dao;



import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.laboratorio3.Mapper.RecordatorioDto;


import java.util.List;
@Dao
public interface DaoRecordatorio {

    @Query("SELECT * FROM recordatorio")
    List<RecordatorioDto> getAll();

    @Insert
    void insertAll(RecordatorioDto... reco);

    @Insert(onConflict = IGNORE)
    Long insert(RecordatorioDto reco);

    @Delete
    void delete(RecordatorioDto reco);
}
