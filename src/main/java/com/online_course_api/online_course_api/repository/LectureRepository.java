package com.online_course_api.online_course_api.repository;

import com.online_course_api.online_course_api.repository.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> getLectureByCourseId(Long courseId);
}
