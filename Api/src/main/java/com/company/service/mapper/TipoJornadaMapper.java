package com.company.service.mapper;


import com.company.domain.*;
import com.company.service.dto.TipoJornadaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoJornada} and its DTO {@link TipoJornadaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoJornadaMapper extends EntityMapper<TipoJornadaDTO, TipoJornada> {



    default TipoJornada fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoJornada tipoJornada = new TipoJornada();
        tipoJornada.setId(id);
        return tipoJornada;
    }
}
