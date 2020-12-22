package com.company.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PosicionMapperTest {

    private PosicionMapper posicionMapper;

    @BeforeEach
    public void setUp() {
        posicionMapper = new PosicionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(posicionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(posicionMapper.fromId(null)).isNull();
    }
}
