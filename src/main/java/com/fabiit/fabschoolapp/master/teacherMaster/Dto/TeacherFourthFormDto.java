package com.fabiit.fabschoolapp.master.teacherMaster.Dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherFourthFormEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherMasterEntity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherFourthFormDto {
	
	private int fileId;
	
	private String fileName;
	
	private String fileType;
	
	private String fileCustomName;
	
	private String fileInfo;
	
	private String base64String;
	
	public TeacherFourthFormDto(TeacherFourthFormEntity fourth)
	{
		this.fileId=fourth.getFileId();
		this.fileInfo=fourth.getFileInfo();		
		this.fileName=fourth.getFileName();
		this.fileType=fourth.getFileType();
		this.fileCustomName=fourth.getFileCustomName();
		
	
	}
	
	

}
