package com.onebit.demoitp.infra.adapter.inbound.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Schema(name = "FechaDTO", description = "DTO para asignar/modificar la fecha de adhesion de una empresa")
@Getter
@Setter
@Builder
public class FechaDTO {

    @Schema(description = "Id de la empresa")
    @NotNull
    private Long idEmpresa;

    @Schema(description = "Fecha de adhesion de la empresa")
    @NotNull
    private LocalDate fechaAdhesion;
}
