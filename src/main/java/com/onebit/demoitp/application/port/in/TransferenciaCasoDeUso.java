package com.onebit.demoitp.application.port.in;

import com.onebit.demoitp.domain.Transferencia;
import com.onebit.demoitp.infra.adapter.inbound.dto.TransferenciaDTO;

import java.util.Optional;

public interface TransferenciaCasoDeUso {
    Transferencia guardar(TransferenciaDTO transferencia);
    Optional<TransferenciaDTO> porId(Long id);
}
