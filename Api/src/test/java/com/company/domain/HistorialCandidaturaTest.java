package com.company.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class HistorialCandidaturaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistorialCandidatura.class);
        HistorialCandidatura historialCandidatura1 = new HistorialCandidatura();
        historialCandidatura1.setId(1L);
        HistorialCandidatura historialCandidatura2 = new HistorialCandidatura();
        historialCandidatura2.setId(historialCandidatura1.getId());
        assertThat(historialCandidatura1).isEqualTo(historialCandidatura2);
        historialCandidatura2.setId(2L);
        assertThat(historialCandidatura1).isNotEqualTo(historialCandidatura2);
        historialCandidatura1.setId(null);
        assertThat(historialCandidatura1).isNotEqualTo(historialCandidatura2);
    }
}
