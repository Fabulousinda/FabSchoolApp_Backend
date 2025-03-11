package com.fabiit.fabschoolapp.assign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentListAssignDto {

	private int studentId;
	private String name;
	private String fatherName;
	private String fatherNumber;
	private String motherName;
	private String motherNumber;
	private int assignId;
	private String std;
	private String div;
}
