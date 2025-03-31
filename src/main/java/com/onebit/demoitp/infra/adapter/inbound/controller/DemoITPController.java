package com.onebit.demoitp.infra.adapter.inbound.controller;

import com.onebit.demoitp.application.service.DemoITPCasoDeUsoImpl;
import com.onebit.demoitp.infra.adapter.inbound.controller.utils.Utils;
import com.onebit.demoitp.infra.adapter.inbound.dto.FechaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @Operation(summary = "Obtener empresas con fecha de adhesion en el ultimo mes a partir de la fecha ingresada")
    @GetMapping("/empresasConAdhesionUltimoMes/{fechaActual}")
    public ResponseEntity<?> obtenerEmpresasConAdhesionUltimoMes(@PathVariable LocalDate fechaActual) {
        return ResponseEntity.ok(demoITPCasoDeUsoImpl.obtenerEmpresasConAdhesionUltimoMes(fechaActual));
    }

    @Operation(summary = "Asignar/Modificar la fecha de adhesion de una empresa")
    @PostMapping("/adhesionEmpresa")
    public ResponseEntity<?> adhesionEmpresa(@Valid @RequestBody FechaDTO fechaEmpresa, BindingResult result) {
        if (result.hasErrors()) {
            return Utils.validar(result);
        }
        return ResponseEntity.ok(demoITPCasoDeUsoImpl.adhesionEmpresa(fechaEmpresa));
    }
}
