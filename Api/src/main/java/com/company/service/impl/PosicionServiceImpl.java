package com.company.service.impl;

import com.company.service.PosicionService;
import com.company.domain.Posicion;
import com.company.repository.PosicionRepository;
import com.company.service.dto.PosicionDTO;
import com.company.service.mapper.PosicionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Posicion}.
 */
@Service
@Transactional
public class PosicionServiceImpl implements PosicionService {

    private final Logger log = LoggerFactory.getLogger(PosicionServiceImpl.class);

    private final PosicionRepository posicionRepository;

    private final PosicionMapper posicionMapper;

    public PosicionServiceImpl(PosicionRepository posicionRepository, PosicionMapper posicionMapper) {
        this.posicionRepository = posicionRepository;
        this.posicionMapper = posicionMapper;
    }

    @Override
    public PosicionDTO save(PosicionDTO posicionDTO) {
        log.debug("Request to save Posicion : {}", posicionDTO);
        Posicion posicion = posicionMapper.toEntity(posicionDTO);
        posicion = posicionRepository.save(posicion);
        return posicionMapper.toDto(posicion);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PosicionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Posicions");
        return posicionRepository.findAll(pageable)
            .map(posicionMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PosicionDTO> findOne(Long id) {
        log.debug("Request to get Posicion : {}", id);
        return posicionRepository.findById(id)
            .map(posicionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Posicion : {}", id);
        posicionRepository.deleteById(id);
    }
}
