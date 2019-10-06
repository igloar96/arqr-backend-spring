package com.softwaretina.services;

import com.softwaretina.models.entities.Account;
import com.softwaretina.models.entities.Grupo;
import com.softwaretina.models.exception.CuentaNoEncontradaException;
import com.softwaretina.models.exception.GrupoNoEncontradoException;
import com.softwaretina.models.exception.NoAutorizadoException;
import com.softwaretina.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GrupoService grupoService;

    @Override
    public Page<Account> getAccounts(Long grupoId, int limit, int offset) {
        PageRequest pr = PageRequest.of(offset,limit);
        return this.accountRepository.findAccountsByGrupoId(grupoId,pr);
    }

    @Override
    public Account createAccount(Account account, Long grupoId) throws GrupoNoEncontradoException {
        Grupo grupoDb = this.grupoService.getGrupo(grupoId);
        account.setId(null);
        account.setGrupo(grupoDb);
        return this.accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account accountUpdate, Long groupShouldToBe) throws NoAutorizadoException, CuentaNoEncontradaException {
        this.getAccount(accountUpdate.getId(),groupShouldToBe);
        return this.accountRepository.save(accountUpdate);
    }

    @Override
    public Account getAccount(Long id, Long groupShouldToBe) throws  CuentaNoEncontradaException, NoAutorizadoException {
        Account account = this.accountRepository.findById(id).orElseThrow(()->new CuentaNoEncontradaException(""));

        if(account.getGrupo().getId()!=groupShouldToBe){
            throw new NoAutorizadoException("GRAVE: Se intento crear una cuenta en otro grupo");
        }

        return account;
    }

    @Override
    public void deleteAccount(Long accountId, Long groupdShouldToBe) throws  CuentaNoEncontradaException, NoAutorizadoException {
        Account account = this.getAccount(accountId,groupdShouldToBe);
        account.setDeleted(true);
        account.setSuspended(true);
        this.accountRepository.save(account);
    }
}
