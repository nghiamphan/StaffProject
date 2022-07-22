package com.example.staff_project.service;

import com.example.staff_project.entity.StaffProject;

import java.util.List;

public interface StaffProjectService {
    public List<StaffProject> getStaffProjects() throws Exception;
    public StaffProject getStaffProject(int staffProjectId) throws Exception;
    public StaffProject createStaffProject(StaffProject staffProject) throws Exception;
    public StaffProject updateStaffProject(int staffProjectId, StaffProject staffProject) throws Exception;
    public StaffProject deleteStaffProject(int staffProjectId) throws Exception;
}
