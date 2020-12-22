package com.company.service.impl;

import com.company.service.TipoJornadaService;
import com.company.domain.TipoJornada;
import com.company.repository.TipoJornadaRepository;
import com.company.service.dto.TipoJornadaDTO;
import com.company.service.mapper.TipoJornadaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TipoJornada}.
 */
@Service
@Transactional
public class TipoJornadaServiceImpl implements TipoJornadaService {

    private final Logger log = LoggerFactory.getLogger(TipoJornadaServiceImpl.class);

    private final TipoJornadaRepository tipoJornadaRepository;

    private final TipoJornadaMapper tipoJornadaMapper;

    public TipoJornadaServiceImpl(TipoJornadaRepository tipoJornadaRepository, TipoJornadaMapper tipoJornadaMapper) {
        this.tipoJornadaRepository = tipoJornadaRepository;
        this.tipoJornadaMapper = tipoJornadaMapper;
    }

    @Override
    public TipoJornadaDTO save(TipoJornadaDTO tipoJornadaDTO) {
        log.debug("Request to save TipoJornada : {}", tipoJornadaDTO);
        TipoJornada tipoJornada = tipoJornadaMapper.toEntity(tipoJornadaDTO);
        tipoJornada = tipoJornadaRepository.save(tipoJornada);
        return tipoJornadaMapper.toDto(tipoJornada);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TipoJornadaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoJornadas");
        return tipoJornadaRepository.findAll(pageable)
            .map(tipoJornadaMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TipoJornadaDTO> findOne(Long id) {
        log.debug("Request to get TipoJornada : {}", id);
        return tipoJornadaRepository.findById(id)
            .map(tipoJornadaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoJornada : {}", id);
        tipoJornadaRepository.deleteById(id);
    }
}
