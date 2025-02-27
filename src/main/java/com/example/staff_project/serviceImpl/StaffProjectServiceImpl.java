package com.example.staff_project.serviceImpl;

import com.example.staff_project.entity.Project;
import com.example.staff_project.entity.Staff;
import com.example.staff_project.entity.StaffProject;
import com.example.staff_project.exception.MyException;
import com.example.staff_project.repository.ProjectRepository;
import com.example.staff_project.repository.StaffProjectRepository;
import com.example.staff_project.repository.StaffRepository;
import com.example.staff_project.service.StaffProjectService;
import com.example.staff_project.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffProjectServiceImpl implements StaffProjectService {

    @Autowired
    private StaffProjectRepository staffProjectRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<StaffProject> getStaffProjects() throws Exception {
        Iterable<StaffProject> iterable = staffProjectRepository.findAll();
        List<StaffProject> staffProjects = new ArrayList<>();
        for (StaffProject staffProject : iterable) {
            staffProjects.add(staffProject);
        }
        return staffProjects;
    }

    @Override
    public StaffProject getStaffProject(int staffProjectId) throws Exception {
        return staffProjectRepository.findById(staffProjectId).orElseThrow(() -> new MyException("StaffProjectService.STAFF_PROJECT_NOT_FOUND"));
    }

    @Override
    public StaffProject createStaffProject(StaffProject staffProject) throws Exception {
        if (staffProject.getStaff() != null) {
            Staff staff = staffRepository.findById(staffProject.getStaff().getStaffId()).orElseThrow(() -> new MyException("StaffService.STAFF_NOT_FOUND"));
            staffProject.setStaff(staff);
        }

        if (staffProject.getProject() != null) {
            Project project = projectRepository.findById(staffProject.getProject().getProjectId()).orElseThrow(() -> new MyException("ProjectService.PROJECT_NOT_FOUND"));
            staffProject.setProject(project);
        }

        return staffProjectRepository.save(staffProject);
    }

    @Override
    public StaffProject updateStaffProject(int staffProjectId, StaffProject updatedStaffProject) throws Exception {
        StaffProject staffProject = staffProjectRepository.findById(staffProjectId).orElseThrow(() -> new MyException("StaffProjectService.STAFF_PROJECT_NOT_FOUND"));
        Helper.copyStaffProjectInfo(staffProject, updatedStaffProject);

        if (updatedStaffProject.getStaff() != null) {
            Staff staff = staffRepository.findById(updatedStaffProject.getStaff().getStaffId()).orElseThrow(() -> new MyException("StaffService.STAFF_NOT_FOUND"));
            staffProject.setStaff(staff);
        }

        if (updatedStaffProject.getProject() != null) {
            Project project = projectRepository.findById(updatedStaffProject.getProject().getProjectId()).orElseThrow(() -> new MyException("ProjectService.PROJECT_NOT_FOUND"));
            staffProject.setProject(project);
        }

        return staffProjectRepository.save(staffProject);
    }

    @Override
    public StaffProject deleteStaffProject(int staffProjectId) throws Exception {
        StaffProject staffProject = staffProjectRepository.findById(staffProjectId).orElseThrow(() -> new MyException("StaffProjectService.STAFF_PROJECT_NOT_FOUND"));
        staffProjectRepository.deleteById(staffProjectId);
        return staffProject;
    }
}
