package com.onebit.demoitp.infra.adapter.inbound.controller;

import com.onebit.demoitp.application.service.TransferenciaCasoDeUsoImpl;
import com.onebit.demoitp.infra.adapter.inbound.controller.utils.Utils;
import com.onebit.demoitp.infra.adapter.inbound.dto.TransferenciaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
            return Utils.validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(transferenciaDTO));
    }
}