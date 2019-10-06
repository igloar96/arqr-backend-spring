package com.softwaretina.services;

import com.softwaretina.models.entities.Movimiento;
import com.softwaretina.models.exception.MovimientoNoEncontradoException;
import com.softwaretina.models.exception.NoAutorizadoException;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

public interface MovimientoService {
    Page<Movimiento> getMovimientos(Long grupoId, int limit, int offset) ;

    Movimiento createMovimiento(Movimiento Movimiento, Long grupoId)  ;

    @Transactional
    Movimiento updateMovimiento(Movimiento MovimientoToUpdate, Long groupShouldToBe) throws NoAutorizadoException, MovimientoNoEncontradoException;

    Movimiento getMovimiento(Long MovimientoId, Long groupShouldToBe) throws NoAutorizadoException, MovimientoNoEncontradoException, NoAutorizadoException;

    void deleteMovimiento(Long MovimientoId, Long groupdShouldToBe) throws NoAutorizadoException,MovimientoNoEncontradoException, NoAutorizadoException;

}
