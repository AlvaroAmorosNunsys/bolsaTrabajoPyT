package com.company.service;

import com.company.service.dto.TipoJornadaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.company.domain.TipoJornada}.
 */
public interface TipoJornadaService {

    /**
     * Save a tipoJornada.
     *
     * @param tipoJornadaDTO the entity to save.
     * @return the persisted entity.
     */
    TipoJornadaDTO save(TipoJornadaDTO tipoJornadaDTO);

    /**
     * Get all the tipoJornadas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TipoJornadaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tipoJornada.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TipoJornadaDTO> findOne(Long id);

    /**
     * Delete the "id" tipoJornada.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
