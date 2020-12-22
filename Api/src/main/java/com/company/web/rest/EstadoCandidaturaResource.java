package com.company.web.rest;

import com.company.service.EstadoCandidaturaService;
import com.company.web.rest.errors.BadRequestAlertException;
import com.company.service.dto.EstadoCandidaturaDTO;
import com.company.service.dto.EstadoCandidaturaCriteria;
import com.company.service.EstadoCandidaturaQueryService;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.company.domain.EstadoCandidatura}.
 */
@RestController
@RequestMapping("/api")
public class EstadoCandidaturaResource {

    private final Logger log = LoggerFactory.getLogger(EstadoCandidaturaResource.class);

    private static final String ENTITY_NAME = "estadoCandidatura";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadoCandidaturaService estadoCandidaturaService;

    private final EstadoCandidaturaQueryService estadoCandidaturaQueryService;

    public EstadoCandidaturaResource(EstadoCandidaturaService estadoCandidaturaService, EstadoCandidaturaQueryService estadoCandidaturaQueryService) {
        this.estadoCandidaturaService = estadoCandidaturaService;
        this.estadoCandidaturaQueryService = estadoCandidaturaQueryService;
    }

    /**
     * {@code POST  /estado-candidaturas} : Create a new estadoCandidatura.
     *
     * @param estadoCandidaturaDTO the estadoCandidaturaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadoCandidaturaDTO, or with status {@code 400 (Bad Request)} if the estadoCandidatura has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estado-candidaturas")
    public ResponseEntity<EstadoCandidaturaDTO> createEstadoCandidatura(@Valid @RequestBody EstadoCandidaturaDTO estadoCandidaturaDTO) throws URISyntaxException {
        log.debug("REST request to save EstadoCandidatura : {}", estadoCandidaturaDTO);
        if (estadoCandidaturaDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadoCandidatura cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadoCandidaturaDTO result = estadoCandidaturaService.save(estadoCandidaturaDTO);
        return ResponseEntity.created(new URI("/api/estado-candidaturas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estado-candidaturas} : Updates an existing estadoCandidatura.
     *
     * @param estadoCandidaturaDTO the estadoCandidaturaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadoCandidaturaDTO,
     * or with status {@code 400 (Bad Request)} if the estadoCandidaturaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadoCandidaturaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estado-candidaturas")
    public ResponseEntity<EstadoCandidaturaDTO> updateEstadoCandidatura(@Valid @RequestBody EstadoCandidaturaDTO estadoCandidaturaDTO) throws URISyntaxException {
        log.debug("REST request to update EstadoCandidatura : {}", estadoCandidaturaDTO);
        if (estadoCandidaturaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadoCandidaturaDTO result = estadoCandidaturaService.save(estadoCandidaturaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, estadoCandidaturaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /estado-candidaturas} : get all the estadoCandidaturas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadoCandidaturas in body.
     */
    @GetMapping("/estado-candidaturas")
    public ResponseEntity<List<EstadoCandidaturaDTO>> getAllEstadoCandidaturas(EstadoCandidaturaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EstadoCandidaturas by criteria: {}", criteria);
        Page<EstadoCandidaturaDTO> page = estadoCandidaturaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /estado-candidaturas/count} : count all the estadoCandidaturas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/estado-candidaturas/count")
    public ResponseEntity<Long> countEstadoCandidaturas(EstadoCandidaturaCriteria criteria) {
        log.debug("REST request to count EstadoCandidaturas by criteria: {}", criteria);
        return ResponseEntity.ok().body(estadoCandidaturaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /estado-candidaturas/:id} : get the "id" estadoCandidatura.
     *
     * @param id the id of the estadoCandidaturaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadoCandidaturaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estado-candidaturas/{id}")
    public ResponseEntity<EstadoCandidaturaDTO> getEstadoCandidatura(@PathVariable Long id) {
        log.debug("REST request to get EstadoCandidatura : {}", id);
        Optional<EstadoCandidaturaDTO> estadoCandidaturaDTO = estadoCandidaturaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadoCandidaturaDTO);
    }

    /**
     * {@code DELETE  /estado-candidaturas/:id} : delete the "id" estadoCandidatura.
     *
     * @param id the id of the estadoCandidaturaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estado-candidaturas/{id}")
    public ResponseEntity<Void> deleteEstadoCandidatura(@PathVariable Long id) {
        log.debug("REST request to delete EstadoCandidatura : {}", id);
        estadoCandidaturaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
