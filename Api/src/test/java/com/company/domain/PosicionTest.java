package com.company.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class PosicionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Posicion.class);
        Posicion posicion1 = new Posicion();
        posicion1.setId(1L);
        Posicion posicion2 = new Posicion();
        posicion2.setId(posicion1.getId());
        assertThat(posicion1).isEqualTo(posicion2);
        posicion2.setId(2L);
        assertThat(posicion1).isNotEqualTo(posicion2);
        posicion1.setId(null);
        assertThat(posicion1).isNotEqualTo(posicion2);
    }
}
