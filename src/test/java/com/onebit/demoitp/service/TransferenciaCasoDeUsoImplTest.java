package com.onebit.demoitp.service;

import com.onebit.demoitp.application.service.TransferenciaCasoDeUsoImpl;
import com.onebit.demoitp.domain.Empresa;
import com.onebit.demoitp.domain.Transferencia;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaDTO;
import com.onebit.demoitp.infra.adapter.inbound.dto.TransferenciaDTO;
import com.onebit.demoitp.infra.adapter.outbound.EmpresaRepository;
import com.onebit.demoitp.infra.adapter.outbound.TransferenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferenciaCasoDeUsoImplTest {

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private TransferenciaCasoDeUsoImpl transferenciaCasoDeUsoImpl;

    private TransferenciaDTO transferenciaDTO;
    private Transferencia transferencia;
    private Empresa empresa;
    private EmpresaDTO empresaDTO;


    @BeforeEach
    void setUp() {
        LocalDate fechaAdhesion = LocalDate.of(2024, 3, 28);

        empresa = Empresa.builder()
                .id(1L)
                .cuit("30123456789")
                .razonSocial("Empresa Burger King")
                .fechaAdhesion(fechaAdhesion)
                .build();

        empresaDTO = new EmpresaDTO();
        empresaDTO.setCuit("30123456789");
        empresaDTO.setRazonSocial("Empresa Burger King");
        empresaDTO.setFechaAdhesion(fechaAdhesion);

        transferenciaDTO = TransferenciaDTO.builder()
                        .importe(new BigDecimal("1000.0"))
                        .cuentaDebito("123456789")
                        .cuentaCredito("987654321")
                                .idEmpresa(1L)
                                .build();


        transferencia = Transferencia.builder()
                        .importe(new BigDecimal("1000.0"))
                        .cuentaDebito("123456789")
                        .cuentaCredito("987654321")
                                .empresa(empresa)
                                .build();


    }

    @Test
    void guardarTransferenciaOK() {
        when(empresaRepository.findById(transferenciaDTO.getIdEmpresa())).thenReturn(Optional.of(empresa));
        when(transferenciaRepository.save(any(Transferencia.class))).thenReturn(transferencia);

        Transferencia transferenciaGuardada = transferenciaCasoDeUsoImpl.guardar(transferenciaDTO);

        assertNotNull(transferenciaGuardada);
        assertEquals(transferencia.getEmpresa(), transferenciaGuardada.getEmpresa());
        assertEquals(transferencia.getImporte(), transferenciaGuardada.getImporte());
        assertEquals(transferencia.getCuentaCredito(), transferenciaGuardada.getCuentaCredito());
        assertEquals(transferencia.getCuentaDebito(), transferenciaGuardada.getCuentaDebito());
        verify(transferenciaRepository, times(1)).save(any(Transferencia.class));
    }

    @Test
    void buscarPorIdQueExiste() {
        Long id = 1L;
        when(transferenciaRepository.findById(id)).thenReturn(Optional.of(transferencia));

        Optional<TransferenciaDTO> resultado = transferenciaCasoDeUsoImpl.porId(id);

        assertTrue(resultado.isPresent());
        assertEquals(empresa.getId(), resultado.get().getIdEmpresa());
        assertEquals(transferenciaDTO.getImporte(), resultado.get().getImporte());
        assertEquals(transferenciaDTO.getCuentaCredito(), resultado.get().getCuentaCredito());
        assertEquals(transferenciaDTO.getCuentaDebito(), resultado.get().getCuentaDebito());
        verify(transferenciaRepository, times(1)).findById(id);
    }

    @Test
    void buscarPorIdQueNoExiste() {
        Long id = 999L;
        when(transferenciaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<TransferenciaDTO> resultado = transferenciaCasoDeUsoImpl.porId(id);

        assertFalse(resultado.isPresent());
        verify(transferenciaRepository, times(1)).findById(id);
    }

}
