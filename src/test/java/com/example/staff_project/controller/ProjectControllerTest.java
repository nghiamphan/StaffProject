package com.example.staff_project.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.staff_project.entity.Project;
import com.example.staff_project.entity.Staff;
import com.example.staff_project.helper.Helper;
import com.example.staff_project.repository.ProjectRepository;
import com.example.staff_project.repository.StaffRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Environment environment;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ProjectRepository projectRepository;

    private final String url = "/projects/";
    private static final List<Staff> staffs = new ArrayList<>();
    private static final List<Project> projects = new ArrayList<>();

    @BeforeAll
    public static void beforeAll() {
        staffs.add(new Staff("S1", null, "Staff 1 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S2", null, "Staff 2 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S3", null, "Staff 3 full name", null, null, null, null, null, null, null, null, null, null, null));

        projects.add(new Project("P1001", "Project 1", null, null, null, null, null, null, staffs.get(0)));
        projects.add(new Project("P1002", "Project 2", null, null, null, null, null, null, staffs.get(0)));
        projects.add(new Project("P1003", "Project 3", null, null, null, null, null, null, staffs.get(0)));
    }

    @BeforeEach
    public void beforeEach() {
        projectRepository.deleteAll();
        staffRepository.deleteAll();
        for (Staff staff : staffs) {
            staffRepository.save(staff);
        }
        for (Project project : projects) {
            projectRepository.save(project);
        }
    }

    @AfterEach
    public void afterEach() {
        projectRepository.deleteAll();
        staffRepository.deleteAll();
    }

    @Test
    public void getProjects() throws Exception {
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(projects.get(projects.size() - 1).getProjectName())));
    }

    @Test
    public void getProject() throws Exception {
        String projectId = "P1001";
        mockMvc.perform(get(url + projectId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Helper.getProjectById(projects, projectId).get().getProjectName())));
    }

    @Test
    public void getProjectInvalidProjectId() throws Exception {
        String invalidProjectId = "invalid project id";
        mockMvc.perform(get(url + invalidProjectId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(environment.getProperty("ProjectService.PROJECT_NOT_FOUND"))));
    }

    @Test
    public void createProject() throws Exception {
        String staffId = "S1";
        Staff projectLeader = new Staff();
        projectLeader.setStaffId(staffId);

        String newProjectName = "New Project Name";
        Project newProject = new Project();
        newProject.setProjectName(newProjectName);
        newProject.setProjectLeader(projectLeader);

        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(newProject)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(newProjectName)))
                .andExpect(content().string(containsString(staffId)));
    }

    @Test
    public void createProjectInvalidLeaderId() throws Exception {
        String invalidStaffId = "invalid staff id";
        Staff invalidProjectLeader = new Staff();
        invalidProjectLeader.setStaffId(invalidStaffId);

        String newProjectName = "New Project Name";
        Project newProject = new Project();
        newProject.setProjectName(newProjectName);
        newProject.setProjectLeader(invalidProjectLeader);

        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(newProject)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(environment.getProperty("StaffService.STAFF_NOT_FOUND"))));
    }

    @Test
    public void createProjectNoBodyData() throws Exception {
        mockMvc.perform(post(url))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateProject() throws Exception {
        String staffId = "S2";
        Staff projectLeader = new Staff();
        projectLeader.setStaffId(staffId);

        String projectId = "P1001";
        String updatedProjectName = "Updated Project Name";
        Project updatedProject = new Project();
        updatedProject.setProjectName(updatedProjectName);
        updatedProject.setProjectLeader(projectLeader);

        mockMvc.perform(put(url + projectId).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updatedProject)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updatedProjectName)))
                .andExpect(content().string(containsString(staffId)));
    }

    @Test
    public void updateProjectInvalidLeaderId() throws Exception {
        String invalidStaffId = "invalid staff id";
        Staff projectLeader = new Staff();
        projectLeader.setStaffId(invalidStaffId);

        String projectId = "P1001";
        String updatedProjectName = "Updated Project Name";
        Project updatedProject = new Project();
        updatedProject.setProjectName(updatedProjectName);
        updatedProject.setProjectLeader(projectLeader);

        mockMvc.perform(put(url + projectId).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updatedProject)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(environment.getProperty("StaffService.STAFF_NOT_FOUND"))));
    }

    @Test
    public void updateProjectNoBodyData() throws Exception {
        String projectId = "P1001";
        mockMvc.perform(put(url + projectId))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteProject() throws Exception {
        String projectId = "P1001";
        mockMvc.perform(delete(url + projectId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Helper.getProjectById(projects, projectId).get().getProjectName())));
    }

    @Test
    public void deleteProjectInvalidProjectId() throws Exception {
        String invalidProjectId = "invalid project id";
        mockMvc.perform(delete(url + invalidProjectId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(environment.getProperty("ProjectService.PROJECT_NOT_FOUND"))));
    }
}
