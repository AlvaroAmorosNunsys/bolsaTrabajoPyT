package com.company.web.rest;

import com.company.ApiApp;
import com.company.domain.Fuente;
import com.company.repository.FuenteRepository;
import com.company.service.FuenteService;
import com.company.service.dto.FuenteDTO;
import com.company.service.mapper.FuenteMapper;
import com.company.service.dto.FuenteCriteria;
import com.company.service.FuenteQueryService;

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
 * Integration tests for the {@link FuenteResource} REST controller.
 */
@SpringBootTest(classes = ApiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FuenteResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private FuenteRepository fuenteRepository;

    @Autowired
    private FuenteMapper fuenteMapper;

    @Autowired
    private FuenteService fuenteService;

    @Autowired
    private FuenteQueryService fuenteQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFuenteMockMvc;

    private Fuente fuente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fuente createEntity(EntityManager em) {
        Fuente fuente = new Fuente()
            .nombre(DEFAULT_NOMBRE);
        return fuente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fuente createUpdatedEntity(EntityManager em) {
        Fuente fuente = new Fuente()
            .nombre(UPDATED_NOMBRE);
        return fuente;
    }

    @BeforeEach
    public void initTest() {
        fuente = createEntity(em);
    }

    @Test
    @Transactional
    public void createFuente() throws Exception {
        int databaseSizeBeforeCreate = fuenteRepository.findAll().size();
        // Create the Fuente
        FuenteDTO fuenteDTO = fuenteMapper.toDto(fuente);
        restFuenteMockMvc.perform(post("/api/fuentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteDTO)))
            .andExpect(status().isCreated());

        // Validate the Fuente in the database
        List<Fuente> fuenteList = fuenteRepository.findAll();
        assertThat(fuenteList).hasSize(databaseSizeBeforeCreate + 1);
        Fuente testFuente = fuenteList.get(fuenteList.size() - 1);
        assertThat(testFuente.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void createFuenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fuenteRepository.findAll().size();

        // Create the Fuente with an existing ID
        fuente.setId(1L);
        FuenteDTO fuenteDTO = fuenteMapper.toDto(fuente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFuenteMockMvc.perform(post("/api/fuentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fuente in the database
        List<Fuente> fuenteList = fuenteRepository.findAll();
        assertThat(fuenteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteRepository.findAll().size();
        // set the field null
        fuente.setNombre(null);

        // Create the Fuente, which fails.
        FuenteDTO fuenteDTO = fuenteMapper.toDto(fuente);


        restFuenteMockMvc.perform(post("/api/fuentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteDTO)))
            .andExpect(status().isBadRequest());

        List<Fuente> fuenteList = fuenteRepository.findAll();
        assertThat(fuenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFuentes() throws Exception {
        // Initialize the database
        fuenteRepository.saveAndFlush(fuente);

        // Get all the fuenteList
        restFuenteMockMvc.perform(get("/api/fuentes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fuente.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    @Transactional
    public void getFuente() throws Exception {
        // Initialize the database
        fuenteRepository.saveAndFlush(fuente);

        // Get the fuente
        restFuenteMockMvc.perform(get("/api/fuentes/{id}", fuente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fuente.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }


    @Test
    @Transactional
    public void getFuentesByIdFiltering() throws Exception {
        // Initialize the database
        fuenteRepository.saveAndFlush(fuente);

        Long id = fuente.getId();

        defaultFuenteShouldBeFound("id.equals=" + id);
        defaultFuenteShouldNotBeFound("id.notEquals=" + id);

        defaultFuenteShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultFuenteShouldNotBeFound("id.greaterThan=" + id);

        defaultFuenteShouldBeFound("id.lessThanOrEqual=" + id);
        defaultFuenteShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllFuentesByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        fuenteRepository.saveAndFlush(fuente);

        // Get all the fuenteList where nombre equals to DEFAULT_NOMBRE
        defaultFuenteShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the fuenteList where nombre equals to UPDATED_NOMBRE
        defaultFuenteShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllFuentesByNombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        fuenteRepository.saveAndFlush(fuente);

        // Get all the fuenteList where nombre not equals to DEFAULT_NOMBRE
        defaultFuenteShouldNotBeFound("nombre.notEquals=" + DEFAULT_NOMBRE);

        // Get all the fuenteList where nombre not equals to UPDATED_NOMBRE
        defaultFuenteShouldBeFound("nombre.notEquals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllFuentesByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        fuenteRepository.saveAndFlush(fuente);

        // Get all the fuenteList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultFuenteShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the fuenteList where nombre equals to UPDATED_NOMBRE
        defaultFuenteShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllFuentesByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        fuenteRepository.saveAndFlush(fuente);

        // Get all the fuenteList where nombre is not null
        defaultFuenteShouldBeFound("nombre.specified=true");

        // Get all the fuenteList where nombre is null
        defaultFuenteShouldNotBeFound("nombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllFuentesByNombreContainsSomething() throws Exception {
        // Initialize the database
        fuenteRepository.saveAndFlush(fuente);

        // Get all the fuenteList where nombre contains DEFAULT_NOMBRE
        defaultFuenteShouldBeFound("nombre.contains=" + DEFAULT_NOMBRE);

        // Get all the fuenteList where nombre contains UPDATED_NOMBRE
        defaultFuenteShouldNotBeFound("nombre.contains=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllFuentesByNombreNotContainsSomething() throws Exception {
        // Initialize the database
        fuenteRepository.saveAndFlush(fuente);

        // Get all the fuenteList where nombre does not contain DEFAULT_NOMBRE
        defaultFuenteShouldNotBeFound("nombre.doesNotContain=" + DEFAULT_NOMBRE);

        // Get all the fuenteList where nombre does not contain UPDATED_NOMBRE
        defaultFuenteShouldBeFound("nombre.doesNotContain=" + UPDATED_NOMBRE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFuenteShouldBeFound(String filter) throws Exception {
        restFuenteMockMvc.perform(get("/api/fuentes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fuente.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));

        // Check, that the count call also returns 1
        restFuenteMockMvc.perform(get("/api/fuentes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFuenteShouldNotBeFound(String filter) throws Exception {
        restFuenteMockMvc.perform(get("/api/fuentes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFuenteMockMvc.perform(get("/api/fuentes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingFuente() throws Exception {
        // Get the fuente
        restFuenteMockMvc.perform(get("/api/fuentes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFuente() throws Exception {
        // Initialize the database
        fuenteRepository.saveAndFlush(fuente);

        int databaseSizeBeforeUpdate = fuenteRepository.findAll().size();

        // Update the fuente
        Fuente updatedFuente = fuenteRepository.findById(fuente.getId()).get();
        // Disconnect from session so that the updates on updatedFuente are not directly saved in db
        em.detach(updatedFuente);
        updatedFuente
            .nombre(UPDATED_NOMBRE);
        FuenteDTO fuenteDTO = fuenteMapper.toDto(updatedFuente);

        restFuenteMockMvc.perform(put("/api/fuentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteDTO)))
            .andExpect(status().isOk());

        // Validate the Fuente in the database
        List<Fuente> fuenteList = fuenteRepository.findAll();
        assertThat(fuenteList).hasSize(databaseSizeBeforeUpdate);
        Fuente testFuente = fuenteList.get(fuenteList.size() - 1);
        assertThat(testFuente.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingFuente() throws Exception {
        int databaseSizeBeforeUpdate = fuenteRepository.findAll().size();

        // Create the Fuente
        FuenteDTO fuenteDTO = fuenteMapper.toDto(fuente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFuenteMockMvc.perform(put("/api/fuentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fuente in the database
        List<Fuente> fuenteList = fuenteRepository.findAll();
        assertThat(fuenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFuente() throws Exception {
        // Initialize the database
        fuenteRepository.saveAndFlush(fuente);

        int databaseSizeBeforeDelete = fuenteRepository.findAll().size();

        // Delete the fuente
        restFuenteMockMvc.perform(delete("/api/fuentes/{id}", fuente.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fuente> fuenteList = fuenteRepository.findAll();
        assertThat(fuenteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
