package com.example.staff_project.controller;

import com.example.staff_project.entity.Project;
import com.example.staff_project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/projects")
    public ResponseEntity<List<Project>> getProjects() throws Exception {
        List<Project> projects = projectService.getProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping(value = "/projects/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable String projectId) throws Exception {
        Project project = projectService.getProject(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping(value = "/projects")
    public ResponseEntity<Project> createProject(@RequestBody Project project) throws Exception {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PutMapping(value = "/projects/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable String projectId, @RequestBody Project project) throws Exception {
        Project updatedProject = projectService.updateProject(projectId, project);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping(value = "/projects/{projectId}")
    public ResponseEntity<Project> deleteProject(@PathVariable String projectId) throws Exception {
        Project deletedProject = projectService.deleteProject(projectId);
        return new ResponseEntity<>(deletedProject, HttpStatus.OK);
    }
}
