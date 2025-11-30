package com.gerenciamento.infrastructure.controller;

import com.gerenciamento.domain.applicationservice.ProjectService;
import com.gerenciamento.domain.entity.Project;
import com.gerenciamento.infrastructure.dto.ProjectDTO;
import com.gerenciamento.infrastructure.dto.SaveProjectDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.gerenciamento.infrastructure.controller.RestConstants.PATH_PROJECTS;

@RestController
@RequestMapping(PATH_PROJECTS)
@RequiredArgsConstructor
public class ProjectRestResource {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody SaveProjectDataDTO saveProjectData) {
        Project project = projectService.createProject(saveProjectData);

        return ResponseEntity
                .created(URI.create(PATH_PROJECTS + project.getId()))
                .body(ProjectDTO.create(project));
    }
}
