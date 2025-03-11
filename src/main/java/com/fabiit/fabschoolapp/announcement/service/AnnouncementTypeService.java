package com.fabiit.fabschoolapp.announcement.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiit.fabschoolapp.announcement.dto.AnnouncementTypeDto;
import com.fabiit.fabschoolapp.announcement.entity.AnnouncementTypeEntity;
import com.fabiit.fabschoolapp.announcement.repo.AnnouncementTypeRepo;
import com.fabiit.fabschoolapp.utils.DatetimeUtils;
import com.fabiit.fabschoolapp.utils.FabEnum;
import com.fabiit.fabschoolapp.utils.FabEnum.state;

@Service
public class AnnouncementTypeService {

	@Autowired
	private AnnouncementTypeRepo announcementTypeRepo;

	public List<AnnouncementTypeDto> getAllAnnounceType() {

		List<AnnouncementTypeEntity> allAnnounceType = announcementTypeRepo.findAllByStateNot(FabEnum.state.DELETED);
		List<AnnouncementTypeDto> list = allAnnounceType.stream().map(announce -> new AnnouncementTypeDto(announce))
				.toList();
		return list;
	}

	public AnnouncementTypeDto saveAnnounceType(String announceType) {
		AnnouncementTypeEntity announce = new AnnouncementTypeEntity();
		announce.setAnnouncementType(announceType);
		announce.setDtCreated(DatetimeUtils.getCurrentInstant());
		announce.setDtLastUpdated(DatetimeUtils.getCurrentInstant());
		announce.setUserCreated(-1);
		announce.setUserLastUpdated(-1);
		announce.setState(state.UNDER_CONFIG);
		AnnouncementTypeEntity announceSaved = announcementTypeRepo.save(announce);
		AnnouncementTypeDto announcementTypeDto = new AnnouncementTypeDto(announceSaved);
		return announcementTypeDto;
	}

	public AnnouncementTypeDto UpdateAnnounceType(AnnouncementTypeDto announceType) throws Exception {
		AnnouncementTypeDto announcementTypeDto = null;

		if (!(announceType.getAnnounceTypeId() > 0)) {
			throw new Exception("announce id cannot be null.");
		}

		int announceTypeId = announceType.getAnnounceTypeId();
		String announceTypeName = announceType.getAnnounceType();
		Instant currentInstant = DatetimeUtils.getCurrentInstant();
		int userLastUpdated = -1;
		int announceSaved = announcementTypeRepo.updateAnnounce(announceTypeId, announceTypeName, userLastUpdated,
				currentInstant);
		if (announceSaved > 0) {
			announcementTypeDto = new AnnouncementTypeDto();
			announcementTypeDto.setAnnounceType(announceTypeName);
			announcementTypeDto.setAnnounceTypeId(announceTypeId);
		}
		return announcementTypeDto;
	}

	public AnnouncementTypeDto getAnnounceById(int announceId) throws Exception {
		// TODO Auto-generated method stub
		AnnouncementTypeEntity announce = announcementTypeRepo.findByAnnouncementTypeId(announceId);
		if (announce == null) {
			throw new Exception("Invalid : Announcement Type Id " + announceId);
		}
		AnnouncementTypeDto announceDto = new AnnouncementTypeDto(announce);

		return announceDto;
	}

	public AnnouncementTypeDto updateState(int announceId) {
		AnnouncementTypeEntity updatedData = null;
		AnnouncementTypeDto announcementTypeDto = null;
		try {
			AnnouncementTypeEntity announce = announcementTypeRepo.findByAnnouncementTypeId(announceId);
			if (announce == null) {
				throw new Exception("Invalid : Announcement Type Id " + announceId);
			}
			if (announce.getState().equals(FabEnum.state.UNDER_CONFIG)) {
				announce.setState(state.ACTIVE);
				updatedData = announcementTypeRepo.save(announce);
			} else if (announce.getState().equals(FabEnum.state.ACTIVE)) {
				announce.setState(state.DEACTIVE);
				updatedData = announcementTypeRepo.save(announce);
			} else if (announce.getState().equals(FabEnum.state.DEACTIVE)) {
				announce.setState(state.ACTIVE);
				updatedData = announcementTypeRepo.save(announce);
			} else {
				// log your data invalid State and return null
			}
			announcementTypeDto = new AnnouncementTypeDto(updatedData);
		} catch (Exception ex) {

		}
		return announcementTypeDto;
	}

	public AnnouncementTypeDto deleteAnnouncementType(int announceId) {
		AnnouncementTypeEntity updatedData = null;
		AnnouncementTypeDto announcementTypeDto = null;
		try {
			AnnouncementTypeEntity announce = announcementTypeRepo.findByAnnouncementTypeId(announceId);
			if (announce == null) {
				throw new Exception("Invalid : Announcement Type Id " + announceId);
			}
			announce.setState(state.DELETED);
			updatedData = announcementTypeRepo.save(announce);

			announcementTypeDto = new AnnouncementTypeDto(updatedData);
		} catch (Exception ex) {

		}
		return announcementTypeDto;
	}
}
