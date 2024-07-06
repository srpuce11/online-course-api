package com.online_course_api.online_course_api.controller;

import com.online_course_api.online_course_api.Service.StudentCourseService;
import com.online_course_api.online_course_api.repository.entity.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/student-courses")
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping
    public ResponseEntity<StudentCourse> createStudentCourse(@RequestBody StudentCourse studentCourse) {
        return ResponseEntity.ok(studentCourseService.createStudentCourse(studentCourse));
    }

    @GetMapping("/{studentId}/{courseId}")
    public ResponseEntity<StudentCourse> getStudentCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return ResponseEntity.ok(studentCourseService.getStudentCourse(studentId, courseId));
    }

    @GetMapping
    public ResponseEntity<List<StudentCourse>> getAllStudentCourses() {
        return ResponseEntity.ok(studentCourseService.getAllStudentCourses());
    }

    @DeleteMapping("/{studentId}/{courseId}")
    public ResponseEntity<Void> deleteStudentCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentCourseService.deleteStudentCourse(studentId, courseId);
        return ResponseEntity.noContent().build();
    }
}
