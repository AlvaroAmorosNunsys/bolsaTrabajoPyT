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

import com.company.domain.UnidadDeNegocio;
import com.company.domain.*; // for static metamodels
import com.company.repository.UnidadDeNegocioRepository;
import com.company.service.dto.UnidadDeNegocioCriteria;
import com.company.service.dto.UnidadDeNegocioDTO;
import com.company.service.mapper.UnidadDeNegocioMapper;

/**
 * Service for executing complex queries for {@link UnidadDeNegocio} entities in the database.
 * The main input is a {@link UnidadDeNegocioCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UnidadDeNegocioDTO} or a {@link Page} of {@link UnidadDeNegocioDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UnidadDeNegocioQueryService extends QueryService<UnidadDeNegocio> {

    private final Logger log = LoggerFactory.getLogger(UnidadDeNegocioQueryService.class);

    private final UnidadDeNegocioRepository unidadDeNegocioRepository;

    private final UnidadDeNegocioMapper unidadDeNegocioMapper;

    public UnidadDeNegocioQueryService(UnidadDeNegocioRepository unidadDeNegocioRepository, UnidadDeNegocioMapper unidadDeNegocioMapper) {
        this.unidadDeNegocioRepository = unidadDeNegocioRepository;
        this.unidadDeNegocioMapper = unidadDeNegocioMapper;
    }

    /**
     * Return a {@link List} of {@link UnidadDeNegocioDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UnidadDeNegocioDTO> findByCriteria(UnidadDeNegocioCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<UnidadDeNegocio> specification = createSpecification(criteria);
        return unidadDeNegocioMapper.toDto(unidadDeNegocioRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UnidadDeNegocioDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UnidadDeNegocioDTO> findByCriteria(UnidadDeNegocioCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<UnidadDeNegocio> specification = createSpecification(criteria);
        return unidadDeNegocioRepository.findAll(specification, page)
            .map(unidadDeNegocioMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UnidadDeNegocioCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<UnidadDeNegocio> specification = createSpecification(criteria);
        return unidadDeNegocioRepository.count(specification);
    }

    /**
     * Function to convert {@link UnidadDeNegocioCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<UnidadDeNegocio> createSpecification(UnidadDeNegocioCriteria criteria) {
        Specification<UnidadDeNegocio> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), UnidadDeNegocio_.id));
            }
            if (criteria.getNombre() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombre(), UnidadDeNegocio_.nombre));
            }
            if (criteria.getPosicionId() != null) {
                specification = specification.and(buildSpecification(criteria.getPosicionId(),
                    root -> root.join(UnidadDeNegocio_.posicions, JoinType.LEFT).get(Posicion_.id)));
            }
            if (criteria.getUsuarioId() != null) {
                specification = specification.and(buildSpecification(criteria.getUsuarioId(),
                    root -> root.join(UnidadDeNegocio_.usuarios, JoinType.LEFT).get(Usuario_.id)));
            }
        }
        return specification;
    }
}
