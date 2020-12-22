package com.company.service.impl;

import com.company.service.EstadoCandidaturaService;
import com.company.domain.EstadoCandidatura;
import com.company.repository.EstadoCandidaturaRepository;
import com.company.service.dto.EstadoCandidaturaDTO;
import com.company.service.mapper.EstadoCandidaturaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EstadoCandidatura}.
 */
@Service
@Transactional
public class EstadoCandidaturaServiceImpl implements EstadoCandidaturaService {

    private final Logger log = LoggerFactory.getLogger(EstadoCandidaturaServiceImpl.class);

    private final EstadoCandidaturaRepository estadoCandidaturaRepository;

    private final EstadoCandidaturaMapper estadoCandidaturaMapper;

    public EstadoCandidaturaServiceImpl(EstadoCandidaturaRepository estadoCandidaturaRepository, EstadoCandidaturaMapper estadoCandidaturaMapper) {
        this.estadoCandidaturaRepository = estadoCandidaturaRepository;
        this.estadoCandidaturaMapper = estadoCandidaturaMapper;
    }

    @Override
    public EstadoCandidaturaDTO save(EstadoCandidaturaDTO estadoCandidaturaDTO) {
        log.debug("Request to save EstadoCandidatura : {}", estadoCandidaturaDTO);
        EstadoCandidatura estadoCandidatura = estadoCandidaturaMapper.toEntity(estadoCandidaturaDTO);
        estadoCandidatura = estadoCandidaturaRepository.save(estadoCandidatura);
        return estadoCandidaturaMapper.toDto(estadoCandidatura);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EstadoCandidaturaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EstadoCandidaturas");
        return estadoCandidaturaRepository.findAll(pageable)
            .map(estadoCandidaturaMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EstadoCandidaturaDTO> findOne(Long id) {
        log.debug("Request to get EstadoCandidatura : {}", id);
        return estadoCandidaturaRepository.findById(id)
            .map(estadoCandidaturaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstadoCandidatura : {}", id);
        estadoCandidaturaRepository.deleteById(id);
    }
}
