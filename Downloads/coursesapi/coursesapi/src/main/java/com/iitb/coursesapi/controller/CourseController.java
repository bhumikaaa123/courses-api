package com.iitb.coursesapi.controller;

import com.iitb.coursesapi.model.Course;
import com.iitb.coursesapi.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    // POST /api/courses - Create a new course
    @PostMapping
    public ResponseEntity<?> createCourse(@Valid @RequestBody Course course) {
        // Check if prerequisites exist
        for (Course prerequisite : course.getPrerequisites()) {
            Optional<Course> preqCourse = courseRepository.findByCourseId(prerequisite.getCourseId());
            if (preqCourse.isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid prerequisite: " + prerequisite.getCourseId());
            }
        }
        courseRepository.save(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    // GET /api/courses - List all courses with prerequisites
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseRepository.findAll());
    }

    // ✅ FIXED: GET /api/courses/{id} - Get a single course by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
    }

    // DELETE /api/courses/{id} - Delete a course
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        Optional<Course> courseOpt = courseRepository.findById(id);
        if (courseOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }

        Course course = courseOpt.get();

        // Check if any course lists this one as a prerequisite
        List<Course> allCourses = courseRepository.findAll();
        for (Course c : allCourses) {
            for (Course preq : c.getPrerequisites()) {
                if (preq.getCourseId().equals(course.getCourseId())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body("Course cannot be deleted; it is a prerequisite for: " + c.getCourseId());
                }
            }
        }

        courseRepository.deleteById(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
}
