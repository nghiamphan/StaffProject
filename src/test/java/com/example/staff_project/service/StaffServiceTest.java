//package com.example.staff_project.service;
//
//import com.example.staff_project.entity.Staff;
//import com.example.staff_project.exception.MyException;
//import com.example.staff_project.helper.Helper;
//import com.example.staff_project.repository.StaffRepository;
//import com.example.staff_project.serviceImpl.StaffServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//public class StaffServiceTest {
//    @Mock
//    StaffRepository staffRepository;
//
//    @InjectMocks
//    StaffService staffService = new StaffServiceImpl();
//
//    List<Staff> staffs = new ArrayList<>();
//
//    @BeforeEach
//    public void setUp() {
//        staffs.add(new Staff("S0", null, "Staff 0 full name", null, null, null, null, null, null, null, null, null, null, null));
//        staffs.add(new Staff("S1", null, "Staff 1 full name", null, null, null, null, null, null, null, null, null, null, null));
//        staffs.add(new Staff("S2", null, "Staff 2 full name", null, null, null, null, null, null, null, null, null, null, null));
//    }
//
//    @Test
//    public void getStaffs() throws Exception {
//        Mockito.when(staffService.getStaffs()).thenReturn(staffs);
//        Assertions.assertEquals(staffs.size(), staffService.getStaffs().size());
//    }
//
//    @Test
//    public void getStaff() throws Exception {
//        String staffId = "S0";
//        Mockito.when(staffRepository.findById(staffId)).thenReturn(Helper.getStaffById(staffs, staffId));
//        Assertions.assertEquals(staffs.get(0), staffService.getStaff(staffId));
//    }
//
//    @Test
//    public void getStaffInvalidStaffId() throws Exception {
//        String invalidStaffId = "invalid staff id";
//
//        Mockito.when(staffRepository.findById(invalidStaffId)).thenReturn(Helper.getStaffById(staffs, invalidStaffId));
//
//        MyException e = Assertions.assertThrows(MyException.class, () -> staffService.getStaff(invalidStaffId));
//        Assertions.assertEquals("StaffService.STAFF_NOT_FOUND", e.getMessage());
//    }
//
//    @Test
//    public void createStaff() throws Exception {
//        String newStaffId = "S3";
//        Staff newStaff = new Staff(newStaffId, null, "Staff 3 full name", null, null, null, null, null, null, null, null, null, null, null);
//
//        Mockito.when(staffRepository.save(newStaff)).thenReturn(newStaff);
//        Assertions.assertEquals(newStaff, staffService.createStaff(newStaff));
//    }
//
//    @Test
//    public void updateStaff() throws Exception {
//        String staffId = "S0";
//        Staff updatedStaff = new Staff(staffId, null, "Updated full name", null, null, null, null, null, null, null, null, null, null, null);
//
//        Mockito.when(staffRepository.findById(staffId)).thenReturn(Helper.getStaffById(staffs, staffId));
//        Mockito.when(staffRepository.save(updatedStaff)).thenReturn(updatedStaff);
//
//        Assertions.assertEquals(updatedStaff, staffService.updateStaff(staffId, updatedStaff));
//    }
//
//    @Test
//    public void updateStaffInvalidStaffId() throws Exception {
//        String invalidStaffId = "invalid staff id";
//        Staff updatedStaff = new Staff(invalidStaffId, null, "Updated full name", null, null, null, null, null, null, null, null, null, null, null);
//
//        Mockito.when(staffRepository.findById(invalidStaffId)).thenReturn(Helper.getStaffById(staffs, invalidStaffId));
//        Mockito.when(staffRepository.save(updatedStaff)).thenReturn(updatedStaff);
//
//        MyException e = Assertions.assertThrows(MyException.class, () -> staffService.updateStaff(invalidStaffId, updatedStaff));
//        Assertions.assertEquals("StaffService.STAFF_NOT_FOUND", e.getMessage());
//    }
//
//    @Test
//    public void deleteStaff() throws Exception {
//        String staffId = "S0";
//        Mockito.when(staffRepository.findById(staffId)).thenReturn(Helper.getStaffById(staffs, staffId));
//        Assertions.assertEquals(staffs.get(0), staffService.deleteStaff(staffId));
//    }
//
//    @Test
//    public void deleteStaffInvalidStaffId() throws Exception {
//        String invalidStaffId = "invalid staff id";
//
//        Mockito.when(staffRepository.findById(invalidStaffId)).thenReturn(Helper.getStaffById(staffs, invalidStaffId));
//
//        MyException e = Assertions.assertThrows(MyException.class, () -> staffService.deleteStaff(invalidStaffId));
//        Assertions.assertEquals("StaffService.STAFF_NOT_FOUND", e.getMessage());
//    }
//}
