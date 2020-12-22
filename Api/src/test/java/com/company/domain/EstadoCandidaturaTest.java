package com.company.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class EstadoCandidaturaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadoCandidatura.class);
        EstadoCandidatura estadoCandidatura1 = new EstadoCandidatura();
        estadoCandidatura1.setId(1L);
        EstadoCandidatura estadoCandidatura2 = new EstadoCandidatura();
        estadoCandidatura2.setId(estadoCandidatura1.getId());
        assertThat(estadoCandidatura1).isEqualTo(estadoCandidatura2);
        estadoCandidatura2.setId(2L);
        assertThat(estadoCandidatura1).isNotEqualTo(estadoCandidatura2);
        estadoCandidatura1.setId(null);
        assertThat(estadoCandidatura1).isNotEqualTo(estadoCandidatura2);
    }
}
