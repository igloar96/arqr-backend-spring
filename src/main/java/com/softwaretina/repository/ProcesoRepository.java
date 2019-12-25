package com.softwaretina.repository;

import com.softwaretina.models.entities.Proceso;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesoRepository extends JpaRepository<Proceso, Long> {

    Page<Proceso> findProcesosByGrupoId(
    		Pageable filter,
    		@Param("groupId") Long groupId);
    
    Page<Proceso> findProcesosBycreatedAtBetweenAndGrupoIdEqualsAndTagsIdIn(
    		Pageable filter,
    		Date from,
    		Date to,
    		Long grupoId,
    		Long[] tags);
    
    Page<Proceso> findProcesosByGrupoIdAndNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCaseAndTagsIdIn(
    		Pageable filter,
    		@Param("groupId") Long groupId,
    		String searchNombre,
    		String searchDescripcion,
    		Long[] tags);
    
    Page<Proceso> findProcesosBycreatedAtBetweenAndGrupoIdEqualsAndNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCaseAndTagsIdIn(
    		Pageable filter,
    		Date from,
    		Date to,
    		Long grupoId,
    		String searchNombre,
    		String searchDescripcion,
    		Long[] tags);
    
}
