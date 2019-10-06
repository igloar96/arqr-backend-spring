package com.softwaretina.repository;

import com.softwaretina.models.entities.Movimiento;
import com.softwaretina.models.entities.Proceso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {
    Page<Movimiento> findMovimientosByProceso(Proceso proceso, Pageable pr);
}
