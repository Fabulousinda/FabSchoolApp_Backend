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
@Table(name="certificate")
@Data
@NoArgsConstructor
public class TeacherCertificate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int certificateId;
	@Column
	private String certificate;
	@Column
	private String skills;
	
//	@ManyToOne
//	transient private TeacherMasterEntity teacher;
	

}
