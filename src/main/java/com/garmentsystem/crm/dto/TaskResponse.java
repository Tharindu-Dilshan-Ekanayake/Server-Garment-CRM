package com.garmentsystem.crm.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TaskResponse {

    private Long taskId;
    private String item;
    private Integer itemQuantity;
    private Long qmId;
    private Long groupLeaderId;
    private String status;
    private Integer finishedQuantity;
    private LocalDateTime createdAt;

    private List<TaskAssignmentDTO> assignments;

}
