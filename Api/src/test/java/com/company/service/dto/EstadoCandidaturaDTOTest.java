package com.company.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class EstadoCandidaturaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadoCandidaturaDTO.class);
        EstadoCandidaturaDTO estadoCandidaturaDTO1 = new EstadoCandidaturaDTO();
        estadoCandidaturaDTO1.setId(1L);
        EstadoCandidaturaDTO estadoCandidaturaDTO2 = new EstadoCandidaturaDTO();
        assertThat(estadoCandidaturaDTO1).isNotEqualTo(estadoCandidaturaDTO2);
        estadoCandidaturaDTO2.setId(estadoCandidaturaDTO1.getId());
        assertThat(estadoCandidaturaDTO1).isEqualTo(estadoCandidaturaDTO2);
        estadoCandidaturaDTO2.setId(2L);
        assertThat(estadoCandidaturaDTO1).isNotEqualTo(estadoCandidaturaDTO2);
        estadoCandidaturaDTO1.setId(null);
        assertThat(estadoCandidaturaDTO1).isNotEqualTo(estadoCandidaturaDTO2);
    }
}
