package com.garmentsystem.crm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class InventoryCreateDTO {
    @NotBlank(message = "Item name is required")
    private String itemName;

    @NotNull(message = "Item quantity is required")
    @PositiveOrZero(message = "Item quantity must be 0 or greater")
    private Integer itemQuantity;
    
    @Positive(message = "Item price must be greater than 0")
    private double itemPrice;
}
