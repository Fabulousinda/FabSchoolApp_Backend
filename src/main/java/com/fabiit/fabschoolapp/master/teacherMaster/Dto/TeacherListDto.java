package com.fabiit.fabschoolapp.master.teacherMaster.Dto;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherMasterEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherListDto {
	
	private int teacherId;
	private String name;
	private String phone;
	private String emailAddress;
	
	
	public TeacherListDto(TeacherMasterEntity teacherMasterEntity)
	{
		this.name=teacherMasterEntity.getName();
		this.teacherId=teacherMasterEntity.getTeacherId();
		this.phone=teacherMasterEntity.getPhone();
		this.emailAddress=teacherMasterEntity.getEmailAddress();
	}

}
