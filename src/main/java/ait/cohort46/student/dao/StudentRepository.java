package ait.cohort46.student.dao;

import ait.cohort46.student.model.Student;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.stream.Stream;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Stream<Student> findByNameIgnoreCase(String name);

    @Query("{'scores.?0': {$gt:  ?1}}")
    Stream<Student> getStudentsByExamMinScore(String examName, Integer minScore);

    Long countByNameInIgnoreCase(Set<String> names);
}
