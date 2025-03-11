package com.fabiit.fabschoolapp.assign.dto;

import java.util.ArrayList;
import java.util.List;

import com.fabiit.fabschoolapp.master.divisionMaster.dto.DivisionDto;
import com.fabiit.fabschoolapp.master.standardMaster.entity.StandardEntity;
import com.fabiit.fabschoolapp.master.subjectMaster.dto.SubjectDto;
import com.fabiit.fabschoolapp.utils.FabEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class StdDivSubListDto {
	
	private int standardId;
	private String stdName;
	private FabEnum.state state;
	private List<SubjectDto> subjects = new ArrayList<>();
	private List<DivisionDto> divisions = new ArrayList<>();
	
	public StdDivSubListDto(StandardEntity standardEntity)
	{
		this.standardId= standardEntity.getStandardId();
		this.stdName= standardEntity.getStdName();
		this.state= standardEntity.getState();
		this.subjects= standardEntity.getSubject().stream().map(sub-> new SubjectDto(sub)).toList();
		this.divisions=standardEntity.getDivision().stream().map(div-> new DivisionDto(div)).toList();
	}

}
