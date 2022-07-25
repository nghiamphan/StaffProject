package com.example.staff_project.serviceImpl;

import com.example.staff_project.entity.Project;
import com.example.staff_project.entity.Staff;
import com.example.staff_project.exception.MyException;
import com.example.staff_project.repository.ProjectRepository;
import com.example.staff_project.repository.StaffRepository;
import com.example.staff_project.service.ProjectService;
import com.example.staff_project.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Project> getProjects() throws Exception {
        Iterable<Project> iterable = projectRepository.findAll();
        List<Project> projects = new ArrayList<>();
        for (Project project : iterable) {
            projects.add(project);
        }
        return projects;
    }

    @Override
    public Project getProject(String projectId) throws Exception {
        return projectRepository.findById(projectId).orElseThrow(() -> new MyException("ProjectService.PROJECT_NOT_FOUND"));
    }

    @Override
    public Project createProject(Project project) throws Exception {
        if (project.getProjectLeader() != null) {
            Staff projectLeader = staffRepository.findById(project.getProjectLeader().getStaffId()).orElseThrow(() -> new MyException("StaffService.STAFF_NOT_FOUND"));
            project.setProjectLeader(projectLeader);
        }
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(String projectId, Project updatedProject) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new MyException("ProjectService.PROJECT_NOT_FOUND"));
        Helper.copyProjectInfo(project, updatedProject);

        if (updatedProject.getProjectLeader() != null) {
            Staff projectLeader = staffRepository.findById(project.getProjectLeader().getStaffId()).orElseThrow(() -> new MyException("StaffService.STAFF_NOT_FOUND"));
            project.setProjectLeader(projectLeader);
        }
        return projectRepository.save(project);
    }

    @Override
    public Project deleteProject(String projectId) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new MyException("ProjectService.PROJECT_NOT_FOUND"));
        projectRepository.deleteById(projectId);
        return project;
    }
}
