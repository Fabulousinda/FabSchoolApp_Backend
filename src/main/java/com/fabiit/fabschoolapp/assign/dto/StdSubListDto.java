package com.fabiit.fabschoolapp.assign.dto;

import java.util.ArrayList;
import java.util.List;

import com.fabiit.fabschoolapp.master.standardMaster.entity.StandardEntity;
import com.fabiit.fabschoolapp.master.subjectMaster.dto.SubjectDto;
import com.fabiit.fabschoolapp.utils.FabEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StdSubListDto {

	private int standardId;
	private String stdName;
	private FabEnum.state state;
	private List<SubjectDto> subjects = new ArrayList<>();

	public StdSubListDto(StandardEntity std) {
		this.standardId = std.getStandardId();
		this.stdName = std.getStdName();
		this.state = std.getState();
		this.subjects = std.getSubject().stream().map(sub -> new SubjectDto(sub)).toList();
	}
}
