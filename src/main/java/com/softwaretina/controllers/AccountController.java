package com.softwaretina.controllers;

import com.softwaretina.models.dto.AccountTagsDto;
import com.softwaretina.models.entities.Account;
import com.softwaretina.services.AccountService;
import com.softwaretina.services.auth.LogginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AccountController {

    @Autowired
    private LogginService logginService;

    @Autowired
    private AccountService accountservice;



    @GetMapping("/account/info")
    public ResponseEntity<Account> getAccountInformation() throws Exception {
    	AccountTagsDto accountInfo = new AccountTagsDto();
        return ResponseEntity.ok(this.logginService.getLoggedAccount());
    }

    //Get Accounts in my group el middleware validara roles
    @RequestMapping(value = "/grupo/{grupo_id}/accounts", method = RequestMethod.GET)
    Page<Account> getAll(
            @PathVariable("grupo_id") Long grupoId,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) throws Exception {

        return this.accountservice.getAccounts(grupoId, limit, offset);

    }

    @PostMapping("/grupo/{grupo_id}/accounts")
    Account create(
            @PathVariable("grupo_id") Long grupoId,
            @RequestBody Account Account) throws Exception {
        return this.accountservice.createAccount(Account, grupoId);
    }

    @PutMapping("/grupo/{grupo_id}/accounts/{id}")
    Account update(
            @PathVariable("grupo_id") Long grupoId,
            @RequestBody Account Account) throws Exception {
        return this.accountservice.updateAccount(Account, grupoId);
    }

    @DeleteMapping("/grupo/{grupo_id}/accounts/{id}")
    void delete(
            @PathVariable("grupo_id") Long grupoId,
            @PathVariable("id") Long AccountId) throws Exception {
        this.accountservice.deleteAccount(AccountId, grupoId);
    }
}
