package com.sky.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EmployPasswordDTO {
    private Long empId;
    private String newPassword;
    private String oldPassword;
}
