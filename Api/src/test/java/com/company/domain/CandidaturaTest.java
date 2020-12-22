package com.company.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class CandidaturaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Candidatura.class);
        Candidatura candidatura1 = new Candidatura();
        candidatura1.setId(1L);
        Candidatura candidatura2 = new Candidatura();
        candidatura2.setId(candidatura1.getId());
        assertThat(candidatura1).isEqualTo(candidatura2);
        candidatura2.setId(2L);
        assertThat(candidatura1).isNotEqualTo(candidatura2);
        candidatura1.setId(null);
        assertThat(candidatura1).isNotEqualTo(candidatura2);
    }
}
