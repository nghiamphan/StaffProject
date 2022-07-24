package com.example.staff_project.service;

import com.example.staff_project.entity.Staff;
import com.example.staff_project.helper.Helper;
import com.example.staff_project.repository.StaffRepository;
import com.example.staff_project.service.StaffService;
import com.example.staff_project.serviceImpl.StaffServiceImpl;
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
public class StaffServiceTest {
    @Mock
    StaffRepository staffRepository;

    @InjectMocks
    StaffService staffService = new StaffServiceImpl();

    List<Staff> staffs = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        staffs.add(new Staff("S0", null, "Staff 0 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S1", null, "Staff 1 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S2", null, "Staff 2 full name", null, null, null, null, null, null, null, null, null, null, null));
    }

    @Test
    public void getStaffs() throws Exception {
        Mockito.when(staffService.getStaffs()).thenReturn(staffs);
        Assertions.assertEquals(staffs.size(), staffService.getStaffs().size());
    }

    @Test
    public void getStaff() throws Exception {
        String staffId = "S0";
        Mockito.when(staffRepository.findById(staffId)).thenReturn(Helper.getStaffById(staffs, staffId));
        Assertions.assertEquals(staffs.get(0), staffService.getStaff(staffId));
    }

    @Test
    public void createStaff() throws Exception {
        Staff newStaff = new Staff("S3", null, "Staff 3 full name", null, null, null, null, null, null, null, null, null, null, null);
        Mockito.when(staffRepository.save(newStaff)).thenReturn(newStaff);
        Assertions.assertEquals(newStaff, staffService.createStaff(newStaff));
    }

    @Test
    public void updateStaff() throws Exception {
        String staffId = "S0";
        Staff updatedStaff = new Staff("S0", null, "Updated full name", null, null, null, null, null, null, null, null, null, null, null);

        Mockito.when(staffRepository.findById(staffId)).thenReturn(Helper.getStaffById(staffs, staffId));
        Mockito.when(staffRepository.save(updatedStaff)).thenReturn(updatedStaff);

        Assertions.assertEquals(updatedStaff, staffService.updateStaff(staffId, updatedStaff));
    }

    @Test
    public void deleteStaff() throws Exception {
        String staffId = "S0";
        Mockito.when(staffRepository.findById(staffId)).thenReturn(Helper.getStaffById(staffs, staffId));
        Assertions.assertEquals(staffs.get(0), staffService.deleteStaff(staffId));
    }
}
