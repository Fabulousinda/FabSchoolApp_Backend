package com.fabiit.fabschoolapp.exam.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMarksDto {

	private int studentId;
	private String studentName;
	private int totalMarks;
	private int studentMarks;
	private int activationId;
	private String evaluationType;
	private String studentGrade;
	private int marksId;
	
}
