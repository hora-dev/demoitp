package com.onebit.demoitp.service;


import com.onebit.demoitp.application.service.EmpresaCasoDeUsoImpl;
import com.onebit.demoitp.domain.Empresa;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaDTO;
import com.onebit.demoitp.infra.adapter.outbound.EmpresaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmpresaCasoDeUsoImplTest {

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaCasoDeUsoImpl empresaCasoDeUso;

    private EmpresaDTO empresaDTO;
    private Empresa empresa;

    @BeforeEach
    void setUp() {
        // Configuración de datos de prueba
        LocalDate fechaAdhesion = LocalDate.of(2024, 3, 28);

        empresaDTO = new EmpresaDTO();
        empresaDTO.setCuit("30123456789");
        empresaDTO.setRazonSocial("Empresa Burger King");
        empresaDTO.setFechaAdhesion(fechaAdhesion);

        empresa = Empresa.builder()
                .id(1L)
                .cuit("30123456789")
                .razonSocial("Empresa Burger King")
                .fechaAdhesion(fechaAdhesion)
                .build();
    }

    @Test
    void guardarEmpresaOK() {
        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        Empresa empresaGuardada = empresaCasoDeUso.guardar(empresaDTO);

        assertNotNull(empresaGuardada);
        assertEquals(1L, empresaGuardada.getId());
        assertEquals("30123456789", empresaGuardada.getCuit());
        assertEquals("Empresa Burger King", empresaGuardada.getRazonSocial());
        verify(empresaRepository, times(1)).save(any(Empresa.class));
    }

    @Test
    void buscarPorIdQueExiste() {
        Long id = 1L;
        when(empresaRepository.findById(id)).thenReturn(Optional.of(empresa));

        Optional<Empresa> resultado = empresaCasoDeUso.porId(id);

        assertTrue(resultado.isPresent());
        assertEquals(empresa.getId(), resultado.get().getId());
        assertEquals(empresa.getCuit(), resultado.get().getCuit());
        assertEquals(empresa.getRazonSocial(), resultado.get().getRazonSocial());
        verify(empresaRepository, times(1)).findById(id);
    }

    @Test
    void buscarPorIdQueNoExiste() {
        Long id = 999L;
        when(empresaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Empresa> resultado = empresaCasoDeUso.porId(id);

        assertFalse(resultado.isPresent());
        verify(empresaRepository, times(1)).findById(id);
    }
}