package com.codex.profiler.profilerservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    @NotNull
    private Long idCandidate;
    @NotNull
    @NotBlank
    private String skill;
    @NotNull
    @NotBlank
    private String rating;
}
