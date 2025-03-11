package com.fabiit.fabschoolapp.master.standardMaster.entity;

import java.time.Instant;
import java.util.List;

import com.fabiit.fabschoolapp.master.divisionMaster.entity.DivisionEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;
import com.fabiit.fabschoolapp.master.subjectMaster.entity.SubjectEntity;
import com.fabiit.fabschoolapp.utils.FabEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Standard")
@NoArgsConstructor
@Data
public class StandardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int standardId;
	@Column(unique = true)
	private String stdName;
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

	@ManyToMany(fetch = FetchType.LAZY)
	@Column(name = "divisionId")
	private List<DivisionEntity> division;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Column(name = "subjectId")
	private List<SubjectEntity> subject;
	
	
	
	
}
