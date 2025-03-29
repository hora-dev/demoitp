package com.onebit.demoitp.infra.adapter.inbound.dto;

import com.onebit.demoitp.domain.Empresa;

public class EmpresaMapper {
    public static EmpresaDTO toDTO(Empresa empresa) {
        return EmpresaDTO.builder()
                .id( empresa.getId() )
                .cuit( empresa.getCuit() )
                .razonSocial( empresa.getRazonSocial() )
                .fechaAdhesion( empresa.getFechaAdhesion() )
                .build();
        }
}
