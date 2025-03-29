package com.onebit.demoitp.infra.adapter.inbound.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class TransferenciaDTO {

    @NotEmpty
    private BigDecimal importe;

    private String cuentaDebito;
    private String cuentaCredito;
    private LocalDateTime fechaTransferencia;

    @NotEmpty
    private Long idEmpresa;
}
