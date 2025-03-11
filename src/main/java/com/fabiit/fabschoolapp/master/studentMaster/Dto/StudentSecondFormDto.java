package com.fabiit.fabschoolapp.master.studentMaster.Dto;

import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentSecondFormEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSecondFormDto {

	public StudentSecondFormDto(StudentMasterEntity studentMasterEntity) {
		
        this.studentId=studentMasterEntity.getStudentId();
        this.address=studentMasterEntity.getStudentSecondFormEntity().getAddress();
        this.aadharNumber=studentMasterEntity.getStudentSecondFormEntity().getAadharNumber();
        this.designation=studentMasterEntity.getStudentSecondFormEntity().getDesignation();
        this.daughter=studentMasterEntity.getStudentSecondFormEntity().getDaughter();
        this.email=studentMasterEntity.getStudentSecondFormEntity().getEmail();
        this.fatherName=studentMasterEntity.getStudentSecondFormEntity().getFatherName();
		this.fatherNumber=studentMasterEntity.getStudentSecondFormEntity().getFatherNumber();
		this.hobbies=studentMasterEntity.getStudentSecondFormEntity().getHobbies();
		this.income=studentMasterEntity.getStudentSecondFormEntity().getIncome();
		this.motherAadhar=studentMasterEntity.getStudentSecondFormEntity().getMotherAadhar();
		this.motherDesignation=studentMasterEntity.getStudentSecondFormEntity().getMotherDesignation();
		this.motherEmail=studentMasterEntity.getStudentSecondFormEntity().getMotherEmail();
		this.motherHobbies=studentMasterEntity.getStudentSecondFormEntity().getMotherHobbies();
		this.motherIncome=studentMasterEntity.getStudentSecondFormEntity().getMotherIncome();
		this.motherName=studentMasterEntity.getStudentSecondFormEntity().getMotherName();
		this.motherNumber=studentMasterEntity.getStudentSecondFormEntity().getMotherNumber();
		this.motherOfficeAddress=studentMasterEntity.getStudentSecondFormEntity().getMotherOfficeAddress();
		this.motherOfficeNumber=studentMasterEntity.getStudentSecondFormEntity().getMotherOfficeNumber();
		this.motherOrganization=studentMasterEntity.getStudentSecondFormEntity().getMotherOrganization();
		this.motherProfession=studentMasterEntity.getStudentSecondFormEntity().getMotherProfession();
		this.motherQualification=studentMasterEntity.getStudentSecondFormEntity().getMotherQualification();
		this.motherSocialActivity=studentMasterEntity.getStudentSecondFormEntity().getMotherSocialActivity();
	    this.noOfChild=studentMasterEntity.getStudentSecondFormEntity().getNoOfChild();
	    this.officeNumber=studentMasterEntity.getStudentSecondFormEntity().getOfficeNumber();
	    this.organization=studentMasterEntity.getStudentSecondFormEntity().getOrganization();
	    this.parentsId=studentMasterEntity.getStudentSecondFormEntity().getParentsId();
	    this.profession=studentMasterEntity.getStudentSecondFormEntity().getProfession();
	    this.qualification=studentMasterEntity.getStudentSecondFormEntity().getQualification();
	    this.socialActivity=studentMasterEntity.getStudentSecondFormEntity().getSocialActivity();
	    this.son=studentMasterEntity.getStudentSecondFormEntity().getSon();

	}
	private int studentId;
	private int parentsId;
	private String fatherName;
	private String fatherNumber;
	private String email;
	private String aadharNumber;
	private String qualification;
	private String profession;
	private String organization;
	private String designation;
	private String address;
	private String officeNumber;
	private int income;
	private String socialActivity;
	private String hobbies;
	private String motherName;
	private String motherNumber;
	private String motherEmail;
	private String motherAadhar;
	private String motherQualification;
	private String motherProfession;
	private String motherOrganization;
	private String motherDesignation;
	private String motherOfficeAddress;
	private String motherOfficeNumber;
	private int motherIncome;
	private String motherSocialActivity;
	private String motherHobbies;
	private int noOfChild;
	private int son;
	private int daughter;
	
	
	
}
