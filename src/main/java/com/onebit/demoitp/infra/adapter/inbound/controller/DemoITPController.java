package com.onebit.demoitp.infra.adapter.inbound.controller;

import com.onebit.demoitp.application.service.DemoITPCasoDeUsoImpl;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaDTO;
import com.onebit.demoitp.infra.adapter.inbound.dto.FechaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DemoITPController {

    private final DemoITPCasoDeUsoImpl demoITPCasoDeUsoImpl;

    @GetMapping("/empresasConTransferenciasUltimoMes/{fechaActual}")
    public ResponseEntity<?> obtenerEmpresasConTransferenciasUltimoMes(@PathVariable LocalDate fechaActual) {
        return ResponseEntity.ok(demoITPCasoDeUsoImpl.obtenerEmpresasConTransferenciasUltimoMes(fechaActual));
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @GetMapping("/empresasConAdhesionUltimoMes/{fechaActual}")
    public ResponseEntity<?> obtenerEmpresasConAdhesionUltimoMes(@PathVariable LocalDate fechaActual) {
        return ResponseEntity.ok(demoITPCasoDeUsoImpl.obtenerEmpresasConAdhesionUltimoMes(fechaActual));
    }

    @PostMapping("/adhesionEmpresa")
    public ResponseEntity<?> adhesionEmpresa(@Valid @RequestBody FechaDTO fechaEmpresa, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.ok(demoITPCasoDeUsoImpl.adhesionEmpresa(fechaEmpresa));
    }
}
