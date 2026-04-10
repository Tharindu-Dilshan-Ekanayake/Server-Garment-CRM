package com.garmentsystem.crm.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

/**
 * DTO used for PATCH updates to Inventory.
 * All fields are optional; only non-null fields will be applied.
 */
@Data
public class InventoryUpdateDTO {

    private String itemName;

    @PositiveOrZero(message = "Item quantity must be 0 or greater")
    private Integer itemQuantity;

    @Positive(message = "Item price must be greater than 0")
    private Double itemPrice;
}

