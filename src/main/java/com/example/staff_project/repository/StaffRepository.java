package com.example.staff_project.repository;

import com.example.staff_project.entity.Staff;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<Staff, String> {
}
