package com.softwaretina.repository;

import com.softwaretina.models.entities.Proceso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ProcesoRepository extends JpaRepository<Proceso, Long> {

    Page<Proceso> findProcesosByGrupoId(Pageable filter, @Param("groupId") Long groupId);
}
