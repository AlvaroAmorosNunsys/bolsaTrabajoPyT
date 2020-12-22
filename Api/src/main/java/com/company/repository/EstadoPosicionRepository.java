package com.company.repository;

import com.company.domain.EstadoPosicion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EstadoPosicion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstadoPosicionRepository extends JpaRepository<EstadoPosicion, Long>, JpaSpecificationExecutor<EstadoPosicion> {
}
