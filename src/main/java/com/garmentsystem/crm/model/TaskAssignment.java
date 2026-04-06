package com.garmentsystem.crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupMemberId;

    private Integer completedQty;

    private Integer rejectedQty;

    private Integer approvedQty;




    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
