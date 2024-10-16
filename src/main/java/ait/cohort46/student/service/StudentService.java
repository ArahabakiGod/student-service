package ait.cohort46.student.service;

import ait.cohort46.student.dto.ScoreAddDto;
import ait.cohort46.student.dto.StudentAddDto;
import ait.cohort46.student.dto.StudentDto;
import ait.cohort46.student.dto.StudentUpdateDto;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Boolean addStudent(StudentAddDto studentAddDto);

    StudentDto findStudent(Long id);

    StudentDto removeStudent(Long id);

    StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto);

    Boolean addScore(Long id, ScoreAddDto scoreAddDto);

    List<StudentDto> findStudentByName(String name);

    Integer getStudentQuantityByNames(Set<String> names);

    List<StudentDto> findStudentsByExamMinScore(String examName, Integer minScore);
}
