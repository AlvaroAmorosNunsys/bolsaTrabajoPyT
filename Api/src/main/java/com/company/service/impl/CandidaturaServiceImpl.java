package com.company.service.impl;

import com.company.service.CandidaturaService;
import com.company.domain.Candidatura;
import com.company.repository.CandidaturaRepository;
import com.company.service.dto.CandidaturaDTO;
import com.company.service.mapper.CandidaturaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Candidatura}.
 */
@Service
@Transactional
public class CandidaturaServiceImpl implements CandidaturaService {

    private final Logger log = LoggerFactory.getLogger(CandidaturaServiceImpl.class);

    private final CandidaturaRepository candidaturaRepository;

    private final CandidaturaMapper candidaturaMapper;

    public CandidaturaServiceImpl(CandidaturaRepository candidaturaRepository, CandidaturaMapper candidaturaMapper) {
        this.candidaturaRepository = candidaturaRepository;
        this.candidaturaMapper = candidaturaMapper;
    }

    @Override
    public CandidaturaDTO save(CandidaturaDTO candidaturaDTO) {
        log.debug("Request to save Candidatura : {}", candidaturaDTO);
        Candidatura candidatura = candidaturaMapper.toEntity(candidaturaDTO);
        candidatura = candidaturaRepository.save(candidatura);
        return candidaturaMapper.toDto(candidatura);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CandidaturaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Candidaturas");
        return candidaturaRepository.findAll(pageable)
            .map(candidaturaMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CandidaturaDTO> findOne(Long id) {
        log.debug("Request to get Candidatura : {}", id);
        return candidaturaRepository.findById(id)
            .map(candidaturaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Candidatura : {}", id);
        candidaturaRepository.deleteById(id);
    }
}
