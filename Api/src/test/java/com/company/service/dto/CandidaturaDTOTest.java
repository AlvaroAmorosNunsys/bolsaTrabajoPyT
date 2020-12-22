package com.company.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class CandidaturaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CandidaturaDTO.class);
        CandidaturaDTO candidaturaDTO1 = new CandidaturaDTO();
        candidaturaDTO1.setId(1L);
        CandidaturaDTO candidaturaDTO2 = new CandidaturaDTO();
        assertThat(candidaturaDTO1).isNotEqualTo(candidaturaDTO2);
        candidaturaDTO2.setId(candidaturaDTO1.getId());
        assertThat(candidaturaDTO1).isEqualTo(candidaturaDTO2);
        candidaturaDTO2.setId(2L);
        assertThat(candidaturaDTO1).isNotEqualTo(candidaturaDTO2);
        candidaturaDTO1.setId(null);
        assertThat(candidaturaDTO1).isNotEqualTo(candidaturaDTO2);
    }
}
