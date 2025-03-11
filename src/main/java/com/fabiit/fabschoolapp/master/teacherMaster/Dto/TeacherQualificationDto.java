package com.fabiit.fabschoolapp.master.teacherMaster.Dto;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherQualification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherQualificationDto {
	
	public TeacherQualificationDto(TeacherQualification teacherQualification)
	{
		this.qualificationId=teacherQualification.getQualificationId();
		this.degree=teacherQualification.getDegree();
		this.board=teacherQualification.getBoard();
		this.year=teacherQualification.getYear();
	}
	
	private int qualificationId;
	private String degree;
	private String board;
	private String year;
	
	
	

}
