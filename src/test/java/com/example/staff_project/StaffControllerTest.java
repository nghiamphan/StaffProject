package com.example.staff_project;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.staff_project.entity.Staff;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getStaffs() throws Exception {
        this.mockMvc.perform(get("/staffs")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("1 full name")));
    }

    @Test
    public void getStaff() throws Exception {
        this.mockMvc.perform(get("/staffs/S1")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("1 full name")));
    }

    @Test
    public void createdStaff() throws Exception {
        Staff newStaff = new Staff();
        newStaff.setFullName("new full name");
        this.mockMvc.perform(post("/staffs").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(newStaff)))
                .andDo(print()).andExpect(status().isCreated()).andExpect(content().string(containsString("new full name")));
    }
}
