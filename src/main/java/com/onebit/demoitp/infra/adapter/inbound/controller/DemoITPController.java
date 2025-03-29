package com.onebit.demoitp.infra.adapter.inbound.controller;

import com.onebit.demoitp.application.service.DemoITPCasoDeUsoImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DemoITPController {

    private final DemoITPCasoDeUsoImpl demoITPCasoDeUsoImpl;


    @PostMapping("/obtenerEmpresasConTransferenciasUltimoMes/{fechaActual}")
    public ResponseEntity<?> obtenerEmpresasConTransferenciasUltimoMes(@PathVariable LocalDate fechaActual) {
       /* if (result.hasErrors()) {
            return validar(result);
        }*/
        return ResponseEntity.ok(demoITPCasoDeUsoImpl.obtenerEmpresasConTransferenciasUltimoMes(fechaActual));
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @PostMapping("/obtenerEmpresasConAdhesionUltimoMes/{fechaActual}")
    public ResponseEntity<?> obtenerEmpresasConAdhesionUltimoMes(@PathVariable LocalDate fechaActual) {
        return ResponseEntity.ok(demoITPCasoDeUsoImpl.obtenerEmpresasConAdhesionUltimoMes(fechaActual));
    }

}
