package com.company.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class HistorialCandidaturaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistorialCandidaturaDTO.class);
        HistorialCandidaturaDTO historialCandidaturaDTO1 = new HistorialCandidaturaDTO();
        historialCandidaturaDTO1.setId(1L);
        HistorialCandidaturaDTO historialCandidaturaDTO2 = new HistorialCandidaturaDTO();
        assertThat(historialCandidaturaDTO1).isNotEqualTo(historialCandidaturaDTO2);
        historialCandidaturaDTO2.setId(historialCandidaturaDTO1.getId());
        assertThat(historialCandidaturaDTO1).isEqualTo(historialCandidaturaDTO2);
        historialCandidaturaDTO2.setId(2L);
        assertThat(historialCandidaturaDTO1).isNotEqualTo(historialCandidaturaDTO2);
        historialCandidaturaDTO1.setId(null);
        assertThat(historialCandidaturaDTO1).isNotEqualTo(historialCandidaturaDTO2);
    }
}
