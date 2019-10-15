package com.softwaretina.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Proceso extends Auditoria{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;

    private String descripcion;

    private ESTADO estado;

    @OneToOne
    @JoinColumn(name="asignado_id",referencedColumnName = "id")
    private Account empleadoAsignado;

    @NotNull
    @OneToMany
    @JoinColumn(name="proceso_id",referencedColumnName = "id")
    private List<Tag> tags; //Ejemplo AMBIENTAL - PEPSI


    @NotNull
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "grupo_id", referencedColumnName = "id")
    private Grupo grupo;

    public enum ESTADO{
        ESPERANDO_APROBACION,
        RECHAZADO,
        ACEPTADO,
        EN_PROCESO,
        FINALIZADO
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ESTADO getEstado() {
        return estado;
    }

    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
