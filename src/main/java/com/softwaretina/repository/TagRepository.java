package com.softwaretina.repository;

import com.softwaretina.models.entities.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository  extends JpaRepository<Tag,Long> {
    Optional<Tag> getTagById(Long id);
    Page<Tag> getTagsByGrupoId(Long grupoId, Pageable pr);
}
