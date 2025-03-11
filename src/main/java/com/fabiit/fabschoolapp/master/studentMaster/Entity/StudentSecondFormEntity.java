package com.fabiit.fabschoolapp.master.studentMaster.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="parentsInfo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSecondFormEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int parentsId;
	@Column
	private String fatherName;
	@Column
	private String fatherNumber;
	@Column
	private String email;
	@Column
	private String aadharNumber;
	@Column
	private String qualification;
	@Column
	private String profession;
	@Column
	private String organization;
	@Column
	private String designation;
	@Column
	private String address;
	@Column
	private String officeNumber;
	@Column
	private int income;
	@Column
	private String socialActivity;
	@Column
	private String hobbies;
	@Column
	private String motherName;
	@Column
	private String motherNumber;
	@Column
	private String motherEmail;
	@Column
	private String motherAadhar;
	@Column
	private String motherQualification;
	@Column
	private String motherProfession;
	@Column
	private String motherOrganization;
	@Column
	private String motherDesignation;
	@Column
	private String motherOfficeAddress;
	@Column
	private String motherOfficeNumber;
	@Column
	private int motherIncome;
	@Column
	private String motherSocialActivity;
	@Column
	private String motherHobbies;
	@Column
	private int noOfChild;
	@Column
	private int son;
	@Column
	private int daughter;

	
}
