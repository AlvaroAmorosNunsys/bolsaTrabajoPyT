package com.company.service.impl;

import com.company.service.FuenteService;
import com.company.domain.Fuente;
import com.company.repository.FuenteRepository;
import com.company.service.dto.FuenteDTO;
import com.company.service.mapper.FuenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Fuente}.
 */
@Service
@Transactional
public class FuenteServiceImpl implements FuenteService {

    private final Logger log = LoggerFactory.getLogger(FuenteServiceImpl.class);

    private final FuenteRepository fuenteRepository;

    private final FuenteMapper fuenteMapper;

    public FuenteServiceImpl(FuenteRepository fuenteRepository, FuenteMapper fuenteMapper) {
        this.fuenteRepository = fuenteRepository;
        this.fuenteMapper = fuenteMapper;
    }

    @Override
    public FuenteDTO save(FuenteDTO fuenteDTO) {
        log.debug("Request to save Fuente : {}", fuenteDTO);
        Fuente fuente = fuenteMapper.toEntity(fuenteDTO);
        fuente = fuenteRepository.save(fuente);
        return fuenteMapper.toDto(fuente);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FuenteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Fuentes");
        return fuenteRepository.findAll(pageable)
            .map(fuenteMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FuenteDTO> findOne(Long id) {
        log.debug("Request to get Fuente : {}", id);
        return fuenteRepository.findById(id)
            .map(fuenteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Fuente : {}", id);
        fuenteRepository.deleteById(id);
    }
}
