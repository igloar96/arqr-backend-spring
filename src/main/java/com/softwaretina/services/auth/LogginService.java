package com.softwaretina.services.auth;

import com.softwaretina.models.entities.Account;
import com.softwaretina.models.exception.CuentaNoEncontradaException;

public interface LogginService {

    Account getLoggedAccount() throws CuentaNoEncontradaException;
    Account createAccount();
    Account deleteAccount(Long accountId) throws CuentaNoEncontradaException;
    Account modifyAccount(Account account)throws CuentaNoEncontradaException;

}
