package com.company.service.mapper;


import com.company.domain.*;
import com.company.service.dto.EstadoPosicionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstadoPosicion} and its DTO {@link EstadoPosicionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstadoPosicionMapper extends EntityMapper<EstadoPosicionDTO, EstadoPosicion> {


    @Mapping(target = "historialPosicions", ignore = true)
    @Mapping(target = "removeHistorialPosicion", ignore = true)
    EstadoPosicion toEntity(EstadoPosicionDTO estadoPosicionDTO);

    default EstadoPosicion fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadoPosicion estadoPosicion = new EstadoPosicion();
        estadoPosicion.setId(id);
        return estadoPosicion;
    }
}
