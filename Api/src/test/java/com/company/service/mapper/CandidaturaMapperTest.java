package com.company.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CandidaturaMapperTest {

    private CandidaturaMapper candidaturaMapper;

    @BeforeEach
    public void setUp() {
        candidaturaMapper = new CandidaturaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(candidaturaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(candidaturaMapper.fromId(null)).isNull();
    }
}
