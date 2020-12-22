package com.company.service.mapper;

import com.company.domain.Fuente;
import com.company.service.dto.FuenteDTO;
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
public class FuenteMapperImpl implements FuenteMapper {

    @Override
    public Fuente toEntity(FuenteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Fuente fuente = new Fuente();

        fuente.setId( dto.getId() );
        fuente.setNombre( dto.getNombre() );

        return fuente;
    }

    @Override
    public FuenteDTO toDto(Fuente entity) {
        if ( entity == null ) {
            return null;
        }

        FuenteDTO fuenteDTO = new FuenteDTO();

        fuenteDTO.setId( entity.getId() );
        fuenteDTO.setNombre( entity.getNombre() );

        return fuenteDTO;
    }

    @Override
    public List<Fuente> toEntity(List<FuenteDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Fuente> list = new ArrayList<Fuente>( dtoList.size() );
        for ( FuenteDTO fuenteDTO : dtoList ) {
            list.add( toEntity( fuenteDTO ) );
        }

        return list;
    }

    @Override
    public List<FuenteDTO> toDto(List<Fuente> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<FuenteDTO> list = new ArrayList<FuenteDTO>( entityList.size() );
        for ( Fuente fuente : entityList ) {
            list.add( toDto( fuente ) );
        }

        return list;
    }
}
