
package com.fabiit.fabschoolapp.exam.entity;



import java.sql.Date;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(
	    name = "ExamActivation",
	    uniqueConstraints = @UniqueConstraint(columnNames = {"examId", "standard", "subject"}))
public class ExamActivationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int activationId;
	@Column
	private int examId;
	@Column
	private String standard;
	@Column
	private String subject;
	@Column
	private int totalMarks;
	@Column
	private int passingMarks;
	@Column
	private String evaluationType;
	

}
