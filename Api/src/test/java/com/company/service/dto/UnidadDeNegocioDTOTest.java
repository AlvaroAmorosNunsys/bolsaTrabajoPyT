package com.company.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.company.web.rest.TestUtil;

public class UnidadDeNegocioDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UnidadDeNegocioDTO.class);
        UnidadDeNegocioDTO unidadDeNegocioDTO1 = new UnidadDeNegocioDTO();
        unidadDeNegocioDTO1.setId(1L);
        UnidadDeNegocioDTO unidadDeNegocioDTO2 = new UnidadDeNegocioDTO();
        assertThat(unidadDeNegocioDTO1).isNotEqualTo(unidadDeNegocioDTO2);
        unidadDeNegocioDTO2.setId(unidadDeNegocioDTO1.getId());
        assertThat(unidadDeNegocioDTO1).isEqualTo(unidadDeNegocioDTO2);
        unidadDeNegocioDTO2.setId(2L);
        assertThat(unidadDeNegocioDTO1).isNotEqualTo(unidadDeNegocioDTO2);
        unidadDeNegocioDTO1.setId(null);
        assertThat(unidadDeNegocioDTO1).isNotEqualTo(unidadDeNegocioDTO2);
    }
}
