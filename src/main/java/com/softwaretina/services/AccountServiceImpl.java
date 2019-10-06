package com.softwaretina.services;

import com.softwaretina.models.entities.Account;
import com.softwaretina.models.exception.CuentaNoEncontradaException;
import com.softwaretina.models.exception.NoAutorizadoException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public Page<Account> getAccounts(Long grupoId, int limit, int offset) {
        return null;
    }

    @Override
    public Account createAccount(Account Account, Long grupoId) {
        return null;
    }

    @Override
    public Account updateAccount(Account AccountToUpdate, Long groupShouldToBe) throws NoAutorizadoException, CuentaNoEncontradaException {
        return null;
    }

    @Override
    public Account getAccount(Long AccountId, Long groupShouldToBe) throws NoAutorizadoException, CuentaNoEncontradaException, NoAutorizadoException {
        return null;
    }

    @Override
    public void deleteAccount(Long AccountId, Long groupdShouldToBe) throws NoAutorizadoException, CuentaNoEncontradaException, NoAutorizadoException {

    }
}
