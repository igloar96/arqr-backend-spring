package com.softwaretina.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tag extends Auditoria{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="grupo_id",referencedColumnName = "id")
    private Grupo grupo;

    private Long costoUnitario;

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
}
