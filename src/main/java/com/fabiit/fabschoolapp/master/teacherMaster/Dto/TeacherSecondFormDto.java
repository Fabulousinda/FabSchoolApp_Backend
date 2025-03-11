package com.fabiit.fabschoolapp.master.teacherMaster.Dto;

import java.util.List;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherCertificate;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherMasterEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherQualification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherSecondFormDto {

	public TeacherSecondFormDto(TeacherMasterEntity save) {
		this.teacherId = save.getTeacherId();
		this.certificate = save.getCertificate();
		this.degree = save.getQualification();
	}

	private int teacherId;
	private List<TeacherQualification> degree;
	private List<TeacherCertificate> certificate;

}
