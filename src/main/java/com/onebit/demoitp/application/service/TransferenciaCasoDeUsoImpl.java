package com.onebit.demoitp.application.service;

import com.onebit.demoitp.application.port.in.TransferenciaCasoDeUso;
import com.onebit.demoitp.application.service.exception.EmpresaNotFoundException;
import com.onebit.demoitp.domain.Empresa;
import com.onebit.demoitp.domain.Transferencia;
import com.onebit.demoitp.infra.adapter.inbound.dto.TransferenciaDTO;
import com.onebit.demoitp.infra.adapter.inbound.dto.TransferenciaMapper;
import com.onebit.demoitp.infra.adapter.outbound.EmpresaRepository;
import com.onebit.demoitp.infra.adapter.outbound.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransferenciaCasoDeUsoImpl implements TransferenciaCasoDeUso {

    private final TransferenciaRepository transferenciaRepository;
    private final EmpresaRepository empresaRepository;

    @Override
    public Transferencia guardar(TransferenciaDTO transferenciaDTO) {
        Empresa empresa = empresaRepository
                .findById(transferenciaDTO.getIdEmpresa())
                .orElseThrow(() -> new EmpresaNotFoundException("Empresa con id " + transferenciaDTO.getIdEmpresa() + " no encontrada"));
        Transferencia nuevaTransferencia = Transferencia.builder()
                .empresa(empresa)
                .importe(transferenciaDTO.getImporte())
                .cuentaCredito(transferenciaDTO.getCuentaCredito())
                .cuentaDebito(transferenciaDTO.getCuentaDebito())
                .fechaTransferencia(transferenciaDTO.getFechaTransferencia())
                .build();
        return transferenciaRepository.save(nuevaTransferencia);
    }

    @Override
    public Optional<TransferenciaDTO> porId(Long id) {
        return transferenciaRepository.findById(id)
                .map(transferencia -> TransferenciaMapper.toDTO(transferencia));
    }
}
