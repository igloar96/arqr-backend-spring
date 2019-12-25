package com.softwaretina.services;

import com.softwaretina.models.entities.Grupo;
import com.softwaretina.models.entities.Proceso;
import com.softwaretina.models.exception.GrupoNoEncontradoException;
import com.softwaretina.models.exception.NoAutorizadoException;
import com.softwaretina.models.exception.ProcesoNoEncontradoException;
import com.softwaretina.repository.ProcesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

import javax.transaction.Transactional;

@Service
public class ProcesoServiceImpl implements ProcesoService {

    @Autowired
    private ProcesoRepository procesoRepository;

    @Autowired
    private GrupoService grupoService;


    @Override
    public Page<Proceso> getProcesos(Long grupoId,int limit, int offset,Date from, Date to,Long[] selectedTags) {
        PageRequest pr = PageRequest.of(offset,limit);
        Page<Proceso> results = null;
        
        if(from != null && to !=null) {
        	results = this.procesoRepository.findProcesosBycreatedAtBetweenAndGrupoIdEqualsAndTagsIdIn(pr, from, to,grupoId,selectedTags);
        }else {
        	results = this.procesoRepository.findProcesosByGrupoId(pr,grupoId);
        }
        return results;
    }

    @Override
    public Page<Proceso> getProcesos(Long grupoId,int limit, int offset,Date from, Date to,String search,Long[] selectedTags) {
        PageRequest pr = PageRequest.of(offset,limit);
        Page<Proceso> results = null;
        
        if(from != null && to !=null) {
        	results = this.procesoRepository.findProcesosBycreatedAtBetweenAndGrupoIdEqualsAndNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCaseAndTagsIdIn(pr, from, to,grupoId,search,search,selectedTags);
        }else {
        	results = this.procesoRepository.findProcesosByGrupoIdAndNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCaseAndTagsIdIn(pr,grupoId,search,search,selectedTags);
        }
        return results;
    }
    
    
    @Override
    public Proceso createProceso(Proceso proceso, Long grupoId) throws GrupoNoEncontradoException {
        Grupo grupo = this.grupoService.getGrupo(grupoId);
        proceso.setGrupo(grupo);

        return this.procesoRepository.save(proceso);
    }

    @Override
    @Transactional
    public Proceso updateProceso(Proceso procesoToUpdate, Long groupShouldToBe) throws NoAutorizadoException,ProcesoNoEncontradoException {

        Proceso proceso = this.procesoRepository.findById(procesoToUpdate.getId()).orElseThrow(()-> new ProcesoNoEncontradoException(""));

        if(proceso.getGrupo().getId() == groupShouldToBe){

        }else{
            throw new NoAutorizadoException("Se intento modificar un proceso de otro grupo");
        }

        proceso.setNombre(procesoToUpdate.getNombre());
        proceso.setDescripcion(procesoToUpdate.getDescripcion());
        proceso.setEstado(procesoToUpdate.getEstado());

        return proceso;
    }

    @Override
    public Proceso getProceso(Long procesoId, Long groupShouldToBe) throws ProcesoNoEncontradoException, NoAutorizadoException {
        Proceso proceso = this.procesoRepository.findById(procesoId).orElseThrow(()-> new ProcesoNoEncontradoException(""));
        if(proceso.getGrupo().getId() != groupShouldToBe){
            throw new NoAutorizadoException("Se intento modificar un proceso de otro grupo");
        }

        return proceso;

    }

    @Override
    public void deleteProceso(Long procesoId, Long groupdShouldToBe) throws ProcesoNoEncontradoException, NoAutorizadoException {
        Proceso proceso = this.getProceso(procesoId,groupdShouldToBe);

        this.procesoRepository.delete(proceso);
    }
}
