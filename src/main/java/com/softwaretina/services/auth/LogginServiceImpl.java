package com.softwaretina.services.auth;

import com.softwaretina.models.entities.Account;
import com.softwaretina.models.exception.CuentaNoEncontradaException;
import com.softwaretina.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LogginServiceImpl implements LogginService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getLoggedAccount() throws CuentaNoEncontradaException {
        return this.accountRepository.findByUsernameAndDeletedIsFalseAndSuspendedIsFalse(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).orElseThrow(()-> new CuentaNoEncontradaException("No se encontro la cuenta loggeada"));
    }

    @Override
    public Account createAccount() {
        return null;
    }

    @Override
    public Account deleteAccount(Long accountId) throws CuentaNoEncontradaException {
        return null;
    }

    @Override
    public Account modifyAccount(Account account) throws CuentaNoEncontradaException {
        return null;
    }


}
