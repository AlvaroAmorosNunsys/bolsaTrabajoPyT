package com.company.service;

import com.company.service.dto.EstadoCandidaturaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.company.domain.EstadoCandidatura}.
 */
public interface EstadoCandidaturaService {

    /**
     * Save a estadoCandidatura.
     *
     * @param estadoCandidaturaDTO the entity to save.
     * @return the persisted entity.
     */
    EstadoCandidaturaDTO save(EstadoCandidaturaDTO estadoCandidaturaDTO);

    /**
     * Get all the estadoCandidaturas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EstadoCandidaturaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" estadoCandidatura.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EstadoCandidaturaDTO> findOne(Long id);

    /**
     * Delete the "id" estadoCandidatura.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
