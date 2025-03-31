package com.onebit.demoitp.application.service;

import com.onebit.demoitp.application.port.in.EmpresaCasoDeUso;
import com.onebit.demoitp.infra.adapter.outbound.EmpresaRepository;
import com.onebit.demoitp.domain.Empresa;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaCasoDeUsoImpl implements EmpresaCasoDeUso {

    private final EmpresaRepository empresaRepository;

    @Override
    @Transactional
    public Empresa guardar(EmpresaDTO empresaDTO) {
        Empresa nuevaEmpresa = Empresa.builder()
                .cuit( empresaDTO.getCuit() )
                .razonSocial( empresaDTO.getRazonSocial() )
                .fechaAdhesion( empresaDTO.getFechaAdhesion() )
                .build();
        return empresaRepository.save( nuevaEmpresa );
    }

    @Override
    @Transactional
    public Optional<Empresa> porId(Long id) {
        return empresaRepository.findById( id );
    }
}
