package com.company.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TipoJornadaMapperTest {

    private TipoJornadaMapper tipoJornadaMapper;

    @BeforeEach
    public void setUp() {
        tipoJornadaMapper = new TipoJornadaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tipoJornadaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipoJornadaMapper.fromId(null)).isNull();
    }
}
