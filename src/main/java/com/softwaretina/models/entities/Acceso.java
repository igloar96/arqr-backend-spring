package com.softwaretina.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Acceso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @OneToOne
    @JoinColumn(name="account_id" , referencedColumnName = "id")
    private Account account;
    private String ip;
    private LocalDateTime at;

    @PrePersist
    public void onLogin(){
        this.at = LocalDateTime.now();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getAt() {
        return at;
    }

    public void setAt(LocalDateTime at) {
        this.at = at;
    }
}
