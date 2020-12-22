package com.company.web.rest;

import com.company.ApiApp;
import com.company.domain.UnidadDeNegocio;
import com.company.domain.Posicion;
import com.company.domain.Usuario;
import com.company.repository.UnidadDeNegocioRepository;
import com.company.service.UnidadDeNegocioService;
import com.company.service.dto.UnidadDeNegocioDTO;
import com.company.service.mapper.UnidadDeNegocioMapper;
import com.company.service.dto.UnidadDeNegocioCriteria;
import com.company.service.UnidadDeNegocioQueryService;

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
 * Integration tests for the {@link UnidadDeNegocioResource} REST controller.
 */
@SpringBootTest(classes = ApiApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UnidadDeNegocioResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private UnidadDeNegocioRepository unidadDeNegocioRepository;

    @Autowired
    private UnidadDeNegocioMapper unidadDeNegocioMapper;

    @Autowired
    private UnidadDeNegocioService unidadDeNegocioService;

    @Autowired
    private UnidadDeNegocioQueryService unidadDeNegocioQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUnidadDeNegocioMockMvc;

    private UnidadDeNegocio unidadDeNegocio;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnidadDeNegocio createEntity(EntityManager em) {
        UnidadDeNegocio unidadDeNegocio = new UnidadDeNegocio()
            .nombre(DEFAULT_NOMBRE);
        return unidadDeNegocio;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnidadDeNegocio createUpdatedEntity(EntityManager em) {
        UnidadDeNegocio unidadDeNegocio = new UnidadDeNegocio()
            .nombre(UPDATED_NOMBRE);
        return unidadDeNegocio;
    }

    @BeforeEach
    public void initTest() {
        unidadDeNegocio = createEntity(em);
    }

    @Test
    @Transactional
    public void createUnidadDeNegocio() throws Exception {
        int databaseSizeBeforeCreate = unidadDeNegocioRepository.findAll().size();
        // Create the UnidadDeNegocio
        UnidadDeNegocioDTO unidadDeNegocioDTO = unidadDeNegocioMapper.toDto(unidadDeNegocio);
        restUnidadDeNegocioMockMvc.perform(post("/api/unidad-de-negocios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unidadDeNegocioDTO)))
            .andExpect(status().isCreated());

        // Validate the UnidadDeNegocio in the database
        List<UnidadDeNegocio> unidadDeNegocioList = unidadDeNegocioRepository.findAll();
        assertThat(unidadDeNegocioList).hasSize(databaseSizeBeforeCreate + 1);
        UnidadDeNegocio testUnidadDeNegocio = unidadDeNegocioList.get(unidadDeNegocioList.size() - 1);
        assertThat(testUnidadDeNegocio.getNombre()).isEqualTo(DEFAULT_NOMBRE);
    }

    @Test
    @Transactional
    public void createUnidadDeNegocioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = unidadDeNegocioRepository.findAll().size();

        // Create the UnidadDeNegocio with an existing ID
        unidadDeNegocio.setId(1L);
        UnidadDeNegocioDTO unidadDeNegocioDTO = unidadDeNegocioMapper.toDto(unidadDeNegocio);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnidadDeNegocioMockMvc.perform(post("/api/unidad-de-negocios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unidadDeNegocioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UnidadDeNegocio in the database
        List<UnidadDeNegocio> unidadDeNegocioList = unidadDeNegocioRepository.findAll();
        assertThat(unidadDeNegocioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = unidadDeNegocioRepository.findAll().size();
        // set the field null
        unidadDeNegocio.setNombre(null);

        // Create the UnidadDeNegocio, which fails.
        UnidadDeNegocioDTO unidadDeNegocioDTO = unidadDeNegocioMapper.toDto(unidadDeNegocio);


        restUnidadDeNegocioMockMvc.perform(post("/api/unidad-de-negocios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unidadDeNegocioDTO)))
            .andExpect(status().isBadRequest());

        List<UnidadDeNegocio> unidadDeNegocioList = unidadDeNegocioRepository.findAll();
        assertThat(unidadDeNegocioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUnidadDeNegocios() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);

        // Get all the unidadDeNegocioList
        restUnidadDeNegocioMockMvc.perform(get("/api/unidad-de-negocios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unidadDeNegocio.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));
    }
    
    @Test
    @Transactional
    public void getUnidadDeNegocio() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);

        // Get the unidadDeNegocio
        restUnidadDeNegocioMockMvc.perform(get("/api/unidad-de-negocios/{id}", unidadDeNegocio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unidadDeNegocio.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE));
    }


    @Test
    @Transactional
    public void getUnidadDeNegociosByIdFiltering() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);

        Long id = unidadDeNegocio.getId();

        defaultUnidadDeNegocioShouldBeFound("id.equals=" + id);
        defaultUnidadDeNegocioShouldNotBeFound("id.notEquals=" + id);

        defaultUnidadDeNegocioShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultUnidadDeNegocioShouldNotBeFound("id.greaterThan=" + id);

        defaultUnidadDeNegocioShouldBeFound("id.lessThanOrEqual=" + id);
        defaultUnidadDeNegocioShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllUnidadDeNegociosByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);

        // Get all the unidadDeNegocioList where nombre equals to DEFAULT_NOMBRE
        defaultUnidadDeNegocioShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the unidadDeNegocioList where nombre equals to UPDATED_NOMBRE
        defaultUnidadDeNegocioShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllUnidadDeNegociosByNombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);

        // Get all the unidadDeNegocioList where nombre not equals to DEFAULT_NOMBRE
        defaultUnidadDeNegocioShouldNotBeFound("nombre.notEquals=" + DEFAULT_NOMBRE);

        // Get all the unidadDeNegocioList where nombre not equals to UPDATED_NOMBRE
        defaultUnidadDeNegocioShouldBeFound("nombre.notEquals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllUnidadDeNegociosByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);

        // Get all the unidadDeNegocioList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultUnidadDeNegocioShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the unidadDeNegocioList where nombre equals to UPDATED_NOMBRE
        defaultUnidadDeNegocioShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllUnidadDeNegociosByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);

        // Get all the unidadDeNegocioList where nombre is not null
        defaultUnidadDeNegocioShouldBeFound("nombre.specified=true");

        // Get all the unidadDeNegocioList where nombre is null
        defaultUnidadDeNegocioShouldNotBeFound("nombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllUnidadDeNegociosByNombreContainsSomething() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);

        // Get all the unidadDeNegocioList where nombre contains DEFAULT_NOMBRE
        defaultUnidadDeNegocioShouldBeFound("nombre.contains=" + DEFAULT_NOMBRE);

        // Get all the unidadDeNegocioList where nombre contains UPDATED_NOMBRE
        defaultUnidadDeNegocioShouldNotBeFound("nombre.contains=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllUnidadDeNegociosByNombreNotContainsSomething() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);

        // Get all the unidadDeNegocioList where nombre does not contain DEFAULT_NOMBRE
        defaultUnidadDeNegocioShouldNotBeFound("nombre.doesNotContain=" + DEFAULT_NOMBRE);

        // Get all the unidadDeNegocioList where nombre does not contain UPDATED_NOMBRE
        defaultUnidadDeNegocioShouldBeFound("nombre.doesNotContain=" + UPDATED_NOMBRE);
    }


    @Test
    @Transactional
    public void getAllUnidadDeNegociosByPosicionIsEqualToSomething() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);
        Posicion posicion = PosicionResourceIT.createEntity(em);
        em.persist(posicion);
        em.flush();
        unidadDeNegocio.addPosicion(posicion);
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);
        Long posicionId = posicion.getId();

        // Get all the unidadDeNegocioList where posicion equals to posicionId
        defaultUnidadDeNegocioShouldBeFound("posicionId.equals=" + posicionId);

        // Get all the unidadDeNegocioList where posicion equals to posicionId + 1
        defaultUnidadDeNegocioShouldNotBeFound("posicionId.equals=" + (posicionId + 1));
    }


    @Test
    @Transactional
    public void getAllUnidadDeNegociosByUsuarioIsEqualToSomething() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);
        Usuario usuario = UsuarioResourceIT.createEntity(em);
        em.persist(usuario);
        em.flush();
        unidadDeNegocio.addUsuario(usuario);
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);
        Long usuarioId = usuario.getId();

        // Get all the unidadDeNegocioList where usuario equals to usuarioId
        defaultUnidadDeNegocioShouldBeFound("usuarioId.equals=" + usuarioId);

        // Get all the unidadDeNegocioList where usuario equals to usuarioId + 1
        defaultUnidadDeNegocioShouldNotBeFound("usuarioId.equals=" + (usuarioId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultUnidadDeNegocioShouldBeFound(String filter) throws Exception {
        restUnidadDeNegocioMockMvc.perform(get("/api/unidad-de-negocios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unidadDeNegocio.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)));

        // Check, that the count call also returns 1
        restUnidadDeNegocioMockMvc.perform(get("/api/unidad-de-negocios/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultUnidadDeNegocioShouldNotBeFound(String filter) throws Exception {
        restUnidadDeNegocioMockMvc.perform(get("/api/unidad-de-negocios?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restUnidadDeNegocioMockMvc.perform(get("/api/unidad-de-negocios/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingUnidadDeNegocio() throws Exception {
        // Get the unidadDeNegocio
        restUnidadDeNegocioMockMvc.perform(get("/api/unidad-de-negocios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUnidadDeNegocio() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);

        int databaseSizeBeforeUpdate = unidadDeNegocioRepository.findAll().size();

        // Update the unidadDeNegocio
        UnidadDeNegocio updatedUnidadDeNegocio = unidadDeNegocioRepository.findById(unidadDeNegocio.getId()).get();
        // Disconnect from session so that the updates on updatedUnidadDeNegocio are not directly saved in db
        em.detach(updatedUnidadDeNegocio);
        updatedUnidadDeNegocio
            .nombre(UPDATED_NOMBRE);
        UnidadDeNegocioDTO unidadDeNegocioDTO = unidadDeNegocioMapper.toDto(updatedUnidadDeNegocio);

        restUnidadDeNegocioMockMvc.perform(put("/api/unidad-de-negocios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unidadDeNegocioDTO)))
            .andExpect(status().isOk());

        // Validate the UnidadDeNegocio in the database
        List<UnidadDeNegocio> unidadDeNegocioList = unidadDeNegocioRepository.findAll();
        assertThat(unidadDeNegocioList).hasSize(databaseSizeBeforeUpdate);
        UnidadDeNegocio testUnidadDeNegocio = unidadDeNegocioList.get(unidadDeNegocioList.size() - 1);
        assertThat(testUnidadDeNegocio.getNombre()).isEqualTo(UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingUnidadDeNegocio() throws Exception {
        int databaseSizeBeforeUpdate = unidadDeNegocioRepository.findAll().size();

        // Create the UnidadDeNegocio
        UnidadDeNegocioDTO unidadDeNegocioDTO = unidadDeNegocioMapper.toDto(unidadDeNegocio);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnidadDeNegocioMockMvc.perform(put("/api/unidad-de-negocios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unidadDeNegocioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UnidadDeNegocio in the database
        List<UnidadDeNegocio> unidadDeNegocioList = unidadDeNegocioRepository.findAll();
        assertThat(unidadDeNegocioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUnidadDeNegocio() throws Exception {
        // Initialize the database
        unidadDeNegocioRepository.saveAndFlush(unidadDeNegocio);

        int databaseSizeBeforeDelete = unidadDeNegocioRepository.findAll().size();

        // Delete the unidadDeNegocio
        restUnidadDeNegocioMockMvc.perform(delete("/api/unidad-de-negocios/{id}", unidadDeNegocio.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UnidadDeNegocio> unidadDeNegocioList = unidadDeNegocioRepository.findAll();
        assertThat(unidadDeNegocioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
