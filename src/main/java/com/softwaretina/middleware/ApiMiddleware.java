package com.softwaretina.middleware;

import com.softwaretina.models.exception.CuentaNoEncontradaException;
import com.softwaretina.services.auth.LogginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class ApiMiddleware extends OncePerRequestFilter {

    @Autowired
    private LogginService logginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // your code
        //request.getRequestURI() voy a /api/group/25
        String[] uriParts = request.getRequestURI().split("/");
        int groupPart = Arrays.asList(uriParts).indexOf("grupo");

        if (groupPart != -1) {
            if (this.tienePermisosEnGrupo(Long.parseLong(uriParts[groupPart + 1]))) {
                filterChain.doFilter(request, response);
            } else {
                response.sendError(403);
            }

        } else {
            //otra ruta sin grupos
            filterChain.doFilter(request, response);
        }


    }

    private boolean tienePermisosEnGrupo(long grupoId) {
        boolean permiso = false;

        try {
            if (this.logginService.getLoggedAccount().getGrupo().getId() == grupoId) {
                permiso = true;
            }
        } catch (CuentaNoEncontradaException e) {
            e.printStackTrace();
        }

        return permiso;

    }

}