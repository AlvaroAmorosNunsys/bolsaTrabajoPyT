package com.company.service;

import com.company.service.dto.EstadoPosicionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.company.domain.EstadoPosicion}.
 */
public interface EstadoPosicionService {

    /**
     * Save a estadoPosicion.
     *
     * @param estadoPosicionDTO the entity to save.
     * @return the persisted entity.
     */
    EstadoPosicionDTO save(EstadoPosicionDTO estadoPosicionDTO);

    /**
     * Get all the estadoPosicions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EstadoPosicionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" estadoPosicion.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadoPosicionDTO> findOne(Long id);

    /**
     * Delete the "id" estadoPosicion.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
