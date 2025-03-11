package com.fabiit.fabschoolapp.exam.entity;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="studentExam")
public class ExamMasterEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int examId;
    @Column
	private String examName;
    @Column
	private String classes;
    @Column
    private boolean confirm;
    @Column
	private Instant startDate;
	@Column
	private Instant endDate;
	@Column
	@Enumerated(EnumType.STRING)
	private FabEnum.state state;

}
