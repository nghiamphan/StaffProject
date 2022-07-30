package com.example.staff_project.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.staff_project.entity.Project;
import com.example.staff_project.entity.Staff;
import com.example.staff_project.entity.StaffProject;
import com.example.staff_project.helper.Helper;
import com.example.staff_project.repository.ProjectRepository;
import com.example.staff_project.repository.StaffProjectRepository;
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
public class StaffProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Environment environment;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StaffProjectRepository staffProjectRepository;

    private static final List<Staff> staffs = new ArrayList<>();
    private static final List<Project> projects = new ArrayList<>();
    private static final List<StaffProject> staffProjects = new ArrayList<>();
    private final String url = "/staffProjects/";

    @BeforeAll
    public static void beforeAll() {
        staffs.add(new Staff("S1", null, "Staff 1 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S2", null, "Staff 2 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S3", null, "Staff 3 full name", null, null, null, null, null, null, null, null, null, null, null));

        projects.add(new Project("P1001", "Project 1", null, null, null, null, null, null, staffs.get(0)));
        projects.add(new Project("P1002", "Project 2", null, null, null, null, null, null, staffs.get(0)));
        projects.add(new Project("P1003", "Project 3", null, null, null, null, null, null, staffs.get(0)));

        staffProjects.add(new StaffProject(101, staffs.get(0), projects.get(0), null, null, null, "Staff 1 Project 1"));
        staffProjects.add(new StaffProject(102, staffs.get(0), projects.get(1), null, null, null, "Staff 1 Project 2"));
        staffProjects.add(new StaffProject(103, staffs.get(0), projects.get(2), null, null, null, "Staff 1 Project 3"));
        staffProjects.add(new StaffProject(104, staffs.get(1), projects.get(0), null, null, null, "Staff 2 Project 1"));
        staffProjects.add(new StaffProject(105, staffs.get(1), projects.get(2), null, null, null, "Staff 2 Project 3"));
        staffProjects.add(new StaffProject(106, staffs.get(2), projects.get(1), null, null, null, "Staff 3 Project 2"));
    }

    @BeforeEach
    public void beforeEach() {
        staffProjectRepository.deleteAll();
        projectRepository.deleteAll();
        staffRepository.deleteAll();
        for (Staff staff : staffs) {
            staffRepository.save(staff);
        }
        for (Project project : projects) {
            projectRepository.save(project);
        }
        for (StaffProject staffProject : staffProjects) {
            staffProjectRepository.save(staffProject);
        }
    }

    @AfterEach
    public void afterEach() {
        staffProjectRepository.deleteAll();
        projectRepository.deleteAll();
        staffRepository.deleteAll();
    }

    @Test
    public void getStaffProjects() throws Exception {
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(staffProjects.get(staffProjects.size() - 1).getNote())));
    }

    @Test
    public void getStaffProject() throws Exception {
        int staffProjectId = 101;
        mockMvc.perform(get(url + staffProjectId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Helper.getStaffProjectById(staffProjects, staffProjectId).get().getNote())));
    }

    @Test
    public void getStaffProjectInvalidStaffProjectId() throws Exception {
        int invalidStaffProjectId = -99;
        mockMvc.perform(get(url + invalidStaffProjectId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(environment.getProperty("StaffProjectService.STAFF_PROJECT_NOT_FOUND"))));
    }

    @Test
    public void getStaffProjectWronglyFormattedStaffProjectId() throws Exception {
        String invalidStaffProjectId = "wrongly formatted staff project id";
        mockMvc.perform(get(url + invalidStaffProjectId))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createStaffProject() throws Exception {
        String staffId = "S3";
        Staff staff = new Staff();
        staff.setStaffId(staffId);

        String projectId = "P1001";
        Project project = new Project();
        project.setProjectId(projectId);

        String newNote = "New Note";
        StaffProject newStaffProject = new StaffProject(-99, staff, project, null, null, null, newNote);

        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(newStaffProject)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(String.valueOf(staffProjects.get(staffProjects.size() - 1).getStaffProjectId() + 1))))
                .andExpect(content().string(containsString(newNote)))
                .andExpect(content().string(containsString(staffId)))
                .andExpect(content().string(containsString(projectId)));
    }

    @Test
    public void createStaffProjectInvalidStaffId() throws Exception {
        String invalidStaffId = "invalid staff id";
        Staff invalidStaff = new Staff();
        invalidStaff.setStaffId(invalidStaffId);

        String projectId = "P1001";
        Project project = new Project();
        project.setProjectId(projectId);

        String newNote = "New Note";
        StaffProject newStaffProject = new StaffProject(-99, invalidStaff, project, null, null, null, newNote);

        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(newStaffProject)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(environment.getProperty("StaffService.STAFF_NOT_FOUND"))));
    }

    @Test
    public void createStaffProjectInvalidProjectId() throws Exception {
        String staffId = "S3";
        Staff staff = new Staff();
        staff.setStaffId(staffId);

        String invalidProjectId = "invalid project id";
        Project invalidProject = new Project();
        invalidProject.setProjectId(invalidProjectId);

        String newNote = "New Note";
        StaffProject newStaffProject = new StaffProject(-99, staff, invalidProject, null, null, null, newNote);

        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(newStaffProject)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(environment.getProperty("ProjectService.PROJECT_NOT_FOUND"))));
    }

    @Test
    public void createStaffProjectNoBodyData() throws Exception {
        mockMvc.perform(post(url))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateStaffProject() throws Exception {
        String staffId = "S3";
        Staff staff = new Staff();
        staff.setStaffId(staffId);

        String projectId = "P1003";
        Project project = new Project();
        project.setProjectId(projectId);

        int staffProjectId = 101;
        String updatedNote = "New Note";
        StaffProject updatedStaffProject = new StaffProject(staffProjectId, staff, project, null, null, null, updatedNote);

        mockMvc.perform(put(url + staffProjectId).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updatedStaffProject)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updatedNote)))
                .andExpect(content().string(containsString(staffId)))
                .andExpect(content().string(containsString(projectId)));
    }

    @Test
    public void updateStaffProjectInvalidStaffProjectId() throws Exception {
        String staffId = "S3";
        Staff staff = new Staff();
        staff.setStaffId(staffId);

        String projectId = "P1003";
        Project project = new Project();
        project.setProjectId(projectId);

        int invalidStaffProjectId = -99;
        String updatedNote = "New Note";
        StaffProject updatedStaffProject = new StaffProject(invalidStaffProjectId, staff, project, null, null, null, updatedNote);

        mockMvc.perform(put(url + invalidStaffProjectId).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updatedStaffProject)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(environment.getProperty("StaffProjectService.STAFF_PROJECT_NOT_FOUND"))));
    }

    @Test
    public void updateStaffProjectInvalidStaffId() throws Exception {
        String invalidStaffId = "invalid staff id";
        Staff invalidStaff = new Staff();
        invalidStaff.setStaffId(invalidStaffId);

        String projectId = "P1003";
        Project project = new Project();
        project.setProjectId(projectId);

        int staffProjectId = 101;
        String updatedNote = "New Note";
        StaffProject updatedStaffProject = new StaffProject(staffProjectId, invalidStaff, project, null, null, null, updatedNote);

        mockMvc.perform(put(url + staffProjectId).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updatedStaffProject)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(environment.getProperty("StaffService.STAFF_NOT_FOUND"))));
    }

    @Test
    public void updateStaffProjectInvalidProjectId() throws Exception {
        String staffId = "S3";
        Staff staff = new Staff();
        staff.setStaffId(staffId);

        String invalidProjectId = "invalid project id";
        Project invalidProject = new Project();
        invalidProject.setProjectId(invalidProjectId);

        int staffProjectId = 101;
        String updatedNote = "New Note";
        StaffProject updatedStaffProject = new StaffProject(staffProjectId, staff, invalidProject, null, null, null, updatedNote);

        mockMvc.perform(put(url + staffProjectId).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updatedStaffProject)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(environment.getProperty("ProjectService.PROJECT_NOT_FOUND"))));
    }

    @Test
    public void updateStaffProjectNoBodyData() throws Exception {
        int staffProjectId = 101;
        mockMvc.perform(put(url + staffProjectId))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteStaffProject() throws Exception {
        int staffProjectId = 101;
        mockMvc.perform(delete(url + staffProjectId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Helper.getStaffProjectById(staffProjects, staffProjectId).get().getNote())));
    }

    @Test
    public void deleteStaffProjectInvalidStaffProjectId() throws Exception {
        int invalidStaffProjectId = -99;
        mockMvc.perform(delete(url + invalidStaffProjectId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(environment.getProperty("StaffProjectService.STAFF_PROJECT_NOT_FOUND"))));
    }
}
