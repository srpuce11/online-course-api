package com.online_course_api.online_course_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.online_course_api.online_course_api.repository.entity.StudentCourse;
import com.online_course_api.online_course_api.repository.entity.StudentCourseId;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {
}
