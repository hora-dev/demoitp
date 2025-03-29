package com.onebit.demoitp.application.service;

import com.onebit.demoitp.application.port.in.DemoITPCasoDeUso;
import com.onebit.demoitp.domain.Empresa;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaDTO;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaMapper;
import com.onebit.demoitp.infra.adapter.outbound.EmpresaRepository;
import com.onebit.demoitp.infra.adapter.outbound.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoITPCasoDeUsoImpl implements DemoITPCasoDeUso {

    private final TransferenciaRepository transferenciaRepository;
    private final EmpresaRepository empresaRepository;

    @Override
    public List<EmpresaDTO> obtenerEmpresasConTransferenciasUltimoMes(LocalDate fecha) {
        List<Long> ids = transferenciaRepository.obtenerEmpresasConTransferenciasUltimoMes(fecha);
        List<Empresa> empresaList = (List<Empresa>) empresaRepository.findAllById(ids);
        return empresaList.stream().map(
                empresa -> EmpresaMapper.toDTO(empresa)
        ).toList();
    }

    @Override
    public List<EmpresaDTO> obtenerEmpresasConAdhesionUltimoMes(LocalDate fecha) {
        List<Long> ids = transferenciaRepository.obtenerEmpresasConFechaAdhesionUltimoMes(fecha);
        List<Empresa> empresaList = (List<Empresa>) empresaRepository.findAllById(ids);
        return empresaList.stream().map(
                empresa -> EmpresaMapper.toDTO(empresa)
        ).toList();
    }

    @Override
    public boolean adhesionEmpresa(EmpresaDTO empresa) {
        return false;
    }
}
