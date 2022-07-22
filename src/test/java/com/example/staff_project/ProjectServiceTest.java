package com.example.staff_project;

import com.example.staff_project.entity.Project;
import com.example.staff_project.entity.Staff;
import com.example.staff_project.repository.ProjectRepository;
import com.example.staff_project.repository.StaffRepository;
import com.example.staff_project.service.ProjectService;
import com.example.staff_project.serviceImpl.ProjectServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProjectServiceTest {

    @Mock
    StaffRepository staffRepository;

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectService projectService = new ProjectServiceImpl();

    List<Staff> staffs = new ArrayList<>();
    List<Project> projects = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        staffs.add(new Staff("S1", null, "Staff 1 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S2", null, "Staff 2 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S3", null, "Staff 3 full name", null, null, null, null, null, null, null, null, null, null, null));

        projects.add(new Project("P1001", "Project 1", null, null, null, null, null, null, staffs.get(0)));
        projects.add(new Project("P1002", "Project 2", null, null, null, null, null, null, staffs.get(0)));
        projects.add(new Project("P1003", "Project 3", null, null, null, null, null, null, staffs.get(0)));
    }

    @Test
    public void getProjects() throws Exception {
        Mockito.when(projectRepository.findAll()).thenReturn(projects);
        Assertions.assertEquals(3, projectService.getProjects().size());
    }

    @Test
    public void getProject() throws Exception {
        Mockito.when(projectRepository.findById("P1001")).thenReturn(Optional.of(projects.get(0)));
        Assertions.assertEquals(projects.get(0), projectService.getProject("P1001"));
    }

    @Test
    public void createProject() throws Exception {
        Project newProject = new Project("P1004", "Project 4", null, null, null, null, null, null, staffs.get(0));
        Mockito.when(staffRepository.findById("S1")).thenReturn(Optional.of(staffs.get(0)));
        Mockito.when(projectRepository.save(newProject)).thenReturn(newProject);
        Assertions.assertEquals(newProject, projectService.createProject(newProject));
    }
}
