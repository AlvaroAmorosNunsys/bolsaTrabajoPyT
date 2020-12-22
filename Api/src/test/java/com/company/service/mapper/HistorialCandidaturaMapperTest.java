package com.company.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HistorialCandidaturaMapperTest {

    private HistorialCandidaturaMapper historialCandidaturaMapper;

    @BeforeEach
    public void setUp() {
        historialCandidaturaMapper = new HistorialCandidaturaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(historialCandidaturaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(historialCandidaturaMapper.fromId(null)).isNull();
    }
}
