package com.fabiit.fabschoolapp.exam.controller;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabiit.fabschoolapp.exam.Dto.AddMarksDto;
import com.fabiit.fabschoolapp.exam.Dto.ExamActivationDto;
import com.fabiit.fabschoolapp.exam.Dto.ExamMasterDto;
import com.fabiit.fabschoolapp.exam.Dto.StudentMarksDto;
import com.fabiit.fabschoolapp.exam.Service.ExamService;


import com.fabiit.fabschoolapp.utils.MessageConstants;
import com.fabiit.fabschoolapp.utils.ResponseDto;
import com.fabiit.fabschoolapp.utils.ResponseUtil;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ExamController {
	
	@Autowired
	private  ExamService examService;

	@PostMapping("createexam")
	public ResponseEntity<ResponseDto> saveStudentExam(
			@RequestParam String name,
			@RequestParam List<Integer> standards,
			@RequestParam boolean confirm,
			@RequestParam Instant startDate,
			@RequestParam Instant endDate)
	{
		ResponseDto response=null;
		
		try {
			List<ExamMasterDto> objectExamDto=examService.createStudentExam( name,standards,confirm,startDate,endDate);
			if(objectExamDto != null)
			{
				response= ResponseUtil.createResponse(MessageConstants.DATA_SAVED, objectExamDto, true);
			}
		}
		catch(Exception e)
		{
			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
	@GetMapping("getexam")
	public ResponseEntity<ResponseDto> getStudentExam()
	{
		ResponseDto response=null;
		

		
		try {
			List<ExamMasterDto> objectExam= examService.getStudentExam();
			if(objectExam != null)
			{
				response= ResponseUtil.createResponse(MessageConstants.DATA_FOUND, objectExam, true);
			}
		}
		catch(Exception e)
		{
			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
				
				
				return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
	@PostMapping("saveexamactivation")
	public ResponseEntity<ResponseDto> saveExamMarks(
			@RequestParam(required = false, defaultValue = "0") int activationId,
			@RequestParam int examId,
			@RequestParam int standard,
			@RequestParam List<Integer> subject,
			@RequestParam String evaluationType,
			@RequestParam(required = false, defaultValue = "-1") int totalMarks,
			@RequestParam(required = false, defaultValue = "-1") int passingMarks,
			@RequestParam(required = false) String grade
			)
			
			
	{
		ResponseDto response=null;
		
		try {
			List<ExamActivationDto> objectExamMarks=examService.saveExamActivation(activationId,examId,standard,subject,evaluationType,totalMarks,passingMarks, grade);
			if(objectExamMarks != null)
			{
				response=ResponseUtil.createResponse(MessageConstants.DATA_FOUND, objectExamMarks, true);
			}
		}
		catch(Exception e)
		{
			response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
	@GetMapping("getexamactivation")
	public ResponseEntity<ResponseDto> getExamMarks(@RequestParam int examId)
	{
		ResponseDto response=null;
		
		try {
			List<ExamActivationDto> objectExamMarks=examService.getExamActivation(examId);
			if(objectExamMarks != null)
			{
				response=ResponseUtil.createResponse(MessageConstants.DATA_FOUND, objectExamMarks, true);
			}
		}
		catch(Exception e)
		{
			response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
	@PostMapping("savestudentmarks")
	public ResponseEntity<ResponseDto> getStudentByStdDiv(
			@RequestParam int examId,
			@RequestParam int std,
			@RequestParam int div,
			@RequestParam int sub,
		  @RequestBody	List<StudentMarksDto> studentMarksDto
			)

	{
		ResponseDto response = null;
		try {
			List<AddMarksDto> objectStudentMarks = examService.studentDetailMarks(examId,std,div,sub, studentMarksDto);
			if (objectStudentMarks != null) {
				response = ResponseUtil.createResponse(MessageConstants.DATA_FOUND, objectStudentMarks, true);
			}
		} catch (Exception e) {
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}

		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}	
	
@GetMapping("getstudentdetailsbystddivsub")
public ResponseEntity<ResponseDto> getStudentByStdDivSub(
		@RequestParam int examId,
		@RequestParam int std,
		@RequestParam int div,
		@RequestParam int sub)
{
	ResponseDto response=null;
	try {
		List<AddMarksDto> studentDetailMarksDto=examService.getStudentByStdDivSub(examId, std, div, sub);
		if(studentDetailMarksDto != null)
		{
			response=ResponseUtil.createResponse(MessageConstants.DATA_FOUND, studentDetailMarksDto, true);
		}
	}
	catch(Exception e)
	{
		response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
	}
	
	return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
}

@PutMapping("updatestatus/{examId}")
public ResponseEntity<ResponseDto> updateStatus(@PathVariable("examId") int examId)
{
	ResponseDto response=null;
	try {
		ExamMasterDto examMasterDto=examService.updateStatus(examId);
		if(examMasterDto != null)
		{
			response= ResponseUtil.createResponse(MessageConstants.DATA_SAVED, examMasterDto, true);
		}
		
	}
	catch(Exception e)
	{
		response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
	}
	
	return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
}
@DeleteMapping("deleteexam/{examId}")
public ResponseEntity<ResponseDto> deleteExam(@PathVariable("examId") int examId)
{
	ResponseDto response=null;
	try {
		ExamMasterDto examMasterDto = examService.deleteExam(examId);
		if(examMasterDto != null)
		{
			response=ResponseUtil.createResponse(MessageConstants.EXAM_DELETED, examMasterDto, true);
		}
	}
	catch(Exception e)
	{
		response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
	}
	
	return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
}
@GetMapping("examActivationsearch")
public ResponseEntity<ResponseDto> examActivationSearch(@RequestParam String search)
{
	ResponseDto response=null;
	try {
		List<ExamActivationDto> examActivation=examService.examActivationSearch(search);
		if(examActivation != null)
		{
			response=ResponseUtil.createResponse(MessageConstants.DATA_FOUND, examActivation, true);
		}
	}
	catch(Exception e)
	{
		response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
	}
	
	return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
}

@GetMapping("studentMarkssearch")
public ResponseEntity<ResponseDto> studentMarkssearch(
		@RequestParam int examId, 
		@RequestParam int std,
		@RequestParam int div,
		@RequestParam int sub,
		@RequestParam String search)
{
	ResponseDto response=null;
	try {
		List<AddMarksDto> addMarks=examService.studentMarksSearch(examId, std,div,sub,search);
		if(addMarks != null)
		{
			response=ResponseUtil.createResponse(MessageConstants.DATA_FOUND, addMarks, true);
		}
	}
	catch(Exception e)
	{
		response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
	}
	
	return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
}
	
}

	
