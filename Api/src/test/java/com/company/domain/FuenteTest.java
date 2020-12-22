package com.company.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class FuenteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fuente.class);
        Fuente fuente1 = new Fuente();
        fuente1.setId(1L);
        Fuente fuente2 = new Fuente();
        fuente2.setId(fuente1.getId());
        assertThat(fuente1).isEqualTo(fuente2);
        fuente2.setId(2L);
        assertThat(fuente1).isNotEqualTo(fuente2);
        fuente1.setId(null);
        assertThat(fuente1).isNotEqualTo(fuente2);
    }
}
