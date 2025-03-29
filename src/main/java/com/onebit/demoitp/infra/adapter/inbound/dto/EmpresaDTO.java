package com.onebit.demoitp.infra.adapter.inbound.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpresaDTO {

    private Long id;
    private String cuit;

    @NotEmpty
    private String razonSocial;

    private LocalDate fechaAdhesion;
}
