package com.company.web.rest;

import com.company.ApiApp;
import com.company.domain.HistorialCandidatura;
import com.company.domain.Posicion;
import com.company.domain.Candidatura;
import com.company.domain.EstadoCandidatura;
import com.company.repository.HistorialCandidaturaRepository;
import com.company.service.HistorialCandidaturaService;
import com.company.service.dto.HistorialCandidaturaDTO;
import com.company.service.mapper.HistorialCandidaturaMapper;
import com.company.service.dto.HistorialCandidaturaCriteria;
import com.company.service.HistorialCandidaturaQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link HistorialCandidaturaResource} REST controller.
 */
@SpringBootTest(classes = ApiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class HistorialCandidaturaResourceIT {

    private static final LocalDate DEFAULT_FECHA_CAMBIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_CAMBIO = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA_CAMBIO = LocalDate.ofEpochDay(-1L);

    private static final Boolean DEFAULT_POR_DEFECTO = false;
    private static final Boolean UPDATED_POR_DEFECTO = true;

    private static final LocalDate DEFAULT_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA_MODIFICACION = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_NOMBRE_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_EDITOR = "BBBBBBBBBB";

    @Autowired
    private HistorialCandidaturaRepository historialCandidaturaRepository;

    @Autowired
    private HistorialCandidaturaMapper historialCandidaturaMapper;

    @Autowired
    private HistorialCandidaturaService historialCandidaturaService;

    @Autowired
    private HistorialCandidaturaQueryService historialCandidaturaQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHistorialCandidaturaMockMvc;

    private HistorialCandidatura historialCandidatura;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistorialCandidatura createEntity(EntityManager em) {
        HistorialCandidatura historialCandidatura = new HistorialCandidatura()
            .fechaCambio(DEFAULT_FECHA_CAMBIO)
            .porDefecto(DEFAULT_POR_DEFECTO)
            .fechaModificacion(DEFAULT_FECHA_MODIFICACION)
            .nombreEditor(DEFAULT_NOMBRE_EDITOR);
        return historialCandidatura;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistorialCandidatura createUpdatedEntity(EntityManager em) {
        HistorialCandidatura historialCandidatura = new HistorialCandidatura()
            .fechaCambio(UPDATED_FECHA_CAMBIO)
            .porDefecto(UPDATED_POR_DEFECTO)
            .fechaModificacion(UPDATED_FECHA_MODIFICACION)
            .nombreEditor(UPDATED_NOMBRE_EDITOR);
        return historialCandidatura;
    }

    @BeforeEach
    public void initTest() {
        historialCandidatura = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistorialCandidatura() throws Exception {
        int databaseSizeBeforeCreate = historialCandidaturaRepository.findAll().size();
        // Create the HistorialCandidatura
        HistorialCandidaturaDTO historialCandidaturaDTO = historialCandidaturaMapper.toDto(historialCandidatura);
        restHistorialCandidaturaMockMvc.perform(post("/api/historial-candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialCandidaturaDTO)))
            .andExpect(status().isCreated());

        // Validate the HistorialCandidatura in the database
        List<HistorialCandidatura> historialCandidaturaList = historialCandidaturaRepository.findAll();
        assertThat(historialCandidaturaList).hasSize(databaseSizeBeforeCreate + 1);
        HistorialCandidatura testHistorialCandidatura = historialCandidaturaList.get(historialCandidaturaList.size() - 1);
        assertThat(testHistorialCandidatura.getFechaCambio()).isEqualTo(DEFAULT_FECHA_CAMBIO);
        assertThat(testHistorialCandidatura.isPorDefecto()).isEqualTo(DEFAULT_POR_DEFECTO);
        assertThat(testHistorialCandidatura.getFechaModificacion()).isEqualTo(DEFAULT_FECHA_MODIFICACION);
        assertThat(testHistorialCandidatura.getNombreEditor()).isEqualTo(DEFAULT_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void createHistorialCandidaturaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = historialCandidaturaRepository.findAll().size();

        // Create the HistorialCandidatura with an existing ID
        historialCandidatura.setId(1L);
        HistorialCandidaturaDTO historialCandidaturaDTO = historialCandidaturaMapper.toDto(historialCandidatura);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistorialCandidaturaMockMvc.perform(post("/api/historial-candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialCandidaturaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistorialCandidatura in the database
        List<HistorialCandidatura> historialCandidaturaList = historialCandidaturaRepository.findAll();
        assertThat(historialCandidaturaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFechaCambioIsRequired() throws Exception {
        int databaseSizeBeforeTest = historialCandidaturaRepository.findAll().size();
        // set the field null
        historialCandidatura.setFechaCambio(null);

        // Create the HistorialCandidatura, which fails.
        HistorialCandidaturaDTO historialCandidaturaDTO = historialCandidaturaMapper.toDto(historialCandidatura);


        restHistorialCandidaturaMockMvc.perform(post("/api/historial-candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialCandidaturaDTO)))
            .andExpect(status().isBadRequest());

        List<HistorialCandidatura> historialCandidaturaList = historialCandidaturaRepository.findAll();
        assertThat(historialCandidaturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPorDefectoIsRequired() throws Exception {
        int databaseSizeBeforeTest = historialCandidaturaRepository.findAll().size();
        // set the field null
        historialCandidatura.setPorDefecto(null);

        // Create the HistorialCandidatura, which fails.
        HistorialCandidaturaDTO historialCandidaturaDTO = historialCandidaturaMapper.toDto(historialCandidatura);


        restHistorialCandidaturaMockMvc.perform(post("/api/historial-candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialCandidaturaDTO)))
            .andExpect(status().isBadRequest());

        List<HistorialCandidatura> historialCandidaturaList = historialCandidaturaRepository.findAll();
        assertThat(historialCandidaturaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturas() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList
        restHistorialCandidaturaMockMvc.perform(get("/api/historial-candidaturas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historialCandidatura.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaCambio").value(hasItem(DEFAULT_FECHA_CAMBIO.toString())))
            .andExpect(jsonPath("$.[*].porDefecto").value(hasItem(DEFAULT_POR_DEFECTO.booleanValue())))
            .andExpect(jsonPath("$.[*].fechaModificacion").value(hasItem(DEFAULT_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].nombreEditor").value(hasItem(DEFAULT_NOMBRE_EDITOR)));
    }
    
    @Test
    @Transactional
    public void getHistorialCandidatura() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get the historialCandidatura
        restHistorialCandidaturaMockMvc.perform(get("/api/historial-candidaturas/{id}", historialCandidatura.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(historialCandidatura.getId().intValue()))
            .andExpect(jsonPath("$.fechaCambio").value(DEFAULT_FECHA_CAMBIO.toString()))
            .andExpect(jsonPath("$.porDefecto").value(DEFAULT_POR_DEFECTO.booleanValue()))
            .andExpect(jsonPath("$.fechaModificacion").value(DEFAULT_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.nombreEditor").value(DEFAULT_NOMBRE_EDITOR));
    }


    @Test
    @Transactional
    public void getHistorialCandidaturasByIdFiltering() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        Long id = historialCandidatura.getId();

        defaultHistorialCandidaturaShouldBeFound("id.equals=" + id);
        defaultHistorialCandidaturaShouldNotBeFound("id.notEquals=" + id);

        defaultHistorialCandidaturaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultHistorialCandidaturaShouldNotBeFound("id.greaterThan=" + id);

        defaultHistorialCandidaturaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultHistorialCandidaturaShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaCambioIsEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaCambio equals to DEFAULT_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldBeFound("fechaCambio.equals=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialCandidaturaList where fechaCambio equals to UPDATED_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldNotBeFound("fechaCambio.equals=" + UPDATED_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaCambioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaCambio not equals to DEFAULT_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldNotBeFound("fechaCambio.notEquals=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialCandidaturaList where fechaCambio not equals to UPDATED_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldBeFound("fechaCambio.notEquals=" + UPDATED_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaCambioIsInShouldWork() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaCambio in DEFAULT_FECHA_CAMBIO or UPDATED_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldBeFound("fechaCambio.in=" + DEFAULT_FECHA_CAMBIO + "," + UPDATED_FECHA_CAMBIO);

        // Get all the historialCandidaturaList where fechaCambio equals to UPDATED_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldNotBeFound("fechaCambio.in=" + UPDATED_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaCambioIsNullOrNotNull() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaCambio is not null
        defaultHistorialCandidaturaShouldBeFound("fechaCambio.specified=true");

        // Get all the historialCandidaturaList where fechaCambio is null
        defaultHistorialCandidaturaShouldNotBeFound("fechaCambio.specified=false");
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaCambioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaCambio is greater than or equal to DEFAULT_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldBeFound("fechaCambio.greaterThanOrEqual=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialCandidaturaList where fechaCambio is greater than or equal to UPDATED_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldNotBeFound("fechaCambio.greaterThanOrEqual=" + UPDATED_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaCambioIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaCambio is less than or equal to DEFAULT_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldBeFound("fechaCambio.lessThanOrEqual=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialCandidaturaList where fechaCambio is less than or equal to SMALLER_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldNotBeFound("fechaCambio.lessThanOrEqual=" + SMALLER_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaCambioIsLessThanSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaCambio is less than DEFAULT_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldNotBeFound("fechaCambio.lessThan=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialCandidaturaList where fechaCambio is less than UPDATED_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldBeFound("fechaCambio.lessThan=" + UPDATED_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaCambioIsGreaterThanSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaCambio is greater than DEFAULT_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldNotBeFound("fechaCambio.greaterThan=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialCandidaturaList where fechaCambio is greater than SMALLER_FECHA_CAMBIO
        defaultHistorialCandidaturaShouldBeFound("fechaCambio.greaterThan=" + SMALLER_FECHA_CAMBIO);
    }


    @Test
    @Transactional
    public void getAllHistorialCandidaturasByPorDefectoIsEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where porDefecto equals to DEFAULT_POR_DEFECTO
        defaultHistorialCandidaturaShouldBeFound("porDefecto.equals=" + DEFAULT_POR_DEFECTO);

        // Get all the historialCandidaturaList where porDefecto equals to UPDATED_POR_DEFECTO
        defaultHistorialCandidaturaShouldNotBeFound("porDefecto.equals=" + UPDATED_POR_DEFECTO);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByPorDefectoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where porDefecto not equals to DEFAULT_POR_DEFECTO
        defaultHistorialCandidaturaShouldNotBeFound("porDefecto.notEquals=" + DEFAULT_POR_DEFECTO);

        // Get all the historialCandidaturaList where porDefecto not equals to UPDATED_POR_DEFECTO
        defaultHistorialCandidaturaShouldBeFound("porDefecto.notEquals=" + UPDATED_POR_DEFECTO);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByPorDefectoIsInShouldWork() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where porDefecto in DEFAULT_POR_DEFECTO or UPDATED_POR_DEFECTO
        defaultHistorialCandidaturaShouldBeFound("porDefecto.in=" + DEFAULT_POR_DEFECTO + "," + UPDATED_POR_DEFECTO);

        // Get all the historialCandidaturaList where porDefecto equals to UPDATED_POR_DEFECTO
        defaultHistorialCandidaturaShouldNotBeFound("porDefecto.in=" + UPDATED_POR_DEFECTO);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByPorDefectoIsNullOrNotNull() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where porDefecto is not null
        defaultHistorialCandidaturaShouldBeFound("porDefecto.specified=true");

        // Get all the historialCandidaturaList where porDefecto is null
        defaultHistorialCandidaturaShouldNotBeFound("porDefecto.specified=false");
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaModificacionIsEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaModificacion equals to DEFAULT_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldBeFound("fechaModificacion.equals=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialCandidaturaList where fechaModificacion equals to UPDATED_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldNotBeFound("fechaModificacion.equals=" + UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaModificacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaModificacion not equals to DEFAULT_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldNotBeFound("fechaModificacion.notEquals=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialCandidaturaList where fechaModificacion not equals to UPDATED_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldBeFound("fechaModificacion.notEquals=" + UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaModificacionIsInShouldWork() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaModificacion in DEFAULT_FECHA_MODIFICACION or UPDATED_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldBeFound("fechaModificacion.in=" + DEFAULT_FECHA_MODIFICACION + "," + UPDATED_FECHA_MODIFICACION);

        // Get all the historialCandidaturaList where fechaModificacion equals to UPDATED_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldNotBeFound("fechaModificacion.in=" + UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaModificacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaModificacion is not null
        defaultHistorialCandidaturaShouldBeFound("fechaModificacion.specified=true");

        // Get all the historialCandidaturaList where fechaModificacion is null
        defaultHistorialCandidaturaShouldNotBeFound("fechaModificacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaModificacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaModificacion is greater than or equal to DEFAULT_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldBeFound("fechaModificacion.greaterThanOrEqual=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialCandidaturaList where fechaModificacion is greater than or equal to UPDATED_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldNotBeFound("fechaModificacion.greaterThanOrEqual=" + UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaModificacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaModificacion is less than or equal to DEFAULT_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldBeFound("fechaModificacion.lessThanOrEqual=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialCandidaturaList where fechaModificacion is less than or equal to SMALLER_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldNotBeFound("fechaModificacion.lessThanOrEqual=" + SMALLER_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaModificacionIsLessThanSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaModificacion is less than DEFAULT_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldNotBeFound("fechaModificacion.lessThan=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialCandidaturaList where fechaModificacion is less than UPDATED_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldBeFound("fechaModificacion.lessThan=" + UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByFechaModificacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where fechaModificacion is greater than DEFAULT_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldNotBeFound("fechaModificacion.greaterThan=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialCandidaturaList where fechaModificacion is greater than SMALLER_FECHA_MODIFICACION
        defaultHistorialCandidaturaShouldBeFound("fechaModificacion.greaterThan=" + SMALLER_FECHA_MODIFICACION);
    }


    @Test
    @Transactional
    public void getAllHistorialCandidaturasByNombreEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where nombreEditor equals to DEFAULT_NOMBRE_EDITOR
        defaultHistorialCandidaturaShouldBeFound("nombreEditor.equals=" + DEFAULT_NOMBRE_EDITOR);

        // Get all the historialCandidaturaList where nombreEditor equals to UPDATED_NOMBRE_EDITOR
        defaultHistorialCandidaturaShouldNotBeFound("nombreEditor.equals=" + UPDATED_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByNombreEditorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where nombreEditor not equals to DEFAULT_NOMBRE_EDITOR
        defaultHistorialCandidaturaShouldNotBeFound("nombreEditor.notEquals=" + DEFAULT_NOMBRE_EDITOR);

        // Get all the historialCandidaturaList where nombreEditor not equals to UPDATED_NOMBRE_EDITOR
        defaultHistorialCandidaturaShouldBeFound("nombreEditor.notEquals=" + UPDATED_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByNombreEditorIsInShouldWork() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where nombreEditor in DEFAULT_NOMBRE_EDITOR or UPDATED_NOMBRE_EDITOR
        defaultHistorialCandidaturaShouldBeFound("nombreEditor.in=" + DEFAULT_NOMBRE_EDITOR + "," + UPDATED_NOMBRE_EDITOR);

        // Get all the historialCandidaturaList where nombreEditor equals to UPDATED_NOMBRE_EDITOR
        defaultHistorialCandidaturaShouldNotBeFound("nombreEditor.in=" + UPDATED_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByNombreEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where nombreEditor is not null
        defaultHistorialCandidaturaShouldBeFound("nombreEditor.specified=true");

        // Get all the historialCandidaturaList where nombreEditor is null
        defaultHistorialCandidaturaShouldNotBeFound("nombreEditor.specified=false");
    }
                @Test
    @Transactional
    public void getAllHistorialCandidaturasByNombreEditorContainsSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where nombreEditor contains DEFAULT_NOMBRE_EDITOR
        defaultHistorialCandidaturaShouldBeFound("nombreEditor.contains=" + DEFAULT_NOMBRE_EDITOR);

        // Get all the historialCandidaturaList where nombreEditor contains UPDATED_NOMBRE_EDITOR
        defaultHistorialCandidaturaShouldNotBeFound("nombreEditor.contains=" + UPDATED_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void getAllHistorialCandidaturasByNombreEditorNotContainsSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        // Get all the historialCandidaturaList where nombreEditor does not contain DEFAULT_NOMBRE_EDITOR
        defaultHistorialCandidaturaShouldNotBeFound("nombreEditor.doesNotContain=" + DEFAULT_NOMBRE_EDITOR);

        // Get all the historialCandidaturaList where nombreEditor does not contain UPDATED_NOMBRE_EDITOR
        defaultHistorialCandidaturaShouldBeFound("nombreEditor.doesNotContain=" + UPDATED_NOMBRE_EDITOR);
    }


    @Test
    @Transactional
    public void getAllHistorialCandidaturasByPosicionIsEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);
        Posicion posicion = PosicionResourceIT.createEntity(em);
        em.persist(posicion);
        em.flush();
        historialCandidatura.setPosicion(posicion);
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);
        Long posicionId = posicion.getId();

        // Get all the historialCandidaturaList where posicion equals to posicionId
        defaultHistorialCandidaturaShouldBeFound("posicionId.equals=" + posicionId);

        // Get all the historialCandidaturaList where posicion equals to posicionId + 1
        defaultHistorialCandidaturaShouldNotBeFound("posicionId.equals=" + (posicionId + 1));
    }


    @Test
    @Transactional
    public void getAllHistorialCandidaturasByCandidaturaIsEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);
        Candidatura candidatura = CandidaturaResourceIT.createEntity(em);
        em.persist(candidatura);
        em.flush();
        historialCandidatura.setCandidatura(candidatura);
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);
        Long candidaturaId = candidatura.getId();

        // Get all the historialCandidaturaList where candidatura equals to candidaturaId
        defaultHistorialCandidaturaShouldBeFound("candidaturaId.equals=" + candidaturaId);

        // Get all the historialCandidaturaList where candidatura equals to candidaturaId + 1
        defaultHistorialCandidaturaShouldNotBeFound("candidaturaId.equals=" + (candidaturaId + 1));
    }


    @Test
    @Transactional
    public void getAllHistorialCandidaturasByEstadoCandidaturaIsEqualToSomething() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);
        EstadoCandidatura estadoCandidatura = EstadoCandidaturaResourceIT.createEntity(em);
        em.persist(estadoCandidatura);
        em.flush();
        historialCandidatura.setEstadoCandidatura(estadoCandidatura);
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);
        Long estadoCandidaturaId = estadoCandidatura.getId();

        // Get all the historialCandidaturaList where estadoCandidatura equals to estadoCandidaturaId
        defaultHistorialCandidaturaShouldBeFound("estadoCandidaturaId.equals=" + estadoCandidaturaId);

        // Get all the historialCandidaturaList where estadoCandidatura equals to estadoCandidaturaId + 1
        defaultHistorialCandidaturaShouldNotBeFound("estadoCandidaturaId.equals=" + (estadoCandidaturaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultHistorialCandidaturaShouldBeFound(String filter) throws Exception {
        restHistorialCandidaturaMockMvc.perform(get("/api/historial-candidaturas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historialCandidatura.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaCambio").value(hasItem(DEFAULT_FECHA_CAMBIO.toString())))
            .andExpect(jsonPath("$.[*].porDefecto").value(hasItem(DEFAULT_POR_DEFECTO.booleanValue())))
            .andExpect(jsonPath("$.[*].fechaModificacion").value(hasItem(DEFAULT_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].nombreEditor").value(hasItem(DEFAULT_NOMBRE_EDITOR)));

        // Check, that the count call also returns 1
        restHistorialCandidaturaMockMvc.perform(get("/api/historial-candidaturas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultHistorialCandidaturaShouldNotBeFound(String filter) throws Exception {
        restHistorialCandidaturaMockMvc.perform(get("/api/historial-candidaturas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restHistorialCandidaturaMockMvc.perform(get("/api/historial-candidaturas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingHistorialCandidatura() throws Exception {
        // Get the historialCandidatura
        restHistorialCandidaturaMockMvc.perform(get("/api/historial-candidaturas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistorialCandidatura() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        int databaseSizeBeforeUpdate = historialCandidaturaRepository.findAll().size();

        // Update the historialCandidatura
        HistorialCandidatura updatedHistorialCandidatura = historialCandidaturaRepository.findById(historialCandidatura.getId()).get();
        // Disconnect from session so that the updates on updatedHistorialCandidatura are not directly saved in db
        em.detach(updatedHistorialCandidatura);
        updatedHistorialCandidatura
            .fechaCambio(UPDATED_FECHA_CAMBIO)
            .porDefecto(UPDATED_POR_DEFECTO)
            .fechaModificacion(UPDATED_FECHA_MODIFICACION)
            .nombreEditor(UPDATED_NOMBRE_EDITOR);
        HistorialCandidaturaDTO historialCandidaturaDTO = historialCandidaturaMapper.toDto(updatedHistorialCandidatura);

        restHistorialCandidaturaMockMvc.perform(put("/api/historial-candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialCandidaturaDTO)))
            .andExpect(status().isOk());

        // Validate the HistorialCandidatura in the database
        List<HistorialCandidatura> historialCandidaturaList = historialCandidaturaRepository.findAll();
        assertThat(historialCandidaturaList).hasSize(databaseSizeBeforeUpdate);
        HistorialCandidatura testHistorialCandidatura = historialCandidaturaList.get(historialCandidaturaList.size() - 1);
        assertThat(testHistorialCandidatura.getFechaCambio()).isEqualTo(UPDATED_FECHA_CAMBIO);
        assertThat(testHistorialCandidatura.isPorDefecto()).isEqualTo(UPDATED_POR_DEFECTO);
        assertThat(testHistorialCandidatura.getFechaModificacion()).isEqualTo(UPDATED_FECHA_MODIFICACION);
        assertThat(testHistorialCandidatura.getNombreEditor()).isEqualTo(UPDATED_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingHistorialCandidatura() throws Exception {
        int databaseSizeBeforeUpdate = historialCandidaturaRepository.findAll().size();

        // Create the HistorialCandidatura
        HistorialCandidaturaDTO historialCandidaturaDTO = historialCandidaturaMapper.toDto(historialCandidatura);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistorialCandidaturaMockMvc.perform(put("/api/historial-candidaturas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialCandidaturaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistorialCandidatura in the database
        List<HistorialCandidatura> historialCandidaturaList = historialCandidaturaRepository.findAll();
        assertThat(historialCandidaturaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHistorialCandidatura() throws Exception {
        // Initialize the database
        historialCandidaturaRepository.saveAndFlush(historialCandidatura);

        int databaseSizeBeforeDelete = historialCandidaturaRepository.findAll().size();

        // Delete the historialCandidatura
        restHistorialCandidaturaMockMvc.perform(delete("/api/historial-candidaturas/{id}", historialCandidatura.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HistorialCandidatura> historialCandidaturaList = historialCandidaturaRepository.findAll();
        assertThat(historialCandidaturaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
