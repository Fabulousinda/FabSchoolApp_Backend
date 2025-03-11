package com.fabiit.fabschoolapp.master.studentMaster.Entity;

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
@Table(name="contactInfo")
public class StudentThirdFormEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int contactId;
    
	private String phone;
	private String email;
	private String address1;
	private String address2;
	private String address3;
	private String country;
	private String state;
	private String pincode;
	private String emergencyContact;
	private String emergencyAddress1;
	private String emergencyAddress2;
	
	private String emergencyPersonName;
	private String relation;
	private String previous;
	private String studentName;
	private String standard;
	private String current;
	private String transport;
	private String pickup;


}
