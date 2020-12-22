package com.company.repository;

import com.company.domain.Fuente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Fuente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FuenteRepository extends JpaRepository<Fuente, Long>, JpaSpecificationExecutor<Fuente> {
}
