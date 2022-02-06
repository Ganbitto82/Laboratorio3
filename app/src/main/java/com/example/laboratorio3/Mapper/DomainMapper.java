package com.example.laboratorio3.Mapper;

import com.example.laboratorio3.Model.Recordatorio;

public interface DomainMapper <DTO,DomainModel>{
    DomainModel fromDtoTODomainModel(RecordatorioDto DTO);
    DTO fromDomainModelTODto (Recordatorio DomainModel);

}
