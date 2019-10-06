package com.softwaretina.controllers;

import com.softwaretina.models.entities.Proceso;
import com.softwaretina.services.ProcesoService;
import com.softwaretina.services.auth.LogginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/grupo/{grupo_id}")
public class ProcesoController {

    @Autowired
    private ProcesoService procesoService;

    @Autowired
    private LogginService logginService;

    @RequestMapping(value = "/procesos", method = RequestMethod.GET)
    Page<Proceso> getAll(
            @PathVariable("grupo_id") Long grupoId,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) throws Exception {

        return this.procesoService.getProcesos(grupoId, limit, offset);

    }

    @PostMapping("/procesos")
    Proceso create(
            @PathVariable("grupo_id") Long grupoId,
            @RequestBody Proceso proceso) throws Exception {
        proceso.setModifingAccount(this.logginService.getLoggedAccount());
        return this.procesoService.createProceso(proceso, grupoId);
    }

    @PutMapping("/procesos/{id}")
    Proceso update(
            @PathVariable("grupo_id") Long grupoId,
            @RequestBody Proceso proceso) throws Exception {
        proceso.setModifingAccount(this.logginService.getLoggedAccount());
        return this.procesoService.updateProceso(proceso, grupoId);
    }

    @DeleteMapping("/procesos/{id}")
    void delete(
            @PathVariable("grupo_id") Long grupoId,
            @PathVariable("id") Long procesoId) throws Exception {
        this.procesoService.deleteProceso(procesoId, grupoId);
    }
}
