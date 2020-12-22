package com.company.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UnidadDeNegocioMapperTest {

    private UnidadDeNegocioMapper unidadDeNegocioMapper;

    @BeforeEach
    public void setUp() {
        unidadDeNegocioMapper = new UnidadDeNegocioMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(unidadDeNegocioMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(unidadDeNegocioMapper.fromId(null)).isNull();
    }
}
