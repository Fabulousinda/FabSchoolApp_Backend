package com.fabiit.fabschoolapp.master.standardMaster.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiit.fabschoolapp.assign.Entity.AssignStudentEntity;
import com.fabiit.fabschoolapp.assign.Repo.AssignStudentRepo;
import com.fabiit.fabschoolapp.assign.dto.AssignStudentDto;
import com.fabiit.fabschoolapp.assign.dto.StdDivListDto;
import com.fabiit.fabschoolapp.assign.dto.StdDivSubListDto;
import com.fabiit.fabschoolapp.assign.dto.StdSubListDto;
import com.fabiit.fabschoolapp.master.divisionMaster.entity.DivisionEntity;
import com.fabiit.fabschoolapp.master.divisionMaster.repo.DivisionRepo;
import com.fabiit.fabschoolapp.master.divisionMaster.service.DivisionService;
import com.fabiit.fabschoolapp.master.standardMaster.dto.StandardDto;
 
import com.fabiit.fabschoolapp.master.standardMaster.entity.StandardEntity;

import com.fabiit.fabschoolapp.master.standardMaster.repo.StandardRepo;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Repo.StudentMasterRepo;
import com.fabiit.fabschoolapp.master.subjectMaster.entity.SubjectEntity;
import com.fabiit.fabschoolapp.master.subjectMaster.service.SubjectService;
import com.fabiit.fabschoolapp.utils.DatetimeUtils;
import com.fabiit.fabschoolapp.utils.FabEnum;
import com.fabiit.fabschoolapp.utils.FabEnum.state;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StandardService {
	@Autowired
	private StandardRepo standardRepo;
	

	@Autowired
	private DivisionService divisionService;

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private StudentMasterRepo studentMasterRepo;
	
	@Autowired
	private DivisionRepo divisionRepo;


	public List<StandardDto> getAllStandard() {

		List<StandardEntity> standard = standardRepo.findAllByStateNot(FabEnum.state.DELETED);
//		standard.stream().forEach(std->System.out.println(std.getDivision()));
		List<StandardDto> list = standard.stream().map(std -> new StandardDto(std)).toList();
		return list;
	}

	public StandardDto saveStandard(String stdName) {
		StandardEntity standard = new StandardEntity();
		standard.setStdName(stdName);
		standard.setDtCreated(DatetimeUtils.getCurrentInstant());
		standard.setDtLastUpdated(DatetimeUtils.getCurrentInstant());
		standard.setUserCreated(-1);
		standard.setUserLastUpdated(-1);
		standard.setState(state.UNDER_CONFIG);
		StandardEntity announceSaved = standardRepo.save(standard);
		StandardDto StandardDto = new StandardDto(announceSaved);
		return StandardDto;
	}

	public StandardDto UpdateSatandard(StandardDto standard) throws Exception {
		StandardDto StandardDto = null;

		if (!(standard.getStandardId() > 0)) {
			throw new Exception("Standard id cannot be null.");
		}

		int standardId = standard.getStandardId();
		String stdName = standard.getStdName();
		Instant currentInstant = DatetimeUtils.getCurrentInstant();
		int userLastUpdated = -1;
		int announceSaved = standardRepo.updateStandard(standardId, stdName, userLastUpdated, currentInstant);
		if (announceSaved > 0) {
			StandardDto = new StandardDto();
			StandardDto.setStdName(stdName);
			StandardDto.setStandardId(standardId);
		}
		return StandardDto;
	}

	public StandardDto getStandardById(int standardId) throws Exception {
		// TODO Auto-generated method stub
		StandardEntity std = standardRepo.findByStandardId(standardId);
		if (std == null) {
			throw new Exception("Invalid : Standard Id " + standardId);
		}
		StandardDto stdDto = new StandardDto(std);
		return stdDto;
	}

	public StdDivListDto getDivisionsByStdId(int standardId) throws Exception {
		// TODO Auto-generated method stub
		StandardEntity std = standardRepo.findByStandardId(standardId);
		if (std == null) {
			throw new Exception("Invalid : Standard Id " + standardId);
		}
		StdDivListDto stdDivListDto = new StdDivListDto(std);
//		List<DivisionDto> divList = std.stream().map(div->new DivisionDto(div)).toList();
		return stdDivListDto;
	}

	public StandardDto updateState(int standard) {
		StandardEntity updatedData = null;
		StandardDto standardDto = null;
		try {
			StandardEntity std = standardRepo.findByStandardId(standard);
			if (std == null) {
				throw new Exception("Invalid : Standard Id " + standard);
			}
			if (std.getState().equals(FabEnum.state.UNDER_CONFIG)) {
				std.setState(state.ACTIVE);
				updatedData = standardRepo.save(std);
			} else if (std.getState().equals(FabEnum.state.ACTIVE)) {
				std.setState(state.DEACTIVE);
				updatedData = standardRepo.save(std);
			} else if (std.getState().equals(FabEnum.state.DEACTIVE)) {
				std.setState(state.ACTIVE);
				updatedData = standardRepo.save(std);
			} else {
				// log your data invalid State and return null
			}
			standardDto = new StandardDto(updatedData);
		} catch (Exception ex) {

		}
		return standardDto;
	}

	public StandardDto deleteStandard(int standardId) throws Exception {
		StandardEntity updatedData = null;
		StandardDto StandardDto = null;
		try {
			StandardEntity standard = standardRepo.findByStandardId(standardId);

			standard.setState(state.DELETED);
			updatedData = standardRepo.save(standard);

			StandardDto = new StandardDto(updatedData);
		} catch (Exception ex) {
			throw new Exception("Invalid : Standard Id " + standardId);
		}
		return StandardDto;
	}

	public List<StandardDto> getAllActiveStandard() {
		List<StandardEntity> standards = standardRepo.findAllByState(state.ACTIVE);
		List<StandardDto> divGroup = standards.stream().map(std -> new StandardDto(std)).toList();
		return divGroup;
	}

	public List<StdDivListDto> setDivisionsByStdId(int standardId, List<Integer> division) {
		List<StdDivListDto> divList = new ArrayList<>();
		StandardEntity standard = standardRepo.findByStandardIdAndState(standardId, state.ACTIVE);
		List<DivisionEntity> divisionList = divisionService.fetchAllByDivisionIds(division);
		standard.setDivision(divisionList);
		StandardEntity save = standardRepo.save(standard);
		if (save != null) {
//		   divList = divisionList.stream().map(div-> new StdDivListDto(save)).toList();
			divList = getDivisionsforAllStds();

		}

		return divList;
	}

	public List<StdDivListDto> getDivisionsforAllStds() {
		List<StandardEntity> standards = standardRepo.findAllByState(state.ACTIVE);
		List<StdDivListDto> divGroup = standards.stream().map(std -> new StdDivListDto(std)).toList();
		return divGroup;
	}

	public StdSubListDto getSubjectByStdId(int subjectId) throws Exception {
		StandardEntity std = standardRepo.findByStandardId(subjectId);
		if (std == null) {
			throw new Exception("Invalid : Standard Id " + subjectId);
		}
		StdSubListDto stdDivListDto = new StdSubListDto(std);
		return stdDivListDto;
	}
	
	public List<StdSubListDto> getSubjectforAllStds() {
		List<StandardEntity> standards = standardRepo.findAllByState(state.ACTIVE);
		List<StdSubListDto> divGroup = standards.stream().map(std -> new StdSubListDto(std)).toList();
		return divGroup;
	}
	
	public List<StdSubListDto> setSubjectByStdId(int subjectId, List<Integer> division) {
		List<StdSubListDto> divList = new ArrayList<>();
		StandardEntity standard = standardRepo.findByStandardIdAndState(subjectId, state.ACTIVE);
		List<SubjectEntity> divisionList = subjectService.fetchAllBySubjectIds(division);
		standard.setSubject(divisionList);
		StandardEntity save = standardRepo.save(standard);
		if (save != null) {
//		   divList = divisionList.stream().map(div-> new StdDivListDto(save)).toList();
			divList = getSubjectforAllStds();

		}

		return divList;
	}
	


	
	public List<StdDivSubListDto> getDivSubByStd()
	{
		List<StandardEntity> standardEntity=standardRepo.findAllByState(state.ACTIVE);
		List<StdDivSubListDto> subDivGroup =  standardEntity.stream().map(subDiv-> new StdDivSubListDto(subDiv)).toList();
		
		return subDivGroup;
	}
	
	public String verifyStandardId(int standardId)
	{
		String stdName=null;
		try {
			StandardEntity std=standardRepo.findByStandardId(standardId);
			if(std != null)
			{
				stdName=std.getStdName();
			}
			else throw new  Exception("Standard Id does not Exist "+standardId);
		}
		catch(Exception e)
		{
			log.error("Error in verifyStandardId() "+e);
		}
		return stdName;
	}
	
	
	
}
