package com.fabiit.fabschoolapp.assign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.fabiit.fabschoolapp.assign.Entity.AssignStudentEntity;
import com.fabiit.fabschoolapp.assign.Repo.AssignStudentRepo;
import com.fabiit.fabschoolapp.assign.Service.AssignService;
import com.fabiit.fabschoolapp.assign.dto.AssignStudentDto;
import com.fabiit.fabschoolapp.assign.dto.AssignTeacherDto;



import com.fabiit.fabschoolapp.assign.dto.StdDivListDto;
import com.fabiit.fabschoolapp.assign.dto.StdDivSubListDto;
import com.fabiit.fabschoolapp.assign.dto.StdSubListDto;
import com.fabiit.fabschoolapp.assign.dto.StudentListAssignDto;
import com.fabiit.fabschoolapp.assign.dto.TeacherListAssignDto;
import com.fabiit.fabschoolapp.master.standardMaster.service.StandardService;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentListDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherListDto;
import com.fabiit.fabschoolapp.utils.MessageConstants;
import com.fabiit.fabschoolapp.utils.ResponseDto;
import com.fabiit.fabschoolapp.utils.ResponseUtil;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class AssignController {

	@Autowired
	private StandardService standardService;

	@Autowired
	private AssignService assignService;

	// Division List By standardId
	@Operation(summary = "fetch Divisions list by Standard Id", description = "Returns Division as per StandardId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Divisions not found") })
	@GetMapping("/divbystdid/{id}")
	public ResponseEntity<ResponseDto> getDivisionByStdId(@PathVariable("id") int standardId) {

		ResponseDto response = null;
		try {
			StdDivListDto divList = standardService.getDivisionsByStdId(standardId);
			if (divList != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, divList, true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, divList, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "fetch Divisions grouped by Standards", description = "Returns Divisions as per StandardId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Divisions not found") })
	@GetMapping("/divbystd")
	public ResponseEntity<ResponseDto> getDivisionByStds() {

		ResponseDto response = null;
		try {
			List<StdDivListDto> divList = standardService.getDivisionsforAllStds();
			if (divList != null && !divList.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, divList, true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, divList, true);
			}

		} catch (Exception e) {
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "fetch Divisions grouped by Standards", description = "Returns Divisions as per StandardId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Divisions not found") })
	@GetMapping("divsubbystd")
	public ResponseEntity<ResponseDto> getDivSubByStd() {
		ResponseDto response = null;
		try {

			List<StdDivSubListDto> stdDivSubList = standardService.getDivSubByStd();
			if (stdDivSubList != null && !stdDivSubList.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, stdDivSubList, true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, stdDivSubList, true);
			}

		} catch (Exception e) {
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);

		}

		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}

	@Operation(summary = "Update Divisions list by Standard Id", description = "Returns Division as per StandardId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Divisions not found") })
	@PutMapping("/divbystdid")
	public ResponseEntity<ResponseDto> setDivisionByStdId(@RequestParam("stdId") int standardId,
			@RequestParam List<Integer> division) {

		ResponseDto response = null;
		try {
			if (division.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.DIVISION_EMPTY, null, true);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			List<StdDivListDto> divList = standardService.setDivisionsByStdId(standardId, division);

			if (divList != null && !divList.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, divList, true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, divList, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	// Subject list by StandardId

	@Operation(summary = "fetch Divisions list by Standard Id", description = "Returns Division as per StandardId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Divisions not found") })
	@GetMapping("/subbystdid/{id}")
	public ResponseEntity<ResponseDto> getSubjectByStdId(@PathVariable("id") int standardId) {

		ResponseDto response = null;
		try {
			StdSubListDto subList = standardService.getSubjectByStdId(standardId);
			if (subList != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, subList, true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, subList, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "fetch Divisions grouped by Standards", description = "Returns Divisions as per StandardId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Divisions not found") })
	@GetMapping("/subbystd")
	public ResponseEntity<ResponseDto> getSubjectByStds() {

		ResponseDto response = null;
		try {
			List<StdSubListDto> divList = standardService.getSubjectforAllStds();
			if (divList != null && !divList.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, divList, true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, divList, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Update Divisions list by Standard Id", description = "Returns Division as per StandardId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Divisions not found") })
	@PutMapping("/subbystdid")
	public ResponseEntity<ResponseDto> setSubjectByStdId(@RequestParam("stdId") int standardId,
			@RequestParam List<Integer> subjectIds) {

		ResponseDto response = null;
		try {
			if (subjectIds.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.DIVISION_EMPTY, null, true);
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			List<StdSubListDto> divList = standardService.setSubjectByStdId(standardId, subjectIds);

			if (divList != null && !divList.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, divList, true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, divList, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Assign Student Std div ", description = "Assign  newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@PostMapping("/stddivbystudentid")
	public ResponseEntity<ResponseDto> saveStdDivbyStudentId(@RequestParam int studentId, @RequestParam int std,
			@RequestParam int div)

	{
		ResponseDto response = null;

		try {

			AssignStudentDto assignStudentDto = assignService.saveStdDivByStudentId(studentId, std, div);
			if (assignStudentDto != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, assignStudentDto, true);
			}

		} catch (Exception e) {
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}

		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}

	@Operation(summary = "Assign Teacher Std div sub ", description = "Assign  newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@PostMapping("/stddivsubbyteacherid")
	public ResponseEntity<ResponseDto> saveStdDivSubbyTeacherId
	(@RequestParam int assignId,
	@RequestParam int teacherId,
	@RequestParam int std,
	@RequestParam int div,
	@RequestParam int sub,
	@RequestParam boolean classTeacher)

	{
		ResponseDto response = null;

		try {

			AssignTeacherDto assignTeacherDto = assignService.saveStdDivSubByTeacherId(assignId,teacherId, std, div, sub,classTeacher);
			if (assignTeacherDto != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, assignTeacherDto, true);
			}

		} catch (Exception e) {
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}

		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}

	
	
	@Operation(summary = "Assign Multiple Student Std div ", description = "Assign  newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@PostMapping("/multiplestddivbystudentid")
	public ResponseEntity<ResponseDto> saveMultipleStdDivbyStudentId(@RequestParam List<Integer> studentId,
			@RequestParam int std, @RequestParam int div)
{
		ResponseDto response = null;

		try {

			List<AssignStudentDto> assignStudentDto = assignService.saveMultipleStdDivByStudentId(studentId,std,div);
					
			if (assignStudentDto != null) {
				response = ResponseUtil.createResponse(MessageConstants.DATA_SAVED, assignStudentDto, true);
			}

		} catch (Exception e) {
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}

		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
//	@Operation(summary = "Get Assign Student ", description = "Returns newly saved Student.")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
//			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
//	@GetMapping("getstddivbystudentId")
//	public ResponseEntity<ResponseDto> getStdDivByStudentId() {
//		ResponseDto response = null;
//		try {
//			// Fetch data for both DTOs
//			List<StudentListDto> studentDto = assignService.getListByStudentId();
//			List<AssignStudentDto> assignStudent = assignService.getStdDivByStudentId();
//
//			// Combine them into a single object
//			CombinedStudentDto combinedDto = new CombinedStudentDto();
//			combinedDto.setStudentListDto(studentDto);
//			combinedDto.setAssignStudent(assignStudent);
//
//			// Set the combined DTO in the response
//			if (studentDto != null || assignStudent != null) {
//				response = ResponseUtil.createResponse(MessageConstants.DATA_FOUND, combinedDto, true);
//			}
//		} catch (Exception e) {
//			response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, e, false);
//		}
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
	
//	@Operation(summary = "Get Assign Teacher ", description = "Returns newly saved Teacher.")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
//			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
//	@GetMapping("getstddivsubbyteacherid")
//	public ResponseEntity<ResponseDto> getStdDivSubByTeacherId() {
//		ResponseDto response = null;
//		try {
//			// Fetch data for both DTOs
//			List<CombinedTeachersDto> objectTeacherDto=assignService.getDetailsOfTeacher();
//			List<TeacherListDto> teacherDto = assignService.getListByTeacherId();
//			List<AssignTeacherDto> assignTeacher = assignService.getStdDivSubByTeacherId();
//
//			// Combine them into a single object
//			CombinedTeacherDto combinedDto = new CombinedTeacherDto();
//			combinedDto.setTeacherData(teacherDto);
//			combinedDto.setAssignTeacher(assignTeacher);
//
////			// Set the combined DTO in the response
////			if (teacherDto != null || assignTeacher != null)
////			{
////				response = ResponseUtil.createResponse(MessageConstants.DATA_FOUND, combinedDto, true);
////			}
//			if(objectTeacherDto != null)
//			{
//				response=ResponseUtil.createResponse(MessageConstants.DATA_FOUND, objectTeacherDto,true);
//			}
//		} catch (Exception e) {
//			response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, e, false);
//		}
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
	
//	@Operation(summary = "Get Student By Std Div ", description = "Returns newly saved Student.")
//	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
//			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
//	@GetMapping("getstudentbystddiv")
//	public ResponseEntity<ResponseDto> getDetailsByStdDiv(@RequestParam int std, @RequestParam int div)
//	{
//		ResponseDto response=null;
//		
//		try {
//			List<CombinedStudentDto> combinedStudent= assignService.getDetailsByStdDiv(std, div);
//			if(combinedStudent != null)
//			{
//				response= ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, combinedStudent, true);
//			}
//			
//		}
//		catch(Exception e)
//		{
//			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
//		}
//		
//		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
//	}
	
//	@GetMapping("getstudentbysearching")
//	public ResponseEntity<ResponseDto> getStudentsBySearching(@RequestParam String search)
//	{
//		ResponseDto response=null;
//		
//		try {
//			List<CombinedStudentDto> objectStudent= assignService.getStudentsBySearhing(search);
//			if(objectStudent != null)
//			{
//				response= ResponseUtil.createResponse(MessageConstants.DATA_FOUND, objectStudent, true);
//			}
//			
//		}
//		catch(Exception e)
//		{
//			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
//		}
//		
//		return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
//	}
	@Operation(summary = "Get TeacherList Assign ", description = "Returns newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Teacher was not found") })
	@GetMapping("getteacherlistassign")
	public ResponseEntity<ResponseDto> getTeacherAssign()
	{
		ResponseDto response=null;
		
		try {
			List<TeacherListDto> teacherListAssign= assignService.getTeacherListAssign();
			if(teacherListAssign != null)
			{
				response= ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, teacherListAssign, true);
			}
			
		}
		catch(Exception e)
		{
			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	@Operation(summary = "Get StudentList Assign ", description = "Returns newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@GetMapping("getstudentlistassign")
	public ResponseEntity<ResponseDto> getstudentassign()
	{
		ResponseDto response=null;
		
		try {
			List<StudentListAssignDto> studentListAssign= assignService.getStudentListAssign();
			if(studentListAssign != null)
			{
				response= ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, studentListAssign, true);
			}
			
		}
		catch(Exception e)
		{
			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	@Operation(summary = "Get StudentList By Std Div ", description = "Returns newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@GetMapping("getstudentbystddiv")
	public ResponseEntity<ResponseDto> getStudentByStdDiv(@RequestParam int std, @RequestParam int div)
	{
		ResponseDto response=null;
		
		try {
			List<StudentListAssignDto> studentListAssign= assignService.getStudentsByStdDiv(std, div);
			if(studentListAssign != null)
			{
				response= ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, studentListAssign, true);
			}
			
		}
		catch(Exception e)
		{
			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	@Operation(summary = "Get StudentList By Std Div and search ", description = "Returns newly saved Student.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Student was not found") })
	@GetMapping("findstudents")
	public ResponseEntity<ResponseDto> findStudents(@RequestParam(required=false) int std, @RequestParam(required=false) int div, @RequestParam String search)
	{
		ResponseDto response=null;
		
		try {
			List<StudentListAssignDto> studentListAssign= assignService.findStudents(std, div,search);
			if(studentListAssign != null)
			{
				response= ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, studentListAssign, true);
			}
			
		}
		catch(Exception e)
		{
			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	@Operation(summary = "Get Teacher Assign By Id  ", description = "Returns newly saved Teacher.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully fetched"),
			@ApiResponse(responseCode = "404", description = "Not found - The Teacher was not found") })
	@GetMapping("getassignteacherbyid")
	public ResponseEntity<ResponseDto> getTeachersAssignById(@RequestParam int  teacherId)
	{
		ResponseDto response=null;
		
		try {
			List<TeacherListAssignDto> teacherListAssign= assignService.getAssignTeachersByid(teacherId);
			if(teacherListAssign != null)
			{
				response= ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, teacherListAssign, true);
			}
			
		}
		catch(Exception e)
		{
			response= ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e, false);
		}
		
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
}
