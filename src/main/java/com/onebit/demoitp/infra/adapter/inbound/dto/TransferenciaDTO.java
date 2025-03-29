package com.onebit.demoitp.infra.adapter.inbound.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransferenciaDTO {

    @PositiveOrZero
    private BigDecimal importe;

    private String cuentaDebito;
    private String cuentaCredito;

    @Positive
    private Long idEmpresa;

    @NotNull
    private LocalDate fechaTransferencia;
}
