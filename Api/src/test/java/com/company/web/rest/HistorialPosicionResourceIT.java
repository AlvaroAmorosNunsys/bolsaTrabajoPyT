package com.company.web.rest;

import com.company.ApiApp;
import com.company.domain.HistorialPosicion;
import com.company.domain.EstadoPosicion;
import com.company.domain.Posicion;
import com.company.repository.HistorialPosicionRepository;
import com.company.service.HistorialPosicionService;
import com.company.service.dto.HistorialPosicionDTO;
import com.company.service.mapper.HistorialPosicionMapper;
import com.company.service.dto.HistorialPosicionCriteria;
import com.company.service.HistorialPosicionQueryService;

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
 * Integration tests for the {@link HistorialPosicionResource} REST controller.
 */
@SpringBootTest(classes = ApiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class HistorialPosicionResourceIT {

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
    private HistorialPosicionRepository historialPosicionRepository;

    @Autowired
    private HistorialPosicionMapper historialPosicionMapper;

    @Autowired
    private HistorialPosicionService historialPosicionService;

    @Autowired
    private HistorialPosicionQueryService historialPosicionQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHistorialPosicionMockMvc;

    private HistorialPosicion historialPosicion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistorialPosicion createEntity(EntityManager em) {
        HistorialPosicion historialPosicion = new HistorialPosicion()
            .fechaCambio(DEFAULT_FECHA_CAMBIO)
            .porDefecto(DEFAULT_POR_DEFECTO)
            .fechaModificacion(DEFAULT_FECHA_MODIFICACION)
            .nombreEditor(DEFAULT_NOMBRE_EDITOR);
        return historialPosicion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistorialPosicion createUpdatedEntity(EntityManager em) {
        HistorialPosicion historialPosicion = new HistorialPosicion()
            .fechaCambio(UPDATED_FECHA_CAMBIO)
            .porDefecto(UPDATED_POR_DEFECTO)
            .fechaModificacion(UPDATED_FECHA_MODIFICACION)
            .nombreEditor(UPDATED_NOMBRE_EDITOR);
        return historialPosicion;
    }

    @BeforeEach
    public void initTest() {
        historialPosicion = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistorialPosicion() throws Exception {
        int databaseSizeBeforeCreate = historialPosicionRepository.findAll().size();
        // Create the HistorialPosicion
        HistorialPosicionDTO historialPosicionDTO = historialPosicionMapper.toDto(historialPosicion);
        restHistorialPosicionMockMvc.perform(post("/api/historial-posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialPosicionDTO)))
            .andExpect(status().isCreated());

        // Validate the HistorialPosicion in the database
        List<HistorialPosicion> historialPosicionList = historialPosicionRepository.findAll();
        assertThat(historialPosicionList).hasSize(databaseSizeBeforeCreate + 1);
        HistorialPosicion testHistorialPosicion = historialPosicionList.get(historialPosicionList.size() - 1);
        assertThat(testHistorialPosicion.getFechaCambio()).isEqualTo(DEFAULT_FECHA_CAMBIO);
        assertThat(testHistorialPosicion.isPorDefecto()).isEqualTo(DEFAULT_POR_DEFECTO);
        assertThat(testHistorialPosicion.getFechaModificacion()).isEqualTo(DEFAULT_FECHA_MODIFICACION);
        assertThat(testHistorialPosicion.getNombreEditor()).isEqualTo(DEFAULT_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void createHistorialPosicionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = historialPosicionRepository.findAll().size();

        // Create the HistorialPosicion with an existing ID
        historialPosicion.setId(1L);
        HistorialPosicionDTO historialPosicionDTO = historialPosicionMapper.toDto(historialPosicion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistorialPosicionMockMvc.perform(post("/api/historial-posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialPosicionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistorialPosicion in the database
        List<HistorialPosicion> historialPosicionList = historialPosicionRepository.findAll();
        assertThat(historialPosicionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFechaCambioIsRequired() throws Exception {
        int databaseSizeBeforeTest = historialPosicionRepository.findAll().size();
        // set the field null
        historialPosicion.setFechaCambio(null);

        // Create the HistorialPosicion, which fails.
        HistorialPosicionDTO historialPosicionDTO = historialPosicionMapper.toDto(historialPosicion);


        restHistorialPosicionMockMvc.perform(post("/api/historial-posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialPosicionDTO)))
            .andExpect(status().isBadRequest());

        List<HistorialPosicion> historialPosicionList = historialPosicionRepository.findAll();
        assertThat(historialPosicionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPorDefectoIsRequired() throws Exception {
        int databaseSizeBeforeTest = historialPosicionRepository.findAll().size();
        // set the field null
        historialPosicion.setPorDefecto(null);

        // Create the HistorialPosicion, which fails.
        HistorialPosicionDTO historialPosicionDTO = historialPosicionMapper.toDto(historialPosicion);


        restHistorialPosicionMockMvc.perform(post("/api/historial-posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialPosicionDTO)))
            .andExpect(status().isBadRequest());

        List<HistorialPosicion> historialPosicionList = historialPosicionRepository.findAll();
        assertThat(historialPosicionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicions() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList
        restHistorialPosicionMockMvc.perform(get("/api/historial-posicions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historialPosicion.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaCambio").value(hasItem(DEFAULT_FECHA_CAMBIO.toString())))
            .andExpect(jsonPath("$.[*].porDefecto").value(hasItem(DEFAULT_POR_DEFECTO.booleanValue())))
            .andExpect(jsonPath("$.[*].fechaModificacion").value(hasItem(DEFAULT_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].nombreEditor").value(hasItem(DEFAULT_NOMBRE_EDITOR)));
    }
    
    @Test
    @Transactional
    public void getHistorialPosicion() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get the historialPosicion
        restHistorialPosicionMockMvc.perform(get("/api/historial-posicions/{id}", historialPosicion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(historialPosicion.getId().intValue()))
            .andExpect(jsonPath("$.fechaCambio").value(DEFAULT_FECHA_CAMBIO.toString()))
            .andExpect(jsonPath("$.porDefecto").value(DEFAULT_POR_DEFECTO.booleanValue()))
            .andExpect(jsonPath("$.fechaModificacion").value(DEFAULT_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.nombreEditor").value(DEFAULT_NOMBRE_EDITOR));
    }


    @Test
    @Transactional
    public void getHistorialPosicionsByIdFiltering() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        Long id = historialPosicion.getId();

        defaultHistorialPosicionShouldBeFound("id.equals=" + id);
        defaultHistorialPosicionShouldNotBeFound("id.notEquals=" + id);

        defaultHistorialPosicionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultHistorialPosicionShouldNotBeFound("id.greaterThan=" + id);

        defaultHistorialPosicionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultHistorialPosicionShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaCambioIsEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaCambio equals to DEFAULT_FECHA_CAMBIO
        defaultHistorialPosicionShouldBeFound("fechaCambio.equals=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialPosicionList where fechaCambio equals to UPDATED_FECHA_CAMBIO
        defaultHistorialPosicionShouldNotBeFound("fechaCambio.equals=" + UPDATED_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaCambioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaCambio not equals to DEFAULT_FECHA_CAMBIO
        defaultHistorialPosicionShouldNotBeFound("fechaCambio.notEquals=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialPosicionList where fechaCambio not equals to UPDATED_FECHA_CAMBIO
        defaultHistorialPosicionShouldBeFound("fechaCambio.notEquals=" + UPDATED_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaCambioIsInShouldWork() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaCambio in DEFAULT_FECHA_CAMBIO or UPDATED_FECHA_CAMBIO
        defaultHistorialPosicionShouldBeFound("fechaCambio.in=" + DEFAULT_FECHA_CAMBIO + "," + UPDATED_FECHA_CAMBIO);

        // Get all the historialPosicionList where fechaCambio equals to UPDATED_FECHA_CAMBIO
        defaultHistorialPosicionShouldNotBeFound("fechaCambio.in=" + UPDATED_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaCambioIsNullOrNotNull() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaCambio is not null
        defaultHistorialPosicionShouldBeFound("fechaCambio.specified=true");

        // Get all the historialPosicionList where fechaCambio is null
        defaultHistorialPosicionShouldNotBeFound("fechaCambio.specified=false");
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaCambioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaCambio is greater than or equal to DEFAULT_FECHA_CAMBIO
        defaultHistorialPosicionShouldBeFound("fechaCambio.greaterThanOrEqual=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialPosicionList where fechaCambio is greater than or equal to UPDATED_FECHA_CAMBIO
        defaultHistorialPosicionShouldNotBeFound("fechaCambio.greaterThanOrEqual=" + UPDATED_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaCambioIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaCambio is less than or equal to DEFAULT_FECHA_CAMBIO
        defaultHistorialPosicionShouldBeFound("fechaCambio.lessThanOrEqual=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialPosicionList where fechaCambio is less than or equal to SMALLER_FECHA_CAMBIO
        defaultHistorialPosicionShouldNotBeFound("fechaCambio.lessThanOrEqual=" + SMALLER_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaCambioIsLessThanSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaCambio is less than DEFAULT_FECHA_CAMBIO
        defaultHistorialPosicionShouldNotBeFound("fechaCambio.lessThan=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialPosicionList where fechaCambio is less than UPDATED_FECHA_CAMBIO
        defaultHistorialPosicionShouldBeFound("fechaCambio.lessThan=" + UPDATED_FECHA_CAMBIO);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaCambioIsGreaterThanSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaCambio is greater than DEFAULT_FECHA_CAMBIO
        defaultHistorialPosicionShouldNotBeFound("fechaCambio.greaterThan=" + DEFAULT_FECHA_CAMBIO);

        // Get all the historialPosicionList where fechaCambio is greater than SMALLER_FECHA_CAMBIO
        defaultHistorialPosicionShouldBeFound("fechaCambio.greaterThan=" + SMALLER_FECHA_CAMBIO);
    }


    @Test
    @Transactional
    public void getAllHistorialPosicionsByPorDefectoIsEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where porDefecto equals to DEFAULT_POR_DEFECTO
        defaultHistorialPosicionShouldBeFound("porDefecto.equals=" + DEFAULT_POR_DEFECTO);

        // Get all the historialPosicionList where porDefecto equals to UPDATED_POR_DEFECTO
        defaultHistorialPosicionShouldNotBeFound("porDefecto.equals=" + UPDATED_POR_DEFECTO);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByPorDefectoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where porDefecto not equals to DEFAULT_POR_DEFECTO
        defaultHistorialPosicionShouldNotBeFound("porDefecto.notEquals=" + DEFAULT_POR_DEFECTO);

        // Get all the historialPosicionList where porDefecto not equals to UPDATED_POR_DEFECTO
        defaultHistorialPosicionShouldBeFound("porDefecto.notEquals=" + UPDATED_POR_DEFECTO);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByPorDefectoIsInShouldWork() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where porDefecto in DEFAULT_POR_DEFECTO or UPDATED_POR_DEFECTO
        defaultHistorialPosicionShouldBeFound("porDefecto.in=" + DEFAULT_POR_DEFECTO + "," + UPDATED_POR_DEFECTO);

        // Get all the historialPosicionList where porDefecto equals to UPDATED_POR_DEFECTO
        defaultHistorialPosicionShouldNotBeFound("porDefecto.in=" + UPDATED_POR_DEFECTO);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByPorDefectoIsNullOrNotNull() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where porDefecto is not null
        defaultHistorialPosicionShouldBeFound("porDefecto.specified=true");

        // Get all the historialPosicionList where porDefecto is null
        defaultHistorialPosicionShouldNotBeFound("porDefecto.specified=false");
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaModificacionIsEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaModificacion equals to DEFAULT_FECHA_MODIFICACION
        defaultHistorialPosicionShouldBeFound("fechaModificacion.equals=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialPosicionList where fechaModificacion equals to UPDATED_FECHA_MODIFICACION
        defaultHistorialPosicionShouldNotBeFound("fechaModificacion.equals=" + UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaModificacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaModificacion not equals to DEFAULT_FECHA_MODIFICACION
        defaultHistorialPosicionShouldNotBeFound("fechaModificacion.notEquals=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialPosicionList where fechaModificacion not equals to UPDATED_FECHA_MODIFICACION
        defaultHistorialPosicionShouldBeFound("fechaModificacion.notEquals=" + UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaModificacionIsInShouldWork() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaModificacion in DEFAULT_FECHA_MODIFICACION or UPDATED_FECHA_MODIFICACION
        defaultHistorialPosicionShouldBeFound("fechaModificacion.in=" + DEFAULT_FECHA_MODIFICACION + "," + UPDATED_FECHA_MODIFICACION);

        // Get all the historialPosicionList where fechaModificacion equals to UPDATED_FECHA_MODIFICACION
        defaultHistorialPosicionShouldNotBeFound("fechaModificacion.in=" + UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaModificacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaModificacion is not null
        defaultHistorialPosicionShouldBeFound("fechaModificacion.specified=true");

        // Get all the historialPosicionList where fechaModificacion is null
        defaultHistorialPosicionShouldNotBeFound("fechaModificacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaModificacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaModificacion is greater than or equal to DEFAULT_FECHA_MODIFICACION
        defaultHistorialPosicionShouldBeFound("fechaModificacion.greaterThanOrEqual=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialPosicionList where fechaModificacion is greater than or equal to UPDATED_FECHA_MODIFICACION
        defaultHistorialPosicionShouldNotBeFound("fechaModificacion.greaterThanOrEqual=" + UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaModificacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaModificacion is less than or equal to DEFAULT_FECHA_MODIFICACION
        defaultHistorialPosicionShouldBeFound("fechaModificacion.lessThanOrEqual=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialPosicionList where fechaModificacion is less than or equal to SMALLER_FECHA_MODIFICACION
        defaultHistorialPosicionShouldNotBeFound("fechaModificacion.lessThanOrEqual=" + SMALLER_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaModificacionIsLessThanSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaModificacion is less than DEFAULT_FECHA_MODIFICACION
        defaultHistorialPosicionShouldNotBeFound("fechaModificacion.lessThan=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialPosicionList where fechaModificacion is less than UPDATED_FECHA_MODIFICACION
        defaultHistorialPosicionShouldBeFound("fechaModificacion.lessThan=" + UPDATED_FECHA_MODIFICACION);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByFechaModificacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where fechaModificacion is greater than DEFAULT_FECHA_MODIFICACION
        defaultHistorialPosicionShouldNotBeFound("fechaModificacion.greaterThan=" + DEFAULT_FECHA_MODIFICACION);

        // Get all the historialPosicionList where fechaModificacion is greater than SMALLER_FECHA_MODIFICACION
        defaultHistorialPosicionShouldBeFound("fechaModificacion.greaterThan=" + SMALLER_FECHA_MODIFICACION);
    }


    @Test
    @Transactional
    public void getAllHistorialPosicionsByNombreEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where nombreEditor equals to DEFAULT_NOMBRE_EDITOR
        defaultHistorialPosicionShouldBeFound("nombreEditor.equals=" + DEFAULT_NOMBRE_EDITOR);

        // Get all the historialPosicionList where nombreEditor equals to UPDATED_NOMBRE_EDITOR
        defaultHistorialPosicionShouldNotBeFound("nombreEditor.equals=" + UPDATED_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByNombreEditorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where nombreEditor not equals to DEFAULT_NOMBRE_EDITOR
        defaultHistorialPosicionShouldNotBeFound("nombreEditor.notEquals=" + DEFAULT_NOMBRE_EDITOR);

        // Get all the historialPosicionList where nombreEditor not equals to UPDATED_NOMBRE_EDITOR
        defaultHistorialPosicionShouldBeFound("nombreEditor.notEquals=" + UPDATED_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByNombreEditorIsInShouldWork() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where nombreEditor in DEFAULT_NOMBRE_EDITOR or UPDATED_NOMBRE_EDITOR
        defaultHistorialPosicionShouldBeFound("nombreEditor.in=" + DEFAULT_NOMBRE_EDITOR + "," + UPDATED_NOMBRE_EDITOR);

        // Get all the historialPosicionList where nombreEditor equals to UPDATED_NOMBRE_EDITOR
        defaultHistorialPosicionShouldNotBeFound("nombreEditor.in=" + UPDATED_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByNombreEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where nombreEditor is not null
        defaultHistorialPosicionShouldBeFound("nombreEditor.specified=true");

        // Get all the historialPosicionList where nombreEditor is null
        defaultHistorialPosicionShouldNotBeFound("nombreEditor.specified=false");
    }
                @Test
    @Transactional
    public void getAllHistorialPosicionsByNombreEditorContainsSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where nombreEditor contains DEFAULT_NOMBRE_EDITOR
        defaultHistorialPosicionShouldBeFound("nombreEditor.contains=" + DEFAULT_NOMBRE_EDITOR);

        // Get all the historialPosicionList where nombreEditor contains UPDATED_NOMBRE_EDITOR
        defaultHistorialPosicionShouldNotBeFound("nombreEditor.contains=" + UPDATED_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void getAllHistorialPosicionsByNombreEditorNotContainsSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        // Get all the historialPosicionList where nombreEditor does not contain DEFAULT_NOMBRE_EDITOR
        defaultHistorialPosicionShouldNotBeFound("nombreEditor.doesNotContain=" + DEFAULT_NOMBRE_EDITOR);

        // Get all the historialPosicionList where nombreEditor does not contain UPDATED_NOMBRE_EDITOR
        defaultHistorialPosicionShouldBeFound("nombreEditor.doesNotContain=" + UPDATED_NOMBRE_EDITOR);
    }


    @Test
    @Transactional
    public void getAllHistorialPosicionsByEstadoPosicionIsEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);
        EstadoPosicion estadoPosicion = EstadoPosicionResourceIT.createEntity(em);
        em.persist(estadoPosicion);
        em.flush();
        historialPosicion.setEstadoPosicion(estadoPosicion);
        historialPosicionRepository.saveAndFlush(historialPosicion);
        Long estadoPosicionId = estadoPosicion.getId();

        // Get all the historialPosicionList where estadoPosicion equals to estadoPosicionId
        defaultHistorialPosicionShouldBeFound("estadoPosicionId.equals=" + estadoPosicionId);

        // Get all the historialPosicionList where estadoPosicion equals to estadoPosicionId + 1
        defaultHistorialPosicionShouldNotBeFound("estadoPosicionId.equals=" + (estadoPosicionId + 1));
    }


    @Test
    @Transactional
    public void getAllHistorialPosicionsByPosicionIsEqualToSomething() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);
        Posicion posicion = PosicionResourceIT.createEntity(em);
        em.persist(posicion);
        em.flush();
        historialPosicion.setPosicion(posicion);
        historialPosicionRepository.saveAndFlush(historialPosicion);
        Long posicionId = posicion.getId();

        // Get all the historialPosicionList where posicion equals to posicionId
        defaultHistorialPosicionShouldBeFound("posicionId.equals=" + posicionId);

        // Get all the historialPosicionList where posicion equals to posicionId + 1
        defaultHistorialPosicionShouldNotBeFound("posicionId.equals=" + (posicionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultHistorialPosicionShouldBeFound(String filter) throws Exception {
        restHistorialPosicionMockMvc.perform(get("/api/historial-posicions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historialPosicion.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaCambio").value(hasItem(DEFAULT_FECHA_CAMBIO.toString())))
            .andExpect(jsonPath("$.[*].porDefecto").value(hasItem(DEFAULT_POR_DEFECTO.booleanValue())))
            .andExpect(jsonPath("$.[*].fechaModificacion").value(hasItem(DEFAULT_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].nombreEditor").value(hasItem(DEFAULT_NOMBRE_EDITOR)));

        // Check, that the count call also returns 1
        restHistorialPosicionMockMvc.perform(get("/api/historial-posicions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultHistorialPosicionShouldNotBeFound(String filter) throws Exception {
        restHistorialPosicionMockMvc.perform(get("/api/historial-posicions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restHistorialPosicionMockMvc.perform(get("/api/historial-posicions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingHistorialPosicion() throws Exception {
        // Get the historialPosicion
        restHistorialPosicionMockMvc.perform(get("/api/historial-posicions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistorialPosicion() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        int databaseSizeBeforeUpdate = historialPosicionRepository.findAll().size();

        // Update the historialPosicion
        HistorialPosicion updatedHistorialPosicion = historialPosicionRepository.findById(historialPosicion.getId()).get();
        // Disconnect from session so that the updates on updatedHistorialPosicion are not directly saved in db
        em.detach(updatedHistorialPosicion);
        updatedHistorialPosicion
            .fechaCambio(UPDATED_FECHA_CAMBIO)
            .porDefecto(UPDATED_POR_DEFECTO)
            .fechaModificacion(UPDATED_FECHA_MODIFICACION)
            .nombreEditor(UPDATED_NOMBRE_EDITOR);
        HistorialPosicionDTO historialPosicionDTO = historialPosicionMapper.toDto(updatedHistorialPosicion);

        restHistorialPosicionMockMvc.perform(put("/api/historial-posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialPosicionDTO)))
            .andExpect(status().isOk());

        // Validate the HistorialPosicion in the database
        List<HistorialPosicion> historialPosicionList = historialPosicionRepository.findAll();
        assertThat(historialPosicionList).hasSize(databaseSizeBeforeUpdate);
        HistorialPosicion testHistorialPosicion = historialPosicionList.get(historialPosicionList.size() - 1);
        assertThat(testHistorialPosicion.getFechaCambio()).isEqualTo(UPDATED_FECHA_CAMBIO);
        assertThat(testHistorialPosicion.isPorDefecto()).isEqualTo(UPDATED_POR_DEFECTO);
        assertThat(testHistorialPosicion.getFechaModificacion()).isEqualTo(UPDATED_FECHA_MODIFICACION);
        assertThat(testHistorialPosicion.getNombreEditor()).isEqualTo(UPDATED_NOMBRE_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingHistorialPosicion() throws Exception {
        int databaseSizeBeforeUpdate = historialPosicionRepository.findAll().size();

        // Create the HistorialPosicion
        HistorialPosicionDTO historialPosicionDTO = historialPosicionMapper.toDto(historialPosicion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistorialPosicionMockMvc.perform(put("/api/historial-posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historialPosicionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistorialPosicion in the database
        List<HistorialPosicion> historialPosicionList = historialPosicionRepository.findAll();
        assertThat(historialPosicionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHistorialPosicion() throws Exception {
        // Initialize the database
        historialPosicionRepository.saveAndFlush(historialPosicion);

        int databaseSizeBeforeDelete = historialPosicionRepository.findAll().size();

        // Delete the historialPosicion
        restHistorialPosicionMockMvc.perform(delete("/api/historial-posicions/{id}", historialPosicion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HistorialPosicion> historialPosicionList = historialPosicionRepository.findAll();
        assertThat(historialPosicionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
