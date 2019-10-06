package com.softwaretina.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles")
    private List<String> roles;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Grupo grupo;
    @JsonIgnore
    private Boolean deleted;
    @JsonIgnore
    private Boolean suspended;
    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private LocalDateTime modifiedAt;

    public Account(){
        this.roles = new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){
        this.deleted = false;
        this.suspended = false;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        this.modifiedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void AddRole(ROLES role){

        this.roles.add(role.name().replace("GID",this.grupo.getId().toString()));
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public enum ROLES{
        ROLE_ADMIN,
        ROLE_SUPERVISOR,
        ROLE_MODERADOR,
        ROLE_USUARIO
    }
}
