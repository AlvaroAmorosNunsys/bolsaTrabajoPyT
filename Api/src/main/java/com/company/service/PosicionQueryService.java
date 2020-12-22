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

import com.company.domain.Posicion;
import com.company.domain.*; // for static metamodels
import com.company.repository.PosicionRepository;
import com.company.service.dto.PosicionCriteria;
import com.company.service.dto.PosicionDTO;
import com.company.service.mapper.PosicionMapper;

/**
 * Service for executing complex queries for {@link Posicion} entities in the database.
 * The main input is a {@link PosicionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PosicionDTO} or a {@link Page} of {@link PosicionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PosicionQueryService extends QueryService<Posicion> {

    private final Logger log = LoggerFactory.getLogger(PosicionQueryService.class);

    private final PosicionRepository posicionRepository;

    private final PosicionMapper posicionMapper;

    public PosicionQueryService(PosicionRepository posicionRepository, PosicionMapper posicionMapper) {
        this.posicionRepository = posicionRepository;
        this.posicionMapper = posicionMapper;
    }

    /**
     * Return a {@link List} of {@link PosicionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PosicionDTO> findByCriteria(PosicionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Posicion> specification = createSpecification(criteria);
        return posicionMapper.toDto(posicionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PosicionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PosicionDTO> findByCriteria(PosicionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Posicion> specification = createSpecification(criteria);
        return posicionRepository.findAll(specification, page)
            .map(posicionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PosicionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Posicion> specification = createSpecification(criteria);
        return posicionRepository.count(specification);
    }

    /**
     * Function to convert {@link PosicionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Posicion> createSpecification(PosicionCriteria criteria) {
        Specification<Posicion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Posicion_.id));
            }
            if (criteria.getTitulo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitulo(), Posicion_.titulo));
            }
            if (criteria.getDescripcion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescripcion(), Posicion_.descripcion));
            }
            if (criteria.getNumeroPuestos() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumeroPuestos(), Posicion_.numeroPuestos));
            }
            if (criteria.getSalarioMinimo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSalarioMinimo(), Posicion_.salarioMinimo));
            }
            if (criteria.getSalarioMaximo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSalarioMaximo(), Posicion_.salarioMaximo));
            }
            if (criteria.getFechaAlta() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaAlta(), Posicion_.fechaAlta));
            }
            if (criteria.getFechaNecesidad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFechaNecesidad(), Posicion_.fechaNecesidad));
            }
            if (criteria.getCandidaturaId() != null) {
                specification = specification.and(buildSpecification(criteria.getCandidaturaId(),
                    root -> root.join(Posicion_.candidaturas, JoinType.LEFT).get(Candidatura_.id)));
            }
            if (criteria.getHistorialCandidaturaId() != null) {
                specification = specification.and(buildSpecification(criteria.getHistorialCandidaturaId(),
                    root -> root.join(Posicion_.historialCandidaturas, JoinType.LEFT).get(HistorialCandidatura_.id)));
            }
            if (criteria.getHistorialPosicionId() != null) {
                specification = specification.and(buildSpecification(criteria.getHistorialPosicionId(),
                    root -> root.join(Posicion_.historialPosicions, JoinType.LEFT).get(HistorialPosicion_.id)));
            }
            if (criteria.getEstadoPosicionId() != null) {
                specification = specification.and(buildSpecification(criteria.getEstadoPosicionId(),
                    root -> root.join(Posicion_.estadoPosicion, JoinType.LEFT).get(EstadoPosicion_.id)));
            }
            if (criteria.getTipoJornadaId() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoJornadaId(),
                    root -> root.join(Posicion_.tipoJornada, JoinType.LEFT).get(TipoJornada_.id)));
            }
            if (criteria.getUnidadDeNegocioId() != null) {
                specification = specification.and(buildSpecification(criteria.getUnidadDeNegocioId(),
                    root -> root.join(Posicion_.unidadDeNegocio, JoinType.LEFT).get(UnidadDeNegocio_.id)));
            }
        }
        return specification;
    }
}
