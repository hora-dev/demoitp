package com.onebit.demoitp.service;

import com.onebit.demoitp.application.service.DemoITPCasoDeUsoImpl;
import com.onebit.demoitp.domain.Empresa;
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

import java.time.LocalDate;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DemoItPCasoDeUsoImplTest {

    @Mock
    TransferenciaRepository transferenciaRepository;

    @Mock
    EmpresaRepository empresaRepository;


    @InjectMocks
    DemoITPCasoDeUsoImpl demoITPCasoDeUsoImpl;


    EmpresaDTO empresaDTO1, empresaDTO2, empresaDTO3;
    Empresa empresa1, empresa2, empresa3;

    TransferenciaDTO transferenciaDTO1, transferenciaDTO2, transferenciaDTO3;

    LocalDate currentDate;

    @BeforeEach
    public void setUp() {
        empresaDTO1 = new EmpresaDTO();
        empresaDTO1.setRazonSocial("Empresa Burger King");
        empresaDTO1.setCuit("30123456789");
        empresaDTO1.setFechaAdhesion(LocalDate.of(2025, 3, 28));

        empresaDTO2 = new EmpresaDTO();
        empresaDTO2.setRazonSocial("Empresa Wendys");
        empresaDTO2.setCuit("30987654321");
        empresaDTO2.setFechaAdhesion(LocalDate.of(2025, 3, 15));

        empresaDTO3 = new EmpresaDTO();
        empresaDTO3.setRazonSocial("Empresa McDonalds");
        empresaDTO3.setCuit("30234567890");
        empresaDTO3.setFechaAdhesion(LocalDate.of(2025, 3, 5));

        currentDate = LocalDate.of(2025, 3, 27);

        empresa1 = new Empresa();
        empresa2 = new Empresa();
        empresa3 = new Empresa();

        /*transferenciaDTO1 = new TransferenciaDTO();
        transferenciaDTO2 = new TransferenciaDTO();
        transferenciaDTO3 = new TransferenciaDTO();*/

    }

    @Test
    public void obtenerEmpresasConTransferenciasUltimoMesOK() {
        when(transferenciaRepository.obtenerEmpresasConTransferenciasUltimoMes(currentDate)).thenReturn(List.of(1L, 2L, 3L));
        when(empresaRepository.findAllById(any())).thenReturn(List.of(empresa1, empresa2, empresa3));

        List<EmpresaDTO> empresaDTOList = demoITPCasoDeUsoImpl.obtenerEmpresasConTransferenciasUltimoMes(currentDate);

        assertNotNull(empresaDTOList);
        assertNotEmpty(empresaDTOList, "No se encontraron empresas con transferencias");
        verify(transferenciaRepository, times(1)).obtenerEmpresasConTransferenciasUltimoMes(currentDate);
    }

    @Test
    public void obtenerEmpresasConAdhesionUltimoMesOK() {
        when(transferenciaRepository.obtenerEmpresasConFechaAdhesionUltimoMes(currentDate)).thenReturn(List.of(1L, 2L, 3L));
        when(empresaRepository.findAllById(any())).thenReturn(List.of(empresa1, empresa2, empresa3));

        List<EmpresaDTO> empresaIdList = demoITPCasoDeUsoImpl.obtenerEmpresasConAdhesionUltimoMes(currentDate);

        assertNotNull(empresaIdList);
        assertNotEmpty(empresaIdList, "No se encontraron empresas con adhesion en el ultimo mes");
        verify(transferenciaRepository, times(1)).obtenerEmpresasConFechaAdhesionUltimoMes(currentDate);
    }

}
