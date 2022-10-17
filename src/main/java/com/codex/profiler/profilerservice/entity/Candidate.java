package com.codex.profiler.profilerservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @Lob
    private byte[] image;
    @NotNull
    private String accessToken;

    public Candidate(String name, String email, byte[] image, String accessToken) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.accessToken = accessToken;
    }
}
