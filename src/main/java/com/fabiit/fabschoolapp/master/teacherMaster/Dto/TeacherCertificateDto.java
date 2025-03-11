package com.fabiit.fabschoolapp.master.teacherMaster.Dto;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherCertificate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCertificateDto {
	
	public TeacherCertificateDto(TeacherCertificate teacherCertificate)
	{
		this.certificateId=teacherCertificate.getCertificateId();
		this.certificate=teacherCertificate.getCertificate();
		this.skills=teacherCertificate.getSkills();
		
	}
	
	private int certificateId;
	private String certificate;
	private String skills;

}
