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

import com.company.domain.Candidatura;
import com.company.domain.*; // for static metamodels
import com.company.repository.CandidaturaRepository;
import com.company.service.dto.CandidaturaCriteria;
import com.company.service.dto.CandidaturaDTO;
import com.company.service.mapper.CandidaturaMapper;

/**
 * Service for executing complex queries for {@link Candidatura} entities in the database.
 * The main input is a {@link CandidaturaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CandidaturaDTO} or a {@link Page} of {@link CandidaturaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CandidaturaQueryService extends QueryService<Candidatura> {

    private final Logger log = LoggerFactory.getLogger(CandidaturaQueryService.class);

    private final CandidaturaRepository candidaturaRepository;

    private final CandidaturaMapper candidaturaMapper;

    public CandidaturaQueryService(CandidaturaRepository candidaturaRepository, CandidaturaMapper candidaturaMapper) {
        this.candidaturaRepository = candidaturaRepository;
        this.candidaturaMapper = candidaturaMapper;
    }

    /**
     * Return a {@link List} of {@link CandidaturaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CandidaturaDTO> findByCriteria(CandidaturaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Candidatura> specification = createSpecification(criteria);
        return candidaturaMapper.toDto(candidaturaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CandidaturaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CandidaturaDTO> findByCriteria(CandidaturaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Candidatura> specification = createSpecification(criteria);
        return candidaturaRepository.findAll(specification, page)
            .map(candidaturaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CandidaturaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Candidatura> specification = createSpecification(criteria);
        return candidaturaRepository.count(specification);
    }

    /**
     * Function to convert {@link CandidaturaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Candidatura> createSpecification(CandidaturaCriteria criteria) {
        Specification<Candidatura> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Candidatura_.id));
            }
            if (criteria.getHistorialCandidaturaId() != null) {
                specification = specification.and(buildSpecification(criteria.getHistorialCandidaturaId(),
                    root -> root.join(Candidatura_.historialCandidaturas, JoinType.LEFT).get(HistorialCandidatura_.id)));
            }
            if (criteria.getEstadoCandidaturaId() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoCandidaturaId(),
                    root -> root.join(Candidatura_.estadoCandidatura, JoinType.LEFT).get(EstadoCandidatura_.id)));
            }
            if (criteria.getFuenteId() != null) {
                specification = specification.and(buildSpecification(criteria.getFuenteId(),
                    root -> root.join(Candidatura_.fuente, JoinType.LEFT).get(Fuente_.id)));
            }
            if (criteria.getPosicionId() != null) {
                specification = specification.and(buildSpecification(criteria.getPosicionId(),
                    root -> root.join(Candidatura_.posicion, JoinType.LEFT).get(Posicion_.id)));
            }
            if (criteria.getPersonaId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonaId(),
                    root -> root.join(Candidatura_.persona, JoinType.LEFT).get(Persona_.id)));
            }
        }
        return specification;
    }
}
