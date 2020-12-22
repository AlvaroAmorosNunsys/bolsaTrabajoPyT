package com.company.service.impl;

import com.company.service.HistorialCandidaturaService;
import com.company.domain.HistorialCandidatura;
import com.company.repository.HistorialCandidaturaRepository;
import com.company.service.dto.HistorialCandidaturaDTO;
import com.company.service.mapper.HistorialCandidaturaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link HistorialCandidatura}.
 */
@Service
@Transactional
public class HistorialCandidaturaServiceImpl implements HistorialCandidaturaService {

    private final Logger log = LoggerFactory.getLogger(HistorialCandidaturaServiceImpl.class);

    private final HistorialCandidaturaRepository historialCandidaturaRepository;

    private final HistorialCandidaturaMapper historialCandidaturaMapper;

    public HistorialCandidaturaServiceImpl(HistorialCandidaturaRepository historialCandidaturaRepository, HistorialCandidaturaMapper historialCandidaturaMapper) {
        this.historialCandidaturaRepository = historialCandidaturaRepository;
        this.historialCandidaturaMapper = historialCandidaturaMapper;
    }

    @Override
    public HistorialCandidaturaDTO save(HistorialCandidaturaDTO historialCandidaturaDTO) {
        log.debug("Request to save HistorialCandidatura : {}", historialCandidaturaDTO);
        HistorialCandidatura historialCandidatura = historialCandidaturaMapper.toEntity(historialCandidaturaDTO);
        historialCandidatura = historialCandidaturaRepository.save(historialCandidatura);
        return historialCandidaturaMapper.toDto(historialCandidatura);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HistorialCandidaturaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HistorialCandidaturas");
        return historialCandidaturaRepository.findAll(pageable)
            .map(historialCandidaturaMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<HistorialCandidaturaDTO> findOne(Long id) {
        log.debug("Request to get HistorialCandidatura : {}", id);
        return historialCandidaturaRepository.findById(id)
            .map(historialCandidaturaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete HistorialCandidatura : {}", id);
        historialCandidaturaRepository.deleteById(id);
    }
}
