package com.company.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class TipoJornadaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoJornada.class);
        TipoJornada tipoJornada1 = new TipoJornada();
        tipoJornada1.setId(1L);
        TipoJornada tipoJornada2 = new TipoJornada();
        tipoJornada2.setId(tipoJornada1.getId());
        assertThat(tipoJornada1).isEqualTo(tipoJornada2);
        tipoJornada2.setId(2L);
        assertThat(tipoJornada1).isNotEqualTo(tipoJornada2);
        tipoJornada1.setId(null);
        assertThat(tipoJornada1).isNotEqualTo(tipoJornada2);
    }
}
