package com.fabiit.fabschoolapp.master.gradeMaster.controller;

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

import com.fabiit.fabschoolapp.master.gradeMaster.dto.GradeDto;
import com.fabiit.fabschoolapp.master.gradeMaster.service.GradeService;
import com.fabiit.fabschoolapp.utils.MessageConstants;
import com.fabiit.fabschoolapp.utils.ResponseDto;
import com.fabiit.fabschoolapp.utils.ResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class GradeController {
	@Autowired
	private GradeService gradeService;

	@Operation(summary = "Save New Grade", description = "Returns newly saved Grade.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Grade was not found") })
	@PostMapping("/grade")
	public ResponseEntity<ResponseDto> saveGrade(@RequestParam String gradeName) {
		ResponseDto response = null;
		try {
			GradeDto grade = gradeService.saveGrade(gradeName);
			if (grade != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, grade, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch All Grade", description = "Returns All Grades.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Grade was not found") })
	@GetMapping("/grade")
	public ResponseEntity<ResponseDto> getAllGrade() {
		ResponseDto response = null;
		try {
			List<GradeDto> allGrade = gradeService.getAllGrade();
			if (allGrade != null && !allGrade.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, allGrade, true);
			} else if (allGrade.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, allGrade, true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@Operation(summary = "Fetch All  Active Grade", description = "Returns All Active Grades.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Grade was not found") })
	@GetMapping("/activegrade")
	public ResponseEntity<ResponseDto> getAllActiveGrade() {
		ResponseDto response = null;
		try {
			List<GradeDto> allGrade = gradeService.getAllActiveGrade();
			if (allGrade != null && !allGrade.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, allGrade, true);
			} else if (allGrade.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, allGrade, true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch Grade by Grade Id", description = "Returns Grade as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Grade was not found") })
	@GetMapping("/grade/{id}")
	public ResponseEntity<ResponseDto> getGradeById(@PathVariable("id") int gradeId) {
		ResponseDto response = null;
		try {
			GradeDto grade = gradeService.getGradeById(gradeId);
			if (grade != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, grade, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Update Grade by id.", description = "Returns updated Grade Object.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Grade was not found") })
	@PutMapping("/grade")
	public ResponseEntity<ResponseDto> updateGrade(@RequestBody GradeDto grade) {
		GradeDto updateGrade = null;
		ResponseDto response = null;
		try {
			updateGrade = gradeService.UpdateGrade(grade);
			if (updateGrade != null) {
				response = ResponseUtil.createResponse(MessageConstants.GRADE_UPDATED, updateGrade, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.GRADE_NOT_UPDATED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Update State of Grade by Id", description = "Returns Grade as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Grade was not found") })
	@PutMapping("/grade/{id}")
	public ResponseEntity<ResponseDto> updateGradeState(@PathVariable("id") int gradeId) {
		ResponseDto response = null;
		try {
			GradeDto updatedState = gradeService.updateState(gradeId);
			if (updatedState != null) {
				response = ResponseUtil.createResponse(MessageConstants.GRADE_STATE_UPDATED, updatedState, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.GRADE_STATE_NOT_UPDATED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Delete Grade by Id", description = "Returns Grade as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Grade was not found") })
	@DeleteMapping("/grade/{id}")
	public ResponseEntity<ResponseDto> deleteGrade(@PathVariable("id") int gradeId) {

		ResponseDto response = null;
		try {
			GradeDto deleted = gradeService.deleteGrade(gradeId);
			if (deleted != null) {
				response = ResponseUtil.createResponse(MessageConstants.GRADE_DELETED, deleted, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.GRADE_NOT_DELETED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
