package com.softwaretina.services;

import com.softwaretina.models.entities.Acceso;
import com.softwaretina.models.exception.CuentaNoEncontradaException;
import org.springframework.data.domain.Page;

public interface AccesoService {

    Page<Acceso> getAccesos(int limit, int offset) throws CuentaNoEncontradaException;

    Acceso createAcceso(Acceso acceso) throws CuentaNoEncontradaException;
}
