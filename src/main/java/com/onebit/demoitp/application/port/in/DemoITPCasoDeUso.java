package com.onebit.demoitp.application.port.in;

import com.onebit.demoitp.domain.Empresa;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaDTO;

import java.util.List;

public interface DemoITPCasoDeUso {

    List<EmpresaDTO> obtenerEmpresasConTransferenciasUltimoMes();
    List<Empresa> obtenerEmpresasConAdhesionUltimoMes();
    boolean adhesionEmpresa(Empresa empresa);
}
