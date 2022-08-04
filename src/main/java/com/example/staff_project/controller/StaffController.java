package com.example.staff_project.controller;

import com.example.staff_project.entity.Staff;
import com.example.staff_project.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping(value = "/staffs")
    public ResponseEntity<List<Staff>> getStaffs() throws Exception {
        List<Staff> staffs = staffService.getStaffs();
        return new ResponseEntity<>(staffs, HttpStatus.OK);
    }

    @GetMapping(value = "/staffs/{staffId}")
    public ResponseEntity<Staff> getStaff(@PathVariable String staffId) throws Exception {
        Staff staff = staffService.getStaff(staffId);
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    @PostMapping(value = "/staffs")
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) throws Exception {
        Staff createdStaff = staffService.createStaff(staff);
        return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
    }

    @PutMapping(value = "/staffs/{staffId}")
    public ResponseEntity<Staff> updateStaff(@PathVariable String staffId, @RequestBody Staff staff) throws Exception {
        Staff updatedStaff = staffService.updateStaff(staffId, staff);
        return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
    }

    @DeleteMapping(value = "/staffs/{staffId}")
    public ResponseEntity<Staff> deleteStaff(@PathVariable String staffId) throws Exception {
        Staff deletedStaff = staffService.deleteStaff(staffId);
        return new ResponseEntity<>(deletedStaff, HttpStatus.OK);
    }
}
