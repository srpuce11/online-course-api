package com.online_course_api.online_course_api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online_course_api.online_course_api.Service.FileStorageService;
import com.online_course_api.online_course_api.Service.LectureService;
import com.online_course_api.online_course_api.repository.entity.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/courses")
public class LectureController {
    @Autowired
    private LectureService lectureService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/course/{courseId}/lectures")
    public List<Lecture> getAllLectures(@PathVariable Long courseId) {
        return lectureService.getAllLecturesByCourseId(courseId);
    }

    @GetMapping("/course/{courseId}/lectures/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable Long courseId, @PathVariable Long id) {
        Optional<Lecture> lecture = lectureService.getLectureById(id);
        return lecture.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/course/{courseId}/lectures")
    public Lecture createLecture(@RequestPart("lecture") String lectureJson, @PathVariable Long courseId, @RequestParam("file") MultipartFile file) throws JsonProcessingException {
        Lecture lecture = new ObjectMapper().readValue(lectureJson, Lecture.class);
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromPath("/")
                .path(fileName)
                .toUriString();
        lecture.setVideoUrl(fileDownloadUri);
        return lectureService.saveLecture(lecture, courseId);
    }


    @PutMapping("/course/{courseId}/lectures/{id}")
    public ResponseEntity<Lecture> updateLecture(@RequestParam("file") MultipartFile file, @PathVariable Long id, @RequestBody Lecture lectureDetails, @PathVariable Long courseId) {

        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/api/videos/download/")
        		.path("/edu-app/")
                .path(fileName)
                .toUriString();
        lectureDetails.setVideoUrl(fileDownloadUri);

        Optional<Lecture> optionalLecture = lectureService.getLectureById(id);
        if (optionalLecture.isPresent()) {
            Lecture lecture = optionalLecture.get();
            lecture.setTitle(lectureDetails.getTitle());
            lecture.setVideoUrl(lectureDetails.getVideoUrl());
            lecture.setCourse(lectureDetails.getCourse());
            final Lecture updatedLecture = lectureService.saveLecture(lecture, courseId);
            return ResponseEntity.ok(updatedLecture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/course/{courseId}/lectures/{id}")
    public ResponseEntity<Void> deleteLecture(@PathVariable Long id, @PathVariable Long courseId) {
        Optional<Lecture> optionalLecture = lectureService.getLectureById(id);
        if (optionalLecture.isPresent()) {
            lectureService.deleteLecture(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/course/{courseId}/lectures/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long courseId, @PathVariable String fileName) {
        Path filePath = fileStorageService.loadFileAsResource(fileName);

//        Optional<Lecture> optionalLecture = lectureService.getLectureById(id);
//        if (optionalLecture.isPresent()) {
//            String videoLink = optionalLecture.get().getVideoUrl();
//        }

        Resource resource;
        try {
            resource = new PathResource(filePath);
            System.out.println("Resource: " + resource.getURI());
            System.out.println("Resource exists: " + resource.exists());
            System.out.println("Resource is readable: " + resource.isReadable());
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println("Error loading resource: " + filePath + "-------------->>>>>>>>>>>" + e.getStackTrace().toString());
            return ResponseEntity.notFound().build();
        }

        String contentType;
        try {
            contentType = Files.probeContentType(filePath);
        } catch (IOException ex) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
