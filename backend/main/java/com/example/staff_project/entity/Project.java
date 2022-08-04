package com.example.staff_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Project {
    @Id
    @GenericGenerator(name = "sequence_project_id", strategy = "com.example.staff_project.entity.ProjectIdGenerator")
    @GeneratedValue(generator = "sequence_project_id")
    private String projectId;
    private String projectName;
    private String description;
    private String client;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String note;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project_leader")
    private Staff projectLeader;
}
