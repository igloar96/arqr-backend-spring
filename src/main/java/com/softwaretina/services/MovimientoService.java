package com.softwaretina.services;

import com.softwaretina.models.entities.Movimiento;
import com.softwaretina.models.exception.MovimientoNoEncontradoException;
import com.softwaretina.models.exception.NoAutorizadoException;
import com.softwaretina.models.exception.ProcesoNoEncontradoException;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

public interface MovimientoService {

    Page<Movimiento> getMovimientos(Long groupShouldToBe, Long procesoId, int limit, int offset) throws NoAutorizadoException, ProcesoNoEncontradoException;

    Movimiento createMovimiento(Movimiento movimiento, Long procesoId, Long groupShouldToBe) throws ProcesoNoEncontradoException, NoAutorizadoException;

    @Transactional
    Movimiento updateMovimiento(Movimiento MovimientoToUpdate, Long groupShouldToBe) throws NoAutorizadoException, MovimientoNoEncontradoException;

    Movimiento getMovimiento(Long MovimientoId, Long groupShouldToBe) throws MovimientoNoEncontradoException, NoAutorizadoException;

    void deleteMovimiento(Long MovimientoId, Long groupdShouldToBe) throws MovimientoNoEncontradoException, NoAutorizadoException;

}
