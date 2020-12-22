package com.company.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class PosicionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PosicionDTO.class);
        PosicionDTO posicionDTO1 = new PosicionDTO();
        posicionDTO1.setId(1L);
        PosicionDTO posicionDTO2 = new PosicionDTO();
        assertThat(posicionDTO1).isNotEqualTo(posicionDTO2);
        posicionDTO2.setId(posicionDTO1.getId());
        assertThat(posicionDTO1).isEqualTo(posicionDTO2);
        posicionDTO2.setId(2L);
        assertThat(posicionDTO1).isNotEqualTo(posicionDTO2);
        posicionDTO1.setId(null);
        assertThat(posicionDTO1).isNotEqualTo(posicionDTO2);
    }
}
