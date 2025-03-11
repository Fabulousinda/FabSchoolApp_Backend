package com.fabiit.fabschoolapp.master.subjectMaster.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiit.fabschoolapp.master.subjectMaster.dto.SubjectDto;
import com.fabiit.fabschoolapp.master.subjectMaster.entity.SubjectEntity;
import com.fabiit.fabschoolapp.master.subjectMaster.repo.SubjectRepo;
import com.fabiit.fabschoolapp.utils.DatetimeUtils;
import com.fabiit.fabschoolapp.utils.FabEnum;
import com.fabiit.fabschoolapp.utils.FabEnum.state;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepo subjectRepo;

	public SubjectDto saveSubject(String subName) {
		SubjectEntity subject = new SubjectEntity();
		subject.setSubName(subName);		
		subject.setDtCreated(DatetimeUtils.getCurrentInstant());
		subject.setDtLastUpdated(DatetimeUtils.getCurrentInstant());
		subject.setUserCreated(-1);
		subject.setUserLastUpdated(-1);
		subject.setState(state.UNDER_CONFIG);
		SubjectEntity announceSaved = subjectRepo.save(subject);
		SubjectDto StandardDto = new SubjectDto(announceSaved);
		return StandardDto;
	}

	public List<SubjectDto> getAllSubject() {
		List<SubjectEntity> subject = subjectRepo.findAllByStateNot(FabEnum.state.DELETED);
		List<SubjectDto> list = subject.stream().map(std -> new SubjectDto(std)).toList();
		return list;
	}

	public List<SubjectDto> getAllActiveSubject() {
		List<SubjectEntity> subject = subjectRepo.findAllByState(FabEnum.state.ACTIVE);
		List<SubjectDto> list = subject.stream().map(std -> new SubjectDto(std)).toList();
		return list;
	}

	public SubjectDto getSubjectById(int subjectId) throws Exception {
		SubjectEntity sub = subjectRepo.findBySubjectId(subjectId);
		if (sub == null) {
			throw new Exception("Invalid : Standard Id " + subjectId);
		}
		SubjectDto subDto = new SubjectDto(sub);
		return subDto;
	}

	public SubjectDto UpdateSubject(SubjectDto subject) throws Exception {
		SubjectDto SubjectDto = null;

		if (!(subject.getSubjectId() > 0)) {
			throw new Exception("Subject id cannot be null.");
		}

		int subjectId = subject.getSubjectId();
		String stdName = subject.getSubName();
		Instant currentInstant = DatetimeUtils.getCurrentInstant();
		int userLastUpdated = -1;
		int announceSaved = subjectRepo.updateSubject(subjectId, stdName, userLastUpdated, currentInstant);
		if (announceSaved > 0) {
			SubjectDto = new SubjectDto();
			SubjectDto.setSubName(stdName);
			SubjectDto.setSubjectId(subjectId);			
		}
		return SubjectDto;
	}

	public SubjectDto updateState(int subjectId) {
		SubjectEntity updatedData = null;
		SubjectDto subjectdDto = null;
		try {
			SubjectEntity std = subjectRepo.findBySubjectId(subjectId);
			if (std == null) {
				throw new Exception("Invalid : Subject Id " + subjectId);
			}
			if (std.getState().equals(FabEnum.state.UNDER_CONFIG)) {
				std.setState(state.ACTIVE);
				updatedData = subjectRepo.save(std);
			} else if (std.getState().equals(FabEnum.state.ACTIVE)) {
				std.setState(state.DEACTIVE);
				updatedData = subjectRepo.save(std);
			} else if (std.getState().equals(FabEnum.state.DEACTIVE)) {
				std.setState(state.ACTIVE);
				updatedData = subjectRepo.save(std);
			} else {
				// log your data invalid State and return null
			}
			subjectdDto = new SubjectDto(updatedData);
		} catch (Exception ex) {

		}
		return subjectdDto;
	}

	public SubjectDto deleteSubject(int subjectId) throws Exception {
		SubjectEntity updatedData = null;
		SubjectDto SubjectDto = null;
		try {
			SubjectEntity standard = subjectRepo.findBySubjectId(subjectId);

			standard.setState(state.DELETED);
			updatedData = subjectRepo.save(standard);

			SubjectDto = new SubjectDto(updatedData);
		} catch (Exception ex) {
			throw new Exception("Invalid : Subject Id " + subjectId);
		}
		return SubjectDto;
	}

	public List<SubjectEntity> fetchAllBySubjectIds(List<Integer> division) {
		List<SubjectEntity> allById = subjectRepo.findAllBySubjectIdInAndState(division,state.ACTIVE);
		return allById;
	}

}
