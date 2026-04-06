package com.garmentsystem.crm.dto;

import lombok.Data;

@Data
public class TaskUpdateDTO {

    private String item;
    private Integer itemQuantity;
    private Long groupLeaderId;
    private String status; // pass as string
}