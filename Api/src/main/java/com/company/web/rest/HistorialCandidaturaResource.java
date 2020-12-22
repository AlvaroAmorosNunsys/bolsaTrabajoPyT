package com.company.web.rest;

import com.company.domain.User;
import com.company.service.HistorialCandidaturaService;
import com.company.service.dto.UsuarioDTO;
import com.company.web.rest.errors.BadRequestAlertException;
import com.company.service.dto.HistorialCandidaturaDTO;
import com.company.service.dto.HistorialCandidaturaCriteria;
import com.company.service.HistorialCandidaturaQueryService;

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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.company.domain.HistorialCandidatura}.
 */
@RestController
@RequestMapping("/api")
public class HistorialCandidaturaResource {

    private final Logger log = LoggerFactory.getLogger(HistorialCandidaturaResource.class);

    private static final String ENTITY_NAME = "historialCandidatura";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistorialCandidaturaService historialCandidaturaService;

    private final HistorialCandidaturaQueryService historialCandidaturaQueryService;

    public HistorialCandidaturaResource(HistorialCandidaturaService historialCandidaturaService, HistorialCandidaturaQueryService historialCandidaturaQueryService) {
        this.historialCandidaturaService = historialCandidaturaService;
        this.historialCandidaturaQueryService = historialCandidaturaQueryService;
    }

    /**
     * {@code POST  /historial-candidaturas} : Create a new historialCandidatura.
     *
     * @param historialCandidaturaDTO the historialCandidaturaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historialCandidaturaDTO, or with status {@code 400 (Bad Request)} if the historialCandidatura has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/historial-candidaturas")
    public ResponseEntity<HistorialCandidaturaDTO> createHistorialCandidatura(@Valid @RequestBody HistorialCandidaturaDTO historialCandidaturaDTO) throws URISyntaxException {
        log.debug("REST request to save HistorialCandidatura : {}", historialCandidaturaDTO);
        if (historialCandidaturaDTO.getId() != null) {
            throw new BadRequestAlertException("A new historialCandidatura cannot already have an ID", ENTITY_NAME, "idexists");
        }
        historialCandidaturaDTO.setFechaCambio(LocalDate.now());
        
        HistorialCandidaturaDTO result = historialCandidaturaService.save(historialCandidaturaDTO);
        return ResponseEntity.created(new URI("/api/historial-candidaturas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /historial-candidaturas} : Updates an existing historialCandidatura.
     *
     * @param historialCandidaturaDTO the historialCandidaturaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historialCandidaturaDTO,
     * or with status {@code 400 (Bad Request)} if the historialCandidaturaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historialCandidaturaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/historial-candidaturas")
    public ResponseEntity<HistorialCandidaturaDTO> updateHistorialCandidatura(@Valid @RequestBody HistorialCandidaturaDTO historialCandidaturaDTO) throws URISyntaxException {
        log.debug("REST request to update HistorialCandidatura : {}", historialCandidaturaDTO);
        if (historialCandidaturaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistorialCandidaturaDTO result = historialCandidaturaService.save(historialCandidaturaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, historialCandidaturaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /historial-candidaturas} : get all the historialCandidaturas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historialCandidaturas in body.
     */
    @GetMapping("/historial-candidaturas")
    public ResponseEntity<List<HistorialCandidaturaDTO>> getAllHistorialCandidaturas(HistorialCandidaturaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get HistorialCandidaturas by criteria: {}", criteria);
        Page<HistorialCandidaturaDTO> page = historialCandidaturaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /historial-candidaturas/count} : count all the historialCandidaturas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/historial-candidaturas/count")
    public ResponseEntity<Long> countHistorialCandidaturas(HistorialCandidaturaCriteria criteria) {
        log.debug("REST request to count HistorialCandidaturas by criteria: {}", criteria);
        return ResponseEntity.ok().body(historialCandidaturaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /historial-candidaturas/:id} : get the "id" historialCandidatura.
     *
     * @param id the id of the historialCandidaturaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historialCandidaturaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/historial-candidaturas/{id}")
    public ResponseEntity<HistorialCandidaturaDTO> getHistorialCandidatura(@PathVariable Long id) {
        log.debug("REST request to get HistorialCandidatura : {}", id);
        Optional<HistorialCandidaturaDTO> historialCandidaturaDTO = historialCandidaturaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(historialCandidaturaDTO);
    }

    /**
     * {@code DELETE  /historial-candidaturas/:id} : delete the "id" historialCandidatura.
     *
     * @param id the id of the historialCandidaturaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/historial-candidaturas/{id}")
    public ResponseEntity<Void> deleteHistorialCandidatura(@PathVariable Long id) {
        log.debug("REST request to delete HistorialCandidatura : {}", id);
        historialCandidaturaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
