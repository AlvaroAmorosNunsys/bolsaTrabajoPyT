package com.company.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.company.domain.Usuario} entity.
 */
public class UsuarioDTO implements Serializable {
    
    private Long id;


    private Long userId;

    private String userLogin;

    private Long unidadDeNegocioId;

    private String unidadDeNegocioNombre;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getUnidadDeNegocioId() {
        return unidadDeNegocioId;
    }

    public void setUnidadDeNegocioId(Long unidadDeNegocioId) {
        this.unidadDeNegocioId = unidadDeNegocioId;
    }

    public String getUnidadDeNegocioNombre() {
        return unidadDeNegocioNombre;
    }

    public void setUnidadDeNegocioNombre(String unidadDeNegocioNombre) {
        this.unidadDeNegocioNombre = unidadDeNegocioNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UsuarioDTO)) {
            return false;
        }

        return id != null && id.equals(((UsuarioDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UsuarioDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", userLogin='" + getUserLogin() + "'" +
            ", unidadDeNegocioId=" + getUnidadDeNegocioId() +
            ", unidadDeNegocioNombre='" + getUnidadDeNegocioNombre() + "'" +
            "}";
    }
}
