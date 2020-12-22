package com.company.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.company.domain.TipoJornada;
import com.company.domain.*; // for static metamodels
import com.company.repository.TipoJornadaRepository;
import com.company.service.dto.TipoJornadaCriteria;
import com.company.service.dto.TipoJornadaDTO;
import com.company.service.mapper.TipoJornadaMapper;

/**
 * Service for executing complex queries for {@link TipoJornada} entities in the database.
 * The main input is a {@link TipoJornadaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TipoJornadaDTO} or a {@link Page} of {@link TipoJornadaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TipoJornadaQueryService extends QueryService<TipoJornada> {

    private final Logger log = LoggerFactory.getLogger(TipoJornadaQueryService.class);

    private final TipoJornadaRepository tipoJornadaRepository;

    private final TipoJornadaMapper tipoJornadaMapper;

    public TipoJornadaQueryService(TipoJornadaRepository tipoJornadaRepository, TipoJornadaMapper tipoJornadaMapper) {
        this.tipoJornadaRepository = tipoJornadaRepository;
        this.tipoJornadaMapper = tipoJornadaMapper;
    }

    /**
     * Return a {@link List} of {@link TipoJornadaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TipoJornadaDTO> findByCriteria(TipoJornadaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TipoJornada> specification = createSpecification(criteria);
        return tipoJornadaMapper.toDto(tipoJornadaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TipoJornadaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TipoJornadaDTO> findByCriteria(TipoJornadaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TipoJornada> specification = createSpecification(criteria);
        return tipoJornadaRepository.findAll(specification, page)
            .map(tipoJornadaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TipoJornadaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TipoJornada> specification = createSpecification(criteria);
        return tipoJornadaRepository.count(specification);
    }

    /**
     * Function to convert {@link TipoJornadaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TipoJornada> createSpecification(TipoJornadaCriteria criteria) {
        Specification<TipoJornada> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TipoJornada_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), TipoJornada_.nombre));
            }
        }
        return specification;
    }
}
