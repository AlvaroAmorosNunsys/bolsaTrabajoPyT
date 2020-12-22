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

import com.company.domain.EstadoPosicion;
import com.company.domain.*; // for static metamodels
import com.company.repository.EstadoPosicionRepository;
import com.company.service.dto.EstadoPosicionCriteria;
import com.company.service.dto.EstadoPosicionDTO;
import com.company.service.mapper.EstadoPosicionMapper;

/**
 * Service for executing complex queries for {@link EstadoPosicion} entities in the database.
 * The main input is a {@link EstadoPosicionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EstadoPosicionDTO} or a {@link Page} of {@link EstadoPosicionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EstadoPosicionQueryService extends QueryService<EstadoPosicion> {

    private final Logger log = LoggerFactory.getLogger(EstadoPosicionQueryService.class);

    private final EstadoPosicionRepository estadoPosicionRepository;

    private final EstadoPosicionMapper estadoPosicionMapper;

    public EstadoPosicionQueryService(EstadoPosicionRepository estadoPosicionRepository, EstadoPosicionMapper estadoPosicionMapper) {
        this.estadoPosicionRepository = estadoPosicionRepository;
        this.estadoPosicionMapper = estadoPosicionMapper;
    }

    /**
     * Return a {@link List} of {@link EstadoPosicionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EstadoPosicionDTO> findByCriteria(EstadoPosicionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EstadoPosicion> specification = createSpecification(criteria);
        return estadoPosicionMapper.toDto(estadoPosicionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EstadoPosicionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EstadoPosicionDTO> findByCriteria(EstadoPosicionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EstadoPosicion> specification = createSpecification(criteria);
        return estadoPosicionRepository.findAll(specification, page)
            .map(estadoPosicionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EstadoPosicionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EstadoPosicion> specification = createSpecification(criteria);
        return estadoPosicionRepository.count(specification);
    }

    /**
     * Function to convert {@link EstadoPosicionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EstadoPosicion> createSpecification(EstadoPosicionCriteria criteria) {
        Specification<EstadoPosicion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EstadoPosicion_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), EstadoPosicion_.nombre));
            }
            if (criteria.getHistorialPosicionId() != null) {
                specification = specification.and(buildSpecification(criteria.getHistorialPosicionId(),
                    root -> root.join(EstadoPosicion_.historialPosicions, JoinType.LEFT).get(HistorialPosicion_.id)));
            }
        }
        return specification;
    }
}
