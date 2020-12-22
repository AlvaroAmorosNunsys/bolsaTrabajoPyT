package com.company.service;

import com.company.service.dto.FuenteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.company.domain.Fuente}.
 */
public interface FuenteService {

    /**
     * Save a fuente.
     *
     * @param fuenteDTO the entity to save.
     * @return the persisted entity.
     */
    FuenteDTO save(FuenteDTO fuenteDTO);

    /**
     * Get all the fuentes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FuenteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fuente.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FuenteDTO> findOne(Long id);

    /**
     * Delete the "id" fuente.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
