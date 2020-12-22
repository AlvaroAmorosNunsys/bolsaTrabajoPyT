package com.company.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HistorialPosicionMapperTest {

    private HistorialPosicionMapper historialPosicionMapper;

    @BeforeEach
    public void setUp() {
        historialPosicionMapper = new HistorialPosicionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(historialPosicionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(historialPosicionMapper.fromId(null)).isNull();
    }
}
