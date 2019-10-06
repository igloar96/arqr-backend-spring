package com.softwaretina.services;

import com.softwaretina.models.entities.Account;
import com.softwaretina.models.exception.CuentaNoEncontradaException;
import com.softwaretina.models.exception.NoAutorizadoException;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

public interface AccountService {

    Page<Account> getAccounts(Long grupoId, int limit, int offset) ;

    Account createAccount(Account Account,Long grupoId)  ;

    @Transactional
    Account updateAccount(Account AccountToUpdate, Long groupShouldToBe) throws NoAutorizadoException, CuentaNoEncontradaException;

    Account getAccount(Long AccountId, Long groupShouldToBe) throws NoAutorizadoException,CuentaNoEncontradaException, NoAutorizadoException;

    void deleteAccount(Long AccountId, Long groupdShouldToBe) throws NoAutorizadoException,CuentaNoEncontradaException, NoAutorizadoException;

}
