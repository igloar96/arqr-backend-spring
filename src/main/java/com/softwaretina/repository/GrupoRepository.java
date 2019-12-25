package com.softwaretina.repository;

import com.softwaretina.models.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GrupoRepository extends JpaRepository<Grupo,Long> {
}
