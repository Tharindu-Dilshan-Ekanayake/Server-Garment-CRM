package com.garmentsystem.crm.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskAssignmentDTO {

    private Long id;
    private Long groupMemberId;
    private Integer completedQty;
    private Integer rejectedQty;
    private Integer approvedQty;
}