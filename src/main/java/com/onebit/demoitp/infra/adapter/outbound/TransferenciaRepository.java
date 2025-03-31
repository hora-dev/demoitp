package com.onebit.demoitp.infra.adapter.outbound;

import com.onebit.demoitp.domain.Empresa;
import com.onebit.demoitp.domain.Transferencia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransferenciaRepository extends CrudRepository<Transferencia, Long> {

    @Query
    (
            value = "select id_empresa from transferencia t where t.fecha_transferencia <= :fecha and" +
                    " t.fecha_transferencia >= :fecha - interval '1' month",
            nativeQuery = true
    )
    List<Long> obtenerEmpresasConTransferenciasUltimoMes(LocalDate fecha);

}
