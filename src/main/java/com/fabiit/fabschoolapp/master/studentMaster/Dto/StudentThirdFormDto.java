package com.fabiit.fabschoolapp.master.studentMaster.Dto;

import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentThirdFormDto {
	
	private int studentId;
	private int contactId;
	private String phone;
	private String email;
	private String address1;
	private String address2;
	private String address3;
	private String emergencyPersonName;
	private String relation;
	private String country;
	private String state;
	private String pincode;
	private String emergencyContact;
	private String emergencyAddress1;
	private String emergencyAddress2;
	private String previous;
	private String studentName;
	private String standard;
	private String current;
	private String transport;
	private String pickup;
	
	public StudentThirdFormDto(StudentMasterEntity studentMasterEntity)
	{
		
		    this.studentId = studentMasterEntity.getStudentId();
	        this.contactId = studentMasterEntity.getStudentThirdFormEntity().getContactId();
	        this.phone = studentMasterEntity.getStudentThirdFormEntity().getPhone();
	        this.email = studentMasterEntity.getStudentThirdFormEntity().getEmail();
	        this.address1 = studentMasterEntity.getStudentThirdFormEntity().getAddress1();
	        this.address2 = studentMasterEntity.getStudentThirdFormEntity().getAddress2();
	        this.address3 = studentMasterEntity.getStudentThirdFormEntity().getAddress3();
	        this.emergencyPersonName= studentMasterEntity.getStudentThirdFormEntity().getEmergencyPersonName();
	        this.relation=studentMasterEntity.getStudentThirdFormEntity().getRelation();
	        this.country = studentMasterEntity.getStudentThirdFormEntity().getCountry();
	        this.state = studentMasterEntity.getStudentThirdFormEntity().getState();
	        this.pincode = studentMasterEntity.getStudentThirdFormEntity().getPincode();
	        this.emergencyContact = studentMasterEntity.getStudentThirdFormEntity().getEmergencyContact();
	        this.emergencyAddress1 = studentMasterEntity.getStudentThirdFormEntity().getEmergencyAddress1();
	        this.emergencyAddress2 = studentMasterEntity.getStudentThirdFormEntity().getEmergencyAddress2();
	        this.previous = studentMasterEntity.getStudentThirdFormEntity().getPrevious();
	        this.studentName = studentMasterEntity.getStudentThirdFormEntity().getStudentName();
	        this.standard = studentMasterEntity.getStudentThirdFormEntity().getStandard();
	        this.current = studentMasterEntity.getStudentThirdFormEntity().getCurrent();
	        this.transport = studentMasterEntity.getStudentThirdFormEntity().getTransport();
	        this.pickup = studentMasterEntity.getStudentThirdFormEntity().getPickup();
	        
	}
	
	

}
