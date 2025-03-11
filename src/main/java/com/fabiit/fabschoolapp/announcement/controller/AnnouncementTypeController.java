package com.fabiit.fabschoolapp.announcement.controller;

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

import com.fabiit.fabschoolapp.announcement.dto.AnnouncementTypeDto;
import com.fabiit.fabschoolapp.announcement.service.AnnouncementTypeService;
import com.fabiit.fabschoolapp.utils.MessageConstants;
import com.fabiit.fabschoolapp.utils.ResponseDto;
import com.fabiit.fabschoolapp.utils.ResponseUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class AnnouncementTypeController {

	@Autowired
	private AnnouncementTypeService announcementTypeService;

	@Operation(summary = "Save New Announcement type", description = "Returns newly saved Announcement type.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@PostMapping("/announcetype")
	public ResponseEntity<ResponseDto> saveAnnouncementType(@RequestParam String announceType) {
		ResponseDto response = null;
		try {
			AnnouncementTypeDto announce = announcementTypeService.saveAnnounceType(announceType);
			if (announce != null) {
				response = ResponseUtil.createResponse(MessageConstants.ANNOUNCE_TYPE_SAVED, announce, true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.ANNOUNCE_TYPE_NOT_SAVED, announce, false);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch All Announcement type", description = "Returns All Announcement types.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@GetMapping("/announcetype")
	public ResponseEntity<ResponseDto> getAllAnnouceType() {
		ResponseDto response = null;
		try {
			List<AnnouncementTypeDto> allAnnounceType = announcementTypeService.getAllAnnounceType();
			if (allAnnounceType != null) {
				response = ResponseUtil.createResponse(MessageConstants.ANNOUNCE_TYPE_LIST_FOUND, allAnnounceType,
						true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.ANNOUNCE_TYPE_LIST_NULL, allAnnounceType,
						false);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Fetch Announcement type by announcement Id", description = "Returns Announcement type as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@GetMapping("/announcetype/{id}")
	public ResponseEntity<ResponseDto> getAnnounceTypeById(@PathVariable("id") int announceId) {
		ResponseDto response = ResponseUtil.createEmptyResponse();
		try {
			AnnouncementTypeDto allAnnounceType = announcementTypeService.getAnnounceById(announceId);
			if (allAnnounceType != null) {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_FOUND, allAnnounceType, true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.RECORD_NOT_FOUND, allAnnounceType, false);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createExceptionResponse(MessageConstants.EXCEPTION_OCCURED, e.getLocalizedMessage(),
					false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Update Announcement type by id.", description = "Returns updated Announcement type Object.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@PutMapping("/announcetype")
	public ResponseEntity<ResponseDto> updateAnnounceType(@RequestBody AnnouncementTypeDto announceType) {
		AnnouncementTypeDto updateAnnounceType = null;
		ResponseDto response = null;
		try {
			updateAnnounceType = announcementTypeService.UpdateAnnounceType(announceType);
			if (updateAnnounceType != null) {
				response = ResponseUtil.createResponse(MessageConstants.ANNOUNCE_TYPE_UPDATED, updateAnnounceType,
						true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.ANNOUNCE_TYPE_NOT_UPDATED, updateAnnounceType,
						false);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Update State of Announcement type by Id", description = "Returns Announcement type as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@PutMapping("/announcetype/{id}")
	public ResponseEntity<ResponseDto> updateAnnounceTypeState(@PathVariable("id") int announceId) {
		ResponseDto response = null;
		try {
			AnnouncementTypeDto updatedState = announcementTypeService.updateState(announceId);
			if (updatedState != null) {
				response = ResponseUtil.createResponse(MessageConstants.ANNOUNCE_STATE_UPDATED, updatedState, true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.ANNOUNCE_STATE_NOT_UPDATED, updatedState,
						false);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "Delete Announcement type by Id", description = "Returns Announcement type as per Id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully updated"),
			@ApiResponse(responseCode = "404", description = "Not found - The Announcement was not found") })
	@DeleteMapping("/announcetype/{id}")
	public ResponseEntity<ResponseDto> deleteAnnounceType(@PathVariable("id") int announceId) {
		ResponseDto response = null;
		try {
			AnnouncementTypeDto deleted = announcementTypeService.deleteAnnouncementType(announceId);
			if (deleted != null) {
				response = ResponseUtil.createResponse(MessageConstants.ANNOUNCE_DELETED, deleted, true);
			} else {
				response = ResponseUtil.createResponse(MessageConstants.ANNOUNCE_NOT_DELETED, deleted, false);
			}

		} catch (Exception e) {
			// TODO: handle exception
			response = ResponseUtil.createResponse(MessageConstants.EXCEPTION_OCCURED, e.getMessage(), false);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
