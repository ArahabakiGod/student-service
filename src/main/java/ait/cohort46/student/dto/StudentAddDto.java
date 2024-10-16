package ait.cohort46.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentAddDto {
    private long id;
    private String name;
    private String password;
}
