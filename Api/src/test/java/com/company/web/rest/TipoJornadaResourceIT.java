package com.company.web.rest;

import com.company.ApiApp;
import com.company.domain.TipoJornada;
import com.company.repository.TipoJornadaRepository;
import com.company.service.TipoJornadaService;
import com.company.service.dto.TipoJornadaDTO;
import com.company.service.mapper.TipoJornadaMapper;
import com.company.service.dto.TipoJornadaCriteria;
import com.company.service.TipoJornadaQueryService;

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
 * Integration tests for the {@link TipoJornadaResource} REST controller.
 */
@SpringBootTest(classes = ApiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TipoJornadaResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private TipoJornadaRepository tipoJornadaRepository;

    @Autowired
    private TipoJornadaMapper tipoJornadaMapper;

    @Autowired
    private TipoJornadaService tipoJornadaService;

    @Autowired
    private TipoJornadaQueryService tipoJornadaQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTipoJornadaMockMvc;

    private TipoJornada tipoJornada;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoJornada createEntity(EntityManager em) {
        TipoJornada tipoJornada = new TipoJornada()
            .nombre(DEFAULT_NOMBRE);
        return tipoJornada;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoJornada createUpdatedEntity(EntityManager em) {
        TipoJornada tipoJornada = new TipoJornada()
            .nombre(UPDATED_NOMBRE);
        return tipoJornada;
    }

    @BeforeEach
    public void initTest() {
        tipoJornada = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoJornada() throws Exception {
        int databaseSizeBeforeCreate = tipoJornadaRepository.findAll().size();
        // Create the TipoJornada
        TipoJornadaDTO tipoJornadaDTO = tipoJornadaMapper.toDto(tipoJornada);
        restTipoJornadaMockMvc.perform(post("/api/tipo-jornadas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoJornadaDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoJornada in the database
        List<TipoJornada> tipoJornadaList = tipoJornadaRepository.findAll();
        assertThat(tipoJornadaList).hasSize(databaseSizeBeforeCreate + 1);
        TipoJornada testTipoJornada = tipoJornadaList.get(tipoJornadaList.size() - 1);
        assertThat(testTipoJornada.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void createTipoJornadaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoJornadaRepository.findAll().size();

        // Create the TipoJornada with an existing ID
        tipoJornada.setId(1L);
        TipoJornadaDTO tipoJornadaDTO = tipoJornadaMapper.toDto(tipoJornada);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoJornadaMockMvc.perform(post("/api/tipo-jornadas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoJornadaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoJornada in the database
        List<TipoJornada> tipoJornadaList = tipoJornadaRepository.findAll();
        assertThat(tipoJornadaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoJornadaRepository.findAll().size();
        // set the field null
        tipoJornada.setNombre(null);

        // Create the TipoJornada, which fails.
        TipoJornadaDTO tipoJornadaDTO = tipoJornadaMapper.toDto(tipoJornada);


        restTipoJornadaMockMvc.perform(post("/api/tipo-jornadas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoJornadaDTO)))
            .andExpect(status().isBadRequest());

        List<TipoJornada> tipoJornadaList = tipoJornadaRepository.findAll();
        assertThat(tipoJornadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoJornadas() throws Exception {
        // Initialize the database
        tipoJornadaRepository.saveAndFlush(tipoJornada);

        // Get all the tipoJornadaList
        restTipoJornadaMockMvc.perform(get("/api/tipo-jornadas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoJornada.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    @Transactional
    public void getTipoJornada() throws Exception {
        // Initialize the database
        tipoJornadaRepository.saveAndFlush(tipoJornada);

        // Get the tipoJornada
        restTipoJornadaMockMvc.perform(get("/api/tipo-jornadas/{id}", tipoJornada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tipoJornada.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }


    @Test
    @Transactional
    public void getTipoJornadasByIdFiltering() throws Exception {
        // Initialize the database
        tipoJornadaRepository.saveAndFlush(tipoJornada);

        Long id = tipoJornada.getId();

        defaultTipoJornadaShouldBeFound("id.equals=" + id);
        defaultTipoJornadaShouldNotBeFound("id.notEquals=" + id);

        defaultTipoJornadaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTipoJornadaShouldNotBeFound("id.greaterThan=" + id);

        defaultTipoJornadaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTipoJornadaShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTipoJornadasByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        tipoJornadaRepository.saveAndFlush(tipoJornada);

        // Get all the tipoJornadaList where nombre equals to DEFAULT_NOMBRE
        defaultTipoJornadaShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the tipoJornadaList where nombre equals to UPDATED_NOMBRE
        defaultTipoJornadaShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllTipoJornadasByNombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tipoJornadaRepository.saveAndFlush(tipoJornada);

        // Get all the tipoJornadaList where nombre not equals to DEFAULT_NOMBRE
        defaultTipoJornadaShouldNotBeFound("nombre.notEquals=" + DEFAULT_NOMBRE);

        // Get all the tipoJornadaList where nombre not equals to UPDATED_NOMBRE
        defaultTipoJornadaShouldBeFound("nombre.notEquals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllTipoJornadasByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        tipoJornadaRepository.saveAndFlush(tipoJornada);

        // Get all the tipoJornadaList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultTipoJornadaShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the tipoJornadaList where nombre equals to UPDATED_NOMBRE
        defaultTipoJornadaShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllTipoJornadasByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        tipoJornadaRepository.saveAndFlush(tipoJornada);

        // Get all the tipoJornadaList where nombre is not null
        defaultTipoJornadaShouldBeFound("nombre.specified=true");

        // Get all the tipoJornadaList where nombre is null
        defaultTipoJornadaShouldNotBeFound("nombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllTipoJornadasByNombreContainsSomething() throws Exception {
        // Initialize the database
        tipoJornadaRepository.saveAndFlush(tipoJornada);

        // Get all the tipoJornadaList where nombre contains DEFAULT_NOMBRE
        defaultTipoJornadaShouldBeFound("nombre.contains=" + DEFAULT_NOMBRE);

        // Get all the tipoJornadaList where nombre contains UPDATED_NOMBRE
        defaultTipoJornadaShouldNotBeFound("nombre.contains=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllTipoJornadasByNombreNotContainsSomething() throws Exception {
        // Initialize the database
        tipoJornadaRepository.saveAndFlush(tipoJornada);

        // Get all the tipoJornadaList where nombre does not contain DEFAULT_NOMBRE
        defaultTipoJornadaShouldNotBeFound("nombre.doesNotContain=" + DEFAULT_NOMBRE);

        // Get all the tipoJornadaList where nombre does not contain UPDATED_NOMBRE
        defaultTipoJornadaShouldBeFound("nombre.doesNotContain=" + UPDATED_NOMBRE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTipoJornadaShouldBeFound(String filter) throws Exception {
        restTipoJornadaMockMvc.perform(get("/api/tipo-jornadas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoJornada.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));

        // Check, that the count call also returns 1
        restTipoJornadaMockMvc.perform(get("/api/tipo-jornadas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTipoJornadaShouldNotBeFound(String filter) throws Exception {
        restTipoJornadaMockMvc.perform(get("/api/tipo-jornadas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTipoJornadaMockMvc.perform(get("/api/tipo-jornadas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingTipoJornada() throws Exception {
        // Get the tipoJornada
        restTipoJornadaMockMvc.perform(get("/api/tipo-jornadas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoJornada() throws Exception {
        // Initialize the database
        tipoJornadaRepository.saveAndFlush(tipoJornada);

        int databaseSizeBeforeUpdate = tipoJornadaRepository.findAll().size();

        // Update the tipoJornada
        TipoJornada updatedTipoJornada = tipoJornadaRepository.findById(tipoJornada.getId()).get();
        // Disconnect from session so that the updates on updatedTipoJornada are not directly saved in db
        em.detach(updatedTipoJornada);
        updatedTipoJornada
            .nombre(UPDATED_NOMBRE);
        TipoJornadaDTO tipoJornadaDTO = tipoJornadaMapper.toDto(updatedTipoJornada);

        restTipoJornadaMockMvc.perform(put("/api/tipo-jornadas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoJornadaDTO)))
            .andExpect(status().isOk());

        // Validate the TipoJornada in the database
        List<TipoJornada> tipoJornadaList = tipoJornadaRepository.findAll();
        assertThat(tipoJornadaList).hasSize(databaseSizeBeforeUpdate);
        TipoJornada testTipoJornada = tipoJornadaList.get(tipoJornadaList.size() - 1);
        assertThat(testTipoJornada.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoJornada() throws Exception {
        int databaseSizeBeforeUpdate = tipoJornadaRepository.findAll().size();

        // Create the TipoJornada
        TipoJornadaDTO tipoJornadaDTO = tipoJornadaMapper.toDto(tipoJornada);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoJornadaMockMvc.perform(put("/api/tipo-jornadas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tipoJornadaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoJornada in the database
        List<TipoJornada> tipoJornadaList = tipoJornadaRepository.findAll();
        assertThat(tipoJornadaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoJornada() throws Exception {
        // Initialize the database
        tipoJornadaRepository.saveAndFlush(tipoJornada);

        int databaseSizeBeforeDelete = tipoJornadaRepository.findAll().size();

        // Delete the tipoJornada
        restTipoJornadaMockMvc.perform(delete("/api/tipo-jornadas/{id}", tipoJornada.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoJornada> tipoJornadaList = tipoJornadaRepository.findAll();
        assertThat(tipoJornadaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
