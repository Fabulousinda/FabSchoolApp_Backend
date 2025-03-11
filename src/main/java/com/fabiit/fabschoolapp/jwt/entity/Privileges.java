package com.fabiit.fabschoolapp.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Privileges {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int privilegeId;
	private String category;
	private String displayName;
	private String name;
}
