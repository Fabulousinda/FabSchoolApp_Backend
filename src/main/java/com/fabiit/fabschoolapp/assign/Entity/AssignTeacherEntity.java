package com.fabiit.fabschoolapp.assign.Entity;

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
@Table(name = "assignTeacher", 
uniqueConstraints = @UniqueConstraint(columnNames = {"teacherId", "stdName", "divName", "subName"}))
          
public class AssignTeacherEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int assignId;
	@Column
	private int teacherId;
	@Column
	private String stdName;
	@Column
	private String divName;
	@Column
	private String subName;
	@Column
    private boolean classTeacher;

}
