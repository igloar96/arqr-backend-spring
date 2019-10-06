package com.softwaretina.services;

import com.softwaretina.models.entities.Grupo;
import com.softwaretina.models.entities.Proceso;
import com.softwaretina.models.entities.Tag;
import com.softwaretina.models.exception.GrupoNoEncontradoException;
import com.softwaretina.models.exception.NoAutorizadoException;
import com.softwaretina.models.exception.ProcesoNoEncontradoException;
import com.softwaretina.models.exception.TagNoEncontradoException;
import com.softwaretina.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private ProcesoService procesoService;

    @Override
    public Page<Tag> getTagsByGrupo(Long grupoId, int limit, int offset) {
        PageRequest pr = PageRequest.of(offset,limit);
        return this.tagRepository.getTagsByGrupoId(grupoId,pr);
    }

    @Override
    public Tag createTag(Tag tag, Long grupoId) throws GrupoNoEncontradoException {
        Grupo grupo = this.grupoService.getGrupo(grupoId);
        tag.setGrupo(grupo);
        return this.tagRepository.save(tag);
    }

    @Override
    @Transactional
    public Tag updateTag(Tag tag, Long groupShouldToBe) throws NoAutorizadoException, TagNoEncontradoException, GrupoNoEncontradoException {
        Tag tagDb = this.getTag(tag.getId(),groupShouldToBe);
        tagDb.setCategoria(tag.getCategoria());
        tagDb.setName(tag.getName());
        tagDb.setCostoUnitario(tag.getCostoUnitario());

        return tagDb;
    }

    @Override
    @Transactional
    public Tag getTag(Long tagId, Long groupShouldToBe) throws NoAutorizadoException, TagNoEncontradoException, GrupoNoEncontradoException {
        Grupo grupo = this.grupoService.getGrupo(groupShouldToBe);
        Tag tag = this.tagRepository.getTagById(tagId).orElseThrow(()->new TagNoEncontradoException(""));

        if(grupo.getId()!= tag.getGrupo().getId()){
            throw new NoAutorizadoException("GRAVE: se intenta crear un tag en otro grupo");
        }

        return tag;
    }

    @Override
    public void deleteTag(Long tagId, Long groupdShouldToBe) throws NoAutorizadoException, TagNoEncontradoException, GrupoNoEncontradoException {
        Tag tag = this.getTag(tagId,groupdShouldToBe);
        this.tagRepository.delete(tag);
    }

    @Override
    public List<Tag> getTagsByProceso(Long procesoId,Long groupdShouldToBe) throws ProcesoNoEncontradoException, NoAutorizadoException {
        Proceso proceso = this.procesoService.getProceso(procesoId,groupdShouldToBe);
        return proceso.getTags();
    }
}
