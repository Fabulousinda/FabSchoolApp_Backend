package com.fabiit.fabschoolapp.master.subjectMaster.entity;

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
@Table(name = "Subject")
@NoArgsConstructor
@Data
public class SubjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int subjectId;
	@Column(unique = true)
	private String subName;
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
