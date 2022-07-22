package com.example.staff_project.controller;

import com.example.staff_project.entity.StaffProject;
import com.example.staff_project.service.StaffProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/")
@CrossOrigin
@RestController
public class StaffProjectController {

    @Autowired
    private StaffProjectService staffProjectService;

    @GetMapping(value = "/staffProjects")
    public ResponseEntity<List<StaffProject>> getStaffProjects() throws Exception {
        List<StaffProject> staffProjects = staffProjectService.getStaffProjects();
        return new ResponseEntity<>(staffProjects, HttpStatus.OK);
    }

    @GetMapping(value = "/staffProjects/{staffProjectId}")
    public ResponseEntity<StaffProject> getStaffProject(@PathVariable int staffProjectId) throws Exception {
        StaffProject staffProject = staffProjectService.getStaffProject(staffProjectId);
        return new ResponseEntity<>(staffProject, HttpStatus.OK);
    }

    @PostMapping(value = "/staffProjects")
    public ResponseEntity<StaffProject> createStaffProject(@RequestBody StaffProject staffProject) throws Exception {
        StaffProject newStaffProject = staffProjectService.createStaffProject(staffProject);
        return new ResponseEntity<>(newStaffProject, HttpStatus.CREATED);
    }

    @PutMapping(value = "/staffProjects/{staffProjectId}")
    public ResponseEntity<StaffProject> updateStaffProject(@PathVariable int staffProjectId, @RequestBody StaffProject staffProject) throws Exception {
        StaffProject updatedStaffProject = staffProjectService.updateStaffProject(staffProjectId, staffProject);
        return new ResponseEntity<>(updatedStaffProject, HttpStatus.OK);
    }

    @DeleteMapping(value = "/staffProjects/{staffProjectId}")
    public ResponseEntity<StaffProject> deleteStaffProject(@PathVariable int staffProjectId) throws Exception {
        StaffProject deletedStaffProject = staffProjectService.deleteStaffProject(staffProjectId);
        return new ResponseEntity<>(deletedStaffProject, HttpStatus.OK);
    }
}
