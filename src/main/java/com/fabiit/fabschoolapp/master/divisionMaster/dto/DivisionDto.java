package com.fabiit.fabschoolapp.master.divisionMaster.dto;

import com.fabiit.fabschoolapp.master.divisionMaster.entity.DivisionEntity;
import com.fabiit.fabschoolapp.utils.FabEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DivisionDto {

	private int divisionId;
	private String divName;
	private FabEnum.state state;

	public DivisionDto(DivisionEntity std) {
		this.divName = std.getDivName();
		this.divisionId = std.getDivisionId();
		this.state = std.getState();

	}
}
