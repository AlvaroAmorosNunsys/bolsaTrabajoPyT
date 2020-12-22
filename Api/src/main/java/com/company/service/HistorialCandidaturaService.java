package com.company.service;

import com.company.service.dto.HistorialCandidaturaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.company.domain.HistorialCandidatura}.
 */
public interface HistorialCandidaturaService {

    /**
     * Save a historialCandidatura.
     *
     * @param historialCandidaturaDTO the entity to save.
     * @return the persisted entity.
     */
    HistorialCandidaturaDTO save(HistorialCandidaturaDTO historialCandidaturaDTO);

    /**
     * Get all the historialCandidaturas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HistorialCandidaturaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" historialCandidatura.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HistorialCandidaturaDTO> findOne(Long id);

    /**
     * Delete the "id" historialCandidatura.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
