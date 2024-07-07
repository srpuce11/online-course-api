package com.online_course_api.online_course_api.repository;


import com.online_course_api.online_course_api.repository.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> getCourseByTeacherId(int teacherId);
}
