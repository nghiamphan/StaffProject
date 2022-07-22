package com.example.staff_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "staff_project")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class StaffProject {
    @Id
    @GenericGenerator(name = "sequence_staff_project_id", strategy = "com.example.staff_project.entity.StaffProjectIdGenerator")
    @GeneratedValue(generator = "sequence_staff_project_id")
    private int staffProjectId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project_id")
    private Project project;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String note;
}
