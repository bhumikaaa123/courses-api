package com.iitb.coursesapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String semester;

    @NotBlank
    private String instructor;

    @NotNull
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
