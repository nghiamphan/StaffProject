package com.example.staff_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "staff")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Staff {
    @Id
    @GenericGenerator(name = "sequence_staff_id", strategy = "com.example.staff_project.entity.StaffIdGenerator")
    @GeneratedValue(generator = "sequence_staff_id")
    private String staffId;
    private String account;
    private String fullName;
    private String firstName;
    private String email;
    private String gender;
    private String status;
    private LocalDate birthDate;
    private LocalDate joinDate;
    private LocalDate fullTimeStart;
    private LocalDate leaveDate;
    private String unit;
    private String currentJobTitle;
    private LocalDate professionalStart;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "staff_project", joinColumns = @JoinColumn(name = "staff_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
//    private Set<Project> projects;
}
