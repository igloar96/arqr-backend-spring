package com.softwaretina.repository;

import com.softwaretina.models.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByUsernameAndDeletedIsFalseAndSuspendedIsFalse(String username);
    Page<Account> findAccountsByGrupoId(Long grupoId, Pageable pr);
}
