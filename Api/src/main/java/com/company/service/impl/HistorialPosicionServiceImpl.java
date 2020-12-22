package com.company.service.impl;

import com.company.service.HistorialPosicionService;
import com.company.domain.HistorialPosicion;
import com.company.repository.HistorialPosicionRepository;
import com.company.service.dto.HistorialPosicionDTO;
import com.company.service.mapper.HistorialPosicionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link HistorialPosicion}.
 */
@Service
@Transactional
public class HistorialPosicionServiceImpl implements HistorialPosicionService {

    private final Logger log = LoggerFactory.getLogger(HistorialPosicionServiceImpl.class);

    private final HistorialPosicionRepository historialPosicionRepository;

    private final HistorialPosicionMapper historialPosicionMapper;

    public HistorialPosicionServiceImpl(HistorialPosicionRepository historialPosicionRepository, HistorialPosicionMapper historialPosicionMapper) {
        this.historialPosicionRepository = historialPosicionRepository;
        this.historialPosicionMapper = historialPosicionMapper;
    }

    @Override
    public HistorialPosicionDTO save(HistorialPosicionDTO historialPosicionDTO) {
        log.debug("Request to save HistorialPosicion : {}", historialPosicionDTO);
        HistorialPosicion historialPosicion = historialPosicionMapper.toEntity(historialPosicionDTO);
        historialPosicion = historialPosicionRepository.save(historialPosicion);
        return historialPosicionMapper.toDto(historialPosicion);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HistorialPosicionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HistorialPosicions");
        return historialPosicionRepository.findAll(pageable)
            .map(historialPosicionMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<HistorialPosicionDTO> findOne(Long id) {
        log.debug("Request to get HistorialPosicion : {}", id);
        return historialPosicionRepository.findById(id)
            .map(historialPosicionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete HistorialPosicion : {}", id);
        historialPosicionRepository.deleteById(id);
    }
}
