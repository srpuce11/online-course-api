package com.online_course_api.online_course_api.Service;


import com.online_course_api.online_course_api.repository.LectureRepository;
import com.online_course_api.online_course_api.repository.entity.Course;
import com.online_course_api.online_course_api.repository.entity.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {
    @Autowired
    private LectureRepository lectureRepository;

    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public Optional<Lecture> getLectureById(Long id) {
        return lectureRepository.findById(id);
    }

    public Lecture saveLecture(Lecture lecture, long courseId) {

        Course course = new Course();
        course.setId(courseId);
        lecture.setCourse(course);
        return lectureRepository.save(lecture);
    }

    public void deleteLecture(Long id) {
        lectureRepository.deleteById(id);
    }


    public List<Lecture> getAllLecturesByCourseId(Long courseId) {

        return lectureRepository.getLectureByCourseId(courseId);

    }
}
