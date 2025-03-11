package com.fabiit.fabschoolapp.master.studentMaster.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="studentFile")
public class StudentFourthFormEntity  implements Serializable {

	
	private static final long serialVersionUID = 100L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private int fileId;
	@Column
	private String fileName;
	@Column
	private String fileType;
	@Column
	private String fileCustomName;
	@Column
	private String fileInfo;
}
