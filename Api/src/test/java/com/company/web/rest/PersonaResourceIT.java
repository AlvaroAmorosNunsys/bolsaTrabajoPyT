package com.company.web.rest;

import com.company.ApiApp;
import com.company.domain.Persona;
import com.company.domain.Candidatura;
import com.company.repository.PersonaRepository;
import com.company.service.PersonaService;
import com.company.service.dto.PersonaDTO;
import com.company.service.mapper.PersonaMapper;
import com.company.service.dto.PersonaCriteria;
import com.company.service.PersonaQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PersonaResource} REST controller.
 */
@SpringBootTest(classes = ApiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PersonaResourceIT {

    private static final String DEFAULT_DOCUMENTO_IDENTIDAD = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENTO_IDENTIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDOS = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDOS = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_CURRICULUM = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CURRICULUM = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_CURRICULUM_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CURRICULUM_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_COMENTARIOS = "AAAAAAAAAA";
    private static final String UPDATED_COMENTARIOS = "BBBBBBBBBB";

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaQueryService personaQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonaMockMvc;

    private Persona persona;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Persona createEntity(EntityManager em) {
        Persona persona = new Persona()
            .documentoIdentidad(DEFAULT_DOCUMENTO_IDENTIDAD)
            .nombre(DEFAULT_NOMBRE)
            .apellidos(DEFAULT_APELLIDOS)
            .email(DEFAULT_EMAIL)
            .telefono(DEFAULT_TELEFONO)
            .curriculum(DEFAULT_CURRICULUM)
            .curriculumContentType(DEFAULT_CURRICULUM_CONTENT_TYPE)
            .comentarios(DEFAULT_COMENTARIOS);
        return persona;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Persona createUpdatedEntity(EntityManager em) {
        Persona persona = new Persona()
            .documentoIdentidad(UPDATED_DOCUMENTO_IDENTIDAD)
            .nombre(UPDATED_NOMBRE)
            .apellidos(UPDATED_APELLIDOS)
            .email(UPDATED_EMAIL)
            .telefono(UPDATED_TELEFONO)
            .curriculum(UPDATED_CURRICULUM)
            .curriculumContentType(UPDATED_CURRICULUM_CONTENT_TYPE)
            .comentarios(UPDATED_COMENTARIOS);
        return persona;
    }

    @BeforeEach
    public void initTest() {
        persona = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersona() throws Exception {
        int databaseSizeBeforeCreate = personaRepository.findAll().size();
        // Create the Persona
        PersonaDTO personaDTO = personaMapper.toDto(persona);
        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isCreated());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeCreate + 1);
        Persona testPersona = personaList.get(personaList.size() - 1);
        assertThat(testPersona.getDocumentoIdentidad()).isEqualTo(DEFAULT_DOCUMENTO_IDENTIDAD);
        assertThat(testPersona.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPersona.getApellidos()).isEqualTo(DEFAULT_APELLIDOS);
        assertThat(testPersona.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPersona.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testPersona.getCurriculum()).isEqualTo(DEFAULT_CURRICULUM);
        assertThat(testPersona.getCurriculumContentType()).isEqualTo(DEFAULT_CURRICULUM_CONTENT_TYPE);
        assertThat(testPersona.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
    }

    @Test
    @Transactional
    public void createPersonaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personaRepository.findAll().size();

        // Create the Persona with an existing ID
        persona.setId(1L);
        PersonaDTO personaDTO = personaMapper.toDto(persona);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDocumentoIdentidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaRepository.findAll().size();
        // set the field null
        persona.setDocumentoIdentidad(null);

        // Create the Persona, which fails.
        PersonaDTO personaDTO = personaMapper.toDto(persona);


        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isBadRequest());

        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaRepository.findAll().size();
        // set the field null
        persona.setNombre(null);

        // Create the Persona, which fails.
        PersonaDTO personaDTO = personaMapper.toDto(persona);


        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isBadRequest());

        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApellidosIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaRepository.findAll().size();
        // set the field null
        persona.setApellidos(null);

        // Create the Persona, which fails.
        PersonaDTO personaDTO = personaMapper.toDto(persona);


        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isBadRequest());

        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaRepository.findAll().size();
        // set the field null
        persona.setEmail(null);

        // Create the Persona, which fails.
        PersonaDTO personaDTO = personaMapper.toDto(persona);


        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isBadRequest());

        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelefonoIsRequired() throws Exception {
        int databaseSizeBeforeTest = personaRepository.findAll().size();
        // set the field null
        persona.setTelefono(null);

        // Create the Persona, which fails.
        PersonaDTO personaDTO = personaMapper.toDto(persona);


        restPersonaMockMvc.perform(post("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isBadRequest());

        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonas() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList
        restPersonaMockMvc.perform(get("/api/personas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(persona.getId().intValue())))
            .andExpect(jsonPath("$.[*].documentoIdentidad").value(hasItem(DEFAULT_DOCUMENTO_IDENTIDAD)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellidos").value(hasItem(DEFAULT_APELLIDOS)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].curriculumContentType").value(hasItem(DEFAULT_CURRICULUM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].curriculum").value(hasItem(Base64Utils.encodeToString(DEFAULT_CURRICULUM))))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS)));
    }
    
    @Test
    @Transactional
    public void getPersona() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get the persona
        restPersonaMockMvc.perform(get("/api/personas/{id}", persona.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(persona.getId().intValue()))
            .andExpect(jsonPath("$.documentoIdentidad").value(DEFAULT_DOCUMENTO_IDENTIDAD))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.apellidos").value(DEFAULT_APELLIDOS))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.curriculumContentType").value(DEFAULT_CURRICULUM_CONTENT_TYPE))
            .andExpect(jsonPath("$.curriculum").value(Base64Utils.encodeToString(DEFAULT_CURRICULUM)))
            .andExpect(jsonPath("$.comentarios").value(DEFAULT_COMENTARIOS));
    }


    @Test
    @Transactional
    public void getPersonasByIdFiltering() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        Long id = persona.getId();

        defaultPersonaShouldBeFound("id.equals=" + id);
        defaultPersonaShouldNotBeFound("id.notEquals=" + id);

        defaultPersonaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPersonaShouldNotBeFound("id.greaterThan=" + id);

        defaultPersonaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPersonaShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllPersonasByDocumentoIdentidadIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where documentoIdentidad equals to DEFAULT_DOCUMENTO_IDENTIDAD
        defaultPersonaShouldBeFound("documentoIdentidad.equals=" + DEFAULT_DOCUMENTO_IDENTIDAD);

        // Get all the personaList where documentoIdentidad equals to UPDATED_DOCUMENTO_IDENTIDAD
        defaultPersonaShouldNotBeFound("documentoIdentidad.equals=" + UPDATED_DOCUMENTO_IDENTIDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByDocumentoIdentidadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where documentoIdentidad not equals to DEFAULT_DOCUMENTO_IDENTIDAD
        defaultPersonaShouldNotBeFound("documentoIdentidad.notEquals=" + DEFAULT_DOCUMENTO_IDENTIDAD);

        // Get all the personaList where documentoIdentidad not equals to UPDATED_DOCUMENTO_IDENTIDAD
        defaultPersonaShouldBeFound("documentoIdentidad.notEquals=" + UPDATED_DOCUMENTO_IDENTIDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByDocumentoIdentidadIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where documentoIdentidad in DEFAULT_DOCUMENTO_IDENTIDAD or UPDATED_DOCUMENTO_IDENTIDAD
        defaultPersonaShouldBeFound("documentoIdentidad.in=" + DEFAULT_DOCUMENTO_IDENTIDAD + "," + UPDATED_DOCUMENTO_IDENTIDAD);

        // Get all the personaList where documentoIdentidad equals to UPDATED_DOCUMENTO_IDENTIDAD
        defaultPersonaShouldNotBeFound("documentoIdentidad.in=" + UPDATED_DOCUMENTO_IDENTIDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByDocumentoIdentidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where documentoIdentidad is not null
        defaultPersonaShouldBeFound("documentoIdentidad.specified=true");

        // Get all the personaList where documentoIdentidad is null
        defaultPersonaShouldNotBeFound("documentoIdentidad.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByDocumentoIdentidadContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where documentoIdentidad contains DEFAULT_DOCUMENTO_IDENTIDAD
        defaultPersonaShouldBeFound("documentoIdentidad.contains=" + DEFAULT_DOCUMENTO_IDENTIDAD);

        // Get all the personaList where documentoIdentidad contains UPDATED_DOCUMENTO_IDENTIDAD
        defaultPersonaShouldNotBeFound("documentoIdentidad.contains=" + UPDATED_DOCUMENTO_IDENTIDAD);
    }

    @Test
    @Transactional
    public void getAllPersonasByDocumentoIdentidadNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where documentoIdentidad does not contain DEFAULT_DOCUMENTO_IDENTIDAD
        defaultPersonaShouldNotBeFound("documentoIdentidad.doesNotContain=" + DEFAULT_DOCUMENTO_IDENTIDAD);

        // Get all the personaList where documentoIdentidad does not contain UPDATED_DOCUMENTO_IDENTIDAD
        defaultPersonaShouldBeFound("documentoIdentidad.doesNotContain=" + UPDATED_DOCUMENTO_IDENTIDAD);
    }


    @Test
    @Transactional
    public void getAllPersonasByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre equals to DEFAULT_NOMBRE
        defaultPersonaShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the personaList where nombre equals to UPDATED_NOMBRE
        defaultPersonaShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByNombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre not equals to DEFAULT_NOMBRE
        defaultPersonaShouldNotBeFound("nombre.notEquals=" + DEFAULT_NOMBRE);

        // Get all the personaList where nombre not equals to UPDATED_NOMBRE
        defaultPersonaShouldBeFound("nombre.notEquals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultPersonaShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the personaList where nombre equals to UPDATED_NOMBRE
        defaultPersonaShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre is not null
        defaultPersonaShouldBeFound("nombre.specified=true");

        // Get all the personaList where nombre is null
        defaultPersonaShouldNotBeFound("nombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByNombreContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre contains DEFAULT_NOMBRE
        defaultPersonaShouldBeFound("nombre.contains=" + DEFAULT_NOMBRE);

        // Get all the personaList where nombre contains UPDATED_NOMBRE
        defaultPersonaShouldNotBeFound("nombre.contains=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllPersonasByNombreNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where nombre does not contain DEFAULT_NOMBRE
        defaultPersonaShouldNotBeFound("nombre.doesNotContain=" + DEFAULT_NOMBRE);

        // Get all the personaList where nombre does not contain UPDATED_NOMBRE
        defaultPersonaShouldBeFound("nombre.doesNotContain=" + UPDATED_NOMBRE);
    }


    @Test
    @Transactional
    public void getAllPersonasByApellidosIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellidos equals to DEFAULT_APELLIDOS
        defaultPersonaShouldBeFound("apellidos.equals=" + DEFAULT_APELLIDOS);

        // Get all the personaList where apellidos equals to UPDATED_APELLIDOS
        defaultPersonaShouldNotBeFound("apellidos.equals=" + UPDATED_APELLIDOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByApellidosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellidos not equals to DEFAULT_APELLIDOS
        defaultPersonaShouldNotBeFound("apellidos.notEquals=" + DEFAULT_APELLIDOS);

        // Get all the personaList where apellidos not equals to UPDATED_APELLIDOS
        defaultPersonaShouldBeFound("apellidos.notEquals=" + UPDATED_APELLIDOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByApellidosIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellidos in DEFAULT_APELLIDOS or UPDATED_APELLIDOS
        defaultPersonaShouldBeFound("apellidos.in=" + DEFAULT_APELLIDOS + "," + UPDATED_APELLIDOS);

        // Get all the personaList where apellidos equals to UPDATED_APELLIDOS
        defaultPersonaShouldNotBeFound("apellidos.in=" + UPDATED_APELLIDOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByApellidosIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellidos is not null
        defaultPersonaShouldBeFound("apellidos.specified=true");

        // Get all the personaList where apellidos is null
        defaultPersonaShouldNotBeFound("apellidos.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByApellidosContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellidos contains DEFAULT_APELLIDOS
        defaultPersonaShouldBeFound("apellidos.contains=" + DEFAULT_APELLIDOS);

        // Get all the personaList where apellidos contains UPDATED_APELLIDOS
        defaultPersonaShouldNotBeFound("apellidos.contains=" + UPDATED_APELLIDOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByApellidosNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where apellidos does not contain DEFAULT_APELLIDOS
        defaultPersonaShouldNotBeFound("apellidos.doesNotContain=" + DEFAULT_APELLIDOS);

        // Get all the personaList where apellidos does not contain UPDATED_APELLIDOS
        defaultPersonaShouldBeFound("apellidos.doesNotContain=" + UPDATED_APELLIDOS);
    }


    @Test
    @Transactional
    public void getAllPersonasByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where email equals to DEFAULT_EMAIL
        defaultPersonaShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the personaList where email equals to UPDATED_EMAIL
        defaultPersonaShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where email not equals to DEFAULT_EMAIL
        defaultPersonaShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the personaList where email not equals to UPDATED_EMAIL
        defaultPersonaShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultPersonaShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the personaList where email equals to UPDATED_EMAIL
        defaultPersonaShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where email is not null
        defaultPersonaShouldBeFound("email.specified=true");

        // Get all the personaList where email is null
        defaultPersonaShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByEmailContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where email contains DEFAULT_EMAIL
        defaultPersonaShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the personaList where email contains UPDATED_EMAIL
        defaultPersonaShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPersonasByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where email does not contain DEFAULT_EMAIL
        defaultPersonaShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the personaList where email does not contain UPDATED_EMAIL
        defaultPersonaShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllPersonasByTelefonoIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefono equals to DEFAULT_TELEFONO
        defaultPersonaShouldBeFound("telefono.equals=" + DEFAULT_TELEFONO);

        // Get all the personaList where telefono equals to UPDATED_TELEFONO
        defaultPersonaShouldNotBeFound("telefono.equals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllPersonasByTelefonoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefono not equals to DEFAULT_TELEFONO
        defaultPersonaShouldNotBeFound("telefono.notEquals=" + DEFAULT_TELEFONO);

        // Get all the personaList where telefono not equals to UPDATED_TELEFONO
        defaultPersonaShouldBeFound("telefono.notEquals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllPersonasByTelefonoIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefono in DEFAULT_TELEFONO or UPDATED_TELEFONO
        defaultPersonaShouldBeFound("telefono.in=" + DEFAULT_TELEFONO + "," + UPDATED_TELEFONO);

        // Get all the personaList where telefono equals to UPDATED_TELEFONO
        defaultPersonaShouldNotBeFound("telefono.in=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllPersonasByTelefonoIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefono is not null
        defaultPersonaShouldBeFound("telefono.specified=true");

        // Get all the personaList where telefono is null
        defaultPersonaShouldNotBeFound("telefono.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByTelefonoContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefono contains DEFAULT_TELEFONO
        defaultPersonaShouldBeFound("telefono.contains=" + DEFAULT_TELEFONO);

        // Get all the personaList where telefono contains UPDATED_TELEFONO
        defaultPersonaShouldNotBeFound("telefono.contains=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllPersonasByTelefonoNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where telefono does not contain DEFAULT_TELEFONO
        defaultPersonaShouldNotBeFound("telefono.doesNotContain=" + DEFAULT_TELEFONO);

        // Get all the personaList where telefono does not contain UPDATED_TELEFONO
        defaultPersonaShouldBeFound("telefono.doesNotContain=" + UPDATED_TELEFONO);
    }


    @Test
    @Transactional
    public void getAllPersonasByComentariosIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where comentarios equals to DEFAULT_COMENTARIOS
        defaultPersonaShouldBeFound("comentarios.equals=" + DEFAULT_COMENTARIOS);

        // Get all the personaList where comentarios equals to UPDATED_COMENTARIOS
        defaultPersonaShouldNotBeFound("comentarios.equals=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByComentariosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where comentarios not equals to DEFAULT_COMENTARIOS
        defaultPersonaShouldNotBeFound("comentarios.notEquals=" + DEFAULT_COMENTARIOS);

        // Get all the personaList where comentarios not equals to UPDATED_COMENTARIOS
        defaultPersonaShouldBeFound("comentarios.notEquals=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByComentariosIsInShouldWork() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where comentarios in DEFAULT_COMENTARIOS or UPDATED_COMENTARIOS
        defaultPersonaShouldBeFound("comentarios.in=" + DEFAULT_COMENTARIOS + "," + UPDATED_COMENTARIOS);

        // Get all the personaList where comentarios equals to UPDATED_COMENTARIOS
        defaultPersonaShouldNotBeFound("comentarios.in=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByComentariosIsNullOrNotNull() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where comentarios is not null
        defaultPersonaShouldBeFound("comentarios.specified=true");

        // Get all the personaList where comentarios is null
        defaultPersonaShouldNotBeFound("comentarios.specified=false");
    }
                @Test
    @Transactional
    public void getAllPersonasByComentariosContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where comentarios contains DEFAULT_COMENTARIOS
        defaultPersonaShouldBeFound("comentarios.contains=" + DEFAULT_COMENTARIOS);

        // Get all the personaList where comentarios contains UPDATED_COMENTARIOS
        defaultPersonaShouldNotBeFound("comentarios.contains=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllPersonasByComentariosNotContainsSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        // Get all the personaList where comentarios does not contain DEFAULT_COMENTARIOS
        defaultPersonaShouldNotBeFound("comentarios.doesNotContain=" + DEFAULT_COMENTARIOS);

        // Get all the personaList where comentarios does not contain UPDATED_COMENTARIOS
        defaultPersonaShouldBeFound("comentarios.doesNotContain=" + UPDATED_COMENTARIOS);
    }


    @Test
    @Transactional
    public void getAllPersonasByCandidaturaIsEqualToSomething() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);
        Candidatura candidatura = CandidaturaResourceIT.createEntity(em);
        em.persist(candidatura);
        em.flush();
        persona.addCandidatura(candidatura);
        personaRepository.saveAndFlush(persona);
        Long candidaturaId = candidatura.getId();

        // Get all the personaList where candidatura equals to candidaturaId
        defaultPersonaShouldBeFound("candidaturaId.equals=" + candidaturaId);

        // Get all the personaList where candidatura equals to candidaturaId + 1
        defaultPersonaShouldNotBeFound("candidaturaId.equals=" + (candidaturaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPersonaShouldBeFound(String filter) throws Exception {
        restPersonaMockMvc.perform(get("/api/personas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(persona.getId().intValue())))
            .andExpect(jsonPath("$.[*].documentoIdentidad").value(hasItem(DEFAULT_DOCUMENTO_IDENTIDAD)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellidos").value(hasItem(DEFAULT_APELLIDOS)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].curriculumContentType").value(hasItem(DEFAULT_CURRICULUM_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].curriculum").value(hasItem(Base64Utils.encodeToString(DEFAULT_CURRICULUM))))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS)));

        // Check, that the count call also returns 1
        restPersonaMockMvc.perform(get("/api/personas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPersonaShouldNotBeFound(String filter) throws Exception {
        restPersonaMockMvc.perform(get("/api/personas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPersonaMockMvc.perform(get("/api/personas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingPersona() throws Exception {
        // Get the persona
        restPersonaMockMvc.perform(get("/api/personas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersona() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        int databaseSizeBeforeUpdate = personaRepository.findAll().size();

        // Update the persona
        Persona updatedPersona = personaRepository.findById(persona.getId()).get();
        // Disconnect from session so that the updates on updatedPersona are not directly saved in db
        em.detach(updatedPersona);
        updatedPersona
            .documentoIdentidad(UPDATED_DOCUMENTO_IDENTIDAD)
            .nombre(UPDATED_NOMBRE)
            .apellidos(UPDATED_APELLIDOS)
            .email(UPDATED_EMAIL)
            .telefono(UPDATED_TELEFONO)
            .curriculum(UPDATED_CURRICULUM)
            .curriculumContentType(UPDATED_CURRICULUM_CONTENT_TYPE)
            .comentarios(UPDATED_COMENTARIOS);
        PersonaDTO personaDTO = personaMapper.toDto(updatedPersona);

        restPersonaMockMvc.perform(put("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isOk());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeUpdate);
        Persona testPersona = personaList.get(personaList.size() - 1);
        assertThat(testPersona.getDocumentoIdentidad()).isEqualTo(UPDATED_DOCUMENTO_IDENTIDAD);
        assertThat(testPersona.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPersona.getApellidos()).isEqualTo(UPDATED_APELLIDOS);
        assertThat(testPersona.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPersona.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testPersona.getCurriculum()).isEqualTo(UPDATED_CURRICULUM);
        assertThat(testPersona.getCurriculumContentType()).isEqualTo(UPDATED_CURRICULUM_CONTENT_TYPE);
        assertThat(testPersona.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void updateNonExistingPersona() throws Exception {
        int databaseSizeBeforeUpdate = personaRepository.findAll().size();

        // Create the Persona
        PersonaDTO personaDTO = personaMapper.toDto(persona);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonaMockMvc.perform(put("/api/personas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(personaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Persona in the database
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePersona() throws Exception {
        // Initialize the database
        personaRepository.saveAndFlush(persona);

        int databaseSizeBeforeDelete = personaRepository.findAll().size();

        // Delete the persona
        restPersonaMockMvc.perform(delete("/api/personas/{id}", persona.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Persona> personaList = personaRepository.findAll();
        assertThat(personaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
