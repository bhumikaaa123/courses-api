package com.iitb.coursesapi.service;

import com.iitb.coursesapi.exception.DependencyException;
import com.iitb.coursesapi.exception.ResourceNotFoundException;
import com.iitb.coursesapi.model.Course;
import com.iitb.coursesapi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        // Check if all prerequisite courses exist
        for (Course prerequisite : course.getPrerequisites()) {
            courseRepository.findById(prerequisite.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Prerequisite course not found with ID: " + prerequisite.getId()));
        }
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));
    }

    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));

        List<Course> allCourses = courseRepository.findAll();
        for (Course c : allCourses) {
            for (Course prereq : c.getPrerequisites()) {
                if (prereq.getId().equals(id)) {
                    throw new DependencyException("Cannot delete course as it is a prerequisite for another course.");
                }
            }
        }

        courseRepository.delete(course);
    }
}
