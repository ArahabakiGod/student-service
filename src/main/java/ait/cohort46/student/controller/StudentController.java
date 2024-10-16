package ait.cohort46.student.controller;

import ait.cohort46.student.dto.ScoreAddDto;
import ait.cohort46.student.dto.StudentAddDto;
import ait.cohort46.student.dto.StudentDto;
import ait.cohort46.student.dto.StudentUpdateDto;
import ait.cohort46.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/student")
    public Boolean addStudent(@RequestBody StudentAddDto studentAddDto) {
        return studentService.addStudent(studentAddDto);
    }

    @GetMapping("/student/{id}")
    public StudentDto findStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @DeleteMapping("/student/{id}")
    public StudentDto removeStudent(@PathVariable Long id) {
        return studentService.removeStudent(id);
    }

    @PutMapping("/student/{id}")
    public StudentAddDto updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDto studentUpdateDto) {
        return studentService.updateStudent(id, studentUpdateDto);
    }

    @PutMapping("/score/student/{id}")
    public Boolean addScore(@PathVariable Long id, @RequestBody ScoreAddDto scoreAddDto) {
        return studentService.addScore(id, scoreAddDto);
    }

    @GetMapping("/students/name/{name}")
    public List<StudentDto> findStudentByName(@PathVariable String name) {
        return studentService.findStudentByName(name);
    }

    @PostMapping("/quantity/students")
    public Integer getStudentQuantityByNames(@RequestBody Set<String> names) {
        return studentService.getStudentQuantityByNames(names);
    }

    @GetMapping("/students/exam/{examName}/minscore/{minScore}")
    public List<StudentDto> findStudentsByExamMinScore(@PathVariable String examName, @PathVariable Integer minScore) {
        return studentService.findStudentsByExamMinScore(examName, minScore);
    }
}
