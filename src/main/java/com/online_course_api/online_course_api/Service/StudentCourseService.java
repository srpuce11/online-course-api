package com.online_course_api.online_course_api.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online_course_api.online_course_api.ResourceNotFoundException;
import com.online_course_api.online_course_api.repository.StudentCourseRepository;
import com.online_course_api.online_course_api.repository.entity.StudentCourse;
import com.online_course_api.online_course_api.repository.entity.StudentCourseId;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public StudentCourse createStudentCourse(StudentCourse studentCourse) {
        return studentCourseRepository.save(studentCourse);
    }

    public StudentCourse getStudentCourse(Long studentId, Long courseId) {
        return studentCourseRepository.findById(new StudentCourseId(studentId, courseId))
                .orElseThrow(() -> new ResourceNotFoundException("StudentCourse not found"));
    }

    public List<StudentCourse> getAllStudentCourses() {
        return studentCourseRepository.findAll();
    }

    public void deleteStudentCourse(Long studentId, Long courseId) {
        StudentCourse studentCourse = getStudentCourse(studentId, courseId);
        studentCourseRepository.delete(studentCourse);
    }
}
