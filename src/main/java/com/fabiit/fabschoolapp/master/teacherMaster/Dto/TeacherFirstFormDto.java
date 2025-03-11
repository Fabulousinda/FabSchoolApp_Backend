package com.fabiit.fabschoolapp.master.teacherMaster.Dto;

import java.time.Instant;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherMasterEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class TeacherFirstFormDto {
	
	
	public TeacherFirstFormDto(TeacherMasterEntity objectTeacherCons) {
		this.teacherId=objectTeacherCons.getTeacherId();
		this.adharnumber=objectTeacherCons.getAdharnumber();
		this.alternatenumber=objectTeacherCons.getAlternatenumber();
		this.currentaddress=objectTeacherCons.getCurrentaddress();
		this.date=objectTeacherCons.getDate();
		this.emailAddress=objectTeacherCons.getEmailAddress();
		this.emergencyNumber=objectTeacherCons.getEmergencyNumber();
		this.fatherName=objectTeacherCons.getFatherName();
		this.gender=objectTeacherCons.getGender();
		this.maritalStatus=objectTeacherCons.getMaritalStatus();
		this.motherName=objectTeacherCons.getMotherName();
		this.pannumber=objectTeacherCons.getPannumber();
		this.presentaddress=objectTeacherCons.getPresentaddress();
		this.phone=objectTeacherCons.getPhone();
		this.name=objectTeacherCons.getName();
		// TODO Auto-generated constructor stub
	}
	private int teacherId;
	private String name;
	private Instant date;
	private String fatherName;
	private String motherName;
	private String gender;
	private String adharnumber;
	private String maritalStatus;
	private String pannumber;
	private String phone;
	private String alternatenumber;
	private String emailAddress;
	private String presentaddress;
	private String currentaddress;
	private String emergencyNumber;
	

}
