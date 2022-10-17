package com.codex.profiler.profilerservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class PhoneNo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    @NotNull
    private Long idCandidate;
    @NotNull
    private String phoneNo;
}
