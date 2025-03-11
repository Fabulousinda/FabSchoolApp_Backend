package com.fabiit.fabschoolapp.master.divisionMaster.controller;

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

import com.fabiit.fabschoolapp.master.divisionMaster.dto.DivisionDto;
import com.fabiit.fabschoolapp.master.divisionMaster.service.DivisionService;
import com.fabiit.fabschoolapp.utils.MessageConstants;
import com.fabiit.fabschoolapp.utils.ResponseDto;
import com.fabiit.fabschoolapp.utils.ResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class DivisionController {
	@Autowired
	private DivisionService divisionService;

	@Operation(summary = "Save New Division", description = "Returns newly saved Division.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Division was not found") })
	@PostMapping("/division")
	public ResponseEntity<ResponseDto> saveDivision(@RequestParam String divName) {
		ResponseDto response = null;
		try {
			DivisionDto div = divisionService.saveDivision(divName);
			if (div != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, div, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch All Division", description = "Returns All Divisions.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Division was not found") })
	@GetMapping("/master/division")
	public ResponseEntity<ResponseDto> getAllDivisionMaster() {
		ResponseDto response = null;
		try {
			List<DivisionDto> allDivision = divisionService.getAllDivision();
			if (allDivision != null && !allDivision.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, allDivision, true);
			} else if (allDivision.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, allDivision, true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch All Division", description = "Returns All Divisions.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Division was not found") })
	@GetMapping("/division")
	public ResponseEntity<ResponseDto> getAllDivision() {
		ResponseDto response = null;
		try {
			List<DivisionDto> allDivision = divisionService.getAllActiveDivision();
			if (allDivision != null && !allDivision.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, allDivision, true);
			} else if (allDivision.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, allDivision, true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch Division by Division Id", description = "Returns Division as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Division was not found") })
	@GetMapping("/division/{id}")
	public ResponseEntity<ResponseDto> getDivisionById(@PathVariable("id") int divisionId) {
		ResponseDto response = null;
		try {
			DivisionDto standard = divisionService.getDivisionById(divisionId);
			if (standard != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, standard, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Update Division by id.", description = "Returns updated Division Object.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Division was not found") })
	@PutMapping("/division")
	public ResponseEntity<ResponseDto> updateDivision(@RequestBody DivisionDto standard) {
		DivisionDto updateDivision = null;
		ResponseDto response = null;
		try {
			updateDivision = divisionService.UpdateDivision(standard);
			if (updateDivision != null) {
				response = ResponseUtil.createResponse(MessageConstants.DIVISION_UPDATED, updateDivision, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.DIVISION_NOT_UPDATED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Update State of Division by Id", description = "Returns Division as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Division was not found") })
	@PutMapping("/division/{id}")
	public ResponseEntity<ResponseDto> updateDivisionState(@PathVariable("id") int divisionId) {
		ResponseDto response = null;
		try {
			DivisionDto updatedState = divisionService.updateState(divisionId);
			if (updatedState != null) {
				response = ResponseUtil.createResponse(MessageConstants.DIVISION_STATE_UPDATED, updatedState, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.DIVISION_STATE_NOT_UPDATED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Delete Division by Id", description = "Returns Division as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Division was not found") })
	@DeleteMapping("/division/{id}")
	public ResponseEntity<ResponseDto> deleteDivision(@PathVariable("id") int divisionId) {

		ResponseDto response = null;
		try {
			DivisionDto deleted = divisionService.deleteDivision(divisionId);
			if (deleted != null) {
				response = ResponseUtil.createResponse(MessageConstants.DIVISION_DELETED, deleted, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.DIVISION_NOT_DELETED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
