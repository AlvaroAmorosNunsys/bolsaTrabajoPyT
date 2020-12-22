package com.company.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class TipoJornadaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoJornadaDTO.class);
        TipoJornadaDTO tipoJornadaDTO1 = new TipoJornadaDTO();
        tipoJornadaDTO1.setId(1L);
        TipoJornadaDTO tipoJornadaDTO2 = new TipoJornadaDTO();
        assertThat(tipoJornadaDTO1).isNotEqualTo(tipoJornadaDTO2);
        tipoJornadaDTO2.setId(tipoJornadaDTO1.getId());
        assertThat(tipoJornadaDTO1).isEqualTo(tipoJornadaDTO2);
        tipoJornadaDTO2.setId(2L);
        assertThat(tipoJornadaDTO1).isNotEqualTo(tipoJornadaDTO2);
        tipoJornadaDTO1.setId(null);
        assertThat(tipoJornadaDTO1).isNotEqualTo(tipoJornadaDTO2);
    }
}
