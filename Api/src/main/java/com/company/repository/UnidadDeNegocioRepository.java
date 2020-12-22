package com.company.repository;

import com.company.domain.UnidadDeNegocio;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UnidadDeNegocio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnidadDeNegocioRepository extends JpaRepository<UnidadDeNegocio, Long>, JpaSpecificationExecutor<UnidadDeNegocio> {
}
