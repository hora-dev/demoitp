package com.onebit.demoitp.infra.adapter.outbound;

import com.onebit.demoitp.domain.Transferencia;
import org.springframework.data.repository.CrudRepository;

public interface TransferenciaRepository extends CrudRepository<Transferencia, Long> {
}
