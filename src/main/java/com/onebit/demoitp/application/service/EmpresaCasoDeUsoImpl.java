package com.onebit.demoitp.application.service;

import com.onebit.demoitp.application.port.in.EmpresaCasoDeUso;
import com.onebit.demoitp.infra.adapter.outbound.EmpresaRepository;
import com.onebit.demoitp.domain.Empresa;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaCasoDeUsoImpl implements EmpresaCasoDeUso {

    private final EmpresaRepository empresaRepository;

    @Override
    public Empresa guardar(EmpresaDTO empresaDTO) {
        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setCuit( empresaDTO.getCuit() );
        nuevaEmpresa.setRazonSocial( empresaDTO.getRazonSocial() );
        nuevaEmpresa.setFechaAdhesion( empresaDTO.getFechaAdhesion() );
        return empresaRepository.save( nuevaEmpresa );
    }

    @Override
    public Optional<Empresa> porId(Long id) {
        return empresaRepository.findById( id );
    }
}
