package com.company.service.mapper;


import com.company.domain.*;
import com.company.service.dto.HistorialCandidaturaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HistorialCandidatura} and its DTO {@link HistorialCandidaturaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PosicionMapper.class, CandidaturaMapper.class, EstadoCandidaturaMapper.class})
public interface HistorialCandidaturaMapper extends EntityMapper<HistorialCandidaturaDTO, HistorialCandidatura> {

    @Mapping(source = "posicion.id", target = "posicionId")
    @Mapping(source = "posicion.titulo", target = "posicionTitulo")
    @Mapping(source = "candidatura.id", target = "candidaturaId")
    @Mapping(source = "estadoCandidatura.id", target = "estadoCandidaturaId")
    @Mapping(source = "estadoCandidatura.nombre", target = "estadoCandidaturaNombre")
    HistorialCandidaturaDTO toDto(HistorialCandidatura historialCandidatura);

    @Mapping(source = "posicionId", target = "posicion")
    @Mapping(source = "candidaturaId", target = "candidatura")
    @Mapping(source = "estadoCandidaturaId", target = "estadoCandidatura")
    HistorialCandidatura toEntity(HistorialCandidaturaDTO historialCandidaturaDTO);

    default HistorialCandidatura fromId(Long id) {
        if (id == null) {
            return null;
        }
        HistorialCandidatura historialCandidatura = new HistorialCandidatura();
        historialCandidatura.setId(id);
        return historialCandidatura;
    }
}
