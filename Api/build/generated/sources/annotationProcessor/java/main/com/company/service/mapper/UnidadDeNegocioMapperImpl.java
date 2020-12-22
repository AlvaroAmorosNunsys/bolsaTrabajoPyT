package com.company.service.mapper;

import com.company.domain.UnidadDeNegocio;
import com.company.service.dto.UnidadDeNegocioDTO;
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
public class UnidadDeNegocioMapperImpl implements UnidadDeNegocioMapper {

    @Override
    public UnidadDeNegocioDTO toDto(UnidadDeNegocio entity) {
        if ( entity == null ) {
            return null;
        }

        UnidadDeNegocioDTO unidadDeNegocioDTO = new UnidadDeNegocioDTO();

        unidadDeNegocioDTO.setId( entity.getId() );
        unidadDeNegocioDTO.setNombre( entity.getNombre() );

        return unidadDeNegocioDTO;
    }

    @Override
    public List<UnidadDeNegocio> toEntity(List<UnidadDeNegocioDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UnidadDeNegocio> list = new ArrayList<UnidadDeNegocio>( dtoList.size() );
        for ( UnidadDeNegocioDTO unidadDeNegocioDTO : dtoList ) {
            list.add( toEntity( unidadDeNegocioDTO ) );
        }

        return list;
    }

    @Override
    public List<UnidadDeNegocioDTO> toDto(List<UnidadDeNegocio> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UnidadDeNegocioDTO> list = new ArrayList<UnidadDeNegocioDTO>( entityList.size() );
        for ( UnidadDeNegocio unidadDeNegocio : entityList ) {
            list.add( toDto( unidadDeNegocio ) );
        }

        return list;
    }

    @Override
    public UnidadDeNegocio toEntity(UnidadDeNegocioDTO unidadDeNegocioDTO) {
        if ( unidadDeNegocioDTO == null ) {
            return null;
        }

        UnidadDeNegocio unidadDeNegocio = new UnidadDeNegocio();

        unidadDeNegocio.setId( unidadDeNegocioDTO.getId() );
        unidadDeNegocio.setNombre( unidadDeNegocioDTO.getNombre() );

        return unidadDeNegocio;
    }
}
