package com.softwaretina.services.auth;

import com.softwaretina.models.entities.Account;
import com.softwaretina.models.entities.UserPrincipal;
import com.softwaretina.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Account account = this.accountRepository.findByUsernameAndDeletedIsFalseAndSuspendedIsFalse(username)
                .orElseThrow(()->new UsernameNotFoundException(username));

        return new UserPrincipal(account);
    }
}