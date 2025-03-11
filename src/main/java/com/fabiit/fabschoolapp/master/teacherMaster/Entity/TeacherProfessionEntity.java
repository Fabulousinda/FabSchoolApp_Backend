package com.fabiit.fabschoolapp.master.teacherMaster.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="experienceDetail")
@Data
@NoArgsConstructor
public class TeacherProfessionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int organizationId;
	@Column(name="company")
	private String organization;
	@Column
	private String designation;
	@Column
	private String joiningYear;
	@Column
	private String resigningYear;
	@Column
	private int experience;

}
