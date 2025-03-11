package com.fabiit.fabschoolapp.master.studentMaster.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="studentDetails")
public class StudentMasterEntity implements Serializable {
	

	private static final long serialVersionUID = 100L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int studentId;
	@Column
	private String name;
	@Column
	private int enrollment;
	@Column
	private String father;
	@Column
	private String mother;
	@Column
	private String motherTongue;
	@Column
	private String nationality;
	@Column
	private String phone;
	@Column
	private String placeOfBirth;
	@Column
	private Date dateOfBirth;
	@Column
	private String gender;
	@Column
	private String bloodGroup;
	@Column
	private String caste;
	@Column
	private String adharNumber;
	@Column
	private String medicalCondition;
	@Column
	private String lastSchoolName;
	@Column
	private String grade;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="studentParentsInfo")
	private StudentSecondFormEntity studentSecondFormEntity;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="studentContactInfo")
	private StudentThirdFormEntity studentThirdFormEntity;
	
	@OneToMany(cascade = CascadeType.ALL )
	@Column(name="studentFiles")
	private List<StudentFourthFormEntity> fourthFormStudent;

}
