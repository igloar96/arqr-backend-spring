package com.softwaretina.services;

import com.softwaretina.models.entities.Grupo;
import com.softwaretina.models.exception.GrupoNoEncontradoException;
import com.softwaretina.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GrupoServiceImpl implements GrupoService{

    @Autowired
    private GrupoRepository grupoRepository;



    @Override
    public Page<Grupo> getGrupos(int limit, int offset) {
        PageRequest pr = PageRequest.of(offset,limit);
        return this.grupoRepository.findAll(pr);
    }

    @Override
    public Grupo createGrupo(Grupo grupo) {
        return this.grupoRepository.save(grupo);
    }

    @Override
    @Transactional
    public Grupo updateGrupo(Grupo grupo, Long grupoId) throws GrupoNoEncontradoException {
        Grupo grupoDb = this.getGrupo(grupoId);
        grupoDb.setNombre(grupo.getNombre());

        return grupoDb;
    }

    @Override
    public Grupo getGrupo(Long userGroupId) throws GrupoNoEncontradoException {

        return this.grupoRepository.findById(userGroupId).orElseThrow(()-> new GrupoNoEncontradoException("No se encontro el grupo"));

    }

    @Override
    public void deleteGrupo(Long grupoId) throws GrupoNoEncontradoException {
        Grupo grupo = this.getGrupo(grupoId);
        this.grupoRepository.delete(grupo);

    }
}
