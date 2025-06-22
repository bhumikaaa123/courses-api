package com.iitb.coursesapi.controller;

import com.iitb.coursesapi.model.Course;
import com.iitb.coursesapi.model.CourseInstance;
import com.iitb.coursesapi.model.CourseInstanceDTO;
import com.iitb.coursesapi.repository.CourseInstanceRepository;
import com.iitb.coursesapi.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-instances")
public class CourseInstanceController {

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    @Autowired
    private CourseRepository courseRepository;

    // ✅ POST - Create a course instance using DTO
    @PostMapping
    public ResponseEntity<Object> createCourseInstance(@Valid @RequestBody CourseInstanceDTO dto) {
        Course course = courseRepository.findById(dto.getCourseId()).orElse(null);
        if (course == null) {
            return ResponseEntity.badRequest().body("Invalid course ID: " + dto.getCourseId());
        }

        CourseInstance instance = new CourseInstance();
        instance.setInstructor(dto.getInstructor());
        instance.setSemester(dto.getSemester());
        instance.setYear(dto.getYear());
        instance.setCourse(course);
        CourseInstance savedInstance = courseInstanceRepository.save(instance);
        return ResponseEntity.ok(savedInstance);
    }

    // ✅ GET - All course instances
    @GetMapping
    public ResponseEntity<List<CourseInstance>> getAllInstances() {
        return ResponseEntity.ok(courseInstanceRepository.findAll());
    }

    // ✅ GET - By year and semester
    @GetMapping("/filter")
    public ResponseEntity<List<CourseInstance>> getByYearAndSemester(
            @RequestParam Integer year,
            @RequestParam String semester) {
        return ResponseEntity.ok(courseInstanceRepository.findByYearAndSemester(year, semester));
    }

    // ✅ GET - By course ID + year + semester
    @GetMapping("/course")
    public ResponseEntity<List<CourseInstance>> getByCourseAndYearAndSemester(
            @RequestParam Long courseId,
            @RequestParam Integer year,
            @RequestParam String semester) {
        return ResponseEntity.ok(
                courseInstanceRepository.findByCourseIdAndYearAndSemester(courseId, year, semester));
    }

    // ✅ DELETE - Remove an instance by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstance(@PathVariable Long id) {
        if (!courseInstanceRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instance not found");
        }
        courseInstanceRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
