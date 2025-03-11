package com.fabiit.fabschoolapp.master.teacherMaster.Controller;


import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fabiit.fabschoolapp.master.gradeMaster.dto.GradeDto;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentFourthFormDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherFourthFormDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherListDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherFirstFormDto;

import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherSecondFormDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherThirdFormDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherFourthFormEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherMasterEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.repo.TeacherMasterRepo;
import com.fabiit.fabschoolapp.master.teacherMaster.service.TeacherService;
import com.fabiit.fabschoolapp.utils.FabConstant;
import com.fabiit.fabschoolapp.utils.MessageConstants;
import com.fabiit.fabschoolapp.utils.ResponseDto;
import com.fabiit.fabschoolapp.utils.ResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class TeacherMasterController {
	
	
	
	@Autowired
	TeacherMasterRepo teacherMasterRepo;
	
	@Autowired
	TeacherService teacherService;
	

	
	@Operation(summary = "Save New Teacher Details  ", description = "Returns newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Teacher was not found") })
	@PostMapping("/firstform")
	public ResponseEntity<ResponseDto> saveFirstForm(@RequestBody TeacherFirstFormDto teacherMasterDto) {
		ResponseDto response=null;
		
			TeacherFirstFormDto firstFormDto = teacherService.saveFirstForm(teacherMasterDto);
			if(firstFormDto != null) {
				response= ResponseUtil.createResponse(MessageConstants.DATA_SAVED, firstFormDto, true);
			
			}
			if (firstFormDto == null)
			{
				response= ResponseUtil.createResponse(MessageConstants.DATA_NOT_SAVED, null, false);
			}
		

		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@Operation(summary = "Save New Teacher Details  ", description = "Returns newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Teacher was not found") })
  @PostMapping("/secondform")
  public ResponseEntity<ResponseDto> saveSecondForm(@RequestBody TeacherSecondFormDto qualificationCertificateDto)
	{
	  ResponseDto response =null;
	  if(qualificationCertificateDto.getTeacherId()== 0)
	  {
		  response= ResponseUtil.createResponse(MessageConstants.ID_NOT_FOUND, null, false);
	  }
	  
		  TeacherSecondFormDto secondFormDto= teacherService.saveSecondForm(qualificationCertificateDto);
		  if(secondFormDto != null)
		  {
			  response= ResponseUtil.createResponse(MessageConstants.DATA_SAVED, secondFormDto, true);
		  }
			 
	 if(secondFormDto == null)
	 {
		 response=ResponseUtil.createResponse(MessageConstants.DATA_NOT_SAVED, null, false);
	 }
      return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
	 
	}
	@Operation(summary = "Save New Teacher Details  ", description = "Returns newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Teacher was not found") })
  @PostMapping("thirdform")
  public ResponseEntity<ResponseDto> saveThirdForm(@RequestBody TeacherThirdFormDto professionDto)
  {
	  ResponseDto response=null;
	  TeacherThirdFormDto thirdFormDtoData=teacherService.saveThirdForm(professionDto);
	  if(professionDto.getTeacherId()== 0)
	  {
		  response= ResponseUtil.createResponse(MessageConstants.ID_NOT_FOUND, null, false);
	  }
	  if(thirdFormDtoData !=null)
	  {
		  response=ResponseUtil.createResponse(MessageConstants.DATA_SAVED, thirdFormDtoData, true);
	  }
	  if(thirdFormDtoData == null)
	  {
		  response=ResponseUtil.createResponse(MessageConstants.DATA_NOT_SAVED, null, false);
	  }
	  return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
  }
  
	@Operation(summary = "Save New Teacher Files  ", description = "Returns newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Teacher was not found") })
  
  @PostMapping("/teacherfourthform")
  public ResponseEntity<ResponseDto> saveFourthForm(
		    @RequestParam MultipartFile resume,
		    @RequestParam MultipartFile photo,
		    @RequestParam MultipartFile aadhar,
		    @RequestParam MultipartFile pan,
		    @RequestParam MultipartFile education,
		    @RequestParam(required = false) MultipartFile experience,
		    @RequestParam(required = false) MultipartFile skill,
		    @RequestParam(required = true) int teacherId) {

		    ResponseDto response = null;

		    try {
		    List<TeacherFourthFormDto> teacherFourthForm=teacherService.gettingFourthForm(resume, photo, aadhar, pan, education, experience, skill, teacherId);
		    if(teacherFourthForm != null)
		    {
		    	response=ResponseUtil.createResponse(MessageConstants.DATA_SAVED, teacherFourthForm, true);
		    }
		    }
 
		    	catch (Exception e) {
		       
		        e.printStackTrace();
		        response = ResponseUtil.createResponse(MessageConstants.DATA_NOT_SAVED, null, false);
		    }

		    return new ResponseEntity<>(response, HttpStatus.OK);
		}


  // Getting Methods are here
	@Operation(summary = "Getting  New Teacher Details  ", description = "Returns newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Teacher was not found") })
  @GetMapping("/getfirstform")
  public ResponseEntity<ResponseDto> getFirstForm(@RequestParam int id)
  {
	  ResponseDto response=null;
	  try {
		  TeacherFirstFormDto firstFormDto=teacherService.firstFormMethod(id);
		  if(firstFormDto !=null)
		  {
			  response=ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, firstFormDto, true);
		  }
	  }
	  catch(Exception e)
	  {
		  response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
	  }
	  return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
  }
	@Operation(summary = "Getting  New Teacher Details  ", description = "Returns newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Teacher was not found") })
  @GetMapping("/getsecondform")
  public ResponseEntity<ResponseDto> getSecondForm(@RequestParam("id") int id)
  {
	  ResponseDto response=null;
	  try {
		  TeacherSecondFormDto secondFormDto=teacherService.secondFormMethod(id);
		  if(secondFormDto !=null)
		  {
			  response=ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, secondFormDto, true);
		  }
	  }
	  catch(Exception e)
	  {
		  response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
	  }
	  return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
  }
	@Operation(summary = "Getting  New Teacher Details  ", description = "Returns newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Teacher was not found") })
  @GetMapping("/getthirdform")
  public ResponseEntity<ResponseDto> getThirdForm(@RequestParam("id") int id)
  {
	  ResponseDto response=null;
	  try {
		  TeacherThirdFormDto thirdFormDto=teacherService.thirdFormMethod(id);
		  if(thirdFormDto != null)
		  {
			  response=ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, thirdFormDto, true);
		  }
	  }
	  catch(Exception e)
	  {
		  response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
	  }
	  return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
  }
	@Operation(summary = "Getting  New Teacher Details  ", description = "Returns newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Teacher was not found") })
  @GetMapping("getfourthform")
  public ResponseEntity<ResponseDto> getFourthForm(@RequestParam("id") int id)
  {
	   ResponseDto response=null;
	   try {
		  List<TeacherFourthFormDto> fileInput=teacherService.fourthFormMethod(id);
		   if(fileInput != null)
		   {
			   response=ResponseUtil.createResponse(MessageConstants.DATA_FOUND, fileInput, true);
		   }
	   }
	   catch(Exception e)
	   {
		   response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
	   }
	   
		   
	   
	   
	  return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
  }
  
	@Operation(summary = "Getting  Teacher List  ", description = "Returns newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Teacher was not found") })
  @GetMapping("getteacherlist")
  public ResponseEntity<ResponseDto> getTeacherList()
  {
	   ResponseDto response=null;
	   try {
		List<TeacherListDto> teacherListDto=teacherService.teacherListDto();
		   if(teacherListDto != null)
		   {
			   response=ResponseUtil.createResponse(MessageConstants.DATA_FOUND, teacherListDto, true);
		   }
	   }
	   catch(Exception e)
	   {
		   response=ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
	   }
	   
		   
	   
	   
	  return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
  }
}
