package com.softwaretina.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tag extends Auditoria{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @JsonIgnore
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="grupo_id",referencedColumnName = "id")
    private Grupo grupo;

    private Long puntos;

    private TAG_TIPO tipo;

    @NotNull
    public enum TAG_TIPO{
        SERVICIO,
        CLIENTE,
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Long getPuntos() {
        return puntos;
    }

    public void setPuntos(Long puntos) {
        this.puntos = puntos;
    }

    public TAG_TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TAG_TIPO tipo) {
        this.tipo = tipo;
    }
}
