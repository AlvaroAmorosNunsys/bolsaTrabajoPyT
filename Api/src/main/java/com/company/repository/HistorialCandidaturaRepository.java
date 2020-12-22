package com.company.repository;

import com.company.domain.HistorialCandidatura;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the HistorialCandidatura entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistorialCandidaturaRepository extends JpaRepository<HistorialCandidatura, Long>, JpaSpecificationExecutor<HistorialCandidatura> {
}
