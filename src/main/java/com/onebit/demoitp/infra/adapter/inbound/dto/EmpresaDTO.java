package com.onebit.demoitp.infra.adapter.inbound.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EmpresaDTO {

    private String cuit;

    @NotEmpty
    private String razonSocial;

    private LocalDate fechaAdhesion;
}
