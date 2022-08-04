package com.example.staff_project.repository;

import com.example.staff_project.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, String> {
}
