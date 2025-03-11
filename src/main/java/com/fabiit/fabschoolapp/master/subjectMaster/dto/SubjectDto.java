package com.fabiit.fabschoolapp.master.subjectMaster.dto;

import com.fabiit.fabschoolapp.master.subjectMaster.entity.SubjectEntity;
import com.fabiit.fabschoolapp.utils.FabEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubjectDto {

	private int subjectId;
	private String subName;

	private FabEnum.state state;

	public SubjectDto(SubjectEntity std) {
		this.subjectId = std.getSubjectId();
		this.subName = std.getSubName();
		this.state = std.getState();
	

	}
}
