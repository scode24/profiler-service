package com.codex.profiler.profilerservice.model;

import com.codex.profiler.profilerservice.entity.Project;
import com.codex.profiler.profilerservice.entity.ProjectImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectData {

    private Project projects;
    private List<ProjectImage> projectImages;
}
