package com.example.staff_project.helper;

import com.example.staff_project.entity.Project;
import com.example.staff_project.entity.Staff;
import com.example.staff_project.entity.StaffProject;

import java.util.List;
import java.util.Optional;

public class Helper {

    public static Optional<Staff> getStaffById(List<Staff> staffs, String staffId) {
        return staffs.stream()
                .filter(staff -> staff.getStaffId().equals(staffId))
                .findAny();
    }

    public static Optional<Project> getProjectById(List<Project> projects, String projectId) {
        return projects.stream()
                .filter(project -> project.getProjectId().equals(projectId))
                .findAny();
    }

    public static Optional<StaffProject> getStaffProjectById(List<StaffProject> staffProjects, int staffProjectId) {
        return staffProjects.stream()
                .filter(staffProject -> staffProject.getStaffProjectId() == staffProjectId)
                .findAny();
    }
}
