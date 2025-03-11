package com.fabiit.fabschoolapp.master.divisionMaster.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiit.fabschoolapp.master.divisionMaster.dto.DivisionDto;
import com.fabiit.fabschoolapp.master.divisionMaster.entity.DivisionEntity;
import com.fabiit.fabschoolapp.master.divisionMaster.repo.DivisionRepo;
import com.fabiit.fabschoolapp.master.gradeMaster.entity.GradeEntity;
import com.fabiit.fabschoolapp.utils.DatetimeUtils;
import com.fabiit.fabschoolapp.utils.FabEnum;
import com.fabiit.fabschoolapp.utils.FabEnum.state;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DivisionService {
	@Autowired
	private DivisionRepo divisionRepo;

	public List<DivisionDto> getAllDivision() {

		List<DivisionEntity> division = divisionRepo.findAllByStateNot(FabEnum.state.DELETED);
		List<DivisionDto> list = division.stream().map(std -> new DivisionDto(std)).toList();
		return list;
	}

	public List<DivisionDto> getAllActiveDivision() {

		List<DivisionEntity> division = divisionRepo.findAllByState(FabEnum.state.ACTIVE);
		List<DivisionDto> list = division.stream().map(std -> new DivisionDto(std)).toList();
		return list;
	}

	public DivisionDto saveDivision(String divName) {
		DivisionEntity division = new DivisionEntity();
		division.setDivName(divName);
		division.setDtCreated(DatetimeUtils.getCurrentInstant());
		division.setDtLastUpdated(DatetimeUtils.getCurrentInstant());
		division.setUserCreated(-1);
		division.setUserLastUpdated(-1);
		division.setState(state.UNDER_CONFIG);
		DivisionEntity divisionSaved = divisionRepo.save(division);
		DivisionDto DivisionDto = new DivisionDto(divisionSaved);
		return DivisionDto;
	}

	public DivisionDto UpdateDivision(DivisionDto division) throws Exception {
		DivisionDto DivisionDto = null;

		if (!(division.getDivisionId() > 0)) {
			throw new Exception("Division id cannot be null.");
		}

		int divisionId = division.getDivisionId();
		String divName = division.getDivName();
		Instant currentInstant = DatetimeUtils.getCurrentInstant();
		int userLastUpdated = -1;
		int divisionSaved = divisionRepo.updateDivision(divisionId, divName, userLastUpdated, currentInstant);
		if (divisionSaved > 0) {
			DivisionDto = new DivisionDto();
			DivisionDto.setDivName(divName);
			DivisionDto.setDivisionId(divisionId);
		}
		return DivisionDto;
	}

	public DivisionDto getDivisionById(int divisionId) throws Exception {
		// TODO Auto-generated method stub
		DivisionEntity std = divisionRepo.findByDivisionId(divisionId);
		if (std == null) {
			throw new Exception("Invalid : Division Id " + divisionId);
		}
		DivisionDto stdDto = new DivisionDto(std);
		return stdDto;
	}

	public DivisionDto updateState(int division) {
		DivisionEntity updatedData = null;
		DivisionDto DivisionDto = null;
		try {
			DivisionEntity std = divisionRepo.findByDivisionId(division);
			if (std == null) {
				throw new Exception("Invalid : Division Id " + division);
			}
			if (std.getState().equals(FabEnum.state.UNDER_CONFIG)) {
				std.setState(state.ACTIVE);
				updatedData = divisionRepo.save(std);
			} else if (std.getState().equals(FabEnum.state.ACTIVE)) {
				std.setState(state.DEACTIVE);
				updatedData = divisionRepo.save(std);
			} else if (std.getState().equals(FabEnum.state.DEACTIVE)) {
				std.setState(state.ACTIVE);
				updatedData = divisionRepo.save(std);
			} else {
				// log your data invalid State and return null
			}
			DivisionDto = new DivisionDto(updatedData);
		} catch (Exception ex) {

		}
		return DivisionDto;
	}

	public DivisionDto deleteDivision(int divisionId) throws Exception {
		DivisionEntity updatedData = null;
		DivisionDto DivisionDto = null;
		try {
			DivisionEntity division = divisionRepo.findByDivisionId(divisionId);

			division.setState(state.DELETED);
			updatedData = divisionRepo.save(division);

			DivisionDto = new DivisionDto(updatedData);
		} catch (Exception ex) {
			throw new Exception("Invalid : Division Id " + divisionId);
		}
		return DivisionDto;
	}

	public List<DivisionEntity> fetchAllByDivisionIds(List<Integer> division) {
		// TODO Auto-generated method stub
		List<DivisionEntity> allById = divisionRepo.findAllByDivisionIdInAndState(division,state.ACTIVE);
		return allById;
	}
	
	public String verifyDivisionName(int divisionId)
	{
		String divName=null;
		try {
			DivisionEntity div=divisionRepo.findByDivisionId(divisionId);
			if(div != null)
			{
				divisionId=div.getDivisionId();
			}
			else throw new  Exception("division Id does not Exist "+divisionId);
		}
		catch(Exception e)
		{
			log.error("Error in verifyDivisionName() "+e);
		}
		return divName;
	}
	
	
	

}
