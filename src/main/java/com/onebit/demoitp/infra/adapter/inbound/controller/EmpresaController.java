package com.onebit.demoitp.infra.adapter.inbound.controller;

import com.onebit.demoitp.application.service.EmpresaCasoDeUsoImpl;
import com.onebit.demoitp.infra.adapter.inbound.controller.utils.Utils;
import com.onebit.demoitp.infra.adapter.inbound.dto.EmpresaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Empresa CRUD API", description = "CREATE, READ empresa")
@RestController
@RequiredArgsConstructor
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaCasoDeUsoImpl service;

    @Operation(summary = "Obtener empresa por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        return service.porId(id)
                        .map(empresa -> ResponseEntity.ok(empresa))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear empresa")
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody EmpresaDTO empresaDTO, BindingResult result) {
        if(result.hasErrors()) {
            return Utils.validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(empresaDTO));
    }
}
