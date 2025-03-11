package com.fabiit.fabschoolapp.assign.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.catalina.realm.CombinedRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fabiit.fabschoolapp.assign.Entity.AssignStudentEntity;
import com.fabiit.fabschoolapp.assign.Entity.AssignTeacherEntity;
import com.fabiit.fabschoolapp.assign.Repo.AssignStudentRepo;
import com.fabiit.fabschoolapp.assign.Repo.AssignTeacherRepo;
import com.fabiit.fabschoolapp.assign.dto.AssignStudentDto;
import com.fabiit.fabschoolapp.assign.dto.AssignTeacherDto;

import com.fabiit.fabschoolapp.assign.dto.StudentListAssignDto;
import com.fabiit.fabschoolapp.assign.dto.TeacherListAssignDto;
import com.fabiit.fabschoolapp.master.divisionMaster.entity.DivisionEntity;
import com.fabiit.fabschoolapp.master.divisionMaster.repo.DivisionRepo;
import com.fabiit.fabschoolapp.master.standardMaster.entity.StandardEntity;
import com.fabiit.fabschoolapp.master.standardMaster.repo.StandardRepo;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentListDto;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Repo.StudentMasterRepo;
import com.fabiit.fabschoolapp.master.subjectMaster.entity.SubjectEntity;
import com.fabiit.fabschoolapp.master.subjectMaster.repo.SubjectRepo;
import com.fabiit.fabschoolapp.master.teacherMaster.Dto.TeacherListDto;
import com.fabiit.fabschoolapp.master.teacherMaster.Entity.TeacherMasterEntity;
import com.fabiit.fabschoolapp.master.teacherMaster.repo.TeacherMasterRepo;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class AssignService {
	
	@Autowired
	private StandardRepo standardRepo;
	
	@Autowired
	private DivisionRepo divisionRepo;
	
	@Autowired
	private AssignStudentRepo assignStudentRepo;
	
	@Autowired
	private StudentMasterRepo studentMasterRepo;
	
	@Autowired
	private TeacherMasterRepo teacherMasterRepo;
	
	@Autowired
	private SubjectRepo subjectRepo;
	
	@Autowired
	private AssignTeacherRepo assignTeacherRepo;
	
	
	

	public AssignStudentDto saveStdDivByStudentId(int studentId, int std, int div)
	{
//		StandardEntity standardEntity= new StandardEntity();
//		DivisionEntity divisionEntity= new DivisionEntity();
//	    StudentMasterEntity studentMasterEntity= new StudentMasterEntity();
	   AssignStudentDto assignStudentDto=null;
	   AssignStudentEntity assignEntity=null;
	    AssignStudentEntity assignStudentEntity=new AssignStudentEntity();
	   
		
		StudentMasterEntity studentMasterEntity2= studentMasterRepo.findAllByStudentId(studentId);
		
		
		if(studentMasterEntity2 != null)
		{
			AssignStudentEntity objetAssign = assignStudentRepo.findByStudentId(studentId);
			if(objetAssign != null)
			{
				assignStudentEntity.setAssignId(objetAssign.getAssignId());
				assignStudentEntity.setStudentId(studentId);
		
			
			StandardEntity standardEntity2=standardRepo.findByStandardId(std);
			if(standardEntity2 != null)
			{
				assignStudentEntity.setStdName(standardEntity2.getStdName());
			}
			
			DivisionEntity divisionEntity2=divisionRepo.findByDivisionId(div);
			if(divisionEntity2 != null)
			{
				assignStudentEntity.setDivName(divisionEntity2.getDivName());
			}
			
			assignEntity= assignStudentRepo.save(assignStudentEntity);
			assignStudentDto=new AssignStudentDto(assignEntity);
			}
			else
			{
				assignStudentEntity.setStudentId(studentId);
				
				StandardEntity standardEntity2=standardRepo.findByStandardId(std);
				if(standardEntity2 != null)
				{
					assignStudentEntity.setStdName(standardEntity2.getStdName());
				}
				
				DivisionEntity divisionEntity2=divisionRepo.findByDivisionId(div);
				if(divisionEntity2 != null)
				{
					assignStudentEntity.setDivName(divisionEntity2.getDivName());
				}
				
				assignEntity= assignStudentRepo.save(assignStudentEntity);
				assignStudentDto=new AssignStudentDto(assignEntity);
			}
			
		}
		
		
		
		
		return assignStudentDto;		
	}
	
//	public AssignTeacherDto saveStdDivSubByTeacherId(int assignId ,int teacherId, int std, int div, int sub,boolean classTeacher)
//	{
//		AssignTeacherDto assignTeacherDto= null;
//		AssignTeacherEntity saveObjectTeacherEntity= null;
//		AssignTeacherEntity assignTeacherEntity= new AssignTeacherEntity();
//		
//		
//		try {
//			TeacherMasterEntity teacherMasterEntity= teacherMasterRepo.findAllByTeacherId(teacherId);
//			if(teacherMasterEntity != null)
//			{
//				AssignTeacherEntity assignTeacher=assignTeacherRepo.findByAssignId(assignId);
//				if(assignTeacher != null)
//				{
//					assignTeacherEntity.setAssignId(assignId);
//					assignTeacherEntity.setTeacherId(teacherId);
//					
//					StandardEntity standardEntity= standardRepo.findByStandardId(std);
//					if(standardEntity != null)
//					{
//						assignTeacherEntity.setStdName(standardEntity.getStdName());
//						
//					}
//					DivisionEntity divisionEntity=divisionRepo.findByDivisionId(div);
//					if(divisionEntity != null)
//					{
//						assignTeacherEntity.setDivName(divisionEntity.getDivName());
//					}
//					
//					
//					
//					SubjectEntity subjectEntity= subjectRepo.findBySubjectId(sub);
//					if(subjectEntity != null)
//					{
//						assignTeacherEntity.setSubName(subjectEntity.getSubName());
//					}
//					saveObjectTeacherEntity=assignTeacherRepo.save(assignTeacherEntity);
//					assignTeacherDto=new AssignTeacherDto(saveObjectTeacherEntity);
//				
//				}
//				else
//				{
//					
//					assignTeacherEntity.setTeacherId(teacherId);
//					
//					StandardEntity standardEntity= standardRepo.findByStandardId(std);
//					if(standardEntity != null)
//					{
//						assignTeacherEntity.setStdName(standardEntity.getStdName());
//						
//					}
//					DivisionEntity divisionEntity= divisionRepo.findByDivisionId(div);
//					if(divisionEntity != null)
//					{
//						assignTeacherEntity.setDivName(divisionEntity.getDivName());
//					}
//					
//					SubjectEntity subjectEntity= subjectRepo.findBySubjectId(sub);
//					if(subjectEntity != null)
//					{
//						assignTeacherEntity.setSubName(subjectEntity.getSubName());
//					}
//					saveObjectTeacherEntity=assignTeacherRepo.save(assignTeacherEntity);
//					assignTeacherDto=new AssignTeacherDto(saveObjectTeacherEntity);
//				}
//						
//				
//			}
//			
//		}
//		catch (Exception e) {
//			log.error("Error in saveStdDivSubByTeacherId() "+e);
//		}
//		
//		return assignTeacherDto;
//	}
	
	public AssignTeacherDto saveStdDivSubByTeacherId(int assignId, int teacherId, int std, int div, int sub, boolean classTeacher) {
	    AssignTeacherDto assignTeacherDto = null;
	    AssignTeacherEntity saveObjectTeacherEntity = null;
	    AssignTeacherEntity assignTeacherEntity = new AssignTeacherEntity();

	    try {
	        TeacherMasterEntity teacherMasterEntity = teacherMasterRepo.findAllByTeacherId(teacherId);
	        if (teacherMasterEntity != null) {

	            // Check if teacher is already assigned as a class teacher
	            if (classTeacher) {
	                AssignTeacherEntity existingClassTeacher = assignTeacherRepo.findByTeacherIdAndClassTeacherTrue(teacherId);
	                if (existingClassTeacher != null) {
	                    throw new Exception("This teacher is already assigned as a class teacher.");
	                }
	            }

	            AssignTeacherEntity assignTeacher = assignTeacherRepo.findByAssignId(assignId);
	            if (assignTeacher != null) {
	                assignTeacherEntity.setAssignId(assignId);
	            }
	            assignTeacherEntity.setTeacherId(teacherId);
	            assignTeacherEntity.setClassTeacher(classTeacher); // Set the classTeacher boolean

	            StandardEntity standardEntity = standardRepo.findByStandardId(std);
	            if (standardEntity != null) {
	                assignTeacherEntity.setStdName(standardEntity.getStdName());
	            }

	            DivisionEntity divisionEntity = divisionRepo.findByDivisionId(div);
	            if (divisionEntity != null) {
	                assignTeacherEntity.setDivName(divisionEntity.getDivName());
	            }

	            SubjectEntity subjectEntity = subjectRepo.findBySubjectId(sub);
	            if (subjectEntity != null) {
	                assignTeacherEntity.setSubName(subjectEntity.getSubName());
	            }

	            saveObjectTeacherEntity = assignTeacherRepo.save(assignTeacherEntity);
	            assignTeacherDto = new AssignTeacherDto(saveObjectTeacherEntity);
	        }
	    } catch (Exception e) {
	        log.error("Error in saveStdDivSubByTeacherId() " + e.getMessage());
	        throw new RuntimeException("Error while assigning teacher.");
	    }

	    return assignTeacherDto;
	}


	public List<AssignStudentDto> getStdDivByStudentId()
	{
				List<AssignStudentDto> assignStudent=null;
		
		try {
			
			List<AssignStudentEntity> assignStudentEntity=assignStudentRepo.findAll();
			
			assignStudent= assignStudentEntity.stream().map(student-> new AssignStudentDto(student)).toList();
			
					
		}
		catch(Exception e)
		{
			log.error("Error in getStdDivByStudentID() "+e);
		}
		return assignStudent;
	}
	
	public List<AssignTeacherDto> getStdDivSubByTeacherId()
	{
				List<AssignTeacherDto> assignTeacher=null;
		
		try {
			
			List<AssignTeacherEntity> assignTeacherEntity=assignTeacherRepo.findAll();
			
			assignTeacher= assignTeacherEntity.stream().map(teacher-> new AssignTeacherDto(teacher)).toList();
			
					
		}
		catch(Exception e)
		{
			log.error("Error in getStdDivSubByTeacherId() "+e);
		}
		return assignTeacher;
	}

	
	public List<StudentListDto> getListByStudentId()
	{
				List<StudentListDto> objectStudentList=null;
		
		try {
			
			List<StudentMasterEntity> objectStudentMaster=studentMasterRepo.findAll();
			
			objectStudentList= objectStudentMaster.stream().map(student-> new StudentListDto(student)).toList();
			
					
		}
		catch(Exception e)
		{
			log.error("Error in getStdDivByStudentID() "+e);
		}
		return objectStudentList;
	}
	
	public List<TeacherListDto> getListByTeacherId()
	{
				List<TeacherListDto> objectTeacherList=null;
		
		try {
			
			List<TeacherMasterEntity> objectTeacherMaster=teacherMasterRepo.findAll();
			
			objectTeacherList= objectTeacherMaster.stream().map(taecher-> new TeacherListDto(taecher)).toList();
			
					
		}
		catch(Exception e)
		{
			log.error("Error in getListByTeacherId() "+e);
		}
		return objectTeacherList;
	}
	
	public List<AssignStudentDto> saveMultipleStdDivByStudentId(List<Integer> studentId, int std, int div)
	{
		List<AssignStudentDto> objectAssignStudent=new ArrayList<>();
		
		
		
		
		
		try {
			studentId.stream().forEach(id -> 
			{
			  AssignStudentDto objectResult= saveStdDivByStudentId(id, std, div);
			  objectAssignStudent.add(objectResult);
				
			
			});
			
		}
		catch(Exception e)
		{
			log.error("Error in saveMultipleStdDivByStudentId() "+e);
		}
		return objectAssignStudent;
	}
	
//	public List<CombinedStudentDto> getDetailsByStdDiv(int std, int div) {
//		List<AssignStudentDto> objectAssignStudent = new ArrayList<>();
//		List<StudentListDto> studentListDto = new ArrayList<>();
//		List<CombinedStudentDto> combinedStudentDto= new ArrayList<>();
//		
//
//		try {
//			StandardEntity standardEntity= standardRepo.findByStandardId(std);
//			String standard= standardEntity.getStdName();
//			
//			DivisionEntity divisionEntity= divisionRepo.findByDivisionId(div);
//			String division=divisionEntity.getDivName();
//			
//					
//
//			List<AssignStudentEntity> assignStudentEntity = assignStudentRepo.findByStdNameAndDivName(standard, division);
//			objectAssignStudent = assignStudentEntity.stream().map(student -> new AssignStudentDto(student)).toList();
//			
//			assignStudentEntity.stream().forEach(studentId -> 
//			{
//				StudentMasterEntity studentMaster=studentMasterRepo.findAllByStudentId(studentId.getStudentId());
//				if(studentMaster != null)
//				{
//					studentListDto.add(new StudentListDto(studentMaster));
//					
//				
//				}
//			}
//					);
//			CombinedStudentDto combinedStudent= new CombinedStudentDto();
//			combinedStudent.setAssignStudent(objectAssignStudent);
//			combinedStudent.setStudentListDto(studentListDto);
//			combinedStudentDto.add(combinedStudent);
//			
//
//		} catch (Exception e) {
//			log.error("Error in getDetailsByStdDiv() " + e);
//		}
//		return combinedStudentDto;
//	}

	
//	public List<CombinedStudentDto> getStudentsBySearhing(String search) {
//		
//		List<CombinedStudentDto> objectCombinedData=new ArrayList<>();
//		List<AssignStudentDto> objectAssignStudent1=new ArrayList<>();
//		List<AssignStudentDto> objectAssignStudent=new ArrayList<>();
//		List<StudentListDto> objectStudentList1=new ArrayList<>();
//		List<StudentListDto> objectStudentList=new ArrayList<>();
//		try {
//			List<StudentMasterEntity> byName= studentMasterRepo.findByNameStartsWithOrFatherStartsWithOrMotherStartsWith(search, search,search);
//			
//			List<AssignStudentEntity> byStd= assignStudentRepo.findByStdNameStartsWithAndDivNameStartsWith(search, search);
//			
//			
//			
//			if(byStd != null)
//			{
//				
//				objectAssignStudent= byStd.stream().map(student-> new AssignStudentDto(student)).toList();
//				
//				byStd.stream().forEach(id-> {
//					StudentMasterEntity objectStudentEntity= studentMasterRepo.findAllByStudentId(id.getStudentId());
//					if(objectStudentEntity != null)
//					{
//						objectStudentList1.add(new StudentListDto(objectStudentEntity));
//					}
//					
//				});
//				
//				CombinedStudentDto combinedStudent= new CombinedStudentDto();
//				combinedStudent.setAssignStudent(objectAssignStudent);
//				combinedStudent.setStudentListDto(objectStudentList1);
//				objectCombinedData.add(combinedStudent);
//			}
//			if(byName != null)
//			{
//				objectStudentList=byName.stream().map(student-> new StudentListDto(student)).toList();
//				
//				byName.stream().forEach(student-> {
//					AssignStudentEntity objectAssignStudentEntity=assignStudentRepo.findByStudentId(student.getStudentId());
//					if(objectAssignStudentEntity != null)
//					{
//						objectAssignStudent1.add(new AssignStudentDto(objectAssignStudentEntity));
//					}
//				});
//				
//				CombinedStudentDto combinedStudent= new CombinedStudentDto();
//				combinedStudent.setAssignStudent(objectAssignStudent1);
//				combinedStudent.setStudentListDto(objectStudentList);
//				objectCombinedData.add(combinedStudent);
//				
//			}
//			
//			
//			
//			
//		}
//		catch(Exception e)
//		{
//			log.error("Error in getStudentsBySearching() "+e);
//		}
//		return objectCombinedData;
//	}
	
//	public List<CombinedTeachersDto> getDetailsOfTeacher()
//	{
//		List<CombinedTeachersDto> objectTeacherDto=new ArrayList<>();
//		
//		try {
//			
//			List<AssignTeacherEntity> assignTeacherList=assignTeacherRepo.findAll();
//			List<TeacherMasterEntity> teacherList=teacherMasterRepo.findAll();
//			
//			 Map<Integer, TeacherMasterEntity> teacherMap = teacherList.stream()
//		                .collect(Collectors.toMap(TeacherMasterEntity::getTeacherId, Function.identity()));
//			 
////			 for (AssignTeacherEntity assignTeacher : assignTeacherList) {
////		            TeacherMasterEntity teacher = teacherMap.get(assignTeacher.getTeacherId());
////		            
////		            if (teacher != null) { // Ensure a match exists
////		            	objectTeacherDto.add(new CombinedTeachersDto(assignTeacher, teacher));
////		            }
////			 }
//			 for (AssignTeacherEntity assignTeacher : assignTeacherList) {
//		            System.out.println("Processing AssignTeacherEntity: " + assignTeacher);
//		            TeacherMasterEntity teacher = teacherMap.get(assignTeacher.getTeacherId());
//
//		            if (teacher != null) {
//		                System.out.println("Matched Teacher: " + teacher);
//		                objectTeacherDto.add(new CombinedTeachersDto(assignTeacher, teacher));
//		            } else {
//		                System.out.println("No matching teacher found for teacherId: " + assignTeacher.getTeacherId());
//		            }
//		        }
//			 }
//			 
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//		return objectTeacherDto;
//	}
	
	
	public List<TeacherListDto> getTeacherListAssign()
	{
		try {
			return teacherMasterRepo.findTeachers();
			
		}
		catch(Exception e)
		{
			 log.error("Error in fetching teacher List");
			 return List.of();
		}
	}
	
	
	public List<StudentListAssignDto> getStudentListAssign()
	{
		try {
			return assignStudentRepo.getStudentListAssign();
		}
		catch(Exception e)
		{
		  log.error("Error in getStudentAssign"+e);
		  return List.of();
		}
	}
public List<StudentListAssignDto> getStudentsByStdDiv(int std, int div)
{
	try {
		return  assignStudentRepo.findStudentsByStdDiv(std, div);
	}
	catch(Exception e)
	{
		log.error("Error in getStudentsByStdDiv() "+e);
		return List.of();
	}
}
	
public List<StudentListAssignDto> findStudents(int std, int div, String search)
{
	try {
		return assignStudentRepo.findStudents(std, div, search);
	}
	catch(Exception e)
	{
		log.error("error in findStudents()"+e);
		return List.of();
	}
}

public List<TeacherListAssignDto> getAssignTeachersByid(int teacherId)
{
	try {
		return assignTeacherRepo.findAssignedTeachers(teacherId);
	
	}
	catch(Exception e)
	{
		log.error("Error in getAssignTeacherById()"+e);
		return List.of();
	}
}
	
}
