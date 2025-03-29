package com.onebit.demoitp.infra.adapter.inbound.dto;

import com.onebit.demoitp.domain.Transferencia;

public class TransferenciaMapper {

    public static TransferenciaDTO toDTO(Transferencia transferencia) {
        return TransferenciaDTO.builder()
                .importe(transferencia.getImporte())
                .cuentaCredito(transferencia.getCuentaCredito())
                .cuentaDebito(transferencia.getCuentaDebito())
                .idEmpresa(transferencia.getEmpresa().getId())
                .fechaTransferencia(transferencia.getFechaTransferencia())
                .build();
    }
}
