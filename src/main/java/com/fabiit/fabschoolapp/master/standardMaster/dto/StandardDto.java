package com.fabiit.fabschoolapp.master.standardMaster.dto;

import com.fabiit.fabschoolapp.master.standardMaster.entity.StandardEntity;
import com.fabiit.fabschoolapp.utils.FabEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StandardDto {

	private int standardId;
	private String stdName;
	private FabEnum.state state;

	public StandardDto(StandardEntity std) {
		this.stdName = std.getStdName();
		this.standardId = std.getStandardId();
		this.state = std.getState();

	}
}
