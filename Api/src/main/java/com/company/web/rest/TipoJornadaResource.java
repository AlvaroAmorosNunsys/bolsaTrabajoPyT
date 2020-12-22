package com.company.web.rest;

import com.company.service.TipoJornadaService;
import com.company.web.rest.errors.BadRequestAlertException;
import com.company.service.dto.TipoJornadaDTO;
import com.company.service.dto.TipoJornadaCriteria;
import com.company.service.TipoJornadaQueryService;

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
 * REST controller for managing {@link com.company.domain.TipoJornada}.
 */
@RestController
@RequestMapping("/api")
public class TipoJornadaResource {

    private final Logger log = LoggerFactory.getLogger(TipoJornadaResource.class);

    private static final String ENTITY_NAME = "tipoJornada";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoJornadaService tipoJornadaService;

    private final TipoJornadaQueryService tipoJornadaQueryService;

    public TipoJornadaResource(TipoJornadaService tipoJornadaService, TipoJornadaQueryService tipoJornadaQueryService) {
        this.tipoJornadaService = tipoJornadaService;
        this.tipoJornadaQueryService = tipoJornadaQueryService;
    }

    /**
     * {@code POST  /tipo-jornadas} : Create a new tipoJornada.
     *
     * @param tipoJornadaDTO the tipoJornadaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoJornadaDTO, or with status {@code 400 (Bad Request)} if the tipoJornada has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-jornadas")
    public ResponseEntity<TipoJornadaDTO> createTipoJornada(@Valid @RequestBody TipoJornadaDTO tipoJornadaDTO) throws URISyntaxException {
        log.debug("REST request to save TipoJornada : {}", tipoJornadaDTO);
        if (tipoJornadaDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoJornada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoJornadaDTO result = tipoJornadaService.save(tipoJornadaDTO);
        return ResponseEntity.created(new URI("/api/tipo-jornadas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-jornadas} : Updates an existing tipoJornada.
     *
     * @param tipoJornadaDTO the tipoJornadaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoJornadaDTO,
     * or with status {@code 400 (Bad Request)} if the tipoJornadaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoJornadaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-jornadas")
    public ResponseEntity<TipoJornadaDTO> updateTipoJornada(@Valid @RequestBody TipoJornadaDTO tipoJornadaDTO) throws URISyntaxException {
        log.debug("REST request to update TipoJornada : {}", tipoJornadaDTO);
        if (tipoJornadaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoJornadaDTO result = tipoJornadaService.save(tipoJornadaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tipoJornadaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tipo-jornadas} : get all the tipoJornadas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoJornadas in body.
     */
    @GetMapping("/tipo-jornadas")
    public ResponseEntity<List<TipoJornadaDTO>> getAllTipoJornadas(TipoJornadaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TipoJornadas by criteria: {}", criteria);
        Page<TipoJornadaDTO> page = tipoJornadaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tipo-jornadas/count} : count all the tipoJornadas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/tipo-jornadas/count")
    public ResponseEntity<Long> countTipoJornadas(TipoJornadaCriteria criteria) {
        log.debug("REST request to count TipoJornadas by criteria: {}", criteria);
        return ResponseEntity.ok().body(tipoJornadaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /tipo-jornadas/:id} : get the "id" tipoJornada.
     *
     * @param id the id of the tipoJornadaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoJornadaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-jornadas/{id}")
    public ResponseEntity<TipoJornadaDTO> getTipoJornada(@PathVariable Long id) {
        log.debug("REST request to get TipoJornada : {}", id);
        Optional<TipoJornadaDTO> tipoJornadaDTO = tipoJornadaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoJornadaDTO);
    }

    /**
     * {@code DELETE  /tipo-jornadas/:id} : delete the "id" tipoJornada.
     *
     * @param id the id of the tipoJornadaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-jornadas/{id}")
    public ResponseEntity<Void> deleteTipoJornada(@PathVariable Long id) {
        log.debug("REST request to delete TipoJornada : {}", id);
        tipoJornadaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
