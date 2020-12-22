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

import com.company.domain.HistorialPosicion;
import com.company.domain.*; // for static metamodels
import com.company.repository.HistorialPosicionRepository;
import com.company.service.dto.HistorialPosicionCriteria;
import com.company.service.dto.HistorialPosicionDTO;
import com.company.service.mapper.HistorialPosicionMapper;

/**
 * Service for executing complex queries for {@link HistorialPosicion} entities in the database.
 * The main input is a {@link HistorialPosicionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HistorialPosicionDTO} or a {@link Page} of {@link HistorialPosicionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HistorialPosicionQueryService extends QueryService<HistorialPosicion> {

    private final Logger log = LoggerFactory.getLogger(HistorialPosicionQueryService.class);

    private final HistorialPosicionRepository historialPosicionRepository;

    private final HistorialPosicionMapper historialPosicionMapper;

    public HistorialPosicionQueryService(HistorialPosicionRepository historialPosicionRepository, HistorialPosicionMapper historialPosicionMapper) {
        this.historialPosicionRepository = historialPosicionRepository;
        this.historialPosicionMapper = historialPosicionMapper;
    }

    /**
     * Return a {@link List} of {@link HistorialPosicionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HistorialPosicionDTO> findByCriteria(HistorialPosicionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<HistorialPosicion> specification = createSpecification(criteria);
        return historialPosicionMapper.toDto(historialPosicionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link HistorialPosicionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<HistorialPosicionDTO> findByCriteria(HistorialPosicionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<HistorialPosicion> specification = createSpecification(criteria);
        return historialPosicionRepository.findAll(specification, page)
            .map(historialPosicionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HistorialPosicionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<HistorialPosicion> specification = createSpecification(criteria);
        return historialPosicionRepository.count(specification);
    }

    /**
     * Function to convert {@link HistorialPosicionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<HistorialPosicion> createSpecification(HistorialPosicionCriteria criteria) {
        Specification<HistorialPosicion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), HistorialPosicion_.id));
            }
            if (criteria.getFechaCambio() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaCambio(), HistorialPosicion_.fechaCambio));
            }
            if (criteria.getPorDefecto() != null) {
                specification = specification.and(buildSpecification(criteria.getPorDefecto(), HistorialPosicion_.porDefecto));
            }
            if (criteria.getFechaModificacion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaModificacion(), HistorialPosicion_.fechaModificacion));
            }
            if (criteria.getNombreEditor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombreEditor(), HistorialPosicion_.nombreEditor));
            }
            if (criteria.getEstadoPosicionId() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoPosicionId(),
                    root -> root.join(HistorialPosicion_.estadoPosicion, JoinType.LEFT).get(EstadoPosicion_.id)));
            }
            if (criteria.getPosicionId() != null) {
                specification = specification.and(buildSpecification(criteria.getPosicionId(),
                    root -> root.join(HistorialPosicion_.posicion, JoinType.LEFT).get(Posicion_.id)));
            }
        }
        return specification;
    }
}
