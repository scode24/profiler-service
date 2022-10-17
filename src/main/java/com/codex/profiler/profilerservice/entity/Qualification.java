package com.codex.profiler.profilerservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long idCandidate;
    @NotNull
    private String degree;
    @NotNull
    private String yearOfPassing;
    @NotNull
    private String institute;
    private String cgpaDgpaPercentage;
}
