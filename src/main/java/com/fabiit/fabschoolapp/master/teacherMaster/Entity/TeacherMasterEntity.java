package com.fabiit.fabschoolapp.master.teacherMaster.Entity;

import java.io.Serializable;
import java.time.Instant;
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
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="teacher")
@Data
@NoArgsConstructor
public class TeacherMasterEntity implements Serializable {

	private static final long serialVersionUID = 11L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int teacherId;
	@Column
	private String name;
	@Column
	private Instant date;
	@Column
	private String fatherName;
	@Column
	private String motherName;
	@Column
	private String gender;
	@Column
	private String adharnumber;
	@Column
	private String maritalStatus;
	@Column
	private String pannumber;
	@Column
	private String phone;
	@Column
	private String alternatenumber;
	@Column
	private String emailAddress;
	@Column
	private String presentaddress;
	@Column
	private String currentaddress;
	@Column
	private String emergencyNumber;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Column(name="qualificationId")
	private List<TeacherQualification> qualification;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Column(name="certificateId")
	private List<TeacherCertificate> certificate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="salaryDetail")
	private TeacherThirdFormEntity thirdFormEntity;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Column(name ="teacherFiles")
	private List<TeacherFourthFormEntity> files;

	
	

}
