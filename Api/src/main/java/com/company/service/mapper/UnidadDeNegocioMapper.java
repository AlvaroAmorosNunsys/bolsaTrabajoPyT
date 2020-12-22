package com.company.service.mapper;


import com.company.domain.*;
import com.company.service.dto.UnidadDeNegocioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UnidadDeNegocio} and its DTO {@link UnidadDeNegocioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UnidadDeNegocioMapper extends EntityMapper<UnidadDeNegocioDTO, UnidadDeNegocio> {


    @Mapping(target = "posicions", ignore = true)
    @Mapping(target = "removePosicion", ignore = true)
    @Mapping(target = "usuarios", ignore = true)
    @Mapping(target = "removeUsuario", ignore = true)
    UnidadDeNegocio toEntity(UnidadDeNegocioDTO unidadDeNegocioDTO);

    default UnidadDeNegocio fromId(Long id) {
        if (id == null) {
            return null;
        }
        UnidadDeNegocio unidadDeNegocio = new UnidadDeNegocio();
        unidadDeNegocio.setId(id);
        return unidadDeNegocio;
    }
}
