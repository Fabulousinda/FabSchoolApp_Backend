package com.fabiit.fabschoolapp.master.studentMaster.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentFirstFormDto;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentFourthFormDto;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentListDto;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentSecondFormDto;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentThirdFormDto;
import com.fabiit.fabschoolapp.master.studentMaster.Service.studentService;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherFirstFormDto;
import com.fabiit.fabschoolapp.utils.FabConstant;
import com.fabiit.fabschoolapp.utils.MessageConstants;
import com.fabiit.fabschoolapp.utils.ResponseDto;
import com.fabiit.fabschoolapp.utils.ResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RestController

public class StudentController {
	
	@Autowired
	studentService studentService;

	@Operation(summary = "Save New Student", description = "Returns newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@PostMapping("/savestudentfirstform")
	public ResponseEntity<ResponseDto> saveStudentFirstForm(@RequestBody StudentFirstFormDto studentFirstFormDto)
	{
		ResponseDto response=null;
		
		StudentFirstFormDto studentFirstFormDto2=studentService.saveStudentFirstForm(studentFirstFormDto);
		if(studentFirstFormDto2 != null)
		{
			response= ResponseUtil.createResponse(MessageConstants.DATA_SAVED, studentFirstFormDto2, true);
		}
		if(studentFirstFormDto2 == null)
		{
			response=ResponseUtil.createResponse(MessageConstants.DATA_NOT_SAVED,null, false);
		}
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	@Operation(summary = "Save Student parents  ", description = "Returns newly saved Details.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@PostMapping("/savestudentsecondform")
	public ResponseEntity<ResponseDto> saveStudentSecondForm(@RequestBody StudentSecondFormDto studentSecondFormDto)
	{
		ResponseDto response=null;
		
		StudentSecondFormDto studentSecondFormDto2=studentService.saveStudentSecondForm(studentSecondFormDto);
		if(studentSecondFormDto2  != null)
		{
			response= ResponseUtil.createResponse(MessageConstants.DATA_SAVED, studentSecondFormDto2, true);
		}
		if(studentSecondFormDto2 == null)
		{
			response= ResponseUtil.createResponse(MessageConstants.DATA_NOT_SAVED, null, false);
		}
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
	@Operation(summary = "Save New Student contact ", description = "Returns newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@PostMapping("/savestudentthirdform")
	public ResponseEntity<ResponseDto> saveStudentThirdForm(@RequestBody StudentThirdFormDto studentThirdFormDto)
	{
		ResponseDto response=null;
		
		try {
			StudentThirdFormDto objectStudentThirdFormDto=studentService.saveStudentThirdFormDto(studentThirdFormDto);
			if(objectStudentThirdFormDto != null)
			{
				response= ResponseUtil.createResponse(MessageConstants.DATA_SAVED, objectStudentThirdFormDto, true);
			}
			
		}
		catch(Exception e)
		{
			response= ResponseUtil.createResponse(MessageConstants.DATA_NOT_SAVED, e, false);
		}
		
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
	@Operation(summary = "Save New Student Files ", description = "Returns newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	
	@PostMapping("/savestudentfourthform")
	public ResponseEntity<ResponseDto> saveStudentFourthForm(
			@RequestParam() MultipartFile birth,
			@RequestParam MultipartFile lc,
			@RequestParam MultipartFile reportCard,
			@RequestParam MultipartFile medical,
			@RequestParam MultipartFile studentAadhar,
			@RequestParam MultipartFile fatherAadhar,
			@RequestParam MultipartFile motherAadhar,
			@RequestParam MultipartFile studentPhoto,
			@RequestParam MultipartFile fatherPhoto,
			@RequestParam MultipartFile motherPhoto,
			@RequestParam Boolean confirm,
			@RequestParam int studentId)
	{
		ResponseDto response=null;
		
		try {
			
			
		List<StudentFourthFormDto> studentFourthFormDto=	studentService.gettingFourthFormInput( birth,lc,reportCard,medical,studentAadhar,fatherAadhar
					,motherAadhar,studentPhoto, fatherPhoto, motherPhoto, studentId);
		if(studentFourthFormDto != null)
		{
			response=ResponseUtil.createResponse(MessageConstants.DATA_SAVED, studentFourthFormDto, true);
		}
	}
		
		catch(Exception e )
		{
			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Getting Student Details  ", description = "Returns newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })

	@GetMapping("/getstudentfirstform")
	public ResponseEntity<ResponseDto> getStudentFirstForm(@RequestParam int id) {
		ResponseDto response=null;
		try {
			StudentFirstFormDto studentFirstFormDto=studentService.getStudentFirstForm(id);
			if(studentFirstFormDto != null)
			{
				response=ResponseUtil.createResponse(MessageConstants.DATA_FOUND, studentFirstFormDto, true);
			}
		}
		catch(Exception e)
		{
			response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, null, false);
		}
		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	}
	
	@Operation(summary = "Getting Student Parents Details ", description = "Returns newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@GetMapping("/getstudentsecondform")
	public ResponseEntity<ResponseDto> getStudentSecondForm(@RequestParam int id)
	{
		ResponseDto response=null;
		try {
			StudentSecondFormDto studentSecondFormDto= studentService.getStudentSecondForm(id);
			if(studentSecondFormDto != null)
			{
				response= ResponseUtil.createResponse(MessageConstants.DATA_FOUND, studentSecondFormDto, true);
			}
		}
		catch(Exception e )
		{
			response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	@Operation(summary = "Getting student Parents Details  ", description = "Returns newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@GetMapping("/getstudentthirdform")
	public ResponseEntity<ResponseDto> getStudentThirdForm(@RequestParam int id)
	{
		ResponseDto response=null;
		
		try {
			StudentThirdFormDto objectStudentThirdForm=studentService.getStudentThirdForm(id);
			if(objectStudentThirdForm != null)
			{
				response= ResponseUtil.createResponse(MessageConstants.DATA_FOUND, objectStudentThirdForm, true);
			}
		}
		catch(Exception e)
		{
			
			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	@Operation(summary = "Getting Students Files  ", description = "Returns newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@GetMapping("/getstudentfourthform")
	public ResponseEntity<ResponseDto> getStudentFourthForm(@RequestParam int id)
	{
		ResponseDto response=null;
		
		try {
			List<StudentFourthFormDto> studntFourthForm= studentService.getStudentFourthForm(id);
			if(studntFourthForm != null)
			{
				response= ResponseUtil.createResponse(MessageConstants.DATA_FOUND, studntFourthForm, true);
			}
			
		}
		catch(Exception e)
		{
			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	@Operation(summary = "Geeting Student List ", description = "Returns newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@GetMapping("getstudentlist")
public ResponseEntity<ResponseDto> getStudentList()
{
	ResponseDto response=null;
	try {
		List<StudentListDto> studentListDto=studentService.getStudentBySorting();
		if(studentListDto != null)
		{
			response = ResponseUtil.createResponse(MessageConstants.DATA_FOUND, studentListDto, true);
		}
	}
	catch(Exception e)
	{
		response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
	}
	return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
}
	
	

	
	
	
}
