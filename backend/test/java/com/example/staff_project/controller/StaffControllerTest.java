//package com.example.staff_project.controller;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.example.staff_project.entity.Staff;
//import com.example.staff_project.helper.Helper;
//import com.example.staff_project.repository.StaffRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.env.Environment;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
//public class StaffControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private Environment environment;
//
//    @Autowired
//    private StaffRepository staffRepository;
//
//    private final String url = "/staffs/";
//    private static final List<Staff> staffs = new ArrayList<>();
//
//    @BeforeAll
//    public static void beforeAll() {
//        staffs.add(new Staff("S1", null, "Staff 1 full name", null, null, null, null, null, null, null, null, null, null, null));
//        staffs.add(new Staff("S2", null, "Staff 2 full name", null, null, null, null, null, null, null, null, null, null, null));
//        staffs.add(new Staff("S3", null, "Staff 3 full name", null, null, null, null, null, null, null, null, null, null, null));
//    }
//
//    @BeforeEach
//    public void beforeEach() {
//        staffRepository.deleteAll();
//        for (Staff staff : staffs) {
//            staffRepository.save(staff);
//        }
//    }
//
//    @AfterEach
//    public void deleteEach() {
//        staffRepository.deleteAll();
//    }
//
//    @Test
//    public void getStaffs() throws Exception {
//        mockMvc.perform(get(url))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString(staffs.get(staffs.size() - 1).getFullName())));
//    }
//
//    @Test
//    public void getStaff() throws Exception {
//        String staffId = "S1";
//        mockMvc.perform(get(url + staffId))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString(Helper.getStaffById(staffs, staffId).get().getFullName())));
//    }
//
//    @Test
//    public void getStaffInvalidStaffId() throws Exception {
//        String invalidStaffId = "invalid staff id";
//        mockMvc.perform(get(url + invalidStaffId))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string(containsString(environment.getProperty("StaffService.STAFF_NOT_FOUND"))));
//    }
//
//    @Test
//    public void createStaff() throws Exception {
//        String newStaffName = "New staff full name";
//        Staff newStaff = new Staff();
//        newStaff.setFullName(newStaffName);
//
//        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(newStaff)))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(content().string(containsString(newStaffName)));
//    }
//
//    @Test
//    public void createStaffNoBodyData() throws Exception {
//        mockMvc.perform(post(url))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void updateStaff() throws Exception {
//        String staffId = "S1";
//        String updatedStaffName = "New staff full name";
//        Staff updatedStaff = new Staff();
//        updatedStaff.setFullName(updatedStaffName);
//
//        mockMvc.perform(put(url + staffId).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updatedStaff)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString(updatedStaffName)));
//    }
//
//    @Test
//    public void updateStaffInvalidStaffId() throws Exception {
//        String invalidStaffId = "invalid staff id";
//        String updatedStaffName = "New staff full name";
//        Staff updatedStaff = new Staff();
//        updatedStaff.setFullName(updatedStaffName);
//
//        mockMvc.perform(put(url + invalidStaffId).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(updatedStaff)))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string(containsString(environment.getProperty("StaffService.STAFF_NOT_FOUND"))));
//    }
//
//    @Test
//    public void updateStaffNoBodyData() throws Exception {
//        String staffId = "S1";
//        mockMvc.perform(put(url + staffId))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void deleteStaff() throws Exception {
//        String staffId = "S1";
//        mockMvc.perform(delete(url + staffId))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString(Helper.getStaffById(staffs, staffId).get().getFullName())));
//    }
//
//    @Test
//    public void deleteStaffInvalidStaffId() throws Exception {
//        String invalidStaffId = "invalid staff id";
//        mockMvc.perform(delete(url + invalidStaffId))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string(containsString(environment.getProperty("StaffService.STAFF_NOT_FOUND"))));
//    }
//}
