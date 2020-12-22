package com.company.service.mapper;


import com.company.domain.*;
import com.company.service.dto.CandidaturaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Candidatura} and its DTO {@link CandidaturaDTO}.
 */
@Mapper(componentModel = "spring", uses = {EstadoCandidaturaMapper.class, FuenteMapper.class, PosicionMapper.class, PersonaMapper.class})
public interface CandidaturaMapper extends EntityMapper<CandidaturaDTO, Candidatura> {

    @Mapping(source = "estadoCandidatura.id", target = "estadoCandidaturaId")
    @Mapping(source = "estadoCandidatura.nombre", target = "estadoCandidaturaNombre")
    @Mapping(source = "fuente.id", target = "fuenteId")
    @Mapping(source = "fuente.nombre", target = "fuenteNombre")
    @Mapping(source = "posicion.id", target = "posicionId")
    @Mapping(source = "posicion.titulo", target = "posicionTitulo")
    @Mapping(source = "persona.id", target = "personaId")
    @Mapping(source = "persona.documentoIdentidad", target = "personaDocumentoIdentidad")
    CandidaturaDTO toDto(Candidatura candidatura);

    @Mapping(target = "historialCandidaturas", ignore = true)
    @Mapping(target = "removeHistorialCandidatura", ignore = true)
    @Mapping(source = "estadoCandidaturaId", target = "estadoCandidatura")
    @Mapping(source = "fuenteId", target = "fuente")
    @Mapping(source = "posicionId", target = "posicion")
    @Mapping(source = "personaId", target = "persona")
    Candidatura toEntity(CandidaturaDTO candidaturaDTO);

    default Candidatura fromId(Long id) {
        if (id == null) {
            return null;
        }
        Candidatura candidatura = new Candidatura();
        candidatura.setId(id);
        return candidatura;
    }
}
