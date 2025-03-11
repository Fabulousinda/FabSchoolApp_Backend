package com.fabiit.fabschoolapp.assign.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fabiit.fabschoolapp.assign.Entity.AssignStudentEntity;
import com.fabiit.fabschoolapp.assign.dto.StudentListAssignDto;
import com.fabiit.fabschoolapp.assign.dto.TeacherListAssignDto;

@Repository
public interface AssignStudentRepo extends JpaRepository<AssignStudentEntity, Integer> {

	 
	 
	 AssignStudentEntity findByStudentId(int studentId);
	 
	 List<AssignStudentEntity> findByStdNameAndDivName(String std, String div);
	 
	 List<AssignStudentEntity> findByStdNameStartsWithAndDivNameStartsWith(String std, String div);
	 
	 List<AssignStudentEntity> findByDivNameStartsWith(String div);
	 
	
	 @Query("""
			    SELECT new com.fabiit.fabschoolapp.assign.dto.StudentListAssignDto (
			       s.studentId,
			       s.name,
			       sf.fatherName,
			       sf.fatherNumber,
			       sf.motherName,
			       sf.motherNumber,
			       COALESCE(asg.assignId, 0),
			       COALESCE(asg.stdName, ''),  
			       COALESCE(asg.divName, '')   
			    ) 
			    FROM StudentMasterEntity s
			    LEFT JOIN s.studentSecondFormEntity sf  
			    LEFT JOIN AssignStudentEntity asg ON s.studentId = asg.studentId
			""")    
			List<StudentListAssignDto> getStudentListAssign();
	 
	 


	 @Query("""
			    SELECT new com.fabiit.fabschoolapp.assign.dto.StudentListAssignDto (
			       s.studentId,
			       s.name,
			       sf.fatherName,
			       sf.fatherNumber,
			       sf.motherName,
			       sf.motherNumber,
			       COALESCE(asg.assignId, 0),
			       COALESCE(asg.stdName, ''),  
			       COALESCE(asg.divName, '')   
			    ) 
			    FROM StudentMasterEntity s
			    LEFT JOIN s.studentSecondFormEntity sf  
			    LEFT JOIN AssignStudentEntity asg ON s.studentId = asg.studentId
			    WHERE asg.stdName = (SELECT st.stdName FROM StandardEntity st WHERE st.standardId = :standardId)
			      AND asg.divName = (SELECT d.divName FROM DivisionEntity d WHERE d.divisionId = :divisionId)
			""")    
			List<StudentListAssignDto> findStudentsByStdDiv(@Param("standardId") int standardId, @Param("divisionId") int divisionId);



//	 @Query("""
//			    SELECT new com.fabiit.fabschoolapp.assign.dto.StudentListAssignDto (
//			       s.studentId,
//			       s.name,
//			       sf.fatherName,
//			       sf.fatherNumber,
//			       sf.motherName,
//			       sf.motherNumber,
//			       COALESCE(asg.assignId, 0),
//			       COALESCE(asg.stdName, ''),  
//			       COALESCE(asg.divName, '')   
//			    ) 
//			    FROM StudentMasterEntity s
//			    LEFT JOIN s.studentSecondFormEntity sf  
//			    LEFT JOIN AssignStudentEntity asg ON s.studentId = asg.studentId
//			    LEFT JOIN StandardEntity st ON asg.stdName = st.stdName
//			    LEFT JOIN DivisionEntity d ON asg.divName = d.divName
//			    WHERE 
//			        (
//			            (:standardId IS NULL OR :divisionId IS NULL) 
//			            OR (st.standardId = :standardId AND d.divisionId = :divisionId)
//			            OR NOT EXISTS (
//			                SELECT 1 FROM AssignStudentEntity a
//			                WHERE a.studentId = s.studentId
//			                AND a.stdName IN (SELECT st2.stdName FROM StandardEntity st2 WHERE st2.standardId = :standardId)
//			                AND a.divName IN (SELECT d2.divName FROM DivisionEntity d2 WHERE d2.divisionId = :divisionId)
//			            )
//			        )
//			        AND (
//			            CAST(s.studentId AS string) LIKE %:searchKeyword% OR
//			            LOWER(s.name) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR
//			            LOWER(sf.fatherName) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR
//			            LOWER(sf.motherName) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR
//			            sf.fatherNumber LIKE %:searchKeyword% OR 
//			            sf.motherNumber LIKE %:searchKeyword% 
//			            
//			        )
//			    ORDER BY s.name ASC
//			""")
//			List<StudentListAssignDto> findStudents(
//			    @Param("standardId") Integer standardId, 
//			    @Param("divisionId") Integer divisionId, 
//			    @Param("searchKeyword") String searchKeyword
//			);

	 @Query("""
			    SELECT new com.fabiit.fabschoolapp.assign.dto.StudentListAssignDto (
			       s.studentId,
			       s.name,
			       sf.fatherName,
			       sf.fatherNumber,
			       sf.motherName,
			       sf.motherNumber,
			       COALESCE(asg.assignId, 0),
			       COALESCE(asg.stdName, ''),  
			       COALESCE(asg.divName, '')   
			    ) 
			    FROM StudentMasterEntity s
			    LEFT JOIN s.studentSecondFormEntity sf  
			    LEFT JOIN AssignStudentEntity asg ON s.studentId = asg.studentId
			    LEFT JOIN StandardEntity st ON asg.stdName = st.stdName
			    LEFT JOIN DivisionEntity d ON asg.divName = d.divName
			    WHERE 
			        (
			            (:standardId IS NULL OR :divisionId IS NULL) 
			            OR (st.standardId = :standardId AND d.divisionId = :divisionId)
			            OR NOT EXISTS (
			                SELECT 1 FROM AssignStudentEntity a
			                WHERE a.studentId = s.studentId
			                AND a.stdName IN (SELECT st2.stdName FROM StandardEntity st2 WHERE st2.standardId = :standardId)
			                AND a.divName IN (SELECT d2.divName FROM DivisionEntity d2 WHERE d2.divisionId = :divisionId)
			            )
			        )
			        AND (
			            CAST(s.studentId AS string) LIKE %:searchKeyword% OR
			            LOWER(s.name) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR
			            LOWER(sf.fatherName) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR
			            LOWER(sf.motherName) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR
			            sf.fatherNumber LIKE %:searchKeyword% OR 
			            sf.motherNumber LIKE %:searchKeyword% OR
			            LOWER(asg.stdName) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR   
			            LOWER(asg.divName) LIKE LOWER(CONCAT('%', :searchKeyword, '%'))    
			        )
			    ORDER BY s.name ASC
			""")
			List<StudentListAssignDto> findStudents(
			    @Param("standardId") Integer standardId, 
			    @Param("divisionId") Integer divisionId, 
			    @Param("searchKeyword") String searchKeyword
			);

	 
	 

}
