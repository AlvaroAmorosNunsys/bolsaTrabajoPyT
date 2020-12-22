package com.company.service.impl;

import com.company.service.UnidadDeNegocioService;
import com.company.domain.UnidadDeNegocio;
import com.company.repository.UnidadDeNegocioRepository;
import com.company.service.dto.UnidadDeNegocioDTO;
import com.company.service.mapper.UnidadDeNegocioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UnidadDeNegocio}.
 */
@Service
@Transactional
public class UnidadDeNegocioServiceImpl implements UnidadDeNegocioService {

    private final Logger log = LoggerFactory.getLogger(UnidadDeNegocioServiceImpl.class);

    private final UnidadDeNegocioRepository unidadDeNegocioRepository;

    private final UnidadDeNegocioMapper unidadDeNegocioMapper;

    public UnidadDeNegocioServiceImpl(UnidadDeNegocioRepository unidadDeNegocioRepository, UnidadDeNegocioMapper unidadDeNegocioMapper) {
        this.unidadDeNegocioRepository = unidadDeNegocioRepository;
        this.unidadDeNegocioMapper = unidadDeNegocioMapper;
    }

    @Override
    public UnidadDeNegocioDTO save(UnidadDeNegocioDTO unidadDeNegocioDTO) {
        log.debug("Request to save UnidadDeNegocio : {}", unidadDeNegocioDTO);
        UnidadDeNegocio unidadDeNegocio = unidadDeNegocioMapper.toEntity(unidadDeNegocioDTO);
        unidadDeNegocio = unidadDeNegocioRepository.save(unidadDeNegocio);
        return unidadDeNegocioMapper.toDto(unidadDeNegocio);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UnidadDeNegocioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UnidadDeNegocios");
        return unidadDeNegocioRepository.findAll(pageable)
            .map(unidadDeNegocioMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UnidadDeNegocioDTO> findOne(Long id) {
        log.debug("Request to get UnidadDeNegocio : {}", id);
        return unidadDeNegocioRepository.findById(id)
            .map(unidadDeNegocioMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UnidadDeNegocio : {}", id);
        unidadDeNegocioRepository.deleteById(id);
    }
}
