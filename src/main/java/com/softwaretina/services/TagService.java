package com.softwaretina.services;

import com.softwaretina.models.entities.Tag;
import com.softwaretina.models.exception.GrupoNoEncontradoException;
import com.softwaretina.models.exception.NoAutorizadoException;
import com.softwaretina.models.exception.ProcesoNoEncontradoException;
import com.softwaretina.models.exception.TagNoEncontradoException;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.List;

public interface TagService {

    Page<Tag> getTagsByGrupo(Long grupoId, int limit, int offset) ;

    Tag createTag(Tag tag, Long grupoId) throws GrupoNoEncontradoException;

    @Transactional
    Tag updateTag(Tag tag, Long groupShouldToBe) throws NoAutorizadoException, TagNoEncontradoException, GrupoNoEncontradoException;

    Tag getTag(Long tagId, Long groupShouldToBe) throws NoAutorizadoException, TagNoEncontradoException, GrupoNoEncontradoException;

    void deleteTag(Long tagId, Long groupdShouldToBe) throws NoAutorizadoException, TagNoEncontradoException, GrupoNoEncontradoException;

    List<Tag> getTagsByProceso(Long procesoId, Long groupdShouldToBe) throws ProcesoNoEncontradoException, NoAutorizadoException;

}
