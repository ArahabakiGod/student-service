package ait.cohort46.student.service;

import ait.cohort46.student.dao.StudentRepository;
import ait.cohort46.student.dto.ScoreAddDto;
import ait.cohort46.student.dto.StudentAddDto;
import ait.cohort46.student.dto.StudentDto;
import ait.cohort46.student.dto.StudentUpdateDto;
import ait.cohort46.student.dto.exceptions.StudentNotFoundException;
import ait.cohort46.student.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        if (studentRepository.findById(studentAddDto.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(studentAddDto.getId(), studentAddDto.getName(), studentAddDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getScores());
        return studentDto;
    }

    @Override
    public StudentDto removeStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.deleteById(id);
        StudentDto studentDto = new StudentDto(student.getId(), student.getName(), student.getScores());
        return studentDto;
    }

    @Override
    public StudentAddDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        student.setName(studentUpdateDto.getName());
        student.setPassword(studentUpdateDto.getPassword());
        studentRepository.save(student);
        StudentAddDto studentAddDto = new StudentAddDto(student.getId(), student.getName(), student.getPassword());
        return studentAddDto;
    }

    @Override
    public Boolean addScore(Long id, ScoreAddDto scoreAddDto) {
        Student student = studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return student.addScore(scoreAddDto.getExamName(), scoreAddDto.getScore());
    }

    @Override
    public List<StudentDto> findStudentByName(String name) {
        List<StudentDto> students = StreamSupport.stream(studentRepository.findAll().spliterator(), true)
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getScores()))
                .toList();
        return students;
    }

    @Override
    public Integer getStudentQuantityByNames(Set<String> names) {
        long quantity = StreamSupport.stream(studentRepository.findAll().spliterator(), true)
                .filter(student -> names.contains(student.getName()))
                .count();
        return (int) quantity;
    }

    @Override
    public List<StudentDto> findStudentsByExamMinScore(String examName, Integer minScore) {
        List<StudentDto> students = StreamSupport.stream(studentRepository.findAll().spliterator(), true)
                .filter(student -> student.getScores().containsKey(examName) && student.getScores().get(examName) >= minScore)
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getScores()))
                .toList();
        return students;
    }
}