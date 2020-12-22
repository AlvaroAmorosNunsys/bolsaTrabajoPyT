package com.company.service.impl;

import com.company.service.EstadoPosicionService;
import com.company.domain.EstadoPosicion;
import com.company.repository.EstadoPosicionRepository;
import com.company.service.dto.EstadoPosicionDTO;
import com.company.service.mapper.EstadoPosicionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EstadoPosicion}.
 */
@Service
@Transactional
public class EstadoPosicionServiceImpl implements EstadoPosicionService {

    private final Logger log = LoggerFactory.getLogger(EstadoPosicionServiceImpl.class);

    private final EstadoPosicionRepository estadoPosicionRepository;

    private final EstadoPosicionMapper estadoPosicionMapper;

    public EstadoPosicionServiceImpl(EstadoPosicionRepository estadoPosicionRepository, EstadoPosicionMapper estadoPosicionMapper) {
        this.estadoPosicionRepository = estadoPosicionRepository;
        this.estadoPosicionMapper = estadoPosicionMapper;
    }

    @Override
    public EstadoPosicionDTO save(EstadoPosicionDTO estadoPosicionDTO) {
        log.debug("Request to save EstadoPosicion : {}", estadoPosicionDTO);
        EstadoPosicion estadoPosicion = estadoPosicionMapper.toEntity(estadoPosicionDTO);
        estadoPosicion = estadoPosicionRepository.save(estadoPosicion);
        return estadoPosicionMapper.toDto(estadoPosicion);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EstadoPosicionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EstadoPosicions");
        return estadoPosicionRepository.findAll(pageable)
            .map(estadoPosicionMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EstadoPosicionDTO> findOne(Long id) {
        log.debug("Request to get EstadoPosicion : {}", id);
        return estadoPosicionRepository.findById(id)
            .map(estadoPosicionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EstadoPosicion : {}", id);
        estadoPosicionRepository.deleteById(id);
    }
}
