package com.softwaretina.services;

import com.softwaretina.models.entities.Movimiento;
import com.softwaretina.models.entities.Proceso;
import com.softwaretina.models.exception.MovimientoNoEncontradoException;
import com.softwaretina.models.exception.NoAutorizadoException;
import com.softwaretina.models.exception.ProcesoNoEncontradoException;
import com.softwaretina.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private ProcesoService procesoService;

    @Override
    public Page<Movimiento> getMovimientos(Long groupShouldToBe, Long procesoId, int limit, int offset) throws ProcesoNoEncontradoException, NoAutorizadoException {
        PageRequest pr = PageRequest.of(offset,limit);
        Proceso proceso = this.procesoService.getProceso(procesoId,groupShouldToBe);
        return this.movimientoRepository.findMovimientosByProceso(proceso,pr);
    }

    @Override
    public Movimiento createMovimiento(Movimiento movimiento, Long procesoId, Long groupShouldToBe) throws ProcesoNoEncontradoException, NoAutorizadoException {
        Proceso proceso = this.procesoService.getProceso(procesoId,groupShouldToBe);
        movimiento.setProceso(proceso);
        return this.movimientoRepository.save(movimiento);
    }

    @Override
    public Movimiento updateMovimiento(Movimiento movimientoToUpdate, Long groupShouldToBe) throws NoAutorizadoException, MovimientoNoEncontradoException {
        Movimiento movimiento = this.getMovimiento(movimientoToUpdate.getId(),groupShouldToBe);
        return this.movimientoRepository.save(movimientoToUpdate);
    }

    @Override
    public Movimiento getMovimiento(Long idMovimiento, Long groupShouldToBe) throws MovimientoNoEncontradoException, NoAutorizadoException {
        Movimiento movimiento = this.movimientoRepository.findById(idMovimiento).orElseThrow(()->new MovimientoNoEncontradoException(""));
        if(movimiento.getProceso().getGrupo().getId() != groupShouldToBe){
            throw new NoAutorizadoException("GRAVE: se intenta crear movimiento en otro grupo");
        }
        return movimiento;
    }

    @Override
    public void deleteMovimiento(Long movimientoId, Long groupdShouldToBe) throws MovimientoNoEncontradoException, NoAutorizadoException {
        Movimiento movimiento = this.getMovimiento(movimientoId,groupdShouldToBe);
        this.movimientoRepository.delete(movimiento);

    }
}
