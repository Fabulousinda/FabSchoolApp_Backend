package com.fabiit.fabschoolapp.exam.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // Generates a proper default constructor
@AllArgsConstructor // Generates a constructor with all arguments
public class StudentMarksDto {
    private int marksId;
    private int studentId;
    private int activationId;
    private Integer studentMarks = -1;  // Default value
    private Integer gradeId = 0;        // Default value
    
    
}

