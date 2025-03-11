package com.fabiit.fabschoolapp.master.studentMaster.Dto;

import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentListDto {
	
	private int studentId;
	private String name;
	private String father;
	private String phone;
	private String mother;
	private String motherNumber;
	
	
	public StudentListDto(StudentMasterEntity studentMasterEntity)
	{
		this.studentId= studentMasterEntity.getStudentId();
		this.name=studentMasterEntity.getName();
		this.father= studentMasterEntity.getFather();
		this.phone= studentMasterEntity.getPhone();
		this.mother=studentMasterEntity.getMother();
		this.motherNumber= studentMasterEntity.getStudentSecondFormEntity().getMotherNumber();
	}

}
