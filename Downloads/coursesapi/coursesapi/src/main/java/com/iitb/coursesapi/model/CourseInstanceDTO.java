package com.iitb.coursesapi.model;

import lombok.Data;

@Data
public class CourseInstanceDTO {
    private Long courseId;
    private String semester;
    private String instructor;
    private Integer year;
}
