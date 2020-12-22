package com.company.service.mapper;


import com.company.domain.*;
import com.company.service.dto.HistorialPosicionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HistorialPosicion} and its DTO {@link HistorialPosicionDTO}.
 */
@Mapper(componentModel = "spring", uses = {EstadoPosicionMapper.class, PosicionMapper.class})
public interface HistorialPosicionMapper extends EntityMapper<HistorialPosicionDTO, HistorialPosicion> {

    @Mapping(source = "estadoPosicion.id", target = "estadoPosicionId")
    @Mapping(source = "estadoPosicion.nombre", target = "estadoPosicionNombre")
    @Mapping(source = "posicion.id", target = "posicionId")
    @Mapping(source = "posicion.titulo", target = "posicionTitulo")
    HistorialPosicionDTO toDto(HistorialPosicion historialPosicion);

    @Mapping(source = "estadoPosicionId", target = "estadoPosicion")
    @Mapping(source = "posicionId", target = "posicion")
    HistorialPosicion toEntity(HistorialPosicionDTO historialPosicionDTO);

    default HistorialPosicion fromId(Long id) {
        if (id == null) {
            return null;
        }
        HistorialPosicion historialPosicion = new HistorialPosicion();
        historialPosicion.setId(id);
        return historialPosicion;
    }
}
