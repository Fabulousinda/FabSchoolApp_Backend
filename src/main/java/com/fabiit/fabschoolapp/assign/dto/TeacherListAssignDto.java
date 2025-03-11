package com.fabiit.fabschoolapp.assign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherListAssignDto {
	
	private int teacherId;
	private int assignId;
	private int standardId;
	private String stdName;
	private int divisionId;
	private String divName;
	private int subjectId;
	private String subName;
	private boolean classTeacher;

}
