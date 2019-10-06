package com.softwaretina.models.entities;

import javax.persistence.*;

@Entity
public class Movimiento extends Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String descripcion;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    private Proceso proceso;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }
}
