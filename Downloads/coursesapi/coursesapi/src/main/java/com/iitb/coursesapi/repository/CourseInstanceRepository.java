package com.iitb.coursesapi.repository;

import com.iitb.coursesapi.model.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {

    List<CourseInstance> findByYearAndSemester(Integer year, String semester);

    List<CourseInstance> findByCourseIdAndYearAndSemester(Long courseId, Integer year, String semester);
}
