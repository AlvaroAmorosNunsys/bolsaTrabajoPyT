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

import com.company.domain.HistorialCandidatura;
import com.company.domain.*; // for static metamodels
import com.company.repository.HistorialCandidaturaRepository;
import com.company.service.dto.HistorialCandidaturaCriteria;
import com.company.service.dto.HistorialCandidaturaDTO;
import com.company.service.mapper.HistorialCandidaturaMapper;

/**
 * Service for executing complex queries for {@link HistorialCandidatura} entities in the database.
 * The main input is a {@link HistorialCandidaturaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HistorialCandidaturaDTO} or a {@link Page} of {@link HistorialCandidaturaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HistorialCandidaturaQueryService extends QueryService<HistorialCandidatura> {

    private final Logger log = LoggerFactory.getLogger(HistorialCandidaturaQueryService.class);

    private final HistorialCandidaturaRepository historialCandidaturaRepository;

    private final HistorialCandidaturaMapper historialCandidaturaMapper;

    public HistorialCandidaturaQueryService(HistorialCandidaturaRepository historialCandidaturaRepository, HistorialCandidaturaMapper historialCandidaturaMapper) {
        this.historialCandidaturaRepository = historialCandidaturaRepository;
        this.historialCandidaturaMapper = historialCandidaturaMapper;
    }

    /**
     * Return a {@link List} of {@link HistorialCandidaturaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HistorialCandidaturaDTO> findByCriteria(HistorialCandidaturaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<HistorialCandidatura> specification = createSpecification(criteria);
        return historialCandidaturaMapper.toDto(historialCandidaturaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link HistorialCandidaturaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<HistorialCandidaturaDTO> findByCriteria(HistorialCandidaturaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<HistorialCandidatura> specification = createSpecification(criteria);
        return historialCandidaturaRepository.findAll(specification, page)
            .map(historialCandidaturaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HistorialCandidaturaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<HistorialCandidatura> specification = createSpecification(criteria);
        return historialCandidaturaRepository.count(specification);
    }

    /**
     * Function to convert {@link HistorialCandidaturaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<HistorialCandidatura> createSpecification(HistorialCandidaturaCriteria criteria) {
        Specification<HistorialCandidatura> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), HistorialCandidatura_.id));
            }
            if (criteria.getFechaCambio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCambio(), HistorialCandidatura_.fechaCambio));
            }
            if (criteria.getPorDefecto() != null) {
                specification = specification.and(buildSpecification(criteria.getPorDefecto(), HistorialCandidatura_.porDefecto));
            }
            if (criteria.getFechaModificacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaModificacion(), HistorialCandidatura_.fechaModificacion));
            }
            if (criteria.getNombreEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombreEditor(), HistorialCandidatura_.nombreEditor));
            }
            if (criteria.getPosicionId() != null) {
                specification = specification.and(buildSpecification(criteria.getPosicionId(),
                    root -> root.join(HistorialCandidatura_.posicion, JoinType.LEFT).get(Posicion_.id)));
            }
            if (criteria.getCandidaturaId() != null) {
                specification = specification.and(buildSpecification(criteria.getCandidaturaId(),
                    root -> root.join(HistorialCandidatura_.candidatura, JoinType.LEFT).get(Candidatura_.id)));
            }
            if (criteria.getEstadoCandidaturaId() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoCandidaturaId(),
                    root -> root.join(HistorialCandidatura_.estadoCandidatura, JoinType.LEFT).get(EstadoCandidatura_.id)));
            }
        }
        return specification;
    }
}
