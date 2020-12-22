package com.company.service.mapper;


import com.company.domain.*;
import com.company.service.dto.EstadoCandidaturaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EstadoCandidatura} and its DTO {@link EstadoCandidaturaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstadoCandidaturaMapper extends EntityMapper<EstadoCandidaturaDTO, EstadoCandidatura> {


    @Mapping(target = "historialCandidaturas", ignore = true)
    @Mapping(target = "removeHistorialCandidatura", ignore = true)
    EstadoCandidatura toEntity(EstadoCandidaturaDTO estadoCandidaturaDTO);

    default EstadoCandidatura fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadoCandidatura estadoCandidatura = new EstadoCandidatura();
        estadoCandidatura.setId(id);
        return estadoCandidatura;
    }
}
