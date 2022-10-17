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
public class Menu {

    @NotNull
    private long idCandidate;
    @NotNull
    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Menu(long idCandidate, String title) {
        this.idCandidate = idCandidate;
        this.title = title;
    }
}
