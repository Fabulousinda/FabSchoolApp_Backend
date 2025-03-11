package com.fabiit.fabschoolapp.exam.Dto;

import java.util.List;
import java.util.Map;

import com.fabiit.fabschoolapp.exam.entity.ExamActivationEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamActivationDto {
	
	private int activationId;
	private int examId;
	private int stdId;
	private String stdValue;
	private int subjectId;
	private String subName;
	private String evaluationType;
	private int totalMarks;
	private int passingMarks;
	
	
	public ExamActivationDto(ExamActivationEntity examActivation)
	{
		this.activationId=examActivation.getActivationId();
		this.evaluationType=examActivation.getEvaluationType();
		this.examId=examActivation.getExamId();
		this.totalMarks=examActivation.getTotalMarks();
		this.passingMarks=examActivation.getPassingMarks();
		this.stdValue=examActivation.getStandard();
		this.subName=examActivation.getSubject();
		
	}
	
	
	
	
	
}
