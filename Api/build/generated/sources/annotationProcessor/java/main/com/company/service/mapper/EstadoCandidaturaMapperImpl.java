package com.company.service.mapper;

import com.company.domain.EstadoCandidatura;
import com.company.service.dto.EstadoCandidaturaDTO;
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
public class EstadoCandidaturaMapperImpl implements EstadoCandidaturaMapper {

    @Override
    public EstadoCandidaturaDTO toDto(EstadoCandidatura entity) {
        if ( entity == null ) {
            return null;
        }

        EstadoCandidaturaDTO estadoCandidaturaDTO = new EstadoCandidaturaDTO();

        estadoCandidaturaDTO.setId( entity.getId() );
        estadoCandidaturaDTO.setNombre( entity.getNombre() );

        return estadoCandidaturaDTO;
    }

    @Override
    public List<EstadoCandidatura> toEntity(List<EstadoCandidaturaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<EstadoCandidatura> list = new ArrayList<EstadoCandidatura>( dtoList.size() );
        for ( EstadoCandidaturaDTO estadoCandidaturaDTO : dtoList ) {
            list.add( toEntity( estadoCandidaturaDTO ) );
        }

        return list;
    }

    @Override
    public List<EstadoCandidaturaDTO> toDto(List<EstadoCandidatura> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<EstadoCandidaturaDTO> list = new ArrayList<EstadoCandidaturaDTO>( entityList.size() );
        for ( EstadoCandidatura estadoCandidatura : entityList ) {
            list.add( toDto( estadoCandidatura ) );
        }

        return list;
    }

    @Override
    public EstadoCandidatura toEntity(EstadoCandidaturaDTO estadoCandidaturaDTO) {
        if ( estadoCandidaturaDTO == null ) {
            return null;
        }

        EstadoCandidatura estadoCandidatura = new EstadoCandidatura();

        estadoCandidatura.setId( estadoCandidaturaDTO.getId() );
        estadoCandidatura.setNombre( estadoCandidaturaDTO.getNombre() );

        return estadoCandidatura;
    }
}
