package com.example.staff_project.utility;

import com.example.staff_project.entity.Project;
import com.example.staff_project.entity.Staff;
import com.example.staff_project.entity.StaffProject;

public class Helper {

    /*
    Copy info from oldStaff to newStaff
     */
    public static Staff copyStaffInfo(Staff oldStaff, Staff newStaff) {
        if (newStaff.getStaffId() != null) oldStaff.setStaffId(newStaff.getStaffId());
        if (newStaff.getAccount() != null) oldStaff.setAccount(newStaff.getAccount());
        if (newStaff.getFullName() != null) oldStaff.setFullName(newStaff.getFullName());
        if (newStaff.getFirstName() != null) oldStaff.setFirstName(newStaff.getFirstName());
        if (newStaff.getEmail() != null) oldStaff.setEmail(newStaff.getEmail());
        if (newStaff.getGender() != null) oldStaff.setGender(newStaff.getGender());
        if (newStaff.getStatus() != null) oldStaff.setStatus(newStaff.getStatus());
        if (newStaff.getBirthDate() != null) oldStaff.setBirthDate(newStaff.getBirthDate());
        if (newStaff.getJoinDate() != null) oldStaff.setJoinDate(newStaff.getJoinDate());
        if (newStaff.getFullTimeStart() != null) oldStaff.setFullTimeStart(newStaff.getFullTimeStart());
        if (newStaff.getLeaveDate() != null) oldStaff.setLeaveDate(newStaff.getLeaveDate());
        if (newStaff.getUnit() != null) oldStaff.setUnit(newStaff.getUnit());
        if (newStaff.getCurrentJobTitle() != null) oldStaff.setCurrentJobTitle(newStaff.getCurrentJobTitle());
        if (newStaff.getProfessionalStart() != null) oldStaff.setProfessionalStart(newStaff.getProfessionalStart());

        return oldStaff;
    }

    /*
    Copy info from oldStaff to newStaff
    */
    public static Project copyProjectInfo(Project oldProject, Project newProject) {
        if (newProject.getProjectId() != null) oldProject.setProjectId(newProject.getProjectId());
        if (newProject.getProjectName() != null) oldProject.setProjectName(newProject.getProjectName());
        if (newProject.getDescription() != null) oldProject.setDescription(newProject.getDescription());
        if (newProject.getClient() != null) oldProject.setClient(newProject.getClient());
        if (newProject.getStartDate() != null) oldProject.setStartDate(newProject.getStartDate());
        if (newProject.getEndDate() != null) oldProject.setEndDate(newProject.getEndDate());
        if (newProject.getStatus() != null) oldProject.setStatus(newProject.getStatus());
        if (newProject.getNote() != null) oldProject.setNote(newProject.getNote());
        if (newProject.getProjectLeader() != null) oldProject.setProjectLeader(newProject.getProjectLeader());

        return oldProject;
    }

    public static StaffProject copyStaffProjectInfo(StaffProject oldStaffProject, StaffProject newStaffProject) {
        if (newStaffProject.getStaffProjectId() != 0) oldStaffProject.setStaffProjectId(newStaffProject.getStaffProjectId());
        if (newStaffProject.getStartDate() != null) oldStaffProject.setStartDate(newStaffProject.getStartDate());
        if (newStaffProject.getEndDate() != null) oldStaffProject.setEndDate(newStaffProject.getEndDate());
        if (newStaffProject.getStatus() != null) oldStaffProject.setStatus(newStaffProject.getStatus());
        if (newStaffProject.getNote() != null) oldStaffProject.setNote(newStaffProject.getNote());

        return oldStaffProject;
    }
}
