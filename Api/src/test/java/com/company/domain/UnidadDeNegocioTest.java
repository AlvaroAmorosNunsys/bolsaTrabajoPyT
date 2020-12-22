package com.company.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class UnidadDeNegocioTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnidadDeNegocio.class);
        UnidadDeNegocio unidadDeNegocio1 = new UnidadDeNegocio();
        unidadDeNegocio1.setId(1L);
        UnidadDeNegocio unidadDeNegocio2 = new UnidadDeNegocio();
        unidadDeNegocio2.setId(unidadDeNegocio1.getId());
        assertThat(unidadDeNegocio1).isEqualTo(unidadDeNegocio2);
        unidadDeNegocio2.setId(2L);
        assertThat(unidadDeNegocio1).isNotEqualTo(unidadDeNegocio2);
        unidadDeNegocio1.setId(null);
        assertThat(unidadDeNegocio1).isNotEqualTo(unidadDeNegocio2);
    }
}
