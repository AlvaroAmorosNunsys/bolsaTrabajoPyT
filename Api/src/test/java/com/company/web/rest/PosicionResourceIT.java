package com.company.web.rest;

import com.company.ApiApp;
import com.company.domain.Posicion;
import com.company.domain.Candidatura;
import com.company.domain.HistorialCandidatura;
import com.company.domain.HistorialPosicion;
import com.company.domain.EstadoPosicion;
import com.company.domain.TipoJornada;
import com.company.domain.UnidadDeNegocio;
import com.company.repository.PosicionRepository;
import com.company.service.PosicionService;
import com.company.service.dto.PosicionDTO;
import com.company.service.mapper.PosicionMapper;
import com.company.service.dto.PosicionCriteria;
import com.company.service.PosicionQueryService;

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
 * Integration tests for the {@link PosicionResource} REST controller.
 */
@SpringBootTest(classes = ApiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PosicionResourceIT {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_PUESTOS = 1;
    private static final Integer UPDATED_NUMERO_PUESTOS = 2;
    private static final Integer SMALLER_NUMERO_PUESTOS = 1 - 1;

    private static final Long DEFAULT_SALARIO_MINIMO = 1L;
    private static final Long UPDATED_SALARIO_MINIMO = 2L;
    private static final Long SMALLER_SALARIO_MINIMO = 1L - 1L;

    private static final Long DEFAULT_SALARIO_MAXIMO = 1L;
    private static final Long UPDATED_SALARIO_MAXIMO = 2L;
    private static final Long SMALLER_SALARIO_MAXIMO = 1L - 1L;

    private static final LocalDate DEFAULT_FECHA_ALTA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_ALTA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA_ALTA = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_FECHA_NECESIDAD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_NECESIDAD = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_FECHA_NECESIDAD = LocalDate.ofEpochDay(-1L);

    @Autowired
    private PosicionRepository posicionRepository;

    @Autowired
    private PosicionMapper posicionMapper;

    @Autowired
    private PosicionService posicionService;

    @Autowired
    private PosicionQueryService posicionQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPosicionMockMvc;

    private Posicion posicion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Posicion createEntity(EntityManager em) {
        Posicion posicion = new Posicion()
            .titulo(DEFAULT_TITULO)
            .descripcion(DEFAULT_DESCRIPCION)
            .numeroPuestos(DEFAULT_NUMERO_PUESTOS)
            .salarioMinimo(DEFAULT_SALARIO_MINIMO)
            .salarioMaximo(DEFAULT_SALARIO_MAXIMO)
            .fechaAlta(DEFAULT_FECHA_ALTA)
            .fechaNecesidad(DEFAULT_FECHA_NECESIDAD);
        return posicion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Posicion createUpdatedEntity(EntityManager em) {
        Posicion posicion = new Posicion()
            .titulo(UPDATED_TITULO)
            .descripcion(UPDATED_DESCRIPCION)
            .numeroPuestos(UPDATED_NUMERO_PUESTOS)
            .salarioMinimo(UPDATED_SALARIO_MINIMO)
            .salarioMaximo(UPDATED_SALARIO_MAXIMO)
            .fechaAlta(UPDATED_FECHA_ALTA)
            .fechaNecesidad(UPDATED_FECHA_NECESIDAD);
        return posicion;
    }

    @BeforeEach
    public void initTest() {
        posicion = createEntity(em);
    }

    @Test
    @Transactional
    public void createPosicion() throws Exception {
        int databaseSizeBeforeCreate = posicionRepository.findAll().size();
        // Create the Posicion
        PosicionDTO posicionDTO = posicionMapper.toDto(posicion);
        restPosicionMockMvc.perform(post("/api/posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posicionDTO)))
            .andExpect(status().isCreated());

        // Validate the Posicion in the database
        List<Posicion> posicionList = posicionRepository.findAll();
        assertThat(posicionList).hasSize(databaseSizeBeforeCreate + 1);
        Posicion testPosicion = posicionList.get(posicionList.size() - 1);
        assertThat(testPosicion.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testPosicion.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testPosicion.getNumeroPuestos()).isEqualTo(DEFAULT_NUMERO_PUESTOS);
        assertThat(testPosicion.getSalarioMinimo()).isEqualTo(DEFAULT_SALARIO_MINIMO);
        assertThat(testPosicion.getSalarioMaximo()).isEqualTo(DEFAULT_SALARIO_MAXIMO);
        assertThat(testPosicion.getFechaAlta()).isEqualTo(DEFAULT_FECHA_ALTA);
        assertThat(testPosicion.getFechaNecesidad()).isEqualTo(DEFAULT_FECHA_NECESIDAD);
    }

    @Test
    @Transactional
    public void createPosicionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = posicionRepository.findAll().size();

        // Create the Posicion with an existing ID
        posicion.setId(1L);
        PosicionDTO posicionDTO = posicionMapper.toDto(posicion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPosicionMockMvc.perform(post("/api/posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posicionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Posicion in the database
        List<Posicion> posicionList = posicionRepository.findAll();
        assertThat(posicionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTituloIsRequired() throws Exception {
        int databaseSizeBeforeTest = posicionRepository.findAll().size();
        // set the field null
        posicion.setTitulo(null);

        // Create the Posicion, which fails.
        PosicionDTO posicionDTO = posicionMapper.toDto(posicion);


        restPosicionMockMvc.perform(post("/api/posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posicionDTO)))
            .andExpect(status().isBadRequest());

        List<Posicion> posicionList = posicionRepository.findAll();
        assertThat(posicionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = posicionRepository.findAll().size();
        // set the field null
        posicion.setDescripcion(null);

        // Create the Posicion, which fails.
        PosicionDTO posicionDTO = posicionMapper.toDto(posicion);


        restPosicionMockMvc.perform(post("/api/posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posicionDTO)))
            .andExpect(status().isBadRequest());

        List<Posicion> posicionList = posicionRepository.findAll();
        assertThat(posicionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroPuestosIsRequired() throws Exception {
        int databaseSizeBeforeTest = posicionRepository.findAll().size();
        // set the field null
        posicion.setNumeroPuestos(null);

        // Create the Posicion, which fails.
        PosicionDTO posicionDTO = posicionMapper.toDto(posicion);


        restPosicionMockMvc.perform(post("/api/posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posicionDTO)))
            .andExpect(status().isBadRequest());

        List<Posicion> posicionList = posicionRepository.findAll();
        assertThat(posicionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaNecesidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = posicionRepository.findAll().size();
        // set the field null
        posicion.setFechaNecesidad(null);

        // Create the Posicion, which fails.
        PosicionDTO posicionDTO = posicionMapper.toDto(posicion);


        restPosicionMockMvc.perform(post("/api/posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posicionDTO)))
            .andExpect(status().isBadRequest());

        List<Posicion> posicionList = posicionRepository.findAll();
        assertThat(posicionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPosicions() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList
        restPosicionMockMvc.perform(get("/api/posicions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(posicion.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].numeroPuestos").value(hasItem(DEFAULT_NUMERO_PUESTOS)))
            .andExpect(jsonPath("$.[*].salarioMinimo").value(hasItem(DEFAULT_SALARIO_MINIMO.intValue())))
            .andExpect(jsonPath("$.[*].salarioMaximo").value(hasItem(DEFAULT_SALARIO_MAXIMO.intValue())))
            .andExpect(jsonPath("$.[*].fechaAlta").value(hasItem(DEFAULT_FECHA_ALTA.toString())))
            .andExpect(jsonPath("$.[*].fechaNecesidad").value(hasItem(DEFAULT_FECHA_NECESIDAD.toString())));
    }
    
    @Test
    @Transactional
    public void getPosicion() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get the posicion
        restPosicionMockMvc.perform(get("/api/posicions/{id}", posicion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(posicion.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.numeroPuestos").value(DEFAULT_NUMERO_PUESTOS))
            .andExpect(jsonPath("$.salarioMinimo").value(DEFAULT_SALARIO_MINIMO.intValue()))
            .andExpect(jsonPath("$.salarioMaximo").value(DEFAULT_SALARIO_MAXIMO.intValue()))
            .andExpect(jsonPath("$.fechaAlta").value(DEFAULT_FECHA_ALTA.toString()))
            .andExpect(jsonPath("$.fechaNecesidad").value(DEFAULT_FECHA_NECESIDAD.toString()));
    }


    @Test
    @Transactional
    public void getPosicionsByIdFiltering() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        Long id = posicion.getId();

        defaultPosicionShouldBeFound("id.equals=" + id);
        defaultPosicionShouldNotBeFound("id.notEquals=" + id);

        defaultPosicionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPosicionShouldNotBeFound("id.greaterThan=" + id);

        defaultPosicionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPosicionShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllPosicionsByTituloIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where titulo equals to DEFAULT_TITULO
        defaultPosicionShouldBeFound("titulo.equals=" + DEFAULT_TITULO);

        // Get all the posicionList where titulo equals to UPDATED_TITULO
        defaultPosicionShouldNotBeFound("titulo.equals=" + UPDATED_TITULO);
    }

    @Test
    @Transactional
    public void getAllPosicionsByTituloIsNotEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where titulo not equals to DEFAULT_TITULO
        defaultPosicionShouldNotBeFound("titulo.notEquals=" + DEFAULT_TITULO);

        // Get all the posicionList where titulo not equals to UPDATED_TITULO
        defaultPosicionShouldBeFound("titulo.notEquals=" + UPDATED_TITULO);
    }

    @Test
    @Transactional
    public void getAllPosicionsByTituloIsInShouldWork() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where titulo in DEFAULT_TITULO or UPDATED_TITULO
        defaultPosicionShouldBeFound("titulo.in=" + DEFAULT_TITULO + "," + UPDATED_TITULO);

        // Get all the posicionList where titulo equals to UPDATED_TITULO
        defaultPosicionShouldNotBeFound("titulo.in=" + UPDATED_TITULO);
    }

    @Test
    @Transactional
    public void getAllPosicionsByTituloIsNullOrNotNull() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where titulo is not null
        defaultPosicionShouldBeFound("titulo.specified=true");

        // Get all the posicionList where titulo is null
        defaultPosicionShouldNotBeFound("titulo.specified=false");
    }
                @Test
    @Transactional
    public void getAllPosicionsByTituloContainsSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where titulo contains DEFAULT_TITULO
        defaultPosicionShouldBeFound("titulo.contains=" + DEFAULT_TITULO);

        // Get all the posicionList where titulo contains UPDATED_TITULO
        defaultPosicionShouldNotBeFound("titulo.contains=" + UPDATED_TITULO);
    }

    @Test
    @Transactional
    public void getAllPosicionsByTituloNotContainsSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where titulo does not contain DEFAULT_TITULO
        defaultPosicionShouldNotBeFound("titulo.doesNotContain=" + DEFAULT_TITULO);

        // Get all the posicionList where titulo does not contain UPDATED_TITULO
        defaultPosicionShouldBeFound("titulo.doesNotContain=" + UPDATED_TITULO);
    }


    @Test
    @Transactional
    public void getAllPosicionsByDescripcionIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where descripcion equals to DEFAULT_DESCRIPCION
        defaultPosicionShouldBeFound("descripcion.equals=" + DEFAULT_DESCRIPCION);

        // Get all the posicionList where descripcion equals to UPDATED_DESCRIPCION
        defaultPosicionShouldNotBeFound("descripcion.equals=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllPosicionsByDescripcionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where descripcion not equals to DEFAULT_DESCRIPCION
        defaultPosicionShouldNotBeFound("descripcion.notEquals=" + DEFAULT_DESCRIPCION);

        // Get all the posicionList where descripcion not equals to UPDATED_DESCRIPCION
        defaultPosicionShouldBeFound("descripcion.notEquals=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllPosicionsByDescripcionIsInShouldWork() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where descripcion in DEFAULT_DESCRIPCION or UPDATED_DESCRIPCION
        defaultPosicionShouldBeFound("descripcion.in=" + DEFAULT_DESCRIPCION + "," + UPDATED_DESCRIPCION);

        // Get all the posicionList where descripcion equals to UPDATED_DESCRIPCION
        defaultPosicionShouldNotBeFound("descripcion.in=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllPosicionsByDescripcionIsNullOrNotNull() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where descripcion is not null
        defaultPosicionShouldBeFound("descripcion.specified=true");

        // Get all the posicionList where descripcion is null
        defaultPosicionShouldNotBeFound("descripcion.specified=false");
    }
                @Test
    @Transactional
    public void getAllPosicionsByDescripcionContainsSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where descripcion contains DEFAULT_DESCRIPCION
        defaultPosicionShouldBeFound("descripcion.contains=" + DEFAULT_DESCRIPCION);

        // Get all the posicionList where descripcion contains UPDATED_DESCRIPCION
        defaultPosicionShouldNotBeFound("descripcion.contains=" + UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void getAllPosicionsByDescripcionNotContainsSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where descripcion does not contain DEFAULT_DESCRIPCION
        defaultPosicionShouldNotBeFound("descripcion.doesNotContain=" + DEFAULT_DESCRIPCION);

        // Get all the posicionList where descripcion does not contain UPDATED_DESCRIPCION
        defaultPosicionShouldBeFound("descripcion.doesNotContain=" + UPDATED_DESCRIPCION);
    }


    @Test
    @Transactional
    public void getAllPosicionsByNumeroPuestosIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where numeroPuestos equals to DEFAULT_NUMERO_PUESTOS
        defaultPosicionShouldBeFound("numeroPuestos.equals=" + DEFAULT_NUMERO_PUESTOS);

        // Get all the posicionList where numeroPuestos equals to UPDATED_NUMERO_PUESTOS
        defaultPosicionShouldNotBeFound("numeroPuestos.equals=" + UPDATED_NUMERO_PUESTOS);
    }

    @Test
    @Transactional
    public void getAllPosicionsByNumeroPuestosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where numeroPuestos not equals to DEFAULT_NUMERO_PUESTOS
        defaultPosicionShouldNotBeFound("numeroPuestos.notEquals=" + DEFAULT_NUMERO_PUESTOS);

        // Get all the posicionList where numeroPuestos not equals to UPDATED_NUMERO_PUESTOS
        defaultPosicionShouldBeFound("numeroPuestos.notEquals=" + UPDATED_NUMERO_PUESTOS);
    }

    @Test
    @Transactional
    public void getAllPosicionsByNumeroPuestosIsInShouldWork() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where numeroPuestos in DEFAULT_NUMERO_PUESTOS or UPDATED_NUMERO_PUESTOS
        defaultPosicionShouldBeFound("numeroPuestos.in=" + DEFAULT_NUMERO_PUESTOS + "," + UPDATED_NUMERO_PUESTOS);

        // Get all the posicionList where numeroPuestos equals to UPDATED_NUMERO_PUESTOS
        defaultPosicionShouldNotBeFound("numeroPuestos.in=" + UPDATED_NUMERO_PUESTOS);
    }

    @Test
    @Transactional
    public void getAllPosicionsByNumeroPuestosIsNullOrNotNull() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where numeroPuestos is not null
        defaultPosicionShouldBeFound("numeroPuestos.specified=true");

        // Get all the posicionList where numeroPuestos is null
        defaultPosicionShouldNotBeFound("numeroPuestos.specified=false");
    }

    @Test
    @Transactional
    public void getAllPosicionsByNumeroPuestosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where numeroPuestos is greater than or equal to DEFAULT_NUMERO_PUESTOS
        defaultPosicionShouldBeFound("numeroPuestos.greaterThanOrEqual=" + DEFAULT_NUMERO_PUESTOS);

        // Get all the posicionList where numeroPuestos is greater than or equal to UPDATED_NUMERO_PUESTOS
        defaultPosicionShouldNotBeFound("numeroPuestos.greaterThanOrEqual=" + UPDATED_NUMERO_PUESTOS);
    }

    @Test
    @Transactional
    public void getAllPosicionsByNumeroPuestosIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where numeroPuestos is less than or equal to DEFAULT_NUMERO_PUESTOS
        defaultPosicionShouldBeFound("numeroPuestos.lessThanOrEqual=" + DEFAULT_NUMERO_PUESTOS);

        // Get all the posicionList where numeroPuestos is less than or equal to SMALLER_NUMERO_PUESTOS
        defaultPosicionShouldNotBeFound("numeroPuestos.lessThanOrEqual=" + SMALLER_NUMERO_PUESTOS);
    }

    @Test
    @Transactional
    public void getAllPosicionsByNumeroPuestosIsLessThanSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where numeroPuestos is less than DEFAULT_NUMERO_PUESTOS
        defaultPosicionShouldNotBeFound("numeroPuestos.lessThan=" + DEFAULT_NUMERO_PUESTOS);

        // Get all the posicionList where numeroPuestos is less than UPDATED_NUMERO_PUESTOS
        defaultPosicionShouldBeFound("numeroPuestos.lessThan=" + UPDATED_NUMERO_PUESTOS);
    }

    @Test
    @Transactional
    public void getAllPosicionsByNumeroPuestosIsGreaterThanSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where numeroPuestos is greater than DEFAULT_NUMERO_PUESTOS
        defaultPosicionShouldNotBeFound("numeroPuestos.greaterThan=" + DEFAULT_NUMERO_PUESTOS);

        // Get all the posicionList where numeroPuestos is greater than SMALLER_NUMERO_PUESTOS
        defaultPosicionShouldBeFound("numeroPuestos.greaterThan=" + SMALLER_NUMERO_PUESTOS);
    }


    @Test
    @Transactional
    public void getAllPosicionsBySalarioMinimoIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMinimo equals to DEFAULT_SALARIO_MINIMO
        defaultPosicionShouldBeFound("salarioMinimo.equals=" + DEFAULT_SALARIO_MINIMO);

        // Get all the posicionList where salarioMinimo equals to UPDATED_SALARIO_MINIMO
        defaultPosicionShouldNotBeFound("salarioMinimo.equals=" + UPDATED_SALARIO_MINIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMinimoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMinimo not equals to DEFAULT_SALARIO_MINIMO
        defaultPosicionShouldNotBeFound("salarioMinimo.notEquals=" + DEFAULT_SALARIO_MINIMO);

        // Get all the posicionList where salarioMinimo not equals to UPDATED_SALARIO_MINIMO
        defaultPosicionShouldBeFound("salarioMinimo.notEquals=" + UPDATED_SALARIO_MINIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMinimoIsInShouldWork() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMinimo in DEFAULT_SALARIO_MINIMO or UPDATED_SALARIO_MINIMO
        defaultPosicionShouldBeFound("salarioMinimo.in=" + DEFAULT_SALARIO_MINIMO + "," + UPDATED_SALARIO_MINIMO);

        // Get all the posicionList where salarioMinimo equals to UPDATED_SALARIO_MINIMO
        defaultPosicionShouldNotBeFound("salarioMinimo.in=" + UPDATED_SALARIO_MINIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMinimoIsNullOrNotNull() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMinimo is not null
        defaultPosicionShouldBeFound("salarioMinimo.specified=true");

        // Get all the posicionList where salarioMinimo is null
        defaultPosicionShouldNotBeFound("salarioMinimo.specified=false");
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMinimoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMinimo is greater than or equal to DEFAULT_SALARIO_MINIMO
        defaultPosicionShouldBeFound("salarioMinimo.greaterThanOrEqual=" + DEFAULT_SALARIO_MINIMO);

        // Get all the posicionList where salarioMinimo is greater than or equal to UPDATED_SALARIO_MINIMO
        defaultPosicionShouldNotBeFound("salarioMinimo.greaterThanOrEqual=" + UPDATED_SALARIO_MINIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMinimoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMinimo is less than or equal to DEFAULT_SALARIO_MINIMO
        defaultPosicionShouldBeFound("salarioMinimo.lessThanOrEqual=" + DEFAULT_SALARIO_MINIMO);

        // Get all the posicionList where salarioMinimo is less than or equal to SMALLER_SALARIO_MINIMO
        defaultPosicionShouldNotBeFound("salarioMinimo.lessThanOrEqual=" + SMALLER_SALARIO_MINIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMinimoIsLessThanSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMinimo is less than DEFAULT_SALARIO_MINIMO
        defaultPosicionShouldNotBeFound("salarioMinimo.lessThan=" + DEFAULT_SALARIO_MINIMO);

        // Get all the posicionList where salarioMinimo is less than UPDATED_SALARIO_MINIMO
        defaultPosicionShouldBeFound("salarioMinimo.lessThan=" + UPDATED_SALARIO_MINIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMinimoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMinimo is greater than DEFAULT_SALARIO_MINIMO
        defaultPosicionShouldNotBeFound("salarioMinimo.greaterThan=" + DEFAULT_SALARIO_MINIMO);

        // Get all the posicionList where salarioMinimo is greater than SMALLER_SALARIO_MINIMO
        defaultPosicionShouldBeFound("salarioMinimo.greaterThan=" + SMALLER_SALARIO_MINIMO);
    }


    @Test
    @Transactional
    public void getAllPosicionsBySalarioMaximoIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMaximo equals to DEFAULT_SALARIO_MAXIMO
        defaultPosicionShouldBeFound("salarioMaximo.equals=" + DEFAULT_SALARIO_MAXIMO);

        // Get all the posicionList where salarioMaximo equals to UPDATED_SALARIO_MAXIMO
        defaultPosicionShouldNotBeFound("salarioMaximo.equals=" + UPDATED_SALARIO_MAXIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMaximoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMaximo not equals to DEFAULT_SALARIO_MAXIMO
        defaultPosicionShouldNotBeFound("salarioMaximo.notEquals=" + DEFAULT_SALARIO_MAXIMO);

        // Get all the posicionList where salarioMaximo not equals to UPDATED_SALARIO_MAXIMO
        defaultPosicionShouldBeFound("salarioMaximo.notEquals=" + UPDATED_SALARIO_MAXIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMaximoIsInShouldWork() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMaximo in DEFAULT_SALARIO_MAXIMO or UPDATED_SALARIO_MAXIMO
        defaultPosicionShouldBeFound("salarioMaximo.in=" + DEFAULT_SALARIO_MAXIMO + "," + UPDATED_SALARIO_MAXIMO);

        // Get all the posicionList where salarioMaximo equals to UPDATED_SALARIO_MAXIMO
        defaultPosicionShouldNotBeFound("salarioMaximo.in=" + UPDATED_SALARIO_MAXIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMaximoIsNullOrNotNull() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMaximo is not null
        defaultPosicionShouldBeFound("salarioMaximo.specified=true");

        // Get all the posicionList where salarioMaximo is null
        defaultPosicionShouldNotBeFound("salarioMaximo.specified=false");
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMaximoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMaximo is greater than or equal to DEFAULT_SALARIO_MAXIMO
        defaultPosicionShouldBeFound("salarioMaximo.greaterThanOrEqual=" + DEFAULT_SALARIO_MAXIMO);

        // Get all the posicionList where salarioMaximo is greater than or equal to UPDATED_SALARIO_MAXIMO
        defaultPosicionShouldNotBeFound("salarioMaximo.greaterThanOrEqual=" + UPDATED_SALARIO_MAXIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMaximoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMaximo is less than or equal to DEFAULT_SALARIO_MAXIMO
        defaultPosicionShouldBeFound("salarioMaximo.lessThanOrEqual=" + DEFAULT_SALARIO_MAXIMO);

        // Get all the posicionList where salarioMaximo is less than or equal to SMALLER_SALARIO_MAXIMO
        defaultPosicionShouldNotBeFound("salarioMaximo.lessThanOrEqual=" + SMALLER_SALARIO_MAXIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMaximoIsLessThanSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMaximo is less than DEFAULT_SALARIO_MAXIMO
        defaultPosicionShouldNotBeFound("salarioMaximo.lessThan=" + DEFAULT_SALARIO_MAXIMO);

        // Get all the posicionList where salarioMaximo is less than UPDATED_SALARIO_MAXIMO
        defaultPosicionShouldBeFound("salarioMaximo.lessThan=" + UPDATED_SALARIO_MAXIMO);
    }

    @Test
    @Transactional
    public void getAllPosicionsBySalarioMaximoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where salarioMaximo is greater than DEFAULT_SALARIO_MAXIMO
        defaultPosicionShouldNotBeFound("salarioMaximo.greaterThan=" + DEFAULT_SALARIO_MAXIMO);

        // Get all the posicionList where salarioMaximo is greater than SMALLER_SALARIO_MAXIMO
        defaultPosicionShouldBeFound("salarioMaximo.greaterThan=" + SMALLER_SALARIO_MAXIMO);
    }


    @Test
    @Transactional
    public void getAllPosicionsByFechaAltaIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaAlta equals to DEFAULT_FECHA_ALTA
        defaultPosicionShouldBeFound("fechaAlta.equals=" + DEFAULT_FECHA_ALTA);

        // Get all the posicionList where fechaAlta equals to UPDATED_FECHA_ALTA
        defaultPosicionShouldNotBeFound("fechaAlta.equals=" + UPDATED_FECHA_ALTA);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaAltaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaAlta not equals to DEFAULT_FECHA_ALTA
        defaultPosicionShouldNotBeFound("fechaAlta.notEquals=" + DEFAULT_FECHA_ALTA);

        // Get all the posicionList where fechaAlta not equals to UPDATED_FECHA_ALTA
        defaultPosicionShouldBeFound("fechaAlta.notEquals=" + UPDATED_FECHA_ALTA);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaAltaIsInShouldWork() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaAlta in DEFAULT_FECHA_ALTA or UPDATED_FECHA_ALTA
        defaultPosicionShouldBeFound("fechaAlta.in=" + DEFAULT_FECHA_ALTA + "," + UPDATED_FECHA_ALTA);

        // Get all the posicionList where fechaAlta equals to UPDATED_FECHA_ALTA
        defaultPosicionShouldNotBeFound("fechaAlta.in=" + UPDATED_FECHA_ALTA);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaAltaIsNullOrNotNull() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaAlta is not null
        defaultPosicionShouldBeFound("fechaAlta.specified=true");

        // Get all the posicionList where fechaAlta is null
        defaultPosicionShouldNotBeFound("fechaAlta.specified=false");
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaAltaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaAlta is greater than or equal to DEFAULT_FECHA_ALTA
        defaultPosicionShouldBeFound("fechaAlta.greaterThanOrEqual=" + DEFAULT_FECHA_ALTA);

        // Get all the posicionList where fechaAlta is greater than or equal to UPDATED_FECHA_ALTA
        defaultPosicionShouldNotBeFound("fechaAlta.greaterThanOrEqual=" + UPDATED_FECHA_ALTA);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaAltaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaAlta is less than or equal to DEFAULT_FECHA_ALTA
        defaultPosicionShouldBeFound("fechaAlta.lessThanOrEqual=" + DEFAULT_FECHA_ALTA);

        // Get all the posicionList where fechaAlta is less than or equal to SMALLER_FECHA_ALTA
        defaultPosicionShouldNotBeFound("fechaAlta.lessThanOrEqual=" + SMALLER_FECHA_ALTA);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaAltaIsLessThanSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaAlta is less than DEFAULT_FECHA_ALTA
        defaultPosicionShouldNotBeFound("fechaAlta.lessThan=" + DEFAULT_FECHA_ALTA);

        // Get all the posicionList where fechaAlta is less than UPDATED_FECHA_ALTA
        defaultPosicionShouldBeFound("fechaAlta.lessThan=" + UPDATED_FECHA_ALTA);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaAltaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaAlta is greater than DEFAULT_FECHA_ALTA
        defaultPosicionShouldNotBeFound("fechaAlta.greaterThan=" + DEFAULT_FECHA_ALTA);

        // Get all the posicionList where fechaAlta is greater than SMALLER_FECHA_ALTA
        defaultPosicionShouldBeFound("fechaAlta.greaterThan=" + SMALLER_FECHA_ALTA);
    }


    @Test
    @Transactional
    public void getAllPosicionsByFechaNecesidadIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaNecesidad equals to DEFAULT_FECHA_NECESIDAD
        defaultPosicionShouldBeFound("fechaNecesidad.equals=" + DEFAULT_FECHA_NECESIDAD);

        // Get all the posicionList where fechaNecesidad equals to UPDATED_FECHA_NECESIDAD
        defaultPosicionShouldNotBeFound("fechaNecesidad.equals=" + UPDATED_FECHA_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaNecesidadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaNecesidad not equals to DEFAULT_FECHA_NECESIDAD
        defaultPosicionShouldNotBeFound("fechaNecesidad.notEquals=" + DEFAULT_FECHA_NECESIDAD);

        // Get all the posicionList where fechaNecesidad not equals to UPDATED_FECHA_NECESIDAD
        defaultPosicionShouldBeFound("fechaNecesidad.notEquals=" + UPDATED_FECHA_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaNecesidadIsInShouldWork() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaNecesidad in DEFAULT_FECHA_NECESIDAD or UPDATED_FECHA_NECESIDAD
        defaultPosicionShouldBeFound("fechaNecesidad.in=" + DEFAULT_FECHA_NECESIDAD + "," + UPDATED_FECHA_NECESIDAD);

        // Get all the posicionList where fechaNecesidad equals to UPDATED_FECHA_NECESIDAD
        defaultPosicionShouldNotBeFound("fechaNecesidad.in=" + UPDATED_FECHA_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaNecesidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaNecesidad is not null
        defaultPosicionShouldBeFound("fechaNecesidad.specified=true");

        // Get all the posicionList where fechaNecesidad is null
        defaultPosicionShouldNotBeFound("fechaNecesidad.specified=false");
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaNecesidadIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaNecesidad is greater than or equal to DEFAULT_FECHA_NECESIDAD
        defaultPosicionShouldBeFound("fechaNecesidad.greaterThanOrEqual=" + DEFAULT_FECHA_NECESIDAD);

        // Get all the posicionList where fechaNecesidad is greater than or equal to UPDATED_FECHA_NECESIDAD
        defaultPosicionShouldNotBeFound("fechaNecesidad.greaterThanOrEqual=" + UPDATED_FECHA_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaNecesidadIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaNecesidad is less than or equal to DEFAULT_FECHA_NECESIDAD
        defaultPosicionShouldBeFound("fechaNecesidad.lessThanOrEqual=" + DEFAULT_FECHA_NECESIDAD);

        // Get all the posicionList where fechaNecesidad is less than or equal to SMALLER_FECHA_NECESIDAD
        defaultPosicionShouldNotBeFound("fechaNecesidad.lessThanOrEqual=" + SMALLER_FECHA_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaNecesidadIsLessThanSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaNecesidad is less than DEFAULT_FECHA_NECESIDAD
        defaultPosicionShouldNotBeFound("fechaNecesidad.lessThan=" + DEFAULT_FECHA_NECESIDAD);

        // Get all the posicionList where fechaNecesidad is less than UPDATED_FECHA_NECESIDAD
        defaultPosicionShouldBeFound("fechaNecesidad.lessThan=" + UPDATED_FECHA_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllPosicionsByFechaNecesidadIsGreaterThanSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        // Get all the posicionList where fechaNecesidad is greater than DEFAULT_FECHA_NECESIDAD
        defaultPosicionShouldNotBeFound("fechaNecesidad.greaterThan=" + DEFAULT_FECHA_NECESIDAD);

        // Get all the posicionList where fechaNecesidad is greater than SMALLER_FECHA_NECESIDAD
        defaultPosicionShouldBeFound("fechaNecesidad.greaterThan=" + SMALLER_FECHA_NECESIDAD);
    }


    @Test
    @Transactional
    public void getAllPosicionsByCandidaturaIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);
        Candidatura candidatura = CandidaturaResourceIT.createEntity(em);
        em.persist(candidatura);
        em.flush();
        posicion.addCandidatura(candidatura);
        posicionRepository.saveAndFlush(posicion);
        Long candidaturaId = candidatura.getId();

        // Get all the posicionList where candidatura equals to candidaturaId
        defaultPosicionShouldBeFound("candidaturaId.equals=" + candidaturaId);

        // Get all the posicionList where candidatura equals to candidaturaId + 1
        defaultPosicionShouldNotBeFound("candidaturaId.equals=" + (candidaturaId + 1));
    }


    @Test
    @Transactional
    public void getAllPosicionsByHistorialCandidaturaIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);
        HistorialCandidatura historialCandidatura = HistorialCandidaturaResourceIT.createEntity(em);
        em.persist(historialCandidatura);
        em.flush();
        posicion.addHistorialCandidatura(historialCandidatura);
        posicionRepository.saveAndFlush(posicion);
        Long historialCandidaturaId = historialCandidatura.getId();

        // Get all the posicionList where historialCandidatura equals to historialCandidaturaId
        defaultPosicionShouldBeFound("historialCandidaturaId.equals=" + historialCandidaturaId);

        // Get all the posicionList where historialCandidatura equals to historialCandidaturaId + 1
        defaultPosicionShouldNotBeFound("historialCandidaturaId.equals=" + (historialCandidaturaId + 1));
    }


    @Test
    @Transactional
    public void getAllPosicionsByHistorialPosicionIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);
        HistorialPosicion historialPosicion = HistorialPosicionResourceIT.createEntity(em);
        em.persist(historialPosicion);
        em.flush();
        posicion.addHistorialPosicion(historialPosicion);
        posicionRepository.saveAndFlush(posicion);
        Long historialPosicionId = historialPosicion.getId();

        // Get all the posicionList where historialPosicion equals to historialPosicionId
        defaultPosicionShouldBeFound("historialPosicionId.equals=" + historialPosicionId);

        // Get all the posicionList where historialPosicion equals to historialPosicionId + 1
        defaultPosicionShouldNotBeFound("historialPosicionId.equals=" + (historialPosicionId + 1));
    }


    @Test
    @Transactional
    public void getAllPosicionsByEstadoPosicionIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);
        EstadoPosicion estadoPosicion = EstadoPosicionResourceIT.createEntity(em);
        em.persist(estadoPosicion);
        em.flush();
        posicion.setEstadoPosicion(estadoPosicion);
        posicionRepository.saveAndFlush(posicion);
        Long estadoPosicionId = estadoPosicion.getId();

        // Get all the posicionList where estadoPosicion equals to estadoPosicionId
        defaultPosicionShouldBeFound("estadoPosicionId.equals=" + estadoPosicionId);

        // Get all the posicionList where estadoPosicion equals to estadoPosicionId + 1
        defaultPosicionShouldNotBeFound("estadoPosicionId.equals=" + (estadoPosicionId + 1));
    }


    @Test
    @Transactional
    public void getAllPosicionsByTipoJornadaIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);
        TipoJornada tipoJornada = TipoJornadaResourceIT.createEntity(em);
        em.persist(tipoJornada);
        em.flush();
        posicion.setTipoJornada(tipoJornada);
        posicionRepository.saveAndFlush(posicion);
        Long tipoJornadaId = tipoJornada.getId();

        // Get all the posicionList where tipoJornada equals to tipoJornadaId
        defaultPosicionShouldBeFound("tipoJornadaId.equals=" + tipoJornadaId);

        // Get all the posicionList where tipoJornada equals to tipoJornadaId + 1
        defaultPosicionShouldNotBeFound("tipoJornadaId.equals=" + (tipoJornadaId + 1));
    }


    @Test
    @Transactional
    public void getAllPosicionsByUnidadDeNegocioIsEqualToSomething() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);
        UnidadDeNegocio unidadDeNegocio = UnidadDeNegocioResourceIT.createEntity(em);
        em.persist(unidadDeNegocio);
        em.flush();
        posicion.setUnidadDeNegocio(unidadDeNegocio);
        posicionRepository.saveAndFlush(posicion);
        Long unidadDeNegocioId = unidadDeNegocio.getId();

        // Get all the posicionList where unidadDeNegocio equals to unidadDeNegocioId
        defaultPosicionShouldBeFound("unidadDeNegocioId.equals=" + unidadDeNegocioId);

        // Get all the posicionList where unidadDeNegocio equals to unidadDeNegocioId + 1
        defaultPosicionShouldNotBeFound("unidadDeNegocioId.equals=" + (unidadDeNegocioId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPosicionShouldBeFound(String filter) throws Exception {
        restPosicionMockMvc.perform(get("/api/posicions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(posicion.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].numeroPuestos").value(hasItem(DEFAULT_NUMERO_PUESTOS)))
            .andExpect(jsonPath("$.[*].salarioMinimo").value(hasItem(DEFAULT_SALARIO_MINIMO.intValue())))
            .andExpect(jsonPath("$.[*].salarioMaximo").value(hasItem(DEFAULT_SALARIO_MAXIMO.intValue())))
            .andExpect(jsonPath("$.[*].fechaAlta").value(hasItem(DEFAULT_FECHA_ALTA.toString())))
            .andExpect(jsonPath("$.[*].fechaNecesidad").value(hasItem(DEFAULT_FECHA_NECESIDAD.toString())));

        // Check, that the count call also returns 1
        restPosicionMockMvc.perform(get("/api/posicions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPosicionShouldNotBeFound(String filter) throws Exception {
        restPosicionMockMvc.perform(get("/api/posicions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPosicionMockMvc.perform(get("/api/posicions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingPosicion() throws Exception {
        // Get the posicion
        restPosicionMockMvc.perform(get("/api/posicions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePosicion() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        int databaseSizeBeforeUpdate = posicionRepository.findAll().size();

        // Update the posicion
        Posicion updatedPosicion = posicionRepository.findById(posicion.getId()).get();
        // Disconnect from session so that the updates on updatedPosicion are not directly saved in db
        em.detach(updatedPosicion);
        updatedPosicion
            .titulo(UPDATED_TITULO)
            .descripcion(UPDATED_DESCRIPCION)
            .numeroPuestos(UPDATED_NUMERO_PUESTOS)
            .salarioMinimo(UPDATED_SALARIO_MINIMO)
            .salarioMaximo(UPDATED_SALARIO_MAXIMO)
            .fechaAlta(UPDATED_FECHA_ALTA)
            .fechaNecesidad(UPDATED_FECHA_NECESIDAD);
        PosicionDTO posicionDTO = posicionMapper.toDto(updatedPosicion);

        restPosicionMockMvc.perform(put("/api/posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posicionDTO)))
            .andExpect(status().isOk());

        // Validate the Posicion in the database
        List<Posicion> posicionList = posicionRepository.findAll();
        assertThat(posicionList).hasSize(databaseSizeBeforeUpdate);
        Posicion testPosicion = posicionList.get(posicionList.size() - 1);
        assertThat(testPosicion.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testPosicion.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testPosicion.getNumeroPuestos()).isEqualTo(UPDATED_NUMERO_PUESTOS);
        assertThat(testPosicion.getSalarioMinimo()).isEqualTo(UPDATED_SALARIO_MINIMO);
        assertThat(testPosicion.getSalarioMaximo()).isEqualTo(UPDATED_SALARIO_MAXIMO);
        assertThat(testPosicion.getFechaAlta()).isEqualTo(UPDATED_FECHA_ALTA);
        assertThat(testPosicion.getFechaNecesidad()).isEqualTo(UPDATED_FECHA_NECESIDAD);
    }

    @Test
    @Transactional
    public void updateNonExistingPosicion() throws Exception {
        int databaseSizeBeforeUpdate = posicionRepository.findAll().size();

        // Create the Posicion
        PosicionDTO posicionDTO = posicionMapper.toDto(posicion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPosicionMockMvc.perform(put("/api/posicions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(posicionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Posicion in the database
        List<Posicion> posicionList = posicionRepository.findAll();
        assertThat(posicionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePosicion() throws Exception {
        // Initialize the database
        posicionRepository.saveAndFlush(posicion);

        int databaseSizeBeforeDelete = posicionRepository.findAll().size();

        // Delete the posicion
        restPosicionMockMvc.perform(delete("/api/posicions/{id}", posicion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Posicion> posicionList = posicionRepository.findAll();
        assertThat(posicionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
