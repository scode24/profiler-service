package com.codex.profiler.profilerservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(max = 2000)
    private String description;
    @NotNull
    private String technology;
    @NotNull
    private String associatedCompany;

}
