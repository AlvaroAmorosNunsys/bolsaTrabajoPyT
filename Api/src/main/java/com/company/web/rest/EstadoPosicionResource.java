package com.company.web.rest;

import com.company.service.EstadoPosicionService;
import com.company.web.rest.errors.BadRequestAlertException;
import com.company.service.dto.EstadoPosicionDTO;
import com.company.service.dto.EstadoPosicionCriteria;
import com.company.service.EstadoPosicionQueryService;

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
 * REST controller for managing {@link com.company.domain.EstadoPosicion}.
 */
@RestController
@RequestMapping("/api")
public class EstadoPosicionResource {

    private final Logger log = LoggerFactory.getLogger(EstadoPosicionResource.class);

    private static final String ENTITY_NAME = "estadoPosicion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EstadoPosicionService estadoPosicionService;

    private final EstadoPosicionQueryService estadoPosicionQueryService;

    public EstadoPosicionResource(EstadoPosicionService estadoPosicionService, EstadoPosicionQueryService estadoPosicionQueryService) {
        this.estadoPosicionService = estadoPosicionService;
        this.estadoPosicionQueryService = estadoPosicionQueryService;
    }

    /**
     * {@code POST  /estado-posicions} : Create a new estadoPosicion.
     *
     * @param estadoPosicionDTO the estadoPosicionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new estadoPosicionDTO, or with status {@code 400 (Bad Request)} if the estadoPosicion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/estado-posicions")
    public ResponseEntity<EstadoPosicionDTO> createEstadoPosicion(@Valid @RequestBody EstadoPosicionDTO estadoPosicionDTO) throws URISyntaxException {
        log.debug("REST request to save EstadoPosicion : {}", estadoPosicionDTO);
        if (estadoPosicionDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadoPosicion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadoPosicionDTO result = estadoPosicionService.save(estadoPosicionDTO);
        return ResponseEntity.created(new URI("/api/estado-posicions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /estado-posicions} : Updates an existing estadoPosicion.
     *
     * @param estadoPosicionDTO the estadoPosicionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated estadoPosicionDTO,
     * or with status {@code 400 (Bad Request)} if the estadoPosicionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the estadoPosicionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/estado-posicions")
    public ResponseEntity<EstadoPosicionDTO> updateEstadoPosicion(@Valid @RequestBody EstadoPosicionDTO estadoPosicionDTO) throws URISyntaxException {
        log.debug("REST request to update EstadoPosicion : {}", estadoPosicionDTO);
        if (estadoPosicionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadoPosicionDTO result = estadoPosicionService.save(estadoPosicionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, estadoPosicionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /estado-posicions} : get all the estadoPosicions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of estadoPosicions in body.
     */
    @GetMapping("/estado-posicions")
    public ResponseEntity<List<EstadoPosicionDTO>> getAllEstadoPosicions(EstadoPosicionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EstadoPosicions by criteria: {}", criteria);
        Page<EstadoPosicionDTO> page = estadoPosicionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /estado-posicions/count} : count all the estadoPosicions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/estado-posicions/count")
    public ResponseEntity<Long> countEstadoPosicions(EstadoPosicionCriteria criteria) {
        log.debug("REST request to count EstadoPosicions by criteria: {}", criteria);
        return ResponseEntity.ok().body(estadoPosicionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /estado-posicions/:id} : get the "id" estadoPosicion.
     *
     * @param id the id of the estadoPosicionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the estadoPosicionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/estado-posicions/{id}")
    public ResponseEntity<EstadoPosicionDTO> getEstadoPosicion(@PathVariable Long id) {
        log.debug("REST request to get EstadoPosicion : {}", id);
        Optional<EstadoPosicionDTO> estadoPosicionDTO = estadoPosicionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadoPosicionDTO);
    }

    /**
     * {@code DELETE  /estado-posicions/:id} : delete the "id" estadoPosicion.
     *
     * @param id the id of the estadoPosicionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/estado-posicions/{id}")
    public ResponseEntity<Void> deleteEstadoPosicion(@PathVariable Long id) {
        log.debug("REST request to delete EstadoPosicion : {}", id);
        estadoPosicionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
