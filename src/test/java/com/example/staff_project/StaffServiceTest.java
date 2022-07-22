package com.example.staff_project;

import com.example.staff_project.entity.Staff;
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
        staffs.add(new Staff("S1", null, "Staff 1 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S2", null, "Staff 2 full name", null, null, null, null, null, null, null, null, null, null, null));
        staffs.add(new Staff("S3", null, "Staff 3 full name", null, null, null, null, null, null, null, null, null, null, null));
    }

    @Test
    public void getStaffs() throws Exception {
        Mockito.when(staffService.getStaffs()).thenReturn(staffs);
        Assertions.assertEquals(3, staffService.getStaffs().size());
    }

    @Test
    public void getStaff() throws Exception {
        Mockito.when(staffRepository.findById("S1")).thenReturn(Optional.of(staffs.get(0)));
        Assertions.assertEquals(staffs.get(0), staffService.getStaff("S1"));
    }

    @Test
    public void createStaff() throws Exception {
        Staff newStaff = new Staff("S4", null, "Staff 4 full name", null, null, null, null, null, null, null, null, null, null, null);
        Mockito.when(staffRepository.save(newStaff)).thenReturn(newStaff);
        Assertions.assertEquals(newStaff, staffService.createStaff(newStaff));
    }

    @Test
    public void updateStaff() throws Exception {
        Staff updatedStaff = new Staff("S4", null, "Staff 4 full name", null, null, null, null, null, null, null, null, null, null, null);
        Mockito.when(staffRepository.findById("S1")).thenReturn(Optional.of(staffs.get(0)));
        Mockito.when(staffRepository.save(updatedStaff)).thenReturn(updatedStaff);
        Assertions.assertEquals(updatedStaff, staffService.updateStaff("S1", updatedStaff));
    }

    @Test
    public void deleteStaff() throws Exception {
        Mockito.when(staffRepository.findById("S1")).thenReturn(Optional.of(staffs.get(0)));
        Assertions.assertEquals(staffs.get(0), staffService.deleteStaff("S1"));
    }
}
