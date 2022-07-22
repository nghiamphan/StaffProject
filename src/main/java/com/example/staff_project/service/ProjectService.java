package com.example.staff_project.service;

import com.example.staff_project.entity.Project;

import java.util.List;

public interface ProjectService {
    public List<Project> getProjects() throws Exception;
    public Project getProject(String projectId) throws Exception;
    public Project createProject(Project project) throws Exception;
    public Project updateProject(String projectId, Project updatedProject) throws Exception;
    public Project deleteProject(String projectId) throws Exception;
}
