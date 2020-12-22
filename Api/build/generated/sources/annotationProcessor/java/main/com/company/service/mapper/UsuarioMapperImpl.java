package com.company.service.mapper;

import com.company.domain.UnidadDeNegocio;
import com.company.domain.User;
import com.company.domain.Usuario;
import com.company.service.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-21T13:34:00+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UnidadDeNegocioMapper unidadDeNegocioMapper;

    @Override
    public List<Usuario> toEntity(List<UsuarioDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Usuario> list = new ArrayList<Usuario>( dtoList.size() );
        for ( UsuarioDTO usuarioDTO : dtoList ) {
            list.add( toEntity( usuarioDTO ) );
        }

        return list;
    }

    @Override
    public List<UsuarioDTO> toDto(List<Usuario> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>( entityList.size() );
        for ( Usuario usuario : entityList ) {
            list.add( toDto( usuario ) );
        }

        return list;
    }

    @Override
    public UsuarioDTO toDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setUserLogin( usuarioUserLogin( usuario ) );
        usuarioDTO.setUnidadDeNegocioId( usuarioUnidadDeNegocioId( usuario ) );
        usuarioDTO.setUnidadDeNegocioNombre( usuarioUnidadDeNegocioNombre( usuario ) );
        usuarioDTO.setUserId( usuarioUserId( usuario ) );
        usuarioDTO.setId( usuario.getId() );

        return usuarioDTO;
    }

    @Override
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setUser( userMapper.userFromId( usuarioDTO.getUserId() ) );
        usuario.setUnidadDeNegocio( unidadDeNegocioMapper.fromId( usuarioDTO.getUnidadDeNegocioId() ) );
        usuario.setId( usuarioDTO.getId() );

        return usuario;
    }

    private String usuarioUserLogin(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }
        User user = usuario.getUser();
        if ( user == null ) {
            return null;
        }
        String login = user.getLogin();
        if ( login == null ) {
            return null;
        }
        return login;
    }

    private Long usuarioUnidadDeNegocioId(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }
        UnidadDeNegocio unidadDeNegocio = usuario.getUnidadDeNegocio();
        if ( unidadDeNegocio == null ) {
            return null;
        }
        Long id = unidadDeNegocio.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String usuarioUnidadDeNegocioNombre(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }
        UnidadDeNegocio unidadDeNegocio = usuario.getUnidadDeNegocio();
        if ( unidadDeNegocio == null ) {
            return null;
        }
        String nombre = unidadDeNegocio.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private Long usuarioUserId(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }
        User user = usuario.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
