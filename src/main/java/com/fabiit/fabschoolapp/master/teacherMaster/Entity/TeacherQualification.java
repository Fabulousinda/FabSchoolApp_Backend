package com.fabiit.fabschoolapp.master.teacherMaster.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="qualification")
@Data
@NoArgsConstructor
public class TeacherQualification implements Serializable {

	private static final long serialVersionUID = 11L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int qualificationId;
	@Column
	private String degree;
	@Column
	private String board;
	@Column
	private String year;

//	@ManyToOne
//	transient private TeacherMasterEntity teacher;
}
