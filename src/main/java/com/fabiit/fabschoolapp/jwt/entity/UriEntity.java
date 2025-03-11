package com.fabiit.fabschoolapp.jwt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class UriEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uriId;

	private String uri;
	@JoinColumn(table = "Privileges", columnDefinition = "privilegeId")
	private int privilegeId;
}
