package com.garmentsystem.crm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponseDTO {
    private Long id;
    private String itemName;
    private Integer itemQuantity;
    private double itemPrice;
}
