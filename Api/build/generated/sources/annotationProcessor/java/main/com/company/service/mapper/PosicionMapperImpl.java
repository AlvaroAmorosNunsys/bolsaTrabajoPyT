package com.company.service.mapper;

import com.company.domain.EstadoPosicion;
import com.company.domain.Posicion;
import com.company.domain.TipoJornada;
import com.company.domain.UnidadDeNegocio;
import com.company.service.dto.PosicionDTO;
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
public class PosicionMapperImpl implements PosicionMapper {

    @Autowired
    private EstadoPosicionMapper estadoPosicionMapper;
    @Autowired
    private TipoJornadaMapper tipoJornadaMapper;
    @Autowired
    private UnidadDeNegocioMapper unidadDeNegocioMapper;

    @Override
    public List<Posicion> toEntity(List<PosicionDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Posicion> list = new ArrayList<Posicion>( dtoList.size() );
        for ( PosicionDTO posicionDTO : dtoList ) {
            list.add( toEntity( posicionDTO ) );
        }

        return list;
    }

    @Override
    public List<PosicionDTO> toDto(List<Posicion> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PosicionDTO> list = new ArrayList<PosicionDTO>( entityList.size() );
        for ( Posicion posicion : entityList ) {
            list.add( toDto( posicion ) );
        }

        return list;
    }

    @Override
    public PosicionDTO toDto(Posicion posicion) {
        if ( posicion == null ) {
            return null;
        }

        PosicionDTO posicionDTO = new PosicionDTO();

        posicionDTO.setUnidadDeNegocioNombre( posicionUnidadDeNegocioNombre( posicion ) );
        posicionDTO.setUnidadDeNegocioId( posicionUnidadDeNegocioId( posicion ) );
        posicionDTO.setTipoJornadaId( posicionTipoJornadaId( posicion ) );
        posicionDTO.setTipoJornadaNombre( posicionTipoJornadaNombre( posicion ) );
        posicionDTO.setEstadoPosicionNombre( posicionEstadoPosicionNombre( posicion ) );
        posicionDTO.setEstadoPosicionId( posicionEstadoPosicionId( posicion ) );
        posicionDTO.setId( posicion.getId() );
        posicionDTO.setTitulo( posicion.getTitulo() );
        posicionDTO.setDescripcion( posicion.getDescripcion() );
        posicionDTO.setNumeroPuestos( posicion.getNumeroPuestos() );
        posicionDTO.setSalarioMinimo( posicion.getSalarioMinimo() );
        posicionDTO.setSalarioMaximo( posicion.getSalarioMaximo() );
        posicionDTO.setFechaAlta( posicion.getFechaAlta() );
        posicionDTO.setFechaNecesidad( posicion.getFechaNecesidad() );

        return posicionDTO;
    }

    @Override
    public Posicion toEntity(PosicionDTO posicionDTO) {
        if ( posicionDTO == null ) {
            return null;
        }

        Posicion posicion = new Posicion();

        posicion.setUnidadDeNegocio( unidadDeNegocioMapper.fromId( posicionDTO.getUnidadDeNegocioId() ) );
        posicion.setEstadoPosicion( estadoPosicionMapper.fromId( posicionDTO.getEstadoPosicionId() ) );
        posicion.setTipoJornada( tipoJornadaMapper.fromId( posicionDTO.getTipoJornadaId() ) );
        posicion.setId( posicionDTO.getId() );
        posicion.setTitulo( posicionDTO.getTitulo() );
        posicion.setDescripcion( posicionDTO.getDescripcion() );
        posicion.setNumeroPuestos( posicionDTO.getNumeroPuestos() );
        posicion.setSalarioMinimo( posicionDTO.getSalarioMinimo() );
        posicion.setSalarioMaximo( posicionDTO.getSalarioMaximo() );
        posicion.setFechaAlta( posicionDTO.getFechaAlta() );
        posicion.setFechaNecesidad( posicionDTO.getFechaNecesidad() );

        return posicion;
    }

    private String posicionUnidadDeNegocioNombre(Posicion posicion) {
        if ( posicion == null ) {
            return null;
        }
        UnidadDeNegocio unidadDeNegocio = posicion.getUnidadDeNegocio();
        if ( unidadDeNegocio == null ) {
            return null;
        }
        String nombre = unidadDeNegocio.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private Long posicionUnidadDeNegocioId(Posicion posicion) {
        if ( posicion == null ) {
            return null;
        }
        UnidadDeNegocio unidadDeNegocio = posicion.getUnidadDeNegocio();
        if ( unidadDeNegocio == null ) {
            return null;
        }
        Long id = unidadDeNegocio.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long posicionTipoJornadaId(Posicion posicion) {
        if ( posicion == null ) {
            return null;
        }
        TipoJornada tipoJornada = posicion.getTipoJornada();
        if ( tipoJornada == null ) {
            return null;
        }
        Long id = tipoJornada.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String posicionTipoJornadaNombre(Posicion posicion) {
        if ( posicion == null ) {
            return null;
        }
        TipoJornada tipoJornada = posicion.getTipoJornada();
        if ( tipoJornada == null ) {
            return null;
        }
        String nombre = tipoJornada.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private String posicionEstadoPosicionNombre(Posicion posicion) {
        if ( posicion == null ) {
            return null;
        }
        EstadoPosicion estadoPosicion = posicion.getEstadoPosicion();
        if ( estadoPosicion == null ) {
            return null;
        }
        String nombre = estadoPosicion.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private Long posicionEstadoPosicionId(Posicion posicion) {
        if ( posicion == null ) {
            return null;
        }
        EstadoPosicion estadoPosicion = posicion.getEstadoPosicion();
        if ( estadoPosicion == null ) {
            return null;
        }
        Long id = estadoPosicion.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
