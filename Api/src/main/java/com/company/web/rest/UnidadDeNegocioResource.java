package com.company.web.rest;

import com.company.service.UnidadDeNegocioService;
import com.company.web.rest.errors.BadRequestAlertException;
import com.company.service.dto.UnidadDeNegocioDTO;
import com.company.service.dto.UnidadDeNegocioCriteria;
import com.company.service.UnidadDeNegocioQueryService;

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
 * REST controller for managing {@link com.company.domain.UnidadDeNegocio}.
 */
@RestController
@RequestMapping("/api")
public class UnidadDeNegocioResource {

    private final Logger log = LoggerFactory.getLogger(UnidadDeNegocioResource.class);

    private static final String ENTITY_NAME = "unidadDeNegocio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UnidadDeNegocioService unidadDeNegocioService;

    private final UnidadDeNegocioQueryService unidadDeNegocioQueryService;

    public UnidadDeNegocioResource(UnidadDeNegocioService unidadDeNegocioService, UnidadDeNegocioQueryService unidadDeNegocioQueryService) {
        this.unidadDeNegocioService = unidadDeNegocioService;
        this.unidadDeNegocioQueryService = unidadDeNegocioQueryService;
    }

    /**
     * {@code POST  /unidad-de-negocios} : Create a new unidadDeNegocio.
     *
     * @param unidadDeNegocioDTO the unidadDeNegocioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new unidadDeNegocioDTO, or with status {@code 400 (Bad Request)} if the unidadDeNegocio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unidad-de-negocios")
    public ResponseEntity<UnidadDeNegocioDTO> createUnidadDeNegocio(@Valid @RequestBody UnidadDeNegocioDTO unidadDeNegocioDTO) throws URISyntaxException {
        log.debug("REST request to save UnidadDeNegocio : {}", unidadDeNegocioDTO);
        if (unidadDeNegocioDTO.getId() != null) {
            throw new BadRequestAlertException("A new unidadDeNegocio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnidadDeNegocioDTO result = unidadDeNegocioService.save(unidadDeNegocioDTO);
        return ResponseEntity.created(new URI("/api/unidad-de-negocios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /unidad-de-negocios} : Updates an existing unidadDeNegocio.
     *
     * @param unidadDeNegocioDTO the unidadDeNegocioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unidadDeNegocioDTO,
     * or with status {@code 400 (Bad Request)} if the unidadDeNegocioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the unidadDeNegocioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unidad-de-negocios")
    public ResponseEntity<UnidadDeNegocioDTO> updateUnidadDeNegocio(@Valid @RequestBody UnidadDeNegocioDTO unidadDeNegocioDTO) throws URISyntaxException {
        log.debug("REST request to update UnidadDeNegocio : {}", unidadDeNegocioDTO);
        if (unidadDeNegocioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UnidadDeNegocioDTO result = unidadDeNegocioService.save(unidadDeNegocioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, unidadDeNegocioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /unidad-de-negocios} : get all the unidadDeNegocios.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unidadDeNegocios in body.
     */
    @GetMapping("/unidad-de-negocios")
    public ResponseEntity<List<UnidadDeNegocioDTO>> getAllUnidadDeNegocios(UnidadDeNegocioCriteria criteria, Pageable pageable) {
        log.debug("REST request to get UnidadDeNegocios by criteria: {}", criteria);
        Page<UnidadDeNegocioDTO> page = unidadDeNegocioQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /unidad-de-negocios/count} : count all the unidadDeNegocios.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/unidad-de-negocios/count")
    public ResponseEntity<Long> countUnidadDeNegocios(UnidadDeNegocioCriteria criteria) {
        log.debug("REST request to count UnidadDeNegocios by criteria: {}", criteria);
        return ResponseEntity.ok().body(unidadDeNegocioQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /unidad-de-negocios/:id} : get the "id" unidadDeNegocio.
     *
     * @param id the id of the unidadDeNegocioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the unidadDeNegocioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unidad-de-negocios/{id}")
    public ResponseEntity<UnidadDeNegocioDTO> getUnidadDeNegocio(@PathVariable Long id) {
        log.debug("REST request to get UnidadDeNegocio : {}", id);
        Optional<UnidadDeNegocioDTO> unidadDeNegocioDTO = unidadDeNegocioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(unidadDeNegocioDTO);
    }

    /**
     * {@code DELETE  /unidad-de-negocios/:id} : delete the "id" unidadDeNegocio.
     *
     * @param id the id of the unidadDeNegocioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unidad-de-negocios/{id}")
    public ResponseEntity<Void> deleteUnidadDeNegocio(@PathVariable Long id) {
        log.debug("REST request to delete UnidadDeNegocio : {}", id);
        unidadDeNegocioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
