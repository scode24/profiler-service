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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private long idCandidate;
    @NotNull
    private String title;
    @NotNull
    private int menuOrder;

    public Menu(long idCandidate, String title, int menuOrder) {
        this.idCandidate = idCandidate;
        this.title = title;
        this.menuOrder = menuOrder;
    }
}
