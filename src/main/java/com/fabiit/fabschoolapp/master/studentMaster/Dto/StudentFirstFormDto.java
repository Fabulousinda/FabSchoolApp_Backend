package com.fabiit.fabschoolapp.master.studentMaster.Dto;

import java.sql.Date;

import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFirstFormDto {
	
	
	public StudentFirstFormDto(StudentMasterEntity studentMasterEntity) {
		this.adharNumber=studentMasterEntity.getAdharNumber();
		this.bloodGroup=studentMasterEntity.getBloodGroup();
		this.caste=studentMasterEntity.getCaste();
		this.dateOfBirth=studentMasterEntity.getDateOfBirth();
		this.enrollment=studentMasterEntity.getEnrollment();
		this.father=studentMasterEntity.getFather();
		this.gender=studentMasterEntity.getGender();
		this.grade=studentMasterEntity.getGrade();
		this.lastSchoolName=studentMasterEntity.getLastSchoolName();
		this.medicalCondition=studentMasterEntity.getMedicalCondition();
		this.mother=studentMasterEntity.getMother();
		this.motherTongue=studentMasterEntity.getMotherTongue();
		this.name=studentMasterEntity.getName();
		this.nationality=studentMasterEntity.getNationality();
		this.phone=studentMasterEntity.getPhone();
		this.placeOfBirth=studentMasterEntity.getPlaceOfBirth();
		this.studentId=studentMasterEntity.getStudentId();
		
	}
	private int studentId;
	private String name;
	private int enrollment;
	private String father;
	private String mother;
	private String motherTongue;
	private String nationality;
	private String phone;
	private String placeOfBirth;
	private Date dateOfBirth;
	private String gender;
	private String bloodGroup;
	private String caste;
	private String adharNumber;
	private String medicalCondition;
	private String lastSchoolName;
	private String grade;
	

}
