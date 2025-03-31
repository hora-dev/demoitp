package com.onebit.demoitp.infra.adapter.outbound;

import com.onebit.demoitp.domain.Empresa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {

    @Query
            (
                    value = "select id from empresa e where e.fecha_adhesion <= :fecha and" +
                            " e.fecha_adhesion >= DATE_SUB(:fecha, INTERVAL 1 MONTH)",
                    nativeQuery = true
            )
    List<Long> obtenerEmpresasConFechaAdhesionUltimoMes(LocalDate fecha);
}
