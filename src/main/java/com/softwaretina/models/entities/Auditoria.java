package com.softwaretina.models.entities;



import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Auditoria{

    @Transient
    private Account _loggedAccound;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_creador", referencedColumnName = "id")
    private Account createdBy;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_modificador", referencedColumnName = "id")
    private Account modifiedBy;

    public void setAccount(Account account){
        this._loggedAccound = account;
    }

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.createdBy = this._loggedAccound;

    }

    @PreUpdate
    public void onUpdate(){
        this.modifiedAt = LocalDateTime.now();
        this.modifiedBy = this._loggedAccound;
    }

}
