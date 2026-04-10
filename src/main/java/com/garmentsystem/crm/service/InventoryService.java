package com.garmentsystem.crm.service;

import com.garmentsystem.crm.dto.InventoryCreateDTO;
import com.garmentsystem.crm.dto.InventoryResponseDTO;
import com.garmentsystem.crm.dto.InventoryUpdateDTO;
import com.garmentsystem.crm.model.Inventory;
import com.garmentsystem.crm.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryResponseDTO create(InventoryCreateDTO dto) {
        Inventory inv = Inventory.builder()
                .itemName(dto.getItemName())
                .itemQuantity(dto.getItemQuantity())
                .itemPrice(dto.getItemPrice())
                .build();

        Inventory saved = inventoryRepository.save(inv);
        return toResponse(saved);
    }

    public List<InventoryResponseDTO> getAll() {
        return inventoryRepository.findAll().stream().map(this::toResponse).toList();
    }

    public InventoryResponseDTO getById(Long id) {
        Inventory inv = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
        return toResponse(inv);
    }

    /**
     * PATCH update: only applies non-null fields.
     */
    public InventoryResponseDTO update(Long id, InventoryUpdateDTO dto) {
        Inventory inv = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));

        if (dto.getItemName() != null) {
            inv.setItemName(dto.getItemName());
        }
        if (dto.getItemQuantity() != null) {
            inv.setItemQuantity(dto.getItemQuantity());
        }
        if (dto.getItemPrice() != null) {
            inv.setItemPrice(dto.getItemPrice());
        }

        Inventory saved = inventoryRepository.save(inv);
        return toResponse(saved);
    }

    public void delete(Long id) {
        Inventory inv = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
        inventoryRepository.delete(inv);
    }

    private InventoryResponseDTO toResponse(Inventory inv) {
        return InventoryResponseDTO.builder()
                .id(inv.getId())
                .itemName(inv.getItemName())
                .itemQuantity(inv.getItemQuantity())
                .itemPrice(inv.getItemPrice())
                .build();
    }
}

