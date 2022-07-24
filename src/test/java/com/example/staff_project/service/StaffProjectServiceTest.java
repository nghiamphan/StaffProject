package com.example.staff_project.service;

import com.example.staff_project.entity.Project;
import com.example.staff_project.entity.Staff;
import com.example.staff_project.entity.StaffProject;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
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
    public void createStaffProject() throws Exception {
        StaffProject newStaffProject = new StaffProject(106, staffs.get(2), projects.get(2), null, null, null, "Staff 2 Project 2");

        Mockito.when(staffRepository.findById(newStaffProject.getStaff().getStaffId())).thenReturn(Optional.of(newStaffProject.getStaff()));
        Mockito.when(projectRepository.findById(newStaffProject.getProject().getProjectId())).thenReturn(Optional.of(newStaffProject.getProject()));
        Mockito.when(staffProjectRepository.save(newStaffProject)).thenReturn(newStaffProject);

        Assertions.assertEquals(newStaffProject, staffProjectService.createStaffProject(newStaffProject));
    }

    @Test
    public void updateStaffProject() throws Exception {
        int staffProjectId = 100;
        StaffProject updatedStaffProject = new StaffProject(staffProjectId, staffs.get(0), projects.get(0), null, null, "Updated status", "Staff 0 Project 0");

        Mockito.when(staffRepository.findById(updatedStaffProject.getStaff().getStaffId())).thenReturn(Optional.of(updatedStaffProject.getStaff()));
        Mockito.when(projectRepository.findById(updatedStaffProject.getProject().getProjectId())).thenReturn(Optional.of(updatedStaffProject.getProject()));
        Mockito.when(staffProjectRepository.findById(staffProjectId)).thenReturn(Helper.getStaffProjectById(staffProjects, staffProjectId));
        Mockito.when(staffProjectRepository.save(updatedStaffProject)).thenReturn(updatedStaffProject);

        Assertions.assertEquals(updatedStaffProject, staffProjectService.updateStaffProject(staffProjectId, updatedStaffProject));
    }

    @Test
    public void deleteStaffProject() throws Exception {
        int staffProjectId = 100;
        Mockito.when(staffProjectRepository.findById(staffProjectId)).thenReturn(Helper.getStaffProjectById(staffProjects, staffProjectId));
        Assertions.assertEquals(staffProjects.get(0), staffProjectService.deleteStaffProject(staffProjectId));
    }
}
