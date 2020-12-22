package com.company.repository;

import com.company.domain.TipoJornada;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TipoJornada entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoJornadaRepository extends JpaRepository<TipoJornada, Long>, JpaSpecificationExecutor<TipoJornada> {
}
