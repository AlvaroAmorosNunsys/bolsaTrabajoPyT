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

import com.company.domain.EstadoCandidatura;
import com.company.domain.*; // for static metamodels
import com.company.repository.EstadoCandidaturaRepository;
import com.company.service.dto.EstadoCandidaturaCriteria;
import com.company.service.dto.EstadoCandidaturaDTO;
import com.company.service.mapper.EstadoCandidaturaMapper;

/**
 * Service for executing complex queries for {@link EstadoCandidatura} entities in the database.
 * The main input is a {@link EstadoCandidaturaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EstadoCandidaturaDTO} or a {@link Page} of {@link EstadoCandidaturaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EstadoCandidaturaQueryService extends QueryService<EstadoCandidatura> {

    private final Logger log = LoggerFactory.getLogger(EstadoCandidaturaQueryService.class);

    private final EstadoCandidaturaRepository estadoCandidaturaRepository;

    private final EstadoCandidaturaMapper estadoCandidaturaMapper;

    public EstadoCandidaturaQueryService(EstadoCandidaturaRepository estadoCandidaturaRepository, EstadoCandidaturaMapper estadoCandidaturaMapper) {
        this.estadoCandidaturaRepository = estadoCandidaturaRepository;
        this.estadoCandidaturaMapper = estadoCandidaturaMapper;
    }

    /**
     * Return a {@link List} of {@link EstadoCandidaturaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EstadoCandidaturaDTO> findByCriteria(EstadoCandidaturaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EstadoCandidatura> specification = createSpecification(criteria);
        return estadoCandidaturaMapper.toDto(estadoCandidaturaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EstadoCandidaturaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EstadoCandidaturaDTO> findByCriteria(EstadoCandidaturaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EstadoCandidatura> specification = createSpecification(criteria);
        return estadoCandidaturaRepository.findAll(specification, page)
            .map(estadoCandidaturaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EstadoCandidaturaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EstadoCandidatura> specification = createSpecification(criteria);
        return estadoCandidaturaRepository.count(specification);
    }

    /**
     * Function to convert {@link EstadoCandidaturaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EstadoCandidatura> createSpecification(EstadoCandidaturaCriteria criteria) {
        Specification<EstadoCandidatura> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EstadoCandidatura_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), EstadoCandidatura_.nombre));
            }
            if (criteria.getHistorialCandidaturaId() != null) {
                specification = specification.and(buildSpecification(criteria.getHistorialCandidaturaId(),
                    root -> root.join(EstadoCandidatura_.historialCandidaturas, JoinType.LEFT).get(HistorialCandidatura_.id)));
            }
        }
        return specification;
    }
}
