package com.company.web.rest;

import com.company.ApiApp;
import com.company.domain.Candidatura;
import com.company.domain.HistorialCandidatura;
import com.company.domain.EstadoCandidatura;
import com.company.domain.Fuente;
import com.company.domain.Posicion;
import com.company.domain.Persona;
import com.company.repository.CandidaturaRepository;
import com.company.service.CandidaturaService;
import com.company.service.dto.CandidaturaDTO;
import com.company.service.mapper.CandidaturaMapper;
import com.company.service.dto.CandidaturaCriteria;
import com.company.service.CandidaturaQueryService;

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
 * Integration tests for the {@link CandidaturaResource} REST controller.
 */
@SpringBootTest(classes = ApiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CandidaturaResourceIT {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    @Autowired
    private CandidaturaMapper candidaturaMapper;

    @Autowired
    private CandidaturaService candidaturaService;

    @Autowired
    private CandidaturaQueryService candidaturaQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCandidaturaMockMvc;

    private Candidatura candidatura;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Candidatura createEntity(EntityManager em) {
        Candidatura candidatura = new Candidatura();
        return candidatura;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Candidatura createUpdatedEntity(EntityManager em) {
        Candidatura candidatura = new Candidatura();
        return candidatura;
    }

    @BeforeEach
    public void initTest() {
        candidatura = createEntity(em);
    }

    @Test
    @Transactional
    public void createCandidatura() throws Exception {
        int databaseSizeBeforeCreate = candidaturaRepository.findAll().size();
        // Create the Candidatura
        CandidaturaDTO candidaturaDTO = candidaturaMapper.toDto(candidatura);
        restCandidaturaMockMvc.perform(post("/api/candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidaturaDTO)))
            .andExpect(status().isCreated());

        // Validate the Candidatura in the database
        List<Candidatura> candidaturaList = candidaturaRepository.findAll();
        assertThat(candidaturaList).hasSize(databaseSizeBeforeCreate + 1);
        Candidatura testCandidatura = candidaturaList.get(candidaturaList.size() - 1);
    }

    @Test
    @Transactional
    public void createCandidaturaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = candidaturaRepository.findAll().size();

        // Create the Candidatura with an existing ID
        candidatura.setId(1L);
        CandidaturaDTO candidaturaDTO = candidaturaMapper.toDto(candidatura);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCandidaturaMockMvc.perform(post("/api/candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidaturaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Candidatura in the database
        List<Candidatura> candidaturaList = candidaturaRepository.findAll();
        assertThat(candidaturaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCandidaturas() throws Exception {
        // Initialize the database
        candidaturaRepository.saveAndFlush(candidatura);

        // Get all the candidaturaList
        restCandidaturaMockMvc.perform(get("/api/candidaturas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(candidatura.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getCandidatura() throws Exception {
        // Initialize the database
        candidaturaRepository.saveAndFlush(candidatura);

        // Get the candidatura
        restCandidaturaMockMvc.perform(get("/api/candidaturas/{id}", candidatura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(candidatura.getId().intValue()));
    }


    @Test
    @Transactional
    public void getCandidaturasByIdFiltering() throws Exception {
        // Initialize the database
        candidaturaRepository.saveAndFlush(candidatura);

        Long id = candidatura.getId();

        defaultCandidaturaShouldBeFound("id.equals=" + id);
        defaultCandidaturaShouldNotBeFound("id.notEquals=" + id);

        defaultCandidaturaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCandidaturaShouldNotBeFound("id.greaterThan=" + id);

        defaultCandidaturaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCandidaturaShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCandidaturasByHistorialCandidaturaIsEqualToSomething() throws Exception {
        // Initialize the database
        candidaturaRepository.saveAndFlush(candidatura);
        HistorialCandidatura historialCandidatura = HistorialCandidaturaResourceIT.createEntity(em);
        em.persist(historialCandidatura);
        em.flush();
        candidatura.addHistorialCandidatura(historialCandidatura);
        candidaturaRepository.saveAndFlush(candidatura);
        Long historialCandidaturaId = historialCandidatura.getId();

        // Get all the candidaturaList where historialCandidatura equals to historialCandidaturaId
        defaultCandidaturaShouldBeFound("historialCandidaturaId.equals=" + historialCandidaturaId);

        // Get all the candidaturaList where historialCandidatura equals to historialCandidaturaId + 1
        defaultCandidaturaShouldNotBeFound("historialCandidaturaId.equals=" + (historialCandidaturaId + 1));
    }


    @Test
    @Transactional
    public void getAllCandidaturasByEstadoCandidaturaIsEqualToSomething() throws Exception {
        // Initialize the database
        candidaturaRepository.saveAndFlush(candidatura);
        EstadoCandidatura estadoCandidatura = EstadoCandidaturaResourceIT.createEntity(em);
        em.persist(estadoCandidatura);
        em.flush();
        candidatura.setEstadoCandidatura(estadoCandidatura);
        candidaturaRepository.saveAndFlush(candidatura);
        Long estadoCandidaturaId = estadoCandidatura.getId();

        // Get all the candidaturaList where estadoCandidatura equals to estadoCandidaturaId
        defaultCandidaturaShouldBeFound("estadoCandidaturaId.equals=" + estadoCandidaturaId);

        // Get all the candidaturaList where estadoCandidatura equals to estadoCandidaturaId + 1
        defaultCandidaturaShouldNotBeFound("estadoCandidaturaId.equals=" + (estadoCandidaturaId + 1));
    }


    @Test
    @Transactional
    public void getAllCandidaturasByFuenteIsEqualToSomething() throws Exception {
        // Initialize the database
        candidaturaRepository.saveAndFlush(candidatura);
        Fuente fuente = FuenteResourceIT.createEntity(em);
        em.persist(fuente);
        em.flush();
        candidatura.setFuente(fuente);
        candidaturaRepository.saveAndFlush(candidatura);
        Long fuenteId = fuente.getId();

        // Get all the candidaturaList where fuente equals to fuenteId
        defaultCandidaturaShouldBeFound("fuenteId.equals=" + fuenteId);

        // Get all the candidaturaList where fuente equals to fuenteId + 1
        defaultCandidaturaShouldNotBeFound("fuenteId.equals=" + (fuenteId + 1));
    }


    @Test
    @Transactional
    public void getAllCandidaturasByPosicionIsEqualToSomething() throws Exception {
        // Initialize the database
        candidaturaRepository.saveAndFlush(candidatura);
        Posicion posicion = PosicionResourceIT.createEntity(em);
        em.persist(posicion);
        em.flush();
        candidatura.setPosicion(posicion);
        candidaturaRepository.saveAndFlush(candidatura);
        Long posicionId = posicion.getId();

        // Get all the candidaturaList where posicion equals to posicionId
        defaultCandidaturaShouldBeFound("posicionId.equals=" + posicionId);

        // Get all the candidaturaList where posicion equals to posicionId + 1
        defaultCandidaturaShouldNotBeFound("posicionId.equals=" + (posicionId + 1));
    }


    @Test
    @Transactional
    public void getAllCandidaturasByPersonaIsEqualToSomething() throws Exception {
        // Initialize the database
        candidaturaRepository.saveAndFlush(candidatura);
        Persona persona = PersonaResourceIT.createEntity(em);
        em.persist(persona);
        em.flush();
        candidatura.setPersona(persona);
        candidaturaRepository.saveAndFlush(candidatura);
        Long personaId = persona.getId();

        // Get all the candidaturaList where persona equals to personaId
        defaultCandidaturaShouldBeFound("personaId.equals=" + personaId);

        // Get all the candidaturaList where persona equals to personaId + 1
        defaultCandidaturaShouldNotBeFound("personaId.equals=" + (personaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCandidaturaShouldBeFound(String filter) throws Exception {
        restCandidaturaMockMvc.perform(get("/api/candidaturas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(candidatura.getId().intValue())));

        // Check, that the count call also returns 1
        restCandidaturaMockMvc.perform(get("/api/candidaturas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCandidaturaShouldNotBeFound(String filter) throws Exception {
        restCandidaturaMockMvc.perform(get("/api/candidaturas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCandidaturaMockMvc.perform(get("/api/candidaturas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingCandidatura() throws Exception {
        // Get the candidatura
        restCandidaturaMockMvc.perform(get("/api/candidaturas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCandidatura() throws Exception {
        // Initialize the database
        candidaturaRepository.saveAndFlush(candidatura);

        int databaseSizeBeforeUpdate = candidaturaRepository.findAll().size();

        // Update the candidatura
        Candidatura updatedCandidatura = candidaturaRepository.findById(candidatura.getId()).get();
        // Disconnect from session so that the updates on updatedCandidatura are not directly saved in db
        em.detach(updatedCandidatura);
        CandidaturaDTO candidaturaDTO = candidaturaMapper.toDto(updatedCandidatura);

        restCandidaturaMockMvc.perform(put("/api/candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidaturaDTO)))
            .andExpect(status().isOk());

        // Validate the Candidatura in the database
        List<Candidatura> candidaturaList = candidaturaRepository.findAll();
        assertThat(candidaturaList).hasSize(databaseSizeBeforeUpdate);
        Candidatura testCandidatura = candidaturaList.get(candidaturaList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingCandidatura() throws Exception {
        int databaseSizeBeforeUpdate = candidaturaRepository.findAll().size();

        // Create the Candidatura
        CandidaturaDTO candidaturaDTO = candidaturaMapper.toDto(candidatura);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCandidaturaMockMvc.perform(put("/api/candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidaturaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Candidatura in the database
        List<Candidatura> candidaturaList = candidaturaRepository.findAll();
        assertThat(candidaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCandidatura() throws Exception {
        // Initialize the database
        candidaturaRepository.saveAndFlush(candidatura);

        int databaseSizeBeforeDelete = candidaturaRepository.findAll().size();

        // Delete the candidatura
        restCandidaturaMockMvc.perform(delete("/api/candidaturas/{id}", candidatura.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Candidatura> candidaturaList = candidaturaRepository.findAll();
        assertThat(candidaturaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
