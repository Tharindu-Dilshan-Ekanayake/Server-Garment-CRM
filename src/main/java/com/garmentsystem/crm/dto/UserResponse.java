package com.garmentsystem.crm.dto;

import com.garmentsystem.crm.model.Position;
import com.garmentsystem.crm.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private Position position;
}