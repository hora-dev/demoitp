package com.onebit.demoitp.application.port.in;

import com.onebit.demoitp.domain.Empresa;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaDTO;

import java.util.Optional;

public interface EmpresaCasoDeUso {
    Empresa guardar(EmpresaDTO empresa);
    Optional<Empresa> porId(Long id);
}
