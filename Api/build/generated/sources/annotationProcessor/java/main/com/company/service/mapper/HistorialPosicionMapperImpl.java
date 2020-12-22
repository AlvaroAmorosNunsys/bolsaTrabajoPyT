package com.company.service.mapper;

import com.company.domain.EstadoPosicion;
import com.company.domain.HistorialPosicion;
import com.company.domain.Posicion;
import com.company.service.dto.HistorialPosicionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-21T13:34:00+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class HistorialPosicionMapperImpl implements HistorialPosicionMapper {

    @Autowired
    private EstadoPosicionMapper estadoPosicionMapper;
    @Autowired
    private PosicionMapper posicionMapper;

    @Override
    public List<HistorialPosicion> toEntity(List<HistorialPosicionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<HistorialPosicion> list = new ArrayList<HistorialPosicion>( dtoList.size() );
        for ( HistorialPosicionDTO historialPosicionDTO : dtoList ) {
            list.add( toEntity( historialPosicionDTO ) );
        }

        return list;
    }

    @Override
    public List<HistorialPosicionDTO> toDto(List<HistorialPosicion> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<HistorialPosicionDTO> list = new ArrayList<HistorialPosicionDTO>( entityList.size() );
        for ( HistorialPosicion historialPosicion : entityList ) {
            list.add( toDto( historialPosicion ) );
        }

        return list;
    }

    @Override
    public HistorialPosicionDTO toDto(HistorialPosicion historialPosicion) {
        if ( historialPosicion == null ) {
            return null;
        }

        HistorialPosicionDTO historialPosicionDTO = new HistorialPosicionDTO();

        historialPosicionDTO.setEstadoPosicionNombre( historialPosicionEstadoPosicionNombre( historialPosicion ) );
        historialPosicionDTO.setPosicionTitulo( historialPosicionPosicionTitulo( historialPosicion ) );
        historialPosicionDTO.setEstadoPosicionId( historialPosicionEstadoPosicionId( historialPosicion ) );
        historialPosicionDTO.setPosicionId( historialPosicionPosicionId( historialPosicion ) );
        historialPosicionDTO.setId( historialPosicion.getId() );
        historialPosicionDTO.setFechaCambio( historialPosicion.getFechaCambio() );
        historialPosicionDTO.setPorDefecto( historialPosicion.isPorDefecto() );
        historialPosicionDTO.setFechaModificacion( historialPosicion.getFechaModificacion() );
        historialPosicionDTO.setNombreEditor( historialPosicion.getNombreEditor() );

        return historialPosicionDTO;
    }

    @Override
    public HistorialPosicion toEntity(HistorialPosicionDTO historialPosicionDTO) {
        if ( historialPosicionDTO == null ) {
            return null;
        }

        HistorialPosicion historialPosicion = new HistorialPosicion();

        historialPosicion.setPosicion( posicionMapper.fromId( historialPosicionDTO.getPosicionId() ) );
        historialPosicion.setEstadoPosicion( estadoPosicionMapper.fromId( historialPosicionDTO.getEstadoPosicionId() ) );
        historialPosicion.setId( historialPosicionDTO.getId() );
        historialPosicion.setFechaCambio( historialPosicionDTO.getFechaCambio() );
        historialPosicion.setPorDefecto( historialPosicionDTO.isPorDefecto() );
        historialPosicion.setFechaModificacion( historialPosicionDTO.getFechaModificacion() );
        historialPosicion.setNombreEditor( historialPosicionDTO.getNombreEditor() );

        return historialPosicion;
    }

    private String historialPosicionEstadoPosicionNombre(HistorialPosicion historialPosicion) {
        if ( historialPosicion == null ) {
            return null;
        }
        EstadoPosicion estadoPosicion = historialPosicion.getEstadoPosicion();
        if ( estadoPosicion == null ) {
            return null;
        }
        String nombre = estadoPosicion.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private String historialPosicionPosicionTitulo(HistorialPosicion historialPosicion) {
        if ( historialPosicion == null ) {
            return null;
        }
        Posicion posicion = historialPosicion.getPosicion();
        if ( posicion == null ) {
            return null;
        }
        String titulo = posicion.getTitulo();
        if ( titulo == null ) {
            return null;
        }
        return titulo;
    }

    private Long historialPosicionEstadoPosicionId(HistorialPosicion historialPosicion) {
        if ( historialPosicion == null ) {
            return null;
        }
        EstadoPosicion estadoPosicion = historialPosicion.getEstadoPosicion();
        if ( estadoPosicion == null ) {
            return null;
        }
        Long id = estadoPosicion.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long historialPosicionPosicionId(HistorialPosicion historialPosicion) {
        if ( historialPosicion == null ) {
            return null;
        }
        Posicion posicion = historialPosicion.getPosicion();
        if ( posicion == null ) {
            return null;
        }
        Long id = posicion.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
