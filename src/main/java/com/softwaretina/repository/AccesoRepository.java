package com.softwaretina.repository;

import com.softwaretina.models.entities.Acceso;
import com.softwaretina.models.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccesoRepository extends JpaRepository<Acceso,Long> {
    Optional<Acceso> findByAccount(Account account);
    Page<Acceso> findAccesosByAccount(Account account, Pageable pr);
}
