package com.codex.profiler.profilerservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private Long idCandidate;
    @NotNull
    private String type;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String technology;
    @NotNull
    private String associatedCompany;

}
