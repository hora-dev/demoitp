package com.onebit.demoitp.infra.adapter.inbound.controller;

import com.onebit.demoitp.application.service.TransferenciaCasoDeUsoImpl;
import com.onebit.demoitp.infra.adapter.inbound.dto.TransferenciaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Transferencia CRUD API", description = "CREATE, READ transferencia")
@RestController
@RequiredArgsConstructor
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaCasoDeUsoImpl service;

    @Operation(summary = "Obtener transferencia por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        return service.porId(id)
                .map(transferencia -> ResponseEntity.ok(transferencia))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear transferencia")
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody TransferenciaDTO transferenciaDTO, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(transferenciaDTO));
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}