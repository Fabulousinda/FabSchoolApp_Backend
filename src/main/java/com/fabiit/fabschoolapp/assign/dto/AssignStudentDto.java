package com.fabiit.fabschoolapp.assign.dto;

import com.fabiit.fabschoolapp.assign.Entity.AssignStudentEntity;
import com.fabiit.fabschoolapp.master.divisionMaster.entity.DivisionEntity;

import com.fabiit.fabschoolapp.master.standardMaster.entity.StandardEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignStudentDto {
	
	private int assignId;
	private int studentId;
	private String stdName;
	private String divName;
	
	
	public AssignStudentDto(AssignStudentEntity assignEntity)
	{
		this.assignId=assignEntity.getAssignId();
		this.stdName=assignEntity.getStdName();
		this.divName=assignEntity.getDivName();
		this.studentId=assignEntity.getStudentId();
		
		}
	
	

}
