package com.softwaretina.services;

import com.softwaretina.models.entities.Proceso;
import com.softwaretina.models.exception.*;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

import java.util.Date;

public interface ProcesoService {
    Page<Proceso> getProcesos(Long grupoId,int limit, int offset,Date from, Date to,Long[] selectedTags) throws CuentaNoEncontradaException;
    
    Page<Proceso> getProcesos(Long grupoId,int limit, int offset,Date from, Date to,String search,Long[] selectedTags) throws CuentaNoEncontradaException;

    Proceso createProceso(Proceso proceso,Long grupoId) throws GrupoNoEncontradoException, NoAutorizadoException, TagNoEncontradoException;

    @Transactional
    Proceso updateProceso(Proceso procesoToUpdate, Long groupShouldToBe) throws NoAutorizadoException, ProcesoNoEncontradoException;

    Proceso getProceso(Long procesoId, Long groupShouldToBe) throws ProcesoNoEncontradoException, NoAutorizadoException;

    void deleteProceso(Long procesoId, Long groupdShouldToBe) throws ProcesoNoEncontradoException, NoAutorizadoException;
}
