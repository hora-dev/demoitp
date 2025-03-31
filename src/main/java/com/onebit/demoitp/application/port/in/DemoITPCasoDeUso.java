package com.onebit.demoitp.application.port.in;

import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaDTO;
import com.onebit.demoitp.infra.adapter.inbound.dto.FechaDTO;

import java.time.LocalDate;
import java.util.List;

public interface DemoITPCasoDeUso {

    List<EmpresaDTO> obtenerEmpresasConTransferenciasUltimoMes(LocalDate fechaActual);
    List<EmpresaDTO> obtenerEmpresasConAdhesionUltimoMes(LocalDate fechaActual);
    EmpresaDTO adhesionEmpresa(FechaDTO fechaEmpresa);
}
