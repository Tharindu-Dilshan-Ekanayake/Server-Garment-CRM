package com.garmentsystem.crm.dto;

import lombok.Data;

@Data
public class TaskCreateWithMembers {
    private String item;
    private Integer itemQuantity;
    private Long qmId;
    private Long groupLeaderId;
    private java.util.List<Long> groupMemberIds;
}
