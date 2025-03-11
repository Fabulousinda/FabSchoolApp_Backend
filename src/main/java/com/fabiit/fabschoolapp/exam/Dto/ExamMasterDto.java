package com.fabiit.fabschoolapp.exam.Dto;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import com.fabiit.fabschoolapp.exam.entity.ExamMasterEntity;
import com.fabiit.fabschoolapp.utils.FabEnum;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamMasterDto {

	private int examId;
	private String examName;
	private List<Map<String, Object>> standards;
	private boolean confirm;
	private Instant startDate;
	private Instant endDate;
	private FabEnum.state state;
	
	
	public ExamMasterDto(ExamMasterEntity examMasterEntity)
	{
		this.examId=examMasterEntity.getExamId();
		this.examName=examMasterEntity.getExamName();
	    this.state=examMasterEntity.getState();
		this.confirm=examMasterEntity.isConfirm();
		this.startDate=examMasterEntity.getStartDate();
		this.endDate=examMasterEntity.getEndDate();
		
	}
}
