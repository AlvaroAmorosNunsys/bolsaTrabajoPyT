package com.company.service.mapper;


import com.company.domain.*;
import com.company.service.dto.PosicionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Posicion} and its DTO {@link PosicionDTO}.
 */
@Mapper(componentModel = "spring", uses = {EstadoPosicionMapper.class, TipoJornadaMapper.class, UnidadDeNegocioMapper.class})
public interface PosicionMapper extends EntityMapper<PosicionDTO, Posicion> {

    @Mapping(source = "estadoPosicion.id", target = "estadoPosicionId")
    @Mapping(source = "estadoPosicion.nombre", target = "estadoPosicionNombre")
    @Mapping(source = "tipoJornada.id", target = "tipoJornadaId")
    @Mapping(source = "tipoJornada.nombre", target = "tipoJornadaNombre")
    @Mapping(source = "unidadDeNegocio.id", target = "unidadDeNegocioId")
    @Mapping(source = "unidadDeNegocio.nombre", target = "unidadDeNegocioNombre")
    PosicionDTO toDto(Posicion posicion);

    @Mapping(target = "candidaturas", ignore = true)
    @Mapping(target = "removeCandidatura", ignore = true)
    @Mapping(target = "historialCandidaturas", ignore = true)
    @Mapping(target = "removeHistorialCandidatura", ignore = true)
    @Mapping(target = "historialPosicions", ignore = true)
    @Mapping(target = "removeHistorialPosicion", ignore = true)
    @Mapping(source = "estadoPosicionId", target = "estadoPosicion")
    @Mapping(source = "tipoJornadaId", target = "tipoJornada")
    @Mapping(source = "unidadDeNegocioId", target = "unidadDeNegocio")
    Posicion toEntity(PosicionDTO posicionDTO);

    default Posicion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Posicion posicion = new Posicion();
        posicion.setId(id);
        return posicion;
    }
}
