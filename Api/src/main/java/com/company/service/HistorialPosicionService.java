package com.company.service;

import com.company.service.dto.HistorialPosicionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.company.domain.HistorialPosicion}.
 */
public interface HistorialPosicionService {

    /**
     * Save a historialPosicion.
     *
     * @param historialPosicionDTO the entity to save.
     * @return the persisted entity.
     */
    HistorialPosicionDTO save(HistorialPosicionDTO historialPosicionDTO);

    /**
     * Get all the historialPosicions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HistorialPosicionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" historialPosicion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HistorialPosicionDTO> findOne(Long id);

    /**
     * Delete the "id" historialPosicion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
