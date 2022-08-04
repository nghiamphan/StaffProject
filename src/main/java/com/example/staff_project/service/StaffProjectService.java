package com.example.staff_project.service;

import com.example.staff_project.entity.StaffProject;

import java.util.List;

public interface StaffProjectService {
    List<StaffProject> getStaffProjects() throws Exception;
    StaffProject getStaffProject(int staffProjectId) throws Exception;
    StaffProject createStaffProject(StaffProject staffProject) throws Exception;
    StaffProject updateStaffProject(int staffProjectId, StaffProject staffProject) throws Exception;
    StaffProject deleteStaffProject(int staffProjectId) throws Exception;
}
