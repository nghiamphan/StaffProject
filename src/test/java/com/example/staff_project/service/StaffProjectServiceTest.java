package com.example.staff_project.service;

import com.example.staff_project.entity.Project;
import com.example.staff_project.entity.Staff;
import com.example.staff_project.entity.StaffProject;
import com.example.staff_project.exception.MyException;
import com.example.staff_project.helper.Helper;
import com.example.staff_project.repository.ProjectRepository;
import com.example.staff_project.repository.StaffProjectRepository;
import com.example.staff_project.repository.StaffRepository;
import com.example.staff_project.serviceImpl.StaffProjectServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class StaffProjectServiceTest {

    @Mock
    StaffRepository staffRepository;

    @Mock
    ProjectRepository projectRepository;

    @Mock
    StaffProjectRepository staffProjectRepository;

    @InjectMocks
    StaffProjectService staffProjectService = new StaffProjectServiceImpl();

    List<Staff> staffs = new ArrayList<>();
    List<Project> projects = new ArrayList<>();
    List<StaffProject> staffProjects = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        staffs.add(new Staff("S0", null, "Staff 0 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S1", null, "Staff 1 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S2", null, "Staff 2 full name", null, null, null, null, null, null, null, null, null, null, null));

        projects.add(new Project("P1000", "Project 0", null, null, null, null, null, null, staffs.get(0)));
        projects.add(new Project("P1001", "Project 1", null, null, null, null, null, null, staffs.get(0)));
        projects.add(new Project("P1002", "Project 2", null, null, null, null, null, null, staffs.get(0)));

        staffProjects.add(new StaffProject(100, staffs.get(0), projects.get(0), null, null, null, "Staff 0 Project 0"));
        staffProjects.add(new StaffProject(101, staffs.get(0), projects.get(1), null, null, null, "Staff 0 Project 1"));
        staffProjects.add(new StaffProject(102, staffs.get(0), projects.get(2), null, null, null, "Staff 0 Project 2"));
        staffProjects.add(new StaffProject(103, staffs.get(1), projects.get(0), null, null, null, "Staff 1 Project 0"));
        staffProjects.add(new StaffProject(104, staffs.get(1), projects.get(2), null, null, null, "Staff 1 Project 2"));
        staffProjects.add(new StaffProject(105, staffs.get(2), projects.get(1), null, null, null, "Staff 2 Project 1"));
    }

    @Test
    public void getStaffProjects() throws Exception {
        Mockito.when(staffProjectRepository.findAll()).thenReturn(staffProjects);
        Assertions.assertEquals(staffProjects.size(), staffProjectService.getStaffProjects().size());
    }

    @Test
    public void getStaffProject() throws Exception {
        int staffProjectId = 100;
        Mockito.when(staffProjectRepository.findById(staffProjectId)).thenReturn(Helper.getStaffProjectById(staffProjects, staffProjectId));
        Assertions.assertEquals(staffProjects.get(0), staffProjectService.getStaffProject(staffProjectId));
    }

    @Test
    public void getStaffProjectInvalidStaffProjectId() throws Exception {
        int invalidStaffProjectId = -99;

        Mockito.when(staffProjectRepository.findById(invalidStaffProjectId)).thenReturn(Helper.getStaffProjectById(staffProjects, invalidStaffProjectId));

        MyException e = Assertions.assertThrows(MyException.class, () -> staffProjectService.getStaffProject(invalidStaffProjectId));
        Assertions.assertEquals("StaffProjectService.STAFF_PROJECT_NOT_FOUND", e.getMessage());
    }

    @Test
    public void createStaffProject() throws Exception {
        String staffId = "S2";
        Staff staff = Helper.getStaffById(staffs, staffId).get();
        String projectId = "P1002";
        Project project = Helper.getProjectById(projects, projectId).get();
        int newStaffProjectId = 106;
        StaffProject newStaffProject = new StaffProject(newStaffProjectId, staff, project, null, null, null, "Staff 2 Project 2");

        Mockito.when(staffRepository.findById(staffId)).thenReturn(Optional.of(staff));
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        Mockito.when(staffProjectRepository.save(newStaffProject)).thenReturn(newStaffProject);

        Assertions.assertEquals(newStaffProject, staffProjectService.createStaffProject(newStaffProject));
    }

    @Test
    public void createStaffProjectInvalidStaffId() throws Exception {
        String invalidStaffId = "invalid staff id";
        Staff invalidStaff = new Staff();
        invalidStaff.setStaffId(invalidStaffId);
        String projectId = "P1002";
        Project project = Helper.getProjectById(projects, projectId).stream().findAny().get();
        int newStaffProjectId = 106;
        StaffProject newStaffProject = new StaffProject(newStaffProjectId, invalidStaff, project, null, null, null, "Invalid staff Project 2");

        Mockito.when(staffRepository.findById(invalidStaffId)).thenReturn(Helper.getStaffById(staffs, invalidStaffId));
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        Mockito.when(staffProjectRepository.save(newStaffProject)).thenReturn(newStaffProject);

        MyException e = Assertions.assertThrows(MyException.class, () -> staffProjectService.createStaffProject(newStaffProject));
        Assertions.assertEquals("StaffService.STAFF_NOT_FOUND", e.getMessage());
    }

    @Test
    public void createStaffProjectInvalidProjectId() throws Exception {
        String staffId = "S2";
        Staff staff = Helper.getStaffById(staffs, staffId).get();
        String invalidProjectId = "invalid project id";
        Project invalidProject = new Project();
        invalidProject.setProjectId(invalidProjectId);
        int newStaffProjectId = 106;
        StaffProject newStaffProject = new StaffProject(newStaffProjectId, staff, invalidProject, null, null, null, "Staff 2 Invalid project");

        Mockito.when(staffRepository.findById(staffId)).thenReturn(Optional.of(staff));
        Mockito.when(projectRepository.findById(invalidProjectId)).thenReturn(Helper.getProjectById(projects, invalidProjectId));
        Mockito.when(staffProjectRepository.save(newStaffProject)).thenReturn(newStaffProject);

        MyException e = Assertions.assertThrows(MyException.class, () -> staffProjectService.createStaffProject(newStaffProject));
        Assertions.assertEquals("ProjectService.PROJECT_NOT_FOUND", e.getMessage());
    }

    @Test
    public void updateStaffProject() throws Exception {
        String staffId = "S0";
        Staff staff = Helper.getStaffById(staffs, staffId).get();
        String projectId = "P1000";
        Project project = Helper.getProjectById(projects, projectId).get();
        int staffProjectId = 100;
        StaffProject updatedStaffProject = new StaffProject(staffProjectId, staff, project, null, null, "Updated status", "Staff 0 Project 0");

        Mockito.when(staffRepository.findById(staffId)).thenReturn(Optional.of(staff));
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        Mockito.when(staffProjectRepository.findById(staffProjectId)).thenReturn(Helper.getStaffProjectById(staffProjects, staffProjectId));
        Mockito.when(staffProjectRepository.save(updatedStaffProject)).thenReturn(updatedStaffProject);

        Assertions.assertEquals(updatedStaffProject, staffProjectService.updateStaffProject(staffProjectId, updatedStaffProject));
    }

    @Test
    public void updateStaffProjectInvalidStaffProjectId() throws Exception {
        String staffId = "S0";
        Staff staff = Helper.getStaffById(staffs, staffId).get();
        String projectId = "P1000";
        Project project = Helper.getProjectById(projects, projectId).get();
        int invalidStaffProjectId = -99;
        StaffProject updatedStaffProject = new StaffProject(invalidStaffProjectId, staff, project, null, null, "Updated status", "Staff 0 Project 0");

        Mockito.when(staffRepository.findById(staffId)).thenReturn(Optional.of(staff));
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        Mockito.when(staffProjectRepository.findById(invalidStaffProjectId)).thenReturn(Helper.getStaffProjectById(staffProjects, invalidStaffProjectId));
        Mockito.when(staffProjectRepository.save(updatedStaffProject)).thenReturn(updatedStaffProject);

        MyException e = Assertions.assertThrows(MyException.class, () -> staffProjectService.updateStaffProject(invalidStaffProjectId, updatedStaffProject));
        Assertions.assertEquals("StaffProjectService.STAFF_PROJECT_NOT_FOUND", e.getMessage());
    }

    @Test
    public void updateStaffProjectInvalidStaffId() throws Exception {
        String invalidStaffId = "invalid staff id";
        Staff invalidStaff = new Staff();
        invalidStaff.setStaffId(invalidStaffId);
        String projectId = "P1000";
        Project project = Helper.getProjectById(projects, projectId).get();
        int staffProjectId = 100;
        StaffProject updatedStaffProject = new StaffProject(staffProjectId, invalidStaff, project, null, null, "Updated status", "Invalid staff Project 0");

        Mockito.when(staffRepository.findById(invalidStaffId)).thenReturn(Helper.getStaffById(staffs, invalidStaffId));
        Mockito.when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        Mockito.when(staffProjectRepository.findById(staffProjectId)).thenReturn(Helper.getStaffProjectById(staffProjects, staffProjectId));
        Mockito.when(staffProjectRepository.save(updatedStaffProject)).thenReturn(updatedStaffProject);

        MyException e = Assertions.assertThrows(MyException.class, () -> staffProjectService.updateStaffProject(staffProjectId, updatedStaffProject));
        Assertions.assertEquals("StaffService.STAFF_NOT_FOUND", e.getMessage());
    }

    @Test
    public void updateStaffProjectInvalidProjectId() throws Exception {
        String staffId = "S0";
        Staff staff = Helper.getStaffById(staffs, staffId).get();
        String invalidProjectId = "invalid project id";
        Project invalidProject = new Project();
        invalidProject.setProjectId(invalidProjectId);
        int staffProjectId = 100;
        StaffProject updatedStaffProject = new StaffProject(staffProjectId, staff, invalidProject, null, null, "Updated status", "Staff 0 Invalid project");

        Mockito.when(staffRepository.findById(staffId)).thenReturn(Optional.of(staff));
        Mockito.when(projectRepository.findById(invalidProjectId)).thenReturn(Helper.getProjectById(projects, invalidProjectId));
        Mockito.when(staffProjectRepository.findById(staffProjectId)).thenReturn(Helper.getStaffProjectById(staffProjects, staffProjectId));
        Mockito.when(staffProjectRepository.save(updatedStaffProject)).thenReturn(updatedStaffProject);

        MyException e = Assertions.assertThrows(MyException.class, () -> staffProjectService.updateStaffProject(staffProjectId, updatedStaffProject));
        Assertions.assertEquals("ProjectService.PROJECT_NOT_FOUND", e.getMessage());
    }
    @Test
    public void deleteStaffProject() throws Exception {
        int staffProjectId = 100;
        Mockito.when(staffProjectRepository.findById(staffProjectId)).thenReturn(Helper.getStaffProjectById(staffProjects, staffProjectId));
        Assertions.assertEquals(staffProjects.get(0), staffProjectService.deleteStaffProject(staffProjectId));
    }

    @Test
    public void deleteStaffProjectInvalidStaffProjectId() throws Exception {
        int invalidStaffProjectId = -99;

        Mockito.when(staffProjectRepository.findById(invalidStaffProjectId)).thenReturn(Helper.getStaffProjectById(staffProjects, invalidStaffProjectId));

        MyException e = Assertions.assertThrows(MyException.class, () -> staffProjectService.deleteStaffProject(invalidStaffProjectId));
        Assertions.assertEquals("StaffProjectService.STAFF_PROJECT_NOT_FOUND", e.getMessage());
    }
}
