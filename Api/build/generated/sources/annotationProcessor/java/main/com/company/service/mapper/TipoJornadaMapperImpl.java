package com.company.service.mapper;

import com.company.domain.TipoJornada;
import com.company.service.dto.TipoJornadaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-21T13:34:01+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class TipoJornadaMapperImpl implements TipoJornadaMapper {

    @Override
    public TipoJornada toEntity(TipoJornadaDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TipoJornada tipoJornada = new TipoJornada();

        tipoJornada.setId( dto.getId() );
        tipoJornada.setNombre( dto.getNombre() );

        return tipoJornada;
    }

    @Override
    public TipoJornadaDTO toDto(TipoJornada entity) {
        if ( entity == null ) {
            return null;
        }

        TipoJornadaDTO tipoJornadaDTO = new TipoJornadaDTO();

        tipoJornadaDTO.setId( entity.getId() );
        tipoJornadaDTO.setNombre( entity.getNombre() );

        return tipoJornadaDTO;
    }

    @Override
    public List<TipoJornada> toEntity(List<TipoJornadaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TipoJornada> list = new ArrayList<TipoJornada>( dtoList.size() );
        for ( TipoJornadaDTO tipoJornadaDTO : dtoList ) {
            list.add( toEntity( tipoJornadaDTO ) );
        }

        return list;
    }

    @Override
    public List<TipoJornadaDTO> toDto(List<TipoJornada> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TipoJornadaDTO> list = new ArrayList<TipoJornadaDTO>( entityList.size() );
        for ( TipoJornada tipoJornada : entityList ) {
            list.add( toDto( tipoJornada ) );
        }

        return list;
    }
}
