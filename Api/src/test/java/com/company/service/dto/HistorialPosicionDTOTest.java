package com.company.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class HistorialPosicionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistorialPosicionDTO.class);
        HistorialPosicionDTO historialPosicionDTO1 = new HistorialPosicionDTO();
        historialPosicionDTO1.setId(1L);
        HistorialPosicionDTO historialPosicionDTO2 = new HistorialPosicionDTO();
        assertThat(historialPosicionDTO1).isNotEqualTo(historialPosicionDTO2);
        historialPosicionDTO2.setId(historialPosicionDTO1.getId());
        assertThat(historialPosicionDTO1).isEqualTo(historialPosicionDTO2);
        historialPosicionDTO2.setId(2L);
        assertThat(historialPosicionDTO1).isNotEqualTo(historialPosicionDTO2);
        historialPosicionDTO1.setId(null);
        assertThat(historialPosicionDTO1).isNotEqualTo(historialPosicionDTO2);
    }
}
