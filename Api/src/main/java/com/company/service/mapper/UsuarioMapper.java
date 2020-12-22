package com.company.service.mapper;


import com.company.domain.*;
import com.company.service.dto.UsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Usuario} and its DTO {@link UsuarioDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, UnidadDeNegocioMapper.class})
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "unidadDeNegocio.id", target = "unidadDeNegocioId")
    @Mapping(source = "unidadDeNegocio.nombre", target = "unidadDeNegocioNombre")
    UsuarioDTO toDto(Usuario usuario);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "unidadDeNegocioId", target = "unidadDeNegocio")
    Usuario toEntity(UsuarioDTO usuarioDTO);

    default Usuario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
