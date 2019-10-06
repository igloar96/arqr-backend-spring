package com.softwaretina.controllers;

import com.softwaretina.models.entities.Tag;
import com.softwaretina.services.TagService;
import com.softwaretina.services.auth.LogginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/grupo/{grupo_id}")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private LogginService logginService;

    @RequestMapping(value = "/procesos/{proceso_id}/tags", method = RequestMethod.GET)
    List<Tag> getAllByProceso(
            @PathVariable("grupo_id") Long grupoId,
            @PathVariable("proceso_id") Long procesoId,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) throws Exception {

        return this.tagService.getTagsByProceso(procesoId,grupoId);

    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    Page<Tag> getAll(
            @PathVariable("grupo_id") Long grupoId,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) throws Exception {

        return this.tagService.getTagsByGrupo(grupoId, limit, offset);

    }

    @PostMapping("/tags")
    Tag create(
            @PathVariable("grupo_id") Long grupoId,
            @RequestBody Tag tag) throws Exception {
        tag.setModifingAccount(this.logginService.getLoggedAccount());
        return this.tagService.createTag(tag, grupoId);
    }

    @PutMapping("/tags")
    Tag update(
            @PathVariable("grupo_id") Long grupoId,
            @RequestBody Tag tag) throws Exception {
        tag.setModifingAccount(this.logginService.getLoggedAccount());
        return this.tagService.updateTag(tag, grupoId);
    }

    @DeleteMapping("/tags/{id}")
    void delete(
            @PathVariable("grupo_id") Long grupoId,
            @PathVariable("id") Long tagId) throws Exception {
        this.tagService.deleteTag(tagId, grupoId);
    }
}
