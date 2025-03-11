package com.fabiit.fabschoolapp.assign.dto;

import java.util.ArrayList;
import java.util.List;

import com.fabiit.fabschoolapp.master.divisionMaster.dto.DivisionDto;
import com.fabiit.fabschoolapp.master.standardMaster.entity.StandardEntity;
import com.fabiit.fabschoolapp.utils.FabEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StdDivListDto {

	private int standardId;
	private String stdName;
	private FabEnum.state state;
	private List<DivisionDto> divisions = new ArrayList<>();

	public StdDivListDto(StandardEntity std) {
		this.standardId = std.getStandardId();
		this.stdName = std.getStdName();
		this.state = std.getState();
		this.divisions = std.getDivision().stream().map(div -> new DivisionDto(div)).toList();
	}
}
