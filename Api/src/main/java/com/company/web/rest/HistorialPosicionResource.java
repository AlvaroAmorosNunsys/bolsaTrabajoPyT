package com.company.web.rest;

import com.company.service.HistorialPosicionService;
import com.company.web.rest.errors.BadRequestAlertException;
import com.company.service.dto.HistorialPosicionDTO;
import com.company.service.dto.HistorialPosicionCriteria;
import com.company.service.HistorialPosicionQueryService;

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
 * REST controller for managing {@link com.company.domain.HistorialPosicion}.
 */
@RestController
@RequestMapping("/api")
public class HistorialPosicionResource {

    private final Logger log = LoggerFactory.getLogger(HistorialPosicionResource.class);

    private static final String ENTITY_NAME = "historialPosicion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistorialPosicionService historialPosicionService;

    private final HistorialPosicionQueryService historialPosicionQueryService;

    public HistorialPosicionResource(HistorialPosicionService historialPosicionService, HistorialPosicionQueryService historialPosicionQueryService) {
        this.historialPosicionService = historialPosicionService;
        this.historialPosicionQueryService = historialPosicionQueryService;
    }

    /**
     * {@code POST  /historial-posicions} : Create a new historialPosicion.
     *
     * @param historialPosicionDTO the historialPosicionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historialPosicionDTO, or with status {@code 400 (Bad Request)} if the historialPosicion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/historial-posicions")
    public ResponseEntity<HistorialPosicionDTO> createHistorialPosicion(@Valid @RequestBody HistorialPosicionDTO historialPosicionDTO) throws URISyntaxException {
        log.debug("REST request to save HistorialPosicion : {}", historialPosicionDTO);
        if (historialPosicionDTO.getId() != null) {
            throw new BadRequestAlertException("A new historialPosicion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistorialPosicionDTO result = historialPosicionService.save(historialPosicionDTO);
        return ResponseEntity.created(new URI("/api/historial-posicions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /historial-posicions} : Updates an existing historialPosicion.
     *
     * @param historialPosicionDTO the historialPosicionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historialPosicionDTO,
     * or with status {@code 400 (Bad Request)} if the historialPosicionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historialPosicionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/historial-posicions")
    public ResponseEntity<HistorialPosicionDTO> updateHistorialPosicion(@Valid @RequestBody HistorialPosicionDTO historialPosicionDTO) throws URISyntaxException {
        log.debug("REST request to update HistorialPosicion : {}", historialPosicionDTO);
        if (historialPosicionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistorialPosicionDTO result = historialPosicionService.save(historialPosicionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, historialPosicionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /historial-posicions} : get all the historialPosicions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historialPosicions in body.
     */
    @GetMapping("/historial-posicions")
    public ResponseEntity<List<HistorialPosicionDTO>> getAllHistorialPosicions(HistorialPosicionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get HistorialPosicions by criteria: {}", criteria);
        Page<HistorialPosicionDTO> page = historialPosicionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /historial-posicions/count} : count all the historialPosicions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/historial-posicions/count")
    public ResponseEntity<Long> countHistorialPosicions(HistorialPosicionCriteria criteria) {
        log.debug("REST request to count HistorialPosicions by criteria: {}", criteria);
        return ResponseEntity.ok().body(historialPosicionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /historial-posicions/:id} : get the "id" historialPosicion.
     *
     * @param id the id of the historialPosicionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historialPosicionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/historial-posicions/{id}")
    public ResponseEntity<HistorialPosicionDTO> getHistorialPosicion(@PathVariable Long id) {
        log.debug("REST request to get HistorialPosicion : {}", id);
        Optional<HistorialPosicionDTO> historialPosicionDTO = historialPosicionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(historialPosicionDTO);
    }

    /**
     * {@code DELETE  /historial-posicions/:id} : delete the "id" historialPosicion.
     *
     * @param id the id of the historialPosicionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/historial-posicions/{id}")
    public ResponseEntity<Void> deleteHistorialPosicion(@PathVariable Long id) {
        log.debug("REST request to delete HistorialPosicion : {}", id);
        historialPosicionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
