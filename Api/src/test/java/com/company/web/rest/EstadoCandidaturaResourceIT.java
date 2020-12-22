package com.company.web.rest;

import com.company.ApiApp;
import com.company.domain.EstadoCandidatura;
import com.company.domain.HistorialCandidatura;
import com.company.repository.EstadoCandidaturaRepository;
import com.company.service.EstadoCandidaturaService;
import com.company.service.dto.EstadoCandidaturaDTO;
import com.company.service.mapper.EstadoCandidaturaMapper;
import com.company.service.dto.EstadoCandidaturaCriteria;
import com.company.service.EstadoCandidaturaQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EstadoCandidaturaResource} REST controller.
 */
@SpringBootTest(classes = ApiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EstadoCandidaturaResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private EstadoCandidaturaRepository estadoCandidaturaRepository;

    @Autowired
    private EstadoCandidaturaMapper estadoCandidaturaMapper;

    @Autowired
    private EstadoCandidaturaService estadoCandidaturaService;

    @Autowired
    private EstadoCandidaturaQueryService estadoCandidaturaQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEstadoCandidaturaMockMvc;

    private EstadoCandidatura estadoCandidatura;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadoCandidatura createEntity(EntityManager em) {
        EstadoCandidatura estadoCandidatura = new EstadoCandidatura()
            .nombre(DEFAULT_NOMBRE);
        return estadoCandidatura;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadoCandidatura createUpdatedEntity(EntityManager em) {
        EstadoCandidatura estadoCandidatura = new EstadoCandidatura()
            .nombre(UPDATED_NOMBRE);
        return estadoCandidatura;
    }

    @BeforeEach
    public void initTest() {
        estadoCandidatura = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadoCandidatura() throws Exception {
        int databaseSizeBeforeCreate = estadoCandidaturaRepository.findAll().size();
        // Create the EstadoCandidatura
        EstadoCandidaturaDTO estadoCandidaturaDTO = estadoCandidaturaMapper.toDto(estadoCandidatura);
        restEstadoCandidaturaMockMvc.perform(post("/api/estado-candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoCandidaturaDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadoCandidatura in the database
        List<EstadoCandidatura> estadoCandidaturaList = estadoCandidaturaRepository.findAll();
        assertThat(estadoCandidaturaList).hasSize(databaseSizeBeforeCreate + 1);
        EstadoCandidatura testEstadoCandidatura = estadoCandidaturaList.get(estadoCandidaturaList.size() - 1);
        assertThat(testEstadoCandidatura.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void createEstadoCandidaturaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadoCandidaturaRepository.findAll().size();

        // Create the EstadoCandidatura with an existing ID
        estadoCandidatura.setId(1L);
        EstadoCandidaturaDTO estadoCandidaturaDTO = estadoCandidaturaMapper.toDto(estadoCandidatura);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadoCandidaturaMockMvc.perform(post("/api/estado-candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoCandidaturaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoCandidatura in the database
        List<EstadoCandidatura> estadoCandidaturaList = estadoCandidaturaRepository.findAll();
        assertThat(estadoCandidaturaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadoCandidaturaRepository.findAll().size();
        // set the field null
        estadoCandidatura.setNombre(null);

        // Create the EstadoCandidatura, which fails.
        EstadoCandidaturaDTO estadoCandidaturaDTO = estadoCandidaturaMapper.toDto(estadoCandidatura);


        restEstadoCandidaturaMockMvc.perform(post("/api/estado-candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoCandidaturaDTO)))
            .andExpect(status().isBadRequest());

        List<EstadoCandidatura> estadoCandidaturaList = estadoCandidaturaRepository.findAll();
        assertThat(estadoCandidaturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadoCandidaturas() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);

        // Get all the estadoCandidaturaList
        restEstadoCandidaturaMockMvc.perform(get("/api/estado-candidaturas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadoCandidatura.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    @Transactional
    public void getEstadoCandidatura() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);

        // Get the estadoCandidatura
        restEstadoCandidaturaMockMvc.perform(get("/api/estado-candidaturas/{id}", estadoCandidatura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(estadoCandidatura.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }


    @Test
    @Transactional
    public void getEstadoCandidaturasByIdFiltering() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);

        Long id = estadoCandidatura.getId();

        defaultEstadoCandidaturaShouldBeFound("id.equals=" + id);
        defaultEstadoCandidaturaShouldNotBeFound("id.notEquals=" + id);

        defaultEstadoCandidaturaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEstadoCandidaturaShouldNotBeFound("id.greaterThan=" + id);

        defaultEstadoCandidaturaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEstadoCandidaturaShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllEstadoCandidaturasByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);

        // Get all the estadoCandidaturaList where nombre equals to DEFAULT_NOMBRE
        defaultEstadoCandidaturaShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the estadoCandidaturaList where nombre equals to UPDATED_NOMBRE
        defaultEstadoCandidaturaShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllEstadoCandidaturasByNombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);

        // Get all the estadoCandidaturaList where nombre not equals to DEFAULT_NOMBRE
        defaultEstadoCandidaturaShouldNotBeFound("nombre.notEquals=" + DEFAULT_NOMBRE);

        // Get all the estadoCandidaturaList where nombre not equals to UPDATED_NOMBRE
        defaultEstadoCandidaturaShouldBeFound("nombre.notEquals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllEstadoCandidaturasByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);

        // Get all the estadoCandidaturaList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultEstadoCandidaturaShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the estadoCandidaturaList where nombre equals to UPDATED_NOMBRE
        defaultEstadoCandidaturaShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllEstadoCandidaturasByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);

        // Get all the estadoCandidaturaList where nombre is not null
        defaultEstadoCandidaturaShouldBeFound("nombre.specified=true");

        // Get all the estadoCandidaturaList where nombre is null
        defaultEstadoCandidaturaShouldNotBeFound("nombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllEstadoCandidaturasByNombreContainsSomething() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);

        // Get all the estadoCandidaturaList where nombre contains DEFAULT_NOMBRE
        defaultEstadoCandidaturaShouldBeFound("nombre.contains=" + DEFAULT_NOMBRE);

        // Get all the estadoCandidaturaList where nombre contains UPDATED_NOMBRE
        defaultEstadoCandidaturaShouldNotBeFound("nombre.contains=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllEstadoCandidaturasByNombreNotContainsSomething() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);

        // Get all the estadoCandidaturaList where nombre does not contain DEFAULT_NOMBRE
        defaultEstadoCandidaturaShouldNotBeFound("nombre.doesNotContain=" + DEFAULT_NOMBRE);

        // Get all the estadoCandidaturaList where nombre does not contain UPDATED_NOMBRE
        defaultEstadoCandidaturaShouldBeFound("nombre.doesNotContain=" + UPDATED_NOMBRE);
    }


    @Test
    @Transactional
    public void getAllEstadoCandidaturasByHistorialCandidaturaIsEqualToSomething() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);
        HistorialCandidatura historialCandidatura = HistorialCandidaturaResourceIT.createEntity(em);
        em.persist(historialCandidatura);
        em.flush();
        estadoCandidatura.addHistorialCandidatura(historialCandidatura);
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);
        Long historialCandidaturaId = historialCandidatura.getId();

        // Get all the estadoCandidaturaList where historialCandidatura equals to historialCandidaturaId
        defaultEstadoCandidaturaShouldBeFound("historialCandidaturaId.equals=" + historialCandidaturaId);

        // Get all the estadoCandidaturaList where historialCandidatura equals to historialCandidaturaId + 1
        defaultEstadoCandidaturaShouldNotBeFound("historialCandidaturaId.equals=" + (historialCandidaturaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEstadoCandidaturaShouldBeFound(String filter) throws Exception {
        restEstadoCandidaturaMockMvc.perform(get("/api/estado-candidaturas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadoCandidatura.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));

        // Check, that the count call also returns 1
        restEstadoCandidaturaMockMvc.perform(get("/api/estado-candidaturas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEstadoCandidaturaShouldNotBeFound(String filter) throws Exception {
        restEstadoCandidaturaMockMvc.perform(get("/api/estado-candidaturas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEstadoCandidaturaMockMvc.perform(get("/api/estado-candidaturas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingEstadoCandidatura() throws Exception {
        // Get the estadoCandidatura
        restEstadoCandidaturaMockMvc.perform(get("/api/estado-candidaturas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadoCandidatura() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);

        int databaseSizeBeforeUpdate = estadoCandidaturaRepository.findAll().size();

        // Update the estadoCandidatura
        EstadoCandidatura updatedEstadoCandidatura = estadoCandidaturaRepository.findById(estadoCandidatura.getId()).get();
        // Disconnect from session so that the updates on updatedEstadoCandidatura are not directly saved in db
        em.detach(updatedEstadoCandidatura);
        updatedEstadoCandidatura
            .nombre(UPDATED_NOMBRE);
        EstadoCandidaturaDTO estadoCandidaturaDTO = estadoCandidaturaMapper.toDto(updatedEstadoCandidatura);

        restEstadoCandidaturaMockMvc.perform(put("/api/estado-candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoCandidaturaDTO)))
            .andExpect(status().isOk());

        // Validate the EstadoCandidatura in the database
        List<EstadoCandidatura> estadoCandidaturaList = estadoCandidaturaRepository.findAll();
        assertThat(estadoCandidaturaList).hasSize(databaseSizeBeforeUpdate);
        EstadoCandidatura testEstadoCandidatura = estadoCandidaturaList.get(estadoCandidaturaList.size() - 1);
        assertThat(testEstadoCandidatura.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadoCandidatura() throws Exception {
        int databaseSizeBeforeUpdate = estadoCandidaturaRepository.findAll().size();

        // Create the EstadoCandidatura
        EstadoCandidaturaDTO estadoCandidaturaDTO = estadoCandidaturaMapper.toDto(estadoCandidatura);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadoCandidaturaMockMvc.perform(put("/api/estado-candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoCandidaturaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoCandidatura in the database
        List<EstadoCandidatura> estadoCandidaturaList = estadoCandidaturaRepository.findAll();
        assertThat(estadoCandidaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadoCandidatura() throws Exception {
        // Initialize the database
        estadoCandidaturaRepository.saveAndFlush(estadoCandidatura);

        int databaseSizeBeforeDelete = estadoCandidaturaRepository.findAll().size();

        // Delete the estadoCandidatura
        restEstadoCandidaturaMockMvc.perform(delete("/api/estado-candidaturas/{id}", estadoCandidatura.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EstadoCandidatura> estadoCandidaturaList = estadoCandidaturaRepository.findAll();
        assertThat(estadoCandidaturaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
