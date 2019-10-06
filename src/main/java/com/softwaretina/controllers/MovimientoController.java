package com.softwaretina.controllers;

import com.softwaretina.models.entities.Movimiento;
import com.softwaretina.services.MovimientoService;
import com.softwaretina.services.auth.LogginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/grupo/{grupo_id}")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private LogginService logginService;

    @RequestMapping(value = "/procesos/{proceso_id}/movimientos", method = RequestMethod.GET)
    Page<Movimiento> getAll(
            @PathVariable("grupo_id") Long grupoId,
            @PathVariable("proceso_id") Long procesoId,
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) throws Exception {

        return this.movimientoService.getMovimientos(grupoId, procesoId, limit, offset);

    }

    @PostMapping("/procesos/{proceso_id}/movimientos")
    Movimiento create(
            @PathVariable("grupo_id") Long grupoId,
            @PathVariable("proceso_id") Long procesoId,
            @RequestBody Movimiento movimiento) throws Exception {
        movimiento.setModifingAccount(this.logginService.getLoggedAccount());
        return this.movimientoService.createMovimiento(movimiento,procesoId, grupoId);
    }

    @PutMapping("/movimientos")
    Movimiento update(
            @PathVariable("grupo_id") Long grupoId,
            @RequestBody Movimiento movimiento) throws Exception {
        movimiento.setModifingAccount(this.logginService.getLoggedAccount());
        return this.movimientoService.updateMovimiento(movimiento, grupoId);
    }

    @DeleteMapping("/movimiento/{movimiento_id}")
    void delete(
            @PathVariable("grupo_id") Long grupoId,
            @PathVariable("movimiento_id") Long movimientoId) throws Exception {
        this.movimientoService.deleteMovimiento(movimientoId, grupoId);
    }
}
