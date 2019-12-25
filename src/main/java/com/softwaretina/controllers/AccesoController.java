package com.softwaretina.controllers;

import com.softwaretina.models.entities.Acceso;
import com.softwaretina.services.AccesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api")
public class AccesoController {

    @Autowired
    private Environment env;

    @Autowired
    private AccesoService accesoService;

    //Get Accounts in my group el middleware validara roles
    @RequestMapping(value = "/grupo/{grupo_id}/accounts/accesos", method = RequestMethod.GET)
    Page<Acceso> getAll(
            @RequestParam(value = "limit", defaultValue = "25") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) throws Exception {
        //get accesos by logged account
        return this.accesoService.getAccesos(limit, offset);

    }

    @GetMapping("/accounts/accesos/new")
    void create(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        this.accesoService.createAcceso(request.getRemoteAddr());
        httpServletResponse.setHeader("Location", this.env.getProperty("arqr.homepage"));
        httpServletResponse.setStatus(302);
    }

}
