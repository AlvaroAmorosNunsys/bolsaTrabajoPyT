package com.company.service.mapper;

import com.company.domain.EstadoPosicion;
import com.company.service.dto.EstadoPosicionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-21T13:34:00+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class EstadoPosicionMapperImpl implements EstadoPosicionMapper {

    @Override
    public EstadoPosicionDTO toDto(EstadoPosicion entity) {
        if ( entity == null ) {
            return null;
        }

        EstadoPosicionDTO estadoPosicionDTO = new EstadoPosicionDTO();

        estadoPosicionDTO.setId( entity.getId() );
        estadoPosicionDTO.setNombre( entity.getNombre() );

        return estadoPosicionDTO;
    }

    @Override
    public List<EstadoPosicion> toEntity(List<EstadoPosicionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<EstadoPosicion> list = new ArrayList<EstadoPosicion>( dtoList.size() );
        for ( EstadoPosicionDTO estadoPosicionDTO : dtoList ) {
            list.add( toEntity( estadoPosicionDTO ) );
        }

        return list;
    }

    @Override
    public List<EstadoPosicionDTO> toDto(List<EstadoPosicion> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EstadoPosicionDTO> list = new ArrayList<EstadoPosicionDTO>( entityList.size() );
        for ( EstadoPosicion estadoPosicion : entityList ) {
            list.add( toDto( estadoPosicion ) );
        }

        return list;
    }

    @Override
    public EstadoPosicion toEntity(EstadoPosicionDTO estadoPosicionDTO) {
        if ( estadoPosicionDTO == null ) {
            return null;
        }

        EstadoPosicion estadoPosicion = new EstadoPosicion();

        estadoPosicion.setId( estadoPosicionDTO.getId() );
        estadoPosicion.setNombre( estadoPosicionDTO.getNombre() );

        return estadoPosicion;
    }
}
