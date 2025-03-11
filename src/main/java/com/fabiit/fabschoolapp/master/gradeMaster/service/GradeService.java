package com.fabiit.fabschoolapp.master.gradeMaster.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiit.fabschoolapp.master.gradeMaster.dto.GradeDto;
import com.fabiit.fabschoolapp.master.gradeMaster.entity.GradeEntity;
import com.fabiit.fabschoolapp.master.gradeMaster.repo.GradeRepo;
import com.fabiit.fabschoolapp.master.standardMaster.entity.StandardEntity;
import com.fabiit.fabschoolapp.utils.DatetimeUtils;
import com.fabiit.fabschoolapp.utils.FabEnum;
import com.fabiit.fabschoolapp.utils.FabEnum.state;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GradeService {
	@Autowired
	private GradeRepo gradeRepo;

	public List<GradeDto> getAllGrade() {

		List<GradeEntity> grade = gradeRepo.findAllByStateNot(FabEnum.state.DELETED);
		List<GradeDto> list = grade.stream().map(std -> new GradeDto(std)).toList();
		return list;
	}

	public List<GradeDto> getAllActiveGrade()
	{
		
		List<GradeEntity> grade=gradeRepo.findAllByState(FabEnum.state.ACTIVE);
		List<GradeDto> list=grade.stream().map(active-> new GradeDto(active)).toList();
		return list;
	}
	
	
	public GradeDto saveGrade(String gradeName) {
		GradeEntity grade = new GradeEntity();
		grade.setGradeName(gradeName);
		grade.setDtCreated(DatetimeUtils.getCurrentInstant());
		grade.setDtLastUpdated(DatetimeUtils.getCurrentInstant());
		grade.setUserCreated(-1);
		grade.setUserLastUpdated(-1);
		grade.setState(state.UNDER_CONFIG);
		GradeEntity gradeSaved = gradeRepo.save(grade);
		GradeDto GradeDto = new GradeDto(gradeSaved);
		return GradeDto;
	}

	public GradeDto UpdateGrade(GradeDto grade) throws Exception {
		GradeDto GradeDto = null;

		if (!(grade.getGradeId() > 0)) {
			throw new Exception("Grade id cannot be null.");
		}

		int gradeId = grade.getGradeId();
		String gradeName = grade.getGradeName();
		Instant currentInstant = DatetimeUtils.getCurrentInstant();
		int userLastUpdated = -1;
		int gradeSaved = gradeRepo.updateGrade(gradeId, gradeName, userLastUpdated, currentInstant);
		if (gradeSaved > 0) {
			GradeDto = new GradeDto();
			GradeDto.setGradeName(gradeName);
			GradeDto.setGradeId(gradeId);
		}
		return GradeDto;
	}

	public GradeDto getGradeById(int gradeId) throws Exception {
		// TODO Auto-generated method stub
		GradeEntity std = gradeRepo.findByGradeId(gradeId);
		if (std == null) {
			throw new Exception("Invalid : Grade Id " + gradeId);
		}
		GradeDto stdDto = new GradeDto(std);
		return stdDto;
	}

	public GradeDto updateState(int grade) {
		GradeEntity updatedData = null;
		GradeDto GradeDto = null;
		try {
			GradeEntity std = gradeRepo.findByGradeId(grade);
			if (std == null) {
				throw new Exception("Invalid : Grade Id " + grade);
			}
			if (std.getState().equals(FabEnum.state.UNDER_CONFIG)) {
				std.setState(state.ACTIVE);
				updatedData = gradeRepo.save(std);
			} else if (std.getState().equals(FabEnum.state.ACTIVE)) {
				std.setState(state.DEACTIVE);
				updatedData = gradeRepo.save(std);
			} else if (std.getState().equals(FabEnum.state.DEACTIVE)) {
				std.setState(state.ACTIVE);
				updatedData = gradeRepo.save(std);
			} else {
				// log your data invalid State and return null
			}
			GradeDto = new GradeDto(updatedData);
		} catch (Exception ex) {

		}
		return GradeDto;
	}

	public GradeDto deleteGrade(int gradeId) throws Exception {
		
		GradeEntity updatedData = null;
		GradeDto GradeDto = null;
		try {
			GradeEntity grade = gradeRepo.findByGradeId(gradeId);

			grade.setState(state.DELETED);
			updatedData = gradeRepo.save(grade);

			GradeDto = new GradeDto(updatedData);
		} catch (Exception ex) {
			throw new Exception("Invalid : Grade Id " + gradeId);
		}
		return GradeDto;
	}
	
	public String verifyGradeId(int gradeId)
	{
		String gradeName=null;
		try {
			GradeEntity grade=gradeRepo.findByGradeId(gradeId);
			if(grade != null)
			{
				gradeName=grade.getGradeName();
			}
			else throw new  Exception("Grade Id does not Exist "+gradeId);
		}
		catch(Exception e)
		{
			log.error("Error in verifyGradeId() "+e);
		}
		return gradeName;
	}
	
	public int verifyGradeName(String gradeName)
	{
		int gradeId=0;
		try {
			GradeEntity grade=gradeRepo.findByGradeName(gradeName);
			if(grade != null)
			{
				gradeId=grade.getGradeId();
			}
			else throw new  Exception("Grade Name does not Exist "+gradeName);
		}
		catch(Exception e)
		{
			log.error("Error in verifyGradeName() "+e);
		}
		return gradeId;
	}
	
}
