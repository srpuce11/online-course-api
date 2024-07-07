package com.online_course_api.online_course_api.Service;

import com.online_course_api.online_course_api.ResourceNotFoundException;
import com.online_course_api.online_course_api.repository.CourseRepository;
import com.online_course_api.online_course_api.repository.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);

        course.setTitle(courseDetails.getTitle());
        course.setDescription(courseDetails.getDescription());
        course.setTeacherId(courseDetails.getTeacherId());
        course.setPrice(courseDetails.getPrice());
        course.setUpdatedAt(LocalDateTime.now());

        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }

    public List<Course> getCourseByTeacherId(int teacherId) {

        return courseRepository.getCourseByTeacherId(teacherId);
    }
}
