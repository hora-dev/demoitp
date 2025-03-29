package com.onebit.demoitp.infra.adapter.outbound;

import com.onebit.demoitp.domain.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
}
