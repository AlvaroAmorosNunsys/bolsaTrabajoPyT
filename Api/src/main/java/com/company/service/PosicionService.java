package com.company.service;

import com.company.service.dto.PosicionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.company.domain.Posicion}.
 */
public interface PosicionService {

    /**
     * Save a posicion.
     *
     * @param posicionDTO the entity to save.
     * @return the persisted entity.
     */
    PosicionDTO save(PosicionDTO posicionDTO);

    /**
     * Get all the posicions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PosicionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" posicion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PosicionDTO> findOne(Long id);

    /**
     * Delete the "id" posicion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
