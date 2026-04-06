package com.garmentsystem.crm.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String item;

    private Integer itemQuantity;

    private Long qmId;

    private Long groupLeaderId;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Integer finishedQuantity;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    @JsonManagedReference
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<TaskAssignment> taskAssignments;

}
