package com.garmentsystem.crm.controller;

import com.garmentsystem.crm.dto.InventoryCreateDTO;
import com.garmentsystem.crm.dto.InventoryResponseDTO;
import com.garmentsystem.crm.dto.InventoryUpdateDTO;
import com.garmentsystem.crm.service.InventoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<InventoryResponseDTO> create(@Valid @RequestBody InventoryCreateDTO dto) {
        return ResponseEntity.ok(inventoryService.create(dto));
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<InventoryResponseDTO>> getAll() {
        return ResponseEntity.ok(inventoryService.getAll());
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<InventoryResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getById(id));
    }

    @PatchMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<InventoryResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody InventoryUpdateDTO dto
    ) {
        return ResponseEntity.ok(inventoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

