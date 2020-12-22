package com.company.service.mapper;

import com.company.domain.Persona;
import com.company.service.dto.PersonaDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-21T13:34:01+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class PersonaMapperImpl implements PersonaMapper {

    @Override
    public PersonaDTO toDto(Persona entity) {
        if ( entity == null ) {
            return null;
        }

        PersonaDTO personaDTO = new PersonaDTO();

        personaDTO.setId( entity.getId() );
        personaDTO.setDocumentoIdentidad( entity.getDocumentoIdentidad() );
        personaDTO.setNombre( entity.getNombre() );
        personaDTO.setApellidos( entity.getApellidos() );
        personaDTO.setEmail( entity.getEmail() );
        personaDTO.setTelefono( entity.getTelefono() );
        byte[] curriculum = entity.getCurriculum();
        if ( curriculum != null ) {
            personaDTO.setCurriculum( Arrays.copyOf( curriculum, curriculum.length ) );
        }
        personaDTO.setCurriculumContentType( entity.getCurriculumContentType() );
        personaDTO.setComentarios( entity.getComentarios() );

        return personaDTO;
    }

    @Override
    public List<Persona> toEntity(List<PersonaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Persona> list = new ArrayList<Persona>( dtoList.size() );
        for ( PersonaDTO personaDTO : dtoList ) {
            list.add( toEntity( personaDTO ) );
        }

        return list;
    }

    @Override
    public List<PersonaDTO> toDto(List<Persona> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PersonaDTO> list = new ArrayList<PersonaDTO>( entityList.size() );
        for ( Persona persona : entityList ) {
            list.add( toDto( persona ) );
        }

        return list;
    }

    @Override
    public Persona toEntity(PersonaDTO personaDTO) {
        if ( personaDTO == null ) {
            return null;
        }

        Persona persona = new Persona();

        persona.setId( personaDTO.getId() );
        persona.setDocumentoIdentidad( personaDTO.getDocumentoIdentidad() );
        persona.setNombre( personaDTO.getNombre() );
        persona.setApellidos( personaDTO.getApellidos() );
        persona.setEmail( personaDTO.getEmail() );
        persona.setTelefono( personaDTO.getTelefono() );
        byte[] curriculum = personaDTO.getCurriculum();
        if ( curriculum != null ) {
            persona.setCurriculum( Arrays.copyOf( curriculum, curriculum.length ) );
        }
        persona.setCurriculumContentType( personaDTO.getCurriculumContentType() );
        persona.setComentarios( personaDTO.getComentarios() );

        return persona;
    }
}
