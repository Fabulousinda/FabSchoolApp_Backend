package com.fabiit.fabschoolapp.master.teacherMaster.Dto;

import java.time.Instant;
import java.util.List;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherCertificate;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherQualification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherMasterDto {
	
	private int masterId;
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
	private String emergenynumber;
	private int teacherId;
	private List<TeacherQualification> qualificationDto;
	private List<TeacherCertificate> certificateDto;
	
	

}
