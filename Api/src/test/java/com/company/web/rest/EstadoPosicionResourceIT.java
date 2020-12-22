package com.company.web.rest;

import com.company.ApiApp;
import com.company.domain.EstadoPosicion;
import com.company.domain.HistorialPosicion;
import com.company.repository.EstadoPosicionRepository;
import com.company.service.EstadoPosicionService;
import com.company.service.dto.EstadoPosicionDTO;
import com.company.service.mapper.EstadoPosicionMapper;
import com.company.service.dto.EstadoPosicionCriteria;
import com.company.service.EstadoPosicionQueryService;

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
 * Integration tests for the {@link EstadoPosicionResource} REST controller.
 */
@SpringBootTest(classes = ApiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EstadoPosicionResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private EstadoPosicionRepository estadoPosicionRepository;

    @Autowired
    private EstadoPosicionMapper estadoPosicionMapper;

    @Autowired
    private EstadoPosicionService estadoPosicionService;

    @Autowired
    private EstadoPosicionQueryService estadoPosicionQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEstadoPosicionMockMvc;

    private EstadoPosicion estadoPosicion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadoPosicion createEntity(EntityManager em) {
        EstadoPosicion estadoPosicion = new EstadoPosicion()
            .nombre(DEFAULT_NOMBRE);
        return estadoPosicion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EstadoPosicion createUpdatedEntity(EntityManager em) {
        EstadoPosicion estadoPosicion = new EstadoPosicion()
            .nombre(UPDATED_NOMBRE);
        return estadoPosicion;
    }

    @BeforeEach
    public void initTest() {
        estadoPosicion = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadoPosicion() throws Exception {
        int databaseSizeBeforeCreate = estadoPosicionRepository.findAll().size();
        // Create the EstadoPosicion
        EstadoPosicionDTO estadoPosicionDTO = estadoPosicionMapper.toDto(estadoPosicion);
        restEstadoPosicionMockMvc.perform(post("/api/estado-posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoPosicionDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadoPosicion in the database
        List<EstadoPosicion> estadoPosicionList = estadoPosicionRepository.findAll();
        assertThat(estadoPosicionList).hasSize(databaseSizeBeforeCreate + 1);
        EstadoPosicion testEstadoPosicion = estadoPosicionList.get(estadoPosicionList.size() - 1);
        assertThat(testEstadoPosicion.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void createEstadoPosicionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadoPosicionRepository.findAll().size();

        // Create the EstadoPosicion with an existing ID
        estadoPosicion.setId(1L);
        EstadoPosicionDTO estadoPosicionDTO = estadoPosicionMapper.toDto(estadoPosicion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadoPosicionMockMvc.perform(post("/api/estado-posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoPosicionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoPosicion in the database
        List<EstadoPosicion> estadoPosicionList = estadoPosicionRepository.findAll();
        assertThat(estadoPosicionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadoPosicionRepository.findAll().size();
        // set the field null
        estadoPosicion.setNombre(null);

        // Create the EstadoPosicion, which fails.
        EstadoPosicionDTO estadoPosicionDTO = estadoPosicionMapper.toDto(estadoPosicion);


        restEstadoPosicionMockMvc.perform(post("/api/estado-posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoPosicionDTO)))
            .andExpect(status().isBadRequest());

        List<EstadoPosicion> estadoPosicionList = estadoPosicionRepository.findAll();
        assertThat(estadoPosicionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadoPosicions() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);

        // Get all the estadoPosicionList
        restEstadoPosicionMockMvc.perform(get("/api/estado-posicions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadoPosicion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    @Transactional
    public void getEstadoPosicion() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);

        // Get the estadoPosicion
        restEstadoPosicionMockMvc.perform(get("/api/estado-posicions/{id}", estadoPosicion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(estadoPosicion.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }


    @Test
    @Transactional
    public void getEstadoPosicionsByIdFiltering() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);

        Long id = estadoPosicion.getId();

        defaultEstadoPosicionShouldBeFound("id.equals=" + id);
        defaultEstadoPosicionShouldNotBeFound("id.notEquals=" + id);

        defaultEstadoPosicionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultEstadoPosicionShouldNotBeFound("id.greaterThan=" + id);

        defaultEstadoPosicionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultEstadoPosicionShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllEstadoPosicionsByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);

        // Get all the estadoPosicionList where nombre equals to DEFAULT_NOMBRE
        defaultEstadoPosicionShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the estadoPosicionList where nombre equals to UPDATED_NOMBRE
        defaultEstadoPosicionShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllEstadoPosicionsByNombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);

        // Get all the estadoPosicionList where nombre not equals to DEFAULT_NOMBRE
        defaultEstadoPosicionShouldNotBeFound("nombre.notEquals=" + DEFAULT_NOMBRE);

        // Get all the estadoPosicionList where nombre not equals to UPDATED_NOMBRE
        defaultEstadoPosicionShouldBeFound("nombre.notEquals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllEstadoPosicionsByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);

        // Get all the estadoPosicionList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultEstadoPosicionShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the estadoPosicionList where nombre equals to UPDATED_NOMBRE
        defaultEstadoPosicionShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllEstadoPosicionsByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);

        // Get all the estadoPosicionList where nombre is not null
        defaultEstadoPosicionShouldBeFound("nombre.specified=true");

        // Get all the estadoPosicionList where nombre is null
        defaultEstadoPosicionShouldNotBeFound("nombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllEstadoPosicionsByNombreContainsSomething() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);

        // Get all the estadoPosicionList where nombre contains DEFAULT_NOMBRE
        defaultEstadoPosicionShouldBeFound("nombre.contains=" + DEFAULT_NOMBRE);

        // Get all the estadoPosicionList where nombre contains UPDATED_NOMBRE
        defaultEstadoPosicionShouldNotBeFound("nombre.contains=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllEstadoPosicionsByNombreNotContainsSomething() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);

        // Get all the estadoPosicionList where nombre does not contain DEFAULT_NOMBRE
        defaultEstadoPosicionShouldNotBeFound("nombre.doesNotContain=" + DEFAULT_NOMBRE);

        // Get all the estadoPosicionList where nombre does not contain UPDATED_NOMBRE
        defaultEstadoPosicionShouldBeFound("nombre.doesNotContain=" + UPDATED_NOMBRE);
    }


    @Test
    @Transactional
    public void getAllEstadoPosicionsByHistorialPosicionIsEqualToSomething() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);
        HistorialPosicion historialPosicion = HistorialPosicionResourceIT.createEntity(em);
        em.persist(historialPosicion);
        em.flush();
        estadoPosicion.addHistorialPosicion(historialPosicion);
        estadoPosicionRepository.saveAndFlush(estadoPosicion);
        Long historialPosicionId = historialPosicion.getId();

        // Get all the estadoPosicionList where historialPosicion equals to historialPosicionId
        defaultEstadoPosicionShouldBeFound("historialPosicionId.equals=" + historialPosicionId);

        // Get all the estadoPosicionList where historialPosicion equals to historialPosicionId + 1
        defaultEstadoPosicionShouldNotBeFound("historialPosicionId.equals=" + (historialPosicionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultEstadoPosicionShouldBeFound(String filter) throws Exception {
        restEstadoPosicionMockMvc.perform(get("/api/estado-posicions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadoPosicion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));

        // Check, that the count call also returns 1
        restEstadoPosicionMockMvc.perform(get("/api/estado-posicions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultEstadoPosicionShouldNotBeFound(String filter) throws Exception {
        restEstadoPosicionMockMvc.perform(get("/api/estado-posicions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restEstadoPosicionMockMvc.perform(get("/api/estado-posicions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingEstadoPosicion() throws Exception {
        // Get the estadoPosicion
        restEstadoPosicionMockMvc.perform(get("/api/estado-posicions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadoPosicion() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);

        int databaseSizeBeforeUpdate = estadoPosicionRepository.findAll().size();

        // Update the estadoPosicion
        EstadoPosicion updatedEstadoPosicion = estadoPosicionRepository.findById(estadoPosicion.getId()).get();
        // Disconnect from session so that the updates on updatedEstadoPosicion are not directly saved in db
        em.detach(updatedEstadoPosicion);
        updatedEstadoPosicion
            .nombre(UPDATED_NOMBRE);
        EstadoPosicionDTO estadoPosicionDTO = estadoPosicionMapper.toDto(updatedEstadoPosicion);

        restEstadoPosicionMockMvc.perform(put("/api/estado-posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoPosicionDTO)))
            .andExpect(status().isOk());

        // Validate the EstadoPosicion in the database
        List<EstadoPosicion> estadoPosicionList = estadoPosicionRepository.findAll();
        assertThat(estadoPosicionList).hasSize(databaseSizeBeforeUpdate);
        EstadoPosicion testEstadoPosicion = estadoPosicionList.get(estadoPosicionList.size() - 1);
        assertThat(testEstadoPosicion.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadoPosicion() throws Exception {
        int databaseSizeBeforeUpdate = estadoPosicionRepository.findAll().size();

        // Create the EstadoPosicion
        EstadoPosicionDTO estadoPosicionDTO = estadoPosicionMapper.toDto(estadoPosicion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadoPosicionMockMvc.perform(put("/api/estado-posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estadoPosicionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoPosicion in the database
        List<EstadoPosicion> estadoPosicionList = estadoPosicionRepository.findAll();
        assertThat(estadoPosicionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadoPosicion() throws Exception {
        // Initialize the database
        estadoPosicionRepository.saveAndFlush(estadoPosicion);

        int databaseSizeBeforeDelete = estadoPosicionRepository.findAll().size();

        // Delete the estadoPosicion
        restEstadoPosicionMockMvc.perform(delete("/api/estado-posicions/{id}", estadoPosicion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EstadoPosicion> estadoPosicionList = estadoPosicionRepository.findAll();
        assertThat(estadoPosicionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
