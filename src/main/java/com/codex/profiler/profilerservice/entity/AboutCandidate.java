package com.codex.profiler.profilerservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class AboutCandidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    @NotNull
    private Long idCandidate;
    @NotNull
    @Size(max = 2000)
    private String about;

    public AboutCandidate(long idCandidate, String about) {
        this.idCandidate = idCandidate;
        this.about = about;
    }
}
