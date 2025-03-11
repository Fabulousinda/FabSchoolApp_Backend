package com.fabiit.fabschoolapp.master.gradeMaster.dto;

import com.fabiit.fabschoolapp.master.gradeMaster.entity.GradeEntity;
import com.fabiit.fabschoolapp.utils.FabEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GradeDto {

	private int gradeId;
	private String gradeName;
	private FabEnum.state state;

	public GradeDto(GradeEntity grade) {
		this.gradeName = grade.getGradeName();
		this.gradeId = grade.getGradeId();
		this.state = grade.getState();

	}
}
