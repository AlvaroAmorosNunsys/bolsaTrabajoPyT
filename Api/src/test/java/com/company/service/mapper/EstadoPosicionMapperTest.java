package com.company.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EstadoPosicionMapperTest {

    private EstadoPosicionMapper estadoPosicionMapper;

    @BeforeEach
    public void setUp() {
        estadoPosicionMapper = new EstadoPosicionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(estadoPosicionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadoPosicionMapper.fromId(null)).isNull();
    }
}
