package com.codex.profiler.profilerservice.model;

import com.codex.profiler.profilerservice.entity.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ProfileInfoModel {

    @NotBlank
    private String about;
    @NotBlank
    private List<Skill> skills;
    @NotBlank
    private List<Project> projects;
    private List<WorkingExp> workingExperiences;
    private List<Achievement> achievements;
    private List<Qualification> qualification;
    @NotBlank
    private Contacts contacts;
    @NotBlank
    private List<Link> links;
}
