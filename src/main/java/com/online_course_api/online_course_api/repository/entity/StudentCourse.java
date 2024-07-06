package com.online_course_api.online_course_api.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student_courses")
@IdClass(StudentCourseId.class)
public class StudentCourse {

	@Id
	@Column(name = "studentId")
	private Long studentId;

	@Id
	@Column(name = "courseId")
	private Long courseId;
}
