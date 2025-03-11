package com.fabiit.fabschoolapp.master.studentMaster.Service;

import java.awt.print.Pageable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;


import java.util.stream.Collectors;


import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentFourthFormEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentSecondFormEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentThirdFormEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Repo.StudentFourthFormRepo;
import com.fabiit.fabschoolapp.master.studentMaster.Repo.StudentMasterRepo;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherFourthFormDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherThirdFormDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherFourthFormEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherMasterEntity;
import com.fabiit.fabschoolapp.utils.FabConstant;
import com.fabiit.fabschoolapp.utils.FabEnum.state;
import com.fabiit.fabschoolapp.utils.MessageConstants;
import com.fabiit.fabschoolapp.utils.ResponseDto;
import com.fabiit.fabschoolapp.utils.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

import com.fabiit.fabschoolapp.master.standardMaster.entity.StandardEntity;
import com.fabiit.fabschoolapp.master.standardMaster.repo.StandardRepo;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentFirstFormDto;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentFourthFormDto;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentListDto;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentSecondFormDto;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentThirdFormDto;


@Service
@Slf4j
public class studentService {

	@Autowired
	StudentMasterRepo studentMasterRepo;
	
	@Autowired
	private StudentFourthFormRepo studentFourthFormRepo;
	
	@Autowired
	private StandardRepo standardRepo;
	

//	
//	public StudentFirstFormDto saveStudentFirstForm(StudentFirstFormDto studentMasterDto) {
//	    try {
//	        // Fetch existing student details from the database
//	        StudentMasterEntity studentMasterEntity = studentMasterRepo.findById(studentMasterDto.getStudentId())
//	                .orElse(new StudentMasterEntity()); // If student doesn't exist, create a new one
//
//	     
//	        	// Update only the necessary fields
//		        studentMasterEntity.setName(studentMasterDto.getName());
//		        studentMasterEntity.setAdharNumber(studentMasterDto.getAdharNumber());
//		        studentMasterEntity.setBloodGroup(studentMasterDto.getBloodGroup());
//		        studentMasterEntity.setCaste(studentMasterDto.getCaste());
//		        studentMasterEntity.setDateOfBirth(studentMasterDto.getDateOfBirth());
//		        studentMasterEntity.setEnrollment(studentMasterDto.getEnrollment());
//		        studentMasterEntity.setFather(studentMasterDto.getFather());
//		        studentMasterEntity.setGender(studentMasterDto.getGender());
//		        studentMasterEntity.setGrade(studentMasterDto.getGrade());
//		        studentMasterEntity.setLastSchoolName(studentMasterDto.getLastSchoolName());
//		        studentMasterEntity.setMedicalCondition(studentMasterDto.getMedicalCondition());
//		        studentMasterEntity.setMother(studentMasterDto.getMother());
//		        studentMasterEntity.setPhone(studentMasterDto.getPhone());
//		        studentMasterEntity.setNationality(studentMasterDto.getNationality());
//		        studentMasterEntity.setPlaceOfBirth(studentMasterDto.getPlaceOfBirth());
//		        studentMasterEntity.setMotherTongue(studentMasterDto.getMotherTongue());
//	        
//	        
//
//	        // ✅ Preserve the linked entities (if they already exist)
//	        if (studentMasterEntity.getStudentSecondFormEntity() == null) {
//	            studentMasterEntity.setStudentSecondFormEntity(new StudentSecondFormEntity());
//	        }
//	        if (studentMasterEntity.getStudentThirdFormEntity() == null) {
//	            studentMasterEntity.setStudentThirdFormEntity(new StudentThirdFormEntity());
//	        }
//	        if (studentMasterEntity.getFourthFormStudent() == null) {
//	            studentMasterEntity.setFourthFormStudent(new ArrayList<>());
//	        }
//
//	        // Save the updated student details
//	        StudentMasterEntity updatedEntity = studentMasterRepo.save(studentMasterEntity);
//
//	        return new StudentFirstFormDto(updatedEntity);
//	    } catch (Exception e) {
//	        log.error("Error in saveStudentFirstForm() method: ", e);
//	        return null;
//	    }
//	}

	
	public StudentFirstFormDto saveStudentFirstForm(StudentFirstFormDto studentMasterDto) {
	    try {
	        // Fetch existing student details from the database
	        StudentMasterEntity studentMasterEntity = studentMasterRepo.findById(studentMasterDto.getStudentId())
	                .orElse(new StudentMasterEntity()); // If student doesn't exist, create a new one

	        // ✅ Validate StandardEntity before setting enrollment
	        StandardEntity standardEntity = standardRepo.findByStandardIdAndState(studentMasterDto.getEnrollment(), state.ACTIVE);
	        if (standardEntity == null) {
	            throw new RuntimeException("Invalid Standard ID or Standard is not active.");
	        }

	        // Update only the necessary fields
	        studentMasterEntity.setName(studentMasterDto.getName());
	        studentMasterEntity.setAdharNumber(studentMasterDto.getAdharNumber());
	        studentMasterEntity.setBloodGroup(studentMasterDto.getBloodGroup());
	        studentMasterEntity.setCaste(studentMasterDto.getCaste());
	        studentMasterEntity.setDateOfBirth(studentMasterDto.getDateOfBirth());
	        studentMasterEntity.setEnrollment(studentMasterDto.getEnrollment()); // Now validated
	        studentMasterEntity.setFather(studentMasterDto.getFather());
	        studentMasterEntity.setGender(studentMasterDto.getGender());
	        studentMasterEntity.setGrade(studentMasterDto.getGrade());
	        studentMasterEntity.setLastSchoolName(studentMasterDto.getLastSchoolName());
	        studentMasterEntity.setMedicalCondition(studentMasterDto.getMedicalCondition());
	        studentMasterEntity.setMother(studentMasterDto.getMother());
	        studentMasterEntity.setPhone(studentMasterDto.getPhone());
	        studentMasterEntity.setNationality(studentMasterDto.getNationality());
	        studentMasterEntity.setPlaceOfBirth(studentMasterDto.getPlaceOfBirth());
	        studentMasterEntity.setMotherTongue(studentMasterDto.getMotherTongue());

	        // ✅ Preserve the linked entities (if they already exist)
	        if (studentMasterEntity.getStudentSecondFormEntity() == null) {
	            studentMasterEntity.setStudentSecondFormEntity(new StudentSecondFormEntity());
	        }
	        if (studentMasterEntity.getStudentThirdFormEntity() == null) {
	            studentMasterEntity.setStudentThirdFormEntity(new StudentThirdFormEntity());
	        }
	        if (studentMasterEntity.getFourthFormStudent() == null) {
	            studentMasterEntity.setFourthFormStudent(new ArrayList<>());
	        }

	        // Save the updated student details
	        StudentMasterEntity updatedEntity = studentMasterRepo.save(studentMasterEntity);

	        return new StudentFirstFormDto(updatedEntity);
	    } catch (Exception e) {
	        log.error("Error in saveStudentFirstForm() method: ", e);
	        throw new RuntimeException("Failed to save student details: " + e.getMessage());
	    }
	}


	
	public StudentSecondFormDto saveStudentSecondForm(StudentSecondFormDto studentSecondFormDto)
	{
		StudentMasterEntity saveStudentEntity=null;
		StudentSecondFormDto objectSecondFormDto=null;
		
		
		try
		{
			StudentMasterEntity studentMasterEntity=studentMasterRepo.findAllByStudentId(studentSecondFormDto.getStudentId());
			if(studentMasterEntity != null)
			{
				StudentSecondFormEntity studentSecondFormEntity=new StudentSecondFormEntity();
				
				studentSecondFormEntity.setAadharNumber(studentSecondFormDto.getAadharNumber());
				studentSecondFormEntity.setAddress(studentSecondFormDto.getAddress());
				studentSecondFormEntity.setDaughter(studentSecondFormDto.getDaughter());
				studentSecondFormEntity.setDesignation(studentSecondFormDto.getDesignation());
				studentSecondFormEntity.setEmail(studentSecondFormDto.getEmail());
				studentSecondFormEntity.setFatherName(studentSecondFormDto.getFatherName());
				studentSecondFormEntity.setFatherNumber(studentSecondFormDto.getFatherNumber());
				studentSecondFormEntity.setHobbies(studentSecondFormDto.getHobbies());
				studentSecondFormEntity.setIncome(studentSecondFormDto.getIncome());
				studentSecondFormEntity.setMotherAadhar(studentSecondFormDto.getMotherAadhar());
				studentSecondFormEntity.setMotherDesignation(studentSecondFormDto.getMotherDesignation());
				studentSecondFormEntity.setMotherEmail(studentSecondFormDto.getMotherEmail());
				studentSecondFormEntity.setMotherHobbies(studentSecondFormDto.getMotherHobbies());
				studentSecondFormEntity.setMotherIncome(studentSecondFormDto.getMotherIncome());
				studentSecondFormEntity.setMotherName(studentSecondFormDto.getMotherName());
				studentSecondFormEntity.setMotherNumber(studentSecondFormDto.getMotherNumber());
				studentSecondFormEntity.setMotherOfficeAddress(studentSecondFormDto.getMotherOfficeAddress());
				studentSecondFormEntity.setMotherOfficeNumber(studentSecondFormDto.getMotherOfficeNumber());
				studentSecondFormEntity.setMotherOrganization(studentSecondFormDto.getMotherOrganization());
				studentSecondFormEntity.setMotherProfession(studentSecondFormDto.getMotherProfession());
				studentSecondFormEntity.setMotherQualification(studentSecondFormDto.getMotherQualification());
				studentSecondFormEntity.setMotherSocialActivity(studentSecondFormDto.getMotherSocialActivity());
				studentSecondFormEntity.setNoOfChild(studentSecondFormDto.getNoOfChild());
				studentSecondFormEntity.setOfficeNumber(studentSecondFormDto.getOfficeNumber());
				studentSecondFormEntity.setOrganization(studentSecondFormDto.getOrganization());
				studentSecondFormEntity.setParentsId(studentSecondFormDto.getParentsId());
				studentSecondFormEntity.setProfession(studentSecondFormDto.getProfession());
				studentSecondFormEntity.setQualification(studentSecondFormDto.getQualification());
				studentSecondFormEntity.setSocialActivity(studentSecondFormDto.getSocialActivity());
				studentSecondFormEntity.setSon(studentSecondFormDto.getSon());
				
				studentMasterEntity.setStudentSecondFormEntity(studentSecondFormEntity);
				saveStudentEntity = studentMasterRepo.save(studentMasterEntity);
				objectSecondFormDto= new StudentSecondFormDto(saveStudentEntity);
			}
			
		}
		catch(Exception e)
		{
			log.error("Error in saveStudentSecondForm() "+e);
		}
		return objectSecondFormDto;
	}
	
	public StudentThirdFormDto saveStudentThirdFormDto(StudentThirdFormDto studentThirdFormDto)
	{
		StudentMasterEntity saveMasterEntity=null;
		StudentThirdFormDto objectStudentThirdForm= null;
		
		try {

			StudentMasterEntity studentMasterEntity=studentMasterRepo.findAllByStudentId(studentThirdFormDto.getStudentId());
			if(studentMasterEntity != null)
			{
			  StudentThirdFormEntity studentThirdForm= new StudentThirdFormEntity();
			  
			  studentThirdForm.setAddress1(studentThirdFormDto.getAddress1());
			    studentThirdForm.setContactId(studentThirdFormDto.getContactId());
			    studentThirdForm.setPhone(studentThirdFormDto.getPhone());
			    studentThirdForm.setEmail(studentThirdFormDto.getEmail());
			    studentThirdForm.setAddress1(studentThirdFormDto.getAddress1());
			    studentThirdForm.setAddress2(studentThirdFormDto.getAddress2());
			    studentThirdForm.setAddress3(studentThirdFormDto.getAddress3());
			    studentThirdForm.setCountry(studentThirdFormDto.getCountry());
			    studentThirdForm.setState(studentThirdFormDto.getState());
			    studentThirdForm.setPincode(studentThirdFormDto.getPincode());
			    studentThirdForm.setEmergencyContact(studentThirdFormDto.getEmergencyContact());
			    studentThirdForm.setEmergencyAddress1(studentThirdFormDto.getEmergencyAddress1());
			    studentThirdForm.setEmergencyAddress2(studentThirdFormDto.getEmergencyAddress2());
			    studentThirdForm.setPrevious(studentThirdFormDto.getPrevious());
			    studentThirdForm.setStudentName(studentThirdFormDto.getStudentName());
			    studentThirdForm.setStandard(studentThirdFormDto.getStandard());
			    studentThirdForm.setCurrent(studentThirdFormDto.getCurrent());
			    studentThirdForm.setTransport(studentThirdFormDto.getTransport());
			    studentThirdForm.setPickup(studentThirdFormDto.getPickup());
			    studentThirdForm.setEmergencyPersonName(studentThirdFormDto.getEmergencyPersonName());
			    studentThirdForm.setRelation(studentThirdFormDto.getRelation());
			    
			    studentMasterEntity.setStudentThirdFormEntity(studentThirdForm);
			    
			    saveMasterEntity=studentMasterRepo.save(studentMasterEntity);
			    objectStudentThirdForm = new StudentThirdFormDto(saveMasterEntity);
			   
			}
			
		}
		catch(Exception e)
		{
			log.error("Error in studentThirdFormDto2");
		}
		return objectStudentThirdForm;
	}
	
	
	public List<StudentFourthFormDto> gettingFourthFormInput( 
			 MultipartFile birth,
			 MultipartFile lc,
			 MultipartFile reportCard,
			 MultipartFile medical,
			 MultipartFile studentAadhar,
			 MultipartFile fatherAadhar,
			 MultipartFile motherAadhar,
			 MultipartFile studentPhoto,
			 MultipartFile fatherPhoto,
			 MultipartFile motherPhoto, 
			 int id)
	{
		
		StudentMasterEntity studentMasterObject=null;
		List<StudentFourthFormDto> studentFourthFormDto=null;
//		   ArrayList<Object> StudentFourthFormDto;
		
		try {
           
			StudentMasterEntity studentMasterEntity= studentMasterRepo.findAllByStudentId(id);
			if(studentMasterEntity != null)
			{
				List<StudentFourthFormEntity> studentFourthForm= new ArrayList<>();
				StudentFourthFormEntity birthFileData = saveStudentFourthForm(birth, FabConstant.BIRTH, id);
				if(birthFileData != null)
				studentFourthForm.add(birthFileData);
				
				StudentFourthFormEntity lcFileData = saveStudentFourthForm(lc, FabConstant.LC, id);
				if(lcFileData != null)
				studentFourthForm.add(lcFileData);
				
				StudentFourthFormEntity reportFileData = saveStudentFourthForm(reportCard, FabConstant.REPORTCARD, id);
				if(reportFileData != null)
				studentFourthForm.add(reportFileData);
				
				StudentFourthFormEntity medicalFileData = saveStudentFourthForm(medical, FabConstant.MEDICAL, id);
				if(medicalFileData != null)
				studentFourthForm.add(medicalFileData);
			
				StudentFourthFormEntity studentAadharFileData = saveStudentFourthForm(studentAadhar, FabConstant.STUDENTAADHAR, id);
				if(studentAadharFileData != null)
				studentFourthForm.add(studentAadharFileData);
			
				StudentFourthFormEntity fatherAadharFileData = saveStudentFourthForm(fatherAadhar, FabConstant.FATHERAADHAR, id);
				if(fatherAadharFileData != null)
				studentFourthForm.add(fatherAadharFileData);
				
				StudentFourthFormEntity motherAadharFileData = saveStudentFourthForm(motherAadhar, FabConstant.MOTHERAADHAR, id);
				if(motherAadharFileData != null)
				studentFourthForm.add(motherAadharFileData);

				StudentFourthFormEntity studentphotoFileData = saveStudentFourthForm(studentPhoto, FabConstant.STUDENTPHOTO, id);
				if(studentphotoFileData != null)
				studentFourthForm.add(studentphotoFileData);
				
				StudentFourthFormEntity fatherphotoFileData = saveStudentFourthForm(fatherPhoto, FabConstant.FATHERPHOTO, id);
				if(fatherphotoFileData != null)
				studentFourthForm.add(fatherphotoFileData);
			
				StudentFourthFormEntity motherphotoFileData = saveStudentFourthForm(motherPhoto, FabConstant.MOTHERPHOTO, id);
				if(motherphotoFileData != null)
				studentFourthForm.add(motherphotoFileData);
		
				
				studentMasterEntity.setFourthFormStudent(studentFourthForm);
				studentMasterObject= studentMasterRepo.save(studentMasterEntity);
				 if(studentMasterObject != null)
				 {
					 studentFourthFormDto=getStudentFourthForm(id);
				 }
				
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return studentFourthFormDto;
	}

	
//	public StudentFourthFormDto gettingFourthFormInput( 
//	        MultipartFile birth,
//	        MultipartFile lc,
//	        MultipartFile reportCard,
//	        MultipartFile medical,
//	        MultipartFile studentAadhar,
//	        MultipartFile fatherAadhar,
//	        MultipartFile motherAadhar,
//	        MultipartFile studentPhoto,
//	        MultipartFile fatherPhoto,
//	        MultipartFile motherPhoto, 
//	        int id) {
//
//	    StudentMasterEntity studentMasterObject = null;
//	    StudentFourthFormDto studentFourthFormDto = null;
//
//	    try {
//	        StudentMasterEntity studentMasterEntity = studentMasterRepo.findAllByStudentId(id);
//	        if (studentMasterEntity != null) {
//	            List<StudentFourthFormEntity> studentFourthForm = new ArrayList<>();
//
//	            // Save each file
//	            List<MultipartFile> files = Arrays.asList(birth, lc, reportCard, medical, studentAadhar,
//	                                                      fatherAadhar, motherAadhar, studentPhoto, fatherPhoto, motherPhoto);
//	            List<String> fileConstants = Arrays.asList(FabConstant.BIRTH, FabConstant.LC, FabConstant.REPORTCARD, 
//	                                                       FabConstant.MEDICAL, FabConstant.STUDENTAADHAR, 
//	                                                       FabConstant.FATHERAADHAR, FabConstant.MOTHERAADHAR,
//	                                                       FabConstant.STUDENTPHOTO, FabConstant.FATHERPHOTO, FabConstant.MOTHERPHOTO);
//
//	            for (int i = 0; i < files.size(); i++) {
//	                if (!files.get(i).isEmpty()) {
//	                    StudentFourthFormEntity fileData = saveStudentFourthForm(files.get(i), fileConstants.get(i), id);
//	                    if (fileData != null) {
//	                        fileData.setStudentMaster(studentMasterEntity);
//	                        studentFourthForm.add(fileData);
//	                    }
//	                }
//	            }
//
//	            studentMasterEntity.setFourthFormStudent(studentFourthForm);
//	            studentMasterObject = studentMasterRepo.save(studentMasterEntity);
//
//	            // Return DTO
//	            studentFourthFormDto = new StudentFourthFormDto(studentMasterObject);
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//
//	    return studentFourthFormDto;
//	}
	
	

//	public StudentFourthFormEntity saveStudentFourthForm(MultipartFile file, String fileInfo, Integer studentId) throws IOException
//	{
//		LocalDateTime date= LocalDateTime.now();
//		long fileCustomName= date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//		
//		 Path imagePath=null;
//		 imagePath = Paths.get(baseStorage, "static"," Student files", String.valueOf(studentId));
//		
//		
//		StudentFourthFormEntity objectForSave= new StudentFourthFormEntity();
//		
//		try {
//			
////			StudentMasterEntity studentMasterObject= studentMasterRepo.findAllByStudentId(studentId);
////			if(studentMasterObject != null)
////			{
//				imagePath = Paths.get(baseStorage, "static"," Student files", String.valueOf(studentId));
//				 File directory = imagePath.toFile();
//				    if (!directory.exists()) {
//				        boolean created = directory.mkdirs();
//				        if (created) {
//				            try {
//				                Files.walk(imagePath)
//				                     .sorted(Comparator.reverseOrder())
//				                     .map(Path::toFile)
//				                     .forEach(File::delete);
//
//				                log.info("Successfully deleted directory: " + imagePath);
//				            } catch (IOException e) {
//				                log.error("Failed to delete directory: " + e.getMessage(), e);
//				                throw new IOException("Failed to delete directory: " + imagePath, e);
//				            }
//				        }
//				        if (!created) {
//				            throw new IOException("Failed to create directories: " + directory.getAbsolutePath());
//				          }
//				        
//				        
//				    }
//				    Files.copy(file.getInputStream(), Paths.get(imagePath+File.separator+fileCustomName+"."
//				    +StringUtils.getFilenameExtension(file.getOriginalFilename())), StandardCopyOption.REPLACE_EXISTING);
//				    
//				    objectForSave.setFileCustomName(String.valueOf(fileCustomName));
//				    objectForSave.setFileInfo(fileInfo);
//				    objectForSave.setFileName(file.getOriginalFilename());
//				    objectForSave.setFileType(StringUtils.getFilenameExtension(file.getOriginalFilename()));
//				    
//				    
////				    
////				    studentMasterObject.setStudentFourthFormEntity(objectForSave);
////				    studentMasterEntity=studentMasterRepo.save(studentMasterObject);
////				    studentFourthFormDto= new StudentFourthFormDto(studentMasterEntity);
//				    
//				    
////			}
//			
//			    
//		}
//		catch(Exception e)
//		{
//			log.error("Error in saveStudentFourthForm() "+e);
//		}
//		return objectForSave;
//	}
//	
	

	 // Add this to save the entity
	@Value("Files")
	private String baseStorage;
	public StudentFourthFormEntity saveStudentFourthForm(MultipartFile file, String fileInfo, Integer studentId) throws IOException {
	    LocalDateTime date = LocalDateTime.now();
	    long fileCustomName = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

	    Path imagePath = Paths.get(baseStorage, "static", "Student files", String.valueOf(studentId));
	    File directory = imagePath.toFile();

	    // ✅ Create Directory if it Doesn't Exist
	    if (!directory.exists() && !directory.mkdirs()) {
	        throw new IOException("Failed to create directory: " + directory.getAbsolutePath());
	    }

	    // ✅ Save File to Directory
	    Path filePath = imagePath.resolve(fileCustomName + "." + StringUtils.getFilenameExtension(file.getOriginalFilename()));
	    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	    // ✅ Create Entity & Save to Database
	    StudentFourthFormEntity objectForSave = new StudentFourthFormEntity();
	    objectForSave.setFileCustomName(String.valueOf(fileCustomName));
	    objectForSave.setFileInfo(fileInfo);
	    objectForSave.setFileName(file.getOriginalFilename());
	    objectForSave.setFileType(StringUtils.getFilenameExtension(file.getOriginalFilename()));

	    return studentFourthFormRepo.save(objectForSave); // ✅ Save to Database
	}


	
	// Geeting methods from here
	
	public StudentFirstFormDto getStudentFirstForm(Integer id)
	{
		StudentFirstFormDto studentFirstFormDto=null;
		
		try {
			StudentMasterEntity studentMasterEntity=studentMasterRepo.findAllByStudentId(id);
			studentFirstFormDto= new StudentFirstFormDto(studentMasterEntity);
		}
		catch(Exception e)
		{
			log.error("Error in getStudentFirstForm() "+e);
		}
		return studentFirstFormDto;
	}
	
	public StudentSecondFormDto getStudentSecondForm(Integer id)
	{
		StudentSecondFormDto studentSecondFormDto=null;
		try {
			StudentMasterEntity studentMasterEntity=studentMasterRepo.findAllByStudentId(id);
			studentSecondFormDto = new StudentSecondFormDto(studentMasterEntity);
		}
		catch(Exception e)
		{
			log.error("Error in getStudentSecondForm() "+e);
		}
		return studentSecondFormDto;
	}
	
	public StudentThirdFormDto getStudentThirdForm(Integer id)
	{
		StudentThirdFormDto objectStudentThirdForm=null;
		try {
			StudentMasterEntity studentMasterEntity=studentMasterRepo.findAllByStudentId(id);
			objectStudentThirdForm= new StudentThirdFormDto(studentMasterEntity);
		}
		
		catch(Exception e)
		{
			log.error("Error in getStudentThirdForm "+e);
		}
		return objectStudentThirdForm;
	}
	
	
	
public List<StudentListDto> getStudentList()
{
	List<StudentListDto> objectStudentList=null;
	
	try {
		List<StudentMasterEntity> objectStudentEntity=studentMasterRepo.findAll();
		objectStudentList= objectStudentEntity.stream().map(student -> new StudentListDto(student)).toList(); 
		
	}
	catch(Exception e)
	{
		log.error("Error in getStudentList() "+e);
	}
	return objectStudentList;
}



@Value("Files") // Ensure this is defined in application.properties
private String filePath;

public List<StudentFourthFormDto> getStudentFourthForm(Integer id) {
    List<StudentFourthFormDto> fileData = null;
    Path myPath = Paths.get(filePath, "static", "Student files", String.valueOf(id));

    try {
        StudentMasterEntity studentMasterObject = studentMasterRepo.findAllByStudentId(id);
        List<StudentFourthFormEntity> studentObjectFourthForm = studentMasterObject.getFourthFormStudent();

        fileData = studentObjectFourthForm.stream().map(fourth -> {
            StudentFourthFormDto stf = new StudentFourthFormDto(fourth);
            try {
                // Corrected path resolution
                Path filePath = myPath.resolve(fourth.getFileCustomName() + "." + fourth.getFileType());

                // Check if file exists and is not empty
                if (Files.exists(filePath) && Files.size(filePath) > 0) {
                    byte[] data = Files.readAllBytes(filePath);
                    String encodeToString = Base64.getEncoder().encodeToString(data);
                    stf.setBase64String(encodeToString);
                } else {
                    log.error("File not found or empty: " + filePath);
                }

            } catch (IOException e) {
                log.error("Error reading file: " + e.getMessage());
            }
            return stf;
        }).toList();

    } catch (Exception e) {
        log.error("Error in getStudentFourthForm(): " + e.getMessage());
    }
    return fileData;
}



public List<StudentListDto> getStudentBySorting()
{
	List<StudentListDto> objectStudentList=null;
	try {
		List<StudentMasterEntity> objectStudentEntity=studentMasterRepo.findAll();
		objectStudentList= objectStudentEntity.stream().map(student-> new StudentListDto(student)).toList();
		
	}
	catch(Exception e)
	{
		log.error("Erro in getStudentBySorting() "+e);
		}
	return objectStudentList;
}

public List<StudentListDto> getStudentByNameLike(String name)
{
	List<StudentListDto> objectStudentList=null;
	
	try {
		List<StudentMasterEntity> objectStudentEntity= studentMasterRepo.findByFatherStartsWith(name);
		objectStudentList= objectStudentEntity.stream().map(student-> new StudentListDto(student)).toList();
		
		
	}
	catch(Exception e)
	{
		log.error("Error in getStudentByNameLike() "+e);
	}
	return objectStudentList;
}
	

}
