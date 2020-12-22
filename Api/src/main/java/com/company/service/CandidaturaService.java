package com.company.service;

import com.company.service.dto.CandidaturaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.company.domain.Candidatura}.
 */
public interface CandidaturaService {

    /**
     * Save a candidatura.
     *
     * @param candidaturaDTO the entity to save.
     * @return the persisted entity.
     */
    CandidaturaDTO save(CandidaturaDTO candidaturaDTO);

    /**
     * Get all the candidaturas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CandidaturaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" candidatura.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CandidaturaDTO> findOne(Long id);

    /**
     * Delete the "id" candidatura.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
