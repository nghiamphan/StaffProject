package com.example.staff_project.service;

import com.example.staff_project.entity.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> getStaffs() throws Exception;
    Staff getStaff(String staffId) throws Exception;
    Staff createStaff(Staff staff) throws Exception;
    Staff updateStaff(String staffId, Staff updatedStaff) throws Exception;
    Staff deleteStaff(String staffId) throws Exception;
}
