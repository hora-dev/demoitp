package com.onebit.demoitp.infra.adapter.inbound.controller;

import com.onebit.demoitp.application.service.DemoITPCasoDeUsoImpl;
import com.onebit.demoitp.infra.adapter.inbound.dto.FechaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "Demo ITP API", description = "Informacion sobre empresas y transferencias")
@RestController
@RequiredArgsConstructor
public class DemoITPController {

    private final DemoITPCasoDeUsoImpl demoITPCasoDeUsoImpl;

    @Operation(summary = "Obtener empresas con transferencias realizadas en el ultimo mes a partir de la fecha ingresada")
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

    @Operation(summary = "Obtener empresas con fecha de adhesion en el ultimo mes a partir de la fecha ingresada")
    @GetMapping("/empresasConAdhesionUltimoMes/{fechaActual}")
    public ResponseEntity<?> obtenerEmpresasConAdhesionUltimoMes(@PathVariable LocalDate fechaActual) {
        return ResponseEntity.ok(demoITPCasoDeUsoImpl.obtenerEmpresasConAdhesionUltimoMes(fechaActual));
    }

    @Operation(summary = "Asignar/Modificar la fecha de adhesion de una empresa")
    @PostMapping("/adhesionEmpresa")
    public ResponseEntity<?> adhesionEmpresa(@Valid @RequestBody FechaDTO fechaEmpresa, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.ok(demoITPCasoDeUsoImpl.adhesionEmpresa(fechaEmpresa));
    }
}
