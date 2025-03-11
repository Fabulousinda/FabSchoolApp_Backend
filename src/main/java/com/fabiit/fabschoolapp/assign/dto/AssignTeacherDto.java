package com.fabiit.fabschoolapp.assign.dto;

import com.fabiit.fabschoolapp.assign.Entity.AssignTeacherEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignTeacherDto {
	
	private int assignId;
	private int teacherId;
	private String stdName;
	private String divName;
	private String subName;
	
	public AssignTeacherDto(AssignTeacherEntity assignTeacherEntity)
	{
		this.assignId= assignTeacherEntity.getAssignId();
		this.divName= assignTeacherEntity.getDivName();
		this.stdName= assignTeacherEntity.getStdName();
		this.subName= assignTeacherEntity.getSubName();
		this.teacherId= assignTeacherEntity.getTeacherId();
	}

}
