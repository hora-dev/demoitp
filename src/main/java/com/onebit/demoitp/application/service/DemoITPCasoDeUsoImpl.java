package com.onebit.demoitp.application.service;

import com.onebit.demoitp.application.port.in.DemoITPCasoDeUso;
import com.onebit.demoitp.application.service.exception.EmpresaNotFoundException;
import com.onebit.demoitp.domain.Empresa;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaDTO;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaMapper;
import com.onebit.demoitp.infra.adapter.inbound.dto.FechaDTO;
import com.onebit.demoitp.infra.adapter.outbound.EmpresaRepository;
import com.onebit.demoitp.infra.adapter.outbound.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoITPCasoDeUsoImpl implements DemoITPCasoDeUso {

    private final TransferenciaRepository transferenciaRepository;
    private final EmpresaRepository empresaRepository;

    @Override
    @Transactional
    public List<EmpresaDTO> obtenerEmpresasConTransferenciasUltimoMes(LocalDate fecha) {
        List<Long> ids = transferenciaRepository.obtenerEmpresasConTransferenciasUltimoMes(fecha);
        List<Empresa> empresaList = (List<Empresa>) empresaRepository.findAllById(ids);
        return empresaList.stream().map(
                empresa -> EmpresaMapper.toDTO(empresa)
        ).toList();
    }

    @Override
    @Transactional
    public List<EmpresaDTO> obtenerEmpresasConAdhesionUltimoMes(LocalDate fecha) {
        List<Long> ids = empresaRepository.obtenerEmpresasConFechaAdhesionUltimoMes(fecha);
        List<Empresa> empresaList = (List<Empresa>) empresaRepository.findAllById(ids);
        return empresaList.stream().map(
                empresa -> EmpresaMapper.toDTO(empresa)
        ).toList();
    }

    @Override
    @Transactional
    public EmpresaDTO adhesionEmpresa(FechaDTO fechaEmpresa) {
        Empresa empresaBD = empresaRepository
                .findById(fechaEmpresa.getIdEmpresa())
                .orElseThrow( () -> new EmpresaNotFoundException("Empresa con id " + fechaEmpresa.getIdEmpresa() + " no encontrada") );
        empresaBD.setFechaAdhesion(fechaEmpresa.getFechaAdhesion());
        return EmpresaMapper.toDTO(empresaRepository.save(empresaBD));
    }
}
