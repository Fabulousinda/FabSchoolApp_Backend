package com.fabiit.fabschoolapp.master.standardMaster.controller;

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

import com.fabiit.fabschoolapp.master.standardMaster.dto.StandardDto;
import com.fabiit.fabschoolapp.master.standardMaster.service.StandardService;
import com.fabiit.fabschoolapp.utils.MessageConstants;
import com.fabiit.fabschoolapp.utils.ResponseDto;
import com.fabiit.fabschoolapp.utils.ResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class StandardController {
	@Autowired
	private StandardService standardService;

	@Operation(summary = "Save New Standard", description = "Returns newly saved Standard.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@PostMapping("/standard")
	public ResponseEntity<ResponseDto> saveStandard(@RequestParam String stdName) {
		ResponseDto response = null;
		try {
			StandardDto std = standardService.saveStandard(stdName);
			if (std != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, std, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch All Standard", description = "Returns All Standards.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@GetMapping("/master/standard")
	public ResponseEntity<ResponseDto> getAllStandardMaster() {
		ResponseDto response = null;
		try {
			List<StandardDto> allStandard = standardService.getAllStandard();
			if (allStandard != null && !allStandard.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, allStandard, true);
			} else if (allStandard.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, allStandard, true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch All Standard", description = "Returns All Standards.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@GetMapping("/standard")
	public ResponseEntity<ResponseDto> getAllStandard() {
		ResponseDto response = null;
		try {
			List<StandardDto> allStandard = standardService.getAllActiveStandard();
			if (allStandard != null && !allStandard.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, allStandard, true);
			} else if (allStandard.isEmpty()) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, allStandard, true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch Standard by Standard Id", description = "Returns Standard as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@GetMapping("/standard/{id}")
	public ResponseEntity<ResponseDto> getStandardById(@PathVariable("id") int standardId) {
		ResponseDto response = null;
		try {
			StandardDto standard = standardService.getStandardById(standardId);
			if (standard != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, standard, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "Update Standard by id.", description = "Returns updated Standard Object.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@PutMapping("/standard")
	public ResponseEntity<ResponseDto> updateStandard(@RequestBody StandardDto standard) {
		StandardDto updateStandard = null;
		ResponseDto response = null;
		try {
			updateStandard = standardService.UpdateSatandard(standard);
			if (updateStandard != null) {
				response = ResponseUtil.createResponse(MessageConstants.STANDARD_UPDATED, updateStandard, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.STANDARD_NOT_UPDATED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Update State of Standard by Id", description = "Returns Standard as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@PutMapping("/standard/{id}")
	public ResponseEntity<ResponseDto> updateStandardState(@PathVariable("id") int standardId) {
		ResponseDto response = null;
		try {
			StandardDto updatedState = standardService.updateState(standardId);
			if (updatedState != null) {
				response = ResponseUtil.createResponse(MessageConstants.STANDARD_STATE_UPDATED, updatedState, true);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.STANDARD_STATE_NOT_UPDATED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Delete Standard by Id", description = "Returns Standard as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@DeleteMapping("/standard/{id}")
	public ResponseEntity<ResponseDto> deleteStandard(@PathVariable("id") int standardId) {

		ResponseDto response = null;
		try {
			StandardDto deleted = standardService.deleteStandard(standardId);
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
