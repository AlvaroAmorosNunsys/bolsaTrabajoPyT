package com.company.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class HistorialPosicionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistorialPosicion.class);
        HistorialPosicion historialPosicion1 = new HistorialPosicion();
        historialPosicion1.setId(1L);
        HistorialPosicion historialPosicion2 = new HistorialPosicion();
        historialPosicion2.setId(historialPosicion1.getId());
        assertThat(historialPosicion1).isEqualTo(historialPosicion2);
        historialPosicion2.setId(2L);
        assertThat(historialPosicion1).isNotEqualTo(historialPosicion2);
        historialPosicion1.setId(null);
        assertThat(historialPosicion1).isNotEqualTo(historialPosicion2);
    }
}
