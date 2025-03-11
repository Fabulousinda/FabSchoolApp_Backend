package com.fabiit.fabschoolapp.master.teacherMaster.Dto;

import java.util.List;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherProfessionEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherMasterEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherThirdFormEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherThirdFormDto {
	
	private int teacherId;
	private int professionId;
	private String branch;
	private String accountNumber;
	private String ifscCode;
	private String bank;
	private String employeeType;
	private String pay;
	private String level;
	private String total;
	private String subject;
	private String salary;
	private List<TeacherProfessionEntity> professionEntity;
	
	public TeacherThirdFormDto(TeacherMasterEntity teacherMasterEntity)
	{
		this.teacherId=teacherMasterEntity.getTeacherId();
		this.accountNumber=teacherMasterEntity.getThirdFormEntity().getAccountNumber();
		this.bank=teacherMasterEntity.getThirdFormEntity().getBank();
		this.branch=teacherMasterEntity.getThirdFormEntity().getBranch();
		this.employeeType=teacherMasterEntity.getThirdFormEntity().getEmployeeType();
		this.ifscCode=teacherMasterEntity.getThirdFormEntity().getIfscCode();
		this.level=teacherMasterEntity.getThirdFormEntity().getLevel();
		this.pay=teacherMasterEntity.getThirdFormEntity().getPay();
		this.salary=teacherMasterEntity.getThirdFormEntity().getSalary();
		this.subject=teacherMasterEntity.getThirdFormEntity().getSubject();
		this.total=teacherMasterEntity.getThirdFormEntity().getTotal();
		this.professionEntity=teacherMasterEntity.getThirdFormEntity().getExperienceDetail();
		this.professionId=teacherMasterEntity.getThirdFormEntity().getProfessionId();
		
		
			}
	
	

}
