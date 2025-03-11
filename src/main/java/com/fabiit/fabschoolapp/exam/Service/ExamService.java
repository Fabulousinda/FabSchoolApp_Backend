package com.fabiit.fabschoolapp.exam.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fabiit.fabschoolapp.assign.Entity.AssignStudentEntity;
import com.fabiit.fabschoolapp.assign.Repo.AssignStudentRepo;
import com.fabiit.fabschoolapp.assign.dto.AssignStudentDto;






import com.fabiit.fabschoolapp.exam.entity.ExamMasterEntity;
import com.fabiit.fabschoolapp.exam.repo.StudentMarksRepo;
import com.fabiit.fabschoolapp.exam.repo.ExamActivationRepo;
import com.fabiit.fabschoolapp.exam.repo.ExamMasterRepo;
import com.fabiit.fabschoolapp.exam.Dto.AddMarksDto;
import com.fabiit.fabschoolapp.exam.Dto.ExamActivationDto;
import com.fabiit.fabschoolapp.exam.Dto.ExamMasterDto;
import com.fabiit.fabschoolapp.exam.Dto.StudentMarksDto;
import com.fabiit.fabschoolapp.exam.entity.StudentMarksEntity;
import com.fabiit.fabschoolapp.exam.entity.ExamActivationEntity;




import com.fabiit.fabschoolapp.master.divisionMaster.entity.DivisionEntity;
import com.fabiit.fabschoolapp.master.divisionMaster.repo.DivisionRepo;
import com.fabiit.fabschoolapp.master.gradeMaster.entity.GradeEntity;
import com.fabiit.fabschoolapp.master.gradeMaster.repo.GradeRepo;
import com.fabiit.fabschoolapp.master.standardMaster.dto.StandardDto;
import com.fabiit.fabschoolapp.master.standardMaster.entity.StandardEntity;
import com.fabiit.fabschoolapp.master.standardMaster.repo.StandardRepo;
import com.fabiit.fabschoolapp.master.studentMaster.Dto.StudentListDto;
import com.fabiit.fabschoolapp.master.studentMaster.Entity.StudentMasterEntity;
import com.fabiit.fabschoolapp.master.studentMaster.Repo.StudentMasterRepo;
import com.fabiit.fabschoolapp.master.subjectMaster.entity.SubjectEntity;
import com.fabiit.fabschoolapp.master.subjectMaster.repo.SubjectRepo;
import com.fabiit.fabschoolapp.utils.FabEnum;
import com.fabiit.fabschoolapp.utils.FabEnum.state;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j

public class ExamService {
	

	
	@Autowired
	private StandardRepo standardRepo;
	
	@Autowired
	private ExamMasterRepo examMasterRepo;
	
	@Autowired
	private ExamActivationRepo examActivationRepo;
	
	@Autowired
	private StudentMarksRepo addMarksRepo;

	
	@Autowired
	private DivisionRepo divisionRepo;
	
	@Autowired
	private SubjectRepo subjectRepo;

	
	@Autowired
	private StudentMasterRepo studentMasterRepo;

	
	@Autowired
	AssignStudentRepo assignStudentRepo;
	
	@Autowired
	GradeRepo gradeRepo;
	

	
	public List<ExamMasterDto> createStudentExam( 
			String name,
			List<Integer> standards,
			boolean confirm,
			Instant startDate,
			Instant endDate)
	{
		ExamMasterDto objectExamMaster=null;
		ExamMasterEntity examEntity=new ExamMasterEntity();
		ExamMasterEntity saveExam=null;
		List<ExamMasterDto> examMasterDto=null;
		
		
		
		try {
			
		String std=	standards.stream().map(String::valueOf).collect(Collectors.joining(","));
			
						
			
				
				examEntity.setClasses(std);
				examEntity.setExamName(name);
				examEntity.setConfirm(confirm);
				examEntity.setStartDate(startDate);
				examEntity.setEndDate(endDate);
				examEntity.setState(state.UNDER_CONFIG);;
                saveExam=examMasterRepo.save(examEntity);
                if(saveExam != null)
                {
                	examMasterDto=getStudentExam();
                }
			
			
		}
		catch(Exception e)
		{
			log.error("Error in saveStudentExam() "+ e);
		}
		return examMasterDto;
	}
	

	
	
	public List<ExamMasterDto> getStudentExam()
	{
		List<ExamMasterDto> objectExamDto=new ArrayList<>();


		try {
			List<ExamMasterEntity> examEntity= examMasterRepo.findAll();
			
			List<String> modifiedResult=new ArrayList<>();
			
			for (ExamMasterEntity record : examEntity) {
	            String storedString = record.getClasses();  
	            
	           
	            if (storedString == null || storedString.isEmpty()) {
	                continue; 
	            }
	            List<Integer> numberIds = Arrays.stream(storedString.split(","))
                        .map(String::trim)  // Remove spaces
                        .filter(s -> !s.isEmpty() && s.matches("\\d+")) // Ensure valid numbers
                        .map(Integer::parseInt)  // Convert to Integer
                        .collect(Collectors.toList());
	            
	            List<StandardEntity> fetchedValues=standardRepo.findByStandardIdIn(numberIds);
	            
//	            String replaceString= fetchedValues.stream().map(StandardEntity::getStdName).collect(Collectors.joining(","));

	            List<Map<String, Object>> standardList = new ArrayList<>();

	           
	            Map<Integer, String> standardMap = fetchedValues.stream()
	                    .collect(Collectors.toMap(StandardEntity::getStandardId, StandardEntity::getStdName, (oldValue, newValue) -> oldValue));

	        
	            for (Integer stdId : numberIds) {
	                if (standardMap.containsKey(stdId)) {
	                    Map<String, Object> standardValue = new HashMap<>();
	                    standardValue.put("StdId", stdId);
	                    standardValue.put("stdValue", standardMap.get(stdId));

	                    standardList.add(standardValue);
	                }
	            }
	            
//	            modifiedResult.add(replaceString);
	            objectExamDto.add(new ExamMasterDto(
	            		record.getExamId(),
	            		record.getExamName(),
	            		standardList,
	            		record.isConfirm(),
	            		record.getStartDate(),
	            		record.getEndDate(),
	            		record.getState()));

			}
		
			
		}
		catch(Exception e)
		{
			log.error("Error in getStudentExam() "+e);
		}
		return objectExamDto;
	}
	

	


	public List<ExamActivationDto> saveExamActivation(int activationId, int examId, int standardId, List<Integer> subjectIds, String evaluationType, int totalMarks, int passingMarks, String grade) {
	    List<ExamActivationDto> objectMarks = null;

	    try {
	        ExamMasterEntity examEntity = examMasterRepo.findByExamId(examId);
	        if (examEntity != null) {
	            StandardEntity standardEntity = standardRepo.findByStandardId(standardId);
	            String standard = (standardEntity != null) ? standardEntity.getStdName() : null;

	            for (Integer subjectId : subjectIds) {
	                SubjectEntity subjectEntity = subjectRepo.findBySubjectId(subjectId);
	                if (subjectEntity != null) {
	                    String subjectName = subjectEntity.getSubName();
	                    
	                    // Check if entry already exists
	                    ExamActivationEntity existingEntity = examActivationRepo.findByExamIdAndStandardAndSubject(examId, standard, subjectName);

	                    if (existingEntity != null) {
	                        if (activationId == 0) {
	                            throw new RuntimeException("This data already exists: ExamId=" + examId + ", Standard=" + standard + ", Subject=" + subjectName);
	                        } else {
	                            // Update the existing entity
	                            existingEntity.setEvaluationType(evaluationType);
	                            if ("grade".equalsIgnoreCase(evaluationType)) {
	                               
	                                existingEntity.setTotalMarks(totalMarks);
	                                existingEntity.setPassingMarks(passingMarks);
	                            } else if ("marks".equalsIgnoreCase(evaluationType)) {
	                                existingEntity.setTotalMarks(totalMarks);
	                                existingEntity.setPassingMarks(passingMarks);
	                               
	                            }
	                            ExamActivationEntity updatedEntity = examActivationRepo.save(existingEntity);
	                            if(updatedEntity != null)
	                            {
	                            	objectMarks=getExamActivation(examId);
	                            }
	                        }
	                    } else {
	                        // Create new entry
	                        ExamActivationEntity examActivationEntity = new ExamActivationEntity();
	                        examActivationEntity.setActivationId(activationId);
	                        examActivationEntity.setExamId(examId);
	                        examActivationEntity.setSubject(subjectName);
	                        examActivationEntity.setStandard(standard);
	                        examActivationEntity.setEvaluationType(evaluationType);

	                        if ("grade".equalsIgnoreCase(evaluationType)) {
	                        	
	                            examActivationEntity.setTotalMarks(totalMarks);
	                            examActivationEntity.setPassingMarks(passingMarks);
	                        } else if ("marks".equalsIgnoreCase(evaluationType)) {
	                            examActivationEntity.setTotalMarks(totalMarks);
	                            examActivationEntity.setPassingMarks(passingMarks);
	                            
	                        }

	                        ExamActivationEntity savedEntity = examActivationRepo.save(examActivationEntity);
	                        if(savedEntity != null)
	                        {
	                        	objectMarks = getExamActivation(examId);
	                        }
	                        
	                    }
	                }
	            }
	        }
	    } catch (Exception e) {
	        log.error("Error in saveExamActivation: " + e.getMessage());
	        throw new RuntimeException("Error while saving exam activation.");
	    }

	    return objectMarks;
	}


	public List<ExamActivationDto> getExamActivation(int examId) {
	    List<ExamActivationDto> examMarksDto = new ArrayList<>();
	    int standardId=0,subjectId=0;
	    String subject=null, standard=null;
	    try {
	        List<ExamActivationEntity> examActivationEntities = examActivationRepo.findByExamId(examId);

	        for (ExamActivationEntity marks : examActivationEntities) {
	            // **Standard Mapping**
	            StandardEntity standardEntity = standardRepo.findByStdName(marks.getStandard());
	            SubjectEntity subjectEntity=subjectRepo.findBySubName(marks.getSubject());
	            if (standardEntity != null) {
	            	standardId=standardEntity.getStandardId();
	            	standard=standardEntity.getStdName();
	                
	            }
	            
	            if(subjectEntity != null)
	            {
	            	subjectId=subjectEntity.getSubjectId();
	            	subject=subjectEntity.getSubName();
	            }


	            // **Creating DTO Object**
	            ExamActivationDto dto = new ExamActivationDto(
	                marks.getActivationId(),
	                marks.getExamId(),
	                standardId,
	                standard,
	                subjectId,
	                subject,
	                marks.getEvaluationType(),
	                marks.getTotalMarks(),
	                marks.getPassingMarks()
	               

	            );

	            examMarksDto.add(dto);
	        }

	    } catch (Exception e) {
	        log.error("Error in getExamActivation(): " + e);
	    }

	    return examMarksDto;
	}

	
	
//	public List<AddMarksDto> studentDetailMarks(int examId, int std, int div, int sub, List<StudentMarksDto> studentMarksDto )
//{
//		
//		List<AddMarksDto> addMarksDto=null;
//		List<StudentMarksEntity> saveAddMarks=new ArrayList<>();
//		StudentMarksEntity addMarksEntity=new StudentMarksEntity();
//		List<StudentMarksEntity> save=null;
//		
//		
//		
//		try {
//			
//			for(StudentMarksDto marks: studentMarksDto)
//			{
//				StudentMasterEntity studentMasterEntity=studentMasterRepo.findAllByStudentId(marks.getStudentId());
//				if(studentMasterEntity != null)
//				{
//					ExamActivationEntity examActivationEntity=examActivationRepo.findByActivationId(marks.getActivationId());
//					if(examActivationEntity != null)
//					{
//						addMarksEntity.setMarksId(marks.getMarksId());
//						addMarksEntity.setActivationId(marks.getActivationId());
//						addMarksEntity.setStudentId(marks.getStudentId());
//						addMarksEntity.setStudentMarks(marks.getStudentMarks());
//						saveAddMarks.add(addMarksEntity);
//						System.out.println(addMarksEntity);
//					}
//					
//				}
//			}
//			System.out.println(saveAddMarks);
//			try {
//			    save = addMarksRepo.saveAll(saveAddMarks);
//			    addMarksDto=getStudentByStdDivSub(examId, std, div, sub);
//			    
//			} catch (Exception e) {
//			    log.error("Error while saving student marks: ", e);
//			}
//	
//		
//		}
//			
//		
//		catch (Exception e) {
//			log.error("Error in studentDetailMarks"+e);
//		}
//		
//		return addMarksDto;
//	}
//	

//	public List<AddMarksDto> studentDetailMarks(int examId, int std, int div, int sub, List<StudentMarksDto> studentMarksDto) {
//	    List<AddMarksDto> addMarksDto = null;
//	    List<StudentMarksEntity> saveAddMarks = new ArrayList<>();
//	    List<StudentMarksEntity> save = null;
//
//	    try {
//	        for (StudentMarksDto marks : studentMarksDto) {
//	            StudentMasterEntity studentMasterEntity = studentMasterRepo.findAllByStudentId(marks.getStudentId());
//	            if (studentMasterEntity != null) {
//	                ExamActivationEntity examActivationEntity = examActivationRepo.findByActivationId(marks.getActivationId());
//	                if (examActivationEntity != null) {
//	                	GradeEntity grade=gradeRepo.findByGradeId(marks.getGradeId());
//	                	if(grade != null)
//	                	{
//	                		String grd=grade.getGradeName();
//	                		StudentMarksEntity addMarksEntity = new StudentMarksEntity();
//		                    
//		                    addMarksEntity.setMarksId(marks.getMarksId());
//		                    addMarksEntity.setActivationId(marks.getActivationId());
//		                    addMarksEntity.setStudentId(marks.getStudentId());
//		                    addMarksEntity.setStudentMarks(marks.getStudentMarks());
//                            addMarksEntity.setStudentGrade(grd);
//		                    saveAddMarks.add(addMarksEntity);
//	                	
//	                	}
//	                	else {
//	                		String grd=null;
//StudentMarksEntity addMarksEntity = new StudentMarksEntity();
//		                    
//		                    addMarksEntity.setMarksId(marks.getMarksId());
//		                    addMarksEntity.setActivationId(marks.getActivationId());
//		                    addMarksEntity.setStudentId(marks.getStudentId());
//		                    addMarksEntity.setStudentMarks(marks.getStudentMarks());
//                            addMarksEntity.setStudentGrade(grd);
//		                    saveAddMarks.add(addMarksEntity);
//	                	}
//	                	
//	                		
//	                    // Create a new instance for each iteration
//	                    
//	                    
//	                }
//	            }
//	        }
//
//	        System.out.println(saveAddMarks);
//
//	       
//	            save = addMarksRepo.saveAll(saveAddMarks);
//	            if(save != null)
//	            {
//	            	addMarksDto = getStudentByStdDivSub(examId, std, div, sub);
//	            }
//	            
//	      
//	    } catch (Exception e) {
//	        log.error("Error in studentDetailMarks", e);
//	    }
//
//	    return addMarksDto;
//	}
//	

//	public List<AddMarksDto> studentDetailMarks(int examId, int std, int div, int sub, List<StudentMarksDto> studentMarksDto) {
//	    List<AddMarksDto> addMarksDto = null;
//	    List<StudentMarksEntity> saveAddMarks = new ArrayList<>();
//	    List<StudentMarksEntity> save = null;
//
//	    try {
//	        for (StudentMarksDto marks : studentMarksDto) {
//	            StudentMasterEntity studentMasterEntity = studentMasterRepo.findAllByStudentId(marks.getStudentId());
//	            if (studentMasterEntity != null) {
//	                ExamActivationEntity examActivationEntity = examActivationRepo.findByActivationId(marks.getActivationId());
//	                if (examActivationEntity != null) {
//
//	                    // Default values
//	                    Integer studentMarks = (marks.getStudentMarks() != null) ? marks.getStudentMarks() : -1;
//	                    String studentGrade = null;
//
//	                    if (marks.getGradeId() != null) {  // Only fetch if gradeId is provided
//	                        GradeEntity grade = gradeRepo.findByGradeId(marks.getGradeId());
//	                        if (grade != null) {
//	                            studentGrade = grade.getGradeName();  // Fetch grade name
//	                        }
//	                    }
//
//	                    StudentMarksEntity addMarksEntity = new StudentMarksEntity();
//	                    addMarksEntity.setMarksId(marks.getMarksId());
//	                    addMarksEntity.setActivationId(marks.getActivationId());
//	                    addMarksEntity.setStudentId(marks.getStudentId());
//	                    addMarksEntity.setStudentMarks(studentMarks);  // Save provided value or -1
//	                    addMarksEntity.setStudentGrade(studentGrade);  // Save fetched grade name or null
//	                    
//	                    saveAddMarks.add(addMarksEntity);
//	                }
//	            }
//	        }
//
//	        System.out.println(saveAddMarks);
//
//	        if (!saveAddMarks.isEmpty()) {
//	            save = addMarksRepo.saveAll(saveAddMarks);
//	            if (save != null) {
//	                addMarksDto = getStudentByStdDivSub(examId, std, div, sub);
//	            }
//	        }
//
//	    } catch (Exception e) {
//	        log.error("Error in studentDetailMarks", e);
//	    }
//
//	    return addMarksDto;
//	}
//
//	
	public List<AddMarksDto> studentDetailMarks(int examId, int std, int div, int sub, List<StudentMarksDto> studentMarksDto) {
	    List<AddMarksDto> addMarksDto = null;
	    List<StudentMarksEntity> saveAddMarks = new ArrayList<>();
	    List<StudentMarksEntity> save = null;

	    try {
	        for (StudentMarksDto marks : studentMarksDto) {
	            StudentMasterEntity studentMasterEntity = studentMasterRepo.findAllByStudentId(marks.getStudentId());
	            if (studentMasterEntity != null) {
	                ExamActivationEntity examActivationEntity = examActivationRepo.findByActivationId(marks.getActivationId());
	                if (examActivationEntity != null) {

	                    Integer studentMarks = null;
	                    String studentGrade = null;

	                    // Check if gradeId is provided
	                    if (marks.getGradeId() != null && marks.getGradeId() > 0) {
	                        GradeEntity grade = gradeRepo.findByGradeId(marks.getGradeId());
	                        if (grade != null) {
	                            studentGrade = grade.getGradeName();
	                        }
	                        studentMarks = -1;  // Set marks to -1
	                    } 
	                    // If grade is not provided, check for studentMarks
	                    else if (marks.getStudentMarks() != null && marks.getStudentMarks() >= 0) {
	                        studentMarks = marks.getStudentMarks();
	                        studentGrade = "0";  // Set grade to "0"
	                    }

	                    StudentMarksEntity addMarksEntity = new StudentMarksEntity();
	                    addMarksEntity.setMarksId(marks.getMarksId());
	                    addMarksEntity.setActivationId(marks.getActivationId());
	                    addMarksEntity.setStudentId(marks.getStudentId());
	                    addMarksEntity.setStudentMarks(studentMarks);
	                    addMarksEntity.setStudentGrade(studentGrade);

	                    saveAddMarks.add(addMarksEntity);
	                }
	            }
	        }

	        if (!saveAddMarks.isEmpty()) {
	            save = addMarksRepo.saveAll(saveAddMarks);
	            if (save != null) {
	                addMarksDto = getStudentByStdDivSub(examId, std, div, sub);
	            }
	        }

	    } catch (Exception e) {
	        log.error("Error in studentDetailMarks", e);
	    }

	    return addMarksDto;
	}

	
	 public List<AddMarksDto> getStudentByStdDivSub(int examId, int std, int div, int sub) {
	        try {
	            return addMarksRepo.findStudentMarks(examId, std, div, sub);
	        } catch (Exception e) {
	            log.error("Error fetching student marks", e);
	            return List.of(); // Return an empty list in case of an error
	        }
	    }
	
	public ExamMasterDto updateStatus(int examId)
	{
		ExamMasterDto examMasterDto=null;
		ExamMasterEntity updateStatus=null;
		
		try {
			ExamMasterEntity exam=examMasterRepo.findByExamId(examId);
					if(exam == null)
					{
						throw new Exception("Invalid : ExamId :"+examId);
					}
			if(exam.getState().equals(FabEnum.state.UNDER_CONFIG))
			{
				exam.setState(state.ACTIVE);
				updateStatus=examMasterRepo.save(exam);
			}
			else if (exam.getState().equals(FabEnum.state.ACTIVE)) {
				exam.setState(state.DEACTIVE);
				updateStatus=examMasterRepo.save(exam);
			}
			else if (exam.getState().equals(FabEnum.state.DEACTIVE)) {
				exam.setState(state.ACTIVE);
				updateStatus=examMasterRepo.save(exam);
			}
			else {
				throw new Exception("Invalid : Bro :");
			}
			examMasterDto=new ExamMasterDto(updateStatus);
		}
		catch(Exception e)
		{
			log.error("Error in updateStatus() "+e);
		}
		
		
		return examMasterDto;
		
	}
	
public ExamMasterDto deleteExam(int examId)
{
	ExamMasterDto examMasterDto=null;
	ExamMasterEntity deleteStatus=null;
	try {
		ExamMasterEntity exam=examMasterRepo.findByExamId(examId);
		if(exam != null)
		{
			exam.setState(state.DEACTIVE);
			deleteStatus=examMasterRepo.save(exam);
			
		}
		else {
			
			throw new Exception("Invalid : examId "+examId);
		}
		examMasterDto=new ExamMasterDto(deleteStatus);
	}
	catch(Exception e)
	{
		log.error("Error in deleteStatus()");
	}
	
	return examMasterDto;
			
}




public List<ExamActivationDto> examActivationSearch(String search) {
    if (search == null || search.trim().isEmpty()) {
        return new ArrayList<>(); // Return empty list if input is empty
    }
    
    List<ExamActivationDto> examactivation = new ArrayList<>();
    try {
        List<ExamActivationEntity> examActivationEntity = examActivationRepo.searchByKeyword(search);
        
        if (examActivationEntity != null && !examActivationEntity.isEmpty()) {
            examactivation = examActivationEntity.stream()
                .map(ExamActivationDto::new) // Correct mapping
                .toList();
        }
    } catch (Exception e) {
        log.error("Error in examActivationSearching", e);
    }

    return examactivation;
}

 public List<AddMarksDto> studentMarksSearch(int examId, int std, int div, int sub, String search)
 {
	 try {
		 return addMarksRepo.findStudentMarksWithSearch(examId, std, div, sub, search);
		 }
	 catch(Exception e)
	 {
		 log.error("error while fetching student marks Search "+e);
		 return List.of();
	 }
 }

	
}
