package com.company.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class EstadoPosicionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadoPosicion.class);
        EstadoPosicion estadoPosicion1 = new EstadoPosicion();
        estadoPosicion1.setId(1L);
        EstadoPosicion estadoPosicion2 = new EstadoPosicion();
        estadoPosicion2.setId(estadoPosicion1.getId());
        assertThat(estadoPosicion1).isEqualTo(estadoPosicion2);
        estadoPosicion2.setId(2L);
        assertThat(estadoPosicion1).isNotEqualTo(estadoPosicion2);
        estadoPosicion1.setId(null);
        assertThat(estadoPosicion1).isNotEqualTo(estadoPosicion2);
    }
}
