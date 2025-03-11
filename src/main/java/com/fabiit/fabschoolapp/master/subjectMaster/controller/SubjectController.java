package com.fabiit.fabschoolapp.master.subjectMaster.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabiit.fabschoolapp.master.subjectMaster.dto.SubjectDto;
import com.fabiit.fabschoolapp.master.subjectMaster.service.SubjectService;
import com.fabiit.fabschoolapp.utils.MessageConstants;
import com.fabiit.fabschoolapp.utils.ResponseDto;
import com.fabiit.fabschoolapp.utils.ResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class SubjectController {
	@Autowired
	private SubjectService subjectService;

	@Operation(summary = "Save New Subject", description = "Returns newly saved Subject.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Subject was not found") })
	@PostMapping("/subject")
	public ResponseEntity<ResponseDto> saveSubject(@RequestParam String subName) {
		ResponseDto response = null;
		try {
			SubjectDto std = subjectService.saveSubject(subName);
			if (std != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, std, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch All Subject for Master", description = "Returns All Standards not DELETED.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Subject was not found") })
	@GetMapping("/master/subject")
	public ResponseEntity<ResponseDto> getAllSubject() {
		ResponseDto response = null;
		try {
			List<SubjectDto> allSubject = subjectService.getAllSubject();
			if (allSubject != null && !allSubject.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, allSubject, true);
			} else if (allSubject.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, allSubject, true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch All Subject", description = "Returns All Active Standards.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Subject was not found") })
	@GetMapping("/subject")
	public ResponseEntity<ResponseDto> getAllStandard() {
		ResponseDto response = null;
		try {
			List<SubjectDto> allSubject = subjectService.getAllActiveSubject();
			if (allSubject != null && !allSubject.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, allSubject, true);
			} else if (allSubject.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, allSubject, true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch Subject by Subject Id", description = "Returns Subject as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Subject was not found") })
	@GetMapping("/subject/{id}")
	public ResponseEntity<ResponseDto> getStandardById(@PathVariable("id") int subjectId) {
		ResponseDto response = null;
		try {
			SubjectDto subject = subjectService.getSubjectById(subjectId);
			if (subject != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, subject, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Update Subject by id.", description = "Returns updated Subject Object.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Subject was not found") })
	@PutMapping("/subject")
	public ResponseEntity<ResponseDto> updateStandard(@RequestBody SubjectDto subject) {
		SubjectDto updateStandard = null;
		ResponseDto response = null;
		try {
			updateStandard = subjectService.UpdateSubject(subject);
			if (updateStandard != null) {
				response = ResponseUtil.createResponse(MessageConstants.STANDARD_UPDATED, updateStandard, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.STANDARD_NOT_UPDATED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Update State of Subject by Id", description = "Returns Subject as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Subject was not found") })
	@PutMapping("/subject/{id}")
	public ResponseEntity<ResponseDto> updateStandardState(@PathVariable("id") int subjectId) {
		ResponseDto response = null;
		try {
			SubjectDto updatedState = subjectService.updateState(subjectId);
			if (updatedState != null) {
				response = ResponseUtil.createResponse(MessageConstants.STANDARD_STATE_UPDATED, updatedState, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.STANDARD_STATE_NOT_UPDATED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Delete Subject by Id", description = "Returns Subject as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Subject was not found") })
	@DeleteMapping("/subject/{id}")
	public ResponseEntity<ResponseDto> deleteStandard(@PathVariable("id") int subjectId) {

		ResponseDto response = null;
		try {
			SubjectDto deleted = subjectService.deleteSubject(subjectId);
			if (deleted != null) {
				response = ResponseUtil.createResponse(MessageConstants.STANDARD_DELETED, deleted, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.STANDARD_NOT_DELETED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
