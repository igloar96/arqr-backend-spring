package com.softwaretina.controllers;

import com.softwaretina.models.entities.Acceso;
import com.softwaretina.services.AccesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/grupo/{grupo_id}")
public class AccesoController {

    @Autowired
    private AccesoService accesoService;

    //Get Accounts in my group el middleware validara roles
    @RequestMapping(value = "/accounts/accesos", method = RequestMethod.GET)
    Page<Acceso> getAll(
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) throws Exception {
        //get accesos by logged account
        return this.accesoService.getAccesos(limit, offset);

    }

    @PostMapping("/accounts/accesos")
    Acceso create(
            @RequestBody Acceso acceso) throws Exception {
        return this.accesoService.createAcceso(acceso);
    }

}
