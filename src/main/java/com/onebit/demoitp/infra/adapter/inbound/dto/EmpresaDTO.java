package com.onebit.demoitp.infra.adapter.inbound.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class EmpresaDTO {

    private String cuit;

    @NotEmpty
    private String razonSocial;

    private LocalDate fechaAdhesion;
}
