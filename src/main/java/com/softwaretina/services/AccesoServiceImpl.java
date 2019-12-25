package com.softwaretina.services;

import com.softwaretina.models.entities.Acceso;
import com.softwaretina.models.exception.CuentaNoEncontradaException;
import com.softwaretina.repository.AccesoRepository;
import com.softwaretina.services.auth.LogginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccesoServiceImpl implements AccesoService {

    @Autowired
    private AccesoRepository accesoRepository;

    @Autowired
    private LogginService logginService;

    @Override
    public Page<Acceso> getAccesos(int limit, int offset) throws CuentaNoEncontradaException {
        PageRequest pr = PageRequest.of(offset,limit);
        return this.accesoRepository.findAccesosByAccount(this.logginService.getLoggedAccount(),pr);
    }

    @Override
    public Acceso createAcceso(String ip) throws CuentaNoEncontradaException {
        Acceso acceso = new Acceso();
        acceso.setAccount(this.logginService.getLoggedAccount());
        acceso.setAt(LocalDateTime.now());
        acceso.setIp(ip);

        return this.accesoRepository.save(acceso);
    }
}
