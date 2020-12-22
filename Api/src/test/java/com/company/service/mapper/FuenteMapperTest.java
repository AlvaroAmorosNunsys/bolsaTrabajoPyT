package com.company.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FuenteMapperTest {

    private FuenteMapper fuenteMapper;

    @BeforeEach
    public void setUp() {
        fuenteMapper = new FuenteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(fuenteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(fuenteMapper.fromId(null)).isNull();
    }
}
