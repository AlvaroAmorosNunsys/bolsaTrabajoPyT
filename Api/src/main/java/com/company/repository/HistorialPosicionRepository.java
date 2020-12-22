package com.company.repository;

import com.company.domain.HistorialPosicion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the HistorialPosicion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistorialPosicionRepository extends JpaRepository<HistorialPosicion, Long>, JpaSpecificationExecutor<HistorialPosicion> {
}
