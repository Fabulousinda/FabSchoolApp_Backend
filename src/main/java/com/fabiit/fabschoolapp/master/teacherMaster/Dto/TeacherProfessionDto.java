package com.fabiit.fabschoolapp.master.teacherMaster.Dto;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherProfessionEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherProfessionDto {
	
	private int organizationId;
	private String organization;
	private String designation;
	private String joiningYear;
	private String resigningYear;
	private int experience;
	
	public TeacherProfessionDto(TeacherProfessionEntity professionEntity)
	{
		this.organizationId=professionEntity.getOrganizationId();
		this.designation=professionEntity.getDesignation();
		this.experience=professionEntity.getExperience();
		this.joiningYear=professionEntity.getJoiningYear();
		this.organization=professionEntity.getOrganization();
		this.resigningYear=professionEntity.getResigningYear();
	}
	

}
