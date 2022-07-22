package com.example.staff_project.service;

import com.example.staff_project.entity.Staff;

import java.util.List;

public interface StaffService {
    public List<Staff> getStaffs() throws Exception;
    public Staff getStaff(String staffId) throws Exception;
    public Staff createStaff(Staff staff) throws Exception;
    public Staff updateStaff(String staffId, Staff updatedStaff) throws Exception;
    public Staff deleteStaff(String staffId) throws Exception;
}
