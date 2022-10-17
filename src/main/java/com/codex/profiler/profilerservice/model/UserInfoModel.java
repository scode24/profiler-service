package com.codex.profiler.profilerservice.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserInfoModel {

    @NotBlank(message = "Name should be provided")
    private String name;

    @NotBlank(message = "Email should be provided")
    @Email(message = "Email should be valid")
    private String email;
}
