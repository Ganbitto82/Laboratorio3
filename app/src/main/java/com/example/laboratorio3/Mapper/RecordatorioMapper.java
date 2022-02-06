package com.example.laboratorio3.Mapper;

import com.example.laboratorio3.Model.Recordatorio;

import java.util.ArrayList;

import java.util.List;

public class RecordatorioMapper implements DomainMapper<RecordatorioDto, Recordatorio> {

    public RecordatorioMapper(){}
    @Override
    public Recordatorio fromDtoTODomainModel(RecordatorioDto dto) {
        Recordatorio recordatorio =new Recordatorio();
        recordatorio.setTexto(dto.getTexto());
        recordatorio.setFecha(dto.getFecha());

        return recordatorio;
    }

    @Override
    public RecordatorioDto fromDomainModelTODto(Recordatorio r) {
        RecordatorioDto recordatorioDto=new RecordatorioDto();
        recordatorioDto.setTexto(r.getTexto());
        recordatorioDto.setFecha(r.getFecha());
        return recordatorioDto;
    }

    public List<RecordatorioDto> fromListDomainModelToListDto(List <Recordatorio> lr){
        List <RecordatorioDto> listaRecordatorioDto=new ArrayList<RecordatorioDto>();
        for(Recordatorio r: lr ){
            RecordatorioDto recordatorioDto= new RecordatorioDto();
            recordatorioDto= fromDomainModelTODto(r);
            listaRecordatorioDto.add(recordatorioDto);
        }

        return listaRecordatorioDto;
    }
    public List<Recordatorio> fromListDtoToListDomainModel(List <RecordatorioDto> lDto){
        List<Recordatorio> listaRecordatorio =new ArrayList<Recordatorio>();
        for(RecordatorioDto dto: lDto ){
            Recordatorio recordatorio= new Recordatorio();
            recordatorio= fromDtoTODomainModel(dto);
            listaRecordatorio.add(recordatorio);
        }



        return listaRecordatorio;
    }




}
