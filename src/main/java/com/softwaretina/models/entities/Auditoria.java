package com.softwaretina.models.entities;



import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
public abstract class Auditoria{

    @Transient
    private Account _loggedAccound;

    private Date createdAt;

    private Date modifiedAt;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_creador", referencedColumnName = "id")
    private Account createdBy;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_modificador", referencedColumnName = "id")
    private Account modifiedBy;

    public void setModifingAccount(Account account){
        this._loggedAccound = account;
    }

    @PrePersist
    public void onCreate(){
        this.createdAt = new Date();
        this.createdBy = this._loggedAccound;

    }

    @PreUpdate
    public void onUpdate(){
        this.modifiedAt = new Date();
        this.modifiedBy = this._loggedAccound;
    }

}
