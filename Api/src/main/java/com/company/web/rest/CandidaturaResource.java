package com.company.web.rest;

import com.company.service.CandidaturaService;
import com.company.web.rest.errors.BadRequestAlertException;
import com.company.service.dto.CandidaturaDTO;
import com.company.service.dto.CandidaturaCriteria;
import com.company.service.CandidaturaQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.company.domain.Candidatura}.
 */
@RestController
@RequestMapping("/api")
public class CandidaturaResource {

    private final Logger log = LoggerFactory.getLogger(CandidaturaResource.class);

    private static final String ENTITY_NAME = "candidatura";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CandidaturaService candidaturaService;

    private final CandidaturaQueryService candidaturaQueryService;

    public CandidaturaResource(CandidaturaService candidaturaService, CandidaturaQueryService candidaturaQueryService) {
        this.candidaturaService = candidaturaService;
        this.candidaturaQueryService = candidaturaQueryService;
    }

    /**
     * {@code POST  /candidaturas} : Create a new candidatura.
     *
     * @param candidaturaDTO the candidaturaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new candidaturaDTO, or with status {@code 400 (Bad Request)} if the candidatura has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/candidaturas")
    public ResponseEntity<CandidaturaDTO> createCandidatura(@RequestBody CandidaturaDTO candidaturaDTO) throws URISyntaxException {
        log.debug("REST request to save Candidatura : {}", candidaturaDTO);
        if (candidaturaDTO.getId() != null) {
            throw new BadRequestAlertException("A new candidatura cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CandidaturaDTO result = candidaturaService.save(candidaturaDTO);
        return ResponseEntity.created(new URI("/api/candidaturas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /candidaturas} : Updates an existing candidatura.
     *
     * @param candidaturaDTO the candidaturaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated candidaturaDTO,
     * or with status {@code 400 (Bad Request)} if the candidaturaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the candidaturaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/candidaturas")
    public ResponseEntity<CandidaturaDTO> updateCandidatura(@RequestBody CandidaturaDTO candidaturaDTO) throws URISyntaxException {
        log.debug("REST request to update Candidatura : {}", candidaturaDTO);
        if (candidaturaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CandidaturaDTO result = candidaturaService.save(candidaturaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, candidaturaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /candidaturas} : get all the candidaturas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of candidaturas in body.
     */
    @GetMapping("/candidaturas")
    public ResponseEntity<List<CandidaturaDTO>> getAllCandidaturas(CandidaturaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Candidaturas by criteria: {}", criteria);
        Page<CandidaturaDTO> page = candidaturaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /candidaturas/count} : count all the candidaturas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/candidaturas/count")
    public ResponseEntity<Long> countCandidaturas(CandidaturaCriteria criteria) {
        log.debug("REST request to count Candidaturas by criteria: {}", criteria);
        return ResponseEntity.ok().body(candidaturaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /candidaturas/:id} : get the "id" candidatura.
     *
     * @param id the id of the candidaturaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the candidaturaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/candidaturas/{id}")
    public ResponseEntity<CandidaturaDTO> getCandidatura(@PathVariable Long id) {
        log.debug("REST request to get Candidatura : {}", id);
        Optional<CandidaturaDTO> candidaturaDTO = candidaturaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(candidaturaDTO);
    }

    /**
     * {@code DELETE  /candidaturas/:id} : delete the "id" candidatura.
     *
     * @param id the id of the candidaturaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/candidaturas/{id}")
    public ResponseEntity<Void> deleteCandidatura(@PathVariable Long id) {
        log.debug("REST request to delete Candidatura : {}", id);
        candidaturaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
