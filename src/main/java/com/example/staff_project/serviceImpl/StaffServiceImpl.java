package com.example.staff_project.serviceImpl;

import com.example.staff_project.entity.Staff;
import com.example.staff_project.repository.StaffRepository;
import com.example.staff_project.service.StaffService;
import com.example.staff_project.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> getStaffs() throws Exception {
        Iterable<Staff> iterable = staffRepository.findAll();
        List<Staff> staffs = new ArrayList<>();
        for (Staff staff : iterable) {
            staffs.add(staff);
        }
        return staffs;
    }

    @Override
    public Staff getStaff(String staffId) throws Exception {
        Staff staff = staffRepository.findById(staffId).orElse(null);
        return staff;
    }

    @Override
    public Staff createStaff(Staff staff) throws Exception {
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(String staffId, Staff updatedStaff) {
        Staff staff = staffRepository.findById(staffId).orElse(null);
        Helper.copyStaffInfo(staff, updatedStaff);
        return staffRepository.save(staff);
    }

    @Override
    public Staff deleteStaff(String staffId) throws Exception {
        Staff staff = staffRepository.findById(staffId).orElse(null);
        staffRepository.deleteById(staffId);
        return staff;
    }
}
