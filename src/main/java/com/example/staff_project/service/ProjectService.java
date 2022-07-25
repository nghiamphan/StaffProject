package com.example.staff_project.service;

import com.example.staff_project.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getProjects() throws Exception;
    Project getProject(String projectId) throws Exception;
    Project createProject(Project project) throws Exception;
    Project updateProject(String projectId, Project updatedProject) throws Exception;
    Project deleteProject(String projectId) throws Exception;
}
