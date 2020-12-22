package com.company.repository;

import com.company.domain.Candidatura;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Candidatura entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long>, JpaSpecificationExecutor<Candidatura> {
}
