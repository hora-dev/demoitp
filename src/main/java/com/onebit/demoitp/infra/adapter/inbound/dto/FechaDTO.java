package com.onebit.demoitp.infra.adapter.inbound.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class FechaDTO {
    private LocalDate fechaAdhesion;
    private Long idEmpresa;
}
