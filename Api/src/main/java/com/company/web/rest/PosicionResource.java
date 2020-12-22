package com.company.web.rest;

import com.company.service.PosicionService;
import com.company.web.rest.errors.BadRequestAlertException;
import com.company.service.dto.PosicionDTO;
import com.company.service.dto.PosicionCriteria;
import com.company.service.PosicionQueryService;

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
 * REST controller for managing {@link com.company.domain.Posicion}.
 */
@RestController
@RequestMapping("/api")
public class PosicionResource {

    private final Logger log = LoggerFactory.getLogger(PosicionResource.class);

    private static final String ENTITY_NAME = "posicion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PosicionService posicionService;

    private final PosicionQueryService posicionQueryService;

    public PosicionResource(PosicionService posicionService, PosicionQueryService posicionQueryService) {
        this.posicionService = posicionService;
        this.posicionQueryService = posicionQueryService;
    }

    /**
     * {@code POST  /posicions} : Create a new posicion.
     *
     * @param posicionDTO the posicionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new posicionDTO, or with status {@code 400 (Bad Request)} if the posicion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/posicions")
    public ResponseEntity<PosicionDTO> createPosicion(@Valid @RequestBody PosicionDTO posicionDTO) throws URISyntaxException {
        log.debug("REST request to save Posicion : {}", posicionDTO);
        if (posicionDTO.getId() != null) {
            throw new BadRequestAlertException("A new posicion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        posicionDTO.setFechaAlta(LocalDate.now());
        PosicionDTO result = posicionService.save(posicionDTO);
        return ResponseEntity.created(new URI("/api/posicions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /posicions} : Updates an existing posicion.
     *
     * @param posicionDTO the posicionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated posicionDTO,
     * or with status {@code 400 (Bad Request)} if the posicionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the posicionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/posicions")
    public ResponseEntity<PosicionDTO> updatePosicion(@Valid @RequestBody PosicionDTO posicionDTO) throws URISyntaxException {
        log.debug("REST request to update Posicion : {}", posicionDTO);
        if (posicionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PosicionDTO result = posicionService.save(posicionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, posicionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /posicions} : get all the posicions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of posicions in body.
     */
    @GetMapping("/posicions")
    public ResponseEntity<List<PosicionDTO>> getAllPosicions(PosicionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Posicions by criteria: {}", criteria);
        Page<PosicionDTO> page = posicionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /posicions/count} : count all the posicions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/posicions/count")
    public ResponseEntity<Long> countPosicions(PosicionCriteria criteria) {
        log.debug("REST request to count Posicions by criteria: {}", criteria);
        return ResponseEntity.ok().body(posicionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /posicions/:id} : get the "id" posicion.
     *
     * @param id the id of the posicionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the posicionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/posicions/{id}")
    public ResponseEntity<PosicionDTO> getPosicion(@PathVariable Long id) {
        log.debug("REST request to get Posicion : {}", id);
        Optional<PosicionDTO> posicionDTO = posicionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(posicionDTO);
    }

    /**
     * {@code DELETE  /posicions/:id} : delete the "id" posicion.
     *
     * @param id the id of the posicionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/posicions/{id}")
    public ResponseEntity<Void> deletePosicion(@PathVariable Long id) {
        log.debug("REST request to delete Posicion : {}", id);
        posicionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
