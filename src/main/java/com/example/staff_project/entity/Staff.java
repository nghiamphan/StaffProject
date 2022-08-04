package com.example.staff_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

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
}
