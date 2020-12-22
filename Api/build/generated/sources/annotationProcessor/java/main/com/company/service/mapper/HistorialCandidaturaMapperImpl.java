package com.company.service.mapper;

import com.company.domain.Candidatura;
import com.company.domain.EstadoCandidatura;
import com.company.domain.HistorialCandidatura;
import com.company.domain.Posicion;
import com.company.service.dto.HistorialCandidaturaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-21T13:34:01+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class HistorialCandidaturaMapperImpl implements HistorialCandidaturaMapper {

    @Autowired
    private PosicionMapper posicionMapper;
    @Autowired
    private CandidaturaMapper candidaturaMapper;
    @Autowired
    private EstadoCandidaturaMapper estadoCandidaturaMapper;

    @Override
    public List<HistorialCandidatura> toEntity(List<HistorialCandidaturaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<HistorialCandidatura> list = new ArrayList<HistorialCandidatura>( dtoList.size() );
        for ( HistorialCandidaturaDTO historialCandidaturaDTO : dtoList ) {
            list.add( toEntity( historialCandidaturaDTO ) );
        }

        return list;
    }

    @Override
    public List<HistorialCandidaturaDTO> toDto(List<HistorialCandidatura> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<HistorialCandidaturaDTO> list = new ArrayList<HistorialCandidaturaDTO>( entityList.size() );
        for ( HistorialCandidatura historialCandidatura : entityList ) {
            list.add( toDto( historialCandidatura ) );
        }

        return list;
    }

    @Override
    public HistorialCandidaturaDTO toDto(HistorialCandidatura historialCandidatura) {
        if ( historialCandidatura == null ) {
            return null;
        }

        HistorialCandidaturaDTO historialCandidaturaDTO = new HistorialCandidaturaDTO();

        historialCandidaturaDTO.setEstadoCandidaturaNombre( historialCandidaturaEstadoCandidaturaNombre( historialCandidatura ) );
        historialCandidaturaDTO.setPosicionTitulo( historialCandidaturaPosicionTitulo( historialCandidatura ) );
        historialCandidaturaDTO.setPosicionId( historialCandidaturaPosicionId( historialCandidatura ) );
        historialCandidaturaDTO.setCandidaturaId( historialCandidaturaCandidaturaId( historialCandidatura ) );
        historialCandidaturaDTO.setEstadoCandidaturaId( historialCandidaturaEstadoCandidaturaId( historialCandidatura ) );
        historialCandidaturaDTO.setId( historialCandidatura.getId() );
        historialCandidaturaDTO.setFechaCambio( historialCandidatura.getFechaCambio() );
        historialCandidaturaDTO.setPorDefecto( historialCandidatura.isPorDefecto() );
        historialCandidaturaDTO.setFechaModificacion( historialCandidatura.getFechaModificacion() );
        historialCandidaturaDTO.setNombreEditor( historialCandidatura.getNombreEditor() );

        return historialCandidaturaDTO;
    }

    @Override
    public HistorialCandidatura toEntity(HistorialCandidaturaDTO historialCandidaturaDTO) {
        if ( historialCandidaturaDTO == null ) {
            return null;
        }

        HistorialCandidatura historialCandidatura = new HistorialCandidatura();

        historialCandidatura.setPosicion( posicionMapper.fromId( historialCandidaturaDTO.getPosicionId() ) );
        historialCandidatura.setCandidatura( candidaturaMapper.fromId( historialCandidaturaDTO.getCandidaturaId() ) );
        historialCandidatura.setEstadoCandidatura( estadoCandidaturaMapper.fromId( historialCandidaturaDTO.getEstadoCandidaturaId() ) );
        historialCandidatura.setId( historialCandidaturaDTO.getId() );
        historialCandidatura.setFechaCambio( historialCandidaturaDTO.getFechaCambio() );
        historialCandidatura.setPorDefecto( historialCandidaturaDTO.isPorDefecto() );
        historialCandidatura.setFechaModificacion( historialCandidaturaDTO.getFechaModificacion() );
        historialCandidatura.setNombreEditor( historialCandidaturaDTO.getNombreEditor() );

        return historialCandidatura;
    }

    private String historialCandidaturaEstadoCandidaturaNombre(HistorialCandidatura historialCandidatura) {
        if ( historialCandidatura == null ) {
            return null;
        }
        EstadoCandidatura estadoCandidatura = historialCandidatura.getEstadoCandidatura();
        if ( estadoCandidatura == null ) {
            return null;
        }
        String nombre = estadoCandidatura.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private String historialCandidaturaPosicionTitulo(HistorialCandidatura historialCandidatura) {
        if ( historialCandidatura == null ) {
            return null;
        }
        Posicion posicion = historialCandidatura.getPosicion();
        if ( posicion == null ) {
            return null;
        }
        String titulo = posicion.getTitulo();
        if ( titulo == null ) {
            return null;
        }
        return titulo;
    }

    private Long historialCandidaturaPosicionId(HistorialCandidatura historialCandidatura) {
        if ( historialCandidatura == null ) {
            return null;
        }
        Posicion posicion = historialCandidatura.getPosicion();
        if ( posicion == null ) {
            return null;
        }
        Long id = posicion.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long historialCandidaturaCandidaturaId(HistorialCandidatura historialCandidatura) {
        if ( historialCandidatura == null ) {
            return null;
        }
        Candidatura candidatura = historialCandidatura.getCandidatura();
        if ( candidatura == null ) {
            return null;
        }
        Long id = candidatura.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long historialCandidaturaEstadoCandidaturaId(HistorialCandidatura historialCandidatura) {
        if ( historialCandidatura == null ) {
            return null;
        }
        EstadoCandidatura estadoCandidatura = historialCandidatura.getEstadoCandidatura();
        if ( estadoCandidatura == null ) {
            return null;
        }
        Long id = estadoCandidatura.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
