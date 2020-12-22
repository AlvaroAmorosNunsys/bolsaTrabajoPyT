package com.company.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class FuenteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FuenteDTO.class);
        FuenteDTO fuenteDTO1 = new FuenteDTO();
        fuenteDTO1.setId(1L);
        FuenteDTO fuenteDTO2 = new FuenteDTO();
        assertThat(fuenteDTO1).isNotEqualTo(fuenteDTO2);
        fuenteDTO2.setId(fuenteDTO1.getId());
        assertThat(fuenteDTO1).isEqualTo(fuenteDTO2);
        fuenteDTO2.setId(2L);
        assertThat(fuenteDTO1).isNotEqualTo(fuenteDTO2);
        fuenteDTO1.setId(null);
        assertThat(fuenteDTO1).isNotEqualTo(fuenteDTO2);
    }
}
