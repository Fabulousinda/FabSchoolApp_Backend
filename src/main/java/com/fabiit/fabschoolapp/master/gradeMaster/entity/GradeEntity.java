package com.fabiit.fabschoolapp.master.gradeMaster.entity;

import java.time.Instant;

import com.fabiit.fabschoolapp.utils.FabEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Grade")
@NoArgsConstructor
@Data
public class GradeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gradeId;
	@Column(unique = true)
	private String gradeName;
	@Column
	@Enumerated(EnumType.STRING)
	private FabEnum.state state;
	@Column
	private long userCreated;
	@Column
	private Instant dtCreated;
	@Column
	private long userLastUpdated;
	@Column
	private Instant dtLastUpdated;

}
