package com.fabiit.fabschoolapp.master.teacherMaster.Entity;

import java.util.List;

import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherProfessionDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="salaryDetail")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherThirdFormEntity {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int professionId;
	@Column
	private String branch;
	@Column	
	private String accountNumber;
	@Column
	private String ifscCode;
	@Column
	private String bank;
	@Column
	private String employeeType;
    @Column
	private String pay;
    @Column
	private String level;
    @Column
	private String total;
    @Column
	private String subject;
    @Column
	private String salary;
	@OneToMany(cascade = CascadeType.ALL)
	@Column(name="experienceDetail")
	private List<TeacherProfessionEntity> experienceDetail;

}
