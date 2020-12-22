package com.company.repository;

import com.company.domain.EstadoCandidatura;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the EstadoCandidatura entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstadoCandidaturaRepository extends JpaRepository<EstadoCandidatura, Long>, JpaSpecificationExecutor<EstadoCandidatura> {
}
