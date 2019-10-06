package com.softwaretina.services;

import com.softwaretina.models.entities.Acceso;
import org.springframework.data.domain.Page;

public interface AccesoService {

    Page<Acceso> getAccesos(int limit, int offset) ;

    Acceso createAcceso(Acceso Acceso,Long grupoId)  ;


}
