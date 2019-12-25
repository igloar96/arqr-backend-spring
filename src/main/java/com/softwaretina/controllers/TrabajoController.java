package com.softwaretina.controllers;

import com.softwaretina.models.entities.Proceso;
import com.softwaretina.services.ProcesoService;
import com.softwaretina.services.auth.LogginService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/grupo/{grupo_id}")
public class TrabajoController {

    @Autowired
    private ProcesoService procesoService;

    @Autowired
    private LogginService logginService;

    @RequestMapping(value = "/trabajos", method = RequestMethod.GET)
    Page<Proceso> getAll(
            @PathVariable("grupo_id") Long grupoId,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "from", required = false) Date from,
            @RequestParam(value = "to", required = false) Date to,
            @RequestParam(value = "tags", required = false) Long[] tags) throws Exception {

        return this.procesoService.getProcesos(grupoId, limit, offset,from,to,tags);

    }
    
    @RequestMapping(value = "/trabajos", method = RequestMethod.GET, params = {"search"})
    Page<Proceso> getAllBySearch(
            @PathVariable("grupo_id") Long grupoId,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "search", required = true) String search,
            @RequestParam(value = "from", required = false) Date from,
            @RequestParam(value = "from", required = false) Date to,
            @RequestParam(value = "tags", required = false) Long[] tags) throws Exception {

        return this.procesoService.getProcesos(grupoId, limit, offset,from,to,search,tags);

    }

    @PostMapping("/trabajos")
    Proceso create(
            @PathVariable("grupo_id") Long grupoId,
            @RequestBody Proceso proceso) throws Exception {
        proceso.setModifingAccount(this.logginService.getLoggedAccount());
        return this.procesoService.createProceso(proceso, grupoId);
    }

    @PutMapping("/trabajos/{id}")
    Proceso update(
            @PathVariable("grupo_id") Long grupoId,
            @RequestBody Proceso proceso) throws Exception {
        proceso.setModifingAccount(this.logginService.getLoggedAccount());
        return this.procesoService.updateProceso(proceso, grupoId);
    }

    @DeleteMapping("/trabajos/{id}")
    void delete(
            @PathVariable("grupo_id") Long grupoId,
            @PathVariable("id") Long procesoId) throws Exception {
        this.procesoService.deleteProceso(procesoId, grupoId);
    }
}
