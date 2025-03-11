package com.fabiit.fabschoolapp.master.teacherMaster.service;

import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherFourthFormDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherListDto;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentFirstFormDto;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentFourthFormDto;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentFourthFormEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentSecondFormEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentThirdFormEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherFirstFormDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherSecondFormDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherThirdFormDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherFourthFormEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherMasterEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherThirdFormEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.repo.TeacherMasterRepo;
import com.fabiit.fabschoolapp.utils.FabConstant;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TeacherService {
	

	
	public TeacherService() throws IOException
	{
		
	}
	@Autowired
	TeacherMasterRepo teacherMasterRepo;
	
	
//	private Object SecondFormDto;
	
	
	
	


	
	public TeacherFirstFormDto saveFirstForm(TeacherFirstFormDto teacherMasterDto) {
	    TeacherMasterEntity save = null;
	    TeacherFirstFormDto objectDto = null;

	    // Fetch existing teacher or create a new one
	    TeacherMasterEntity teacherMasterEntity = teacherMasterRepo.findById(teacherMasterDto.getTeacherId())
	            .orElse(new TeacherMasterEntity());

	    try {
	        // Only update non-null fields from DTO
	        if (teacherMasterDto.getName() != null) teacherMasterEntity.setName(teacherMasterDto.getName());
	        if (teacherMasterDto.getAdharnumber() != null) teacherMasterEntity.setAdharnumber(teacherMasterDto.getAdharnumber());
	        if (teacherMasterDto.getAlternatenumber() != null) teacherMasterEntity.setAlternatenumber(teacherMasterDto.getAlternatenumber());
	        if (teacherMasterDto.getCurrentaddress() != null) teacherMasterEntity.setCurrentaddress(teacherMasterDto.getCurrentaddress());
	        if (teacherMasterDto.getDate() != null) teacherMasterEntity.setDate(teacherMasterDto.getDate());
	        if (teacherMasterDto.getEmailAddress() != null) teacherMasterEntity.setEmailAddress(teacherMasterDto.getEmailAddress());
	        if (teacherMasterDto.getEmergencyNumber() != null) teacherMasterEntity.setEmergencyNumber(teacherMasterDto.getEmergencyNumber());
	        if (teacherMasterDto.getFatherName() != null) teacherMasterEntity.setFatherName(teacherMasterDto.getFatherName());
	        if (teacherMasterDto.getMotherName() != null) teacherMasterEntity.setMotherName(teacherMasterDto.getMotherName());
	        if (teacherMasterDto.getGender() != null) teacherMasterEntity.setGender(teacherMasterDto.getGender());
	        if (teacherMasterDto.getMaritalStatus() != null) teacherMasterEntity.setMaritalStatus(teacherMasterDto.getMaritalStatus());
	        if (teacherMasterDto.getPannumber() != null) teacherMasterEntity.setPannumber(teacherMasterDto.getPannumber());
	        if (teacherMasterDto.getPhone() != null) teacherMasterEntity.setPhone(teacherMasterDto.getPhone());
	        if (teacherMasterDto.getPresentaddress() != null) teacherMasterEntity.setPresentaddress(teacherMasterDto.getPresentaddress());

	        // Retain existing relations (if present)
	        if (teacherMasterEntity.getQualification() == null) {
	            teacherMasterEntity.setQualification(new ArrayList<>()); // Ensure it's not null
	        }
	        if (teacherMasterEntity.getCertificate() == null) {
	            teacherMasterEntity.setCertificate(new ArrayList<>());
	        }
	        if (teacherMasterEntity.getThirdFormEntity() == null) {
	            teacherMasterEntity.setThirdFormEntity(new TeacherThirdFormEntity());
	        }
	        if (teacherMasterEntity.getFiles() == null) {
	            teacherMasterEntity.setFiles(new ArrayList<>());
	        }

	        // Save updated entity
	        save = teacherMasterRepo.save(teacherMasterEntity);
	        objectDto = new TeacherFirstFormDto(save);

	    } catch (Exception e) {
	        log.error("Error in saveFirstForm() method ", e);
	    }

	    return objectDto;
	}

	public TeacherSecondFormDto saveSecondForm(TeacherSecondFormDto qualificationCertificateDto) {
		TeacherMasterEntity save = null;
		TeacherSecondFormDto objectSecondForm = null;
		try {
			Optional<TeacherMasterEntity> teacherOpt = teacherMasterRepo
					.findById(qualificationCertificateDto.getTeacherId());
			
			

			if (teacherOpt.isPresent()) {
				TeacherMasterEntity teacherMasterEntity = teacherOpt.get();
				teacherMasterEntity.setQualification(qualificationCertificateDto.getDegree());
				teacherMasterEntity.setCertificate(qualificationCertificateDto.getCertificate());
				save = teacherMasterRepo.save(teacherMasterEntity);
				objectSecondForm = new TeacherSecondFormDto(save);
			}
		}

		catch (Exception e) {
			log.error("Error in saveSecondForm() method " + e);
		}

		return objectSecondForm;
	}
	
	public TeacherThirdFormDto saveThirdForm(TeacherThirdFormDto thirdFormDto) {
		TeacherMasterEntity saveForm = null;
		TeacherThirdFormDto objectThirdFormDto = null;

		try {
			TeacherMasterEntity teacherMasterData = teacherMasterRepo.findAllByTeacherId(thirdFormDto.getTeacherId());

			if (teacherMasterData != null) {

				TeacherThirdFormEntity thirdFormEntitySave = new TeacherThirdFormEntity();
				thirdFormEntitySave.setAccountNumber(thirdFormDto.getAccountNumber());
				thirdFormEntitySave.setBank(thirdFormDto.getBank());
				thirdFormEntitySave.setBranch(thirdFormDto.getBranch());
				thirdFormEntitySave.setEmployeeType(thirdFormDto.getEmployeeType());
				thirdFormEntitySave.setIfscCode(thirdFormDto.getIfscCode());
				thirdFormEntitySave.setLevel(thirdFormDto.getLevel());
				thirdFormEntitySave.setPay(thirdFormDto.getPay());
				thirdFormEntitySave.setExperienceDetail(thirdFormDto.getProfessionEntity());
				thirdFormEntitySave.setProfessionId(thirdFormDto.getProfessionId());
				thirdFormEntitySave.setSalary(thirdFormDto.getSalary());
				thirdFormEntitySave.setSubject(thirdFormDto.getSubject());
				thirdFormEntitySave.setTotal(thirdFormDto.getTotal());
				
				teacherMasterData.setThirdFormEntity(thirdFormEntitySave);
				saveForm = teacherMasterRepo.save(teacherMasterData);

				objectThirdFormDto = new TeacherThirdFormDto(saveForm);

			}

		}

		catch (Exception e) {
			log.error("Error in saveThirdForm() " + e);
		}
		return objectThirdFormDto;
	}
	
	
	public List<TeacherFourthFormDto> gettingFourthForm(
			 MultipartFile resume,
			   MultipartFile photo,
			   MultipartFile aadhar,
			     MultipartFile pan,
			     MultipartFile education,
			    MultipartFile experience,
			    MultipartFile skill,
			     int teacherId)
	{
		List<TeacherFourthFormDto> teacherFourthForm=null;
		TeacherMasterEntity teacherMasterEntity=null;
		
		try {
			
			
			TeacherMasterEntity teacherMasterEntity2= teacherMasterRepo.findAllByTeacherId(teacherId);
			if(teacherMasterEntity2 != null)
			{
				List<TeacherFourthFormEntity> teacherFormEntities= new ArrayList<>();
				
				TeacherFourthFormEntity resumeFileData = saveTeacherFourthForm(resume, FabConstant.RESUME, teacherId);
				if(resumeFileData!= null)
					teacherFormEntities.add(resumeFileData);
				
				TeacherFourthFormEntity photoFileData = saveTeacherFourthForm(photo, FabConstant.PHOTO, teacherId);
				if(photoFileData!= null)
					teacherFormEntities.add(photoFileData);
				
				TeacherFourthFormEntity aadharFileData = saveTeacherFourthForm(aadhar, FabConstant.AADHAR, teacherId);
				if(aadharFileData!= null)
					teacherFormEntities.add(aadharFileData);
				
				TeacherFourthFormEntity panFileData = saveTeacherFourthForm(pan, FabConstant.PAN, teacherId);
				if(panFileData!= null)
					teacherFormEntities.add(panFileData);
				
				TeacherFourthFormEntity educationFileData = saveTeacherFourthForm(education, FabConstant.EDUCATION, teacherId);
				if(educationFileData!= null)
					teacherFormEntities.add(educationFileData);
				
				TeacherFourthFormEntity skillFileData = saveTeacherFourthForm(skill, FabConstant.SKILL, teacherId);
				if(skillFileData!= null)
					teacherFormEntities.add(skillFileData);
				
				TeacherFourthFormEntity experienceFileData = saveTeacherFourthForm(education, FabConstant.EXPERIENCE, teacherId);
				if(experienceFileData!= null)
					teacherFormEntities.add(experienceFileData);
				
				teacherMasterEntity2.setFiles(teacherFormEntities);
				teacherMasterEntity=	teacherMasterRepo.save(teacherMasterEntity2);	
				if(teacherMasterEntity != null)
				{
					teacherFourthForm=fourthFormMethod(teacherId);
				}
			}
		}
		catch(Exception e)
		{
			
		}
		return teacherFourthForm;
	}
	
	@Value("Files")
	private String filePath;
	public TeacherFourthFormEntity saveTeacherFourthForm(   MultipartFile multipartFile,String fileInfo, int id) throws Exception
	{
		LocalDateTime date=LocalDateTime.now();
		long fileCustomeEpochName = date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		
//		TeacherMasterEntity teacherMasterEntityfour=null;
//		boolean f=false;
		TeacherFourthFormEntity saveFourthForm=new TeacherFourthFormEntity();
		try {
			Path imagePath= Paths.get(filePath, "static","Teacher Files",String.valueOf(id));
			File directory= imagePath.toFile();
			if(!directory.exists())
			{
				boolean created= directory.mkdirs();
				
				if(!created)
				{
					throw new IOException("file not created "+directory.getAbsolutePath());
				}
			}
			
			
			
			
//			TeacherMasterEntity dataTeacher=new TeacherMasterEntity();
			
//			dataTeacher.setFourthFormEntity(saveFourthForm);
//			teacherMasterEntityfour = teacherMasterRepo.save(dataTeacher);
			
Files.copy(multipartFile.getInputStream(), Paths.get(imagePath+File.separator+fileCustomeEpochName+"."+StringUtils.getFilenameExtension(multipartFile.getOriginalFilename())), StandardCopyOption.REPLACE_EXISTING);
		
			saveFourthForm.setFileName(multipartFile.getOriginalFilename());
			saveFourthForm.setFileCustomName(String.valueOf(fileCustomeEpochName));
			saveFourthForm.setFileInfo(fileInfo);
			saveFourthForm.setFileType( StringUtils.getFilenameExtension(multipartFile.getOriginalFilename()));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return saveFourthForm;
		
	}

	public List<TeacherFourthFormDto> fourthFormMethod(Integer id) throws Exception
	{
		List<TeacherFourthFormDto> fileData=null;
		Path imagepath=Paths.get("Files", "static","Teacher Files",String.valueOf(id));
		
		try {
			TeacherMasterEntity teacherMasterEntity = teacherMasterRepo.findAllByTeacherId(id);
			List<TeacherFourthFormEntity> fourthFormEntity = teacherMasterEntity.getFiles();
			 fileData = fourthFormEntity.stream().map(fourth-> {
				 TeacherFourthFormDto f=new TeacherFourthFormDto(fourth);
				   try {
					   Path filePath= imagepath.resolve(fourth.getFileCustomName() +"."+fourth.getFileType());
					   if(Files.exists(imagepath) && Files.size(imagepath)> 0)
					   {
						   byte[] data = Files.readAllBytes(filePath);
							String encodeToString = Base64.getEncoder().encodeToString(data);
							f.setBase64String(encodeToString);
					   }
					   else
					   {
						   log.error("File not found "+filePath);
					   }
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 return f;
			 }).toList();
			 
	//			fileData=new FileInput(fourthFormEntity);
			
		}
		catch(Exception e)
		{
			log.error("Error in fourthFormMrthod() "+e);
		}
		return fileData;
	}
	public TeacherFirstFormDto firstFormMethod(Integer id) 
	{
		TeacherFirstFormDto firstFormDto=null;
		try
		{
			TeacherMasterEntity teacherMasterEntity = teacherMasterRepo.findAllByTeacherId(id);
			
			firstFormDto=new TeacherFirstFormDto(teacherMasterEntity);
		}
		catch(Exception e)
		{
			log.error("Error in firstFormMethod() "+e);
		}
		return firstFormDto;
		
	}
	
	public TeacherSecondFormDto secondFormMethod(Integer id) 
	{
		
		TeacherSecondFormDto  secondFormDto=null;
		
		
		try {
			TeacherMasterEntity teacherMasterEntitySecond =teacherMasterRepo.findAllByTeacherId(id);
//			
			secondFormDto=new TeacherSecondFormDto(teacherMasterEntitySecond);
			
			 
			
		}
		catch(Exception e)
		{
			log.error("Error in secondFormMethod() "+e);
		}
		
		return secondFormDto;
		
	}
	
	public TeacherThirdFormDto thirdFormMethod(Integer id)
	{
		TeacherThirdFormDto thirdFormDto=null;
		
		try {
			TeacherMasterEntity teacherMasterEntityThird=teacherMasterRepo.findAllByTeacherId(id);
			thirdFormDto=new TeacherThirdFormDto(teacherMasterEntityThird);
		}
		
		catch(Exception e)
		{
			log.error(" Error in thirdFormMethod() "+e);
		}
		return thirdFormDto;
	}

 public List<TeacherListDto> teacherListDto()
 {
	 List<TeacherListDto> objectTeacherList=null;
	 try
	 {
		
		List<TeacherMasterEntity> teacherMasterEntity= teacherMasterRepo.findAll();
		objectTeacherList=teacherMasterEntity.stream().map(teacher-> new TeacherListDto(teacher)).toList();
		 
		 
	 }
	 catch(Exception e)
	 {
		 log.error("Error in teacherListDto() :: please Resolve "+e);
	 }
	 return  objectTeacherList;
 }
	
	

}
