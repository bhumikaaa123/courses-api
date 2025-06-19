package com.iitb.coursesapi.controller;

import com.iitb.coursesapi.model.CourseInstance;
import com.iitb.coursesapi.repository.CourseInstanceRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-instances")
public class CourseInstanceController {

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    // POST - Create a course instance
    @PostMapping
    public ResponseEntity<CourseInstance> createCourseInstance(@Valid @RequestBody CourseInstance instance) {
        CourseInstance savedInstance = courseInstanceRepository.save(instance);
        return ResponseEntity.ok(savedInstance);
    }

    // GET - All course instances
    @GetMapping
    public ResponseEntity<List<CourseInstance>> getAllInstances() {
        return ResponseEntity.ok(courseInstanceRepository.findAll());
    }

    // GET - By year and semester
    @GetMapping("/filter")
    public ResponseEntity<List<CourseInstance>> getByYearAndSemester(
            @RequestParam Integer year,
            @RequestParam String semester) {
        return ResponseEntity.ok(courseInstanceRepository.findByYearAndSemester(year, semester));
    }

    // GET - Specific course instance
    @GetMapping("/course")
    public ResponseEntity<List<CourseInstance>> getByCourseAndYearAndSemester(
            @RequestParam Long courseId,
            @RequestParam Integer year,
            @RequestParam String semester) {
        return ResponseEntity.ok(
                courseInstanceRepository.findByCourseIdAndYearAndSemester(courseId, year, semester));
    }
}
