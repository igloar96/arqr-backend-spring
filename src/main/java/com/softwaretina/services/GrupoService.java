package com.softwaretina.services;

import com.softwaretina.models.entities.Grupo;
import com.softwaretina.models.exception.GrupoNoEncontradoException;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

public interface GrupoService {
    Page<Grupo> getGrupos(int limit, int offset) ;

    Grupo createGrupo(Grupo Grupo);

    @Transactional
    Grupo updateGrupo(Grupo grupo, Long grupoId) throws GrupoNoEncontradoException;

    Grupo getGrupo(Long id) throws GrupoNoEncontradoException;

    void deleteGrupo(Long grupoId) throws GrupoNoEncontradoException;
}
