package com.onebit.demoitp.infra.config;

import com.onebit.demoitp.domain.Empresa;
import com.onebit.demoitp.domain.Transferencia;
import com.onebit.demoitp.infra.adapter.outbound.EmpresaRepository;
import com.onebit.demoitp.infra.adapter.outbound.TransferenciaRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Log4j2
public class DataLoader implements CommandLineRunner {

    private final EmpresaRepository empresaRepository;
    private final TransferenciaRepository transferenciaRepository;
    private final EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {
        long cantEmpresas = empresaRepository.count();
        if(cantEmpresas == 0) {
            log.info("no hay empresas cargadas");
            log.info("cargando empresas...");
            cargarEmpresas();
            log.info("ok.");
        } else {
            log.info("hay {} empresas cargadas", cantEmpresas);
        }
        long cantTransferencias = transferenciaRepository.count();
        if(cantTransferencias == 0) {
            log.info("no hay transferencias cargadas");
            log.info("cargando transferencias...");
            cargarTransferencias();
            log.info("ok.");
        } else {
            log.info("hay {} transferencias cargadas", cantTransferencias);
        }
    }

    @Transactional
    private void cargarEmpresas() {
        empresaRepository.save(Empresa.builder()
                .cuit("30123456789")
                .razonSocial("Empresa Burger King")
                .fechaAdhesion(LocalDate.of(2025, 3, 28))
                .build());
        empresaRepository.save(Empresa.builder()
                .cuit("30987654321")
                .razonSocial("Empresa Wendys")
                .fechaAdhesion(LocalDate.of(2025, 3, 15))
                .build());
        empresaRepository.save(Empresa.builder()
                .cuit("30234567890")
                .razonSocial("Empresa McDonalds")
                .fechaAdhesion(LocalDate.of(2025, 3, 5))
                .build());
    }

    @Transactional
    private void cargarTransferencias() {
        transferenciaRepository.save(Transferencia.builder()
                .importe(new BigDecimal("1000"))
                .cuentaDebito("123456789")
                .cuentaCredito("987654321")
                .fechaTransferencia(LocalDate.of(2025, 3, 28))
                .empresa(empresaRepository.findById(3L).get())
                .build());

        transferenciaRepository.save(Transferencia.builder()
                .importe(new BigDecimal("2000"))
                .cuentaDebito("123456789")
                .cuentaCredito("987654321")
                .fechaTransferencia(LocalDate.of(2025, 1, 1))
                .empresa(empresaRepository.findById(2L).get())
                .build());

        transferenciaRepository.save(Transferencia.builder()
                .importe(new BigDecimal("1000"))
                .cuentaDebito("123456789")
                .cuentaCredito("987654321")
                .fechaTransferencia(LocalDate.of(2025, 3, 29))
                .empresa(empresaRepository.findById(1L).get())
                .build());
    }
}
