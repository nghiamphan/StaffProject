package com.example.staff_project.service;

import com.example.staff_project.entity.Project;
import com.example.staff_project.entity.Staff;
import com.example.staff_project.exception.MyException;
import com.example.staff_project.helper.Helper;
import com.example.staff_project.repository.ProjectRepository;
import com.example.staff_project.repository.StaffRepository;
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
        staffs.add(new Staff("S0", null, "Staff 0 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S1", null, "Staff 1 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S2", null, "Staff 2 full name", null, null, null, null, null, null, null, null, null, null, null));

        projects.add(new Project("P1000", "Project 0", null, null, null, null, null, null, staffs.get(0)));
        projects.add(new Project("P1001", "Project 1", null, null, null, null, null, null, staffs.get(0)));
        projects.add(new Project("P1002", "Project 2", null, null, null, null, null, null, staffs.get(0)));
    }

    @Test
    public void getProjects() throws Exception {
        Mockito.when(projectRepository.findAll()).thenReturn(projects);
        Assertions.assertEquals(projects.size(), projectService.getProjects().size());
    }

    @Test
    public void getProject() throws Exception {
        String projectId = "P1000";
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Helper.getProjectById(projects, projectId));
        Assertions.assertEquals(projects.get(0), projectService.getProject(projectId));
    }

    @Test void getProjectInvalidProjectId() throws Exception {
        String invalidProjectId = "invalid project id";

        Mockito.when(projectRepository.findById(invalidProjectId)).thenReturn(Helper.getProjectById(projects, invalidProjectId));

        MyException e = Assertions.assertThrows(MyException.class, () -> projectService.getProject(invalidProjectId));
        Assertions.assertEquals("ProjectService.PROJECT_NOT_FOUND", e.getMessage());
    }

    @Test
    public void createProject() throws Exception {
        String projectLeaderId = "S0";
        Staff projectLeader = Helper.getStaffById(staffs, projectLeaderId).get();
        String newProjectId = "P1003";
        Project newProject = new Project(newProjectId, "Project 3", null, null, null, null, null, null, projectLeader);

        Mockito.when(staffRepository.findById(projectLeaderId)).thenReturn(Optional.of(projectLeader));
        Mockito.when(projectRepository.save(newProject)).thenReturn(newProject);

        Assertions.assertEquals(newProject, projectService.createProject(newProject));
    }

    @Test
    public void createProjectInvalidLeaderId() throws Exception {
        String invalidStaffId = "invalid staff id";
        Staff invalidProjectLeader = new Staff();
        invalidProjectLeader.setStaffId(invalidStaffId);
        String newProjectId = "P1003";
        Project newProject = new Project(newProjectId, "Project 3", null, null, null, null, null, null, invalidProjectLeader);

        Mockito.when(staffRepository.findById(invalidStaffId)).thenReturn(Helper.getStaffById(staffs, invalidStaffId));
        Mockito.when(projectRepository.save(newProject)).thenReturn(newProject);

        MyException e = Assertions.assertThrows(MyException.class, () -> projectService.createProject(newProject));
        Assertions.assertEquals("StaffService.STAFF_NOT_FOUND", e.getMessage());
    }

    @Test
    public void updateProject() throws Exception {
        String projectLeaderId = "S0";
        Staff projectLeader = Helper.getStaffById(staffs, projectLeaderId).get();
        String projectId = "P1000";
        Project updatedProject = new Project(projectId, "Updated Project", null, null, null, null, null, null, projectLeader);

        Mockito.when(staffRepository.findById(projectLeaderId)).thenReturn(Optional.of(projectLeader));
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Helper.getProjectById(projects, projectId));
        Mockito.when(projectRepository.save(updatedProject)).thenReturn(updatedProject);

        Assertions.assertEquals(updatedProject, projectService.updateProject(projectId, updatedProject));
    }

    @Test
    public void updateProjectInvalidProjectId() throws Exception {
        String projectLeaderId = "S0";
        Staff projectLeader = Helper.getStaffById(staffs, projectLeaderId).get();
        String invalidProjectId = "invalid project id";
        Project updatedProject = new Project(invalidProjectId, "Updated Project", null, null, null, null, null, null, projectLeader);

        Mockito.when(staffRepository.findById(projectLeaderId)).thenReturn(Optional.of(projectLeader));
        Mockito.when(projectRepository.findById(invalidProjectId)).thenReturn(Helper.getProjectById(projects, invalidProjectId));
        Mockito.when(projectRepository.save(updatedProject)).thenReturn(updatedProject);

        MyException e = Assertions.assertThrows(MyException.class, () -> projectService.updateProject(invalidProjectId, updatedProject));
        Assertions.assertEquals("ProjectService.PROJECT_NOT_FOUND", e.getMessage());
    }

    @Test
    public void updateProjectInvalidLeaderId() throws Exception {
        String invalidStaffId = "invalid staff id";
        Staff invalidProjectLeader = new Staff();
        invalidProjectLeader.setStaffId(invalidStaffId);
        String projectId = "P1000";
        Project updatedProject = new Project(projectId, "Updated Project", null, null, null, null, null, null, invalidProjectLeader);

        Mockito.when(staffRepository.findById(invalidStaffId)).thenReturn(Helper.getStaffById(staffs, invalidStaffId));
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Helper.getProjectById(projects, projectId));
        Mockito.when(projectRepository.save(updatedProject)).thenReturn(updatedProject);

        MyException e = Assertions.assertThrows(MyException.class, () -> projectService.updateProject(projectId, updatedProject));
        Assertions.assertEquals("StaffService.STAFF_NOT_FOUND", e.getMessage());
    }

    @Test
    public void deleteProject() throws Exception {
        String projectId = "P1000";
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Helper.getProjectById(projects, projectId));
        Assertions.assertEquals(projects.get(0), projectService.deleteProject(projectId));
    }

    @Test
    public void deleteProjectInvalidProjectId() throws Exception {
        String invalidProjectId = "invalid project id";

        Mockito.when(projectRepository.findById(invalidProjectId)).thenReturn(Helper.getProjectById(projects, invalidProjectId));

        MyException e = Assertions.assertThrows(MyException.class, () -> projectService.deleteProject(invalidProjectId));
        Assertions.assertEquals("ProjectService.PROJECT_NOT_FOUND", e.getMessage());
    }
}
