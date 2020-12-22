package com.company.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class EstadoPosicionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadoPosicionDTO.class);
        EstadoPosicionDTO estadoPosicionDTO1 = new EstadoPosicionDTO();
        estadoPosicionDTO1.setId(1L);
        EstadoPosicionDTO estadoPosicionDTO2 = new EstadoPosicionDTO();
        assertThat(estadoPosicionDTO1).isNotEqualTo(estadoPosicionDTO2);
        estadoPosicionDTO2.setId(estadoPosicionDTO1.getId());
        assertThat(estadoPosicionDTO1).isEqualTo(estadoPosicionDTO2);
        estadoPosicionDTO2.setId(2L);
        assertThat(estadoPosicionDTO1).isNotEqualTo(estadoPosicionDTO2);
        estadoPosicionDTO1.setId(null);
        assertThat(estadoPosicionDTO1).isNotEqualTo(estadoPosicionDTO2);
    }
}
