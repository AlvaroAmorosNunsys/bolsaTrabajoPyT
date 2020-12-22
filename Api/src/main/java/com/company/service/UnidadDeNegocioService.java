package com.company.service;

import com.company.service.dto.UnidadDeNegocioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.company.domain.UnidadDeNegocio}.
 */
public interface UnidadDeNegocioService {

    /**
     * Save a unidadDeNegocio.
     *
     * @param unidadDeNegocioDTO the entity to save.
     * @return the persisted entity.
     */
    UnidadDeNegocioDTO save(UnidadDeNegocioDTO unidadDeNegocioDTO);

    /**
     * Get all the unidadDeNegocios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UnidadDeNegocioDTO> findAll(Pageable pageable);


    /**
     * Get the "id" unidadDeNegocio.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UnidadDeNegocioDTO> findOne(Long id);

    /**
     * Delete the "id" unidadDeNegocio.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
