package com.company.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EstadoCandidaturaMapperTest {

    private EstadoCandidaturaMapper estadoCandidaturaMapper;

    @BeforeEach
    public void setUp() {
        estadoCandidaturaMapper = new EstadoCandidaturaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(estadoCandidaturaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(estadoCandidaturaMapper.fromId(null)).isNull();
    }
}
