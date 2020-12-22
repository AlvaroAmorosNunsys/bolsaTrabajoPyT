package com.company.service.mapper;

import com.company.domain.Candidatura;
import com.company.domain.EstadoCandidatura;
import com.company.domain.Fuente;
import com.company.domain.Persona;
import com.company.domain.Posicion;
import com.company.service.dto.CandidaturaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-21T13:33:59+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class CandidaturaMapperImpl implements CandidaturaMapper {

    @Autowired
    private EstadoCandidaturaMapper estadoCandidaturaMapper;
    @Autowired
    private FuenteMapper fuenteMapper;
    @Autowired
    private PosicionMapper posicionMapper;
    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public List<Candidatura> toEntity(List<CandidaturaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Candidatura> list = new ArrayList<Candidatura>( dtoList.size() );
        for ( CandidaturaDTO candidaturaDTO : dtoList ) {
            list.add( toEntity( candidaturaDTO ) );
        }

        return list;
    }

    @Override
    public List<CandidaturaDTO> toDto(List<Candidatura> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CandidaturaDTO> list = new ArrayList<CandidaturaDTO>( entityList.size() );
        for ( Candidatura candidatura : entityList ) {
            list.add( toDto( candidatura ) );
        }

        return list;
    }

    @Override
    public CandidaturaDTO toDto(Candidatura candidatura) {
        if ( candidatura == null ) {
            return null;
        }

        CandidaturaDTO candidaturaDTO = new CandidaturaDTO();

        candidaturaDTO.setPosicionTitulo( candidaturaPosicionTitulo( candidatura ) );
        candidaturaDTO.setFuenteNombre( candidaturaFuenteNombre( candidatura ) );
        candidaturaDTO.setPersonaDocumentoIdentidad( candidaturaPersonaDocumentoIdentidad( candidatura ) );
        candidaturaDTO.setEstadoCandidaturaNombre( candidaturaEstadoCandidaturaNombre( candidatura ) );
        candidaturaDTO.setFuenteId( candidaturaFuenteId( candidatura ) );
        candidaturaDTO.setPosicionId( candidaturaPosicionId( candidatura ) );
        candidaturaDTO.setPersonaId( candidaturaPersonaId( candidatura ) );
        candidaturaDTO.setEstadoCandidaturaId( candidaturaEstadoCandidaturaId( candidatura ) );
        candidaturaDTO.setId( candidatura.getId() );

        return candidaturaDTO;
    }

    @Override
    public Candidatura toEntity(CandidaturaDTO candidaturaDTO) {
        if ( candidaturaDTO == null ) {
            return null;
        }

        Candidatura candidatura = new Candidatura();

        candidatura.setPosicion( posicionMapper.fromId( candidaturaDTO.getPosicionId() ) );
        candidatura.setPersona( personaMapper.fromId( candidaturaDTO.getPersonaId() ) );
        candidatura.setFuente( fuenteMapper.fromId( candidaturaDTO.getFuenteId() ) );
        candidatura.setEstadoCandidatura( estadoCandidaturaMapper.fromId( candidaturaDTO.getEstadoCandidaturaId() ) );
        candidatura.setId( candidaturaDTO.getId() );

        return candidatura;
    }

    private String candidaturaPosicionTitulo(Candidatura candidatura) {
        if ( candidatura == null ) {
            return null;
        }
        Posicion posicion = candidatura.getPosicion();
        if ( posicion == null ) {
            return null;
        }
        String titulo = posicion.getTitulo();
        if ( titulo == null ) {
            return null;
        }
        return titulo;
    }

    private String candidaturaFuenteNombre(Candidatura candidatura) {
        if ( candidatura == null ) {
            return null;
        }
        Fuente fuente = candidatura.getFuente();
        if ( fuente == null ) {
            return null;
        }
        String nombre = fuente.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private String candidaturaPersonaDocumentoIdentidad(Candidatura candidatura) {
        if ( candidatura == null ) {
            return null;
        }
        Persona persona = candidatura.getPersona();
        if ( persona == null ) {
            return null;
        }
        String documentoIdentidad = persona.getDocumentoIdentidad();
        if ( documentoIdentidad == null ) {
            return null;
        }
        return documentoIdentidad;
    }

    private String candidaturaEstadoCandidaturaNombre(Candidatura candidatura) {
        if ( candidatura == null ) {
            return null;
        }
        EstadoCandidatura estadoCandidatura = candidatura.getEstadoCandidatura();
        if ( estadoCandidatura == null ) {
            return null;
        }
        String nombre = estadoCandidatura.getNombre();
        if ( nombre == null ) {
            return null;
        }
        return nombre;
    }

    private Long candidaturaFuenteId(Candidatura candidatura) {
        if ( candidatura == null ) {
            return null;
        }
        Fuente fuente = candidatura.getFuente();
        if ( fuente == null ) {
            return null;
        }
        Long id = fuente.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long candidaturaPosicionId(Candidatura candidatura) {
        if ( candidatura == null ) {
            return null;
        }
        Posicion posicion = candidatura.getPosicion();
        if ( posicion == null ) {
            return null;
        }
        Long id = posicion.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long candidaturaPersonaId(Candidatura candidatura) {
        if ( candidatura == null ) {
            return null;
        }
        Persona persona = candidatura.getPersona();
        if ( persona == null ) {
            return null;
        }
        Long id = persona.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long candidaturaEstadoCandidaturaId(Candidatura candidatura) {
        if ( candidatura == null ) {
            return null;
        }
        EstadoCandidatura estadoCandidatura = candidatura.getEstadoCandidatura();
        if ( estadoCandidatura == null ) {
            return null;
        }
        Long id = estadoCandidatura.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
