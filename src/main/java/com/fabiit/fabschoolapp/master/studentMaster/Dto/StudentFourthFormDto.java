package com.fabiit.fabschoolapp.master.studentMaster.Dto;

import java.util.List;

import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentFourthFormEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFourthFormDto {

private int fileId;
	
	private String fileName;
	
	private String fileType;
	
	private String fileCustomName;
	
	private String fileInfo;
	
	private String base64String;
	
	public StudentFourthFormDto(StudentFourthFormEntity studentFourthForm)
	{
		
		this.fileId=studentFourthForm.getFileId();
		this.fileCustomName=studentFourthForm.getFileCustomName();
		this.fileInfo=studentFourthForm.getFileInfo();
		this.fileType=studentFourthForm.getFileType();
		this.fileName=studentFourthForm.getFileName();
	}
}
